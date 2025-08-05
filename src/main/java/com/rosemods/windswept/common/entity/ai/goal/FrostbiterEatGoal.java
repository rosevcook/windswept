package com.rosemods.windswept.common.entity.ai.goal;

import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.rosemods.windswept.core.registry.WindsweptPlayableEndimations;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.EnumSet;

public class FrostbiterEatGoal extends Goal {
    private final Frostbiter frostbiter;
    private int tick;

    public FrostbiterEatGoal(Frostbiter frostbiter) {
        this.frostbiter = frostbiter;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return this.frostbiter.getRandom().nextInt(100) == 0 && this.frostbiter.isNoEndimationPlaying()
                && this.isFoodAt(this.frostbiter.blockPosition()) && !this.frostbiter.isVehicle();
    }

    @Override
    public boolean canContinueToUse() {
        return this.tick > 0;
    }

    @Override
    public void start() {
        this.tick = this.adjustedTickDelay(40);
        this.frostbiter.level().broadcastEntityEvent(this.frostbiter, (byte) 10);
        this.frostbiter.getNavigation().stop();
        NetworkUtil.setPlayingAnimation(this.frostbiter, WindsweptPlayableEndimations.FROSTBITER_EAT);
    }

    @Override
    public void stop() {
        this.tick = 0;
    }

    @Override
    public void tick() {
        this.tick = Math.max(0, this.tick - 1);

        if (this.tick == this.adjustedTickDelay(5)) {
            BlockPos pos = this.frostbiter.blockPosition();

            if (this.isFoodAt(pos) && ForgeEventFactory.getMobGriefingEvent(frostbiter.level(), frostbiter)) {
                this.frostbiter.level().destroyBlock(pos, false);
                this.frostbiter.ate();

                if (this.frostbiter.isBaby())
                    this.frostbiter.ageUp(AgeableMob.getSpeedUpSecondsWhenFeeding(-this.frostbiter.getAge()), true);
                else
                    this.frostbiter.growRandomAntler();
            }
        }

        if (frostbiter.isTame() && tick % 2 == 0 && tick > adjustedTickDelay(10) && tick < adjustedTickDelay(30))
            this.frostbiter.playSound(SoundEvents.BELL_BLOCK, .5f, .5f);

    }

    private boolean isFoodAt(BlockPos pos) {
        return this.frostbiter.level().getBlockState(pos).is(WindsweptBlockTags.FROSTBITER_FOOD);
    }

}

package com.rosemods.windswept.common.entity.ai.goal;

import com.rosemods.windswept.common.entity.animal.Frostbiter;
import com.rosemods.windswept.core.registry.WindsweptPlayableEndimations;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class FrostbiterShakeGoal extends Goal {
    private final Frostbiter frostbiter;
    private int tick;

    public FrostbiterShakeGoal(Frostbiter frostbiter) {
        this.frostbiter = frostbiter;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return this.frostbiter.getRandom().nextInt(10) == 0 &&
                this.frostbiter.isNoEndimationPlaying() &&
                frostbiter.hasAntlers() &&
                !this.frostbiter.isVehicle();
    }

    @Override
    public boolean canContinueToUse() {
        return this.tick > 0;
    }



    @Override
    public void start() {
        this.tick = this.adjustedTickDelay(40);
        this.frostbiter.level.broadcastEntityEvent(this.frostbiter, (byte) 10);
        this.frostbiter.getNavigation().stop();
        this.frostbiter.playSound(SoundEvents.BELL_BLOCK, 1.0f, 0.5f);
        NetworkUtil.setPlayingAnimation(this.frostbiter, WindsweptPlayableEndimations.FROSTBITER_SHAKE);
    }

    @Override
    public void tick() {
        tick = Math.max(0, this.tick - 1);

        if (tick == this.adjustedTickDelay(8)) {
            frostbiter.dropRandomAntler();
        }
    }

    @Override
    public void stop() {
        this.tick = 0;
    }
}

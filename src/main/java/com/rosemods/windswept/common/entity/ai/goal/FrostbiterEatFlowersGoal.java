package com.rosemods.windswept.common.entity.ai.goal;

import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.registry.WindsweptPlayableEndimations;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class FrostbiterEatFlowersGoal extends Goal {
    private final Frostbiter frostbiter;
    private int tick;

    public FrostbiterEatFlowersGoal(Frostbiter frostbiter) {
        this.frostbiter = frostbiter;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return (!this.frostbiter.hasLeftAntler() || !this.frostbiter.hasRightAntler()) && this.isFoodAt(this.getMouthPos());
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
        this.frostbiter.setEyesShut(true);
        NetworkUtil.setPlayingAnimation(this.frostbiter, WindsweptPlayableEndimations.FROSTBITER_EAT);
    }

    @Override
    public void stop() {
        this.tick = 0;
        this.frostbiter.setEyesShut(false);
    }

    @Override
    public void tick() {
        this.tick = Math.max(0, this.tick - 1);

        if (this.tick == this.adjustedTickDelay(2)) {
            BlockPos pos = this.getMouthPos();
            this.frostbiter.setEyesShut(false);

            if (this.isFoodAt(pos)) {
                this.frostbiter.level.destroyBlock(pos, false);
                this.frostbiter.ate();
                this.frostbiter.growRandomAntler();
            }
        }
    }

    private boolean isFoodAt(BlockPos pos) {
        return this.frostbiter.level.getBlockState(pos).is(BlockTags.FLOWERS);
    }

    private BlockPos getMouthPos() {
        return this.frostbiter.blockPosition().relative(this.frostbiter.getDirection());
    }

}

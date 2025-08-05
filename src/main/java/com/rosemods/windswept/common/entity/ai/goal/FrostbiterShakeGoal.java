package com.rosemods.windswept.common.entity.ai.goal;

import com.rosemods.windswept.common.entity.Frostbiter;
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
        return this.frostbiter.getRandom().nextInt(900) == 0
                && this.frostbiter.isNoEndimationPlaying()
                && this.frostbiter.hasAntlers()
                && !this.frostbiter.isVehicle()
                && this.frostbiter.isTame();
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
        NetworkUtil.setPlayingAnimation(this.frostbiter, WindsweptPlayableEndimations.FROSTBITER_SHAKE);
    }

    @Override
    public void stop() {
        this.tick = 0;
    }

    @Override
    public void tick() {
        this.tick = Math.max(0, this.tick - 1);

        if (tick == adjustedTickDelay(16)) this.frostbiter.dropRandomAntler();
        if (tick > adjustedTickDelay(24)) this.frostbiter.spawnAntlerParticle();

        if (frostbiter.isTame() && tick % 2 == 0 && tick > adjustedTickDelay(20) && tick < adjustedTickDelay(30))
            this.frostbiter.playSound(SoundEvents.BELL_BLOCK, .5f, .5f);

    }

}

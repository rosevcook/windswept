package com.rosemods.windswept.common.entity.ai.goal;

import com.rosemods.windswept.common.entity.animal.Frostbiter;
import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import com.rosemods.windswept.core.registry.WindsweptPlayableEndimations;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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
        return this.frostbiter.getRandom().nextInt(1000) == 0 &&
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
        NetworkUtil.setPlayingAnimation(this.frostbiter, WindsweptPlayableEndimations.FROSTBITER_SHAKE);
    }

    @Override
    public void tick() {
        tick = Math.max(0, this.tick - 1);

        if (tick == adjustedTickDelay(16)) {
            frostbiter.dropRandomAntler();
        }

        if (tick > adjustedTickDelay(24)) {
            addRandomParticle();
        }

        if (frostbiter.isTame()) {
            if (tick % 2 == 0 &&
                    tick > adjustedTickDelay(20) &&
                    tick < adjustedTickDelay(30)) {
                this.frostbiter.playSound(SoundEvents.BELL_BLOCK, 0.5f, 0.5f);
            }
        }
    }

    @Override
    public void stop() {
        this.tick = 0;
    }

    private void addRandomParticle() {
        Level level = this.frostbiter.level;

        Vec3 lookAngle = this.frostbiter.getLookAngle();
        for (int i = 0; i < 5; i++) {
            Vec3 vector = new Vec3(frostbiter.getRandomX(0.25), frostbiter.getRandomY(), frostbiter.getRandomZ(0.25))
                    .add(lookAngle);

            if (level instanceof ServerLevel level1) {
                level1.sendParticles(WindsweptParticleTypes.FROST_LEAF.get(),
                        vector.x, vector.y, vector.z, 1,
                        0f,0f, 0f, 0f);
            }
        }
    }
}

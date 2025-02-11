package com.rosemods.windswept.common.entity.projectile;

import com.rosemods.windswept.core.other.WindsweptTrackedData;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.network.PlayMessages;

public class CupidsArrow extends AbstractArrow {

    public CupidsArrow(EntityType<? extends CupidsArrow> type, Level level) {
        super(type, level);
    }

    public CupidsArrow(Level level, double x, double y, double z) {
        super(WindsweptEntityTypes.CUPIDS_ARROW.get(), x, y, z, level);
    }

    public CupidsArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(WindsweptEntityTypes.CUPIDS_ARROW.get(), world);
    }

    public CupidsArrow(Level worldIn, LivingEntity shooter) {
        super(WindsweptEntityTypes.CUPIDS_ARROW.get(), shooter, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.inGround && this.level.isClientSide && this.tickCount > 2) {
            Vec3 motion = this.getDeltaMovement();

            for (int i = 1; i < 3; i++) {
                double px = getX() - motion.x * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double py = getY() - motion.y * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double pz = getZ() - motion.z * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double mx = (Math.random() - .5d) * .03d - motion.x * .08d;
                double my = (Math.random() - .5d) * .03d - motion.y * .08d;
                double mz = (Math.random() - .5d) * .03d - motion.z * .08d;

                this.level.addParticle(WindsweptParticleTypes.CUPIDS_ARROW.get(), px, py, pz, mx, my, mz);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();

        if (entity instanceof Animal animal && animal.canFallInLove() && !animal.isBaby()) {
            IDataManager animalData = (IDataManager) animal;
            animalData.setValue(WindsweptTrackedData.CANNOT_PANIC, true);

            super.onHitEntity(result);
            removePanicFrom(animal);
            animal.setInLove(null);
        }
        else {
            super.onHitEntity(result);
        }

        if (entity instanceof LivingEntity living) {

            if (living.isInvertedHealAndHarm()) {
                DamageSource source = getOwner() == null ? DamageSource.indirectMagic(living, this.getOwner()) : DamageSource.MAGIC;
                living.hurt(source.bypassInvul(), 8.0f);
            }
            else {
                living.heal(6.0f);
            }
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return WindsweptItems.CUPIDS_ARROW.get().getDefaultInstance();
    }

    private static void removePanicFrom(Animal animal) {
        // the goal police does not run

        animal.goalSelector.getRunningGoals().forEach(goal -> {
            if (goal.getGoal() instanceof PanicGoal) {
                goal.stop();
            }
        });
        animal.setLastHurtByMob(null);
        IDataManager animalData = (IDataManager) animal;
        animalData.setValue(WindsweptTrackedData.CANNOT_PANIC, false);
    }

    public static void entityTakeNoDamageIfStruckByArrow(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof CupidsArrow) {
//            event.setAmount(0);
        }
    }
}

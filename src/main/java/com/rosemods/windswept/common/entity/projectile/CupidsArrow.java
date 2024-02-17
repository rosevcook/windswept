package com.rosemods.windswept.common.entity.projectile;

import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
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
        if (result.getEntity() instanceof Animal animal && animal.canFallInLove())
            animal.setInLove(null);

        super.onHitEntity(result);
    }

    @Override
    protected ItemStack getPickupItem() {
        return WindsweptItems.FROST_ARROW.get().getDefaultInstance();
    }
}

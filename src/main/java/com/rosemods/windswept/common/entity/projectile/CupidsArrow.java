package com.rosemods.windswept.common.entity.projectile;

import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.particles.ParticleTypes;
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

            double px = getX() - motion.x * (1f / 3f) + (Math.random() - .5d) * .1d;
            double py = getY() - motion.y * (1f / 3f) + (Math.random() - .5d) * .1d;
            double pz = getZ() - motion.z * (1f / 3f) + (Math.random() - .5d) * .1d;
            double mx = (Math.random() - .5d) * .03d - motion.x * .08d;
            double my = (Math.random() - .5d) * .03d - motion.y * .08d;
            double mz = (Math.random() - .5d) * .03d - motion.z * .08d;

            this.level.addParticle(ParticleTypes.HEART, px, py, pz, mx, my, mz);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (result.getEntity() instanceof Animal animal && animal.canFallInLove())
            animal.setInLove(null);

        //super.onHitEntity(result);
    }

    @Override
    protected ItemStack getPickupItem() {
        return WindsweptItems.FROST_ARROW.get().getDefaultInstance();
    }
}
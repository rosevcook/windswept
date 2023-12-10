package com.rosemods.windswept.common.entity;

import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class FrostArrow extends AbstractArrow {

    public FrostArrow(EntityType<? extends FrostArrow> type, Level level) {
        super(type, level);
    }

    public FrostArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(WindsweptEntityTypes.FROST_ARROW.get(), world);
    }

    public FrostArrow(Level worldIn, LivingEntity shooter) {
        super(WindsweptEntityTypes.FROST_ARROW.get(), shooter, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        if (!inGround && level.isClientSide && tickCount > 2) {
            Vec3 motion = this.getDeltaMovement();

            for (int i = 1; i < 3; i++) {
                double px = getX() - motion.x * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double py = getY() - motion.y * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double pz = getZ() - motion.z * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double mx = (Math.random() - 0.5) * .03d - motion.x * .08d;
                double my = (Math.random() - 0.5) * .03d - motion.y * .08d;
                double mz = (Math.random() - 0.5) * .03d - motion.z * .08d;

                level.addParticle(ParticleTypes.SNOWFLAKE, px, py, pz, mx, my, mz);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (result.getEntity() instanceof LivingEntity livingEntity)
            livingEntity.setTicksFrozen((int) ((livingEntity.getTicksFrozen() + 100) * this.getDeltaMovement().length()));

        super.onHitEntity(result);
    }

    @Override
    protected ItemStack getPickupItem() {
        return WindsweptItems.FROST_ARROW.get().getDefaultInstance();
    }

}

package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.common.block.IWoodenBucketPickupBlock;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin extends Block implements IWoodenBucketPickupBlock {

    protected PowderSnowBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (WindsweptConfig.CLIENT.powderSnowParticles.get() && rand.nextInt(16) == 0) {
            BlockState below = level.getBlockState(pos.below());
            if (below.isAir() || below.is(BlockTags.FIRE) || below.getMaterial().isLiquid() || below.getMaterial().isReplaceable()) {
                double d0 = (double) pos.getX() + rand.nextDouble();
                double d1 = (double) pos.getY() - .05d;
                double d2 = (double) pos.getZ() + rand.nextDouble();

                level.addParticle(ParticleTypes.SNOWFLAKE, d0, d1, d2, 0d, 0d, 0d);
            }
        }
    }

    @Override
    public Item asItem() {
        return Items.POWDER_SNOW_BUCKET;
    }

    @Override
    public Item getWoodenBucketItem() {
        return WindsweptItems.WOODEN_POWDER_SNOW_BUCKET.get();
    }

    @Inject(method = "canEntityWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void canEntityWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (entity instanceof LivingEntity livingEntity && livingEntity.getItemBySlot(EquipmentSlot.FEET).is(WindsweptItems.SNOW_BOOTS.get()))
            info.setReturnValue(true);
    }

}

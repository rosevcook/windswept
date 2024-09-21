package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SnowGolem.class)
public abstract class SnowGolemMixin extends LivingEntity {
    private SnowGolemMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;"))
    private BlockState defaultBlockState(Block block) {
        return this.random.nextInt(15) == 0 ? WindsweptBlocks.SNOWDROP.get().defaultBlockState() : block.defaultBlockState();
    }

}

package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowerBlock.class)
public class FlowerBlockMixin {
    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> info) {
        if (WindsweptConfig.COMMON.biggerFlowerHitbox.get()) {
            Vec3 vec3 = state.getOffset(getter, pos);
            VoxelShape shape = Block.box(3f, 0f, 3f, 13f, 14f, 13f);
            info.setReturnValue(shape.move(vec3.x, vec3.y, vec3.z));
        }
    }

}

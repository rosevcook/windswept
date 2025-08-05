package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import com.rosemods.windswept.core.registry.datapack.WindsweptDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HollyLeavesBlock extends LeavesBlock {
    private static final VoxelShape AABB = box(1f, 1f, 1f, 15f, 15f, 15f);

    public HollyLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entityInside(1.2f, entity, level);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return .2f;
    }

    public static void entityInside(float damage, Entity entity, Level level) {
        if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ()) && entity instanceof LivingEntity && !entity.getType().is(WindsweptEntityTypeTags.HOLLY_IMMUNE) && !(entity instanceof Player player && player.isCrouching())) {
            double d0 = Math.abs(entity.getX() - entity.xOld);
            double d1 = Math.abs(entity.getZ() - entity.zOld);
            if (d0 >= .003d || d1 >= .003d)
                entity.hurt(entity.damageSources().source(WindsweptDamageTypes.HOLLY_LEAVES), damage);
        }
    }

}

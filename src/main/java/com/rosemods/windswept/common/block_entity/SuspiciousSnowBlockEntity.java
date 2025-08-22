package com.rosemods.windswept.common.block_entity;

import com.rosemods.windswept.core.registry.WindsweptBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuspiciousSnowBlockEntity extends BrushableBlockEntity {
    public SuspiciousSnowBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return WindsweptBlockEntities.SUSPICIOUS_SNOW.get();
    }
}

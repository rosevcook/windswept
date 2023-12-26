package com.rosemods.windswept.common.block_entity;

import com.rosemods.windswept.common.block.WillOTheWispBlock;
import com.rosemods.windswept.core.registry.WindsweptBlockEntities;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WillOTheWispBlockEntity extends BlockEntity implements BlockEntityTicker<WillOTheWispBlockEntity> {
    public WillOTheWispBlockEntity(BlockPos pos, BlockState state) {
        super(WindsweptBlockEntities.WILL_O_THE_WISP.get(), pos, state);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, WillOTheWispBlockEntity blockEntity) {
        for (Direction direction : Direction.Plane.HORIZONTAL)
            if (!level.getBlockState(pos.relative(direction)).getMaterial().isSolid()) {
                AABB aabb = new AABB(pos.relative(direction));

                switch (direction) {
                    case NORTH -> aabb = aabb.expandTowards(0d, 0d, -8d);
                    case SOUTH -> aabb = aabb.expandTowards(0d, 0d, 8d);
                    case EAST -> aabb = aabb.expandTowards(8d, 0d, 0d);
                    case WEST -> aabb = aabb.expandTowards(-8d, 0d, 0d);
                }

                for (Player player : level.getEntitiesOfClass(Player.class, aabb))
                    if (player.getDirection() == direction) {
                        if (state.getValue(WillOTheWispBlock.FACING) != direction)
                            level.setBlock(pos, state.setValue(WillOTheWispBlock.FACING, direction), 2);

                        BlockPos.betweenClosedStream(new AABB(pos.relative(direction.getOpposite(), 3)).inflate(2)).forEach(blockPos -> {
                            BlockState blockState = level.getBlockState(blockPos);

                            if ((blockState.is(WindsweptBlocks.WILL_O_THE_WISP.get()) || blockState.is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get())) && blockState.getValue(HorizontalDirectionalBlock.FACING) != direction)
                                level.setBlock(blockPos, blockState.setValue(HorizontalDirectionalBlock.FACING, direction), 2);
                        });

                        return;
                    }
            }
    }

}

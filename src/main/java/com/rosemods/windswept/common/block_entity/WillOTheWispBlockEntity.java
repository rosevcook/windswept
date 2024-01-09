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
            if (state.getValue(HorizontalDirectionalBlock.FACING) != direction && level.getBlockState(pos.relative(direction)).isAir())
                for (Player player : level.getEntitiesOfClass(Player.class, expandTowards(new AABB(pos.relative(direction)), direction, 6)))
                    if (player.getDirection() == direction) {
                        turn(level, pos, state, direction, player);
                        return;
                    }
    }

    private static void turn(Level level, BlockPos pos, BlockState state, Direction direction, Player player) {
        if (state.getValue(HorizontalDirectionalBlock.FACING) != direction)
            level.setBlock(pos, state.setValue(WillOTheWispBlock.FACING, direction), 2);

        int distance = Math.min(Math.abs(pos.get(direction.getAxis()) - player.blockPosition().get(direction.getAxis())), 3);
        AABB aabb = expandTowards(new AABB(pos.relative(direction.getOpposite(), 3)).inflate(2), direction, distance);

        BlockPos.betweenClosedStream(aabb).forEach(blockPos -> {
            BlockState blockState = level.getBlockState(blockPos);

            if (blockState.is(WindsweptBlocks.WILL_O_THE_WISP.get())
                    && blockState.getValue(HorizontalDirectionalBlock.FACING) != direction && level.getBlockState(blockPos.relative(direction)).isAir())
                level.setBlock(blockPos, blockState.setValue(HorizontalDirectionalBlock.FACING, direction), 2);
        });
    }

    private static AABB expandTowards(AABB aabb, Direction direction, double amount) {
        return switch (direction) {
            case UP -> aabb.expandTowards(0d, amount, 0d);
            case DOWN -> aabb.expandTowards(0d, -amount, 0d);
            case NORTH -> aabb.expandTowards(0d, 0d, -amount);
            case SOUTH -> aabb.expandTowards(0d, 0d, amount);
            case EAST -> aabb.expandTowards(amount, 0d, 0d);
            case WEST -> aabb.expandTowards(-amount, 0d, 0d);
        };
    }

}

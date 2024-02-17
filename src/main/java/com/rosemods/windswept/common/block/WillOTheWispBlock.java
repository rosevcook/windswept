package com.rosemods.windswept.common.block;

import com.rosemods.windswept.common.block_entity.WillOTheWispBlockEntity;
import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WillOTheWispBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public WillOTheWispBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WillOTheWispBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (BlockEntityTicker<T>) this.newBlockEntity(new BlockPos(0, 0, 0), state);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();

        for (int l = 0; l < 14; ++l) {
            blockPos.set(i + Mth.nextInt(rand, -10, 10), j - rand.nextInt(10), k + Mth.nextInt(rand, -10, 10));
            BlockState blockstate = level.getBlockState(blockPos);

            if (!blockstate.isCollisionShapeFullBlock(level, blockPos))
                level.addParticle(WindsweptParticleTypes.WILL_O_THE_WISP.get(), (double) blockPos.getX() + rand.nextDouble(), (double) blockPos.getY() + rand.nextDouble(), (double) blockPos.getZ() + rand.nextDouble(), 0d, 0d, 0d);

        }
    }

}

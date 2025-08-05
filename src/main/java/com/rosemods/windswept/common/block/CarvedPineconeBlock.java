package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class CarvedPineconeBlock extends HorizontalDirectionalBlock {
    private static final int[] KEY = new int[]{0, 3, 5, 10, 14, 15, 21, 22};

    public CarvedPineconeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.blockEvent(pos, this, 0, 0);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.hasNeighborSignal(pos))
            level.blockEvent(pos, this, 0, 0);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        return player.getItemInHand(hand).isEmpty() && this.triggerEvent(state, level, pos, 0, 0) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int i0, int i1) {
        if (!level.getBlockState(pos.relative(state.getValue(FACING))).isSolid()) {
            int below = 0;
            int above = 0;

            for (; isPinecone(level.getBlockState(pos.below(below + 1))); below++) ;
            for (; isPinecone(level.getBlockState(pos.above(above + 1))); above++) ;

            int pitch = KEY[(KEY.length - 1) - ((below + above) % KEY.length)];

            level.playSound(null, pos, WindsweptSounds.PINECONE_NOTE.get(), SoundSource.RECORDS, .35f, (float) Math.pow(2d, (pitch - 10d) / 12d));
            level.addParticle(ParticleTypes.NOTE, (double) pos.getX() + .5d, (double) pos.getY() + (double) above + 1.2d, (double) pos.getZ() + .5d, (double) pitch / KEY.length, 0d, 0d);

            return true;
        }

        return false;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos nextPos, boolean p_60514_) {
        if (!level.isClientSide && level.hasNeighborSignal(pos))
            level.scheduleTick(pos, this, 2);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private static boolean isPinecone(BlockState state) {
        return state.is(WindsweptBlocks.PINECONE_BLOCK.get()) || state.is(WindsweptBlocks.PINECONE_SHINGLES.get());
    }

}

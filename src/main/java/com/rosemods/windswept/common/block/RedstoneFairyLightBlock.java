package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RedstoneFairyLightBlock extends PineconeBlock {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public RedstoneFairyLightBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AMOUNT, 1).setValue(LIT, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (player.getItemInHand(hand).isEmpty()) {
            boolean lit = state.getValue(LIT);
            level.setBlock(pos, state.setValue(LIT, !lit), 3);
            level.playSound(player, pos, lit ? SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF : SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS);


            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return direction == Direction.DOWN && state.getValue(LIT) ? Math.min(state.getValue(AMOUNT) * 4, 15) : 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return state.getValue(LIT);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AMOUNT, LIT);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double d0 = (double)pos.getX() + .5d + (random.nextDouble() - .5d) * .8d;
            double d1 = (double)pos.getY() + .7d + (random.nextDouble() - .5d) * .3d;
            double d2 = (double)pos.getZ() + .5d + (random.nextDouble() - .5d) * .8d;
            level.addParticle(DustParticleOptions.REDSTONE, d0, d1, d2, 0d, 0d, 0d);
        }
    }

}

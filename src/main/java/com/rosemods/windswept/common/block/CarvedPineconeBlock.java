package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class CarvedPineconeBlock extends HorizontalDirectionalBlock implements Wearable {
    public CarvedPineconeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.blockEvent(pos, this, 0, 0);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        return player.getItemInHand(hand).isEmpty() && this.triggerEvent(state, level, pos, 0, 0) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int i0, int i1) {
        if (level.getBlockState(pos.above()).getMaterial().isReplaceable()) {
            int height = 0;

            while (height < level.getMaxBuildHeight() - pos.getY()) {
                BlockState below = level.getBlockState(pos.below(height + 1));

                if (!below.is(WindsweptBlocks.PINECONE_BLOCK.get()) && !below.is(WindsweptBlocks.WILL_O_THE_WISP.get())
                        && !below.is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get()) && !below.is(WindsweptBlocks.PINECONE_SHINGLES.get()))
                    break;
                else
                    height++;
            }

            if (height > 0) {
                int pitch = getPitch(height - 1);

                level.playSound(null, pos, WindsweptSounds.PINECONE_NOTE.get(), SoundSource.RECORDS, .35f, (float) Math.pow(2d, (double) (pitch - 12) / 12d));
                level.addParticle(ParticleTypes.NOTE, (double) pos.getX() + .5d, (double) pos.getY() + 1.2d, (double) pos.getZ() + .5d, (double) pitch / 14d, 0d, 0d);

                return true;
            }
        }

        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private static int getPitch(int height) {
        int[] key = new int[]{0, 2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 19, 20, 22};
        return key[height % key.length];
    }

}

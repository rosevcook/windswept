package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class RoseFlowerBlock extends FlowerBlock implements BonemealableBlock {
    private final Supplier<Block> tall;

    public RoseFlowerBlock(Supplier<Block> tall, Supplier<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
        super(stewEffect, stewEffectDuration, properties);
        this.tall = tall;
    }

    public static void grow(WorldGenLevel level, BlockPos pos, Block tallFlower) {
        TallFlowerBlock.placeAt(level, tallFlower.defaultBlockState(), pos, 2);
    }

    public Block getBushVariant() {
        return this.tall.get();
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return levelReader.getBlockState(blockPos.above()).canBeReplaced();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        grow(level, pos, this.tall.get());
    }

}

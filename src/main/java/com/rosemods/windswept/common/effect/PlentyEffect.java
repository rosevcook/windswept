package com.rosemods.windswept.common.effect;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Objects;

public class PlentyEffect extends BlueprintMobEffect {
    protected PlentyEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    public static void tryLeavePlentyFlowers(Level level, LivingEntity player, BlockPos realPos, int plentyAmp) {
        // affected blocks should find the farmland and also empty grass blocks,
        List<BlockState> flowerStates = flowersThatCanSpawnInBiome(level, realPos);

        double modifier = 1 + (plentyAmp * 0.5);

        for (BlockPos affectedPos : getToPlacePos(player)) {
            // account for jumping (maybe?)
            if (!player.isOnGround() && level.getBlockState(affectedPos).isAir()) {
                affectedPos = affectedPos.below();
            }

//            if (player.getItemInHand(InteractionHand.MAIN_HAND).is(Items.ACACIA_BOAT)) {
//                level.setBlock(affectedPos, Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
//                continue;
//            }

            if (level.getRandom().nextInt((int) (20 / modifier)) == 0) {
                BlockPos abovePos = affectedPos.above();
                BlockState aboveState = level.getBlockState(abovePos);

                boolean didPlace = false;

                if (aboveState.getBlock() instanceof CropBlock)  {
                    int age = aboveState.getValue(CropBlock.AGE);
                    age = Math.min(CropBlock.MAX_AGE, age + level.random.nextInt(1, 4));
                    level.setBlock(abovePos, aboveState.setValue(CropBlock.AGE, age), Block.UPDATE_ALL);

                    level.levelEvent(1505, abovePos, 0);
                    didPlace = true;
                }
                else {
                    BlockState flower = flowerStates.get(level.random.nextInt(flowerStates.size()));
                    // idk should it maybe work if there's snow covering
                    if (flower.canSurvive(level, abovePos) && aboveState.isAir()) {
                        if (level.getBlockState(affectedPos).is(BlockTags.CONVERTABLE_TO_MUD))
                            level.setBlock(affectedPos, Blocks.GRASS_BLOCK.defaultBlockState(), Block.UPDATE_ALL);

                        level.setBlock(abovePos, flower, Block.UPDATE_ALL);

                        for (Direction dir : Direction.Plane.HORIZONTAL)
                            if (level.random.nextInt(8) == 0)
                                randomlyPlaceGrass(level, abovePos.relative(dir));

                        didPlace = true;
                        level.levelEvent(1505, abovePos, 0);
                    }
                }

                // add particles please please please fuck holy shit
                if (level instanceof ServerLevel sLevel && didPlace)
                    for (int i = 0; i < 9; i++) {
                        double d0 = level.random.nextGaussian() * 0.02D;
                        double d1 = level.random.nextGaussian() * 0.02D;
                        double d2 = level.random.nextGaussian() * 0.02D;

                        sLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, player.getRandomX(1.0D),
                                player.getRandomY() + 0.5D, player.getRandomZ(1.0D), 1, d0, d1, d2, 0);
                    }

                return;
            }
        }
    }

    private static Iterable<BlockPos> getToPlacePos(LivingEntity player) {
        Vec3 playerVec = player.position();

        // account for slabs
        double extraY = playerVec.y - Mth.floor(playerVec.y);
        if (!player.isOnGround() || extraY <= 0.4) {
            playerVec = playerVec.add(0, -1, 0);
        }

        // default constructor floors everything
        BlockPos lowerPos = new BlockPos(playerVec);
        BlockPos upperPos = new BlockPos(Mth.ceil(playerVec.x), Mth.floor(playerVec.y), Mth.ceil(playerVec.z));

        return BlockPos.betweenClosed(lowerPos, upperPos);
    }

    private static void randomlyPlaceGrass(Level level, BlockPos pos) {
        if (level.random.nextInt(8) == 0) {
            BlockState replaceState = level.getBlockState(pos);
            BlockState aboveState = level.getBlockState(pos.above());

            BlockState tallGrass = Blocks.TALL_GRASS.defaultBlockState();
            if (replaceState.isAir() && aboveState.isAir() && tallGrass.canSurvive(level, pos)) {
                DoublePlantBlock.placeAt(level, tallGrass, pos, 3);
                level.levelEvent(1505, pos, 0);
            }
        }
        else {
            BlockState grass = Blocks.GRASS.defaultBlockState();
            if (level.getBlockState(pos).isAir() && grass.canSurvive(level, pos)) {
                level.setBlock(pos, grass, 3);
                level.levelEvent(1505, pos, 0);
            }
        }

        if (level.getBlockState(pos.below()).is(BlockTags.CONVERTABLE_TO_MUD)) {
            level.setBlock(pos.below(), Blocks.GRASS_BLOCK.defaultBlockState(), Block.UPDATE_ALL);
        }
    }

    private static List<BlockState> flowersThatCanSpawnInBiome(Level level, BlockPos pos) {
        ImmutableList<BlockState> potentialList = level.getBiome(pos).value().getGenerationSettings().features().stream()
                .flatMap(HolderSet::stream).map(Holder::value).flatMap(PlacedFeature::getFeatures)
                .filter(feature -> feature.feature() == Feature.FLOWER)
                .map(feature -> {
                    if (feature.config() instanceof RandomPatchConfiguration config) {
                        if (config.feature().value().feature().value().config() instanceof SimpleBlockConfiguration blockConfiguration) {
                            return blockConfiguration.toPlace().getState(level.random, pos);
                        } else return null;
                    } else return null;
                }).filter(Objects::nonNull).collect(ImmutableList.toImmutableList());

        return potentialList.isEmpty() ? List.of(Blocks.POPPY.defaultBlockState(), Blocks.DANDELION.defaultBlockState(), Blocks.CORNFLOWER.defaultBlockState(),
                Blocks.AZURE_BLUET.defaultBlockState(), Blocks.OXEYE_DAISY.defaultBlockState()) : potentialList;

    }
}

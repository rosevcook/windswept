package com.rosemods.windswept.common.level.gen.tree.trunk_placer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptTrunkPlacers;
import com.teamabnormals.blueprint.common.block.wood.LogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class ChestnutTrunkPlacer extends TrunkPlacer {
    public static final Codec<ChestnutTrunkPlacer> CODEC = RecordCodecBuilder.create(placer -> trunkPlacerParts(placer).apply(placer, ChestnutTrunkPlacer::new));

    public ChestnutTrunkPlacer() {
        super(0, 0, 0);
    }

    private ChestnutTrunkPlacer(int a, int b, int c) {
        this();
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return WindsweptTrunkPlacers.CHESTNUT_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, int height, BlockPos pos, TreeConfiguration config) {
        List<FoliageAttachment> list = Lists.newArrayList();

        // trunk
        int h = rand.nextInt(3, 9);
        list.add(new FoliagePlacer.FoliageAttachment(pos.offset(0, h + 2, 0), 0, false));

        for (int y = 1; y < h; y++)
            this.placeLog(level, consumer, rand, pos.offset(0, y, 0), config);

        // branches
        List<Integer> raisedX = Lists.newArrayList();
        List<Integer> raisedZ = Lists.newArrayList();
        for (int x = -2; x <= 2; x++)
            for (int z = -2; z <= 2; z++)
                if (x == 0 || z == 0) {
                    int y = h;
                    int finalX = x;
                    int finalZ = z;

                    if (Math.abs(x) == 2 || Math.abs(z) == 2) {
                        y++;
                        int posX = x;
                        int posZ = z;
                        int posY = y;

                        if (rand.nextFloat() < .4f) {
                            boolean success = false;
                            if (x != 0 && rand.nextBoolean()) {
                                posX += x > 0 ? 1 : -1;
                                success = true;
                            } else if (z != 0 && rand.nextBoolean()) {
                                posZ += z > 0 ? 1 : -1;
                                success = true;
                            }

                            if (success && rand.nextInt(5) == 0) {
                                posY++;
                                if (x != 0) raisedX.add((int) Math.signum(x));
                                else raisedZ.add((int) Math.signum(z));
                            }

                            this.placeLog(level, consumer, rand, pos.offset(posX, posY, posZ), config, s -> s.setValue(LogBlock.AXIS, finalX != 0 ? Direction.Axis.X : Direction.Axis.Z));
                        }

                        list.add(new FoliagePlacer.FoliageAttachment(pos.offset(posX, posY, posZ), 0, false));
                    }

                    this.placeLog(level, consumer, rand, pos.offset(x, y, z), config, s -> finalX != 0 || finalZ != 0 ? s.setValue(LogBlock.AXIS, finalX != 0 ? Direction.Axis.X : Direction.Axis.Z) : s);
                }

        // roots
        for (int x = -1; x <= 1; x++)
            for (int z = -1; z <= 1; z++)
                if (x == 0 || z == 0) {
                    this.placeLog(level, consumer, rand, pos.offset(x, 0, z), config);
                    int grassCheck = -1;

                    if (level.isStateAtPosition(pos.offset(x, -1, z), state -> !state.getMaterial().isSolid())) {
                        this.placeLog(level, consumer, rand, pos.offset(x, -1, z), config);
                        grassCheck--;

                        if (level.isStateAtPosition(pos.offset(x, -2, z), state -> state.getMaterial().isReplaceable()) && WindsweptConfig.COMMON.roots.get())
                            consumer.accept(pos.offset(x, -2, z), Blocks.HANGING_ROOTS.defaultBlockState());
                    }

                    if (raisedX.contains(x) || raisedZ.contains(z) || rand.nextInt(32) == 0)
                        this.placeLog(level, consumer, rand, pos.offset(x, 1, z), config);

                    if (level.isStateAtPosition(pos.offset(x, grassCheck, z), state -> state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.PODZOL) || state.is(Blocks.MYCELIUM) || state.is(WindsweptBlocks.GELISOL.get())))
                        setDirtAt(level, consumer, rand, pos.offset(x, grassCheck, z), config);

                }

        return list;
    }

}

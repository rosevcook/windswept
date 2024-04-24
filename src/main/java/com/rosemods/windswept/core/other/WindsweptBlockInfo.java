package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.world.level.block.Blocks;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public final class WindsweptBlockInfo {

    public static void changeLocalisation() {
        DataUtil.changeBlockLocalization(Blocks.SNOW, Windswept.MOD_ID, "snow_carpet");
        DataUtil.changeBlockLocalization(Blocks.SNOW_BLOCK, "minecraft", "snow");
    }

    public static void registerCompostables() {
        DataUtil.registerCompostable(WILD_BERRIES.get(), .3f);
        DataUtil.registerCompostable(WILD_BERRY_PIPS.get(), .3f);
        DataUtil.registerCompostable(HOLLY_BERRIES.get(), .3f);
        DataUtil.registerCompostable(CHESTNUTS.get(), .3f);
        DataUtil.registerCompostable(GINGER_ROOT.get(), .3f);
        DataUtil.registerCompostable(ROASTED_CHESTNUTS.get(), .3f);
        DataUtil.registerCompostable(PINECONE.get(), .3f);

        DataUtil.registerCompostable(CHESTNUT_CRATE.get(), 1f);
        DataUtil.registerCompostable(ROASTED_CHESTNUT_CRATE.get(), 1f);
        DataUtil.registerCompostable(GINGER_ROOT_CRATE.get(), 1f);
        DataUtil.registerCompostable(HOLLY_BERRY_BASKET.get(), 1f);
        DataUtil.registerCompostable(WILD_BERRY_BASKET.get(), 1f);
        DataUtil.registerCompostable(RED_MUSHROOM_BASKET.get(), 1f);
        DataUtil.registerCompostable(BROWN_MUSHROOM_BASKET.get(), 1f);
        DataUtil.registerCompostable(GLOW_SHROOM_BASKET.get(), 1f);

        DataUtil.registerCompostable(RED_ROSE.get(), .65f);
        DataUtil.registerCompostable(BLUE_ROSE.get(), .65f);
        DataUtil.registerCompostable(WHITE_ROSE.get(), .65f);
        DataUtil.registerCompostable(YELLOW_ROSE.get(), .65f);
        DataUtil.registerCompostable(FOXGLOVE.get(), .65f);
        DataUtil.registerCompostable(SNOWDROP.get(), .65f);
        DataUtil.registerCompostable(MOSS_CAMPION.get(), .65f);
        DataUtil.registerCompostable(NIGHTSHADE.get(), .65f);
        DataUtil.registerCompostable(BLUEBELLS.get(), .65f);

        DataUtil.registerCompostable(RED_ROSE_BUSH.get(), .65f);
        DataUtil.registerCompostable(BLUE_ROSE_BUSH.get(), .65f);
        DataUtil.registerCompostable(WHITE_ROSE_BUSH.get(), .65f);
        DataUtil.registerCompostable(YELLOW_ROSE_BUSH.get(), .65f);
        DataUtil.registerCompostable(LUPINE.get(), .65f);

        DataUtil.registerCompostable(HOLLY_LEAVES.get(), .3f);
        DataUtil.registerCompostable(CHESTNUT_LEAVES.get(), .3f);
        DataUtil.registerCompostable(PINE_LEAVES.get(), .3f);
        DataUtil.registerCompostable(HOLLY_SAPLING.get(), .3f);
        DataUtil.registerCompostable(CHESTNUT_SAPLING.get(), .3f);
        DataUtil.registerCompostable(PINE_SAPLING.get(), .3f);
        DataUtil.registerCompostable(HOLLY_LEAF_CARPET.get(), .3f);
        DataUtil.registerCompostable(CHESTNUT_LEAF_CARPET.get(), .3f);
        DataUtil.registerCompostable(PINE_LEAF_CARPET.get(), .3f);

        DataUtil.registerCompostable(SNOWY_SPROUTS.get(), .5f);
        DataUtil.registerCompostable(GELISOL_SPROUTS.get(), .5f);
        DataUtil.registerCompostable(DRY_MOSSY_SPROUTS.get(), .5f);
        DataUtil.registerCompostable(MOSSY_SPROUTS.get(), .5f);

        DataUtil.registerCompostable(WILD_BERRY_COOKIE.get(), .85f);
        DataUtil.registerCompostable(GINGERBREAD_COOKIE.get(), .85f);
        DataUtil.registerCompostable(MUTTON_PIE.get(), 1f);
        DataUtil.registerCompostable(CHRISTMAS_PUDDING.get(), 1f);
        DataUtil.registerCompostable(CHRISTMAS_PUDDING_SLICE.get(), .85f);

        DataUtil.registerCompostable(PINECONE_BLOCK.get(), 1f);
        DataUtil.registerCompostable(PINECONE_JAM_BLOCK.get(), 1f);
    }

    public static void registerFlammables() {
        DataUtil.registerFlammable(STRIPPED_HOLLY_LOG.get(), 5, 5);
        DataUtil.registerFlammable(STRIPPED_HOLLY_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(HOLLY_LOG.get(), 5, 5);
        DataUtil.registerFlammable(HOLLY_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(HOLLY_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(VERTICAL_HOLLY_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_BOARDS.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(HOLLY_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(HOLLY_VERTICAL_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_BOOKSHELF.get(), 30, 20);
        DataUtil.registerFlammable(HOLLY_BEEHIVE.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_POST.get(), 5, 20);
        DataUtil.registerFlammable(STRIPPED_HOLLY_POST.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_LEAF_PILE.get(), 30, 60);

        DataUtil.registerFlammable(STRIPPED_CHESTNUT_LOG.get(), 5, 5);
        DataUtil.registerFlammable(STRIPPED_CHESTNUT_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(CHESTNUT_LOG.get(), 5, 5);
        DataUtil.registerFlammable(CHESTNUT_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(CHESTNUT_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(VERTICAL_CHESTNUT_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_BOARDS.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(CHESTNUT_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(CHESTNUT_VERTICAL_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_BOOKSHELF.get(), 30, 20);
        DataUtil.registerFlammable(CHESTNUT_BEEHIVE.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_POST.get(), 5, 20);
        DataUtil.registerFlammable(STRIPPED_CHESTNUT_POST.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_LEAF_PILE.get(), 30, 60);

        DataUtil.registerFlammable(STRIPPED_PINE_LOG.get(), 5, 5);
        DataUtil.registerFlammable(STRIPPED_PINE_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(PINE_LOG.get(), 5, 5);
        DataUtil.registerFlammable(PINE_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(PINE_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(VERTICAL_PINE_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(PINE_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(PINE_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(PINE_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(PINE_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(PINE_BOARDS.get(), 5, 20);
        DataUtil.registerFlammable(PINE_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(PINE_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(PINE_VERTICAL_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(PINE_BOOKSHELF.get(), 30, 20);
        DataUtil.registerFlammable(PINE_BEEHIVE.get(), 5, 20);
        DataUtil.registerFlammable(PINE_POST.get(), 5, 20);
        DataUtil.registerFlammable(STRIPPED_PINE_POST.get(), 5, 20);
        DataUtil.registerFlammable(PINE_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(PINE_LEAF_PILE.get(), 30, 60);

        DataUtil.registerFlammable(PINECONE_BLOCK.get(), 5, 20);
        DataUtil.registerFlammable(CARVED_PINECONE_BLOCK.get(), 5, 20);
        DataUtil.registerFlammable(PINECONE_SHINGLES.get(), 5, 20);
        DataUtil.registerFlammable(PINECONE_SHINGLE_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(PINECONE_SHINGLE_SLAB.get(), 5, 20);

        DataUtil.registerFlammable(HOLLY_BERRY_BASKET.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_CRATE.get(), 5, 20);
        DataUtil.registerFlammable(GINGER_ROOT_CRATE.get(), 5, 20);
        DataUtil.registerFlammable(ROASTED_CHESTNUT_CRATE.get(), 5, 20);
        DataUtil.registerFlammable(RED_MUSHROOM_BASKET.get(), 5, 20);
        DataUtil.registerFlammable(BROWN_MUSHROOM_BASKET.get(), 5, 20);
        DataUtil.registerFlammable(GLOW_SHROOM_BASKET.get(), 5, 20);

        DataUtil.registerFlammable(WILD_BERRY_BUSH.get(), 60, 100);
        DataUtil.registerFlammable(SNOWY_SPROUTS.get(), 60, 100);
        DataUtil.registerFlammable(GELISOL_SPROUTS.get(), 60, 100);
        DataUtil.registerFlammable(DRY_MOSSY_SPROUTS.get(), 60, 100);
        DataUtil.registerFlammable(RED_ROSE.get(), 60, 100);
        DataUtil.registerFlammable(BLUE_ROSE.get(), 60, 100);
        DataUtil.registerFlammable(WHITE_ROSE.get(), 60, 100);
        DataUtil.registerFlammable(YELLOW_ROSE.get(), 60, 100);
        DataUtil.registerFlammable(FOXGLOVE.get(), 60, 100);
        DataUtil.registerFlammable(SNOWDROP.get(), 60, 100);
        DataUtil.registerFlammable(MOSS_CAMPION.get(), 60, 100);
        DataUtil.registerFlammable(NIGHTSHADE.get(), 60, 100);
        DataUtil.registerFlammable(BLUEBELLS.get(), 60, 100);
        DataUtil.registerFlammable(LUPINE.get(), 60, 100);
        DataUtil.registerFlammable(RED_ROSE_BUSH.get(), 60, 100);
        DataUtil.registerFlammable(BLUE_ROSE_BUSH.get(), 60, 100);
        DataUtil.registerFlammable(WHITE_ROSE_BUSH.get(), 60, 100);
        DataUtil.registerFlammable(YELLOW_ROSE_BUSH.get(), 60, 100);
    }

}

package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;

public class WindsweptBlockTagProvider extends BlockTagsProvider {

    public WindsweptBlockTagProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        //windswept
        this.tag(WindsweptBlockTags.HOLLY_LOGS).add(HOLLY_LOG.get(), HOLLY_WOOD.get(), STRIPPED_HOLLY_LOG.get(), STRIPPED_HOLLY_WOOD.get());
        this.tag(WindsweptBlockTags.CHESTNUT_LOGS).add(CHESTNUT_LOG.get(), CHESTNUT_WOOD.get(), STRIPPED_CHESTNUT_LOG.get(), STRIPPED_CHESTNUT_WOOD.get());
        this.tag(WindsweptBlockTags.PINE_LOGS).add(PINE_LOG.get(), PINE_WOOD.get(), WEATHERED_PINE_LOG.get(), WEATHERED_PINE_WOOD.get(), STRIPPED_PINE_LOG.get(), STRIPPED_PINE_WOOD.get());
        this.tag(WindsweptBlockTags.DOLOMITE).add(DOLOMITE.get(), DOLOMITE_SLAB.get(), DOLOMITE_STAIRS.get(), DOLOMITE_WALL.get(), DOLOMITE_VERTICAL_SLAB.get());
        this.tag(WindsweptBlockTags.SNOW_BOOTS_BLOCKS).addTag(BlockTags.SNOW).add(SNOW_BRICKS.get(), SNOW_BRICK_SLAB.get(), SNOW_BRICK_WALL.get(), SNOW_BRICK_VERTICAL_SLAB.get());
        this.tag(WindsweptBlockTags.DEFAULT_WHITE_TEXT).add(HOLLY_SIGNS.getFirst().get(), HOLLY_SIGNS.getSecond().get(), Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_WALL_SIGN)
                .addOptional(new ResourceLocation("environmental", "cherry_sign"))
                .addOptional(new ResourceLocation("environmental", "cherry_wall_sign"))
                .addOptional(new ResourceLocation("atmospheric", "grimwood_sign"))
                .addOptional(new ResourceLocation("atmospheric", "grimwood_wall_sign"))
                .addOptional(new ResourceLocation("caverns_and_chasms", "azalea_sign"))
                .addOptional(new ResourceLocation("caverns_and_chasms", "azalea_wall_sign"));

        //woodworks
        this.tag(BlueprintBlockTags.LEAF_PILES).add(HOLLY_LEAF_PILE.get(), CHESTNUT_LEAF_PILE.get());

        //quark
        this.tag(BlueprintBlockTags.HEDGES).add(HOLLY_HEDGE.get(), CHESTNUT_HEDGE.get(), PINE_HEDGE.get());
        this.tag(BlueprintBlockTags.LADDERS).add(HOLLY_LADDER.get(), CHESTNUT_LADDER.get(), PINE_LADDER.get());
        this.tag(BlueprintBlockTags.VERTICAL_SLABS).add(BLUE_ICE_BRICK_VERTICAL_SLAB.get(), HOLLY_VERTICAL_SLAB.get(), SNOW_BRICK_VERTICAL_SLAB.get(), CHESTNUT_VERTICAL_SLAB.get(), PINE_VERTICAL_SLAB.get(), PACKED_ICE_BRICK_VERTICAL_SLAB.get(), PACKED_ICE_VERTICAL_SLAB.get(), BLUE_ICE_VERTICAL_SLAB.get(), SNOW_VERTICAL_SLAB.get(), DRY_MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), DRY_MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), SHALE_VERTICAL_SLAB.get(), POLISHED_SHALE_VERTICAL_SLAB.get(), POLISHED_SHALE_BRICK_VERTICAL_SLAB.get(), PINECONE_SHINGLE_VERTICAL_SLAB.get(), DOLOMITE_VERTICAL_SLAB.get(), CUT_DOLOMITE_VERTICAL_SLAB.get(), CUT_DOLOMITE_BRICK_VERTICAL_SLAB.get(), SMOOTH_DOLOMITE_VERTICAL_SLAB.get(), GINGERBREAD_BRICK_VERTICAL_SLAB.get(), GLAZED_GINGERBREAD_BRICK_VERTICAL_SLAB.get());
        this.tag(BlueprintBlockTags.WOODEN_VERTICAL_SLABS).add(HOLLY_VERTICAL_SLAB.get(), CHESTNUT_VERTICAL_SLAB.get(), PINE_VERTICAL_SLAB.get());

        //forge
        this.tag(Tags.Blocks.CHESTS_WOODEN).add(HOLLY_CHEST.get(), HOLLY_TRAPPED_CHEST.get(), CHESTNUT_CHEST.get(), CHESTNUT_TRAPPED_CHEST.get(), PINE_CHEST.get(), PINE_TRAPPED_CHEST.get());
        this.tag(Tags.Blocks.CHESTS_TRAPPED).add(HOLLY_TRAPPED_CHEST.get(), CHESTNUT_TRAPPED_CHEST.get(), PINE_TRAPPED_CHEST.get());
        this.tag(Tags.Blocks.STONE).add(SHALE.get());

        //vanilla
        this.tag(BlockTags.STONE_ORE_REPLACEABLES).add(SHALE.get());
        this.tag(BlockTags.BASE_STONE_OVERWORLD).add(SHALE.get());
        this.tag(BlockTags.VALID_SPAWN).add(GELISOL.get(), DRY_MOSS_BLOCK.get());
        this.tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(GELISOL.get(), DRY_MOSS_BLOCK.get());
        this.tag(BlockTags.FOXES_SPAWNABLE_ON).add(GELISOL.get(), DRY_MOSS_BLOCK.get());
        this.tag(BlockTags.GOATS_SPAWNABLE_ON).add(GELISOL.get(), DRY_MOSS_BLOCK.get());
        this.tag(BlockTags.WOLVES_SPAWNABLE_ON).add(GELISOL.get(), DRY_MOSS_BLOCK.get());
        this.tag(BlockTags.DIRT).add(DRY_MOSS_BLOCK.get(), GELISOL.get(), GELISOL_PATH.get(), GINGER_SOIL.get());
        this.tag(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON).add(HOLLY_LEAVES.get());
        this.tag(BlockTags.WALLS).add(BLUE_ICE_BRICK_WALL.get(), SNOW_BRICK_WALL.get(), PACKED_ICE_BRICK_WALL.get(), DRY_MOSSY_COBBLESTONE_WALL.get(), DRY_MOSSY_STONE_BRICK_WALL.get(), SHALE_WALL.get(), POLISHED_SHALE_WALL.get(), POLISHED_SHALE_BRICK_WALL.get(), PINECONE_SHINGLE_WALL.get(), DOLOMITE_WALL.get(), CUT_DOLOMITE_WALL.get(), CUT_DOLOMITE_BRICK_WALL.get(), GINGERBREAD_BRICK_WALL.get(), GLAZED_GINGERBREAD_BRICK_WALL.get());
        this.tag(BlockTags.BEEHIVES).add(HOLLY_BEEHIVE.get(), CHESTNUT_BEEHIVE.get(), PINE_BEEHIVE.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(HOLLY_BUTTON.get(), CHESTNUT_BUTTON.get(), PINE_BUTTON.get());
        this.tag(BlockTags.BUTTONS).add(POLISHED_DEEPSLATE_BUTTON.get(), POLISHED_SHALE_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(HOLLY_PRESSURE_PLATE.get(), CHESTNUT_PRESSURE_PLATE.get(), PINE_PRESSURE_PLATE.get());
        this.tag(BlockTags.STONE_PRESSURE_PLATES).add(POLISHED_DEEPSLATE_PRESSURE_PLATE.get(), POLISHED_SHALE_PRESSURE_PLATE.get());
        this.tag(BlockTags.PLANKS).add(HOLLY_PLANKS.get(), CHESTNUT_PLANKS.get(), PINE_PLANKS.get(), VERTICAL_HOLLY_PLANKS.get(), VERTICAL_CHESTNUT_PLANKS.get(), VERTICAL_PINE_PLANKS.get());
        this.tag(BlockTags.WOODEN_DOORS).add(HOLLY_DOOR.get(), CHESTNUT_DOOR.get(), PINE_DOOR.get());
        this.tag(BlockTags.DOORS).add(ICICLE_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(HOLLY_TRAPDOOR.get(), CHESTNUT_TRAPDOOR.get(), PINE_TRAPDOOR.get());
        this.tag(BlockTags.TRAPDOORS).add(ICICLE_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(HOLLY_STAIRS.get(), CHESTNUT_STAIRS.get(), PINE_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(HOLLY_SLAB.get(), CHESTNUT_SLAB.get(), PINE_SLAB.get());
        this.tag(BlockTags.WALL_SIGNS).add(HOLLY_SIGNS.getSecond().get(), CHESTNUT_SIGNS.getSecond().get(), PINE_SIGNS.getSecond().get());
        this.tag(BlockTags.STANDING_SIGNS).add(HOLLY_SIGNS.getFirst().get(), CHESTNUT_SIGNS.getFirst().get(), PINE_SIGNS.getFirst().get());
        this.tag(BlockTags.WOODEN_FENCES).add(HOLLY_FENCE.get(), CHESTNUT_FENCE.get(), PINE_FENCE.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(WindsweptBlockTags.HOLLY_LOGS).addTag(WindsweptBlockTags.CHESTNUT_LOGS).addTag(WindsweptBlockTags.PINE_LOGS);
        this.tag(BlockTags.LEAVES).add(HOLLY_LEAVES.get(), CHESTNUT_LEAVES.get(), PINE_LEAVES.get());
        this.tag(BlockTags.CLIMBABLE).add(HOLLY_LADDER.get(), CHESTNUT_LADDER.get(), PINE_LADDER.get());
        this.tag(BlockTags.FENCE_GATES).add(HOLLY_FENCE_GATE.get(), CHESTNUT_FENCE_GATE.get(), PINE_FENCE_GATE.get());
        this.tag(BlockTags.SAPLINGS).add(HOLLY_SAPLING.get(), CHESTNUT_SAPLING.get(), PINE_SAPLING.get());
        this.tag(BlockTags.SLABS).add(BLUE_ICE_BRICK_SLAB.get(), SNOW_BRICK_SLAB.get(), PACKED_ICE_BRICK_SLAB.get(), PACKED_ICE_SLAB.get(), BLUE_ICE_BRICK_SLAB.get(), SNOW_SLAB.get(), DRY_MOSSY_COBBLESTONE_SLAB.get(), DRY_MOSSY_STONE_BRICK_SLAB.get(), SHALE_SLAB.get(), POLISHED_SHALE_SLAB.get(), POLISHED_SHALE_BRICK_SLAB.get(), PINECONE_SHINGLE_SLAB.get(), DOLOMITE_SLAB.get(), CUT_DOLOMITE_SLAB.get(), CUT_DOLOMITE_BRICK_SLAB.get(), SMOOTH_DOLOMITE_SLAB.get(), GINGERBREAD_BRICK_SLAB.get(), GLAZED_GINGERBREAD_BRICK_SLAB.get());
        this.tag(BlockTags.STAIRS).add(BLUE_ICE_BRICK_STAIRS.get(), SNOW_BRICK_STAIRS.get(), PACKED_ICE_BRICK_STAIRS.get(), PACKED_ICE_STAIRS.get(), BLUE_ICE_STAIRS.get(), SNOW_STAIRS.get(), DRY_MOSSY_COBBLESTONE_STAIRS.get(), DRY_MOSSY_STONE_BRICK_STAIRS.get(), SHALE_STAIRS.get(), POLISHED_SHALE_STAIRS.get(), POLISHED_SHALE_BRICK_STAIRS.get(), PINECONE_SHINGLE_STAIRS.get(), DOLOMITE_STAIRS.get(), CUT_DOLOMITE_STAIRS.get(), CUT_DOLOMITE_BRICK_STAIRS.get(), SMOOTH_DOLOMITE_STAIRS.get(), GINGERBREAD_BRICK_STAIRS.get(), GLAZED_GINGERBREAD_BRICK_STAIRS.get());
        this.tag(BlockTags.TALL_FLOWERS).add(RED_ROSE_BUSH.get(), BLUE_ROSE_BUSH.get(), WHITE_ROSE_BUSH.get(), YELLOW_ROSE_BUSH.get(), LUPINE.get());
        this.tag(BlockTags.REPLACEABLE_PLANTS).add(SNOWY_SPROUTS.get(), GELISOL_SPROUTS.get(), DRY_MOSSY_SPROUTS.get(), MOSSY_SPROUTS.get());
        this.tag(BlockTags.SMALL_FLOWERS).add(RED_ROSE.get(), BLUE_ROSE.get(), WHITE_ROSE.get(), YELLOW_ROSE.get(), FOXGLOVE.get(), BLUEBELLS.get(), NIGHTSHADE.get(), SNOWDROP.get(), MOSS_CAMPION.get(), GINGER_FLOWER.get(), LAVENDER.get());
        this.tag(BlockTags.FLOWER_POTS).add(POTTED_RED_ROSE.get(), POTTED_BLUE_ROSE.get(), POTTED_WHITE_ROSE.get(), POTTED_YELLOW_ROSE.get(), POTTED_FOXGLOVE.get(), POTTED_BLUEBELLS.get(), POTTED_NIGHTSHADE.get(), POTTED_HOLLY_SAPLING.get(), POTTED_CHESTNUT_SAPLING.get(), POTTED_PINE_SAPLING.get(), POTTED_SNOWY_SPROUTS.get(), POTTED_SNOWDROP.get(), POTTED_GELISOL_SPROUTS.get(), POTTED_DRY_MOSSY_SPROUTS.get(), POTTED_MOSS_CAMPION.get(), POTTED_GINGER_FLOWER.get(), POTTED_MOSSY_SPROUTS.get(), POTTED_LAVENDER.get());
        this.tag(BlockTags.ICE).add(ICE_SHEET.get(), PACKED_ICE_STAIRS.get(), PACKED_ICE_SLAB.get(), PACKED_ICE_VERTICAL_SLAB.get(), BLUE_ICE_STAIRS.get(), BLUE_ICE_SLAB.get(), BLUE_ICE_VERTICAL_SLAB.get(), ICICLES.get(), ICICLE_BLOCK.get(), CHISELED_ICICLE_BLOCK.get(), ICICLE_DOOR.get(), ICICLE_TRAPDOOR.get(), ICICLE_BARS.get());
        this.tag(BlockTags.SNOW).add(SNOW_BRICKS.get(), SNOW_BRICK_SLAB.get(), SNOW_BRICK_STAIRS.get(), SNOW_BRICK_VERTICAL_SLAB.get(), SNOW_BRICK_WALL.get(), SNOW_STAIRS.get(), SNOW_SLAB.get(), SNOW_VERTICAL_SLAB.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(HOLLY_BOOKSHELF.get(), HOLLY_VERTICAL_SLAB.get(), HOLLY_LADDER.get(), HOLLY_HEDGE.get(), HOLLY_CHEST.get(), HOLLY_TRAPPED_CHEST.get(), HOLLY_BERRY_BASKET.get(), HOLLY_POST.get(), STRIPPED_HOLLY_POST.get(), HOLLY_BEEHIVE.get(), HOLLY_BOARDS.get(), CHESTNUT_BOOKSHELF.get(), CHESTNUT_VERTICAL_SLAB.get(), CHESTNUT_LADDER.get(), CHESTNUT_HEDGE.get(), CHESTNUT_CHEST.get(), CHESTNUT_TRAPPED_CHEST.get(), CHESTNUT_CRATE.get(), ROASTED_CHESTNUT_CRATE.get(), CHESTNUT_POST.get(), STRIPPED_CHESTNUT_POST.get(), CHESTNUT_BEEHIVE.get(), CHESTNUT_BOARDS.get(), HOLLY_CABINET.get(), CHESTNUT_CABINET.get(), RED_MUSHROOM_BASKET.get(), BROWN_MUSHROOM_BASKET.get(), GLOW_SHROOM_BASKET.get(), WILD_BERRY_BASKET.get(), FROSTBITER_TROPHY.get(), PINE_BOOKSHELF.get(), PINE_VERTICAL_SLAB.get(), PINE_LADDER.get(), PINE_HEDGE.get(), PINE_CHEST.get(), PINE_TRAPPED_CHEST.get(), PINE_POST.get(), STRIPPED_PINE_POST.get(), PINE_BEEHIVE.get(), PINE_BOARDS.get(), HOLLY_CABINET.get(), PINE_CABINET.get(), WEATHERED_PINE_POST.get(), PINECONE_BLOCK.get(), CARVED_PINECONE_BLOCK.get(), WILL_O_THE_WISP.get(), PINECONE_SHINGLES.get(), PINECONE_SHINGLE_STAIRS.get(), PINECONE_SHINGLE_SLAB.get(), PINECONE_SHINGLE_WALL.get(), PINECONE_SHINGLE_VERTICAL_SLAB.get(), GINGERBREAD_BLOCK.get(), GLAZED_GINGERBREAD_BLOCK.get(), GINGERBREAD_BRICKS.get(), GINGERBREAD_BRICK_STAIRS.get(), GINGERBREAD_BRICK_SLAB.get(), GINGERBREAD_BRICK_WALL.get(), GINGERBREAD_BRICK_VERTICAL_SLAB.get(), GINGERBREAD_BRICKS.get(), GLAZED_GINGERBREAD_BRICKS.get(), GINGERBREAD_COOKIE_BLOCK.get(), GLAZED_GINGERBREAD_BRICK_STAIRS.get(), GLAZED_GINGERBREAD_BRICK_SLAB.get(), GLAZED_GINGERBREAD_BRICK_WALL.get(), GLAZED_GINGERBREAD_BRICK_VERTICAL_SLAB.get(), FEATHER_WING.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(HOLLY_LEAVES.get(), CHESTNUT_LEAVES.get(), PINE_LEAVES.get(), HOLLY_LEAF_CARPET.get(), CHESTNUT_LEAF_CARPET.get(), PINE_LEAF_CARPET.get(), FROZEN_FLESH_BLOCK.get(), DRY_MOSS_CARPET.get(), DRY_MOSS_BLOCK.get(), FEATHER_ORNAMENT.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ICE_SHEET.get(), BLUE_ICE_BRICKS.get(), CHISELED_BLUE_ICE_BRICKS.get(), BLUE_ICE_BRICK_SLAB.get(), BLUE_ICE_BRICK_STAIRS.get(), BLUE_ICE_BRICK_VERTICAL_SLAB.get(), BLUE_ICE_BRICK_WALL.get(), PACKED_ICE_BRICKS.get(), CHISELED_PACKED_ICE_BRICKS.get(), PACKED_ICE_BRICK_SLAB.get(), PACKED_ICE_BRICK_STAIRS.get(), PACKED_ICE_BRICK_VERTICAL_SLAB.get(), PACKED_ICE_BRICK_WALL.get(), SNOW_BRICKS.get(), SNOW_BRICK_SLAB.get(), SNOW_BRICK_STAIRS.get(), SNOW_BRICK_VERTICAL_SLAB.get(), SNOW_BRICK_WALL.get(), PACKED_ICE_STAIRS.get(), PACKED_ICE_SLAB.get(), PACKED_ICE_VERTICAL_SLAB.get(), BLUE_ICE_STAIRS.get(), BLUE_ICE_SLAB.get(), BLUE_ICE_VERTICAL_SLAB.get(), ICICLES.get(), ICICLE_BLOCK.get(), CHISELED_ICICLE_BLOCK.get(), DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_STAIRS.get(), DRY_MOSSY_COBBLESTONE_SLAB.get(), DRY_MOSSY_COBBLESTONE_WALL.get(), DRY_MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), DRY_MOSSY_STONE_BRICKS.get(), DRY_MOSSY_STONE_BRICK_STAIRS.get(), DRY_MOSSY_STONE_BRICK_SLAB.get(), DRY_MOSSY_STONE_BRICK_WALL.get(), DRY_MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), ICICLE_DOOR.get(), ICICLE_TRAPDOOR.get(), ICICLE_BARS.get(), ICE_LANTERN.get(), SHALE.get(), SHALE_STAIRS.get(), SHALE_SLAB.get(), SHALE_WALL.get(), SHALE_VERTICAL_SLAB.get(), POLISHED_SHALE.get(), POLISHED_SHALE_STAIRS.get(), POLISHED_SHALE_SLAB.get(), POLISHED_SHALE_VERTICAL_SLAB.get(), POLISHED_SHALE_BRICKS.get(), CHISELED_POLISHED_SHALE_BRICKS.get(), POLISHED_SHALE_BRICK_STAIRS.get(), POLISHED_SHALE_BRICK_SLAB.get(), POLISHED_SHALE_BRICK_WALL.get(), POLISHED_SHALE_BRICK_VERTICAL_SLAB.get(), ICY_POLISHED_SHALE_BRICKS.get(), POLISHED_DEEPSLATE_PRESSURE_PLATE.get(), POLISHED_SHALE_PRESSURE_PLATE.get(), DOLOMITE.get(), DOLOMITE_STAIRS.get(), DOLOMITE_SLAB.get(), DOLOMITE_WALL.get(), DOLOMITE_VERTICAL_SLAB.get(), CUT_DOLOMITE.get(), CUT_DOLOMITE_STAIRS.get(), CUT_DOLOMITE_SLAB.get(), CUT_DOLOMITE_WALL.get(), CUT_DOLOMITE_VERTICAL_SLAB.get(), CUT_DOLOMITE_BRICKS.get(), CHISELED_CUT_DOLOMITE_BRICKS.get(), CUT_DOLOMITE_BRICK_STAIRS.get(), CUT_DOLOMITE_BRICK_SLAB.get(), CUT_DOLOMITE_BRICK_WALL.get(), CUT_DOLOMITE_BRICK_VERTICAL_SLAB.get(), SMOOTH_DOLOMITE.get(), SMOOTH_DOLOMITE_STAIRS.get(), SMOOTH_DOLOMITE_SLAB.get(), SMOOTH_DOLOMITE_VERTICAL_SLAB.get(), CANDY_CANE_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(SNOW_BRICKS.get(), SNOW_BRICK_SLAB.get(), SNOW_BRICK_STAIRS.get(), SNOW_BRICK_VERTICAL_SLAB.get(), SNOW_BRICK_WALL.get(), SNOW_STAIRS.get(), SNOW_SLAB.get(), SNOW_VERTICAL_SLAB.get(), GELISOL.get(), GINGER_SOIL.get());
    }

}

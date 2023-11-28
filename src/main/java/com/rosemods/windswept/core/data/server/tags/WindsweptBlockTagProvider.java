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
        this.tag(WindsweptBlockTags.HOLLY_LEAVES).add(HOLLY_LEAVES.get());
        this.tag(WindsweptBlockTags.CHESTNUT_LOGS).add(CHESTNUT_LOG.get(), CHESTNUT_WOOD.get(), STRIPPED_CHESTNUT_LOG.get(), STRIPPED_CHESTNUT_WOOD.get());
        this.tag(WindsweptBlockTags.CHESTNUT_LEAVES).add(CHESTNUT_LEAVES.get());
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
        this.tag(BlueprintBlockTags.HEDGES).add(HOLLY_HEDGE.get(), CHESTNUT_HEDGE.get());
        this.tag(BlueprintBlockTags.LADDERS).add(HOLLY_LADDER.get(), CHESTNUT_LADDER.get());
        this.tag(BlueprintBlockTags.VERTICAL_SLABS).add(BLUE_ICE_BRICK_VERTICAL_SLAB.get(), HOLLY_VERTICAL_SLAB.get(), SNOW_BRICK_VERTICAL_SLAB.get(), CHESTNUT_VERTICAL_SLAB.get(), PACKED_ICE_BRICK_VERTICAL_SLAB.get(), PACKED_ICE_VERTICAL_SLAB.get(), BLUE_ICE_VERTICAL_SLAB.get(), SNOW_VERTICAL_SLAB.get(), TUNDRA_MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), TUNDRA_MOSSY_STONE_BRICK_VERTICAL_SLAB.get());
        this.tag(BlueprintBlockTags.WOODEN_VERTICAL_SLABS).add(HOLLY_VERTICAL_SLAB.get(), CHESTNUT_VERTICAL_SLAB.get());

        //forge
        this.tag(Tags.Blocks.CHESTS_WOODEN).add(HOLLY_CHEST.get(), HOLLY_TRAPPED_CHEST.get(), CHESTNUT_CHEST.get(), CHESTNUT_TRAPPED_CHEST.get());
        this.tag(Tags.Blocks.CHESTS_TRAPPED).add(HOLLY_TRAPPED_CHEST.get(), CHESTNUT_TRAPPED_CHEST.get());

        //vanilla
        this.tag(BlockTags.DIRT).add(TUNDRA_MOSS_BLOCK.get());
        this.tag(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON).add(HOLLY_LEAVES.get());
        this.tag(BlockTags.WALLS).add(BLUE_ICE_BRICK_WALL.get(), SNOW_BRICK_WALL.get(), PACKED_ICE_BRICK_WALL.get(), TUNDRA_MOSSY_COBBLESTONE_WALL.get(), TUNDRA_MOSSY_STONE_BRICK_WALL.get());
        this.tag(BlockTags.BEEHIVES).add(HOLLY_BEEHIVE.get(), CHESTNUT_BEEHIVE.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(HOLLY_BUTTON.get(), CHESTNUT_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(HOLLY_PRESSURE_PLATE.get(), CHESTNUT_PRESSURE_PLATE.get());
        this.tag(BlockTags.PLANKS).add(HOLLY_PLANKS.get(), CHESTNUT_PLANKS.get(), VERTICAL_HOLLY_PLANKS.get(), VERTICAL_CHESTNUT_PLANKS.get());
        this.tag(BlockTags.WOODEN_DOORS).add(HOLLY_DOOR.get(), CHESTNUT_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(HOLLY_TRAPDOOR.get(), CHESTNUT_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(HOLLY_STAIRS.get(), CHESTNUT_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(HOLLY_SLAB.get(), CHESTNUT_SLAB.get());
        this.tag(BlockTags.WALL_SIGNS).add(HOLLY_SIGNS.getSecond().get(), CHESTNUT_SIGNS.getSecond().get());
        this.tag(BlockTags.STANDING_SIGNS).add(HOLLY_SIGNS.getFirst().get(), CHESTNUT_SIGNS.getFirst().get());
        this.tag(BlockTags.WOODEN_FENCES).add(HOLLY_FENCE.get(), CHESTNUT_FENCE.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(WindsweptBlockTags.HOLLY_LOGS).addTag(WindsweptBlockTags.CHESTNUT_LOGS);
        this.tag(BlockTags.LEAVES).addTag(WindsweptBlockTags.HOLLY_LEAVES).addTag(WindsweptBlockTags.CHESTNUT_LEAVES);
        this.tag(BlockTags.CLIMBABLE).add(HOLLY_LADDER.get(), CHESTNUT_LADDER.get());
        this.tag(BlockTags.FENCE_GATES).add(HOLLY_FENCE_GATE.get(), CHESTNUT_FENCE_GATE.get());
        this.tag(BlockTags.SAPLINGS).add(HOLLY_SAPLING.get(), CHESTNUT_SAPLING.get());
        this.tag(BlockTags.SLABS).add(BLUE_ICE_BRICK_SLAB.get(), SNOW_BRICK_SLAB.get(), PACKED_ICE_BRICK_SLAB.get(), PACKED_ICE_SLAB.get(), BLUE_ICE_BRICK_SLAB.get(), SNOW_SLAB.get(), TUNDRA_MOSSY_COBBLESTONE_SLAB.get(), TUNDRA_MOSSY_STONE_BRICK_SLAB.get());
        this.tag(BlockTags.STAIRS).add(BLUE_ICE_BRICK_STAIRS.get(), SNOW_BRICK_STAIRS.get(), PACKED_ICE_BRICK_STAIRS.get(), PACKED_ICE_STAIRS.get(), BLUE_ICE_STAIRS.get(), SNOW_STAIRS.get(), TUNDRA_MOSSY_COBBLESTONE_STAIRS.get(), TUNDRA_MOSSY_STONE_BRICK_STAIRS.get());
        this.tag(BlockTags.TALL_FLOWERS).add(RED_ROSE_BUSH.get(), PINK_ROSE_BUSH.get(), BLUE_ROSE_BUSH.get(), WHITE_ROSE_BUSH.get(), YELLOW_ROSE_BUSH.get(), WITHER_ROSE_BUSH.get());
        this.tag(BlockTags.REPLACEABLE_PLANTS).add(RED_ROSE_BUSH.get(), PINK_ROSE_BUSH.get(), BLUE_ROSE_BUSH.get(), WHITE_ROSE_BUSH.get(), YELLOW_ROSE_BUSH.get(), WITHER_ROSE_BUSH.get(), SNOWY_SPROUTS.get(), RED_TUNDRA_SPROUTS.get(), YELLOW_TUNDRA_SPROUTS.get());
        this.tag(BlockTags.SMALL_FLOWERS).add(RED_ROSE.get(), PINK_ROSE.get(), BLUE_ROSE.get(), WHITE_ROSE.get(), YELLOW_ROSE.get(), FOXGLOVE.get(), BLUEBELLS.get(), NIGHTSHADE.get(), SNOWDROP.get());
        this.tag(BlockTags.FLOWER_POTS).add(POTTED_RED_ROSE.get(), POTTED_PINK_ROSE.get(), POTTED_BLUE_ROSE.get(), POTTED_WHITE_ROSE.get(), POTTED_YELLOW_ROSE.get(), POTTED_FOXGLOVE.get(), POTTED_BLUEBELLS.get(), POTTED_NIGHTSHADE.get(), POTTED_HOLLY_SAPLING.get(), POTTED_CHESTNUT_SAPLING.get(), POTTED_SNOWY_SPROUTS.get(), POTTED_SNOWDROP.get(), POTTED_RED_TUNDRA_SPROUTS.get(), POTTED_YELLOW_TUNDRA_SPROUTS.get());
        this.tag(BlockTags.ICE).add(PACKED_ICE_STAIRS.get(), PACKED_ICE_SLAB.get(), PACKED_ICE_VERTICAL_SLAB.get(), BLUE_ICE_STAIRS.get(), BLUE_ICE_SLAB.get(), BLUE_ICE_VERTICAL_SLAB.get(), CUT_ICE.get(), ICE_SHEET.get(), CUT_ICE_SHEET.get(), ICICLE.get(), ICICLE_BLOCK.get(), CHISELED_ICICLE_BLOCK.get());
        this.tag(BlockTags.SNOW).add(SNOW_BRICKS.get(), SNOW_BRICK_SLAB.get(), SNOW_BRICK_STAIRS.get(), SNOW_BRICK_VERTICAL_SLAB.get(), SNOW_BRICK_WALL.get(), SNOW_STAIRS.get(), SNOW_SLAB.get(), SNOW_VERTICAL_SLAB.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(HOLLY_BOOKSHELF.get(), HOLLY_VERTICAL_SLAB.get(), HOLLY_LADDER.get(), HOLLY_HEDGE.get(), HOLLY_CHEST.get(), HOLLY_TRAPPED_CHEST.get(), HOLLY_BERRY_BASKET.get(), HOLLY_POST.get(), STRIPPED_HOLLY_POST.get(), HOLLY_BEEHIVE.get(), HOLLY_BOARDS.get(), CHESTNUT_BOOKSHELF.get(), CHESTNUT_VERTICAL_SLAB.get(), CHESTNUT_LADDER.get(), CHESTNUT_HEDGE.get(), CHESTNUT_CHEST.get(), CHESTNUT_TRAPPED_CHEST.get(), CHESTNUT_CRATE.get(), ROASTED_CHESTNUT_CRATE.get(), CHESTNUT_POST.get(), STRIPPED_CHESTNUT_POST.get(), CHESTNUT_BEEHIVE.get(), CHESTNUT_BOARDS.get(), HOLLY_CABINET.get(), CHESTNUT_CABINET.get(), RED_MUSHROOM_BASKET.get(), BROWN_MUSHROOM_BASKET.get(), GLOW_SHROOM_BASKET.get(), WILD_BERRY_BASKET.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).addTag(WindsweptBlockTags.HOLLY_LEAVES).addTag(WindsweptBlockTags.CHESTNUT_LEAVES).add(HOLLY_LEAF_CARPET.get(), CHESTNUT_LEAF_CARPET.get(), FROZEN_FLESH_BLOCK.get(), TUNDRA_MOSS_CARPET.get(), TUNDRA_MOSS_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLUE_ICE_BRICKS.get(), CHISELED_BLUE_ICE_BRICKS.get(), BLUE_ICE_BRICK_SLAB.get(), BLUE_ICE_BRICK_STAIRS.get(), BLUE_ICE_BRICK_VERTICAL_SLAB.get(), BLUE_ICE_BRICK_WALL.get(), PACKED_ICE_BRICKS.get(), CHISELED_PACKED_ICE_BRICKS.get(), PACKED_ICE_BRICK_SLAB.get(), PACKED_ICE_BRICK_STAIRS.get(), PACKED_ICE_BRICK_VERTICAL_SLAB.get(), PACKED_ICE_BRICK_WALL.get(), SNOW_BRICKS.get(), SNOW_BRICK_SLAB.get(), SNOW_BRICK_STAIRS.get(), SNOW_BRICK_VERTICAL_SLAB.get(), SNOW_BRICK_WALL.get(), CUT_ICE.get(), ICE_SHEET.get(), CUT_ICE_SHEET.get(), PACKED_ICE_STAIRS.get(), PACKED_ICE_SLAB.get(), PACKED_ICE_VERTICAL_SLAB.get(), BLUE_ICE_STAIRS.get(), BLUE_ICE_SLAB.get(), BLUE_ICE_VERTICAL_SLAB.get(), ICICLE.get(), ICICLE_BLOCK.get(), CHISELED_ICICLE_BLOCK.get(), TUNDRA_MOSSY_COBBLESTONE.get(), TUNDRA_MOSSY_COBBLESTONE_STAIRS.get(), TUNDRA_MOSSY_COBBLESTONE_SLAB.get(), TUNDRA_MOSSY_COBBLESTONE_WALL.get(), TUNDRA_MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), TUNDRA_MOSSY_STONE_BRICKS.get(), TUNDRA_MOSSY_STONE_BRICK_STAIRS.get(), TUNDRA_MOSSY_STONE_BRICK_SLAB.get(), TUNDRA_MOSSY_STONE_BRICK_WALL.get(), TUNDRA_MOSSY_STONE_BRICK_VERTICAL_SLAB.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(SNOW_BRICKS.get(), SNOW_BRICK_SLAB.get(), SNOW_BRICK_STAIRS.get(), SNOW_BRICK_VERTICAL_SLAB.get(), SNOW_BRICK_WALL.get(), SNOW_STAIRS.get(), SNOW_SLAB.get(), SNOW_VERTICAL_SLAB.get());

    }

}

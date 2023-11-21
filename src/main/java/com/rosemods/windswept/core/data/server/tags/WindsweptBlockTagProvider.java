package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptBlockTagProvider extends BlockTagsProvider {
    public static WindsweptBlockTagProvider INSTANCE;

    public WindsweptBlockTagProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MODID, event.getExistingFileHelper());
        INSTANCE = this;
    }

    @Override
    protected void addTags() {
        //windswept
        this.tag(WindsweptBlockTags.HOLLY_LOGS).add(WindsweptBlocks.HOLLY_LOG.get(), WindsweptBlocks.HOLLY_WOOD.get(), WindsweptBlocks.STRIPPED_HOLLY_LOG.get(), WindsweptBlocks.STRIPPED_HOLLY_WOOD.get());
        this.tag(WindsweptBlockTags.HOLLY_LEAVES).add(WindsweptBlocks.HOLLY_LEAVES.get());
        this.tag(WindsweptBlockTags.CHESTNUT_LOGS).add(WindsweptBlocks.CHESTNUT_LOG.get(), WindsweptBlocks.CHESTNUT_WOOD.get(), WindsweptBlocks.STRIPPED_CHESTNUT_LOG.get(), WindsweptBlocks.STRIPPED_CHESTNUT_WOOD.get());
        this.tag(WindsweptBlockTags.CHESTNUT_LEAVES).add(WindsweptBlocks.CHESTNUT_LEAVES.get());
        this.tag(WindsweptBlockTags.SNOW_BOOTS_BLOCKS).addTag(BlockTags.SNOW).add(WindsweptBlocks.SNOW_BRICKS.get(), WindsweptBlocks.SNOW_BRICK_SLAB.get(), WindsweptBlocks.SNOW_BRICK_WALL.get(), WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB.get());
        this.tag(WindsweptBlockTags.DEFAULT_WHITE_TEXT).add(WindsweptBlocks.HOLLY_SIGNS.getFirst().get(), WindsweptBlocks.HOLLY_SIGNS.getSecond().get(), Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_WALL_SIGN);

        //woodworks
        this.tag(BlueprintBlockTags.LEAF_PILES).add(WindsweptBlocks.HOLLY_LEAF_PILE.get(), WindsweptBlocks.CHESTNUT_LEAF_PILE.get());

        //quark
        this.tag(BlueprintBlockTags.HEDGES).add(WindsweptBlocks.HOLLY_HEDGE.get(), WindsweptBlocks.CHESTNUT_HEDGE.get());
        this.tag(BlueprintBlockTags.LADDERS).add(WindsweptBlocks.HOLLY_LADDER.get(), WindsweptBlocks.CHESTNUT_LADDER.get());
        this.tag(BlueprintBlockTags.VERTICAL_SLABS).add(WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB.get(),
                WindsweptBlocks.HOLLY_VERTICAL_SLAB.get(), WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB.get(),
                WindsweptBlocks.CHESTNUT_VERTICAL_SLAB.get(), WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB.get());

        this.tag(BlueprintBlockTags.WOODEN_VERTICAL_SLABS).add(WindsweptBlocks.HOLLY_VERTICAL_SLAB.get(),
                WindsweptBlocks.CHESTNUT_VERTICAL_SLAB.get());

        //forge
        this.tag(Tags.Blocks.CHESTS_WOODEN).add(WindsweptBlocks.HOLLY_CHEST.get(),
                WindsweptBlocks.HOLLY_TRAPPED_CHEST.get(), WindsweptBlocks.CHESTNUT_CHEST.get(),
                WindsweptBlocks.CHESTNUT_TRAPPED_CHEST.get());

        this.tag(Tags.Blocks.CHESTS_TRAPPED).add(WindsweptBlocks.HOLLY_TRAPPED_CHEST.get(),
                WindsweptBlocks.CHESTNUT_TRAPPED_CHEST.get());

        //vanilla
        this.tag(BlockTags.WALLS).add(WindsweptBlocks.BLUE_ICE_BRICK_WALL.get(), WindsweptBlocks.SNOW_BRICK_WALL.get(), WindsweptBlocks.PACKED_ICE_BRICK_WALL.get());
        this.tag(BlockTags.BEEHIVES).add(WindsweptBlocks.HOLLY_BEEHIVE.get(), WindsweptBlocks.CHESTNUT_BEEHIVE.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(WindsweptBlocks.HOLLY_BUTTON.get(), WindsweptBlocks.CHESTNUT_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(WindsweptBlocks.HOLLY_PRESSURE_PLATE.get(), WindsweptBlocks.CHESTNUT_PRESSURE_PLATE.get());
        this.tag(BlockTags.PLANKS).add(WindsweptBlocks.HOLLY_PLANKS.get(), WindsweptBlocks.CHESTNUT_PLANKS.get(), WindsweptBlocks.VERTICAL_HOLLY_PLANKS.get(), WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS.get());
        this.tag(BlockTags.WOODEN_DOORS).add(WindsweptBlocks.HOLLY_DOOR.get(), WindsweptBlocks.CHESTNUT_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(WindsweptBlocks.HOLLY_TRAPDOOR.get(), WindsweptBlocks.CHESTNUT_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(WindsweptBlocks.HOLLY_STAIRS.get(), WindsweptBlocks.CHESTNUT_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(WindsweptBlocks.HOLLY_SLAB.get(), WindsweptBlocks.CHESTNUT_SLAB.get());
        this.tag(BlockTags.WALL_SIGNS).add(WindsweptBlocks.HOLLY_SIGNS.getSecond().get(), WindsweptBlocks.CHESTNUT_SIGNS.getSecond().get());
        this.tag(BlockTags.STANDING_SIGNS).add(WindsweptBlocks.HOLLY_SIGNS.getFirst().get(), WindsweptBlocks.CHESTNUT_SIGNS.getFirst().get());
        this.tag(BlockTags.WOODEN_FENCES).add(WindsweptBlocks.HOLLY_FENCE.get(), WindsweptBlocks.CHESTNUT_FENCE.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(WindsweptBlockTags.HOLLY_LOGS).addTag(WindsweptBlockTags.CHESTNUT_LOGS);
        this.tag(BlockTags.LEAVES).addTag(WindsweptBlockTags.HOLLY_LEAVES).addTag(WindsweptBlockTags.CHESTNUT_LEAVES);
        this.tag(BlockTags.CLIMBABLE).add(WindsweptBlocks.HOLLY_LADDER.get(), WindsweptBlocks.CHESTNUT_LADDER.get());
        this.tag(BlockTags.FENCE_GATES).add(WindsweptBlocks.HOLLY_FENCE_GATE.get(), WindsweptBlocks.CHESTNUT_FENCE_GATE.get());
        this.tag(BlockTags.SAPLINGS).add(WindsweptBlocks.HOLLY_SAPLING.get(), WindsweptBlocks.CHESTNUT_SAPLING.get());

        this.tag(BlockTags.SLABS).add(WindsweptBlocks.BLUE_ICE_BRICK_SLAB.get(), WindsweptBlocks.SNOW_BRICK_SLAB.get(),
                WindsweptBlocks.PACKED_ICE_BRICK_SLAB.get());

        this.tag(BlockTags.STAIRS).add(WindsweptBlocks.BLUE_ICE_BRICK_STAIRS.get(), WindsweptBlocks.SNOW_BRICK_STAIRS.get(),
                WindsweptBlocks.PACKED_ICE_BRICK_STAIRS.get());

        this.tag(BlockTags.TALL_FLOWERS).add(WindsweptBlocks.RED_ROSE_BUSH.get(), WindsweptBlocks.PINK_ROSE_BUSH.get(), WindsweptBlocks.BLUE_ROSE_BUSH.get(),
                WindsweptBlocks.WHITE_ROSE_BUSH.get(), WindsweptBlocks.YELLOW_ROSE_BUSH.get(), WindsweptBlocks.WITHER_ROSE_BUSH.get());

        this.tag(BlockTags.REPLACEABLE_PLANTS).add(WindsweptBlocks.RED_ROSE_BUSH.get(), WindsweptBlocks.PINK_ROSE_BUSH.get(), WindsweptBlocks.BLUE_ROSE_BUSH.get(),
                WindsweptBlocks.WHITE_ROSE_BUSH.get(), WindsweptBlocks.YELLOW_ROSE_BUSH.get(), WindsweptBlocks.WITHER_ROSE_BUSH.get(),
                WindsweptBlocks.SNOWY_SPROUTS.get());

        this.tag(BlockTags.SMALL_FLOWERS).add(WindsweptBlocks.RED_ROSE.get(),
                WindsweptBlocks.PINK_ROSE.get(), WindsweptBlocks.BLUE_ROSE.get(),
                WindsweptBlocks.WHITE_ROSE.get(), WindsweptBlocks.YELLOW_ROSE.get(),
                WindsweptBlocks.FOXGLOVE.get(), WindsweptBlocks.BLUEBELLS.get(),
                WindsweptBlocks.NIGHTSHADE.get());

        this.tag(BlockTags.FLOWER_POTS).add(WindsweptBlocks.POTTED_RED_ROSE.get(),
                WindsweptBlocks.POTTED_PINK_ROSE.get(), WindsweptBlocks.POTTED_BLUE_ROSE.get(),
                WindsweptBlocks.POTTED_WHITE_ROSE.get(), WindsweptBlocks.POTTED_YELLOW_ROSE.get(),
                WindsweptBlocks.POTTED_FOXGLOVE.get(), WindsweptBlocks.POTTED_BLUEBELLS.get(),
                WindsweptBlocks.POTTED_NIGHTSHADE.get(), WindsweptBlocks.POTTED_HOLLY_SAPLING.get(),
                WindsweptBlocks.POTTED_CHESTNUT_SAPLING.get(), WindsweptBlocks.POTTED_SNOWY_SPROUTS.get());

        this.tag(BlockTags.ICE).add(WindsweptBlocks.PACKED_ICE_STAIRS.get(), WindsweptBlocks.PACKED_ICE_SLAB.get(), WindsweptBlocks.PACKED_ICE_VERTICAL_SLAB.get(),
                WindsweptBlocks.BLUE_ICE_STAIRS.get(), WindsweptBlocks.BLUE_ICE_SLAB.get(), WindsweptBlocks.BLUE_ICE_VERTICAL_SLAB.get(),
                WindsweptBlocks.CUT_ICE.get(), WindsweptBlocks.ICE_SHEET.get(), WindsweptBlocks.CUT_ICE_SHEET.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(WindsweptBlocks.HOLLY_BOOKSHELF.get(),
                WindsweptBlocks.HOLLY_VERTICAL_SLAB.get(), WindsweptBlocks.HOLLY_LADDER.get(),
                WindsweptBlocks.HOLLY_HEDGE.get(), WindsweptBlocks.HOLLY_CHEST.get(),
                WindsweptBlocks.HOLLY_TRAPPED_CHEST.get(), WindsweptBlocks.HOLLY_BERRY_BASKET.get(),
                WindsweptBlocks.HOLLY_POST.get(), WindsweptBlocks.STRIPPED_HOLLY_POST.get(),
                WindsweptBlocks.HOLLY_BEEHIVE.get(), WindsweptBlocks.HOLLY_BOARDS.get(),
                WindsweptBlocks.CHESTNUT_BOOKSHELF.get(), WindsweptBlocks.CHESTNUT_VERTICAL_SLAB.get(),
                WindsweptBlocks.CHESTNUT_LADDER.get(), WindsweptBlocks.CHESTNUT_HEDGE.get(),
                WindsweptBlocks.CHESTNUT_CHEST.get(), WindsweptBlocks.CHESTNUT_TRAPPED_CHEST.get(),
                WindsweptBlocks.CHESTNUT_CRATE.get(), WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get(),
                WindsweptBlocks.CHESTNUT_POST.get(), WindsweptBlocks.STRIPPED_CHESTNUT_POST.get(),
                WindsweptBlocks.CHESTNUT_BEEHIVE.get(), WindsweptBlocks.CHESTNUT_BOARDS.get(),
                WindsweptBlocks.HOLLY_CABINET.get(), WindsweptBlocks.CHESTNUT_CABINET.get(),
                WindsweptBlocks.RED_MUSHROOM_BASKET.get(), WindsweptBlocks.BROWN_MUSHROOM_BASKET.get(),
                WindsweptBlocks.GLOW_SHROOM_BASKET.get(), WindsweptBlocks.WILD_BERRY_BASKET.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE).addTag(WindsweptBlockTags.HOLLY_LEAVES)
                .addTag(WindsweptBlockTags.CHESTNUT_LEAVES).add(WindsweptBlocks.HOLLY_LEAF_CARPET.get(),
                        WindsweptBlocks.CHESTNUT_LEAF_CARPET.get(), WindsweptBlocks.FROZEN_FLESH_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(WindsweptBlocks.BLUE_ICE_BRICKS.get(), WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS.get(),
                WindsweptBlocks.BLUE_ICE_BRICK_SLAB.get(), WindsweptBlocks.BLUE_ICE_BRICK_STAIRS.get(),
                WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB.get(), WindsweptBlocks.BLUE_ICE_BRICK_WALL.get(),
                WindsweptBlocks.PACKED_ICE_BRICKS.get(), WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS.get(),
                WindsweptBlocks.PACKED_ICE_BRICK_SLAB.get(), WindsweptBlocks.PACKED_ICE_BRICK_STAIRS.get(),
                WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB.get(), WindsweptBlocks.PACKED_ICE_BRICK_WALL.get(),
                WindsweptBlocks.SNOW_BRICKS.get(), WindsweptBlocks.SNOW_BRICK_SLAB.get(),
                WindsweptBlocks.SNOW_BRICK_STAIRS.get(), WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB.get(),
                WindsweptBlocks.SNOW_BRICK_WALL.get(), WindsweptBlocks.CUT_ICE.get(),
                WindsweptBlocks.ICE_SHEET.get(), WindsweptBlocks.CUT_ICE_SHEET.get(),
                WindsweptBlocks.PACKED_ICE_STAIRS.get(), WindsweptBlocks.PACKED_ICE_SLAB.get(), WindsweptBlocks.PACKED_ICE_VERTICAL_SLAB.get(),
                WindsweptBlocks.BLUE_ICE_STAIRS.get(), WindsweptBlocks.BLUE_ICE_SLAB.get(), WindsweptBlocks.BLUE_ICE_VERTICAL_SLAB.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(WindsweptBlocks.SNOW_BRICKS.get(),
                WindsweptBlocks.SNOW_BRICK_SLAB.get(), WindsweptBlocks.SNOW_BRICK_STAIRS.get(),
                WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB.get(), WindsweptBlocks.SNOW_BRICK_WALL.get());

    }

}

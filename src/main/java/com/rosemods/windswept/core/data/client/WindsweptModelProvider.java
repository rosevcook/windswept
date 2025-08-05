package com.rosemods.windswept.core.data.client;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.*;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptModelProvider extends BlueprintBlockStateProvider {
    public WindsweptModelProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        // item models
        this.generatedItem(HOLLY_BOATS.getFirst().get(), TextureFolder.ITEM);
        this.generatedItem(HOLLY_BOATS.getSecond().get(), TextureFolder.ITEM);
        this.generatedItem(CHESTNUT_BOATS.getFirst().get(), TextureFolder.ITEM);
        this.generatedItem(CHESTNUT_BOATS.getSecond().get(), TextureFolder.ITEM);
        this.generatedItem(PINE_BOATS.getFirst().get(), TextureFolder.ITEM);
        this.generatedItem(PINE_BOATS.getSecond().get(), TextureFolder.ITEM);
        this.generatedItem(HOLLY_BERRIES.get(), TextureFolder.ITEM);
        this.generatedItem(WOODEN_BUCKET.get(), TextureFolder.ITEM);
        this.generatedItem(WOODEN_MILK_BUCKET.get(), TextureFolder.ITEM);
        this.generatedItem(WOODEN_POWDER_SNOW_BUCKET.get(), TextureFolder.ITEM);
        this.generatedItem(WOODEN_WATER_BUCKET.get(), TextureFolder.ITEM);
        this.generatedItem(WILD_BERRIES.get(), TextureFolder.ITEM);
        this.itemOnAStick(WILD_BERRY_POPSICLE.get(), TextureFolder.ITEM);
        this.generatedItem(WILD_BERRY_COOKIE.get(), TextureFolder.ITEM);
        this.generatedItem(WILD_BERRY_JUICE.get(), TextureFolder.ITEM);
        this.generatedItem(WILD_BERRY_BOWL.get(), TextureFolder.ITEM);
        this.generatedItem(SWEET_BERRY_BOWL.get(), TextureFolder.ITEM);
        this.generatedItem(CHRISTMAS_PUDDING_SLICE.get(), TextureFolder.ITEM);
        this.generatedItem(MUTTON_PIE.get(), TextureFolder.ITEM);
        this.generatedItem(GOAT.get(), TextureFolder.ITEM);
        this.generatedItem(COOKED_GOAT.get(), TextureFolder.ITEM);
        this.generatedItem(GOAT_STEW.get(), TextureFolder.ITEM);
        this.generatedItem(GOAT_SHANKS.get(), TextureFolder.ITEM);
        this.generatedItem(COOKED_GOAT_SHANKS.get(), TextureFolder.ITEM);
        this.generatedItem(FOUL_BERRY_BOWL.get(), TextureFolder.ITEM);
        this.generatedItem(WILD_BERRY_PIPS.get(), TextureFolder.ITEM);
        this.generatedItemWithOverlay(SNOW_BOOTS.get());
        this.generatedItem(FROST_ARROW.get(), TextureFolder.ITEM);
        this.itemOnAStick(HOLLY_BERRIES_ON_A_STICK.get(), TextureFolder.ITEM);
        this.generatedItem(FROZEN_FLESH.get(), TextureFolder.ITEM);
        this.generatedItem(FROZEN_BRANCH.get(), TextureFolder.ITEM);
        this.generatedItem(SNOW_GOLEM_BANNER_PATTERN.get(), TextureFolder.ITEM);
        this.generatedItem(SNOW_CHARGE_BANNER_PATTERN.get(), TextureFolder.ITEM);
        this.generatedItem(ROSE_FLOWER_BANNER_PATTERN.get(), TextureFolder.ITEM);
        this.generatedItem(MUSIC_DISC_RAIN.get(), TextureFolder.ITEM);
        this.generatedItem(MUSIC_DISC_SNOW.get(), TextureFolder.ITEM);
        this.generatedItem(MUSIC_DISC_BUMBLEBEE.get(), TextureFolder.ITEM);
        this.spawnEgg(CHILLED_SPAWN_EGG);
        this.spawnEgg(FROSTBITER_SPAWN_EGG);
        this.generatedItem(HOLLY_FURNACE_BOAT.get(), TextureFolder.ITEM);
        this.generatedItem(LARGE_HOLLY_BOAT.get(), TextureFolder.ITEM);
        this.generatedItem(CHESTNUT_FURNACE_BOAT.get(), TextureFolder.ITEM);
        this.generatedItem(LARGE_CHESTNUT_BOAT.get(), TextureFolder.ITEM);
        this.generatedItem(PINE_FURNACE_BOAT.get(), TextureFolder.ITEM);
        this.generatedItem(LARGE_PINE_BOAT.get(), TextureFolder.ITEM);
        this.generatedItem(CHESTNUTS.get(), TextureFolder.ITEM);
        this.generatedItem(ROASTED_CHESTNUTS.get(), TextureFolder.ITEM);
        this.generatedItem(CHESTNUT_SOUP.get(), TextureFolder.ITEM);
        this.generatedItem(CHESTNUT_RISOTTO.get(), TextureFolder.ITEM);
        this.generatedItem(GINGER_ROOT.get(), TextureFolder.ITEM);
        this.generatedItem(GINGERBREAD_COOKIE.get(), TextureFolder.ITEM);
        this.generatedItem(GINGER_SNOW_CONE.get(), TextureFolder.ITEM);
        this.generatedItem(GINGER_TEA.get(), TextureFolder.ITEM);
        this.handheldItem(CANDY_CANE.get(), TextureFolder.ITEM);
        this.generatedItem(LAVENDER_SCONES.get(), TextureFolder.ITEM);
        this.generatedItem(LAVENDER_TEA.get(), TextureFolder.ITEM);
        this.generatedItem(LAVENDER_CROWN.get(), TextureFolder.ITEM);
        this.generatedItem(ANTLER_HELMET.get(), TextureFolder.ITEM);
        this.generatedItem(PINECONE_JAM_BOTTLE.get(), TextureFolder.ITEM);

        // holly
        this.pillar(STRIPPED_HOLLY_LOG);
        this.wood(STRIPPED_HOLLY_WOOD, this.blockTexture(STRIPPED_HOLLY_LOG.get()));
        this.pillar(HOLLY_LOG);
        this.wood(HOLLY_WOOD, this.blockTexture(HOLLY_LOG.get()));
        this.cubeAll(HOLLY_PLANKS);
        this.slab(HOLLY_SLAB, this.blockTexture(HOLLY_PLANKS.get()));
        this.stairs(HOLLY_STAIRS, this.blockTexture(HOLLY_PLANKS.get()));
        this.fence(HOLLY_FENCE, this.blockTexture(HOLLY_PLANKS.get()));
        this.fenceGate(HOLLY_FENCE_GATE, this.blockTexture(HOLLY_PLANKS.get()));
        this.pressurePlate(HOLLY_PRESSURE_PLATE, this.blockTexture(HOLLY_PLANKS.get()));
        this.door(HOLLY_DOOR);
        this.trapdoor(HOLLY_TRAPDOOR);
        this.button(HOLLY_BUTTON, this.blockTexture(HOLLY_PLANKS.get()));
        this.signs(HOLLY_SIGNS, HOLLY_PLANKS);
        this.leaves(HOLLY_LEAVES);
        this.pottedPlant(HOLLY_SAPLING, POTTED_HOLLY_SAPLING);
        this.beehive(HOLLY_BEEHIVE);
        this.leafPile(HOLLY_LEAF_PILE, this.blockTexture(HOLLY_LEAVES.get()), false);
        this.ladder(HOLLY_LADDER);
        this.bookshelf(HOLLY_BOOKSHELF, HOLLY_PLANKS);
        this.boards(HOLLY_BOARDS);
        this.cabinet(HOLLY_CABINET);
        this.chests(HOLLY_CHEST, HOLLY_TRAPPED_CHEST, this.blockTexture(HOLLY_PLANKS.get()));
        this.compressedBlock(HOLLY_BERRY_BASKET);
        this.hangingSignBlocks(STRIPPED_HOLLY_LOG, HOLLY_HANGING_SIGNS);

        // chestnut
        this.pillar(STRIPPED_CHESTNUT_LOG);
        this.wood(STRIPPED_CHESTNUT_WOOD, this.blockTexture(STRIPPED_CHESTNUT_LOG.get()));
        this.pillar(CHESTNUT_LOG);
        this.wood(CHESTNUT_WOOD, this.blockTexture(CHESTNUT_LOG.get()));
        this.cubeAll(CHESTNUT_PLANKS);
        this.slab(CHESTNUT_SLAB, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.stairs(CHESTNUT_STAIRS, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.fence(CHESTNUT_FENCE, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.fenceGate(CHESTNUT_FENCE_GATE, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.pressurePlate(CHESTNUT_PRESSURE_PLATE, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.door(CHESTNUT_DOOR);
        this.trapdoor(CHESTNUT_TRAPDOOR);
        this.button(CHESTNUT_BUTTON, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.signs(CHESTNUT_SIGNS, CHESTNUT_PLANKS);
        this.leaves(CHESTNUT_LEAVES);
        this.pottedPlant(CHESTNUT_SAPLING, POTTED_CHESTNUT_SAPLING);
        this.beehive(CHESTNUT_BEEHIVE);
        this.leafPile(CHESTNUT_LEAF_PILE, this.blockTexture(CHESTNUT_LEAVES.get()), true);
        this.ladder(CHESTNUT_LADDER);
        this.bookshelf(CHESTNUT_BOOKSHELF, CHESTNUT_PLANKS);
        this.boards(CHESTNUT_BOARDS);
        this.cabinet(CHESTNUT_CABINET);
        this.chests(CHESTNUT_CHEST, CHESTNUT_TRAPPED_CHEST, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.hangingSignBlocks(STRIPPED_CHESTNUT_LOG, CHESTNUT_HANGING_SIGNS);

        // pine
        this.pillar(STRIPPED_PINE_LOG);
        this.wood(STRIPPED_PINE_WOOD, this.blockTexture(STRIPPED_PINE_LOG.get()));
        this.pillar(WEATHERED_PINE_LOG);
        this.wood(WEATHERED_PINE_WOOD, this.blockTexture(WEATHERED_PINE_LOG.get()));
        this.pillar(PINE_LOG);
        this.wood(PINE_WOOD, this.blockTexture(PINE_LOG.get()));
        this.cubeAll(PINE_PLANKS);
        this.slab(PINE_SLAB, this.blockTexture(PINE_PLANKS.get()));
        this.stairs(PINE_STAIRS, this.blockTexture(PINE_PLANKS.get()));
        this.fence(PINE_FENCE, this.blockTexture(PINE_PLANKS.get()));
        this.fenceGate(PINE_FENCE_GATE, this.blockTexture(PINE_PLANKS.get()));
        this.pressurePlate(PINE_PRESSURE_PLATE, this.blockTexture(PINE_PLANKS.get()));
        this.doorCutout(PINE_DOOR);
        this.trapdoorCutout(PINE_TRAPDOOR);
        this.button(PINE_BUTTON, this.blockTexture(PINE_PLANKS.get()));
        this.signs(PINE_SIGNS, PINE_PLANKS);
        this.leaves(PINE_LEAVES);
        this.pottedPlant(PINE_SAPLING, POTTED_PINE_SAPLING);
        this.beehive(PINE_BEEHIVE);
        this.leafPile(PINE_LEAF_PILE, this.blockTexture(PINE_LEAVES.get()), false);
        this.ladder(PINE_LADDER);
        this.bookshelf(PINE_BOOKSHELF, PINE_PLANKS);
        this.boards(PINE_BOARDS);
        this.cabinet(PINE_CABINET);
        this.chests(PINE_CHEST, PINE_TRAPPED_CHEST, this.blockTexture(PINE_PLANKS.get()));
        this.hangingSignBlocks(STRIPPED_PINE_LOG, PINE_HANGING_SIGNS);

        // pinecone
        this.hangingPinecone(PINECONE);
        this.hangingPinecone(FAIRY_LIGHT);
        this.hangingPinecone(SOUL_FAIRY_LIGHT);
        this.hangingPinecone(CUPRIC_FAIRY_LIGHT);
        this.hangingPinecone(ENDER_FAIRY_LIGHT);

        this.simpleBlock(PINECONE_JAM_BLOCK.get(), this.models().getExistingFile(this.modLoc("block/pinecone_jam_block")));
        this.itemModel(PINECONE_JAM_BLOCK);

        this.horizontalBlock(FEATHER_WING.get(), this.models().getExistingFile(this.modLoc("block/feather_wing")));
        this.generatedItem(FEATHER_WING.get(), TextureFolder.BLOCK);
        this.simpleCross(FEATHER_ORNAMENT);
        this.generatedItem(FEATHER_ORNAMENT.get(), TextureFolder.BLOCK);
        this.dreamCatcher(DREAM_CATCHER);

        this.pillar(PINECONE_BLOCK);
        this.itemModel(PINECONE_BLOCK);
        this.horizontalBlock(CARVED_PINECONE_BLOCK.get(), this.models().orientable("carved_pinecone_block", this.modLoc("block/pinecone_block"), this.modLoc("block/carved_pinecone_block"), this.modLoc("block/pinecone_block_top")));
        this.itemModel(CARVED_PINECONE_BLOCK);
        this.horizontalBlock(WILL_O_THE_WISP.get(), this.models().orientable("will_o_the_wisp", this.modLoc("block/pinecone_block"), this.modLoc("block/will_o_the_wisp"), this.modLoc("block/pinecone_block_top")));
        this.itemModel(WILL_O_THE_WISP);
        this.cubeAll(PINECONE_SHINGLES);
        this.stairs(PINECONE_SHINGLE_STAIRS, this.blockTexture(PINECONE_SHINGLES.get()));
        this.slab(PINECONE_SHINGLE_SLAB, this.blockTexture(PINECONE_SHINGLES.get()));
        this.wall(PINECONE_SHINGLE_WALL, this.blockTexture(PINECONE_SHINGLES.get()));

        // snow
        this.stairs(SNOW_STAIRS, this.mcLoc("block/snow"));
        this.slab(SNOW_SLAB, this.mcLoc("block/snow_block"), this.mcLoc("block/snow"));
        this.cubeAll(SNOW_BRICKS);
        this.stairs(SNOW_BRICK_STAIRS, this.blockTexture(SNOW_BRICKS.get()));
        this.slab(SNOW_BRICK_SLAB, this.blockTexture(SNOW_BRICKS.get()));
        this.wall(SNOW_BRICK_WALL, this.blockTexture(SNOW_BRICKS.get()));

        // packed ice
        this.stairs(PACKED_ICE_STAIRS, this.blockTexture(Blocks.PACKED_ICE));
        this.slab(PACKED_ICE_SLAB, this.blockTexture(Blocks.PACKED_ICE));
        this.cubeAll(PACKED_ICE_BRICKS);
        this.cubeAll(CHISELED_PACKED_ICE_BRICKS);
        this.stairs(PACKED_ICE_BRICK_STAIRS, this.blockTexture(PACKED_ICE_BRICKS.get()));
        this.slab(PACKED_ICE_BRICK_SLAB, this.blockTexture(PACKED_ICE_BRICKS.get()));
        this.wall(PACKED_ICE_BRICK_WALL, this.blockTexture(PACKED_ICE_BRICKS.get()));

        // blue ice
        this.stairs(BLUE_ICE_STAIRS, this.blockTexture(Blocks.BLUE_ICE));
        this.slab(BLUE_ICE_SLAB, this.blockTexture(Blocks.BLUE_ICE));
        this.cubeAll(BLUE_ICE_BRICKS);
        this.cubeAll(CHISELED_BLUE_ICE_BRICKS);
        this.stairs(BLUE_ICE_BRICK_STAIRS, this.blockTexture(BLUE_ICE_BRICKS.get()));
        this.slab(BLUE_ICE_BRICK_SLAB, this.blockTexture(BLUE_ICE_BRICKS.get()));
        this.wall(BLUE_ICE_BRICK_WALL, this.blockTexture(BLUE_ICE_BRICKS.get()));

        // shale
        this.cubeAll(SHALE);
        this.stairs(SHALE_STAIRS, this.blockTexture(SHALE.get()));
        this.slab(SHALE_SLAB, this.blockTexture(SHALE.get()));
        this.wall(SHALE_WALL, this.blockTexture(SHALE.get()));
        this.cubeAll(POLISHED_SHALE);
        this.stairs(POLISHED_SHALE_STAIRS, this.blockTexture(POLISHED_SHALE.get()));
        this.slab(POLISHED_SHALE_SLAB, this.blockTexture(POLISHED_SHALE.get()));
        this.wall(POLISHED_SHALE_WALL, this.blockTexture(POLISHED_SHALE.get()));
        this.cubeAll(POLISHED_SHALE_BRICKS);
        this.cubeAll(ICY_POLISHED_SHALE_BRICKS);
        this.cubeAll(CHISELED_POLISHED_SHALE_BRICKS);
        this.stairs(POLISHED_SHALE_BRICK_STAIRS, this.blockTexture(POLISHED_SHALE_BRICKS.get()));
        this.slab(POLISHED_SHALE_BRICK_SLAB, this.blockTexture(POLISHED_SHALE_BRICKS.get()));
        this.wall(POLISHED_SHALE_BRICK_WALL, this.blockTexture(POLISHED_SHALE_BRICKS.get()));
        this.pressurePlate(POLISHED_SHALE_PRESSURE_PLATE, this.blockTexture(POLISHED_SHALE.get()));
        this.button(POLISHED_SHALE_BUTTON, this.blockTexture(POLISHED_SHALE.get()));

        // deepslate
        this.pressurePlate(POLISHED_DEEPSLATE_PRESSURE_PLATE, this.blockTexture(Blocks.POLISHED_DEEPSLATE));
        this.button(POLISHED_DEEPSLATE_BUTTON, this.blockTexture(Blocks.POLISHED_DEEPSLATE));

        // decorations
        this.wreath(HOLLY_WREATH);
        this.wreath(PINECONE_WREATH);
        this.wreath(VINE_WREATH);
        this.wreath(CHERRY_WREATH);

        MultiPartBlockStateBuilder builder = this.getMultipartBuilder(CHRISTMAS_PUDDING.get());
        builder.part().modelFile(new ModelFile.ExistingModelFile(this.modLoc("block/christmas_pudding_4"), this.models().existingFileHelper)).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.FOUR);
        builder.part().modelFile(new ModelFile.ExistingModelFile(this.modLoc("block/christmas_pudding_3"), this.models().existingFileHelper)).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.THREE);
        builder.part().modelFile(new ModelFile.ExistingModelFile(this.modLoc("block/christmas_pudding_2"), this.models().existingFileHelper)).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.TWO);
        builder.part().modelFile(new ModelFile.ExistingModelFile(this.modLoc("block/christmas_pudding_1"), this.models().existingFileHelper)).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.ONE);

        builder.part().modelFile(new ModelFile.ExistingModelFile(this.modLoc("block/christmas_pudding_4"), this.models().existingFileHelper))
                .addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.FIRE);
        builder.part().modelFile(new ModelFile.ExistingModelFile(this.modLoc("block/christmas_pudding_fire"), this.models().existingFileHelper))
                .addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.FIRE).end();

        this.generatedItem(CHRISTMAS_PUDDING.get(), TextureFolder.ITEM);
        this.horizontalBlock(FROSTBITER_TROPHY.get(), this.models().getExistingFile(this.modLoc("block/frostbiter_trophy")));
        this.generatedItem(FROSTBITER_TROPHY.get(), TextureFolder.ITEM);
        this.iceSheet(ICE_SHEET, this.blockTexture(Blocks.ICE));

        // sprouts
        this.pottedPlantWithPottedVariant(SNOWY_SPROUTS, POTTED_SNOWY_SPROUTS);
        this.pottedPlantWithPottedVariant(GELISOL_SPROUTS, POTTED_GELISOL_SPROUTS);
        this.pottedPlantWithPottedVariant(DRY_MOSSY_SPROUTS, POTTED_DRY_MOSSY_SPROUTS);
        this.pottedPlantWithPottedVariant(MOSSY_SPROUTS, POTTED_MOSSY_SPROUTS);

        // flowers
        this.tallPlant(RED_ROSE_BUSH);
        this.tallPlant(BLUE_ROSE_BUSH);
        this.tallPlant(WHITE_ROSE_BUSH);
        this.tallPlant(YELLOW_ROSE_BUSH);
        this.tallPlant(LUPINE);

        this.pottedPlant(RED_ROSE, POTTED_RED_ROSE);
        this.pottedPlant(BLUE_ROSE, POTTED_BLUE_ROSE);
        this.pottedPlant(WHITE_ROSE, POTTED_WHITE_ROSE);
        this.pottedPlant(YELLOW_ROSE, POTTED_YELLOW_ROSE);
        this.pottedPlant(FOXGLOVE, POTTED_FOXGLOVE);
        this.pottedPlant(NIGHTSHADE, POTTED_NIGHTSHADE);
        this.pottedPlantWithPottedVariant(SNOWDROP, POTTED_SNOWDROP);
        this.pottedPlantWithPottedVariant(MOSS_CAMPION, POTTED_MOSS_CAMPION);
        this.pottedPlantWithPottedVariant(WILD_GINGER, POTTED_WILD_GINGER);
        this.pottedPlantWithPottedVariant(BLUEBELLS, POTTED_BLUEBELLS);

        // lavender
        this.getVariantBuilder(LAVENDER.get())
                .partialState().with(LavenderBlock.AGE, 0).addModels(new ConfiguredModel(this.models().cross("lavender_stage0", this.modLoc("block/lavender_stage0")).renderType("cutout")))
                .partialState().with(LavenderBlock.AGE, 1).addModels(new ConfiguredModel(this.models().cross("lavender_stage1", this.modLoc("block/lavender_stage1")).renderType("cutout")))
                .partialState().with(LavenderBlock.AGE, 2).addModels(new ConfiguredModel(this.models().cross("lavender_stage2", this.modLoc("block/lavender_stage2")).renderType("cutout")));

        this.generatedItem(LAVENDER.get(), TextureFolder.ITEM);
        this.pot(POTTED_LAVENDER, this.modLoc("block/potted_lavender"));

        this.compressedBlock(LAVENDER_BALE);
        this.thatch(LAVENDER_THATCH);
        this.thatchStairs(LAVENDER_THATCH_STAIRS, this.blockTexture(LAVENDER_THATCH.get()));
        this.thatchSlab(LAVENDER_THATCH_SLAB, this.blockTexture(LAVENDER_THATCH.get()));

        // wild berry blocks
        this.wildBerryBush(WILD_BERRY_BUSH);
        this.compressedBlock(WILD_BERRY_BASKET);

        // candy cane
        this.pillar(CANDY_CANE_BLOCK);

        // icicles
        this.getVariantBuilder(ICICLES.get())
                .partialState().with(IciclesBlock.STATE, IciclesBlock.IcicleStates.NORMAL).addModels(new ConfiguredModel(this.models().cross("icicles", this.blockTexture(ICICLES.get())).renderType("cutout")))
                .partialState().with(IciclesBlock.STATE, IciclesBlock.IcicleStates.TOP).addModels(new ConfiguredModel(this.models().cross("icicles_top", this.modLoc("block/icicles_top")).renderType("cutout")))
                .partialState().with(IciclesBlock.STATE, IciclesBlock.IcicleStates.BOTTOM).addModels(new ConfiguredModel(this.models().cross("icicles_bottom", this.modLoc("block/icicles_bottom")).renderType("cutout")))
                .partialState().with(IciclesBlock.STATE, IciclesBlock.IcicleStates.FLOOR).addModels(new ConfiguredModel(this.models().cross("icicles_floor", this.modLoc("block/icicles_floor")).renderType("cutout")));
        this.generatedItem(ICICLES.get(), TextureFolder.ITEM);
        this.pillar(ICICLE_BLOCK);
        this.pillar(CHISELED_ICICLE_BLOCK);
        this.doorCutout(ICICLE_DOOR);
        this.trapdoorCutout(ICICLE_TRAPDOOR);
        this.bars(ICICLE_BARS);
        this.getVariantBuilder(ICE_LANTERN.get())
                .partialState().with(IceLanternBlock.FACING, Direction.UP).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/ice_lantern"))))
                .partialState().with(IceLanternBlock.FACING, Direction.DOWN).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/ice_lantern_hanging"))))
                .partialState().with(IceLanternBlock.FACING, Direction.NORTH).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/ice_lantern_side"))))
                .partialState().with(IceLanternBlock.FACING, Direction.SOUTH).addModels(ConfiguredModel.builder().modelFile(this.models().getExistingFile(this.modLoc("block/ice_lantern_side"))).rotationY(180).build())
                .partialState().with(IceLanternBlock.FACING, Direction.EAST).addModels(ConfiguredModel.builder().modelFile(this.models().getExistingFile(this.modLoc("block/ice_lantern_side"))).rotationY(90).build())
                .partialState().with(IceLanternBlock.FACING, Direction.WEST).addModels(ConfiguredModel.builder().modelFile(this.models().getExistingFile(this.modLoc("block/ice_lantern_side"))).rotationY(270).build());
        this.generatedItem(ICE_LANTERN.get(), TextureFolder.ITEM);

        // lunalite
        this.getVariantBuilder(LUNALITE.get())
                .partialState().with(LunaliteBlock.TOP, true).addModels(new ConfiguredModel(this.models().cubeBottomTop("lunalite", this.modLoc("block/lunalite"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_top"))))
                .partialState().with(LunaliteBlock.TOP, false).addModels(new ConfiguredModel(this.models().cubeAll("lunalite_bottom", this.modLoc("block/lunalite_bottom"))));
        this.simpleBlock(CUT_LUNALITE.get(), this.models().cubeBottomTop("cut_lunalite", this.modLoc("block/cut_lunalite"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top")));
        this.simpleBlock(CUT_LUNALITE_BRICKS.get(), this.models().cubeBottomTop("cut_lunalite_bricks", this.modLoc("block/cut_lunalite_bricks"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top")));
        this.simpleBlock(CHISELED_CUT_LUNALITE_BRICKS.get(), this.models().cubeBottomTop("chiseled_cut_lunalite_bricks", this.modLoc("block/chiseled_cut_lunalite_bricks"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top")));
        this.itemModel(LUNALITE);
        this.itemModel(CUT_LUNALITE);
        this.itemModel(CUT_LUNALITE_BRICKS);
        this.itemModel(CHISELED_CUT_LUNALITE_BRICKS);

        this.getVariantBuilder(LUNALITE_SLAB.get())
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).with(LunaliteSlabBlock.TOP, true).addModels(new ConfiguredModel(this.models().slab("lunalite_slab", this.modLoc("block/lunalite_slab"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_top"))))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).with(LunaliteSlabBlock.TOP, true).addModels(new ConfiguredModel(this.models().slabTop("lunalite_slab_top", this.modLoc("block/lunalite_slab"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_top"))))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).with(LunaliteSlabBlock.TOP, true).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/lunalite"))))
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).with(LunaliteSlabBlock.TOP, false).addModels(new ConfiguredModel(this.models().slab("lunalite_slab_bottom", this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_bottom"))))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).with(LunaliteSlabBlock.TOP, false).addModels(new ConfiguredModel(this.models().slabTop("lunalite_slab_top_bottom", this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_bottom"))))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).with(LunaliteSlabBlock.TOP, false).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/lunalite_bottom"))));
        this.models().cubeBottomTop("cut_lunalite_slab_double", this.modLoc("block/cut_lunalite_slab"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top"));
        this.slab(CUT_LUNALITE_SLAB, this.modLoc("block/cut_lunalite_slab_double"), this.modLoc("block/cut_lunalite_slab"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top"));
        this.slab(CUT_LUNALITE_BRICK_SLAB, this.blockTexture(CUT_LUNALITE_BRICKS.get()), this.modLoc("block/cut_lunalite_bricks"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top"));
        this.itemModel(LUNALITE_SLAB);

        this.stairsInversion(LUNALITE_STAIRS, this.blockTexture(LUNALITE.get()), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_top"));
        this.stairsInversion(CUT_LUNALITE_STAIRS, this.blockTexture(CUT_LUNALITE.get()), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top"));
        this.stairsInversion(CUT_LUNALITE_BRICK_STAIRS, this.blockTexture(CUT_LUNALITE_BRICKS.get()), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top"));

        this.wall(LUNALITE_WALL, this.blockTexture(LUNALITE.get()), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_top"));
        this.wall(CUT_LUNALITE_WALL, this.blockTexture(CUT_LUNALITE.get()), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top"));
        this.wall(CUT_LUNALITE_BRICK_WALL, this.blockTexture(CUT_LUNALITE_BRICKS.get()), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top"));

        this.cubeAll(SMOOTH_LUNALITE);
        this.stairs(SMOOTH_LUNALITE_STAIRS, this.blockTexture(SMOOTH_LUNALITE.get()));
        this.models().cubeBottomTop("smooth_lunalite_slab_double", this.modLoc("block/smooth_lunalite_slab"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/smooth_lunalite"));
        this.slab(SMOOTH_LUNALITE_SLAB, this.modLoc("block/smooth_lunalite_slab_double"), this.modLoc("block/smooth_lunalite_slab"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/smooth_lunalite"));

        // ginger
        this.doorCutout(GINGERBREAD_DOOR);
        this.trapdoorCutout(GINGERBREAD_TRAPDOOR);
        this.getVariantBuilder(GINGER.get())
                .partialState().with(GingerCropBlock.AGE, 0).addModels(new ConfiguredModel(this.models().crop("ginger_stage0", this.modLoc("block/ginger_stage0")).renderType("cutout")))
                .partialState().with(GingerCropBlock.AGE, 1).addModels(new ConfiguredModel(this.models().crop("ginger_stage1", this.modLoc("block/ginger_stage1")).renderType("cutout")))
                .partialState().with(GingerCropBlock.AGE, 2).addModels(new ConfiguredModel(this.models().crop("ginger_stage2", this.modLoc("block/ginger_stage2")).renderType("cutout")))
                .partialState().with(GingerCropBlock.AGE, 3).addModels(new ConfiguredModel(this.models().crop("ginger_stage3", this.modLoc("block/ginger_stage3")).renderType("cutout")))
                .partialState().with(GingerCropBlock.AGE, 4).addModels(new ConfiguredModel(this.models().crop("ginger_stage4", this.modLoc("block/ginger_stage4")).renderType("cutout")));
        this.cubeAll(GINGER_SOIL);
        this.cubeAll(GINGERBREAD_BLOCK);
        this.cubeAll(GINGERBREAD_BRICKS);
        this.stairs(GINGERBREAD_BRICK_STAIRS, this.blockTexture(GINGERBREAD_BRICKS.get()));
        this.slab(GINGERBREAD_BRICK_SLAB, this.blockTexture(GINGERBREAD_BRICKS.get()));
        this.wall(GINGERBREAD_BRICK_WALL, this.blockTexture(GINGERBREAD_BRICKS.get()));

        this.simpleBlock(GLAZED_GINGERBREAD_BLOCK.get(), this.models().cubeTop("glazed_gingerbread_block", this.blockTexture(GLAZED_GINGERBREAD_BLOCK.get()), this.modLoc("block/glazed_gingerbread_top")));
        this.cubeAll(GINGERBREAD_COOKIE_BLOCK);
        this.simpleBlock(GLAZED_GINGERBREAD_BRICKS.get(), this.models().cubeTop("glazed_gingerbread_bricks", this.blockTexture(GLAZED_GINGERBREAD_BRICKS.get()), this.modLoc("block/glazed_gingerbread_top")));
        this.stairsInversion(GLAZED_GINGERBREAD_BRICK_STAIRS, this.blockTexture(GLAZED_GINGERBREAD_BRICKS.get()), this.blockTexture(GLAZED_GINGERBREAD_BRICKS.get()), this.modLoc("block/glazed_gingerbread_top"));
        this.slab(GLAZED_GINGERBREAD_BRICK_SLAB, this.blockTexture(GLAZED_GINGERBREAD_BRICKS.get()), this.blockTexture(GLAZED_GINGERBREAD_BRICKS.get()), this.blockTexture(GLAZED_GINGERBREAD_BRICKS.get()), this.modLoc("block/glazed_gingerbread_top"));
        this.wall(GLAZED_GINGERBREAD_BRICK_WALL, this.blockTexture(GLAZED_GINGERBREAD_BRICKS.get()), this.blockTexture(GLAZED_GINGERBREAD_BRICKS.get()), this.modLoc("block/glazed_gingerbread_top"));

        this.itemModel(GLAZED_GINGERBREAD_BLOCK);
        this.itemModel(GLAZED_GINGERBREAD_BRICKS);

        this.compressedBlock(GINGER_ROOT_CRATE);

        // dry moss
        this.carpet(DRY_MOSS_CARPET, this.blockTexture(DRY_MOSS_BLOCK.get()));
        this.cubeAll(DRY_MOSS_BLOCK);
        this.cubeAll(DRY_MOSSY_COBBLESTONE);
        this.stairs(DRY_MOSSY_COBBLESTONE_STAIRS, this.blockTexture(DRY_MOSSY_COBBLESTONE.get()));
        this.slab(DRY_MOSSY_COBBLESTONE_SLAB, this.blockTexture(DRY_MOSSY_COBBLESTONE.get()));
        this.wall(DRY_MOSSY_COBBLESTONE_WALL, this.blockTexture(DRY_MOSSY_COBBLESTONE.get()));
        this.cubeAll(DRY_MOSSY_STONE_BRICKS);
        this.stairs(DRY_MOSSY_STONE_BRICK_STAIRS, this.blockTexture(DRY_MOSSY_STONE_BRICKS.get()));
        this.slab(DRY_MOSSY_STONE_BRICK_SLAB, this.blockTexture(DRY_MOSSY_STONE_BRICKS.get()));
        this.wall(DRY_MOSSY_STONE_BRICK_WALL, this.blockTexture(DRY_MOSSY_STONE_BRICKS.get()));
        this.cubeAll(DRY_MOSSY_COBBLESTONE_BRICKS);
        this.stairs(DRY_MOSSY_COBBLESTONE_BRICK_STAIRS, this.blockTexture(DRY_MOSSY_COBBLESTONE_BRICKS.get()));
        this.slab(DRY_MOSSY_COBBLESTONE_BRICK_SLAB, this.blockTexture(DRY_MOSSY_COBBLESTONE_BRICKS.get()));
        this.wall(DRY_MOSSY_COBBLESTONE_BRICK_WALL, this.blockTexture(DRY_MOSSY_COBBLESTONE_BRICKS.get()));
        this.cubeAll(DRY_MOSSY_COBBLESTONE_TILES);
        this.stairs(DRY_MOSSY_COBBLESTONE_TILE_STAIRS, this.blockTexture(DRY_MOSSY_COBBLESTONE_TILES.get()));
        this.slab(DRY_MOSSY_COBBLESTONE_TILE_SLAB, this.blockTexture(DRY_MOSSY_COBBLESTONE_TILES.get()));
        this.wall(DRY_MOSSY_COBBLESTONE_TILE_WALL, this.blockTexture(DRY_MOSSY_COBBLESTONE_TILES.get()));

        // compressed blocks
        this.compressedBlock(RED_MUSHROOM_BASKET);
        this.compressedBlock(BROWN_MUSHROOM_BASKET);
        this.compressedBlock(GLOW_SHROOM_BASKET);
        this.cubeAll(FROZEN_FLESH_BLOCK);
        this.compressedBlock(CHESTNUT_CRATE);
        this.compressedBlock(ROASTED_CHESTNUT_CRATE);

        // gelisol
        this.getVariantBuilder(GELISOL.get())
                .partialState().with(SnowyDirtBlock.SNOWY, false).addModels(new ConfiguredModel(this.models().cubeBottomTop("gelisol", this.modLoc("block/gelisol_side"), this.mcLoc("block/dirt"), this.modLoc("block/gelisol_top"))))
                .partialState().with(SnowyDirtBlock.SNOWY, true).addModels(new ConfiguredModel(this.models().cubeBottomTop("gelisol_snowy", this.modLoc("block/gelisol_side_snowy"), this.mcLoc("block/dirt"), this.mcLoc("block/snow"))));
        this.itemModel(GELISOL);
        this.simpleBlock(GELISOL_PATH.get(), this.models().withExistingParent("gelisol_path", this.mcLoc("dirt_path")).texture("top", this.modLoc("block/gelisol_path_top")).texture("side", this.modLoc("block/gelisol_path_side")));
        this.itemModel(GELISOL_PATH);
    }

    // Items //

    private void itemModel(RegistryObject<Block> block) {
        this.itemModels().withExistingParent(getItemName(block.get()), this.blockTexture(block.get()));
    }

    private void generatedItem(ItemLike item, TextureFolder folder) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc(folder.format(name)));
    }

    private void itemOnAStick(ItemLike item, TextureFolder folder) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/handheld_rod").texture("layer0", this.modLoc(folder.format(name)));
    }

    private void handheldItem(ItemLike item, TextureFolder folder) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/handheld").texture("layer0", this.modLoc(folder.format(name)));
    }

    private void generatedItemWithOverlay(ItemLike item) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc("item/" + name)).texture("layer1", this.modLoc("item/" + name + "_overlay"));
    }

    private void spawnEgg(RegistryObject<? extends Item> egg) {
        this.itemModels().withExistingParent(getItemName(egg.get()), "item/template_spawn_egg");
    }

    // Blocks //

    private void hangingPinecone(RegistryObject<Block> pinecone) {
        String name = getItemName(pinecone.get());
        ResourceLocation texture = this.blockTexture(pinecone.get());
        ModelFile model1 = this.models().withExistingParent(name + "_1", this.modLoc("block/hanging_pinecone_template_1")).texture("texture", texture);
        ModelFile model2 = this.models().withExistingParent(name + "_2", this.modLoc("block/hanging_pinecone_template_2")).texture("texture", texture);
        ModelFile model3 = this.models().withExistingParent(name + "_3", this.modLoc("block/hanging_pinecone_template_3")).texture("texture", texture);
        ModelFile model4 = this.models().withExistingParent(name + "_4", this.modLoc("block/hanging_pinecone_template_4")).texture("texture", texture);

        this.generatedItem(pinecone.get(), TextureFolder.ITEM);
        this.getVariantBuilder(pinecone.get())
                .partialState().with(PineconeBlock.AMOUNT, 1).addModels(new ConfiguredModel(model1), new ConfiguredModel(model1, 0, 90, false), new ConfiguredModel(model1, 0, 180, false), new ConfiguredModel(model1, 0, 270, false))
                .partialState().with(PineconeBlock.AMOUNT, 2).addModels(new ConfiguredModel(model2), new ConfiguredModel(model2, 0, 90, false), new ConfiguredModel(model2, 0, 180, false), new ConfiguredModel(model2, 0, 270, false))
                .partialState().with(PineconeBlock.AMOUNT, 3).addModels(new ConfiguredModel(model3), new ConfiguredModel(model3, 0, 90, false), new ConfiguredModel(model3, 0, 180, false), new ConfiguredModel(model3, 0, 270, false))
                .partialState().with(PineconeBlock.AMOUNT, 4).addModels(new ConfiguredModel(model4), new ConfiguredModel(model4, 0, 90, false), new ConfiguredModel(model4, 0, 180, false), new ConfiguredModel(model4, 0, 270, false));
    }

    private void wildBerryBush(RegistryObject<Block> bush) {
        String name = getBlockName(bush.get());
        Function<Integer, ModelFile> model = i -> this.models().cross(name + "_stage" + i, this.modLoc("block/" + name + "_stage" + i)).renderType("cutout");
        this.getVariantBuilder(bush.get())
                .partialState().with(WildBerryBushBlock.AGE, 0).addModels(new ConfiguredModel(model.apply(0), 0, 0, true))
                .partialState().with(WildBerryBushBlock.AGE, 1).addModels(new ConfiguredModel(model.apply(1), 0, 0, true))
                .partialState().with(WildBerryBushBlock.AGE, 2).addModels(new ConfiguredModel(model.apply(2), 0, 0, true))
                .partialState().with(WildBerryBushBlock.AGE, 3).addModels(new ConfiguredModel(model.apply(3), 0, 0, true));
    }

    private void dreamCatcher(RegistryObject<Block> block) {
        String name = getItemName(block.get());
        Function<String, ModelFile> model = s -> this.models().cross(name + "_" + s, this.modLoc("block/" + name + "_" + s)).renderType("cutout");

        this.generatedItem(block.get(), TextureFolder.ITEM);
        this.getVariantBuilder(block.get())
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).addModels(new ConfiguredModel(model.apply("top")))
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).addModels(new ConfiguredModel(model.apply("bottom")));
    }

    private void wreath(RegistryObject<Block> wreath) {
        ResourceLocation texture = this.blockTexture(wreath.get());

        this.horizontalBlock(wreath.get(), this.models().withExistingParent(getItemName(wreath.get()), "block/ladder").texture("particle", texture).renderType("cutout").texture("texture", texture));
        this.itemModels().withExistingParent(getItemName(wreath.get()), this.modLoc("item/wreath")).texture("layer0", texture);
    }

    private void iceSheet(RegistryObject<Block> block, ResourceLocation texture) {
        this.paneBlockWithRenderType((IronBarsBlock) block.get(), texture, texture, "translucent");
        this.itemModels().withExistingParent(getItemName(block.get()), "item/generated").texture("layer0", texture).renderType("translucent");
    }

    private void bars(RegistryObject<Block> block) {
        ResourceLocation texture = this.blockTexture(block.get());
        this.paneBlockWithRenderType((IronBarsBlock) block.get(), texture, texture, "cutout");
        this.generatedItem(block.get(), TextureFolder.BLOCK);
    }

    private void tallPlant(RegistryObject<Block> flower) {
        String name = getItemName(flower.get());
        Function<String, ModelFile> model = s -> this.models().cross(name + "_" + s, this.modLoc("block/" + name + "_" + s)).renderType("cutout");

        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc("block/" + name + "_top"));
        this.getVariantBuilder(flower.get())
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).addModels(new ConfiguredModel(model.apply("top")))
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).addModels(new ConfiguredModel(model.apply("bottom")));
    }

    private void pottedPlant(RegistryObject<Block> plant, RegistryObject<Block> pot) {
        this.pot(pot, this.blockTexture(plant.get()));
        this.simpleCross(plant);
        this.generatedItem(plant.get(), TextureFolder.BLOCK);
    }

    private void pottedPlantWithPottedVariant(RegistryObject<Block> plant, RegistryObject<Block> pot) {
        this.pot(pot, this.modLoc("block/potted_" + getItemName(plant.get())));
        this.simpleCross(plant);
        this.generatedItem(plant.get(), TextureFolder.BLOCK);
    }

    private void pot(RegistryObject<Block> pot, ResourceLocation texture) {
        ModelFile model = this.models().withExistingParent(getBlockName(pot.get()), "block/flower_pot_cross").texture("plant", texture).renderType("cutout");
        this.simpleBlock(pot.get(), model);
    }

    private void carpet(RegistryObject<Block> carpet, ResourceLocation texture) {
        this.simpleBlock(carpet.get(), this.models().carpet(getItemName(carpet.get()), texture));
        this.itemModel(carpet);
    }

    private void trapdoor(RegistryObject<Block> trapdoor) {
        this.trapdoorBlock((TrapDoorBlock) trapdoor.get(), this.blockTexture(trapdoor.get()), true);
        this.itemModels().withExistingParent(getItemName(trapdoor.get()), this.modLoc("block/" + getItemName(trapdoor.get()) + "_bottom"));
    }

    private void trapdoorCutout(RegistryObject<Block> trapdoor) {
        this.trapdoorBlockWithRenderType((TrapDoorBlock) trapdoor.get(), this.blockTexture(trapdoor.get()), true, "cutout");
        this.itemModels().withExistingParent(getItemName(trapdoor.get()), this.modLoc("block/" + getItemName(trapdoor.get()) + "_bottom"));
    }

    private void door(RegistryObject<Block> door) {
        String name = getItemName(door.get());
        this.doorBlock((DoorBlock) door.get(), name.replace("_door", ""), this.modLoc("block/" + name + "_bottom"), this.modLoc("block/" + name + "_top"));
        this.generatedItem(door.get(), TextureFolder.ITEM);
    }

    private void doorCutout(RegistryObject<Block> door) {
        String name = getItemName(door.get());
        this.doorBlockWithRenderType((DoorBlock) door.get(), name.replace("_door", ""), this.modLoc("block/" + name + "_bottom"), this.modLoc("block/" + name + "_top"), "cutout");
        this.generatedItem(door.get(), TextureFolder.ITEM);
    }

    private void button(RegistryObject<Block> button, ResourceLocation texture) {
        this.buttonBlock((ButtonBlock) button.get(), texture);
        this.itemModels().buttonInventory(getItemName(button.get()), texture);
    }

    private void pressurePlate(RegistryObject<Block> pressurePlate, ResourceLocation texture) {
        this.pressurePlateBlock((PressurePlateBlock) pressurePlate.get(), texture);
        this.itemModel(pressurePlate);
    }

    private void ladder(RegistryObject<Block> ladder) {
        ResourceLocation texture = this.blockTexture(ladder.get());

        this.horizontalBlock(ladder.get(), this.models().withExistingParent(getItemName(ladder.get()), "block/ladder").texture("particle", texture).renderType("cutout").texture("texture", texture));
        this.generatedItem(ladder.get(), TextureFolder.BLOCK);
    }

    private void fence(RegistryObject<Block> fence, ResourceLocation texture) {
        this.fenceBlock((FenceBlock) fence.get(), texture);
        this.itemModels().fenceInventory(getItemName(fence.get()), texture);
    }

    private void fenceGate(RegistryObject<Block> gate, ResourceLocation texture) {
        this.fenceGateBlock((FenceGateBlock) gate.get(), texture);
        this.itemModel(gate);
    }

    private void slab(RegistryObject<Block> slab, ResourceLocation fullModel, ResourceLocation texture) {
        this.slabBlock((SlabBlock) slab.get(), fullModel, texture);
        this.itemModel(slab);
    }

    private void slab(RegistryObject<Block> slab, ResourceLocation texture) {
        this.slabBlock((SlabBlock) slab.get(), texture, texture);
        this.itemModel(slab);
    }

    private void slab(RegistryObject<Block> slab, ResourceLocation fullModel, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        this.slabBlock((SlabBlock) slab.get(), fullModel, side, bottom, top);
        this.itemModel(slab);
    }

    private void stairs(RegistryObject<Block> stairs, ResourceLocation texture) {
        this.stairsBlock((StairBlock) stairs.get(), texture);
        this.itemModel(stairs);
    }

    private void stairs(RegistryObject<Block> stairs, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        this.stairsBlock((StairBlock) stairs.get(), side, bottom, top);
        this.itemModel(stairs);
    }

    private void stairsInversion(RegistryObject<Block> block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        String name = getBlockName(block.get());
        ModelFile stairs = this.models().stairs(name, side, bottom, top);
        ModelFile stairsInner = this.models().stairsInner(name + "_inner", side, bottom, top);
        ModelFile stairsOuter = this.models().stairsOuter(name + "_outer", side, bottom, top);
        ModelFile stairsInverse = this.models().stairs(name + "_inverse", side, top, bottom);
        ModelFile stairsInnerInverse = this.models().stairsInner(name + "_inner_inverse", side, top, bottom);
        ModelFile stairsOuterInverse = this.models().stairsOuter(name + "_outer_inverse", side, top, bottom);

        this.itemModel(block);
        this.getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            Direction facing = state.getValue(StairBlock.FACING);
            Half half = state.getValue(StairBlock.HALF);
            StairsShape shape = state.getValue(StairBlock.SHAPE);
            ModelFile model = half == Half.BOTTOM ? stairs : stairsInverse;
            ModelFile inner = half == Half.BOTTOM ? stairsInner : stairsInnerInverse;
            ModelFile outer = half == Half.BOTTOM ? stairsOuter : stairsOuterInverse;

            int yRot = (int) facing.getClockWise().toYRot();
            if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) yRot += 270;
            if (shape != StairsShape.STRAIGHT && half == Half.TOP) yRot += 90;
            yRot %= 360;

            return ConfiguredModel.builder()
                    .modelFile(shape == StairsShape.STRAIGHT ? model : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? inner : outer)
                    .rotationX(half == Half.BOTTOM ? 0 : 180)
                    .rotationY(yRot)
                    .uvLock(yRot != 0 || half == Half.TOP)
                    .build();
        }, StairBlock.WATERLOGGED);
    }

    private void wall(RegistryObject<Block> wall, ResourceLocation texture) {
        this.wallBlock((WallBlock) wall.get(), texture);
        this.itemModels().wallInventory(getItemName(wall.get()), texture);
    }

    private void wall(RegistryObject<Block> wall, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        String name = getBlockName(wall.get());
        ModelFile postModel = this.models().withExistingParent(name + "_post", this.modLoc("block/template_wall_post")).texture("side", side).texture("bottom", bottom).texture("top", top);
        ModelFile sideModel = this.models().withExistingParent(name + "_side", this.modLoc("block/template_wall_side")).texture("side", side).texture("bottom", bottom).texture("top", top);
        ModelFile sideTallModel = this.models().withExistingParent(name + "_side_tall", this.modLoc("block/template_wall_side_tall")).texture("side", side).texture("bottom", bottom).texture("top", top);

        this.wallBlock((WallBlock) wall.get(), postModel, sideModel, sideTallModel);
        this.itemModels().withExistingParent(name, this.modLoc("block/wall_inventory")).texture("side", side).texture("bottom", bottom).texture("top", top);
    }

    private void cubeAll(RegistryObject<Block> block) {
        this.simpleBlock(block.get());
        this.itemModel(block);
    }

    private void compressedBlock(RegistryObject<Block> block) {
        String name = getItemName(block.get());
        this.directionalBlock(block.get(), this.models().cubeBottomTop(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_bottom"), this.modLoc("block/" + name + "_top")));
        this.itemModel(block);
    }

    private void leaves(RegistryObject<Block> leaves) {
        this.simpleBlock(leaves.get(), this.models().withExistingParent(getItemName(leaves.get()), "block/leaves").texture("all", this.blockTexture(leaves.get())));
        this.itemModel(leaves);
    }

    private void simpleCross(RegistryObject<Block> block) {
        this.simpleBlock(block.get(), this.models().cross(getItemName(block.get()), this.blockTexture(block.get())).renderType("cutout"));
    }

    private void signs(Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> signs, Supplier<Block> planks) {
        this.signBlock(signs.getFirst().get(), signs.getSecond().get(), this.blockTexture(planks.get()));
        this.generatedItem(signs.getFirst().get(), TextureFolder.ITEM);
    }

    private void beehive(RegistryObject<Block> beehive) {
        String name = getItemName(beehive.get());
        ModelFile model = this.models().orientable(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front"), this.modLoc("block/" + name + "_end"));
        ModelFile model_honey = this.models().orientable(name + "_honey", this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front_honey"), this.modLoc("block/" + name + "_end"));

        this.horizontalBlock(beehive.get(), s -> s.getValue(BeehiveBlock.HONEY_LEVEL) == 5 ? model_honey : model);
        this.itemModel(beehive);
    }

    private void cabinet(RegistryObject<Block> cabinet) {
        String name = getItemName(cabinet.get());
        ModelFile model = this.models().orientable(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front"), this.modLoc("block/" + name + "_end"));
        ModelFile model_open = this.models().orientable(name + "_open", this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front_open"), this.modLoc("block/" + name + "_end"));

        this.horizontalBlock(cabinet.get(), s -> s.getValue(BlockStateProperties.OPEN) ? model_open : model);
        this.itemModel(cabinet);
    }

    private void pillar(RegistryObject<Block> pillar) {
        this.axisBlock((RotatedPillarBlock) pillar.get(), this.blockTexture(pillar.get()), this.modLoc("block/" + getItemName(pillar.get()) + "_top"));
        this.itemModel(pillar);
    }

    private void wood(RegistryObject<Block> log, ResourceLocation texture) {
        this.axisBlock((RotatedPillarBlock) log.get(), texture, texture);
        this.itemModel(log);
    }

    private void bookshelf(RegistryObject<Block> bookshelf, Supplier<Block> planks) {
        this.simpleBlock(bookshelf.get(), this.models().cubeColumn(getItemName(bookshelf.get()), this.blockTexture(bookshelf.get()), this.blockTexture(planks.get())));
        this.itemModel(bookshelf);
    }

    // Blueprint Models //

    private void chests(RegistryObject<BlueprintChestBlock> chest, RegistryObject<BlueprintTrappedChestBlock> trapped, ResourceLocation texture) {
        ModelFile model = this.models().getBuilder(getItemName(chest.get())).texture("particle", texture);
        this.simpleBlock(chest.get(), model);
        this.simpleBlock(trapped.get(), model);
        this.itemModels().withExistingParent(getItemName(chest.get()), "blueprint:item/template_chest");
        this.itemModels().withExistingParent(getItemName(trapped.get()), "blueprint:item/template_chest");
    }

    private void leafPile(RegistryObject<Block> leafPile, ResourceLocation texture, boolean tinted) {
        ModelFile model = this.models().withExistingParent(getItemName(leafPile.get()), "blueprint:block/" + (tinted ? "tinted_" : "") + "leaf_pile").texture("all", texture).renderType("cutout");

        this.itemModels().withExistingParent(getItemName(leafPile.get()), "item/generated").texture("layer0", texture);
        this.getMultipartBuilder(leafPile.get())
                .part().modelFile(model).uvLock(true).rotationX(270).addModel().condition(BlockStateProperties.UP, true).end()
                .part().modelFile(model).uvLock(true).rotationX(90).addModel().condition(BlockStateProperties.DOWN, true).end()
                .part().modelFile(model).addModel().condition(BlockStateProperties.NORTH, true).end()
                .part().modelFile(model).uvLock(true).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, true).end()
                .part().modelFile(model).uvLock(true).rotationY(90).addModel().condition(BlockStateProperties.EAST, true).end()
                .part().modelFile(model).uvLock(true).rotationY(270).addModel().condition(BlockStateProperties.WEST, true).end();
    }

    private void boards(RegistryObject<Block> boards) {
        ResourceLocation texture = this.blockTexture(boards.get());
        ModelFile boardsModel = this.models().getBuilder(getItemName(boards.get())).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards"))).texture("all", texture);
        ModelFile boardsHorizontalModel = this.models().getBuilder(getItemName(boards.get()) + "_horizontal").parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards_horizontal"))).texture("all", texture);

        this.itemModel(boards);
        this.getVariantBuilder(boards.get())
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState().modelFile(boardsModel).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState().modelFile(boardsHorizontalModel).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState().modelFile(boardsHorizontalModel).rotationY(270).addModel();
    }

    private void thatch(RegistryObject<Block> thatch) {
        String name = getItemName(thatch.get());
        ResourceLocation texture = this.blockTexture(thatch.get());
        ResourceLocation extrudes = this.modLoc("block/" + name + "_extrudes");

        this.simpleBlock(thatch.get(), this.models().withExistingParent(name, "blueprint:block/template_thatch").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout"));
        this.itemModel(thatch);
    }

    private void thatchSlab(RegistryObject<Block> thatch, ResourceLocation texture) {
        String name = getItemName(thatch.get());
        ResourceLocation extrudes = new ResourceLocation(texture.getNamespace(), texture.getPath() + "_extrudes");

        ModelFile bottom = this.models().withExistingParent(name, "blueprint:block/template_thatch_slab").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
        ModelFile top = this.models().withExistingParent(name + "_top", "blueprint:block/template_thatch_slab_top").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");

        this.slabBlock((SlabBlock) thatch.get(), bottom, top, this.models().getExistingFile(texture));
        this.itemModel(thatch);
    }

    private void thatchStairs(RegistryObject<Block> thatch, ResourceLocation texture) {
        String name = getItemName(thatch.get());
        ResourceLocation extrudes = new ResourceLocation(texture.getNamespace(), texture.getPath() + "_extrudes");

        ModelFile stairs = this.models().withExistingParent(name, "blueprint:block/template_thatch_stairs").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
        ModelFile inner = this.models().withExistingParent(name + "_inner", "blueprint:block/template_thatch_stairs_inner").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
        ModelFile outer = this.models().withExistingParent(name + "_outer", "blueprint:block/template_thatch_stairs_outer").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
        ModelFile inner_top = this.models().withExistingParent(name + "_inner_top", "blueprint:block/template_thatch_stairs_inner_top").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
        ModelFile outer_top = this.models().withExistingParent(name + "_outer_top", "blueprint:block/template_thatch_stairs_outer_top").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
        ModelFile top = this.models().withExistingParent(name + "_top", "blueprint:block/template_thatch_stairs_top").texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");

        this.itemModel(thatch);
        this.getVariantBuilder(thatch.get())
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(StairBlock.FACING);
                    Half half = state.getValue(StairBlock.HALF);
                    StairsShape shape = state.getValue(StairBlock.SHAPE);
                    int yRot = (int) facing.getClockWise().toYRot();

                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) yRot += 270;
                    if (half == Half.TOP && shape == StairsShape.STRAIGHT) yRot += 180;
                    if (half == Half.TOP && (shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT)) yRot += 90;

                    yRot %= 360;

                    return ConfiguredModel.builder().modelFile(shape == StairsShape.STRAIGHT ? (half == Half.BOTTOM ? stairs : top)
                            : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? (half == Half.BOTTOM ? inner : inner_top)
                            : (half == Half.BOTTOM ? outer : outer_top)).rotationY(yRot).uvLock(true).build();
                }, StairBlock.WATERLOGGED);
    }

    // Util //

    private static String getItemName(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
    }

    private static String getBlockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private enum TextureFolder {
        ITEM, BLOCK;

        public String format(String itemName) {
            return this.name().toLowerCase() + '/' + itemName;
        }
    }

}

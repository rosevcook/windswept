package com.rosemods.windswept.core.data.client;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.WildBerryBushBlock;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock.VerticalSlabType;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.Blueprint;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptModelProvider extends BlockStateProvider {

    public WindsweptModelProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        //item models
        this.generatedItem(HOLLY_BOATS.getFirst().get(), TextureFolder.Item);
        this.generatedItem(HOLLY_BOATS.getSecond().get(), TextureFolder.Item);
        this.generatedItem(CHESTNUT_BOATS.getFirst().get(), TextureFolder.Item);
        this.generatedItem(CHESTNUT_BOATS.getSecond().get(), TextureFolder.Item);
        this.generatedItem(HOLLY_BERRIES.get(), TextureFolder.Item);
        this.generatedItem(WOODEN_BUCKET.get(), TextureFolder.Item);
        this.generatedItem(WOODEN_MILK_BUCKET.get(), TextureFolder.Item);
        this.generatedItem(WOODEN_POWDER_SNOW_BUCKET.get(), TextureFolder.Item);
        this.generatedItem(WOODEN_WATER_BUCKET.get(), TextureFolder.Item);
        this.generatedItem(WILD_BERRIES.get(), TextureFolder.Item);
        this.generatedItem(WILD_BERRY_COOKIE.get(), TextureFolder.Item);
        this.generatedItem(WILD_BERRY_JUICE.get(), TextureFolder.Item);
        this.generatedItem(WILD_BERRY_BOWL.get(), TextureFolder.Item);
        this.generatedItem(SWEET_BERRY_BOWL.get(), TextureFolder.Item);
        this.generatedItem(MUTTON_PIE.get(), TextureFolder.Item);
        this.generatedItem(GOAT.get(), TextureFolder.Item);
        this.generatedItem(COOKED_GOAT.get(), TextureFolder.Item);
        this.generatedItem(GOAT_STEW.get(), TextureFolder.Item);
        this.generatedItem(GOAT_SHANKS.get(), TextureFolder.Item);
        this.generatedItem(COOKED_GOAT_SHANKS.get(), TextureFolder.Item);
        this.generatedItem(FOUL_BERRY_BOWL.get(), TextureFolder.Item);
        this.generatedItem(WILD_BERRY_PIPS.get(), TextureFolder.Item);
        this.generatedItemWithOverlay(SNOW_BOOTS.get());
        this.generatedItem(FROST_ARROW.get(), TextureFolder.Item);
        this.itemOnAStick(HOLLY_BERRIES_ON_A_STICK.get(), TextureFolder.Item);
        this.generatedItem(FROZEN_FLESH.get(), TextureFolder.Item);
        this.generatedItem(FROZEN_BRANCH.get(), TextureFolder.Item);
        this.generatedItem(SNOW_GOLEM_BANNER_PATTERN.get(), TextureFolder.Item);
        this.generatedItem(SNOW_CHARGE_BANNER_PATTERN.get(), TextureFolder.Item);
        this.generatedItem(ROSE_FLOWER_BANNER_PATTERN.get(), TextureFolder.Item);
        this.generatedItem(MUSIC_DISC_RAIN.get(), TextureFolder.Item);
        this.generatedItem(MUSIC_DISC_SNOW.get(), TextureFolder.Item);
        this.generatedItem(MUSIC_DISC_BUMBLEBEE.get(), TextureFolder.Item);
        this.spawnEgg(CHILLED_SPAWN_EGG);
        this.spawnEgg(FROSTBITER_SPAWN_EGG);
        this.generatedItem(HOLLY_FURNACE_BOAT.get(), TextureFolder.Item);
        this.generatedItem(LARGE_HOLLY_BOAT.get(), TextureFolder.Item);
        this.generatedItem(CHESTNUT_FURNACE_BOAT.get(), TextureFolder.Item);
        this.generatedItem(LARGE_CHESTNUT_BOAT.get(), TextureFolder.Item);
        this.generatedItem(CHESTNUTS.get(), TextureFolder.Item);
        this.generatedItem(ROASTED_CHESTNUTS.get(), TextureFolder.Item);
        this.generatedItem(CHESTNUT_SOUP.get(), TextureFolder.Item);
        this.generatedItem(CHESTNUT_RISOTTO.get(), TextureFolder.Item);
        this.generatedItem(CHESTNUT_CHICKEN_PLATTER.get(), TextureFolder.Item);

        //block models
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
        this.hedge(HOLLY_HEDGE, HOLLY_LEAVES, HOLLY_LOG);
        this.leafCarpet(HOLLY_LEAF_CARPET, this.blockTexture(HOLLY_LEAVES.get()));
        this.leafPile(HOLLY_LEAF_PILE, this.blockTexture(HOLLY_LEAVES.get()), false);
        this.ladder(HOLLY_LADDER);
        this.verticalPlanks(VERTICAL_HOLLY_PLANKS, this.blockTexture(HOLLY_PLANKS.get()));
        this.bookshelf(HOLLY_BOOKSHELF, HOLLY_PLANKS);
        this.boards(HOLLY_BOARDS);
        this.cabinet(HOLLY_CABINET);
        this.verticalSlab(HOLLY_VERTICAL_SLAB, this.blockTexture(HOLLY_PLANKS.get()));
        this.post(HOLLY_POST, this.blockTexture(HOLLY_LOG.get()));
        this.post(STRIPPED_HOLLY_POST, this.blockTexture(STRIPPED_HOLLY_LOG.get()));
        this.chests(HOLLY_CHEST, HOLLY_TRAPPED_CHEST, this.blockTexture(HOLLY_PLANKS.get()));
        this.ladder(HOLLY_WREATH);
        this.compressedBlock(HOLLY_BERRY_BASKET);

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
        this.doorCutout(CHESTNUT_DOOR);
        this.trapdoorCutout(CHESTNUT_TRAPDOOR);
        this.button(CHESTNUT_BUTTON, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.signs(CHESTNUT_SIGNS, CHESTNUT_PLANKS);
        this.leaves(CHESTNUT_LEAVES);
        this.pottedPlant(CHESTNUT_SAPLING, POTTED_CHESTNUT_SAPLING);
        this.beehive(CHESTNUT_BEEHIVE);
        this.hedge(CHESTNUT_HEDGE, CHESTNUT_LEAVES, CHESTNUT_LOG);
        this.leafCarpet(CHESTNUT_LEAF_CARPET, this.blockTexture(CHESTNUT_LEAVES.get()));
        this.leafPile(CHESTNUT_LEAF_PILE, this.blockTexture(CHESTNUT_LEAVES.get()), true);
        this.ladder(CHESTNUT_LADDER);
        this.verticalPlanks(VERTICAL_CHESTNUT_PLANKS, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.bookshelf(CHESTNUT_BOOKSHELF, CHESTNUT_PLANKS);
        this.boards(CHESTNUT_BOARDS);
        this.cabinet(CHESTNUT_CABINET);
        this.verticalSlab(CHESTNUT_VERTICAL_SLAB, this.blockTexture(CHESTNUT_PLANKS.get()));
        this.post(CHESTNUT_POST, this.blockTexture(CHESTNUT_LOG.get()));
        this.post(STRIPPED_CHESTNUT_POST, this.blockTexture(STRIPPED_CHESTNUT_LOG.get()));
        this.chests(CHESTNUT_CHEST, CHESTNUT_TRAPPED_CHEST, this.blockTexture(CHESTNUT_PLANKS.get()));

        this.compressedBlock(CHESTNUT_CRATE);
        this.compressedBlock(ROASTED_CHESTNUT_CRATE);

        this.stairs(SNOW_STAIRS, this.mcLoc("block/snow"));
        this.slab(SNOW_SLAB, this.mcLoc("block/snow_block"), this.mcLoc("block/snow"));
        this.verticalSlab(SNOW_VERTICAL_SLAB, this.mcLoc("block/snow_block"), this.mcLoc("block/snow"));

        this.cubeAll(SNOW_BRICKS);
        this.stairs(SNOW_BRICK_STAIRS, this.blockTexture(SNOW_BRICKS.get()));
        this.slab(SNOW_BRICK_SLAB, this.blockTexture(SNOW_BRICKS.get()));
        this.wall(SNOW_BRICK_WALL, this.blockTexture(SNOW_BRICKS.get()));
        this.verticalSlab(SNOW_BRICK_VERTICAL_SLAB, this.blockTexture(SNOW_BRICKS.get()));

        this.stairs(PACKED_ICE_STAIRS, this.blockTexture(Blocks.PACKED_ICE));
        this.slab(PACKED_ICE_SLAB, this.blockTexture(Blocks.PACKED_ICE));
        this.verticalSlab(PACKED_ICE_VERTICAL_SLAB, this.blockTexture(Blocks.PACKED_ICE));
        this.cubeAll(PACKED_ICE_BRICKS);
        this.cubeAll(CHISELED_PACKED_ICE_BRICKS);
        this.stairs(PACKED_ICE_BRICK_STAIRS, this.blockTexture(PACKED_ICE_BRICKS.get()));
        this.slab(PACKED_ICE_BRICK_SLAB, this.blockTexture(PACKED_ICE_BRICKS.get()));
        this.wall(PACKED_ICE_BRICK_WALL, this.blockTexture(PACKED_ICE_BRICKS.get()));
        this.verticalSlab(PACKED_ICE_BRICK_VERTICAL_SLAB, this.blockTexture(PACKED_ICE_BRICKS.get()));

        this.stairs(BLUE_ICE_STAIRS, this.blockTexture(Blocks.BLUE_ICE));
        this.slab(BLUE_ICE_SLAB, this.blockTexture(Blocks.BLUE_ICE));
        this.verticalSlab(BLUE_ICE_VERTICAL_SLAB, this.blockTexture(Blocks.BLUE_ICE));
        this.cubeAll(BLUE_ICE_BRICKS);
        this.cubeAll(CHISELED_BLUE_ICE_BRICKS);
        this.stairs(BLUE_ICE_BRICK_STAIRS, this.blockTexture(BLUE_ICE_BRICKS.get()));
        this.slab(BLUE_ICE_BRICK_SLAB, this.blockTexture(BLUE_ICE_BRICKS.get()));
        this.wall(BLUE_ICE_BRICK_WALL, this.blockTexture(BLUE_ICE_BRICKS.get()));
        this.verticalSlab(BLUE_ICE_BRICK_VERTICAL_SLAB, this.blockTexture(BLUE_ICE_BRICKS.get()));

        this.ice(CUT_ICE);
        this.iceSheet(ICE_SHEET, this.blockTexture(Blocks.ICE));
        this.iceSheet(CUT_ICE_SHEET, this.blockTexture(CUT_ICE.get()));

        this.pottedPlantWithPottedVariant(SNOWY_SPROUTS, POTTED_SNOWY_SPROUTS);
        this.pottedPlantWithPottedVariant(RED_TUNDRA_SPROUTS, POTTED_RED_TUNDRA_SPROUTS);
        this.pottedPlantWithPottedVariant(YELLOW_TUNDRA_SPROUTS, POTTED_YELLOW_TUNDRA_SPROUTS);

        this.tallPlant(RED_ROSE_BUSH);
        this.tallPlant(PINK_ROSE_BUSH);
        this.tallPlant(BLUE_ROSE_BUSH);
        this.tallPlant(WHITE_ROSE_BUSH);
        this.tallPlant(YELLOW_ROSE_BUSH);
        this.tallPlant(WITHER_ROSE_BUSH);

        this.pottedPlant(RED_ROSE, POTTED_RED_ROSE);
        this.pottedPlant(PINK_ROSE, POTTED_PINK_ROSE);
        this.pottedPlant(BLUE_ROSE, POTTED_BLUE_ROSE);
        this.pottedPlant(WHITE_ROSE, POTTED_WHITE_ROSE);
        this.pottedPlant(YELLOW_ROSE, POTTED_YELLOW_ROSE);
        this.pottedPlant(FOXGLOVE, POTTED_FOXGLOVE);
        this.pottedPlant(NIGHTSHADE, POTTED_NIGHTSHADE);
        this.pottedPlantWithPottedVariant(SNOWDROP, POTTED_SNOWDROP);
        this.pottedPlantWithPottedVariant(BLUEBELLS, POTTED_BLUEBELLS);

        this.wildBerryBush(WILD_BERRY_BUSH);
        this.compressedBlock(WILD_BERRY_BASKET);

        this.simpleCross(ICICLE);
        this.generatedItem(ICICLE.get(), TextureFolder.Block);
        this.pillar(ICICLE_BLOCK);
        this.pillar(CHISELED_ICICLE_BLOCK);

        this.carpet(TUNDRA_MOSS_CARPET, this.blockTexture(TUNDRA_MOSS_BLOCK.get()));
        this.cubeAll(TUNDRA_MOSS_BLOCK);
        this.cubeAll(TUNDRA_MOSSY_COBBLESTONE);
        this.stairs(TUNDRA_MOSSY_COBBLESTONE_STAIRS, this.blockTexture(TUNDRA_MOSSY_COBBLESTONE.get()));
        this.slab(TUNDRA_MOSSY_COBBLESTONE_SLAB, this.blockTexture(TUNDRA_MOSSY_COBBLESTONE.get()));
        this.wall(TUNDRA_MOSSY_COBBLESTONE_WALL, this.blockTexture(TUNDRA_MOSSY_COBBLESTONE.get()));
        this.verticalSlab(TUNDRA_MOSSY_COBBLESTONE_VERTICAL_SLAB, this.blockTexture(TUNDRA_MOSSY_COBBLESTONE.get()));
        this.cubeAll(TUNDRA_MOSSY_STONE_BRICKS);
        this.stairs(TUNDRA_MOSSY_STONE_BRICK_STAIRS, this.blockTexture(TUNDRA_MOSSY_STONE_BRICKS.get()));
        this.slab(TUNDRA_MOSSY_STONE_BRICK_SLAB, this.blockTexture(TUNDRA_MOSSY_STONE_BRICKS.get()));
        this.wall(TUNDRA_MOSSY_STONE_BRICK_WALL, this.blockTexture(TUNDRA_MOSSY_STONE_BRICKS.get()));
        this.verticalSlab(TUNDRA_MOSSY_STONE_BRICK_VERTICAL_SLAB, this.blockTexture(TUNDRA_MOSSY_STONE_BRICKS.get()));

        this.compressedBlock(RED_MUSHROOM_BASKET);
        this.compressedBlock(BROWN_MUSHROOM_BASKET);
        this.compressedBlock(GLOW_SHROOM_BASKET);

        this.horizontalBlock(FROSTBITER_TROPHY.get(), this.models().getExistingFile(this.modLoc("block/frostbiter_trophy")));
        this.generatedItem(FROSTBITER_TROPHY.get(), TextureFolder.Item);
        this.cubeAll(FROZEN_FLESH_BLOCK);
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

    private void generatedItemWithOverlay(ItemLike item) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc("item/" + name)).texture("layer1", this.modLoc("item/" + name + "_overlay"));
    }

    private void spawnEgg(RegistryObject<? extends Item> egg) {
        this.itemModels().withExistingParent(getItemName(egg.get()), "item/template_spawn_egg");
    }

    // Blocks //

    private void wildBerryBush(RegistryObject<Block> bush) {
        String name = getBlockName(bush.get());
        Function<Integer, ModelFile> model = i -> this.models().cross(name + "_stage" + i, this.modLoc("block/" + name + "_stage" + i)).renderType("cutout");
        this.getVariantBuilder(bush.get())
                .partialState().with(WildBerryBushBlock.AGE, 0).addModels(new ConfiguredModel(model.apply(0), 0, 0, true))
                .partialState().with(WildBerryBushBlock.AGE, 1).addModels(new ConfiguredModel(model.apply(1), 0, 0, true))
                .partialState().with(WildBerryBushBlock.AGE, 2).addModels(new ConfiguredModel(model.apply(2), 0, 0, true))
                .partialState().with(WildBerryBushBlock.AGE, 3).addModels(new ConfiguredModel(model.apply(3), 0, 0, true));
    }

    private void iceSheet(RegistryObject<Block> block, ResourceLocation texture) {
        this.paneBlockWithRenderType((IronBarsBlock) block.get(), texture, texture, "translucent");
        this.itemModels().withExistingParent(getItemName(block.get()), "item/generated").texture("layer0", texture).renderType("translucent");
    }

    private void ice(RegistryObject<Block> block) {
        this.simpleBlock(block.get(), this.models().cubeAll(getItemName(block.get()), this.blockTexture(block.get())).renderType("translucent"));
        this.itemModel(block);
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
        this.generatedItem(plant.get(), TextureFolder.Block);
    }

    private void pottedPlantWithPottedVariant(RegistryObject<Block> plant, RegistryObject<Block> pot) {
        this.pot(pot, this.modLoc("block/potted_" + getItemName(plant.get())));
        this.simpleCross(plant);
        this.generatedItem(plant.get(), TextureFolder.Block);
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
        this.generatedItem(door.get(), TextureFolder.Item);
    }

    private void doorCutout(RegistryObject<Block> door) {
        String name = getItemName(door.get());
        this.doorBlockWithRenderType((DoorBlock) door.get(), name.replace("_door", ""), this.modLoc("block/" + name + "_bottom"), this.modLoc("block/" + name + "_top"), "cutout");
        this.generatedItem(door.get(), TextureFolder.Item);
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
        this.generatedItem(ladder.get(), TextureFolder.Block);
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

    private void stairs(RegistryObject<Block> stairs, ResourceLocation texture) {
        this.stairsBlock((StairBlock) stairs.get(), texture);
        this.itemModel(stairs);
    }

    private void wall(RegistryObject<Block> wall, ResourceLocation texture) {
        this.wallBlock((WallBlock) wall.get(), texture);
        this.itemModels().wallInventory(getItemName(wall.get()), texture);
    }

    private void cubeAll(RegistryObject<Block> block) {
        this.simpleBlock(block.get());
        this.itemModel(block);
    }

    private void compressedBlock(RegistryObject<Block> block) {
        String name = getItemName(block.get());
        this.directionalBlock(block.get(), models().cubeBottomTop(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_bottom"), this.modLoc("block/" + name + "_top")));
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
        this.generatedItem(signs.getFirst().get(), TextureFolder.Item);
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

    private void hedge(RegistryObject<Block> hedge, Supplier<Block> leaves, Supplier<Block> log) {
        String name = getItemName(hedge.get());
        ModelFile post = this.models().withExistingParent(name + "_post", "blueprint:block/hedge_post").texture("leaf", this.blockTexture(leaves.get())).texture("log", this.blockTexture(log.get())).renderType("cutout");
        ModelFile side = this.models().withExistingParent(name + "_side", "blueprint:block/hedge_side").texture("leaf", this.blockTexture(leaves.get())).renderType("cutout");
        ModelFile extend = this.models().withExistingParent(name + "_extend", "blueprint:block/hedge_extend").texture("leaf", this.blockTexture(leaves.get())).renderType("cutout");

        this.itemModels().withExistingParent(name, this.modLoc("block/" + name + "_post"));
        this.getMultipartBuilder(hedge.get())
                .part().modelFile(post).addModel().condition(BooleanProperty.create("extend"), false).end()
                .part().modelFile(extend).addModel().condition(BooleanProperty.create("extend"), true).end()
                .part().modelFile(side).uvLock(true).addModel().condition(CrossCollisionBlock.NORTH, true).end()
                .part().modelFile(side).uvLock(true).rotationY(90).addModel().condition(CrossCollisionBlock.EAST, true).end()
                .part().modelFile(side).uvLock(true).rotationY(180).addModel().condition(CrossCollisionBlock.SOUTH, true).end()
                .part().modelFile(side).uvLock(true).rotationY(270).addModel().condition(CrossCollisionBlock.WEST, true).end();
    }

    private void chests(RegistryObject<BlueprintChestBlock> chest, RegistryObject<BlueprintTrappedChestBlock> trapped, ResourceLocation texture) {
        ModelFile model = this.models().getBuilder(getItemName(chest.get())).texture("particle", texture);
        this.simpleBlock(chest.get(), model);
        this.simpleBlock(trapped.get(), model);
        this.itemModels().withExistingParent(getItemName(chest.get()), "blueprint:item/template_chest");
        this.itemModels().withExistingParent(getItemName(trapped.get()), "blueprint:item/template_chest");
    }

    private void leafCarpet(RegistryObject<Block> carpet, ResourceLocation texture) {
        this.simpleBlock(carpet.get(), this.models().withExistingParent(getItemName(carpet.get()), "blueprint:block/leaf_carpet").texture("all", texture).renderType("cutout"));
        this.itemModel(carpet);
    }

    private void post(RegistryObject<Block> post, ResourceLocation texture) {
        ModelFile model = this.models().withExistingParent(getItemName(post.get()), "blueprint:block/post").texture("texture", texture);
        ModelFile chain = this.models().getExistingFile(Blueprint.REGISTRY_HELPER.prefix("block/chain_small"));
        ModelFile chainTop = this.models().getExistingFile(Blueprint.REGISTRY_HELPER.prefix("block/chain_small_top"));

        this.itemModel(post);
        this.getMultipartBuilder(post.get())
                .part().modelFile(model).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.Y).end()
                .part().modelFile(model).rotationX(90).rotationY(90).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.X).end()
                .part().modelFile(model).rotationX(90).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.Z).end()
                .part().modelFile(chain).addModel().condition(BooleanProperty.create("chain_down"), true).end()
                .part().modelFile(chainTop).addModel().condition(BooleanProperty.create("chain_up"), true).end()
                .part().modelFile(chainTop).rotationX(90).addModel().condition(BooleanProperty.create("chain_north"), true).end()
                .part().modelFile(chain).rotationX(90).addModel().condition(BooleanProperty.create("chain_south"), true).end()
                .part().modelFile(chainTop).rotationX(90).rotationY(90).addModel().condition(BooleanProperty.create("chain_east"), true).end()
                .part().modelFile(chain).rotationX(90).rotationY(90).addModel().condition(BooleanProperty.create("chain_west"), true).end();
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

    private void verticalSlab(RegistryObject<Block> slab, ResourceLocation texture) {
        this.verticalSlab(slab, texture, texture);
    }

    private void verticalSlab(RegistryObject<Block> slab, ResourceLocation fullBlock, ResourceLocation texture) {
        ModelFile model = this.models().withExistingParent(getItemName(slab.get()), "blueprint:block/vertical_slab").texture("top", texture).texture("bottom", texture).texture("side", texture);

        this.itemModel(slab);
        this.getVariantBuilder(slab.get())
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.NORTH).addModels(new ConfiguredModel(model, 0, 0, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.SOUTH).addModels(new ConfiguredModel(model, 0, 180, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.EAST).addModels(new ConfiguredModel(model, 0, 90, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.WEST).addModels(new ConfiguredModel(model, 0, 270, true))
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.DOUBLE).addModels(new ConfiguredModel(this.models().getExistingFile(fullBlock)));
    }

    private void verticalPlanks(RegistryObject<Block> planks, ResourceLocation texture) {
        ModelFile model = this.models().withExistingParent(getItemName(planks.get()), "blueprint:block/vertical_planks").texture("all", texture);

        this.itemModel(planks);
        this.simpleBlock(planks.get(), model);
    }

    private void boards(RegistryObject<Block> boards) {
        ResourceLocation texture = this.blockTexture(boards.get());
        ModelFile boardsModel = models().getBuilder(getItemName(boards.get())).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards"))).texture("all", texture);
        ModelFile boardsHorizontalModel = models().getBuilder(getItemName(boards.get()) + "_horizontal").parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards_horizontal"))).texture("all", texture);

        this.itemModel(boards);
        this.getVariantBuilder(boards.get())
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState().modelFile(boardsModel).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState().modelFile(boardsHorizontalModel).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState().modelFile(boardsHorizontalModel).rotationY(270).addModel();
    }

    // Util //

    private static String getItemName(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
    }

    private static String getBlockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private enum TextureFolder {
        Item, Block;

        public String format(String itemName) {
            return this.name().toLowerCase() + '/' + itemName;
        }
    }

}

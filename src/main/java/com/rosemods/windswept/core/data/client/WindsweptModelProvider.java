package com.rosemods.windswept.core.data.client;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.WildBerryBushBlock;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
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

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class WindsweptModelProvider extends BlockStateProvider {

    public WindsweptModelProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        //item models
        this.generatedItem(WindsweptItems.HOLLY_BOAT.getFirst().get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.HOLLY_BOAT.getSecond().get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.CHESTNUT_BOAT.getFirst().get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.CHESTNUT_BOAT.getSecond().get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.HOLLY_BERRIES.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WOODEN_BUCKET.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WOODEN_MILK_BUCKET.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WOODEN_WATER_BUCKET.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WILD_BERRIES.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WILD_BERRY_COOKIE.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WILD_BERRY_JUICE.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WILD_BERRY_BOWL.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.SWEET_BERRY_BOWL.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.MUTTON_PIE.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.GOAT.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.COOKED_GOAT.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.GOAT_STEW.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.GOAT_SHANKS.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.COOKED_GOAT_SHANKS.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.FOUL_BERRY_BOWL.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.WILD_BERRY_PIPS.get(), TextureFolder.Item);
        this.generatedItemWithOverlay(WindsweptItems.SNOW_BOOTS.get());
        this.generatedItem(WindsweptItems.FROZEN_FLESH.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.SNOW_GOLEM_BANNER_PATTERN.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.SNOW_CHARGE_BANNER_PATTERN.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.ROSE_FLOWER_BANNER_PATTERN.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.MUSIC_DISC_RAIN.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.MUSIC_DISC_SNOW.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.MUSIC_DISC_BUMBLEBEE.get(), TextureFolder.Item);
        this.spawnEgg(WindsweptItems.CHILLED_SPAWN_EGG);
        this.generatedItem(WindsweptItems.HOLLY_FURNACE_BOAT.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.LARGE_HOLLY_BOAT.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.CHESTNUT_FURNACE_BOAT.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.LARGE_CHESTNUT_BOAT.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.CHESTNUTS.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.ROASTED_CHESTNUTS.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.CHESTNUT_SOUP.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.CHESTNUT_RISOTTO.get(), TextureFolder.Item);
        this.generatedItem(WindsweptItems.CHESTNUT_CHICKEN_PLATTER.get(), TextureFolder.Item);

        //block models
        this.log(WindsweptBlocks.STRIPPED_HOLLY_LOG);
        this.wood(WindsweptBlocks.STRIPPED_HOLLY_WOOD, this.blockTexture(WindsweptBlocks.STRIPPED_HOLLY_LOG.get()));
        this.log(WindsweptBlocks.HOLLY_LOG);
        this.wood(WindsweptBlocks.HOLLY_WOOD, this.blockTexture(WindsweptBlocks.HOLLY_LOG.get()));
        this.cubeAll(WindsweptBlocks.HOLLY_PLANKS);
        this.slab(WindsweptBlocks.HOLLY_SLAB, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.stairs(WindsweptBlocks.HOLLY_STAIRS, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.fence(WindsweptBlocks.HOLLY_FENCE, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.fenceGate(WindsweptBlocks.HOLLY_FENCE_GATE, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.pressurePlate(WindsweptBlocks.HOLLY_PRESSURE_PLATE, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.door(WindsweptBlocks.HOLLY_DOOR);
        this.trapdoor(WindsweptBlocks.HOLLY_TRAPDOOR);
        this.button(WindsweptBlocks.HOLLY_BUTTON, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.signs(WindsweptBlocks.HOLLY_SIGNS, WindsweptBlocks.HOLLY_PLANKS);
        this.leaves(WindsweptBlocks.HOLLY_LEAVES);
        this.pottedPlant(WindsweptBlocks.HOLLY_SAPLING, WindsweptBlocks.POTTED_HOLLY_SAPLING);
        this.beehive(WindsweptBlocks.HOLLY_BEEHIVE);
        this.hedge(WindsweptBlocks.HOLLY_HEDGE, WindsweptBlocks.HOLLY_LEAVES, WindsweptBlocks.HOLLY_LOG);
        this.leafCarpet(WindsweptBlocks.HOLLY_LEAF_CARPET, this.blockTexture(WindsweptBlocks.HOLLY_LEAVES.get()));
        this.leafPile(WindsweptBlocks.HOLLY_LEAF_PILE, this.blockTexture(WindsweptBlocks.HOLLY_LEAVES.get()), false);
        this.ladder(WindsweptBlocks.HOLLY_LADDER);
        this.verticalPlanks(WindsweptBlocks.VERTICAL_HOLLY_PLANKS, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.bookshelf(WindsweptBlocks.HOLLY_BOOKSHELF, WindsweptBlocks.HOLLY_PLANKS);
        this.boards(WindsweptBlocks.HOLLY_BOARDS);
        this.cabinet(WindsweptBlocks.HOLLY_CABINET);
        this.verticalSlab(WindsweptBlocks.HOLLY_VERTICAL_SLAB, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.post(WindsweptBlocks.HOLLY_POST, this.blockTexture(WindsweptBlocks.HOLLY_LOG.get()));
        this.post(WindsweptBlocks.STRIPPED_HOLLY_POST, this.blockTexture(WindsweptBlocks.STRIPPED_HOLLY_LOG.get()));
        this.chests(WindsweptBlocks.HOLLY_CHEST, WindsweptBlocks.HOLLY_TRAPPED_CHEST, this.blockTexture(WindsweptBlocks.HOLLY_PLANKS.get()));
        this.compressedBlock(WindsweptBlocks.HOLLY_BERRY_BASKET);

        this.log(WindsweptBlocks.STRIPPED_CHESTNUT_LOG);
        this.wood(WindsweptBlocks.STRIPPED_CHESTNUT_WOOD, this.blockTexture(WindsweptBlocks.STRIPPED_CHESTNUT_LOG.get()));
        this.log(WindsweptBlocks.CHESTNUT_LOG);
        this.wood(WindsweptBlocks.CHESTNUT_WOOD, this.blockTexture(WindsweptBlocks.CHESTNUT_LOG.get()));
        this.cubeAll(WindsweptBlocks.CHESTNUT_PLANKS);
        this.slab(WindsweptBlocks.CHESTNUT_SLAB, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));
        this.stairs(WindsweptBlocks.CHESTNUT_STAIRS, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));
        this.fence(WindsweptBlocks.CHESTNUT_FENCE, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));
        this.fenceGate(WindsweptBlocks.CHESTNUT_FENCE_GATE, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));
        this.pressurePlate(WindsweptBlocks.CHESTNUT_PRESSURE_PLATE, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));
        this.door(WindsweptBlocks.CHESTNUT_DOOR);
        this.trapdoor(WindsweptBlocks.CHESTNUT_TRAPDOOR);
        this.button(WindsweptBlocks.CHESTNUT_BUTTON, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));
        this.signs(WindsweptBlocks.CHESTNUT_SIGNS, WindsweptBlocks.CHESTNUT_PLANKS);
        this.leaves(WindsweptBlocks.CHESTNUT_LEAVES);
        this.pottedPlant(WindsweptBlocks.CHESTNUT_SAPLING, WindsweptBlocks.POTTED_CHESTNUT_SAPLING);
        this.beehive(WindsweptBlocks.CHESTNUT_BEEHIVE);
        this.hedge(WindsweptBlocks.CHESTNUT_HEDGE, WindsweptBlocks.CHESTNUT_LEAVES, WindsweptBlocks.CHESTNUT_LOG);
        this.leafCarpet(WindsweptBlocks.CHESTNUT_LEAF_CARPET, this.blockTexture(WindsweptBlocks.CHESTNUT_LEAVES.get()));
        this.leafPile(WindsweptBlocks.CHESTNUT_LEAF_PILE, this.blockTexture(WindsweptBlocks.CHESTNUT_LEAVES.get()), true);
        this.ladder(WindsweptBlocks.CHESTNUT_LADDER);
        this.verticalPlanks(WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));
        this.bookshelf(WindsweptBlocks.CHESTNUT_BOOKSHELF, WindsweptBlocks.CHESTNUT_PLANKS);
        this.boards(WindsweptBlocks.CHESTNUT_BOARDS);
        this.cabinet(WindsweptBlocks.CHESTNUT_CABINET);
        this.verticalSlab(WindsweptBlocks.CHESTNUT_VERTICAL_SLAB, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));
        this.post(WindsweptBlocks.CHESTNUT_POST, this.blockTexture(WindsweptBlocks.CHESTNUT_LOG.get()));
        this.post(WindsweptBlocks.STRIPPED_CHESTNUT_POST, this.blockTexture(WindsweptBlocks.STRIPPED_CHESTNUT_LOG.get()));
        this.chests(WindsweptBlocks.CHESTNUT_CHEST, WindsweptBlocks.CHESTNUT_TRAPPED_CHEST, this.blockTexture(WindsweptBlocks.CHESTNUT_PLANKS.get()));

        this.compressedBlock(WindsweptBlocks.CHESTNUT_CRATE);
        this.compressedBlock(WindsweptBlocks.ROASTED_CHESTNUT_CRATE);

        this.stairs(WindsweptBlocks.SNOW_STAIRS, this.mcLoc("block/snow"));
        this.slab(WindsweptBlocks.SNOW_SLAB, this.mcLoc("block/snow_block"), this.mcLoc("block/snow"));
        this.verticalSlab(WindsweptBlocks.SNOW_VERTICAL_SLAB, this.mcLoc("block/snow_block"), this.mcLoc("block/snow"));

        this.cubeAll(WindsweptBlocks.SNOW_BRICKS);
        this.stairs(WindsweptBlocks.SNOW_BRICK_STAIRS, this.blockTexture(WindsweptBlocks.SNOW_BRICKS.get()));
        this.slab(WindsweptBlocks.SNOW_BRICK_SLAB, this.blockTexture(WindsweptBlocks.SNOW_BRICKS.get()));
        this.wall(WindsweptBlocks.SNOW_BRICK_WALL, this.blockTexture(WindsweptBlocks.SNOW_BRICKS.get()));
        this.verticalSlab(WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB, this.blockTexture(WindsweptBlocks.SNOW_BRICKS.get()));

        this.stairs(WindsweptBlocks.PACKED_ICE_STAIRS, this.blockTexture(Blocks.PACKED_ICE));
        this.slab(WindsweptBlocks.PACKED_ICE_SLAB, this.blockTexture(Blocks.PACKED_ICE));
        this.verticalSlab(WindsweptBlocks.PACKED_ICE_VERTICAL_SLAB, this.blockTexture(Blocks.PACKED_ICE));
        this.cubeAll(WindsweptBlocks.PACKED_ICE_BRICKS);
        this.cubeAll(WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS);
        this.stairs(WindsweptBlocks.PACKED_ICE_BRICK_STAIRS, this.blockTexture(WindsweptBlocks.PACKED_ICE_BRICKS.get()));
        this.slab(WindsweptBlocks.PACKED_ICE_BRICK_SLAB, this.blockTexture(WindsweptBlocks.PACKED_ICE_BRICKS.get()));
        this.wall(WindsweptBlocks.PACKED_ICE_BRICK_WALL, this.blockTexture(WindsweptBlocks.PACKED_ICE_BRICKS.get()));
        this.verticalSlab(WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB, this.blockTexture(WindsweptBlocks.PACKED_ICE_BRICKS.get()));

        this.stairs(WindsweptBlocks.BLUE_ICE_STAIRS, this.blockTexture(Blocks.BLUE_ICE));
        this.slab(WindsweptBlocks.BLUE_ICE_SLAB, this.blockTexture(Blocks.BLUE_ICE));
        this.verticalSlab(WindsweptBlocks.BLUE_ICE_VERTICAL_SLAB, this.blockTexture(Blocks.BLUE_ICE));
        this.cubeAll(WindsweptBlocks.BLUE_ICE_BRICKS);
        this.cubeAll(WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS);
        this.stairs(WindsweptBlocks.BLUE_ICE_BRICK_STAIRS, this.blockTexture(WindsweptBlocks.BLUE_ICE_BRICKS.get()));
        this.slab(WindsweptBlocks.BLUE_ICE_BRICK_SLAB, this.blockTexture(WindsweptBlocks.BLUE_ICE_BRICKS.get()));
        this.wall(WindsweptBlocks.BLUE_ICE_BRICK_WALL, this.blockTexture(WindsweptBlocks.BLUE_ICE_BRICKS.get()));
        this.verticalSlab(WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB, this.blockTexture(WindsweptBlocks.BLUE_ICE_BRICKS.get()));

        this.ice(WindsweptBlocks.CUT_ICE);
        this.iceSheet(WindsweptBlocks.ICE_SHEET, this.blockTexture(Blocks.ICE));
        this.iceSheet(WindsweptBlocks.CUT_ICE_SHEET, this.blockTexture(WindsweptBlocks.CUT_ICE.get()));

        this.simpleCross(WindsweptBlocks.SNOWY_SPROUTS);
        this.generatedItem(WindsweptBlocks.SNOWY_SPROUTS.get(), TextureFolder.Item);
        this.pot(WindsweptBlocks.POTTED_SNOWY_SPROUTS, this.modLoc("block/potted_snowy_sprouts"));

        this.tallPlant(WindsweptBlocks.RED_ROSE_BUSH);
        this.tallPlant(WindsweptBlocks.PINK_ROSE_BUSH);
        this.tallPlant(WindsweptBlocks.BLUE_ROSE_BUSH);
        this.tallPlant(WindsweptBlocks.WHITE_ROSE_BUSH);
        this.tallPlant(WindsweptBlocks.YELLOW_ROSE_BUSH);
        this.tallPlant(WindsweptBlocks.WITHER_ROSE_BUSH);

        this.pottedPlant(WindsweptBlocks.RED_ROSE, WindsweptBlocks.POTTED_RED_ROSE);
        this.pottedPlant(WindsweptBlocks.PINK_ROSE, WindsweptBlocks.POTTED_PINK_ROSE);
        this.pottedPlant(WindsweptBlocks.BLUE_ROSE, WindsweptBlocks.POTTED_BLUE_ROSE);
        this.pottedPlant(WindsweptBlocks.WHITE_ROSE, WindsweptBlocks.POTTED_WHITE_ROSE);
        this.pottedPlant(WindsweptBlocks.YELLOW_ROSE, WindsweptBlocks.POTTED_YELLOW_ROSE);
        this.pottedPlant(WindsweptBlocks.FOXGLOVE, WindsweptBlocks.POTTED_FOXGLOVE);
        this.pottedPlant(WindsweptBlocks.NIGHTSHADE, WindsweptBlocks.POTTED_NIGHTSHADE);

        this.blubells(WindsweptBlocks.BLUEBELLS, WindsweptBlocks.POTTED_BLUEBELLS);

        this.wildBerryBush(WindsweptBlocks.WILD_BERRY_BUSH);
        this.compressedBlock(WindsweptBlocks.WILD_BERRY_BASKET);

        this.compressedBlock(WindsweptBlocks.RED_MUSHROOM_BASKET);
        this.compressedBlock(WindsweptBlocks.BROWN_MUSHROOM_BASKET);
        this.compressedBlock(WindsweptBlocks.GLOW_SHROOM_BASKET);

        this.cubeAll(WindsweptBlocks.FROZEN_FLESH_BLOCK);
    }

    // Blocks //

    private void wildBerryBush(RegistryObject<Block> bush) {
        String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(bush.get())).getPath();
        final Function<Integer, ModelFile> model = i -> this.models().cross(name + "_stage" + i, this.modLoc("block/" + name + "_stage" + i)).renderType("cutout");
        this.getVariantBuilder(bush.get())
                .partialState().with(WildBerryBushBlock.AGE, 0).addModels(new ConfiguredModel(model.apply(0), 0, 0, true))
                .partialState().with(WildBerryBushBlock.AGE, 1).addModels(new ConfiguredModel(model.apply(1), 0, 180, true))
                .partialState().with(WildBerryBushBlock.AGE, 2).addModels(new ConfiguredModel(model.apply(2), 0, 90, true));
    }

    private void blubells(RegistryObject<Block> bluebells, RegistryObject<Block> pot) {
        this.pot(pot, this.modLoc("block/potted_" + getItemName(bluebells.get())));
        this.simpleCross(bluebells);
        this.generatedItem(bluebells.get(), TextureFolder.Block);
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

    private void pot(RegistryObject<Block> pot, ResourceLocation texture) {
        ModelFile model = this.models().withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(pot.get())).getPath(), "block/flower_pot_cross").texture("plant", texture).renderType("cutout");
        this.simpleBlock(pot.get(), model);
    }

    private void ice(RegistryObject<Block> block) {
        this.simpleBlock(block.get(), this.models().cubeAll(getItemName(block.get()), this.blockTexture(block.get())).renderType("translucent"));
        this.itemModel(block);
    }

    private void iceSheet(RegistryObject<Block> block, ResourceLocation texture) {
        this.paneBlockWithRenderType((IronBarsBlock) block.get(), texture, texture, "translucent");
        this.itemModels().withExistingParent(getItemName(block.get()), "item/generated").texture("layer0", texture).renderType("translucent");
    }

    private void trapdoor(RegistryObject<Block> trapdoor) {
        this.trapdoorBlockWithRenderType((TrapDoorBlock) trapdoor.get(), this.blockTexture(trapdoor.get()), true, "cutout");
        this.itemModels().withExistingParent(getItemName(trapdoor.get()), this.modLoc("block/" + getItemName(trapdoor.get()) + "_bottom"));
    }

    private void door(RegistryObject<Block> door) {
        String name = "block/" + getItemName(door.get());
        this.doorBlockWithRenderType((DoorBlock) door.get(), getItemName(door.get()).replace("_door", ""), this.modLoc(name + "_bottom"), this.modLoc(name + "_top"), "cutout");
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

    private void log(RegistryObject<Block> log) {
        this.axisBlock((RotatedPillarBlock) log.get(), this.blockTexture(log.get()), this.modLoc("block/" + getItemName(log.get()) + "_top"));
        this.itemModel(log);
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

    // Misc Util //

    private void itemModel(RegistryObject<Block> block) {
        this.itemModels().withExistingParent(getItemName(block.get()), this.blockTexture(block.get()));
    }

    private void generatedItem(ItemLike item, TextureFolder folder) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc(folder.format(name)));
    }

    private static String getItemName(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
    }

    private static String getBlockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private void generatedItemWithOverlay(ItemLike item) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc("item/" + name)).texture("layer1", this.modLoc("item/" + name + "_overlay"));
    }

    private void spawnEgg(RegistryObject<? extends Item> egg) {
        this.itemModels().withExistingParent(getItemName(egg.get()), "item/template_spawn_egg");
    }

    private enum TextureFolder {
        Item, Block;

        public String format(String itemName) {
            return this.name().toLowerCase() + '/' + itemName;
        }
    }

}

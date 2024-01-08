package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.*;
import com.rosemods.windswept.common.block.grower.ChestnutTreeGrower;
import com.rosemods.windswept.common.block.grower.HollyTreeGrower;
import com.rosemods.windswept.common.block.grower.PineTreeGrower;
import com.rosemods.windswept.common.item.WearableBlockItem;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.WindsweptSoundTypes;
import com.rosemods.windswept.integration.farmers_delight.WindsweptFDCompat;
import com.teamabnormals.blueprint.common.block.*;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.common.block.wood.*;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptBlocks {
    public static final BlockSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getBlockSubHelper();

    // Holly //
    public static final RegistryObject<Block> HOLLY_LOG = HELPER.createBlock("holly_log", () -> new LogBlock(WindsweptBlocks.STRIPPED_HOLLY_LOG, Properties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_WOOD = HELPER.createBlock("holly_wood", () -> new WoodBlock(WindsweptBlocks.STRIPPED_HOLLY_WOOD, Properties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HOLLY_LOG = HELPER.createBlock("stripped_holly_log", () -> new StrippedLogBlock(Properties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HOLLY_WOOD = HELPER.createBlock("stripped_holly_wood", () -> new StrippedWoodBlock(Properties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_PLANKS = HELPER.createBlock("holly_planks", () -> new PlanksBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_STAIRS = HELPER.createBlock("holly_stairs", () -> new WoodStairBlock(HOLLY_PLANKS.get().defaultBlockState(), Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_SLAB = HELPER.createBlock("holly_slab", () -> new WoodSlabBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_VERTICAL_SLAB = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_vertical_slab", () -> new VerticalSlabBlock(Properties.HOLLY.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_FENCE = HELPER.createFuelBlock("holly_fence", () -> new WoodFenceBlock(Properties.HOLLY.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_FENCE_GATE = HELPER.createFuelBlock("holly_fence_gate", () -> new WoodFenceGateBlock(Properties.HOLLY.planks()), 300, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_PRESSURE_PLATE = HELPER.createBlock("holly_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.HOLLY.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_BUTTON = HELPER.createBlock("holly_button", () -> new BlueprintWoodButtonBlock(Properties.HOLLY.button()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_DOOR = HELPER.createBlock("holly_door", () -> new WoodDoorBlock(Properties.HOLLY.door()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_TRAPDOOR = HELPER.createBlock("holly_trapdoor", () -> new WoodTrapDoorBlock(Properties.HOLLY.trapdoor()), CreativeModeTab.TAB_REDSTONE);
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> HOLLY_SIGNS = HELPER.createSignBlock("holly", MaterialColor.COLOR_PURPLE);
    public static final RegistryObject<Block> HOLLY_SAPLING = HELPER.createBlock("holly_sapling", () -> new HollySaplingBlock(new HollyTreeGrower(), PropertyUtil.sapling()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_HOLLY_SAPLING = HELPER.createBlockNoItem("potted_holly_sapling", () -> new FlowerPotBlock(HOLLY_SAPLING.get(), PropertyUtil.flowerPot()));

    public static final RegistryObject<Block> VERTICAL_HOLLY_PLANKS = HELPER.createCompatBlock(WindsweptConstants.QUARK, "vertical_holly_planks", () -> new Block(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_BEEHIVE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_beehive", () -> new BlueprintBeehiveBlock(Properties.HOLLY.beehive()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LADDER = HELPER.createFuelBlock("holly_ladder", () -> new BlueprintLadderBlock(Properties.HOLLY.ladder()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> HOLLY_BOOKSHELF = HELPER.createFuelBlock("holly_bookshelf", () -> new BookshelfBlock(Properties.HOLLY.bookshelf()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_BUILDING_BLOCKS : null);
    public static final RegistryObject<Block> HOLLY_BOARDS = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_boards", () -> new RotatedPillarBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_CABINET = HELPER.createCompatFuelBlock(WindsweptConstants.FARMERSDELIGHT, "holly_cabinet", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_post", () -> new WoodPostBlock(WindsweptBlocks.STRIPPED_HOLLY_POST, Properties.HOLLY.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> STRIPPED_HOLLY_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "stripped_holly_post", () -> new WoodPostBlock(Properties.HOLLY.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<BlueprintChestBlock> HOLLY_CHEST = HELPER.createChestBlock("holly", Properties.HOLLY.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<BlueprintTrappedChestBlock> HOLLY_TRAPPED_CHEST = HELPER.createTrappedChestBlock("holly", Properties.HOLLY.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_REDSTONE : null);

    public static final RegistryObject<Block> HOLLY_LEAVES = HELPER.createBlock("holly_leaves", () -> new HollyLeavesBlock(Properties.HOLLY.leaves()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_HEDGE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_hedge", () -> new HollyHedgeBlock(Properties.HOLLY.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LEAF_CARPET = HELPER.createCompatBlock(WindsweptConstants.QUARK, "holly_leaf_carpet", () -> new LeafCarpetBlock(Properties.HOLLY.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LEAF_PILE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_leaf_pile", () -> new LeafPileBlock(Properties.HOLLY.leafPile()), CreativeModeTab.TAB_DECORATIONS);

    // Chestnut //
    public static final RegistryObject<Block> CHESTNUT_LOG = HELPER.createBlock("chestnut_log", () -> new LogBlock(WindsweptBlocks.STRIPPED_CHESTNUT_LOG, Properties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_WOOD = HELPER.createBlock("chestnut_wood", () -> new WoodBlock(WindsweptBlocks.STRIPPED_CHESTNUT_WOOD, Properties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_LOG = HELPER.createBlock("stripped_chestnut_log", () -> new StrippedLogBlock(Properties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_WOOD = HELPER.createBlock("stripped_chestnut_wood", () -> new StrippedWoodBlock(Properties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_PLANKS = HELPER.createBlock("chestnut_planks", () -> new PlanksBlock(Properties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_STAIRS = HELPER.createBlock("chestnut_stairs", () -> new WoodStairBlock(CHESTNUT_PLANKS.get().defaultBlockState(), Properties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_SLAB = HELPER.createBlock("chestnut_slab", () -> new WoodSlabBlock(Properties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_VERTICAL_SLAB = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_vertical_slab", () -> new VerticalSlabBlock(Properties.CHESTNUT.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_FENCE = HELPER.createFuelBlock("chestnut_fence", () -> new WoodFenceBlock(Properties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_FENCE_GATE = HELPER.createFuelBlock("chestnut_fence_gate", () -> new WoodFenceGateBlock(Properties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_PRESSURE_PLATE = HELPER.createBlock("chestnut_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.CHESTNUT.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_BUTTON = HELPER.createBlock("chestnut_button", () -> new BlueprintWoodButtonBlock(Properties.CHESTNUT.button()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_DOOR = HELPER.createBlock("chestnut_door", () -> new WoodDoorBlock(Properties.CHESTNUT.door()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_TRAPDOOR = HELPER.createBlock("chestnut_trapdoor", () -> new WoodTrapDoorBlock(Properties.CHESTNUT.trapdoor()), CreativeModeTab.TAB_REDSTONE);
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> CHESTNUT_SIGNS = HELPER.createSignBlock("chestnut", MaterialColor.COLOR_BROWN);
    public static final RegistryObject<Block> CHESTNUT_SAPLING = HELPER.createBlock("chestnut_sapling", () -> new BlueprintSaplingBlock(new ChestnutTreeGrower(), PropertyUtil.sapling()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_CHESTNUT_SAPLING = HELPER.createBlockNoItem("potted_chestnut_sapling", () -> new FlowerPotBlock(CHESTNUT_SAPLING.get(), PropertyUtil.flowerPot()));

    public static final RegistryObject<Block> VERTICAL_CHESTNUT_PLANKS = HELPER.createCompatBlock(WindsweptConstants.QUARK, "vertical_chestnut_planks", () -> new Block(Properties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_BEEHIVE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_beehive", () -> new BlueprintBeehiveBlock(Properties.CHESTNUT.beehive()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LADDER = HELPER.createFuelBlock("chestnut_ladder", () -> new BlueprintLadderBlock(Properties.CHESTNUT.ladder()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> CHESTNUT_BOOKSHELF = HELPER.createFuelBlock("chestnut_bookshelf", () -> new BookshelfBlock(Properties.CHESTNUT.bookshelf()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_BUILDING_BLOCKS : null);
    public static final RegistryObject<Block> CHESTNUT_BOARDS = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_boards", () -> new RotatedPillarBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_CABINET = HELPER.createCompatFuelBlock(WindsweptConstants.FARMERSDELIGHT, "chestnut_cabinet", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_post", () -> new WoodPostBlock(WindsweptBlocks.STRIPPED_CHESTNUT_POST, Properties.CHESTNUT.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "stripped_chestnut_post", () -> new WoodPostBlock(Properties.CHESTNUT.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<BlueprintChestBlock> CHESTNUT_CHEST = HELPER.createChestBlock("chestnut", Properties.CHESTNUT.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<BlueprintTrappedChestBlock> CHESTNUT_TRAPPED_CHEST = HELPER.createTrappedChestBlock("chestnut", Properties.CHESTNUT.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_REDSTONE : null);

    public static final RegistryObject<Block> CHESTNUT_LEAVES = HELPER.createBlock("chestnut_leaves", () -> new BlueprintLeavesBlock(Properties.CHESTNUT.leaves()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_HEDGE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_hedge", () -> new HedgeBlock(Properties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LEAF_CARPET = HELPER.createCompatBlock(WindsweptConstants.QUARK, "chestnut_leaf_carpet", () -> new LeafCarpetBlock(Properties.CHESTNUT.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LEAF_PILE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_leaf_pile", () -> new LeafPileBlock(Properties.CHESTNUT.leafPile()), CreativeModeTab.TAB_DECORATIONS);

    // Pine //
    public static final RegistryObject<Block> PINE_LOG = HELPER.createBlock("pine_log", () -> new LogBlock(WindsweptBlocks.WEATHERED_PINE_LOG, Properties.PINE.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINE_WOOD = HELPER.createBlock("pine_wood", () -> new WoodBlock(WindsweptBlocks.WEATHERED_PINE_WOOD, Properties.PINE.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_PINE_LOG = HELPER.createBlock("weathered_pine_log", () -> new LogBlock(WindsweptBlocks.STRIPPED_PINE_LOG, Properties.PINE.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> WEATHERED_PINE_WOOD = HELPER.createBlock("weathered_pine_wood", () -> new WoodBlock(WindsweptBlocks.STRIPPED_PINE_WOOD, Properties.PINE.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PINE_LOG = HELPER.createBlock("stripped_pine_log", () -> new StrippedLogBlock(Properties.PINE.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PINE_WOOD = HELPER.createBlock("stripped_pine_wood", () -> new StrippedWoodBlock(Properties.PINE.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINE_PLANKS = HELPER.createBlock("pine_planks", () -> new PlanksBlock(Properties.PINE.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINE_STAIRS = HELPER.createBlock("pine_stairs", () -> new WoodStairBlock(PINE_PLANKS.get().defaultBlockState(), Properties.PINE.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINE_SLAB = HELPER.createBlock("pine_slab", () -> new WoodSlabBlock(Properties.PINE.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINE_VERTICAL_SLAB = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "pine_vertical_slab", () -> new VerticalSlabBlock(Properties.PINE.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINE_FENCE = HELPER.createFuelBlock("pine_fence", () -> new WoodFenceBlock(Properties.PINE.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINE_FENCE_GATE = HELPER.createFuelBlock("pine_fence_gate", () -> new WoodFenceGateBlock(Properties.PINE.planks()), 300, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> PINE_PRESSURE_PLATE = HELPER.createBlock("pine_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.PINE.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> PINE_BUTTON = HELPER.createBlock("pine_button", () -> new BlueprintWoodButtonBlock(Properties.PINE.button()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> PINE_DOOR = HELPER.createBlock("pine_door", () -> new WoodDoorBlock(Properties.PINE.door()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> PINE_TRAPDOOR = HELPER.createBlock("pine_trapdoor", () -> new WoodTrapDoorBlock(Properties.PINE.trapdoor()), CreativeModeTab.TAB_REDSTONE);
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> PINE_SIGNS = HELPER.createSignBlock("pine", MaterialColor.COLOR_BROWN);
    public static final RegistryObject<Block> PINE_SAPLING = HELPER.createBlock("pine_sapling", () -> new BlueprintSaplingBlock(new PineTreeGrower(), PropertyUtil.sapling()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_PINE_SAPLING = HELPER.createBlockNoItem("potted_pine_sapling", () -> new FlowerPotBlock(PINE_SAPLING.get(), PropertyUtil.flowerPot()));

    public static final RegistryObject<Block> VERTICAL_PINE_PLANKS = HELPER.createCompatBlock(WindsweptConstants.QUARK, "vertical_pine_planks", () -> new Block(Properties.PINE.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINE_BEEHIVE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "pine_beehive", () -> new BlueprintBeehiveBlock(Properties.PINE.beehive()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINE_LADDER = HELPER.createFuelBlock("pine_ladder", () -> new BlueprintLadderBlock(Properties.PINE.ladder()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> PINE_BOOKSHELF = HELPER.createFuelBlock("pine_bookshelf", () -> new BookshelfBlock(Properties.PINE.bookshelf()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_BUILDING_BLOCKS : null);
    public static final RegistryObject<Block> PINE_BOARDS = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "pine_boards", () -> new RotatedPillarBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINE_CABINET = HELPER.createCompatFuelBlock(WindsweptConstants.FARMERSDELIGHT, "pine_cabinet", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINE_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "pine_post", () -> new WoodPostBlock(WindsweptBlocks.WEATHERED_PINE_POST, Properties.PINE.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WEATHERED_PINE_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "weathered_pine_post", () -> new WoodPostBlock(WindsweptBlocks.STRIPPED_PINE_POST, Properties.PINE.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> STRIPPED_PINE_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "stripped_pine_post", () -> new WoodPostBlock(Properties.PINE.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<BlueprintChestBlock> PINE_CHEST = HELPER.createChestBlock("pine", Properties.PINE.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<BlueprintTrappedChestBlock> PINE_TRAPPED_CHEST = HELPER.createTrappedChestBlock("pine", Properties.PINE.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_REDSTONE : null);

    public static final RegistryObject<Block> PINE_LEAVES = HELPER.createBlock("pine_leaves", () -> new BlueprintLeavesBlock(Properties.PINE.leaves()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINE_HEDGE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "pine_hedge", () -> new HedgeBlock(Properties.PINE.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINE_LEAF_CARPET = HELPER.createCompatBlock(WindsweptConstants.QUARK, "pine_leaf_carpet", () -> new LeafCarpetBlock(Properties.PINE.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINE_LEAF_PILE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "pine_leaf_pile", () -> new LeafPileBlock(Properties.PINE.leafPile()), CreativeModeTab.TAB_DECORATIONS);

    // Moss //
    public static final RegistryObject<Block> MOSSY_SPROUTS = HELPER.createInjectedBlock("mossy_sprouts", Items.SEA_PICKLE, () -> new SproutsBlock(Properties.SPROUTS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DRY_MOSSY_SPROUTS = HELPER.createInjectedBlock("dry_mossy_sprouts", Items.SEA_PICKLE, () -> new SproutsBlock(Properties.SPROUTS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DRY_MOSS_CARPET = HELPER.createInjectedBlock("dry_moss_carpet", Items.MOSS_BLOCK, () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DRY_MOSS_BLOCK = HELPER.createInjectedBlock("dry_moss_block", Items.MOSS_BLOCK, () -> new DryMossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)), CreativeModeTab.TAB_DECORATIONS);

    // Gelisol Blocks //
    public static final RegistryObject<Block> GELISOL_SPROUTS = HELPER.createInjectedBlock("gelisol_sprouts", Items.SEA_PICKLE, () -> new SproutsBlock(Properties.SPROUTS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> GELISOL = HELPER.createInjectedBlock("gelisol", Items.PODZOL, () -> new GelisolBlock(BlockBehaviour.Properties.copy(Blocks.PODZOL)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GELISOL_PATH = HELPER.createInjectedBlock("gelisol_path", Items.DIRT_PATH, () -> new DirtPathBlock(BlockBehaviour.Properties.copy(Blocks.DIRT_PATH)), CreativeModeTab.TAB_DECORATIONS);

    // Plants //
    public static final RegistryObject<Block> RED_ROSE = HELPER.createBlock("red_rose", () -> new RoseFlowerBlock(WindsweptBlocks.RED_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_ROSE = HELPER.createBlock("blue_rose", () -> new RoseFlowerBlock(WindsweptBlocks.BLUE_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WHITE_ROSE = HELPER.createBlock("white_rose", () -> new RoseFlowerBlock(WindsweptBlocks.WHITE_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> YELLOW_ROSE = HELPER.createBlock("yellow_rose", () -> new RoseFlowerBlock(WindsweptBlocks.YELLOW_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FOXGLOVE = HELPER.createBlock("foxglove", () -> new BlueprintFlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUEBELLS = HELPER.createBlock("bluebells", () -> new BlueprintFlowerBlock(() -> MobEffects.SLOW_FALLING, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SNOWY_SPROUTS = HELPER.createInjectedBlock("snowy_sprouts", Items.SEA_PICKLE, () -> new SproutsBlock(Properties.SPROUTS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SNOWDROP = HELPER.createBlock("snowdrop", () -> new SnowdropBlock(WindsweptEffects.FROST_RESISTANCE, 5, PropertyUtil.flower().sound(SoundType.NETHER_SPROUTS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> MOSS_CAMPION = HELPER.createBlock("moss_campion", () -> new MossCampionBlock(WindsweptEffects.THORNS, 5, PropertyUtil.flower().sound(SoundType.AZALEA)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> GINGER_FLOWER = HELPER.createBlock("ginger_flower", () -> new BlueprintFlowerBlock(WindsweptEffects.PLENTY, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> NIGHTSHADE = HELPER.createBlock("nightshade", () -> new NightshadeFlowerBlock(() -> MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(state -> 9)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> LAVENDER = HELPER.createBlock("lavender", () -> new LavenderBlock(PropertyUtil.flower().sound(SoundType.AZALEA)), CreativeModeTab.TAB_DECORATIONS);

    // Tall Flowers//
    public static final RegistryObject<Block> LUPINE = HELPER.createBlock("lupine", () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> RED_ROSE_BUSH = HELPER.createInjectedBlock("red_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = HELPER.createInjectedBlock("blue_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WHITE_ROSE_BUSH = HELPER.createInjectedBlock("white_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> YELLOW_ROSE_BUSH = HELPER.createInjectedBlock("yellow_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WITHER_ROSE_BUSH = HELPER.createInjectedBlock("wither_rose_bush", Items.ROSE_BUSH, () -> new WitherRoseBushBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);

    // Decoration Blocks //
    public static final RegistryObject<Block> HOLLY_WREATH = HELPER.createBlockWithItem("holly_wreath", () -> new WallDecorationBlock(Block.Properties.of(Material.DECORATION).instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.HOLLY_WREATH.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Block> PINECONE_WREATH = HELPER.createBlockWithItem("pinecone_wreath", () -> new WallDecorationBlock(Block.Properties.of(Material.DECORATION).instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.PINECONE_WREATH.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Block> VINE_WREATH = HELPER.createBlockWithItem("vine_wreath", () -> new WallDecorationBlock(Block.Properties.of(Material.DECORATION).instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.VINE_WREATH.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Block> CHRISTMAS_PUDDING = HELPER.createInjectedBlock("christmas_pudding", Items.CAKE, () -> new ChristmasPuddingBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)), CreativeModeTab.TAB_FOOD);
    public static final RegistryObject<Block> FROSTBITER_TROPHY = HELPER.createBlock("frostbiter_trophy", () -> new WallDecorationBlock(Properties.HOLLY.ladder().sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ICE_SHEET = HELPER.createBlock("ice_sheet", () -> new IceSheetBlock(BlockBehaviour.Properties.of(Material.ICE).strength(.3f).sound(SoundType.GLASS).noOcclusion().friction(.98f)), CreativeModeTab.TAB_DECORATIONS);

    // Pinecone Blocks //
    public static final RegistryObject<Block> PINECONE = HELPER.createBlock("pinecone", () -> new PineconeBlock(Block.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).strength(.25f).noOcclusion().sound(WindsweptSoundTypes.PINECONE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FAIRY_LIGHT = HELPER.createBlock("fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.copy(PINECONE.get()).lightLevel(s -> 14)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SOUL_FAIRY_LIGHT = HELPER.createBlock("soul_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.copy(PINECONE.get()).lightLevel(s -> 10)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CUPRIC_FAIRY_LIGHT = HELPER.createCompatBlock(WindsweptConstants.CAVERNS_AND_CHASMS, "cupric_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.copy(PINECONE.get()).lightLevel(s -> 10)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ENDER_FAIRY_LIGHT = HELPER.createCompatBlock(WindsweptConstants.ENDERGETIC, "ender_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.copy(PINECONE.get()).lightLevel(s -> 14)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> FEATHER_WING = HELPER.createBlock("feather_wing", () -> new FeatherWingBlock(Block.Properties.of(Material.DECORATION, MaterialColor.COLOR_BROWN).strength(.1f).noOcclusion().noCollission().sound(WindsweptSoundTypes.PINECONE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FEATHER_ORNAMENT = HELPER.createBlock("feather_ornament", () -> new FeatherOrnamentBlock(Properties.FEATHER_ORNAMENT), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DREAM_CATCHER = HELPER.createBlock("dream_catcher", () -> new DreamCatcherBlock(Properties.FEATHER_ORNAMENT), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> PINECONE_BLOCK = HELPER.createBlock("pinecone_block", () -> new PineconeBlockBlock(Properties.PINECONE_BLOCK), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CARVED_PINECONE_BLOCK = HELPER.createBlockWithItem("carved_pinecone_block", () -> new CarvedPineconeBlock(BlockBehaviour.Properties.copy(PINECONE_BLOCK.get()).randomTicks()), () -> new WearableBlockItem(WindsweptBlocks.CARVED_PINECONE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Block> WILL_O_THE_WISP = HELPER.createBlock("will_o_the_wisp", () -> new WillOTheWispBlock(BlockBehaviour.Properties.copy(PINECONE_BLOCK.get()).lightLevel(s -> 10)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> PINECONE_SHINGLES = HELPER.createBlock("pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINECONE_SHINGLE_STAIRS = HELPER.createBlock("pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get()::defaultBlockState, Properties.PINECONE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINECONE_SHINGLE_SLAB = HELPER.createBlock("pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PINECONE_SHINGLE_WALL = HELPER.createBlock("pinecone_shingle_wall", () -> new WallBlock(Properties.PINECONE_BLOCK), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINECONE_SHINGLE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "pinecone_shingle_vertical_slab", () -> new VerticalSlabBlock(Properties.PINECONE_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Icicle Blocks //
    public static final RegistryObject<Block> ICICLES = HELPER.createInjectedBlock("icicles", Items.HANGING_ROOTS, () -> new IciclesBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ICICLE_BLOCK = HELPER.createBlock("icicle_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE).strength(2f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_ICICLE_BLOCK = HELPER.createBlock("chiseled_icicle_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE).strength(2f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ICICLE_DOOR = HELPER.createBlock("icicle_door", () -> new DoorBlock(Block.Properties.of(Material.ICE, MaterialColor.ICE).strength(3f).sound(SoundType.GLASS).noOcclusion()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> ICICLE_TRAPDOOR = HELPER.createBlock("icicle_trapdoor", () -> new TrapDoorBlock(Block.Properties.of(Material.ICE, MaterialColor.ICE).strength(3f).sound(SoundType.GLASS).noOcclusion().isValidSpawn(PropertyUtil::never)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> ICICLE_BARS = HELPER.createBlock("icicle_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE).strength(2f)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ICE_LANTERN = HELPER.createBlock("ice_lantern", () -> new IceLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel(s -> 13)), CreativeModeTab.TAB_DECORATIONS);

    // Shale //
    public static final RegistryObject<Block> SHALE = HELPER.createInjectedBlock("shale", Items.POLISHED_ANDESITE, () -> new Block(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SHALE_STAIRS = HELPER.createInjectedBlock("shale_stairs", Items.POLISHED_ANDESITE_STAIRS, () -> new StairBlock(SHALE.get()::defaultBlockState, Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SHALE_SLAB = HELPER.createInjectedBlock("shale_slab", Items.POLISHED_ANDESITE_SLAB, () -> new SlabBlock(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SHALE_WALL = HELPER.createInjectedBlock("shale_wall", Items.ANDESITE_WALL, () -> new WallBlock(Properties.SHALE), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SHALE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "shale_vertical_slab", () -> new VerticalSlabBlock(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> POLISHED_SHALE = HELPER.createInjectedBlock("polished_shale", Items.POLISHED_ANDESITE, () -> new Block(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_SHALE_STAIRS = HELPER.createInjectedBlock("polished_shale_stairs", Items.POLISHED_ANDESITE_STAIRS, () -> new StairBlock(POLISHED_SHALE.get()::defaultBlockState, Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_SHALE_SLAB = HELPER.createInjectedBlock("polished_shale_slab", Items.POLISHED_ANDESITE_SLAB, () -> new SlabBlock(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_SHALE_WALL = HELPER.createInjectedBlock("polished_shale_wall", Items.ANDESITE_WALL, () -> new WallBlock(Properties.SHALE), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POLISHED_SHALE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "polished_shale_vertical_slab", () -> new VerticalSlabBlock(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> POLISHED_SHALE_BRICKS = HELPER.createBlock("polished_shale_bricks", () -> new Block(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ICY_POLISHED_SHALE_BRICKS = HELPER.createBlock("icy_polished_shale_bricks", () -> new Block(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_POLISHED_SHALE_BRICKS = HELPER.createBlock("chiseled_polished_shale_bricks", () -> new Block(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_SHALE_BRICK_STAIRS = HELPER.createBlock("polished_shale_brick_stairs", () -> new StairBlock(POLISHED_SHALE_BRICKS.get()::defaultBlockState, Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_SHALE_BRICK_SLAB = HELPER.createBlock("polished_shale_brick_slab", () -> new SlabBlock(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_SHALE_BRICK_WALL = HELPER.createBlock("polished_shale_brick_wall", () -> new WallBlock(Properties.SHALE), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POLISHED_SHALE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "polished_shale_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.SHALE), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> POLISHED_SHALE_PRESSURE_PLATE = HELPER.createInjectedBlock("polished_shale_pressure_plate", Items.STONE_PRESSURE_PLATE, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLUE).requiresCorrectToolForDrops().noCollission().strength(.5f).sound(SoundType.DRIPSTONE_BLOCK)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> POLISHED_SHALE_BUTTON = HELPER.createInjectedBlock("polished_shale_button", Items.STONE_BUTTON, () -> new StoneButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(.5f).sound(SoundType.DRIPSTONE_BLOCK)), CreativeModeTab.TAB_REDSTONE);

    // Deepslate //
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_PRESSURE_PLATE = HELPER.createInjectedBlock("polished_deepslate_pressure_plate", Items.STONE_PRESSURE_PLATE, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().noCollission().strength(.5f).sound(SoundType.POLISHED_DEEPSLATE)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_BUTTON = HELPER.createInjectedBlock("polished_deepslate_button", Items.STONE_BUTTON, () -> new StoneButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(.5f).sound(SoundType.POLISHED_DEEPSLATE)), CreativeModeTab.TAB_REDSTONE);

    // Packed Ice Blocks //
    public static final RegistryObject<Block> PACKED_ICE_STAIRS = HELPER.createBlock("packed_ice_stairs", () -> new StairBlock(Blocks.PACKED_ICE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PACKED_ICE_SLAB = HELPER.createBlock("packed_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PACKED_ICE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "packed_ice_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> PACKED_ICE_BRICKS = HELPER.createBlock("packed_ice_bricks", () -> new Block(Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_PACKED_ICE_BRICKS = HELPER.createBlock("chiseled_packed_ice_bricks", () -> new Block(Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PACKED_ICE_BRICK_STAIRS = HELPER.createBlock("packed_ice_brick_stairs", () -> new StairBlock(PACKED_ICE_BRICKS.get()::defaultBlockState, Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PACKED_ICE_BRICK_SLAB = HELPER.createBlock("packed_ice_brick_slab", () -> new SlabBlock(Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PACKED_ICE_BRICK_WALL = HELPER.createBlock("packed_ice_brick_wall", () -> new WallBlock(Properties.ICE_BRICKS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PACKED_ICE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "packed_ice_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Blue Ice Blocks //
    public static final RegistryObject<Block> BLUE_ICE_STAIRS = HELPER.createBlock("blue_ice_stairs", () -> new StairBlock(Blocks.BLUE_ICE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.BLUE_ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLUE_ICE_SLAB = HELPER.createBlock("blue_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLUE_ICE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "blue_ice_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BLUE_ICE_BRICKS = HELPER.createBlock("blue_ice_bricks", () -> new Block(Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_BLUE_ICE_BRICKS = HELPER.createBlock("chiseled_blue_ice_bricks", () -> new Block(Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLUE_ICE_BRICK_STAIRS = HELPER.createBlock("blue_ice_brick_stairs", () -> new StairBlock(BLUE_ICE_BRICKS.get()::defaultBlockState, Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLUE_ICE_BRICK_SLAB = HELPER.createBlock("blue_ice_brick_slab", () -> new SlabBlock(Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLUE_ICE_BRICK_WALL = HELPER.createBlock("blue_ice_brick_wall", () -> new WallBlock(Properties.ICE_BRICKS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_ICE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "blue_ice_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Snow Blocks //
    public static final RegistryObject<Block> SNOW_STAIRS = HELPER.createBlock("snow_stairs", () -> new StairBlock(Blocks.SNOW_BLOCK::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_SLAB = HELPER.createBlock("snow_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "snow_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> SNOW_BRICKS = HELPER.createBlock("snow_bricks", () -> new Block(Properties.SNOW_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_STAIRS = HELPER.createBlock("snow_brick_stairs", () -> new StairBlock(SNOW_BRICKS.get()::defaultBlockState, Properties.SNOW_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_SLAB = HELPER.createBlock("snow_brick_slab", () -> new SlabBlock(Properties.SNOW_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_WALL = HELPER.createBlock("snow_brick_wall", () -> new WallBlock(Properties.SNOW_BRICKS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SNOW_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "snow_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.SNOW_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Candy Cane Blocks //
    public static final RegistryObject<Block> CANDY_CANE_BLOCK = HELPER.createBlock("candy_cane_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_PILLAR)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Ginger Blocks //
    public static final RegistryObject<Block> GINGER_SOIL = HELPER.createBlock("ginger_soil", () -> new GingerSoilBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(.5f).sound(SoundType.GRAVEL)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> GINGERBREAD_BLOCK = HELPER.createBlock("gingerbread_block", () -> new Block(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GLAZED_GINGERBREAD_BLOCK = HELPER.createBlock("glazed_gingerbread_block", () -> new Block(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GINGERBREAD_COOKIE_BLOCK = HELPER.createBlock("gingerbread_cookie_block", () -> new Block(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> GINGERBREAD_BRICKS = HELPER.createBlock("gingerbread_bricks", () -> new Block(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GINGERBREAD_BRICK_STAIRS = HELPER.createBlock("gingerbread_brick_stairs", () -> new StairBlock(GINGERBREAD_BRICKS.get()::defaultBlockState, Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GINGERBREAD_BRICK_SLAB = HELPER.createBlock("gingerbread_brick_slab", () -> new SlabBlock(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GINGERBREAD_BRICK_WALL = HELPER.createBlock("gingerbread_brick_wall", () -> new WallBlock(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GINGERBREAD_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "gingerbread_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> GLAZED_GINGERBREAD_BRICKS = HELPER.createBlock("glazed_gingerbread_bricks", () -> new Block(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GLAZED_GINGERBREAD_BRICK_STAIRS = HELPER.createBlock("glazed_gingerbread_brick_stairs", () -> new StairBlock(GLAZED_GINGERBREAD_BRICKS.get()::defaultBlockState, Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GLAZED_GINGERBREAD_BRICK_SLAB = HELPER.createBlock("glazed_gingerbread_brick_slab", () -> new SlabBlock(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GLAZED_GINGERBREAD_BRICK_WALL = HELPER.createBlock("glazed_gingerbread_brick_wall", () -> new WallBlock(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GLAZED_GINGERBREAD_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "glazed_gingerbread_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.GINGERBREAD), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Dolomite //
    public static final RegistryObject<Block> DOLOMITE = HELPER.createBlock("dolomite", () -> new DolomiteBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DOLOMITE_STAIRS = HELPER.createBlock("dolomite_stairs", () -> new StairBlock(DOLOMITE.get()::defaultBlockState, Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DOLOMITE_SLAB = HELPER.createBlock("dolomite_slab", () -> new DolomiteSlabBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DOLOMITE_WALL = HELPER.createBlock("dolomite_wall", () -> new WallBlock(Properties.DOLOMITE), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DOLOMITE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "dolomite_vertical_slab", () -> new VerticalSlabBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CUT_DOLOMITE = HELPER.createBlock("cut_dolomite", () -> new Block(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CUT_DOLOMITE_STAIRS = HELPER.createBlock("cut_dolomite_stairs", () -> new StairBlock(CUT_DOLOMITE.get()::defaultBlockState, Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CUT_DOLOMITE_SLAB = HELPER.createBlock("cut_dolomite_slab", () -> new SlabBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CUT_DOLOMITE_WALL = HELPER.createBlock("cut_dolomite_wall", () -> new WallBlock(Properties.DOLOMITE), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CUT_DOLOMITE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "cut_dolomite_vertical_slab", () -> new VerticalSlabBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CUT_DOLOMITE_BRICKS = HELPER.createBlock("cut_dolomite_bricks", () -> new Block(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_CUT_DOLOMITE_BRICKS = HELPER.createBlock("chiseled_cut_dolomite_bricks", () -> new Block(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CUT_DOLOMITE_BRICK_STAIRS = HELPER.createBlock("cut_dolomite_brick_stairs", () -> new StairBlock(CUT_DOLOMITE_BRICKS.get()::defaultBlockState, Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CUT_DOLOMITE_BRICK_SLAB = HELPER.createBlock("cut_dolomite_brick_slab", () -> new SlabBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CUT_DOLOMITE_BRICK_WALL = HELPER.createBlock("cut_dolomite_brick_wall", () -> new WallBlock(Properties.DOLOMITE), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CUT_DOLOMITE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "cut_dolomite_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> SMOOTH_DOLOMITE = HELPER.createBlock("smooth_dolomite", () -> new Block(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_DOLOMITE_STAIRS = HELPER.createBlock("smooth_dolomite_stairs", () -> new StairBlock(SMOOTH_DOLOMITE.get()::defaultBlockState, Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_DOLOMITE_SLAB = HELPER.createBlock("smooth_dolomite_slab", () -> new SlabBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_DOLOMITE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "smooth_dolomite_vertical_slab", () -> new VerticalSlabBlock(Properties.DOLOMITE), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Dry Mossy Blocks //
    public static final RegistryObject<Block> DRY_MOSSY_COBBLESTONE = HELPER.createInjectedBlock("dry_mossy_cobblestone", Items.MOSSY_COBBLESTONE, () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DRY_MOSSY_COBBLESTONE_STAIRS = HELPER.createInjectedBlock("dry_mossy_cobblestone_stairs", Items.MOSSY_COBBLESTONE_STAIRS, () -> new StairBlock(DRY_MOSSY_COBBLESTONE.get()::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DRY_MOSSY_COBBLESTONE_SLAB = HELPER.createInjectedBlock("dry_mossy_cobblestone_slab", Items.MOSSY_COBBLESTONE_SLAB, () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DRY_MOSSY_COBBLESTONE_WALL = HELPER.createInjectedBlock("dry_mossy_cobblestone_wall", Items.MOSSY_COBBLESTONE_WALL, () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DRY_MOSSY_COBBLESTONE_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "dry_mossy_cobblestone_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> DRY_MOSSY_STONE_BRICKS = HELPER.createInjectedBlock("dry_mossy_stone_bricks", Items.MOSSY_STONE_BRICKS, () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DRY_MOSSY_STONE_BRICK_STAIRS = HELPER.createInjectedBlock("dry_mossy_stone_brick_stairs", Items.MOSSY_STONE_BRICK_STAIRS, () -> new StairBlock(DRY_MOSSY_STONE_BRICKS.get()::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DRY_MOSSY_STONE_BRICK_SLAB = HELPER.createInjectedBlock("dry_mossy_stone_brick_slab", Items.MOSSY_STONE_BRICK_SLAB, () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DRY_MOSSY_STONE_BRICK_WALL = HELPER.createInjectedBlock("dry_mossy_stone_brick_wall", Items.MOSSY_STONE_BRICK_WALL, () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DRY_MOSSY_STONE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "dry_mossy_stone_brick_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Compressed Blocks //
    public static final RegistryObject<Block> CHESTNUT_CRATE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_crate", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ROASTED_CHESTNUT_CRATE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "roasted_chestnut_crate", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_BERRY_BASKET = HELPER.createFuelBlock("holly_berry_basket", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> WILD_BERRY_BASKET = HELPER.createFuelBlock("wild_berry_basket", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> RED_MUSHROOM_BASKET = HELPER.createFuelBlock("red_mushroom_basket", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> BROWN_MUSHROOM_BASKET = HELPER.createFuelBlock("brown_mushroom_basket", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> GLOW_SHROOM_BASKET = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "glow_shroom_basket", () -> new BlueprintDirectionalBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).sound(SoundType.WOOD).lightLevel(s -> 12)), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FROZEN_FLESH_BLOCK = HELPER.createBlock("frozen_flesh_block", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_BLUE).strength(.8f).sound(SoundType.CORAL_BLOCK)), CreativeModeTab.TAB_DECORATIONS);

    // Pots //
    public static final RegistryObject<Block> POTTED_RED_ROSE = HELPER.createBlockNoItem("potted_red_rose", () -> new FlowerPotBlock(RED_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_BLUE_ROSE = HELPER.createBlockNoItem("potted_blue_rose", () -> new FlowerPotBlock(BLUE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_WHITE_ROSE = HELPER.createBlockNoItem("potted_white_rose", () -> new FlowerPotBlock(WHITE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_YELLOW_ROSE = HELPER.createBlockNoItem("potted_yellow_rose", () -> new FlowerPotBlock(YELLOW_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_FOXGLOVE = HELPER.createBlockNoItem("potted_foxglove", () -> new FlowerPotBlock(FOXGLOVE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_BLUEBELLS = HELPER.createBlockNoItem("potted_bluebells", () -> new FlowerPotBlock(BLUEBELLS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_SNOWDROP = HELPER.createBlockNoItem("potted_snowdrop", () -> new FlowerPotBlock(SNOWDROP.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_MOSS_CAMPION = HELPER.createBlockNoItem("potted_moss_campion", () -> new FlowerPotBlock(MOSS_CAMPION.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_GINGER_FLOWER = HELPER.createBlockNoItem("potted_ginger_flower", () -> new FlowerPotBlock(GINGER_FLOWER.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_NIGHTSHADE = HELPER.createBlockNoItem("potted_nightshade", () -> new NightShadeFlowerPotBlock(NIGHTSHADE.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion().lightLevel(state -> 9)));
    public static final RegistryObject<Block> POTTED_SNOWY_SPROUTS = HELPER.createBlockNoItem("potted_snowy_sprouts", () -> new FlowerPotBlock(SNOWY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_GELISOL_SPROUTS = HELPER.createBlockNoItem("potted_gelisol_sprouts", () -> new FlowerPotBlock(GELISOL_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_DRY_MOSSY_SPROUTS = HELPER.createBlockNoItem("potted_dry_mossy_sprouts", () -> new FlowerPotBlock(DRY_MOSSY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_MOSSY_SPROUTS = HELPER.createBlockNoItem("potted_mossy_sprouts", () -> new FlowerPotBlock(MOSSY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_LAVENDER = HELPER.createBlockNoItem("potted_lavender", () -> new FlowerPotBlock(LAVENDER.get(), PropertyUtil.flowerPot()));

    // Misc //
    public static final RegistryObject<Block> WILD_BERRY_BUSH = HELPER.createBlockNoItem("wild_berry_bush", () -> new WildBerryBushBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).offsetType(BlockBehaviour.OffsetType.XZ)));

    public static class Properties {
        // Wood Sets //
        public static final PropertyUtil.WoodSetProperties HOLLY = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_PURPLE).build();
        public static final PropertyUtil.WoodSetProperties CHESTNUT = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_BROWN).build();
        public static final PropertyUtil.WoodSetProperties PINE = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_BLACK).build();

        // Blocks //
        public static final BlockBehaviour.Properties SNOW_BRICKS = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SNOW).strength(.85f).sound(SoundType.SNOW);
        public static final BlockBehaviour.Properties ICE_BRICKS = BlockBehaviour.Properties.of(Material.ICE_SOLID, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.8f).sound(SoundType.STONE);
        public static final BlockBehaviour.Properties CRATE = BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).sound(SoundType.WOOD);
        public static final BlockBehaviour.Properties SPROUTS = BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.XZ);
        public static final BlockBehaviour.Properties PINECONE_BLOCK = Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2f, 3f).sound(WindsweptSoundTypes.PINECONE);
        public static final BlockBehaviour.Properties SHALE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLUE).sound(SoundType.DRIPSTONE_BLOCK).requiresCorrectToolForDrops().strength(1.5f, 1f);
        public static final BlockBehaviour.Properties DOLOMITE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_BLUE).sound(SoundType.CALCITE).requiresCorrectToolForDrops().strength(.8f);
        public static final BlockBehaviour.Properties GINGERBREAD = Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD);
        public static final BlockBehaviour.Properties FEATHER_ORNAMENT = Block.Properties.of(Material.DECORATION, MaterialColor.COLOR_BROWN).strength(.1f).noOcclusion().noCollission().sound(SoundType.AZALEA);
    }

}

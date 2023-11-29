package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.*;
import com.rosemods.windswept.common.block.holly.HollyHedgeBlock;
import com.rosemods.windswept.common.block.holly.HollyLeavesBlock;
import com.rosemods.windswept.common.block.holly.HollySaplingBlock;
import com.rosemods.windswept.common.block.nightshade.NightShadeFlowerPotBlock;
import com.rosemods.windswept.common.block.nightshade.NightshadeFlowerBlock;
import com.rosemods.windswept.common.world.gen.tree.ChestnutTreeGrower;
import com.rosemods.windswept.common.world.gen.tree.HollyTreeGrower;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
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
    public static final RegistryObject<Block> STRIPPED_HOLLY_LOG = HELPER.createBlock("stripped_holly_log", () -> new StrippedLogBlock(Properties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HOLLY_WOOD = HELPER.createBlock("stripped_holly_wood", () -> new StrippedWoodBlock(Properties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_LOG = HELPER.createBlock("holly_log", () -> new LogBlock(STRIPPED_HOLLY_LOG, Properties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_WOOD = HELPER.createBlock("holly_wood", () -> new WoodBlock(STRIPPED_HOLLY_WOOD, Properties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_PLANKS = HELPER.createBlock("holly_planks", () -> new PlanksBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_SLAB = HELPER.createBlock("holly_slab", () -> new WoodSlabBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_STAIRS = HELPER.createBlock("holly_stairs", () -> new WoodStairBlock(HOLLY_PLANKS.get().defaultBlockState(), Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_FENCE = HELPER.createFuelBlock("holly_fence", () -> new WoodFenceBlock(Properties.HOLLY.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_FENCE_GATE = HELPER.createFuelBlock("holly_fence_gate", () -> new WoodFenceGateBlock(Properties.HOLLY.planks()), 300, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_PRESSURE_PLATE = HELPER.createBlock("holly_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.HOLLY.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_DOOR = HELPER.createBlock("holly_door", () -> new WoodDoorBlock(Properties.HOLLY.door()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_TRAPDOOR = HELPER.createBlock("holly_trapdoor", () -> new WoodTrapDoorBlock(Properties.HOLLY.trapdoor()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_BUTTON = HELPER.createBlock("holly_button", () -> new BlueprintWoodButtonBlock(Properties.HOLLY.button()), CreativeModeTab.TAB_REDSTONE);
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> HOLLY_SIGNS = HELPER.createSignBlock("holly", MaterialColor.COLOR_PURPLE);
    public static final RegistryObject<Block> HOLLY_SAPLING = HELPER.createBlock("holly_sapling", () -> new HollySaplingBlock(new HollyTreeGrower(), PropertyUtil.sapling()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_HOLLY_SAPLING = HELPER.createBlockNoItem("potted_holly_sapling", () -> new FlowerPotBlock(HOLLY_SAPLING.get(), PropertyUtil.flowerPot()));

    public static final RegistryObject<Block> VERTICAL_HOLLY_PLANKS = HELPER.createCompatBlock(WindsweptConstants.QUARK, "vertical_holly_planks", () -> new Block(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_BEEHIVE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_beehive", () -> new BlueprintBeehiveBlock(Properties.HOLLY.beehive()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LADDER = HELPER.createFuelBlock("holly_ladder", () -> new BlueprintLadderBlock(Properties.HOLLY.ladder()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> HOLLY_BOOKSHELF = HELPER.createFuelBlock("holly_bookshelf", () -> new BookshelfBlock(Properties.HOLLY.bookshelf()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_BUILDING_BLOCKS : null);
    public static final RegistryObject<Block> HOLLY_BOARDS = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_boards", () -> new RotatedPillarBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_CABINET = HELPER.createCompatFuelBlock(WindsweptConstants.FARMERSDELIGHT, "holly_cabinet", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_VERTICAL_SLAB = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_vertical_slab", () -> new VerticalSlabBlock(Properties.HOLLY.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HOLLY_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "stripped_holly_post", () -> new WoodPostBlock(Properties.HOLLY.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_post", () -> new WoodPostBlock(STRIPPED_HOLLY_POST, Properties.HOLLY.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<BlueprintChestBlock> HOLLY_CHEST = HELPER.createChestBlock("holly", Properties.HOLLY.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<BlueprintTrappedChestBlock> HOLLY_TRAPPED_CHEST = HELPER.createTrappedChestBlock("holly", Properties.HOLLY.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_REDSTONE : null);
    public static final RegistryObject<Block> HOLLY_LEAVES = HELPER.createBlock("holly_leaves", () -> new HollyLeavesBlock(Properties.HOLLY.leaves()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_HEDGE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_hedge", () -> new HollyHedgeBlock(Properties.HOLLY.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LEAF_CARPET = HELPER.createCompatBlock(WindsweptConstants.QUARK, "holly_leaf_carpet", () -> new LeafCarpetBlock(Properties.HOLLY.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LEAF_PILE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_leaf_pile", () -> new LeafPileBlock(Properties.HOLLY.leafPile()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> HOLLY_WREATH = HELPER.createBlock("holly_wreath", () -> new LadderBlock(Block.Properties.of(Material.LEAVES).instabreak().sound(SoundType.AZALEA).noCollission().noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_BERRY_BASKET = HELPER.createFuelBlock("holly_berry_basket", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);

    // Chestnut //
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_LOG = HELPER.createBlock("stripped_chestnut_log", () -> new StrippedLogBlock(Properties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_WOOD = HELPER.createBlock("stripped_chestnut_wood", () -> new StrippedWoodBlock(Properties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_LOG = HELPER.createBlock("chestnut_log", () -> new LogBlock(STRIPPED_CHESTNUT_LOG, Properties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_WOOD = HELPER.createBlock("chestnut_wood", () -> new WoodBlock(STRIPPED_CHESTNUT_WOOD, Properties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_PLANKS = HELPER.createBlock("chestnut_planks", () -> new PlanksBlock(Properties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_SLAB = HELPER.createBlock("chestnut_slab", () -> new WoodSlabBlock(Properties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_STAIRS = HELPER.createBlock("chestnut_stairs", () -> new WoodStairBlock(CHESTNUT_PLANKS.get().defaultBlockState(), Properties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_FENCE = HELPER.createFuelBlock("chestnut_fence", () -> new WoodFenceBlock(Properties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_FENCE_GATE = HELPER.createFuelBlock("chestnut_fence_gate", () -> new WoodFenceGateBlock(Properties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_PRESSURE_PLATE = HELPER.createBlock("chestnut_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.CHESTNUT.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_DOOR = HELPER.createBlock("chestnut_door", () -> new WoodDoorBlock(Properties.CHESTNUT.door()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_TRAPDOOR = HELPER.createBlock("chestnut_trapdoor", () -> new WoodTrapDoorBlock(Properties.CHESTNUT.trapdoor()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_BUTTON = HELPER.createBlock("chestnut_button", () -> new BlueprintWoodButtonBlock(Properties.CHESTNUT.button()), CreativeModeTab.TAB_REDSTONE);
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> CHESTNUT_SIGNS = HELPER.createSignBlock("chestnut", MaterialColor.COLOR_BROWN);
    public static final RegistryObject<Block> CHESTNUT_SAPLING = HELPER.createBlock("chestnut_sapling", () -> new BlueprintSaplingBlock(new ChestnutTreeGrower(), PropertyUtil.sapling()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_CHESTNUT_SAPLING = HELPER.createBlockNoItem("potted_chestnut_sapling", () -> new FlowerPotBlock(CHESTNUT_SAPLING.get(), PropertyUtil.flowerPot()));

    public static final RegistryObject<Block> VERTICAL_CHESTNUT_PLANKS = HELPER.createCompatBlock(WindsweptConstants.QUARK, "vertical_chestnut_planks", () -> new Block(Properties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_BEEHIVE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_beehive", () -> new BlueprintBeehiveBlock(Properties.CHESTNUT.beehive()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LADDER = HELPER.createFuelBlock("chestnut_ladder", () -> new BlueprintLadderBlock(Properties.CHESTNUT.ladder()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> CHESTNUT_BOOKSHELF = HELPER.createFuelBlock("chestnut_bookshelf", () -> new BookshelfBlock(Properties.CHESTNUT.bookshelf()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_BUILDING_BLOCKS : null);
    public static final RegistryObject<Block> CHESTNUT_BOARDS = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_boards", () -> new RotatedPillarBlock(Properties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_CABINET = HELPER.createCompatFuelBlock(WindsweptConstants.FARMERSDELIGHT, "chestnut_cabinet", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), 300, CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> CHESTNUT_VERTICAL_SLAB = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_vertical_slab", () -> new VerticalSlabBlock(Properties.CHESTNUT.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "stripped_chestnut_post", () -> new WoodPostBlock(Properties.CHESTNUT.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_post", () -> new WoodPostBlock(STRIPPED_CHESTNUT_POST, Properties.CHESTNUT.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<BlueprintChestBlock> CHESTNUT_CHEST = HELPER.createChestBlock("chestnut", Properties.CHESTNUT.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<BlueprintTrappedChestBlock> CHESTNUT_TRAPPED_CHEST = HELPER.createTrappedChestBlock("chestnut", Properties.CHESTNUT.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_REDSTONE : null);

    public static final RegistryObject<Block> CHESTNUT_LEAVES = HELPER.createBlock("chestnut_leaves", () -> new BlueprintLeavesBlock(Properties.CHESTNUT.leaves()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_HEDGE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_hedge", () -> new HedgeBlock(Properties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LEAF_CARPET = HELPER.createCompatBlock(WindsweptConstants.QUARK, "chestnut_leaf_carpet", () -> new LeafCarpetBlock(Properties.CHESTNUT.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LEAF_PILE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_leaf_pile", () -> new LeafPileBlock(Properties.CHESTNUT.leafPile()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> CHESTNUT_CRATE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_crate", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ROASTED_CHESTNUT_CRATE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "roasted_chestnut_crate", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, CreativeModeTab.TAB_DECORATIONS);

    // Snow Blocks //
    public static final RegistryObject<Block> SNOW_STAIRS = HELPER.createBlock("snow_stairs", () -> new StairBlock(Blocks.SNOW_BLOCK::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_SLAB = HELPER.createBlock("snow_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "snow_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> SNOW_BRICKS = HELPER.createBlock("snow_bricks", () -> new Block(Properties.SNOW_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_STAIRS = HELPER.createBlock("snow_brick_stairs", () -> new StairBlock(SNOW_BRICKS.get()::defaultBlockState, Properties.SNOW_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_SLAB = HELPER.createBlock("snow_brick_slab", () -> new SlabBlock(Properties.SNOW_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_WALL = HELPER.createBlock("snow_brick_wall", () -> new WallBlock(Properties.SNOW_BRICKS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SNOW_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "snow_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.SNOW_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

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

    // Ice Blocks //
    public static final RegistryObject<Block> CUT_ICE = HELPER.createBlock("cut_ice", () -> new IceBlock(BlockBehaviour.Properties.copy(Blocks.ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ICE_SHEET = HELPER.createBlock("ice_sheet", () -> new IceSheetBlock(BlockBehaviour.Properties.copy(Blocks.ICE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CUT_ICE_SHEET = HELPER.createBlock("cut_ice_sheet", () -> new IceSheetBlock(BlockBehaviour.Properties.copy(Blocks.ICE)), CreativeModeTab.TAB_DECORATIONS);

    // Sprouts //
    public static final RegistryObject<Block> SNOWY_SPROUTS = HELPER.createInjectedBlock("snowy_sprouts", Items.SEA_PICKLE, () -> new SnowySproutsBlock(Properties.COLD_SPROUTS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> RED_TUNDRA_SPROUTS = HELPER.createInjectedBlock("red_tundra_sprouts", Items.SEA_PICKLE, () -> new SnowySproutsBlock(Properties.COLD_SPROUTS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> YELLOW_TUNDRA_SPROUTS = HELPER.createInjectedBlock("yellow_tundra_sprouts", Items.SEA_PICKLE, () -> new SnowySproutsBlock(Properties.COLD_SPROUTS), CreativeModeTab.TAB_DECORATIONS);

    // Rose Bushes //
    public static final RegistryObject<Block> RED_ROSE_BUSH = HELPER.createInjectedBlock("red_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINK_ROSE_BUSH = HELPER.createInjectedBlock("pink_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = HELPER.createInjectedBlock("blue_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WHITE_ROSE_BUSH = HELPER.createInjectedBlock("white_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> YELLOW_ROSE_BUSH = HELPER.createInjectedBlock("yellow_rose_bush", Items.ROSE_BUSH, () -> new BlueprintTallFlowerBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WITHER_ROSE_BUSH = HELPER.createInjectedBlock("wither_rose_bush", Items.ROSE_BUSH, () -> new WitherRoseBushBlock(PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);

    // Flowers //
    public static final RegistryObject<Block> RED_ROSE = HELPER.createBlock("red_rose", () -> new RoseFlowerBlock(RED_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINK_ROSE = HELPER.createBlock("pink_rose", () -> new RoseFlowerBlock(PINK_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_ROSE = HELPER.createBlock("blue_rose", () -> new RoseFlowerBlock(BLUE_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> WHITE_ROSE = HELPER.createBlock("white_rose", () -> new RoseFlowerBlock(WHITE_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> YELLOW_ROSE = HELPER.createBlock("yellow_rose", () -> new RoseFlowerBlock(YELLOW_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FOXGLOVE = HELPER.createBlock("foxglove", () -> new BlueprintFlowerBlock(() -> MobEffects.MOVEMENT_SPEED, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUEBELLS = HELPER.createBlock("bluebells", () -> new BlueprintFlowerBlock(() -> MobEffects.SLOW_FALLING, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SNOWDROP = HELPER.createBlock("snowdrop", () -> new SnowdropBlock(() -> MobEffects.MOVEMENT_SLOWDOWN, 5, PropertyUtil.flower().sound(SoundType.NETHER_SPROUTS)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> NIGHTSHADE = HELPER.createBlock("nightshade", () -> new NightshadeFlowerBlock(() -> MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(state -> 9)), CreativeModeTab.TAB_DECORATIONS);

    // Pots //
    public static final RegistryObject<Block> POTTED_RED_ROSE = HELPER.createBlockNoItem("potted_red_rose", () -> new FlowerPotBlock(RED_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_PINK_ROSE = HELPER.createBlockNoItem("potted_pink_rose", () -> new FlowerPotBlock(PINK_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_BLUE_ROSE = HELPER.createBlockNoItem("potted_blue_rose", () -> new FlowerPotBlock(BLUE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_WHITE_ROSE = HELPER.createBlockNoItem("potted_white_rose", () -> new FlowerPotBlock(WHITE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_YELLOW_ROSE = HELPER.createBlockNoItem("potted_yellow_rose", () -> new FlowerPotBlock(YELLOW_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_FOXGLOVE = HELPER.createBlockNoItem("potted_foxglove", () -> new FlowerPotBlock(FOXGLOVE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_BLUEBELLS = HELPER.createBlockNoItem("potted_bluebells", () -> new FlowerPotBlock(BLUEBELLS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_SNOWDROP = HELPER.createBlockNoItem("potted_snowdrop", () -> new FlowerPotBlock(SNOWDROP.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_NIGHTSHADE = HELPER.createBlockNoItem("potted_nightshade", () -> new NightShadeFlowerPotBlock(NIGHTSHADE.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion().lightLevel(state -> 9)));
    public static final RegistryObject<Block> POTTED_SNOWY_SPROUTS = HELPER.createBlockNoItem("potted_snowy_sprouts", () -> new FlowerPotBlock(SNOWY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_RED_TUNDRA_SPROUTS = HELPER.createBlockNoItem("potted_red_tundra_sprouts", () -> new FlowerPotBlock(RED_TUNDRA_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_YELLOW_TUNDRA_SPROUTS = HELPER.createBlockNoItem("potted_yellow_tundra_sprouts", () -> new FlowerPotBlock(YELLOW_TUNDRA_SPROUTS.get(), PropertyUtil.flowerPot()));

    // Wild Berries //
    public static final RegistryObject<Block> WILD_BERRY_BUSH = HELPER.createBlockNoItem("wild_berry_bush", () -> new WildBerryBushBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Block> WILD_BERRY_BASKET = HELPER.createFuelBlock("wild_berry_basket", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);

    // Icicle Blocks //
    public static final RegistryObject<Block> ICICLE = HELPER.createInjectedBlock("icicle", Items.HANGING_ROOTS, () -> new IcicleBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE).noCollission()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ICICLE_BLOCK = HELPER.createBlock("icicle_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_ICICLE_BLOCK = HELPER.createBlock("chiseled_icicle_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Tundra Moss Blocks //
    public static final RegistryObject<Block> DRY_MOSS_CARPET = HELPER.createInjectedBlock("dry_moss_carpet", Items.MOSS_BLOCK, () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DRY_MOSS_BLOCK = HELPER.createInjectedBlock("dry_moss_block", Items.MOSS_BLOCK, () -> new MossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)), CreativeModeTab.TAB_DECORATIONS);

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
    
    // Mushroom Baskets //
    public static final RegistryObject<Block> RED_MUSHROOM_BASKET = HELPER.createFuelBlock("red_mushroom_basket", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> BROWN_MUSHROOM_BASKET = HELPER.createFuelBlock("brown_mushroom_basket", () -> new BlueprintDirectionalBlock(Properties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> GLOW_SHROOM_BASKET = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "glow_shroom_basket", () -> new BlueprintDirectionalBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).sound(SoundType.WOOD).lightLevel(s -> 12)), 300, CreativeModeTab.TAB_DECORATIONS);

    // Misc //
    public static final RegistryObject<Block> GELISOL = HELPER.createInjectedBlock("gelisol", Items.PODZOL, () -> new SnowyDirtBlock(BlockBehaviour.Properties.copy(Blocks.PODZOL)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> FROSTBITER_TROPHY = HELPER.createBlock("frostbiter_trophy", () -> new LadderBlock(Properties.HOLLY.ladder().sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FROZEN_FLESH_BLOCK = HELPER.createBlock("frozen_flesh_block", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_BLUE).strength(.8f).sound(SoundType.CORAL_BLOCK)), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.CAVERNS_AND_CHASMS) ? CreativeModeTab.TAB_DECORATIONS : null);

    public static class Properties {
        // Wood Sets //
        public static final PropertyUtil.WoodSetProperties HOLLY = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_PURPLE).build();
        public static final PropertyUtil.WoodSetProperties CHESTNUT = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_BROWN).build();

        // Blocks //
        public static final BlockBehaviour.Properties SNOW_BRICKS = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SNOW).strength(.85f).sound(SoundType.SNOW);
        public static final BlockBehaviour.Properties ICE_BRICKS = BlockBehaviour.Properties.of(Material.ICE_SOLID, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.8f).sound(SoundType.STONE);
        public static final BlockBehaviour.Properties CRATE = BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).sound(SoundType.WOOD);
        public static final BlockBehaviour.Properties COLD_SPROUTS = BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.XZ);

    }

}

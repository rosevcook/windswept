package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.*;
import com.rosemods.windswept.common.block.holly.HollyHedgeBlock;
import com.rosemods.windswept.common.block.holly.HollyLeavesBlock;
import com.rosemods.windswept.common.block.holly.HollySaplingBlock;
import com.rosemods.windswept.common.block.nightshade.NightShadeFlowerPotBlock;
import com.rosemods.windswept.common.block.nightshade.NightshadeFlowerBlock;
import com.rosemods.windswept.common.block.wild_berry.WildBerryBushBlock;
import com.rosemods.windswept.common.block.wild_berry.WildBerryBushPipsBlock;
import com.rosemods.windswept.common.world.gen.tree.ChestnutTreeGrower;
import com.rosemods.windswept.common.world.gen.tree.HollyTreeGrower;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.WindsweptProperties;
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
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Windswept.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptBlocks {
    public static final BlockSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getBlockSubHelper();

    // Holly //

    public static final RegistryObject<Block> STRIPPED_HOLLY_LOG = HELPER.createBlock("stripped_holly_log", () -> new StrippedLogBlock(WindsweptProperties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HOLLY_WOOD = HELPER.createBlock("stripped_holly_wood", () -> new StrippedWoodBlock(WindsweptProperties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_LOG = HELPER.createBlock("holly_log", () -> new LogBlock(STRIPPED_HOLLY_LOG, WindsweptProperties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_WOOD = HELPER.createBlock("holly_wood", () -> new WoodBlock(STRIPPED_HOLLY_WOOD, WindsweptProperties.HOLLY.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_PLANKS = HELPER.createBlock("holly_planks", () -> new PlanksBlock(WindsweptProperties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_SLAB = HELPER.createBlock("holly_slab", () -> new WoodSlabBlock(WindsweptProperties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_STAIRS = HELPER.createBlock("holly_stairs", () -> new WoodStairBlock(HOLLY_PLANKS.get().defaultBlockState(), WindsweptProperties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_FENCE = HELPER.createFuelBlock("holly_fence", () -> new WoodFenceBlock(WindsweptProperties.HOLLY.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_FENCE_GATE = HELPER.createFuelBlock("holly_fence_gate", () -> new WoodFenceGateBlock(WindsweptProperties.HOLLY.planks()), 300, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_PRESSURE_PLATE = HELPER.createBlock("holly_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, WindsweptProperties.HOLLY.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_DOOR = HELPER.createBlock("holly_door", () -> new WoodDoorBlock(WindsweptProperties.HOLLY.door()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_TRAPDOOR = HELPER.createBlock("holly_trapdoor", () -> new WoodTrapDoorBlock(WindsweptProperties.HOLLY.trapdoor()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> HOLLY_BUTTON = HELPER.createBlock("holly_button", () -> new BlueprintWoodButtonBlock(WindsweptProperties.HOLLY.button()), CreativeModeTab.TAB_REDSTONE);
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> HOLLY_SIGNS = HELPER.createSignBlock("holly", MaterialColor.COLOR_PURPLE);
    public static final RegistryObject<Block> HOLLY_SAPLING = HELPER.createBlock("holly_sapling", () -> new HollySaplingBlock(new HollyTreeGrower(), PropertyUtil.sapling()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_HOLLY_SAPLING = HELPER.createBlockNoItem("potted_holly_sapling", () -> new FlowerPotBlock(HOLLY_SAPLING.get(), PropertyUtil.flowerPot()));

    public static final RegistryObject<Block> VERTICAL_HOLLY_PLANKS = HELPER.createCompatBlock(WindsweptConstants.QUARK, "vertical_holly_planks", () -> new Block(WindsweptProperties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_BEEHIVE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_beehive", () -> new BlueprintBeehiveBlock(WindsweptProperties.HOLLY.beehive()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LADDER = HELPER.createFuelBlock("holly_ladder", () -> new BlueprintLadderBlock(WindsweptProperties.HOLLY.ladder()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> HOLLY_BOOKSHELF = HELPER.createFuelBlock("holly_bookshelf", () -> new BookshelfBlock(WindsweptProperties.HOLLY.bookshelf()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_BUILDING_BLOCKS : null);
    public static final RegistryObject<Block> HOLLY_BOARDS = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_boards", () -> new RotatedPillarBlock(WindsweptProperties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> HOLLY_CABINET = HELPER.createCompatFuelBlock(WindsweptConstants.FARMERSDELIGHT, "holly_cabinet", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_VERTICAL_SLAB = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_vertical_slab", () -> new VerticalSlabBlock(WindsweptProperties.HOLLY.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HOLLY_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "stripped_holly_post", () -> new WoodPostBlock(WindsweptProperties.HOLLY.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_post", () -> new WoodPostBlock(STRIPPED_HOLLY_POST, WindsweptProperties.HOLLY.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<BlueprintChestBlock> HOLLY_CHEST = HELPER.createChestBlock("holly", WindsweptProperties.HOLLY.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<BlueprintTrappedChestBlock> HOLLY_TRAPPED_CHEST = HELPER.createTrappedChestBlock("holly", WindsweptProperties.HOLLY.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_REDSTONE : null);
    public static final RegistryObject<Block> HOLLY_LEAVES = HELPER.createBlock("holly_leaves", () -> new HollyLeavesBlock(WindsweptProperties.HOLLY.leaves()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_HEDGE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "holly_hedge", () -> new HollyHedgeBlock(WindsweptProperties.HOLLY.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LEAF_CARPET = HELPER.createCompatBlock(WindsweptConstants.QUARK, "holly_leaf_carpet", () -> new LeafCarpetBlock(WindsweptProperties.HOLLY.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLY_LEAF_PILE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "holly_leaf_pile", () -> new LeafPileBlock(WindsweptProperties.HOLLY.leafPile()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> HOLLY_BERRY_BASKET = HELPER.createFuelBlock("holly_berry_basket", () -> new BlueprintDirectionalBlock(WindsweptProperties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);

    // Chestnut //

    public static final RegistryObject<Block> STRIPPED_CHESTNUT_LOG = HELPER.createBlock("stripped_chestnut_log", () -> new StrippedLogBlock(WindsweptProperties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_WOOD = HELPER.createBlock("stripped_chestnut_wood", () -> new StrippedWoodBlock(WindsweptProperties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_LOG = HELPER.createBlock("chestnut_log", () -> new LogBlock(STRIPPED_CHESTNUT_LOG, WindsweptProperties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_WOOD = HELPER.createBlock("chestnut_wood", () -> new WoodBlock(STRIPPED_CHESTNUT_WOOD, WindsweptProperties.CHESTNUT.log()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_PLANKS = HELPER.createBlock("chestnut_planks", () -> new PlanksBlock(WindsweptProperties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_SLAB = HELPER.createBlock("chestnut_slab", () -> new WoodSlabBlock(WindsweptProperties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_STAIRS = HELPER.createBlock("chestnut_stairs", () -> new WoodStairBlock(CHESTNUT_PLANKS.get().defaultBlockState(), WindsweptProperties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_FENCE = HELPER.createFuelBlock("chestnut_fence", () -> new WoodFenceBlock(WindsweptProperties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_FENCE_GATE = HELPER.createFuelBlock("chestnut_fence_gate", () -> new WoodFenceGateBlock(WindsweptProperties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_PRESSURE_PLATE = HELPER.createBlock("chestnut_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, WindsweptProperties.CHESTNUT.pressurePlate()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_DOOR = HELPER.createBlock("chestnut_door", () -> new WoodDoorBlock(WindsweptProperties.CHESTNUT.door()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_TRAPDOOR = HELPER.createBlock("chestnut_trapdoor", () -> new WoodTrapDoorBlock(WindsweptProperties.CHESTNUT.trapdoor()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_BUTTON = HELPER.createBlock("chestnut_button", () -> new BlueprintWoodButtonBlock(WindsweptProperties.CHESTNUT.button()), CreativeModeTab.TAB_REDSTONE);
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> CHESTNUT_SIGNS = HELPER.createSignBlock("chestnut", MaterialColor.COLOR_BROWN);
    public static final RegistryObject<Block> CHESTNUT_SAPLING = HELPER.createBlock("chestnut_sapling", () -> new BlueprintSaplingBlock(new ChestnutTreeGrower(), PropertyUtil.sapling()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_CHESTNUT_SAPLING = HELPER.createBlockNoItem("potted_chestnut_sapling", () -> new FlowerPotBlock(CHESTNUT_SAPLING.get(), PropertyUtil.flowerPot()));

    public static final RegistryObject<Block> VERTICAL_CHESTNUT_PLANKS = HELPER.createCompatBlock(WindsweptConstants.QUARK, "vertical_chestnut_planks", () -> new Block(WindsweptProperties.CHESTNUT.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_BEEHIVE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_beehive", () -> new BlueprintBeehiveBlock(WindsweptProperties.CHESTNUT.beehive()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LADDER = HELPER.createFuelBlock("chestnut_ladder", () -> new BlueprintLadderBlock(WindsweptProperties.CHESTNUT.ladder()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> CHESTNUT_BOOKSHELF = HELPER.createFuelBlock("chestnut_bookshelf", () -> new BookshelfBlock(WindsweptProperties.CHESTNUT.bookshelf()), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_BUILDING_BLOCKS : null);
    public static final RegistryObject<Block> CHESTNUT_BOARDS = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_boards", () -> new RotatedPillarBlock(WindsweptProperties.HOLLY.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_CABINET = HELPER.createCompatFuelBlock(WindsweptConstants.FARMERSDELIGHT, "chestnut_cabinet", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), 300, CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> CHESTNUT_VERTICAL_SLAB = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_vertical_slab", () -> new VerticalSlabBlock(WindsweptProperties.CHESTNUT.planks()), 150, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "stripped_chestnut_post", () -> new WoodPostBlock(WindsweptProperties.CHESTNUT.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_POST = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_post", () -> new WoodPostBlock(STRIPPED_CHESTNUT_POST, WindsweptProperties.CHESTNUT.post()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<BlueprintChestBlock> CHESTNUT_CHEST = HELPER.createChestBlock("chestnut", WindsweptProperties.CHESTNUT.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<BlueprintTrappedChestBlock> CHESTNUT_TRAPPED_CHEST = HELPER.createTrappedChestBlock("chestnut", WindsweptProperties.CHESTNUT.chest(), ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.WOODWORKS) ? CreativeModeTab.TAB_REDSTONE : null);

    public static final RegistryObject<Block> CHESTNUT_LEAVES = HELPER.createBlock("chestnut_leaves", () -> new BlueprintLeavesBlock(WindsweptProperties.CHESTNUT.leaves()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_HEDGE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_hedge", () -> new HedgeBlock(WindsweptProperties.CHESTNUT.planks()), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LEAF_CARPET = HELPER.createCompatBlock(WindsweptConstants.QUARK, "chestnut_leaf_carpet", () -> new LeafCarpetBlock(WindsweptProperties.CHESTNUT.leafCarpet()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CHESTNUT_LEAF_PILE = HELPER.createCompatBlock(WindsweptConstants.WOODWORKS, "chestnut_leaf_pile", () -> new LeafPileBlock(WindsweptProperties.CHESTNUT.leafPile()), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> CHESTNUT_CRATE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "chestnut_crate", () -> new BlueprintDirectionalBlock(WindsweptProperties.CRATE), 300, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ROASTED_CHESTNUT_CRATE = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "roasted_chestnut_crate", () -> new BlueprintDirectionalBlock(WindsweptProperties.CRATE), 300, CreativeModeTab.TAB_DECORATIONS);

    // Snow Bricks //

    public static final RegistryObject<Block> SNOW_BRICKS = HELPER.createBlock("snow_bricks", () -> new Block(WindsweptProperties.PACKED_SNOW), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_STAIRS = HELPER.createBlock("snow_brick_stairs", () -> new StairBlock(SNOW_BRICKS.get()::defaultBlockState, WindsweptProperties.PACKED_SNOW), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_SLAB = HELPER.createBlock("snow_brick_slab", () -> new SlabBlock(WindsweptProperties.PACKED_SNOW), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SNOW_BRICK_WALL = HELPER.createBlock("snow_brick_wall", () -> new WallBlock(WindsweptProperties.PACKED_SNOW), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SNOW_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "snow_brick_vertical_slab", () -> new VerticalSlabBlock(WindsweptProperties.PACKED_SNOW), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Packed Ice Bricks //

    public static final RegistryObject<Block> PACKED_ICE_BRICKS = HELPER.createBlock("packed_ice_bricks", () -> new Block(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_PACKED_ICE_BRICKS = HELPER.createBlock("chiseled_packed_ice_bricks", () -> new Block(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PACKED_ICE_BRICK_STAIRS = HELPER.createBlock("packed_ice_brick_stairs", () -> new StairBlock(PACKED_ICE_BRICKS.get()::defaultBlockState, WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PACKED_ICE_BRICK_SLAB = HELPER.createBlock("packed_ice_brick_slab", () -> new SlabBlock(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> PACKED_ICE_BRICK_WALL = HELPER.createBlock("packed_ice_brick_wall", () -> new WallBlock(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PACKED_ICE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "packed_ice_brick_vertical_slab", () -> new VerticalSlabBlock(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Blue Ice Bricks //

    public static final RegistryObject<Block> BLUE_ICE_BRICKS = HELPER.createBlock("blue_ice_bricks", () -> new Block(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_BLUE_ICE_BRICKS = HELPER.createBlock("chiseled_blue_ice_bricks", () -> new Block(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLUE_ICE_BRICK_STAIRS = HELPER.createBlock("blue_ice_brick_stairs", () -> new StairBlock(BLUE_ICE_BRICKS.get()::defaultBlockState, WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLUE_ICE_BRICK_SLAB = HELPER.createBlock("blue_ice_brick_slab", () -> new SlabBlock(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLUE_ICE_BRICK_WALL = HELPER.createBlock("blue_ice_brick_wall", () -> new WallBlock(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_ICE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock(WindsweptConstants.QUARK, "blue_ice_brick_vertical_slab", () -> new VerticalSlabBlock(WindsweptProperties.ICE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Sprouts //

    public static final RegistryObject<Block> SNOWY_SPROUTS = HELPER.createInjectedBlock("snowy_sprouts", Items.GRASS, () -> new SnowySproutsBlock(WindsweptProperties.SNOWY_SPROUTS), CreativeModeTab.TAB_DECORATIONS);

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
    public static final RegistryObject<Block> BLUEBELLS = HELPER.createBlock("bluebells", () -> new BluebellBlock(() -> MobEffects.SLOW_FALLING, 5, PropertyUtil.flower()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> NIGHTSHADE = HELPER.createBlock("nightshade", () -> new NightshadeFlowerBlock(() -> MobEffects.NIGHT_VISION, 5, WindsweptProperties.NIGHTSHADE), CreativeModeTab.TAB_DECORATIONS);

    // Pots //

    public static final RegistryObject<Block> POTTED_RED_ROSE = HELPER.createBlockNoItem("potted_red_rose", () -> new FlowerPotBlock(RED_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_PINK_ROSE = HELPER.createBlockNoItem("potted_pink_rose", () -> new FlowerPotBlock(PINK_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_BLUE_ROSE = HELPER.createBlockNoItem("potted_blue_rose", () -> new FlowerPotBlock(BLUE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_WHITE_ROSE = HELPER.createBlockNoItem("potted_white_rose", () -> new FlowerPotBlock(WHITE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_YELLOW_ROSE = HELPER.createBlockNoItem("potted_yellow_rose", () -> new FlowerPotBlock(YELLOW_ROSE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_FOXGLOVE = HELPER.createBlockNoItem("potted_foxglove", () -> new FlowerPotBlock(FOXGLOVE.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_BLUEBELLS = HELPER.createBlockNoItem("potted_bluebells", () -> new FlowerPotBlock(BLUEBELLS.get(), PropertyUtil.flowerPot()));
    public static final RegistryObject<Block> POTTED_NIGHTSHADE = HELPER.createBlockNoItem("potted_nightshade", () -> new NightShadeFlowerPotBlock(NIGHTSHADE.get(), WindsweptProperties.NIGHTSHADE_POT));
    public static final RegistryObject<Block> POTTED_SNOWY_SPROUTS = HELPER.createBlockNoItem("potted_snowy_sprouts", () -> new FlowerPotBlock(SNOWY_SPROUTS.get(), PropertyUtil.flowerPot()));

    // Wild Berries //

    public static final RegistryObject<Block> WILD_BERRY_BUSH = HELPER.createBlockNoItem("wild_berry_bush", () -> new WildBerryBushBlock(WindsweptProperties.WILD_BERRY_BUSH));
    public static final RegistryObject<Block> WILD_BERRY_BASKET = HELPER.createFuelBlock("wild_berry_basket", () -> new BlueprintDirectionalBlock(WindsweptProperties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> WILD_BERRY_BUSH_PIPS = HELPER.createBlockNoItem("wild_berry_bush_pips", () -> new WildBerryBushPipsBlock(WindsweptProperties.WILD_BERRY_BUSH));

    // Mushroom Baskets //

    public static final RegistryObject<Block> RED_MUSHROOM_BASKET = HELPER.createFuelBlock("red_mushroom_basket", () -> new BlueprintDirectionalBlock(WindsweptProperties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> BROWN_MUSHROOM_BASKET = HELPER.createFuelBlock("brown_mushroom_basket", () -> new BlueprintDirectionalBlock(WindsweptProperties.CRATE), 300, ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.QUARK) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.FARMERSDELIGHT) || ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD)  ? CreativeModeTab.TAB_DECORATIONS : null);
    public static final RegistryObject<Block> GLOW_SHROOM_BASKET = HELPER.createCompatFuelBlock(WindsweptConstants.QUARK, "glow_shroom_basket", () -> new BlueprintDirectionalBlock(WindsweptProperties.GLOW_SHROOM_BASKET), 300, CreativeModeTab.TAB_DECORATIONS);

    // Misc //

    public static final RegistryObject<Block> ICE_SHEET = HELPER.createBlock("ice_sheet", () -> new IceSheetBlock(BlockBehaviour.Properties.copy(Blocks.ICE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FROZEN_FLESH_BLOCK = HELPER.createCompatBlock("frozen_flesh_block", () -> new Block(WindsweptProperties.FROZEN_FLESH_BLOCK), CreativeModeTab.TAB_DECORATIONS, WindsweptConstants.CAVERNS_AND_CHASMS);

}

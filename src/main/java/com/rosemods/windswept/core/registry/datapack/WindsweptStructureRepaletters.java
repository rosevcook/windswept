package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.blueprint.core.registry.BlueprintHolderSets;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.datapack.WindsweptStructures.*;
import static net.minecraft.world.level.levelgen.structure.BuiltinStructures.*;

public final class WindsweptStructureRepaletters {

    public static void bootstrap(BootstapContext<StructureRepaletterEntry> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        //igloo
        register(context, structures, Blocks.SNOW_BLOCK, SNOW_BRICKS.get(), IGLOO);
        register(context, structures, Blocks.OAK_WALL_SIGN, HOLLY_SIGNS.getSecond().get(), IGLOO);
        register(context, structures, Blocks.POTTED_CACTUS, POTTED_WHITE_ROSE.get(), IGLOO);
        register(context, structures, Blocks.SPRUCE_SLAB, HOLLY_SLAB.get(), IGLOO);
        register(context, structures, Blocks.SPRUCE_STAIRS, HOLLY_STAIRS.get(), IGLOO);
        register(context, structures, Blocks.MOSSY_STONE_BRICKS, CHISELED_ICICLE_BLOCK.get(), IGLOO);
        register(context, structures, Blocks.INFESTED_MOSSY_STONE_BRICKS, CHISELED_ICICLE_BLOCK.get(), IGLOO);
        register(context, structures, Blocks.OAK_TRAPDOOR, HOLLY_TRAPDOOR.get(), IGLOO);
        register(context, structures, Blocks.POLISHED_ANDESITE, Blocks.GOLD_BLOCK, IGLOO);
        register(context, structures, Blocks.IRON_BARS, ICICLE_BARS.get(), IGLOO);
        register(context, structures, Blocks.STONE_BRICKS, PACKED_ICE_BRICKS.get(), IGLOO);
        register(context, structures, Blocks.INFESTED_STONE_BRICKS, PACKED_ICE_BRICKS.get(), IGLOO);
        register(context, structures, Blocks.CHISELED_STONE_BRICKS, CHISELED_PACKED_ICE_BRICKS.get(), IGLOO);
        register(context, structures, Blocks.INFESTED_CHISELED_STONE_BRICKS, CHISELED_PACKED_ICE_BRICKS.get(), IGLOO);
        register(context, structures, Blocks.CRACKED_STONE_BRICKS, Blocks.PACKED_ICE, IGLOO);
        register(context, structures, Blocks.INFESTED_CRACKED_STONE_BRICKS, Blocks.PACKED_ICE, IGLOO);
        register(context, structures, Blocks.REDSTONE_TORCH, ICE_LANTERN.get(), IGLOO);

        //villages
        register(context, structures, Blocks.POTTED_SPRUCE_SAPLING, POTTED_BLUEBELLS.get(), VILLAGE_TAIGA);
        register(context, structures, Blocks.POTTED_POPPY, POTTED_RED_ROSE.get(), VILLAGE_TAIGA);
        register(context, structures, Blocks.POPPY, RED_ROSE.get(), VILLAGE_TAIGA);
        register(context, structures, Blocks.LIGHT_GRAY_WOOL, SNOW_BRICKS.get(), VILLAGE_TAIGA);
        register(context, structures, Blocks.BLUE_ICE, PACKED_ICE_BRICKS.get(), VILLAGE_TAIGA);
        register(context, structures, Blocks.ACACIA_SAPLING, MIMOSA.get(), VILLAGE_SAVANNA);
        register(context, structures, Blocks.POPPY, YELLOW_PETALS.get(), VILLAGE_SAVANNA);
        register(context, structures, Blocks.ACACIA_PRESSURE_PLATE, POTTED_MIMOSA.get(), VILLAGE_SAVANNA);

        //chestnut in snowy village
        register(context, structures, Blocks.SPRUCE_PLANKS, CHESTNUT_PLANKS.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.SPRUCE_STAIRS, CHESTNUT_STAIRS.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.SPRUCE_SLAB, CHESTNUT_SLAB.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.STRIPPED_SPRUCE_LOG, STRIPPED_CHESTNUT_LOG.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.STRIPPED_SPRUCE_WOOD, STRIPPED_CHESTNUT_WOOD.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.SPRUCE_FENCE, CHESTNUT_FENCE.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.SPRUCE_FENCE_GATE, CHESTNUT_FENCE_GATE.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.SPRUCE_DOOR, CHESTNUT_DOOR.get(), VILLAGE_SNOWY);

        //shipwrecks
        register(context, structures, Blocks.JUNGLE_DOOR, PINE_DOOR.get(), SHIPWRECK, SHIPWRECK_BEACHED);
        register(context, structures, Blocks.JUNGLE_FENCE, PINE_FENCE.get(), SHIPWRECK, SHIPWRECK_BEACHED);
        register(context, structures, Blocks.JUNGLE_LOG, PINE_LOG.get(), SHIPWRECK, SHIPWRECK_BEACHED);
        register(context, structures, Blocks.JUNGLE_PLANKS, PINE_PLANKS.get(), SHIPWRECK, SHIPWRECK_BEACHED);
        register(context, structures, Blocks.JUNGLE_SLAB, PINE_SLAB.get(), SHIPWRECK, SHIPWRECK_BEACHED);
        register(context, structures, Blocks.JUNGLE_STAIRS, PINE_STAIRS.get(), SHIPWRECK, SHIPWRECK_BEACHED);
        register(context, structures, Blocks.JUNGLE_TRAPDOOR, PINE_TRAPDOOR.get(), SHIPWRECK, SHIPWRECK_BEACHED);

        //mod compat
        ICondition woodworks = new ModLoadedCondition("woodworks");
        ICondition farmersDelight = new ModLoadedCondition("farmersdelight");

        //register(context, structures, Blocks.BOOKSHELF, HOLLY_BOOKSHELF.get(), woodworks, GROVE_WEATHERED_HOUSE);
        //register(context, structures, Blocks.CHEST, HOLLY_CHEST.get(), woodworks, GROVE_WEATHERED_HOUSE);
        //register(context, structures, Blocks.LADDER, HOLLY_LADDER.get(), woodworks, GROVE_WEATHERED_HOUSE);
        //register(context, structures, Blocks.LADDER, CHESTNUT_LADDER.get(), woodworks, CHESTNUT_WEATHERED_HOUSE);
        //register(context, structures, Blocks.BOOKSHELF, HOLLY_BOOKSHELF.get(), woodworks, VILLAGE_FROZEN);
        //register(context, structures, Blocks.CHEST, HOLLY_CHEST.get(), woodworks, VILLAGE_FROZEN);
        //register(context, structures, Blocks.LADDER, HOLLY_LADDER.get(), woodworks, VILLAGE_FROZEN);
        //register(context, structures, Blocks.WHEAT, ModBlocks.CABBAGE_CROP.get(), farmersDelight, VILLAGE_FROZEN);
        register(context, structures, Blocks.CHEST, HOLLY_CHEST.get(), woodworks, IGLOO);
        register(context, structures, Blocks.LADDER, HOLLY_LADDER.get(), woodworks, IGLOO);
        register(context, structures, Blocks.CHEST, CHESTNUT_CHEST.get(), woodworks, VILLAGE_SNOWY);
        register(context, structures, Blocks.BOOKSHELF, CHESTNUT_BOOKSHELF.get(), woodworks, VILLAGE_SNOWY);
        register(context, structures, Blocks.LADDER, CHESTNUT_LADDER.get(), woodworks, VILLAGE_SNOWY);

    }

    @SafeVarargs
    private static void register(BootstapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, Block replacesBlock, Block replacesWith, ICondition condition, ResourceKey<Structure>... selector) {
        register(context, getName(replacesBlock, replacesWith, selector), replacesBlock, replacesWith,
                BlueprintHolderSets.conditional(HolderSet.direct(Stream.of(selector).map(structures::getOrThrow).collect(Collectors.toList())), condition));
    }

    @SafeVarargs
    private static void register(BootstapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, Block replacesBlock, Block replacesWith, ResourceKey<Structure>... selector) {
        register(context, getName(replacesBlock, replacesWith, selector), replacesBlock, replacesWith,
                HolderSet.direct(Stream.of(selector).map(structures::getOrThrow).collect(Collectors.toList())));
    }

    private static void register(BootstapContext<StructureRepaletterEntry> context, String name, Block replacesBlock, Block replacesWith, HolderSet<Structure> structures) {
        context.register(createKey(name), new StructureRepaletterEntry(structures, Optional.empty(), false, new SimpleStructureRepaletter(replacesBlock, replacesWith)));
    }

    @SafeVarargs
    private static String getName(Block replacesBlock, Block replacesWith, ResourceKey<Structure>... selector) {
        return selector[0].location().getPath() + "/" + ForgeRegistries.BLOCKS.getKey(replacesWith).getPath() + "_replaces_" + ForgeRegistries.BLOCKS.getKey(replacesBlock).getPath();
    }

    private static ResourceKey<StructureRepaletterEntry> createKey(String name) {
        return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, Windswept.location(name));
    }

}

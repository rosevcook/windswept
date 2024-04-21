package com.rosemods.windswept.core.data.server;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterProvider;
import com.teamabnormals.blueprint.core.util.modification.selection.ConditionedResourceSelector;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.registries.ForgeRegistries;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptStructures.*;
import static net.minecraft.world.level.levelgen.structure.BuiltinStructures.*;

public class WindsweptStructureRepaletterProvider extends StructureRepaletterProvider {

    public WindsweptStructureRepaletterProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID);
    }

    @Override
    protected void registerRepaletters() {
        // Igloo //
        this.register(IGLOO, Blocks.SNOW_BLOCK, SNOW_BRICKS.get());
        this.register(IGLOO, Blocks.OAK_WALL_SIGN, HOLLY_SIGNS.getSecond().get());
        this.register(IGLOO, Blocks.POTTED_CACTUS, POTTED_WHITE_ROSE.get());
        this.register(IGLOO, Blocks.SPRUCE_SLAB, HOLLY_SLAB.get());
        this.register(IGLOO, Blocks.SPRUCE_STAIRS, HOLLY_STAIRS.get());
        this.register(IGLOO, Blocks.MOSSY_STONE_BRICKS, HOLLY_PLANKS.get());
        this.register(IGLOO, Blocks.INFESTED_MOSSY_STONE_BRICKS, HOLLY_PLANKS.get());
        this.register(IGLOO, Blocks.OAK_TRAPDOOR, HOLLY_TRAPDOOR.get());
        this.register(IGLOO, Blocks.POLISHED_ANDESITE, Blocks.GOLD_BLOCK);

        // Villages //
        this.register(VILLAGE_TAIGA, Blocks.POTTED_SPRUCE_SAPLING, POTTED_BLUEBELLS.get());
        this.register(VILLAGE_TAIGA, Blocks.POTTED_POPPY, POTTED_RED_ROSE.get());
        this.register(VILLAGE_TAIGA, Blocks.POPPY, RED_ROSE.get());
        this.register(VILLAGE_SNOWY, Blocks.LIGHT_GRAY_WOOL, SNOW_BRICKS.get());
        this.register(VILLAGE_SNOWY, Blocks.BLUE_ICE, PACKED_ICE_BRICKS.get());

        // Ancient City //
        this.register(ANCIENT_CITY, Blocks.STONE_PRESSURE_PLATE, POLISHED_DEEPSLATE_PRESSURE_PLATE.get());
        this.register(ANCIENT_CITY, Blocks.BLUE_ICE, PACKED_ICE_BRICKS.get());

        // Chestnut in Snowy Village //
        this.register(VILLAGE_SNOWY, Blocks.SPRUCE_PLANKS, CHESTNUT_PLANKS.get());
        this.register(VILLAGE_SNOWY, Blocks.SPRUCE_STAIRS, CHESTNUT_STAIRS.get());
        this.register(VILLAGE_SNOWY, Blocks.SPRUCE_SLAB, CHESTNUT_SLAB.get());
        this.register(VILLAGE_SNOWY, Blocks.STRIPPED_SPRUCE_LOG, STRIPPED_CHESTNUT_LOG.get());
        this.register(VILLAGE_SNOWY, Blocks.STRIPPED_SPRUCE_WOOD, STRIPPED_CHESTNUT_WOOD.get());
        this.register(VILLAGE_SNOWY, Blocks.SPRUCE_FENCE, CHESTNUT_FENCE.get());
        this.register(VILLAGE_SNOWY, Blocks.SPRUCE_FENCE_GATE, CHESTNUT_FENCE_GATE.get());
        this.register(VILLAGE_SNOWY, Blocks.SPRUCE_DOOR, CHESTNUT_DOOR.get());

        // Mod Compat //
        ICondition quarkOrWoodworks = new OrCondition(new ModLoadedCondition("quark"), new ModLoadedCondition("woodworks"));

        this.register(GROVE_WEATHERED_HOUSE, Blocks.BOOKSHELF, HOLLY_BOOKSHELF.get(), quarkOrWoodworks);
        this.register(GROVE_WEATHERED_HOUSE, Blocks.CHEST, HOLLY_CHEST.get(), quarkOrWoodworks);
        this.register(GROVE_WEATHERED_HOUSE, Blocks.LADDER, HOLLY_LADDER.get(), quarkOrWoodworks);
        this.register(CHESTNUT_WEATHERED_HOUSE, Blocks.LADDER, CHESTNUT_LADDER.get(), quarkOrWoodworks);
        this.register(VILLAGE_FROZEN, Blocks.BOOKSHELF, HOLLY_BOOKSHELF.get(), quarkOrWoodworks);
        this.register(VILLAGE_FROZEN, Blocks.CHEST, HOLLY_CHEST.get(), quarkOrWoodworks);
        this.register(VILLAGE_FROZEN, Blocks.LADDER, HOLLY_LADDER.get(), quarkOrWoodworks);
        this.register(IGLOO, Blocks.CHEST, HOLLY_CHEST.get(), quarkOrWoodworks);
        this.register(IGLOO, Blocks.LADDER, HOLLY_LADDER.get(), quarkOrWoodworks);
        this.register(VILLAGE_SNOWY, Blocks.CHEST, CHESTNUT_CHEST.get(), quarkOrWoodworks);
        this.register(VILLAGE_SNOWY, Blocks.BOOKSHELF, CHESTNUT_BOOKSHELF.get(), quarkOrWoodworks);
        this.register(VILLAGE_SNOWY, Blocks.LADDER, CHESTNUT_LADDER.get(), quarkOrWoodworks);

        // Shipwrecks //
        NamesResourceSelector shipwrecks = new NamesResourceSelector(SHIPWRECK.location(), SHIPWRECK_BEACHED.location());

        this.register("shipwrecks/pine_door_replaces_jungle_door", shipwrecks, Blocks.JUNGLE_DOOR, PINE_DOOR.get());
        this.register("shipwrecks/pine_fence_replaces_jungle_fence", shipwrecks, Blocks.JUNGLE_FENCE, PINE_FENCE.get());
        this.register("shipwrecks/pine_log_replaces_jungle_log", shipwrecks, Blocks.JUNGLE_LOG, PINE_LOG.get());
        this.register("shipwrecks/pine_planks_replaces_jungle_planks", shipwrecks, Blocks.JUNGLE_PLANKS, PINE_PLANKS.get());
        this.register("shipwrecks/pine_slab_replaces_jungle_slab", shipwrecks, Blocks.JUNGLE_SLAB, PINE_SLAB.get());
        this.register("shipwrecks/pine_stairs_replaces_jungle_stairs", shipwrecks, Blocks.JUNGLE_STAIRS, PINE_STAIRS.get());
        this.register("shipwrecks/pine_trapdoor_replaces_jungle_trapdoor", shipwrecks, Blocks.JUNGLE_TRAPDOOR, PINE_TRAPDOOR.get());
    }

    private void register(ResourceKey<Structure> structure, Block replacesBlock, Block replacesWith, ICondition... conditions) {
        this.registerRepaletter(structure.location().getPath() + "/" + getName(replacesWith) + "_replaces_" + getName(replacesBlock),
                new ConditionedResourceSelector(new NamesResourceSelector(structure.location()), conditions), EventPriority.HIGH, new SimpleStructureRepaletter(replacesBlock, replacesWith));
    }

    private void register(String name, NamesResourceSelector structures, Block replacesBlock, Block replacesWith) {
        this.registerRepaletter(name, new ConditionedResourceSelector(structures), EventPriority.HIGH, new SimpleStructureRepaletter(replacesBlock, replacesWith));
    }

    private static String getName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

}

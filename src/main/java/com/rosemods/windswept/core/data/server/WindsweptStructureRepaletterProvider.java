package com.rosemods.windswept.core.data.server;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterProvider;
import com.teamabnormals.blueprint.core.util.modification.selection.ConditionedResourceSelector;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class WindsweptStructureRepaletterProvider extends StructureRepaletterProvider {

    public WindsweptStructureRepaletterProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MODID);
    }

    @Override
    protected void registerRepaletters() {
        // Igloo //
        this.register(BuiltinStructures.IGLOO, Blocks.SNOW_BLOCK, WindsweptBlocks.SNOW_BRICKS.get());
        this.register(BuiltinStructures.IGLOO, Blocks.OAK_WALL_SIGN, WindsweptBlocks.HOLLY_SIGNS.getSecond().get());
        this.register(BuiltinStructures.IGLOO, Blocks.POTTED_CACTUS, WindsweptBlocks.POTTED_WHITE_ROSE.get());
        this.register(BuiltinStructures.IGLOO, Blocks.SPRUCE_SLAB, WindsweptBlocks.HOLLY_SLAB.get());
        this.register(BuiltinStructures.IGLOO, Blocks.SPRUCE_STAIRS, WindsweptBlocks.HOLLY_STAIRS.get());
        this.register(BuiltinStructures.IGLOO, Blocks.MOSSY_STONE_BRICKS, WindsweptBlocks.HOLLY_PLANKS.get());
        this.register(BuiltinStructures.IGLOO, Blocks.INFESTED_MOSSY_STONE_BRICKS, WindsweptBlocks.HOLLY_PLANKS.get());
        this.register(BuiltinStructures.IGLOO, Blocks.OAK_TRAPDOOR, WindsweptBlocks.HOLLY_TRAPDOOR.get());
        this.register(BuiltinStructures.IGLOO, Blocks.POLISHED_ANDESITE, Blocks.GOLD_BLOCK);

        // Villages //
        this.register(BuiltinStructures.VILLAGE_TAIGA, Blocks.POTTED_SPRUCE_SAPLING, WindsweptBlocks.POTTED_RED_ROSE.get());

        // Mod Compat //
        ICondition quarkOrWoodworks = new OrCondition(new ModLoadedCondition("quark"), new ModLoadedCondition(("woodworks")));

        this.register(getLocalKey("grove_weathered_house"), Blocks.BOOKSHELF, WindsweptBlocks.HOLLY_BOOKSHELF.get(), quarkOrWoodworks);
        this.register(getLocalKey("grove_weathered_house"), Blocks.CHEST, WindsweptBlocks.HOLLY_CHEST.get(), quarkOrWoodworks);
        this.register(getLocalKey("chestnut_weathered_house"), Blocks.CHEST, WindsweptBlocks.CHESTNUT_CHEST.get(), quarkOrWoodworks);
    }

    private void register(ResourceKey<Structure> structure, Block replacesBlock, Block replacesWith, ICondition... conditions) {
        this.registerRepaletter(structure.location().getPath() + "/" + getName(replacesWith) + "_replaces_" + getName(replacesBlock),
                new ConditionedResourceSelector(new NamesResourceSelector(structure.location()), conditions), EventPriority.NORMAL, new SimpleStructureRepaletter(replacesBlock, replacesWith));
    }

    private static ResourceKey getLocalKey(String name) {
        return ResourceKey.create(Registry.STRUCTURE_REGISTRY, Windswept.REGISTRY_HELPER.prefix(name));
    }

    private static String getName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

}

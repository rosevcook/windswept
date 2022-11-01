package com.rosemods.windswept.core.data.server;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterProvider;
import com.teamabnormals.blueprint.core.util.modification.selection.ConditionedResourceSelector;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class WindsweptStructureRepaletterProvider extends StructureRepaletterProvider {

    public WindsweptStructureRepaletterProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MODID);
    }

    @Override
    protected void registerRepaletters() {
        this.basicRepaletter("snow_bricks_in_igloos", BuiltinStructures.IGLOO.location(), Blocks.SNOW_BLOCK, WindsweptBlocks.SNOW_BRICKS.get());
        this.basicRepaletter("holly_sign_in_igloo", BuiltinStructures.IGLOO.location(), Blocks.OAK_WALL_SIGN, WindsweptBlocks.HOLLY_SIGNS.getSecond().get());
        this.basicRepaletter("roses_in_igloo", BuiltinStructures.IGLOO.location(), Blocks.POTTED_CACTUS, WindsweptBlocks.POTTED_WHITE_ROSE.get());
        this.basicRepaletter("holly_slab_in_igloo", BuiltinStructures.IGLOO.location(), Blocks.SPRUCE_SLAB, WindsweptBlocks.HOLLY_SLAB.get());
        this.basicRepaletter("holly_stairs_in_igloo", BuiltinStructures.IGLOO.location(), Blocks.SPRUCE_STAIRS, WindsweptBlocks.HOLLY_STAIRS.get());
        this.basicRepaletter("holly_trapdoor_in_igloo", BuiltinStructures.IGLOO.location(), Blocks.OAK_TRAPDOOR, WindsweptBlocks.HOLLY_TRAPDOOR.get());
        this.basicRepaletter("roses_in_taiga_villages", BuiltinStructures.VILLAGE_TAIGA.location(), Blocks.POTTED_SPRUCE_SAPLING, WindsweptBlocks.POTTED_RED_ROSE.get());
    }

    private void basicRepaletter(String name, ResourceLocation structure, Block replacesBlock, Block replacesWith) {
        this.registerRepaletter(name, new ConditionedResourceSelector(new NamesResourceSelector(structure)), EventPriority.NORMAL, new SimpleStructureRepaletter(replacesBlock, replacesWith));
    }

}

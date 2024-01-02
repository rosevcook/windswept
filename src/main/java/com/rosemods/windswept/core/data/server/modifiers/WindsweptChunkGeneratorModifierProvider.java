package com.rosemods.windswept.core.data.server.modifiers;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBiomes;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.common.world.modification.chunk.ChunkGeneratorModifierProvider;
import com.teamabnormals.blueprint.common.world.modification.chunk.modifiers.SurfaceRuleModifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraftforge.data.event.GatherDataEvent;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class WindsweptChunkGeneratorModifierProvider extends ChunkGeneratorModifierProvider {
    public WindsweptChunkGeneratorModifierProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID);
    }

    @Override
    protected void registerEntries() {
        SurfaceRules.ConditionSource aboveFloor = stoneDepthCheck(-1, false, CaveSurface.FLOOR);
        SurfaceRules.ConditionSource inTundra = isBiome(WindsweptBiomes.TUNDRA.getKey());

        RuleSource gelisolRule = sequence(ifTrue(ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), state(WindsweptBlocks.GELISOL.get().defaultBlockState()))));
        RuleSource grassRule = sequence(ifTrue(ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), state(Blocks.GRASS_BLOCK.defaultBlockState()))));
        RuleSource snowRule = sequence(ifTrue(aboveFloor, ifTrue(waterBlockCheck(-1, 0), state(Blocks.SNOW.defaultBlockState()))));

        this.entry("windswept_surface_rule").selects("minecraft:overworld")
                .addModifier(new SurfaceRuleModifier(ifTrue(abovePreliminarySurface(), ifTrue(inTundra, sequence(ifTrue(noiseRange(1f, 1.8f), snowRule), sequence(ifTrue(noiseRange(.3f, 2.5f), grassRule), gelisolRule)))), false));
    }

    private static ConditionSource noiseRange(float low, float high) {
        return noiseCondition(Noises.SURFACE, low / 8.25f, high / 8.25f);
    }

}

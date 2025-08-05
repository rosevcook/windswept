package com.rosemods.windswept.core.data.server;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptFeatures;
import com.rosemods.windswept.core.registry.datapack.*;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class WindsweptDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, WindsweptConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, WindsweptPlacedFeatures::bootstrap)
            .add(Registries.BIOME, WindsweptBiomes::bootstrap)
            //.add(ForgeRegistries.Keys.BIOME_MODIFIERS, WindsweptBiomeModifiers::bootstrap)
            //.add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, WindsweptStructureRepaletters::bootstrap)
            .add(Registries.DAMAGE_TYPE, WindsweptDamageTypes::bootstrap)
        ;
    public WindsweptDatapackProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), BUILDER, Set.of(Windswept.MOD_ID, "minecraft"));
    }

}

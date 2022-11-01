package com.rosemods.windswept.core.data.server.modifiers;

import com.mojang.serialization.JsonOps;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import com.rosemods.windswept.core.registry.WindsweptFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WindsweptBiomeModifier {
    private static RegistryAccess access = RegistryAccess.builtinCopy();
    private static Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
    private static Registry<PlacedFeature> placedFeatures = access.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
    private static HashMap<ResourceLocation, BiomeModifier> modifiers = new HashMap<>();


    public static JsonCodecProvider<BiomeModifier> register(GatherDataEvent event) {
        addFeature("bluebells", WindsweptBiomeTags.HAS_BLUEBELLS, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.BLUEBELLS);
        addFeature("foxgloves", WindsweptBiomeTags.HAS_FOXGLOVES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.FOXGLOVE);
        addFeature("snowy_sprouts", WindsweptBiomeTags.HAS_SNOWY_SPROUTS, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.SNOWY_SPROUTS);
        addFeature("snowy_flowers", WindsweptBiomeTags.HAS_SNOWY_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.WHITE_ROSE, WindsweptFeatures.Placements.PINK_ROSE, WindsweptFeatures.Placements.BLUE_ROSE);
        addFeature("taiga_flowers", WindsweptBiomeTags.HAS_TAIGA_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.RED_ROSE, WindsweptFeatures.Placements.YELLOW_ROSE);
        addFeature("holly_trees", WindsweptBiomeTags.HAS_HOLLY_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.HOLLY_TREES);
        addFeature("grove_holly_trees", WindsweptBiomeTags.HAS_GROVE_HOLLY_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.GROVE_HOLLY_TREES);
        addFeature("wild_berries", WindsweptBiomeTags.HAS_WILD_BERRIES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.WILD_BERRY_BUSH);
        addFeature("common_wild_berries", WindsweptBiomeTags.HAS_COMMON_WILD_BERRIES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.WILD_BERRY_BUSH_COMMON);
        addFeature("nightshades", WindsweptBiomeTags.HAS_NIGHTSHADES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.NIGHTHSADE);

        return JsonCodecProvider.forDatapackRegistry(event.getGenerator(), event.getExistingFileHelper(), Windswept.MODID, RegistryOps.create(JsonOps.INSTANCE, access), ForgeRegistries.Keys.BIOME_MODIFIERS, modifiers);
    }

    private static void addFeature(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, RegistryObject<PlacedFeature>... features) {
        modifiers.put(Windswept.REGISTRY_HELPER.prefix(name),
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(biomeRegistry, tagKey), featureSet(features), step));
    }

    @SafeVarargs
    @SuppressWarnings("ConstantConditions")
    private static HolderSet<PlacedFeature> featureSet(RegistryObject<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(registryObject -> placedFeatures.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
    }

}

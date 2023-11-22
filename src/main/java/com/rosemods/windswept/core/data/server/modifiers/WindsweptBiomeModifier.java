package com.rosemods.windswept.core.data.server.modifiers;

import com.mojang.serialization.JsonOps;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import com.rosemods.windswept.core.registry.WindsweptEntities;
import com.rosemods.windswept.core.registry.WindsweptFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WindsweptBiomeModifier {
    private static final RegistryAccess access = RegistryAccess.builtinCopy();
    private static final Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
    private static final Registry<PlacedFeature> placedFeatures = access.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
    private static final HashMap<ResourceLocation, BiomeModifier> modifiers = new HashMap<>();

    public static JsonCodecProvider<BiomeModifier> register(GatherDataEvent event) {
        // features
        addFeature("bluebells", WindsweptBiomeTags.HAS_BLUEBELLS, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.BLUEBELLS);
        addFeature("foxgloves", WindsweptBiomeTags.HAS_FOXGLOVES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.FOXGLOVE);
        addFeature("snowy_sprouts", WindsweptBiomeTags.HAS_SNOWY_SPROUTS, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.SNOWY_SPROUTS);
        addFeature("snowy_flowers", WindsweptBiomeTags.HAS_SNOWY_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.WHITE_ROSE, WindsweptFeatures.Placements.PINK_ROSE, WindsweptFeatures.Placements.BLUE_ROSE);
        addFeature("taiga_flowers", WindsweptBiomeTags.HAS_TAIGA_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.RED_ROSE, WindsweptFeatures.Placements.YELLOW_ROSE);
        addFeature("holly_trees", WindsweptBiomeTags.HAS_HOLLY_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.HOLLY_TREES);
        addFeature("grove_holly_trees", WindsweptBiomeTags.HAS_GROVE_HOLLY_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.GROVE_HOLLY_TREES, WindsweptFeatures.Placements.GROVE_SPRUCE_TREES);
        addFeature("wild_berries", WindsweptBiomeTags.HAS_WILD_BERRIES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.WILD_BERRY_BUSH);
        addFeature("common_wild_berries", WindsweptBiomeTags.HAS_COMMON_WILD_BERRIES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.WILD_BERRY_BUSH_COMMON);
        addFeature("nightshades", WindsweptBiomeTags.HAS_NIGHTSHADES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.NIGHTHSADE);
        addFeature("rare_chestnut_trees", WindsweptBiomeTags.HAS_RARE_CHESTNUT_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.CHESTNUT_TREES);
        addFeature("rare_snowy_holly_trees", WindsweptBiomeTags.HAS_RARE_SNOWY_HOLLY_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.RARE_SNOWY_HOLLY_TREES);
        addFeature("yellow_rose_bushes", WindsweptBiomeTags.HAS_YELLOW_ROSE_BUSHES, GenerationStep.Decoration.VEGETAL_DECORATION, WindsweptFeatures.Placements.YELLOW_ROSE_BUSH);

        // spawns
        addSpawn("chilled", Tags.Biomes.IS_SNOWY, WindsweptEntities.CHILLED, 5, 3, 7);

        // removed features
        removeFeature("grove_spruces", WindsweptBiomeTags.HAS_GROVE_HOLLY_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_GROVE);
        removeFeature("taiga_default_flowers", BiomeTags.IS_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_DEFAULT);
        removeFeature("snowy_default_flowers", Tags.Biomes.IS_SNOWY, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_DEFAULT);

        return JsonCodecProvider.forDatapackRegistry(event.getGenerator(), event.getExistingFileHelper(), Windswept.MODID, RegistryOps.create(JsonOps.INSTANCE, access), ForgeRegistries.Keys.BIOME_MODIFIERS, modifiers);
    }

    @SafeVarargs
    private static void addFeature(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, RegistryObject<PlacedFeature>... features) {
        modifiers.put(Windswept.REGISTRY_HELPER.prefix("features/" + name),
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(biomeRegistry, tagKey), featureSet(features), step));
    }

    private static void removeFeature(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, Holder<PlacedFeature> feature) {
        modifiers.put(Windswept.REGISTRY_HELPER.prefix("removed_features/" + name),
                new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(new HolderSet.Named<>(biomeRegistry, tagKey), featureSet(feature), Set.of(step)));
    }

    private static <T extends LivingEntity> void addSpawn(String name, TagKey<Biome> tagKey, RegistryObject<EntityType<T>> entity, int weight, int min, int max) {
        modifiers.put(Windswept.REGISTRY_HELPER.prefix("spawns/" + name),
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(new HolderSet.Named<>(biomeRegistry, tagKey), List.of(new MobSpawnSettings.SpawnerData(entity.get(), weight, min, max))));
    }

    @SafeVarargs
    private static HolderSet<PlacedFeature> featureSet(RegistryObject<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(registryObject -> {
            assert registryObject.getKey() != null;
            return placedFeatures.getOrCreateHolderOrThrow(registryObject.getKey());
        }).collect(Collectors.toList()));
    }

    @SafeVarargs
    private static HolderSet<PlacedFeature> featureSet(Holder<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(holder -> placedFeatures.getOrCreateHolderOrThrow(holder.unwrapKey().get())).collect(Collectors.toList()));
    }

}

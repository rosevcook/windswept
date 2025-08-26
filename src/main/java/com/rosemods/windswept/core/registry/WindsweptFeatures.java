package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.levelgen.feature.*;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Windswept.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SNOWY_SPROUTS_PATCH = FEATURES.register("snowy_sprouts_patch", SnowySproutsFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> NIGHTSHADE_PATCH = FEATURES.register("nightshade_patch", NightshadeFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BLUEBELL_PATCH = FEATURES.register("bluebell_patch", BluebellsFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LAVENDER_PATCH = FEATURES.register("lavender_patch", LavenderFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ICICLES_PATCH = FEATURES.register("icicles_patch", IciclesFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOOR_ICICLES_PATCH = FEATURES.register("floor_icicles_patch", FloorIciclesFeature::new);
    public static final RegistryObject<Feature<SimpleBlockConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", FallenLogFeature::new);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SNOWY_GELISOL = FEATURES.register("snowy_gelisol", SnowyGelisolFeature::new);
    public static final RegistryObject<Feature<TreeConfiguration>> PINE_TREE = FEATURES.register("pine_tree", PineTreeFeature::new);

}

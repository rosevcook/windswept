package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public final class WindsweptBiomeTags {
    public static final TagKey<Biome> HAS_BLUEBELLS = TagUtil.biomeTag(Windswept.MODID, "has_feature/bluebells");
    public static final TagKey<Biome> HAS_FOXGLOVES = TagUtil.biomeTag(Windswept.MODID, "has_feature/foxgloves");
    public static final TagKey<Biome> HAS_YELLOW_ROSE_BUSHES = TagUtil.biomeTag(Windswept.MODID, "has_feature/yellow_rose_bushes");
    public static final TagKey<Biome> HAS_SNOWY_FLOWERS = TagUtil.biomeTag(Windswept.MODID, "has_feature/snowy_flowers");
    public static final TagKey<Biome> HAS_TAIGA_FLOWERS = TagUtil.biomeTag(Windswept.MODID, "has_feature/taiga_flowers");
    public static final TagKey<Biome> HAS_HOLLY_TREES = TagUtil.biomeTag(Windswept.MODID, "has_feature/holly_trees");
    public static final TagKey<Biome> HAS_GROVE_HOLLY_TREES = TagUtil.biomeTag(Windswept.MODID, "has_feature/grove_holly_trees");
    public static final TagKey<Biome> HAS_SNOWY_SPROUTS = TagUtil.biomeTag(Windswept.MODID, "has_feature/snowy_sprouts");
    public static final TagKey<Biome> HAS_NIGHTSHADES = TagUtil.biomeTag(Windswept.MODID, "has_feature/nightshades");
    public static final TagKey<Biome> HAS_WILD_BERRIES = TagUtil.biomeTag(Windswept.MODID, "has_feature/wild_berries");
    public static final TagKey<Biome> HAS_COMMON_WILD_BERRIES = TagUtil.biomeTag(Windswept.MODID, "has_feature/common_wild_berries");
    public static final TagKey<Biome> HAS_CHESTNUT_TREES = TagUtil.biomeTag(Windswept.MODID, "has_feature/rare_chestnut_trees");
    public static final TagKey<Biome> HAS_RARE_SNOWY_HOLLY_TREES = TagUtil.biomeTag(Windswept.MODID, "has_feature/rare_snowy_holly_trees");

    public static final TagKey<Biome> HAS_GROVE_WEATHERED_HOUSE = TagUtil.biomeTag(Windswept.MODID, "has_structure/grove_weathered_house");
    public static final TagKey<Biome> HAS_CHESTNUT_WEATHERED_HOUSE = TagUtil.biomeTag(Windswept.MODID, "has_structure/chestnut_weathered_house");

    public static final TagKey<Biome> IS_CHESTNUT_FOREST = TagUtil.biomeTag(Windswept.MODID, "is_chestnut_forest");

    public static final TagKey<Biome> HAS_SPOTTED_ORANGE_MAPLE_TREES = TagUtil.biomeTag("autumnity", "has_feature/spotted_maple_tree/orange");
    public static final TagKey<Biome> HAS_SPOTTED_RED_MAPLE_TREES = TagUtil.biomeTag("autumnity", "has_feature/spotted_maple_tree/red");


}

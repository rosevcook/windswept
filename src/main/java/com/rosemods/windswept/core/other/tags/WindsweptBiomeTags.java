package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public final class WindsweptBiomeTags {
    public static final TagKey<Biome> HAS_RED_ROSE = TagUtil.biomeTag(Windswept.MOD_ID, "has_feature/red_rose");
    public static final TagKey<Biome> HAS_BLUE_ROSE = TagUtil.biomeTag(Windswept.MOD_ID, "has_feature/blue_rose");
    public static final TagKey<Biome> HAS_WHITE_ROSE = TagUtil.biomeTag(Windswept.MOD_ID, "has_feature/white_rose");
    public static final TagKey<Biome> HAS_YELLOW_ROSE = TagUtil.biomeTag(Windswept.MOD_ID, "has_feature/yellow_rose");
    public static final TagKey<Biome> HAS_BLUEBELLS = TagUtil.biomeTag(Windswept.MOD_ID, "has_feature/bluebells");
    public static final TagKey<Biome> HAS_HOLLY_TREES = TagUtil.biomeTag(Windswept.MOD_ID, "has_feature/holly_trees");
    public static final TagKey<Biome> HAS_RARE_CHESTNUT_TREES = TagUtil.biomeTag(Windswept.MOD_ID, "has_feature/rare_chestnut_trees");
    public static final TagKey<Biome> HAS_RARE_SNOWY_HOLLY_TREES = TagUtil.biomeTag(Windswept.MOD_ID, "has_feature/rare_snowy_holly_trees");

    public static final TagKey<Biome> HAS_GROVE_WEATHERED_HOUSE = TagUtil.biomeTag(Windswept.MOD_ID, "has_structure/grove_weathered_house");
    public static final TagKey<Biome> HAS_CHESTNUT_WEATHERED_HOUSE = TagUtil.biomeTag(Windswept.MOD_ID, "has_structure/chestnut_weathered_house");
    public static final TagKey<Biome> HAS_FROZEN_VILLAGE = TagUtil.biomeTag(Windswept.MOD_ID, "has_structure/frozen_village");

    public static final TagKey<Biome> IS_CHESTNUT_FOREST = TagUtil.biomeTag(Windswept.MOD_ID, "is_chestnut_forest");
    public static final TagKey<Biome> IS_PINE_BARRENS = TagUtil.biomeTag(Windswept.MOD_ID, "is_pine_barrens");
    public static final TagKey<Biome> IS_LAVENDER = TagUtil.biomeTag(Windswept.MOD_ID, "is_lavender");

    public static final TagKey<Biome> HAS_SPOTTED_ORANGE_MAPLE_TREES = TagUtil.biomeTag("autumnity", "has_feature/spotted_maple_tree/orange");
    public static final TagKey<Biome> HAS_SPOTTED_RED_MAPLE_TREES = TagUtil.biomeTag("autumnity", "has_feature/spotted_maple_tree/red");

    public static final TagKey<Biome> HAS_YAK = TagUtil.biomeTag("environmental", "has_animal/yak");
    public static final TagKey<Biome> HAS_REINDEER = TagUtil.biomeTag("environmental", "has_animal/reindeer");
    public static final TagKey<Biome> HAS_SHEEP = TagUtil.biomeTag("environmental", "has_animal/sheep");
    public static final TagKey<Biome> HAS_DWARF_SPRUCE_SPARSE = TagUtil.biomeTag("environmental", "has_feature/dwarf_spruce_sparse");

    public static final TagKey<Biome> HAS_STRAWBERRY_BUSH = TagUtil.biomeTag("neapolitan", "has_feature/strawberry_bush");

}

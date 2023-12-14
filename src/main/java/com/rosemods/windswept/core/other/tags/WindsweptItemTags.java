package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class WindsweptItemTags {
    public static final TagKey<Item> HOLLY_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "holly_logs");
    public static final TagKey<Item> CHESTNUT_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "chestnut_logs");
    public static final TagKey<Item> PINE_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "pine_logs");
    public static final TagKey<Item> WILD_BERRY_SEEDS = TagUtil.itemTag("forge", "seeds/wild_berry");
    public static final TagKey<Item> MILK = TagUtil.itemTag("forge", "milk");
    public static final TagKey<Item> RAW_GOAT = TagUtil.itemTag("forge", "raw_goat");
    public static final TagKey<Item> COOKED_GOAT = TagUtil.itemTag("forge", "cooked_goat");
    public static final TagKey<Item> CABBAGE_ROLL_INGREDIENTS = TagUtil.itemTag("farmersdelight", "cabbage_roll_ingredients");
    public static final TagKey<Item> SILVER_INGOT = TagUtil.itemTag("forge", "ingots/silver");
    public static final TagKey<Item> BERRIES = TagUtil.itemTag("forge", "berries");
    public static final TagKey<Item> COOKED_MUTTON = TagUtil.itemTag("forge", "cooked_mutton");
    public static final TagKey<Item> COOKED_CHICKEN = TagUtil.itemTag("forge", "cooked_chicken");
    public static final TagKey<Item> COOKED_BACON = TagUtil.itemTag("forge", "cooked_bacon");
    public static final TagKey<Item> ROSES = TagUtil.itemTag(Windswept.MOD_ID, "roses");
    public static final TagKey<Item> WOODEN_CABINETS = TagUtil.itemTag("farmersdelight", "cabinets/wooden");

}

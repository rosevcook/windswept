package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class WindsweptItemTags {
	public static final TagKey<Item> HOLLY_LOGS = TagUtil.itemTag(Windswept.MODID, "holly_logs");
	public static final TagKey<Item> CHESTNUT_LOGS = TagUtil.itemTag(Windswept.MODID, "chestnut_logs");
	public static final TagKey<Item> WILD_BERRY_SEEDS = TagUtil.itemTag("forge", "seeds/wild_berry");
	public static final TagKey<Item> MILK = TagUtil.itemTag("forge", "milk");

}

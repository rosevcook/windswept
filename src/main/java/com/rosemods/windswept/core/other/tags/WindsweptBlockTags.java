package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class WindsweptBlockTags {
	public static final TagKey<Block> HOLLY_LOGS = TagUtil.blockTag(Windswept.MODID, "holly_logs");
	public static final TagKey<Block> CHESTNUT_LOGS = TagUtil.blockTag(Windswept.MODID, "chestnut_logs");
	public static final TagKey<Block> HOLLY_LEAVES = TagUtil.blockTag(Windswept.MODID, "holly_leaves");
	public static final TagKey<Block> CHESTNUT_LEAVES = TagUtil.blockTag(Windswept.MODID, "chestnut_leaves");
	public static final TagKey<Block> SNOW_BOOTS_BLOCKS = TagUtil.blockTag(Windswept.MODID, "snow_boots_blocks");
	public static final TagKey<Block> DEFAULT_WHITE_TEXT = TagUtil.blockTag(Windswept.MODID, "default_white_text");
}

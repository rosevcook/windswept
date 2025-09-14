package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class WindsweptBlockTags {
    public static final TagKey<Block> HOLLY_LOGS = TagUtil.blockTag(Windswept.MOD_ID, "holly_logs");
    public static final TagKey<Block> CHESTNUT_LOGS = TagUtil.blockTag(Windswept.MOD_ID, "chestnut_logs");
    public static final TagKey<Block> PINE_LOGS = TagUtil.blockTag(Windswept.MOD_ID, "pine_logs");
    public static final TagKey<Block> LUNALITE = TagUtil.blockTag(Windswept.MOD_ID, "lunalite");
    public static final TagKey<Block> SNOW_BOOTS_BLOCKS = TagUtil.blockTag(Windswept.MOD_ID, "snow_boots_blocks");
    public static final TagKey<Block> FROSTBITER_FOOD = TagUtil.blockTag(Windswept.MOD_ID, "frostbiter_food");
    public static final TagKey<Block> PINECONE_NOTE_BLOCKS = TagUtil.blockTag(Windswept.MOD_ID, "pinecone_note_blocks");

    public static final TagKey<Block> CHEST_MOUNTED_STORAGE = TagUtil.blockTag("create", "chest_mounted_storage");
    public static final TagKey<Block> PINECONE_GOLEM_BASE_BLOCKS = TagUtil.blockTag("environmental", "pinecone_golem_base_blocks");
    public static final TagKey<Block> GINGERBREADS = TagUtil.blockTag("snowyspirit", "gingerbreads");
    public static final TagKey<Block> QUARK_LADDERS = TagUtil.blockTag("quark", "ladders");
}

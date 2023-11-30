package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public final class WindsweptEntityTypeTags {
    public static final TagKey<EntityType<?>> CONVERT_TO_CHILLED = TagUtil.entityTypeTag(Windswept.MOD_ID, "convert_to_chilled");
    public static final TagKey<EntityType<?>> HOLLY_IMMUNE = TagUtil.entityTypeTag(Windswept.MOD_ID, "holly_immune");
}

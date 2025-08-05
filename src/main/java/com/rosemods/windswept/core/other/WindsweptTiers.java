package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.core.api.BlueprintArmorMaterial;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class WindsweptTiers {
    public static final BlueprintArmorMaterial SNOW_BOOTS = new BlueprintArmorMaterial(Windswept.location("snow_boots"), 12, new int[]{1, 0, 0, 0}, 17, () -> SoundEvents.ARMOR_EQUIP_GOLD, 0f, 0f, () -> Ingredient.of(Items.LEATHER));
    public static final BlueprintArmorMaterial LAVENDER_CROWN = new BlueprintArmorMaterial(Windswept.location("lavender_crown"), 2, new int[]{0, 0, 0, 0}, 10, () -> SoundEvents.AZALEA_BREAK, 0f, 0f, () -> Ingredient.of(WindsweptBlocks.LAVENDER.get()));
    public static final BlueprintArmorMaterial ANTLER_HELMET = new BlueprintArmorMaterial(Windswept.location("antler_helmet"), 8, new int[]{0, 0, 0, 1}, 17, () -> SoundEvents.ARMOR_EQUIP_GENERIC, 0f, .1f, () -> Ingredient.of(Items.BONE));
    //public static final BlueprintArmorMaterial FEATHER_CLOAK = new BlueprintArmorMaterial(Windswept.location("feather_cloak"), 8, new int[]{0, 0, 1, 0}, 17, () -> SoundEvents.ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.of(WindsweptItems.OWL_FEATHER.get()));
}

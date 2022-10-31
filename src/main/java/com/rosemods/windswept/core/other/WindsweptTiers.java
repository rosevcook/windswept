package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.api.BlueprintArmorMaterial;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class WindsweptTiers {
	public static final BlueprintArmorMaterial SNOW_BOOTS = new BlueprintArmorMaterial(Windswept.REGISTRY_HELPER.prefix("snow_boots"), 
			12, new int[]{2, 3, 2, 3}, 17, () -> SoundEvents.ARMOR_EQUIP_GOLD, 0f, 0f, () -> Ingredient.of(Items.LEATHER));
}

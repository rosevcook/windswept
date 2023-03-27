package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.effect.ThornsEffect;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.util.EffectSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.DataUtil;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Windswept.MODID, bus = Bus.MOD)
public class WindsweptEffects {
	public static final EffectSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getSubHelper(ForgeRegistries.MOB_EFFECTS);
	
	// Effects //
	
	public static final RegistryObject<MobEffect> THORNS = HELPER.createEffect("thorns", ThornsEffect::new);
	
	// Potions //
	
	public static final RegistryObject<Potion> THORNS_POTION = HELPER.createPotion("thorns", THORNS, 3600, 0);
	public static final RegistryObject<Potion> LONG_THORNS_POTION = HELPER.createPotion("long_thorns", THORNS, 9600, 0);
	public static final RegistryObject<Potion> STRONG_THORNS_POTION = HELPER.createPotion("strong_thorns", THORNS, 1800, 1);
	
	public static void registerPotionRecipes() {
		DataUtil.addMix(Potions.AWKWARD, WindsweptItems.HOLLY_BERRIES.get(), THORNS_POTION.get());
		DataUtil.addMix(Potions.AWKWARD, WindsweptBlocks.NIGHTSHADE.get().asItem(), Potions.NIGHT_VISION);
		DataUtil.addMix(THORNS_POTION.get(), Items.REDSTONE, LONG_THORNS_POTION.get());
		DataUtil.addMix(THORNS_POTION.get(), Items.GLOWSTONE_DUST, STRONG_THORNS_POTION.get());
	}
	
}

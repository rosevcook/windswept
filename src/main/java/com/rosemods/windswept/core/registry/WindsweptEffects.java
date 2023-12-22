package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.util.EffectSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptEffects {
    public static final EffectSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getSubHelper(ForgeRegistries.MOB_EFFECTS);

    // Effects //
    public static final RegistryObject<MobEffect> THORNS = HELPER.createEffect("thorns", MobEffectCategory.BENEFICIAL, 0x295230);
    public static final RegistryObject<MobEffect> FROST_RESISTANCE = HELPER.createEffect("frost_resistance", MobEffectCategory.BENEFICIAL, 0x618cff);
    public static final RegistryObject<MobEffect> PLENTY = HELPER.createEffect("plenty", MobEffectCategory.BENEFICIAL, 0xe68834);

    // Potions //
    public static final RegistryObject<Potion> THORNS_POTION = HELPER.createPotion("thorns", THORNS, 3600, 0);
    public static final RegistryObject<Potion> LONG_THORNS_POTION = HELPER.createPotion("long_thorns", THORNS, 9600, 0);
    public static final RegistryObject<Potion> STRONG_THORNS_POTION = HELPER.createPotion("strong_thorns", THORNS, 1800, 1);

    public static final RegistryObject<Potion> FROST_RESISTANCE_POTION = HELPER.createPotion("frost_resistance", FROST_RESISTANCE, 3600, 0);
    public static final RegistryObject<Potion> LONG_FROST_RESISTANCE_POTION = HELPER.createPotion("long_frost_resistance", FROST_RESISTANCE, 9600, 0);

    public static void registerPotionRecipes() {
        DataUtil.addMix(Potions.AWKWARD, WindsweptBlocks.NIGHTSHADE.get().asItem(), Potions.NIGHT_VISION);

        DataUtil.addMix(Potions.AWKWARD, WindsweptItems.HOLLY_BERRIES.get(), THORNS_POTION.get());
        DataUtil.addMix(THORNS_POTION.get(), Items.REDSTONE, LONG_THORNS_POTION.get());
        DataUtil.addMix(THORNS_POTION.get(), Items.GLOWSTONE_DUST, STRONG_THORNS_POTION.get());

        DataUtil.addMix(Potions.AWKWARD, WindsweptItems.FROZEN_BRANCH.get(), FROST_RESISTANCE_POTION.get());
        DataUtil.addMix(FROST_RESISTANCE_POTION.get(), Items.REDSTONE, LONG_FROST_RESISTANCE_POTION.get());
    }

}

package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.enchantment.curse.SlippingCurseEnchantment;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Windswept.MOD_ID);

    public static final RegistryObject<Enchantment> SLIPPING_CURSE = ENCHANTMENTS.register("slipping_curse", SlippingCurseEnchantment::new);
}

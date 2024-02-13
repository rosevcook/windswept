package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Windswept.MOD_ID, bus = Bus.MOD)
public class WindsweptAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Windswept.MOD_ID);

    public static final RegistryObject<Attribute> SNOW_SPEED = register("snow_speed");
    public static final RegistryObject<Attribute> FRAGRANCE = register("fragrance");
    public static final RegistryObject<Attribute> SPRINT_DAMAGE = register("sprint_damage");

    private static RegistryObject<Attribute> register(String name) {
        return ATTRIBUTES.register(name, () -> new RangedAttribute("attribute." + Windswept.MOD_ID + ".name.generic." + name, 0d, 0d, 1d));
    }

}

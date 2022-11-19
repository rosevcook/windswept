package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Windswept.MODID, bus = Bus.MOD)
public class WindsweptAttributes {
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Windswept.MODID);
	
	public static final RegistryObject<Attribute> SNOW_SPEED = ATTRIBUTES.register("snow_speed", () -> new RangedAttribute(createDescriptionId("snow_speed"), 0d, 0d, 1d));
	
	private static String createDescriptionId(String name) {
		return "attribute." + Windswept.MODID + ".name.generic." + name; 
	}
	
}

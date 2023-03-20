package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Windswept.MODID, bus = Bus.MOD)
public class WindsweptSounds {
	private static final SoundSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> MUSIC_DISC_RAIN = HELPER.createSoundEvent("music_disc.rain");
	public static final RegistryObject<SoundEvent> MUSIC_DISC_SNOW = HELPER.createSoundEvent("music_disc.snow");
	public static final RegistryObject<SoundEvent> MUSIC_DISC_BUMBLEBEE = HELPER.createSoundEvent("music_disc.bumblebee");

}

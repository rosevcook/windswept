package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.client.particle.FrostLeafParticle;
import com.rosemods.windswept.client.particle.WillOTheWispParticle;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WindsweptParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Windswept.MOD_ID);

    public static final RegistryObject<SimpleParticleType> WILL_O_THE_WISP = PARTICLE_TYPES.register("will_o_the_wisp", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FROST_LEAF = PARTICLE_TYPES.register("frost_leaf", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FEATHER_CLOAK = PARTICLE_TYPES.register("feather_cloak", () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void register(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WILL_O_THE_WISP.get(), WillOTheWispParticle.Provider::new);
        event.registerSpriteSet(FROST_LEAF.get(), FrostLeafParticle.Provider::new);
        event.registerSpriteSet(FEATHER_CLOAK.get(), EndRodParticle.Provider::new);
    }

}

package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.client.particle.CupidsArrowParticle;
import com.rosemods.windswept.client.particle.FrostLeafParticle;
import com.rosemods.windswept.client.particle.WillOTheWispParticle;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.autumnity.client.particle.FallingMapleLeafParticle;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.HeartParticle;
import net.minecraft.client.particle.SuspendedParticle;
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

    public static final RegistryObject<SimpleParticleType> CUPIDS_ARROW = PARTICLE_TYPES.register("cupids_arrow", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> WILL_O_THE_WISP = PARTICLE_TYPES.register("will_o_the_wisp", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FROST_LEAF = PARTICLE_TYPES.register("frost_leaf", () -> new SimpleParticleType(false));


    @SubscribeEvent
    public static void register(RegisterParticleProvidersEvent event) {
        event.register(CUPIDS_ARROW.get(), CupidsArrowParticle.Provider::new);
        event.register(WILL_O_THE_WISP.get(), WillOTheWispParticle.Provider::new);
        event.register(FROST_LEAF.get(), FrostLeafParticle.Provider::new);
    }

}

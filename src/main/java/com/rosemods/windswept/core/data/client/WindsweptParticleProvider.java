package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rosemods.windswept.core.registry.WindsweptParticleTypes.*;

public class WindsweptParticleProvider extends ParticleProvider {
    public WindsweptParticleProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), Windswept.MOD_ID);
    }

    @Override
    protected void addParticles() {
        this.add(WILL_O_THE_WISP, 3);
        this.add(FROST_LEAF, 3);
        this.add(FEATHER_CLOAK, 10);
        this.add(ACACIA_LEAVES, 12);
    }

    private <T extends ParticleType<?>> void add(RegistryObject<T> particle, int amount) {
        String name = ForgeRegistries.PARTICLE_TYPES.getKey(particle.get()).getPath();
        String[] textures = new String[amount];

        for (int i = 0; i < amount; i++)
            textures[i] = name + "_" + i;

        this.add(particle.get(), textures);
    }

}

package com.rosemods.windswept.core.data.client;

import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class ParticleProvider implements DataProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final DataGenerator gen;
    private final String modid;
    private final Map<String, ParticleDefinition> particles = new LinkedHashMap<>();

    public ParticleProvider(DataGenerator gen, String modid) {
        this.gen = gen;
        this.modid = modid;
    }

    protected abstract void addParticles();

    protected void add(ParticleType<?> particle, String... textures) {
        List<String> entries = Arrays.stream(textures).map(s -> new ResourceLocation(this.modid, s).toString()).toList();
        this.particles.put(ForgeRegistries.PARTICLE_TYPES.getKey(particle).getPath(), new ParticleDefinition(entries));
    }

    @Override
    public void run(CachedOutput cache) {
        this.addParticles();

        this.particles.forEach((k, v) -> {
            Path path = this.gen.getOutputFolder().resolve("assets/" + this.modid + "/particles/" + k + ".json");

            try {
                DataProvider.saveStable(cache, v.serialize(), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't save particles to {}", path, e);
            }

        });
    }

    @Override
    public String getName() {
        return "Particles: " + this.modid;
    }

    private record ParticleDefinition(List<String> entries) {
        private static final Codec<ParticleDefinition> CODEC = RecordCodecBuilder.create(textures -> textures.group(Codec.STRING.listOf().fieldOf("textures").forGetter(ParticleDefinition::entries)).apply(textures, ParticleDefinition::new));

        public JsonElement serialize() {
            return CODEC.encodeStart(JsonOps.INSTANCE, this).getOrThrow(false, LOGGER::error);
        }
    }

}

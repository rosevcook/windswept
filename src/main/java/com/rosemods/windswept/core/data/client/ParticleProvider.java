package com.rosemods.windswept.core.data.client;

import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class ParticleProvider implements DataProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final PackOutput packOutput;
    private final String modid;
    private final Map<String, ParticleDefinition> particles = new LinkedHashMap<>();

    public ParticleProvider(PackOutput packOutput, String modid) {
        this.packOutput = packOutput;
        this.modid = modid;
    }

    protected abstract void addParticles();

    protected void add(ParticleType<?> particle, String... textures) {
        this.particles.put(ForgeRegistries.PARTICLE_TYPES.getKey(particle).getPath(),
                new ParticleDefinition(Arrays.stream(textures).map(s -> new ResourceLocation(this.modid, s).toString()).toList()));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        this.addParticles();
        CompletableFuture<?>[] futures = new CompletableFuture<?>[this.particles.size()];
        int i = 0;

        for (Map.Entry<String, ParticleDefinition> entry : this.particles.entrySet()) {
            Path path = this.packOutput.getOutputFolder().resolve("assets/" + this.modid + "/particles/" + entry.getKey() + ".json");
            futures[i] = DataProvider.saveStable(cache, entry.getValue().serialize(), path);
            i++;
        }

        return CompletableFuture.allOf(futures);
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

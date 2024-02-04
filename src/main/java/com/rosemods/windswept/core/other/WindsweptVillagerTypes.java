package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.registry.WindsweptBiomes;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biomes;

public final class WindsweptVillagerTypes {
    public static void registerVillagerTypes() {
        VillagerTrades.TRADES.isEmpty(); // referenced to call trade modification event to prevent crash
        VillagerType ice = VillagerType.register("windswept:ice");

        VillagerType.BY_BIOME.replace(Biomes.ICE_SPIKES, ice);
        VillagerType.BY_BIOME.replace(Biomes.FROZEN_PEAKS, ice);
        VillagerType.BY_BIOME.replace(Biomes.GROVE, ice);
        VillagerType.BY_BIOME.put(WindsweptBiomes.TUNDRA.getKey(), ice);
        VillagerType.BY_BIOME.put(WindsweptBiomes.PINE_BARRENS.getKey(), VillagerType.TAIGA);
        VillagerType.BY_BIOME.put(WindsweptBiomes.CHESTNUT_FOREST.getKey(), VillagerType.TAIGA);
        VillagerType.BY_BIOME.put(WindsweptBiomes.SNOWY_PINE_BARRENS.getKey(), VillagerType.SNOW);
        VillagerType.BY_BIOME.put(WindsweptBiomes.SNOWY_CHESTNUT_FOREST.getKey(), VillagerType.SNOW);
    }
}

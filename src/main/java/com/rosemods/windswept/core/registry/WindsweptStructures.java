package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class WindsweptStructures {
    public static final ResourceKey<Structure> CHESTNUT_WEATHERED_HOUSE = createKey("chestnut_weathered_house");
    public static final ResourceKey<Structure> GROVE_WEATHERED_HOUSE = createKey("grove_weathered_house");
    public static final ResourceKey<Structure> VILLAGE_FROZEN = createKey("village_frozen");

    private static ResourceKey<Structure> createKey(String id) {
        return ResourceKey.create(Registry.STRUCTURE_REGISTRY, Windswept.location(id));
    }
}

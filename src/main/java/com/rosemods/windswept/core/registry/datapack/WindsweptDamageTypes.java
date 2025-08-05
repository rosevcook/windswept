package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public final class WindsweptDamageTypes {
    public static final ResourceKey<DamageType> HOLLY_LEAVES = createKey("holly_leaves");
    public static final ResourceKey<DamageType> ICICLE = createKey("icicle");

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(HOLLY_LEAVES, new DamageType("hollyLeaves", .1f));
        context.register(ICICLE, new DamageType("icicle", .1f));
    }

    private static ResourceKey<DamageType> createKey(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Windswept.location(name));
    }

}

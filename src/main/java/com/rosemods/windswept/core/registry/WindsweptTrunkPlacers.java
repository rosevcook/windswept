package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.levelgen.tree.trunk_placer.ChestnutTrunkPlacer;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptTrunkPlacers {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, Windswept.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<ChestnutTrunkPlacer>> CHESTNUT_TRUNK_PLACER = TRUNK_PLACERS.register("chestnut_trunk_placer", () -> new TrunkPlacerType<>(ChestnutTrunkPlacer.CODEC));

}

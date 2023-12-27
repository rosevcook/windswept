package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.levelgen.tree.foliage_placer.ChestnutFoliagePlacer;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Windswept.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<?>> CHESTNUT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("chestnut_foliage_placer", () -> new FoliagePlacerType<>(ChestnutFoliagePlacer.CODEC));
}

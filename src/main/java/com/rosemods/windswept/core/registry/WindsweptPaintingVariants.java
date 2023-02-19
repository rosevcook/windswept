package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptPaintingVariants {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Windswept.MODID);

    public static final RegistryObject<PaintingVariant> CLIFFSIDE = PAINTING_VARIANTS.register("cliffside", () -> new PaintingVariant(48, 32));
    public static final RegistryObject<PaintingVariant> BLOOM = PAINTING_VARIANTS.register("bloom", () -> new PaintingVariant(32, 32));
}

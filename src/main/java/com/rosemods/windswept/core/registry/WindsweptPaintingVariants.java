package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptPaintingVariants {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Windswept.MOD_ID);

    public static final RegistryObject<PaintingVariant> CLIFFSIDE = PAINTING_VARIANTS.register("cliffside", () -> new PaintingVariant(48, 32));
    public static final RegistryObject<PaintingVariant> TUNDRA = PAINTING_VARIANTS.register("tundra", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> DRESS_CODES = PAINTING_VARIANTS.register("dress_codes", () -> new PaintingVariant(32, 32));
}

package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptPaintingVariants {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Windswept.MOD_ID);

    public static final RegistryObject<PaintingVariant> CLIFFSIDE = PAINTING_VARIANTS.register("cliffside", () -> new PaintingVariant(48, 32));
    public static final RegistryObject<PaintingVariant> FREE_FIGURE_STUDY = PAINTING_VARIANTS.register("free_figure_study", () -> new PaintingVariant(48, 32));
    public static final RegistryObject<PaintingVariant> BLOOM = PAINTING_VARIANTS.register("bloom", () -> new PaintingVariant(32, 32));
}

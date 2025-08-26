package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptPaintingVariants {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Windswept.MOD_ID);

    public static final RegistryObject<PaintingVariant> CLIFFSIDE = register("cliffside", 3, 2);
    public static final RegistryObject<PaintingVariant> DRESS_CODES = register("dress_codes", 2, 2);
    public static final RegistryObject<PaintingVariant> ECOTONAL_PAREIDOLIA = register("ecotonal_pareidolia", 4, 2);
    public static final RegistryObject<PaintingVariant> THE_FOILS = register("the_foils", 3, 4);
    public static final RegistryObject<PaintingVariant> AURORAE = register("aurorae", 2, 1);
    public static final RegistryObject<PaintingVariant> HEARTH_RUG = register("hearth_rug", 1, 4);
    public static final RegistryObject<PaintingVariant> ALLU_PINE = register("al-lu_pine", 2, 3);

    private static RegistryObject<PaintingVariant> register(String name, int width, int heght) {
        return PAINTING_VARIANTS.register(name, () -> new PaintingVariant(width * 16, heght * 16));
    }

}

package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WindsweptBannerPatterns {
    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, Windswept.MOD_ID);

    public static final RegistryObject<BannerPattern> SNOW_CHARGE = BANNER_PATTERNS.register("snow_charge", () -> new BannerPattern("wsc"));
    public static final RegistryObject<BannerPattern> SNOW_GOLEM = BANNER_PATTERNS.register("snow_golem", () -> new BannerPattern("wsg"));
    public static final RegistryObject<BannerPattern> ROSE_FLOWER = BANNER_PATTERNS.register("rose_flower", () -> new BannerPattern("wrf"));

}

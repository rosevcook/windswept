package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptPotPatterns {
    public static final DeferredRegister<String> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERNS, Windswept.MOD_ID);

    public static final RegistryObject<String> HOOT = register("hoot");
    public static final RegistryObject<String> PLUMAGE = register("plumage");
    public static final RegistryObject<String> OFFSHOOT = register("offshoot");
    public static final RegistryObject<String> FLAKE = register("flake");
    public static final RegistryObject<String> DRUPES = register("drupes");

    private static RegistryObject<String> register(String name) {
        String register = name + "_pottery_pattern";
        return DECORATED_POT_PATTERNS.register(register, () -> register);
    }

    public static void registerPatterns() {
        DataUtil.registerDecoratedPotPattern(
                Pair.of(WindsweptItems.HOOT_POTTERY_SHERD.get(), HOOT),
                Pair.of(WindsweptItems.PLUMAGE_POTTERY_SHERD.get(), PLUMAGE),
                Pair.of(WindsweptItems.OFFSHOOT_POTTERY_SHERD.get(), OFFSHOOT),
                Pair.of(WindsweptItems.FLAKE_POTTERY_SHERD.get(), FLAKE),
                Pair.of(WindsweptItems.DRUPES_POTTERY_SHERD.get(), DRUPES)
        );
    }

}

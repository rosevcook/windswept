package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import com.teamabnormals.blueprint.core.api.BlueprintRabbitVariants;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID)
public class WindsweptRabbitTypes extends BlueprintRabbitVariants {
    public static final BlueprintRabbitVariant CHESTNUT = BlueprintRabbitVariants.register(1227, Windswept.location("chestnut"), context -> {
        Holder<Biome> biome = getBiome(context);
        return biome.is(WindsweptBiomeTags.IS_CHESTNUT_FOREST) || (biome.is(WindsweptBiomeTags.IS_LAVENDER) && context.getFirst().getRandom().nextBoolean());
    });
}

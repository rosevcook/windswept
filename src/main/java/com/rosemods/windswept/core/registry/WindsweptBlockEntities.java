package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.block_entity.NightFairyLightBlockEntity;
import com.rosemods.windswept.common.block_entity.SuspiciousSnowBlockEntity;
import com.rosemods.windswept.common.block_entity.WillOTheWispBlockEntity;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.BrushableBlockRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptBlockEntities {
    public static final BlockEntitySubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final RegistryObject<BlockEntityType<WillOTheWispBlockEntity>> WILL_O_THE_WISP = HELPER.createBlockEntity("will_o_the_wisp", WillOTheWispBlockEntity::new, () -> Set.of(WindsweptBlocks.WILL_O_THE_WISP.get()));
    public static final RegistryObject<BlockEntityType<NightFairyLightBlockEntity>> NIGHT_FAIRY_LIGHT = HELPER.createBlockEntity("night_fairy_light", NightFairyLightBlockEntity::new, () -> Set.of(WindsweptBlocks.NIGHT_FAIRY_LIGHT.get()));
    public static final RegistryObject<BlockEntityType<SuspiciousSnowBlockEntity>> SUSPICIOUS_SNOW = HELPER.createBlockEntity("suspicious_snow", SuspiciousSnowBlockEntity::new, () -> Set.of(WindsweptBlocks.SUSPICIOUS_SNOW.get()));

    @OnlyIn(Dist.CLIENT)
    public static void registerClient() {
        BlockEntityRenderers.register(SUSPICIOUS_SNOW.get(), BrushableBlockRenderer::new);
    }

}


package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.block.WillOTheWispBlock;
import com.rosemods.windswept.common.block_entity.WillOTheWispBlockEntity;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptBlockEntities {
    public static final BlockEntitySubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final RegistryObject<BlockEntityType<WillOTheWispBlockEntity>> WILL_O_THE_WISP = HELPER.createBlockEntity("will_o_the_wisp", WillOTheWispBlockEntity::new, () -> BlockEntitySubRegistryHelper.collectBlocks(WillOTheWispBlock.class));
}


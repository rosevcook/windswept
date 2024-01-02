package com.rosemods.windswept.common.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.level.block.Block;

public class WearableBlockItem extends BlockItem implements Wearable {
    public WearableBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }

}

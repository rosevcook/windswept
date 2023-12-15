package com.rosemods.windswept.common.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.level.block.Block;

public class CarvedPumpkinBlockItem extends BlockItem implements Wearable {
    public CarvedPumpkinBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }
}

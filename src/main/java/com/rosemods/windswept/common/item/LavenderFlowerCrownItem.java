package com.rosemods.windswept.common.item;

import com.rosemods.windswept.core.other.WindsweptTiers;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class LavenderFlowerCrownItem extends ArmorItem {
    public LavenderFlowerCrownItem(Properties properties) {
        super(WindsweptTiers.LAVENDER_FLOWER_CROWN, EquipmentSlot.HEAD, properties);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
        SnowBootsItem.FILLER.fillItem(this, tab, items);
    }

}

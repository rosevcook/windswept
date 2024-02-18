package com.rosemods.windswept.common.item;

import com.rosemods.windswept.core.other.WindsweptTiers;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FeatherCloakItem extends ArmorItem {
    public FeatherCloakItem(Properties properties) {
        super(WindsweptTiers.FEATHER_CLOAK, EquipmentSlot.CHEST, properties);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
        SnowBootsItem.FILLER.fillItem(this, tab, items);
    }
}

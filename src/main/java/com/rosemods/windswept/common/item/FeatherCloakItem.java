package com.rosemods.windswept.common.item;

import com.rosemods.windswept.core.other.WindsweptTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class FeatherCloakItem extends ArmorItem {
    public FeatherCloakItem(Properties properties) {
        super(WindsweptTiers.FEATHER_CLOAK, Type.CHESTPLATE, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    }

}

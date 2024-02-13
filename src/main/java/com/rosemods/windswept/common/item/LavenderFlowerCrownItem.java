package com.rosemods.windswept.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rosemods.windswept.core.other.WindsweptTiers;
import com.rosemods.windswept.core.registry.WindsweptAttributes;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class LavenderFlowerCrownItem extends ArmorItem {
    private static final UUID FRAGRANCE_UUID = UUID.fromString("1e2757d5-d814-4465-a958-36a6cdeeb624");

    public LavenderFlowerCrownItem(Properties properties) {
        super(WindsweptTiers.LAVENDER_CROWN, EquipmentSlot.HEAD, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        if (this.slot == slot)
            builder.put(WindsweptAttributes.FRAGRANCE.get(), new AttributeModifier(FRAGRANCE_UUID, "Fragrance modifier", 1, AttributeModifier.Operation.ADDITION));

        return builder.build();

    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
        SnowBootsItem.FILLER.fillItem(this, tab, items);
    }

}

package com.rosemods.windswept.core.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SolidBucketItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SolidBucketItem.class)
public class SolidBucketItemMixin extends Item {

    protected SolidBucketItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return !this.hasCraftingRemainingItem() ? Items.BUCKET.getDefaultInstance() : super.getCraftingRemainingItem(itemStack);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

}

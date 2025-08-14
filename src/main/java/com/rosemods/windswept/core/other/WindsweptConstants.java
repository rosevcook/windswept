package com.rosemods.windswept.core.other;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public final class WindsweptConstants {
    public static final ResourceLocation MOSSY_COBBLESTONE_BRICK_WALL = new ResourceLocation("caverns_and_chasms", "mossy_cobblestone_brick_wall");
    public static final ResourceLocation MOSSY_COBBLESTONE_TILE_WALL = new ResourceLocation("caverns_and_chasms", "mossy_cobblestone_tile_wall");

    public static Item getItem(String modid, String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(modid, path));
    }

    public static Block getBlock(String modid, String path) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modid, path));
    }

}

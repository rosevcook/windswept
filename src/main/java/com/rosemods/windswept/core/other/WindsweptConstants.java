package com.rosemods.windswept.core.other;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public final class WindsweptConstants {
    public static final ResourceLocation BAMBOO_LADDER = new ResourceLocation("woodworks", "bamboo_ladder");
    public static final ResourceLocation BAMBOO_BEEHIVE = new ResourceLocation("woodworks", "bamboo_beehive");
    public static final ResourceLocation BAMBOO_BOOKSHELF = new ResourceLocation("woodworks", "bamboo_bookshelf");
    public static final ResourceLocation BAMBOO_CLOSET = new ResourceLocation("woodworks", "bamboo_closet");
    public static final ResourceLocation TRAPPED_BAMBOO_CLOSET = new ResourceLocation("woodworks", "trapped_bamboo_closet");
    public static final ResourceLocation FOUL_BERRIES = new ResourceLocation("autumnity", "foul_berries");
    public static final ResourceLocation MOSSY_COBBLESTONE_BRICK_WALL = new ResourceLocation("caverns_and_chasms", "mossy_cobblestone_brick_wall");
    public static final ResourceLocation MOSSY_COBBLESTONE_TILE_WALL = new ResourceLocation("caverns_and_chasms", "mossy_cobblestone_tile_wall");


    public static Item getItem(String modid, String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(modid, path));
    }

    public static Block getBlock(String modid, String path) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modid, path));
    }

}

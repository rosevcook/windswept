package com.rosemods.windswept.core.other;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public final class WindsweptConstants {
    public static final boolean IN_DEV = false;
    public static final String QUARK = modId("quark");
    public static final String BERRY_GOOD = modId("berry_good");
    public static final String WOODWORKS = modId("woodworks");
    public static final String BOATLOAD = modId("boatload");
    public static final String NEAPOLITAN = modId("neapolitan");
    public static final String FARMERSDELIGHT = modId("farmersdelight");
    public static final String AUTUMNITY = modId("autumnity");
    public static final String CAVERNS_AND_CHASMS = modId("caverns_and_chasms");
    public static final String ENDERGETIC = modId("endergetic");
    public static final String ENVIRONMENTAL = modId("environmental");

    public static Item getItem(String modid, String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(modid, path));
    }

    public static Block getBlock(String modid, String path) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modid, path));
    }

    private static String modId(String modId) {
        return IN_DEV ? "minecraft" : modId;
    }

}

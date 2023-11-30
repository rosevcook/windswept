package com.rosemods.windswept.core.other;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class WindsweptConstants {
    public static final boolean IN_DEV = true;
    public static final String QUARK = modId("quark");
    public static final String BERRY_GOOD = modId("berry_good");
    public static final String WOODWORKS = modId("woodworks");
    public static final String BOATLOAD = modId("boatload");
    public static final String NEAPOLITAN = modId("neapolitan");
    public static final String FARMERSDELIGHT = modId("farmersdelight");
    public static final String AUTUMNITY = modId("autumnity");
    public static final String CAVERNS_AND_CHASMS = modId("caverns_and_chasms");

    public static final Supplier<Item> FOUL_BERRIES = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("autumnity", "foul_berries"));
    public static final Supplier<Item> SILVER_SWORD = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("caverns_and_chasms", "silver_sword"));
    public static final Supplier<Item> SILVER_SHOVEL = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("caverns_and_chasms", "silver_shovel"));
    public static final Supplier<Item> SILVER_AXE = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("caverns_and_chasms", "silver_axe"));
    public static final Supplier<Item> SILVER_HELMET = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("caverns_and_chasms", "silver_helmet"));
    public static final Supplier<Item> SILVER_CHESTPLATE = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("caverns_and_chasms", "silver_chestplate"));
    public static final Supplier<Item> SILVER_LEGGINGS = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("caverns_and_chasms", "silver_leggings"));
    public static final Supplier<Item> SILVER_BOOTS = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation("caverns_and_chasms", "silver_boots"));

    private static String modId(String modId) {
        return IN_DEV ? "minecraft" : modId;
    }
}

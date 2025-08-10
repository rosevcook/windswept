package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public final class WindsweptTrimMaterials {
    public static final ResourceKey<TrimMaterial> ICICLES = createKey("icicles");
    public static final ResourceKey<TrimMaterial> PINECONE = createKey("pinecone");

    public static void bootstrap(BootstapContext<TrimMaterial> context) {
        register(context, ICICLES, WindsweptBlocks.ICICLES.get().asItem(), Style.EMPTY.withColor(0x6d91d7), Map.of());
        register(context, PINECONE, WindsweptBlocks.PINECONE.get().asItem(), Style.EMPTY.withColor(0x7c5741), Map.of());
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Windswept.location(name));
    }

    private static void register(BootstapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Item item, Style style, Map<ArmorMaterials, String> overrides) {
        ResourceLocation location = key.location();
        context.register(key, new TrimMaterial(location.getNamespace() + "_" + location.getPath(), ForgeRegistries.ITEMS.getHolder(item).get(), -1.0F, overrides, Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style)));
    }

}

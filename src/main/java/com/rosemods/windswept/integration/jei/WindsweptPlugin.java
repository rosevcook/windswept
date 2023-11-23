package com.rosemods.windswept.integration.jei;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@JeiPlugin
public class WindsweptPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addInfo(registration, WindsweptItems.MUSIC_DISC_RAIN);
        addInfo(registration, WindsweptItems.MUSIC_DISC_SNOW);
        addInfo(registration, WindsweptItems.MUSIC_DISC_BUMBLEBEE);
        addInfo(registration, WindsweptBlocks.RED_ROSE_BUSH);
        addInfo(registration, WindsweptBlocks.PINK_ROSE_BUSH);
        addInfo(registration, WindsweptBlocks.BLUE_ROSE_BUSH);
        addInfo(registration, WindsweptBlocks.WHITE_ROSE_BUSH);
        addInfo(registration, WindsweptBlocks.YELLOW_ROSE_BUSH);
        addInfo(registration, WindsweptBlocks.WITHER_ROSE_BUSH);
        addInfo(registration, WindsweptItems.WILD_BERRIES);
        addInfo(registration, WindsweptBlocks.NIGHTSHADE);
        addInfo(registration, WindsweptBlocks.BLUEBELLS);
        addInfo(registration, WindsweptBlocks.FOXGLOVE);
        addInfo(registration, WindsweptBlocks.SNOWY_SPROUTS);
        addInfo(registration, WindsweptBlocks.RED_ROSE);
        addInfo(registration, WindsweptBlocks.PINK_ROSE);
        addInfo(registration, WindsweptBlocks.BLUE_ROSE);
        addInfo(registration, WindsweptBlocks.WHITE_ROSE);
        addInfo(registration, WindsweptBlocks.YELLOW_ROSE);
        addInfo(registration, WindsweptItems.WOODEN_BUCKET);
        addInfo(registration, WindsweptItems.SNOW_BOOTS);
        addInfo(registration, WindsweptItems.WILD_BERRY_BOWL);
        addInfo(registration, WindsweptItems.SWEET_BERRY_BOWL);
        addInfo(registration, WindsweptItems.FOUL_BERRY_BOWL);
    }

    private static void addInfo(IRecipeRegistration registration, RegistryObject<? extends ItemLike> item) {
        registration.addIngredientInfo(new ItemStack(item.get()), VanillaTypes.ITEM_STACK, Component.translatable(getDesc(item)));
    }

    public static String getDesc(Supplier<? extends ItemLike> item) {
        return Windswept.MODID + ".jei.info." + ForgeRegistries.ITEMS.getKey(item.get().asItem()).getPath();
    }

    @Override
    public ResourceLocation getPluginUid() {
        return Windswept.REGISTRY_HELPER.prefix("jei_plugin");
    }

}

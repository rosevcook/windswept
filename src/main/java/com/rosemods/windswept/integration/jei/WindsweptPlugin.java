package com.rosemods.windswept.integration.jei;

import java.util.function.Supplier;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

@JeiPlugin
public class WindsweptPlugin implements IModPlugin {

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		addInfo(registration,
				// music discs
				WindsweptItems.MUSIC_DISC_RAIN.get(),
				WindsweptItems.MUSIC_DISC_SNOW.get(),

				// rose bushes
				Blocks.ROSE_BUSH,
				WindsweptBlocks.PINK_ROSE_BUSH.get(),
				WindsweptBlocks.BLUE_ROSE_BUSH.get(),
				WindsweptBlocks.WHITE_ROSE_BUSH.get(),
				WindsweptBlocks.YELLOW_ROSE_BUSH.get(),
				WindsweptBlocks.WITHER_ROSE_BUSH.get(),

				// plants
				WindsweptItems.WILD_BERRIES.get(),
				WindsweptBlocks.NIGHTSHADE.get(),
				WindsweptBlocks.BLUEBELLS.get(),
				WindsweptBlocks.FOXGLOVE.get(),
				WindsweptBlocks.SNOWY_SPROUTS.get(),

				WindsweptBlocks.RED_ROSE.get(),
				WindsweptBlocks.PINK_ROSE.get(),
				WindsweptBlocks.BLUE_ROSE.get(),
				WindsweptBlocks.WHITE_ROSE.get(),
				WindsweptBlocks.YELLOW_ROSE.get(),

				// misc
				WindsweptItems.WOODEN_BUCKET.get(),
				WindsweptItems.SNOW_BOOTS.get(),
				WindsweptItems.WILD_BERRY_BOWL.get(),
				WindsweptItems.SWEET_BERRY_BOWL.get()
		);
	}
	
	private static void addInfo(IRecipeRegistration registration, ItemLike... items) {
		for (ItemLike item : items)
			registration.addIngredientInfo(item.asItem().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable(getDesc(item::asItem)));
	}

	
	public static String getDesc(Supplier<? extends ItemLike> item) {
		return Windswept.MODID + ".jei.info." + ForgeRegistries.ITEMS.getKey(item.get().asItem()).getPath();
	}
	
	@Override
	public ResourceLocation getPluginUid() {
		return Windswept.REGISTRY_HELPER.prefix("jei_plugin");
	}
	
}

package com.rosemods.windswept.core.other.events;

import java.util.Arrays;
import java.util.List;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.util.DataUtil;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Windswept.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class WindsweptClientEvents {
	private static final List<RegistryObject<Block>> FOLIAGE_COLOR_BLOCKS = Arrays.asList(WindsweptBlocks.CHESTNUT_LEAVES, WindsweptBlocks.CHESTNUT_LEAF_CARPET, WindsweptBlocks.CHESTNUT_HEDGE, WindsweptBlocks.CHESTNUT_LEAF_PILE);
	
	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		ItemColors colors = event.getItemColors();
		event.register((s, c) -> c > 0 ? -1 : ((DyeableLeatherItem) s.getItem()).getColor(s), WindsweptItems.SNOW_BOOTS.get());
		DataUtil.registerBlockItemColor(colors, (stack, c) -> event.getBlockColors().getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, c), FOLIAGE_COLOR_BLOCKS);
	}
	
	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		DataUtil.registerBlockColor(event.getBlockColors(), (state, tint, pos, u) -> pos != null && tint != null ? BiomeColors.getAverageFoliageColor(tint, pos) : FoliageColor.getDefaultColor(), FOLIAGE_COLOR_BLOCKS);
	}
		
}

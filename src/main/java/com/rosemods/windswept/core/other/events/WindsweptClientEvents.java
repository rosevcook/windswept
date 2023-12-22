package com.rosemods.windswept.core.other.events;

import com.rosemods.windswept.client.render.gui.CarvedPineconeOverlay;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptModelLayers;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;

@EventBusSubscriber(modid = Windswept.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class WindsweptClientEvents {
    private static final List<RegistryObject<Block>> FOLIAGE_COLOR_BLOCKS = Arrays.asList(WindsweptBlocks.CHESTNUT_LEAVES, WindsweptBlocks.CHESTNUT_LEAF_CARPET, WindsweptBlocks.CHESTNUT_HEDGE, WindsweptBlocks.CHESTNUT_LEAF_PILE);

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((s, c) -> c > 0 ? -1 : ((DyeableLeatherItem) s.getItem()).getColor(s), WindsweptItems.SNOW_BOOTS.get());
        DataUtil.registerBlockItemColor(event.getItemColors(), (stack, c) -> event.getBlockColors().getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, c), FOLIAGE_COLOR_BLOCKS);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        DataUtil.registerBlockColor(event.getBlockColors(), (state, tint, pos, u) -> pos != null && tint != null ? BiomeColors.getAverageFoliageColor(tint, pos) : FoliageColor.getDefaultColor(), FOLIAGE_COLOR_BLOCKS);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WindsweptModelLayers.CHILLED, WindsweptModelLayers::createChilledBodyLayer);
        event.registerLayerDefinition(WindsweptModelLayers.FROSTBITER, WindsweptModelLayers::createFrostbiterBodyLayer);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        MinecraftForge.EVENT_BUS.register(new CarvedPineconeOverlay());
        event.registerAbove(VanillaGuiOverlay.FROSTBITE.id(), "carved_pinecone", new CarvedPineconeOverlay());
    }

}

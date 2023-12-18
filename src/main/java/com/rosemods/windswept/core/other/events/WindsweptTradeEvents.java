package com.rosemods.windswept.core.other.events;

import com.google.common.collect.ImmutableMap;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.util.TradeUtil;
import com.teamabnormals.blueprint.core.util.TradeUtil.BlueprintTrade;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.HashMap;

@EventBusSubscriber(modid = Windswept.MOD_ID)
public class WindsweptTradeEvents {

    @SubscribeEvent
    public static void wandererTrades(WandererTradesEvent event) {
        TradeUtil.addWandererTrades(event,
                new BlueprintTrade(5, WindsweptBlocks.HOLLY_SAPLING.get().asItem(), 1, 8, 1),
                new BlueprintTrade(5, WindsweptBlocks.CHESTNUT_SAPLING.get().asItem(), 1, 8, 1),
                new BlueprintTrade(5, WindsweptBlocks.PINE_SAPLING.get().asItem(), 1, 8, 1),
                new BlueprintTrade(1, WindsweptItems.WILD_BERRIES.get(), 4, 12, 10),
                new BlueprintTrade(1, WindsweptItems.HOLLY_BERRIES.get(), 1, 4, 10),
                new BlueprintTrade(1, WindsweptBlocks.PINECONE.get().asItem(), 1, 4, 10),
                new BlueprintTrade(1, WindsweptItems.ROASTED_CHESTNUTS.get(), 1, 4, 10));
    }

    @SubscribeEvent
    public static void villagerTrades(VillagerTradesEvent event) {
        TradeUtil.addVillagerTrades(event, VillagerProfession.FARMER, TradeUtil.APPRENTICE, new BlueprintTrade(1, WindsweptItems.WILD_BERRIES.get(), 4, 12, 10));
        TradeUtil.addVillagerTrades(event, VillagerProfession.BUTCHER, TradeUtil.JOURNEYMAN, new BlueprintTrade(WindsweptItems.GOAT_STEW.get(), 12, 1, 4, 20), new BlueprintTrade(1, WindsweptItems.COOKED_GOAT.get(), 5, 16, 10));
        TradeUtil.addVillagerTrades(event, VillagerProfession.BUTCHER, TradeUtil.NOVICE, new BlueprintTrade(WindsweptItems.GOAT.get(), 12, 1, 16, 20));
        TradeUtil.addVillagerTrades(event, VillagerProfession.LEATHERWORKER, TradeUtil.EXPERT, new BlueprintTrade(WindsweptItems.SNOW_BOOTS.get(), 1, 5, 1, 20));

        if (event.getType().equals(VillagerProfession.FISHERMAN))
            for (VillagerTrades.ItemListing listing : event.getTrades().get(5))
                if (listing instanceof VillagerTrades.EmeraldsForVillagerTypeItem trade) {
                    HashMap<VillagerType, Item> newTrades = new HashMap<>(trade.trades);
                    VillagerType ice = Registry.VILLAGER_TYPE.get(Windswept.location("ice"));

                    if (!trade.trades.containsKey(ice))
                        newTrades.put(ice, WindsweptItems.HOLLY_BOATS.getFirst().get());

                    if (newTrades.get(VillagerType.SNOW) == Items.SPRUCE_BOAT)
                        newTrades.replace(VillagerType.SNOW, WindsweptItems.CHESTNUT_BOATS.getFirst().get());

                    trade.trades = ImmutableMap.copyOf(newTrades);
                }
    }

}

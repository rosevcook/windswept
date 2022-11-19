package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.item.*;
import com.rosemods.windswept.common.item.wooden_bucket.*;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.tags.WindsweptBannerPatternTags;
import com.rosemods.windswept.integration.boatload.WindsweptBoatTypes;
import com.rosemods.windswept.integration.farmers_delight.WindsweptFDCompat;
import com.teamabnormals.blueprint.common.item.BlueprintBannerPatternItem;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.common.item.InjectedItem;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Windswept.MODID, bus = Bus.MOD)
public class WindsweptItems {
	public static final ItemSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getItemSubHelper();
	
	// Wooden Buckets //
			
	public static final RegistryObject<Item> WOODEN_BUCKET = HELPER.createItem("wooden_bucket", () -> new WoodenBucketItem(() -> Fluids.EMPTY, new Item.Properties().durability(24).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> WOODEN_WATER_BUCKET = HELPER.createItem("wooden_water_bucket", () -> new WoodenBucketItem(() -> Fluids.WATER, new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> WOODEN_MILK_BUCKET = HELPER.createItem("wooden_milk_bucket", () -> new WoodenMilkBucketItem(new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> WOODEN_POWDER_SNOW_BUCKET = HELPER.createItem("wooden_powder_snow_bucket", () -> new WoodenPowderSnowBucketItem(new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get()).tab(CreativeModeTab.TAB_MISC)));
	
	// Food //
	
	public static final RegistryObject<Item> WILD_BERRIES = HELPER.createItem("wild_berries", () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH.get(), PropertyUtil.food(Foods.WILD_BERRIES)));
	public static final RegistryObject<Item> WILD_BERRY_JUICE = HELPER.createItem("wild_berry_juice", () -> new DrinkableBottleItem(Foods.WILD_BERRY_JUICE));
	public static final RegistryObject<Item> WILD_BERRY_BOWL = HELPER.createItem("wild_berry_bowl", () -> new BerryBowlItem(WILD_BERRIES.get(), Foods.BERRY_BOWL));
	public static final RegistryObject<Item> SWEET_BERRY_BOWL = HELPER.createItem("sweet_berry_bowl", () -> new BerryBowlItem(Items.SWEET_BERRIES, Foods.BERRY_BOWL));
	public static final RegistryObject<Item> MUTTON_PIE = HELPER.createItem("mutton_pie", () -> new InjectedItem(Items.COOKED_MUTTON, PropertyUtil.food(Foods.MUTTON_PIE)));
	public static final RegistryObject<Item> GOAT = HELPER.createItem("goat", () -> new Item( PropertyUtil.food(Foods.GOAT)));
	public static final RegistryObject<Item> COOKED_GOAT = HELPER.createItem("cooked_goat", () -> new Item(PropertyUtil.food(Foods.COOKED_GOAT)));
	public static final RegistryObject<Item> GOAT_STEW = HELPER.createItem("goat_stew", () -> new BowlFoodItem(PropertyUtil.food(ModList.get().isLoaded(WindsweptConstants.FARMERS_DELIGHT) ? WindsweptFDCompat.GOAT_STEW : Foods.GOAT_STEW).craftRemainder(Items.BOWL).stacksTo(1)));
	public static final RegistryObject<Item> WILD_BERRY_PIPS = HELPER.createItem("wild_berry_pips", () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH_PIPS.get(), new Item.Properties().tab(ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_MISC : null)));
	public static final RegistryObject<Item> CHESTNUTS = HELPER.createItem("chestnuts", () -> new Item(PropertyUtil.food(Foods.CHESTNUTS)));
	public static final RegistryObject<Item> ROASTED_CHESTNUTS = HELPER.createItem("roasted_chestnuts", () -> new Item(PropertyUtil.food(Foods.ROASTED_CHESTNUTS)));
	public static final RegistryObject<Item> GOAT_SHANKS = HELPER.createCompatItem(WindsweptConstants.FARMERS_DELIGHT, "goat_shanks", new Item.Properties().food(Foods.GOAT_SHANKS), CreativeModeTab.TAB_FOOD);
	public static final RegistryObject<Item> COOKED_GOAT_SHANKS = HELPER.createCompatItem(WindsweptConstants.FARMERS_DELIGHT, "cooked_goat_shanks", new Item.Properties().food(Foods.COOKED_GOAT_SHANKS), CreativeModeTab.TAB_FOOD);
	public static final RegistryObject<Item> STINGING_NETTLE_TEA = HELPER.createItem("stinging_nettle_tea", () -> new DrinkableBottleItem(Foods.STINGING_NETTLE_TEA));

	// Misc //
	
	public static final RegistryObject<Item> SNOW_BOOTS = HELPER.createItem("snow_boots", () -> new SnowBootsItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
	public static final RegistryObject<Item> HOLLY_BERRIES = HELPER.createItem("holly_berries", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).food(Foods.HOLLY_BERRIES)));
	public static final RegistryObject<Item> FROZEN_FLESH = HELPER.createItem("frozen_flesh", () -> new InjectedItem(Items.ROTTEN_FLESH, PropertyUtil.food(Foods.FROZEN_FLESH)));
	
	// Banner Patterns //
	
	public static final RegistryObject<Item> SNOW_GOLEM_BANNER_PATTERN = HELPER.createItem("snow_golem_banner_pattern", () -> new BlueprintBannerPatternItem(WindsweptBannerPatternTags.SNOW_GOLEM, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> SNOW_CHARGE_BANNER_PATTERN = HELPER.createItem("snow_charge_banner_pattern", () -> new BlueprintBannerPatternItem(WindsweptBannerPatternTags.SNOW_CHARGE, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	
	// Music Discs //

	public static final RegistryObject<Item> MUSIC_DISC_RAIN = HELPER.createItem("music_disc_rain", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_RAIN::get, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE), 125));
	public static final RegistryObject<Item> MUSIC_DISC_SNOW = HELPER.createItem("music_disc_snow", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_SNOW::get, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE), 125));

	// Spawn Eggs //
	
	public static final RegistryObject<ForgeSpawnEggItem> CHILLED_SPAWN_EGG = HELPER.createSpawnEggItem("chilled", WindsweptEntities.CHILLED::get, 0x98c4df, 0xf1f8f9);

	// Boats //
	
	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> HOLLY_BOAT = HELPER.createBoatAndChestBoatItem("holly", WindsweptBlocks.HOLLY_PLANKS);
    public static final RegistryObject<Item> HOLLY_FURNACE_BOAT = HELPER.createItem("holly_furnace_boat", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BOATLOAD) ? WindsweptBoatTypes.HOLLY_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_HOLLY_BOAT = HELPER.createItem("large_holly_boat", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BOATLOAD) ? WindsweptBoatTypes.LARGE_HOLLY_BOAT : () -> new Item(new Item.Properties()));
	
	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> CHESTNUT_BOAT = HELPER.createBoatAndChestBoatItem("chestnut", WindsweptBlocks.CHESTNUT_PLANKS);
    public static final RegistryObject<Item> CHESTNUT_FURNACE_BOAT = HELPER.createItem("chestnut_furnace_boat", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BOATLOAD) ? WindsweptBoatTypes.CHESTNUT_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_CHESTNUT_BOAT = HELPER.createItem("large_chestnut_boat", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BOATLOAD) ? WindsweptBoatTypes.LARGE_CHESTNUT_BOAT : () -> new Item(new Item.Properties()));
    
	private static class Foods {
		public static final FoodProperties BERRY_BOWL = new FoodProperties.Builder().nutrition(6).saturationMod(.3f).build();
		public static final FoodProperties WILD_BERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(.1f).build();
		public static final FoodProperties WILD_BERRY_JUICE = new FoodProperties.Builder().nutrition(4).alwaysEat().saturationMod(.1f).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1f).build();
		public static final FoodProperties HOLLY_BERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(.4f).effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 0), 1f).build();
		public static final FoodProperties MUTTON_PIE = new FoodProperties.Builder().nutrition(8).saturationMod(.3f).build();
		public static final FoodProperties GOAT = new FoodProperties.Builder().nutrition(2).saturationMod(.3f).meat().build();
		public static final FoodProperties COOKED_GOAT = new FoodProperties.Builder().nutrition(6).saturationMod(.8f).meat().build();
		public static final FoodProperties GOAT_STEW = new FoodProperties.Builder().nutrition(9).saturationMod(.9f).build();
		public static final FoodProperties FROZEN_FLESH = new FoodProperties.Builder().nutrition(4).saturationMod(.1f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), .8f).meat().build();
		public static final FoodProperties CHESTNUTS = new FoodProperties.Builder().nutrition(2).saturationMod(.1f).build();
		public static final FoodProperties ROASTED_CHESTNUTS = new FoodProperties.Builder().nutrition(5).saturationMod(.4f).build();
		public static final FoodProperties GOAT_SHANKS = new FoodProperties.Builder().nutrition(1).saturationMod(.1f).meat().build();
		public static final FoodProperties COOKED_GOAT_SHANKS = new FoodProperties.Builder().nutrition(3).saturationMod(.3f).meat().build();
		public static final FoodProperties STINGING_NETTLE_TEA = new FoodProperties.Builder().nutrition(4).alwaysEat().saturationMod(.1f).effect(() -> new MobEffectInstance(MobEffects.LUCK, 300, 0), 1f).build();

	}
	
}

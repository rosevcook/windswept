package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.item.*;
import com.rosemods.windswept.common.item.wooden_bucket.*;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.WindsweptFoods;
import com.rosemods.windswept.core.other.WindsweptProperties;
import com.rosemods.windswept.core.other.tags.WindsweptBannerPatternTags;
import com.rosemods.windswept.integration.boatload.WindsweptBoatTypes;
import com.rosemods.windswept.integration.farmers_delight.WindsweptFDCompat;
import com.teamabnormals.blueprint.common.item.BlueprintBannerPatternItem;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.common.item.InjectedItem;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;

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
	
	public static final RegistryObject<Item> WILD_BERRIES = HELPER.createItem("wild_berries", () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH.get(), PropertyUtil.food(WindsweptFoods.WILD_BERRIES)));
	public static final RegistryObject<Item> WILD_BERRY_JUICE = HELPER.createItem("wild_berry_juice", () -> new DrinkableBottleItem(WindsweptFoods.WILD_BERRY_JUICE));
	public static final RegistryObject<Item> WILD_BERRY_COOKIE = HELPER.createItem("wild_berry_cookie", () -> new Item(PropertyUtil.food(WindsweptFoods.WILD_BERRY_COOKIE)));
	public static final RegistryObject<Item> WILD_BERRY_BOWL = HELPER.createItem("wild_berry_bowl", () -> new BerryBowlItem(WILD_BERRIES.get(), WindsweptFoods.BERRY_BOWL));
	public static final RegistryObject<Item> SWEET_BERRY_BOWL = HELPER.createItem("sweet_berry_bowl", () -> new BerryBowlItem(Items.SWEET_BERRIES, WindsweptFoods.BERRY_BOWL));
	public static final RegistryObject<Item> MUTTON_PIE = HELPER.createItem("mutton_pie", () -> new InjectedItem(Items.COOKED_MUTTON, PropertyUtil.food(WindsweptFoods.MUTTON_PIE)));
	public static final RegistryObject<Item> GOAT = HELPER.createItem("goat", () -> new Item( PropertyUtil.food(WindsweptFoods.GOAT)));
	public static final RegistryObject<Item> COOKED_GOAT = HELPER.createItem("cooked_goat", () -> new Item(PropertyUtil.food(WindsweptFoods.COOKED_GOAT)));
	public static final RegistryObject<Item> GOAT_STEW = HELPER.createItem("goat_stew", () -> new BowlFoodItem(PropertyUtil.food(ModList.get().isLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.GOAT_STEW : WindsweptFoods.GOAT_STEW).craftRemainder(Items.BOWL).stacksTo(1)));
	public static final RegistryObject<Item> WILD_BERRY_PIPS = HELPER.createItem("wild_berry_pips", () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH_PIPS.get(), new Item.Properties().tab(ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BERRY_GOOD) ? CreativeModeTab.TAB_MISC : null)));
	public static final RegistryObject<Item> CHESTNUTS = HELPER.createItem("chestnuts", () -> new Item(PropertyUtil.food(WindsweptFoods.CHESTNUTS)));
	public static final RegistryObject<Item> ROASTED_CHESTNUTS = HELPER.createItem("roasted_chestnuts", () -> new Item(PropertyUtil.food(WindsweptFoods.ROASTED_CHESTNUTS)));
	public static final RegistryObject<Item> CHESTNUT_SOUP = HELPER.createItem("chestnut_soup", () -> new BowlFoodItem(PropertyUtil.food(ModList.get().isLoaded(WindsweptConstants.FARMERSDELIGHT) ? WindsweptFDCompat.CHESTNUT_SOUP : WindsweptFoods.CHESTNUT_SOUP).craftRemainder(Items.BOWL).stacksTo(1)));
	public static final RegistryObject<Item> GOAT_SHANKS = HELPER.createCompatItem(WindsweptConstants.FARMERSDELIGHT, "goat_shanks", new Item.Properties().food(WindsweptFoods.GOAT_SHANKS), CreativeModeTab.TAB_FOOD);
	public static final RegistryObject<Item> COOKED_GOAT_SHANKS = HELPER.createCompatItem(WindsweptConstants.FARMERSDELIGHT, "cooked_goat_shanks", new Item.Properties().food(WindsweptFoods.COOKED_GOAT_SHANKS), CreativeModeTab.TAB_FOOD);
	public static final RegistryObject<Item> CHESTNUT_RISOTTO = HELPER.createItem("chestnut_risotto", () -> new BowlFoodItem((ModList.get().isLoaded(WindsweptConstants.FARMERSDELIGHT) ? PropertyUtil.food(WindsweptFDCompat.CHESTNUT_RISOTTO) : new Item.Properties()).craftRemainder(Items.BOWL).stacksTo(1)));

	// Misc //
	
	public static final RegistryObject<Item> SNOW_BOOTS = HELPER.createItem("snow_boots", () -> new SnowBootsItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
	public static final RegistryObject<Item> HOLLY_BERRIES = HELPER.createItem("holly_berries", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).food(WindsweptFoods.HOLLY_BERRIES)));
	public static final RegistryObject<Item> FROZEN_FLESH = HELPER.createItem("frozen_flesh", () -> new InjectedItem(Items.ROTTEN_FLESH, PropertyUtil.food(WindsweptFoods.FROZEN_FLESH)));
	
	// Banner Patterns //
	
	public static final RegistryObject<Item> SNOW_GOLEM_BANNER_PATTERN = HELPER.createItem("snow_golem_banner_pattern", () -> new BlueprintBannerPatternItem(WindsweptBannerPatternTags.SNOW_GOLEM, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> SNOW_CHARGE_BANNER_PATTERN = HELPER.createItem("snow_charge_banner_pattern", () -> new BlueprintBannerPatternItem(WindsweptBannerPatternTags.SNOW_CHARGE, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> ROSE_FLOWER_BANNER_PATTERN = HELPER.createItem("rose_flower_banner_pattern", () -> new BlueprintBannerPatternItem(WindsweptBannerPatternTags.ROSE_FLOWER, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

	// Music Discs //

	public static final RegistryObject<Item> MUSIC_DISC_RAIN = HELPER.createItem("music_disc_rain", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_RAIN, WindsweptProperties.MUSIC_DISC, 125));
	public static final RegistryObject<Item> MUSIC_DISC_SNOW = HELPER.createItem("music_disc_snow", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_SNOW, WindsweptProperties.MUSIC_DISC, 125));
	public static final RegistryObject<Item> MUSIC_DISC_BUMBLEBEE = HELPER.createItem("music_disc_bumblebee", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_BUMBLEBEE, WindsweptProperties.MUSIC_DISC, 60));

	// Spawn Eggs //
	
	public static final RegistryObject<ForgeSpawnEggItem> CHILLED_SPAWN_EGG = HELPER.createSpawnEggItem("chilled", WindsweptEntities.CHILLED::get, 0x98c4df, 0xf1f8f9);

	// Boats //
	
	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> HOLLY_BOAT = HELPER.createBoatAndChestBoatItem("holly", WindsweptBlocks.HOLLY_PLANKS);
    public static final RegistryObject<Item> HOLLY_FURNACE_BOAT = HELPER.createItem("holly_furnace_boat", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BOATLOAD) ? WindsweptBoatTypes.HOLLY_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_HOLLY_BOAT = HELPER.createItem("large_holly_boat", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BOATLOAD) ? WindsweptBoatTypes.LARGE_HOLLY_BOAT : () -> new Item(new Item.Properties()));
	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> CHESTNUT_BOAT = HELPER.createBoatAndChestBoatItem("chestnut", WindsweptBlocks.CHESTNUT_PLANKS);
    public static final RegistryObject<Item> CHESTNUT_FURNACE_BOAT = HELPER.createItem("chestnut_furnace_boat", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BOATLOAD) ? WindsweptBoatTypes.CHESTNUT_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_CHESTNUT_BOAT = HELPER.createItem("large_chestnut_boat", ItemSubRegistryHelper.areModsLoaded(WindsweptConstants.BOATLOAD) ? WindsweptBoatTypes.LARGE_CHESTNUT_BOAT : () -> new Item(new Item.Properties()));
	
}

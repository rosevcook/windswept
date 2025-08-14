package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.item.*;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptFoods;
import com.rosemods.windswept.core.other.tags.WindsweptBannerPatternTags;
import com.rosemods.windswept.integration.boatload.WindsweptBoatTypes;
import com.rosemods.windswept.integration.farmers_delight.WindsweptFDCompat;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptItems {
    public static final ItemSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getItemSubHelper();

    // Misc //
    public static final RegistryObject<Item> HOLLY_BERRIES = HELPER.createItem("holly_berries", () -> new Item(new Item.Properties().food(WindsweptFoods.HOLLY_BERRIES)));
    public static final RegistryObject<Item> HOLLY_BERRIES_ON_A_STICK = HELPER.createItem("holly_berries_on_a_stick", () -> new HollyBerriesOnAStickItem(new Item.Properties().durability(25)));
    //public static final RegistryObject<Item> OWL_FEATHER = HELPER.createItem("owl_feather", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FROST_ARROW = HELPER.createItem("frost_arrow", () -> new FrostArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> FROZEN_BRANCH = HELPER.createItem("frozen_branch", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FROZEN_FLESH = HELPER.createItem("frozen_flesh", () -> new Item(PropertyUtil.food(WindsweptFoods.FROZEN_FLESH)));

    // Armour //
    public static final RegistryObject<Item> LAVENDER_CROWN = HELPER.createItem("lavender_crown", () -> new LavenderCrownItem(new Item.Properties()));
    public static final RegistryObject<Item> ANTLER_HELMET = HELPER.createItem("antler_helmet", () -> new AntlerHelmetItem(new Item.Properties()));
    //public static final RegistryObject<Item> FEATHER_CLOAK = HELPER.createItem("feather_cloak", () -> new FeatherCloakItem(new Item.Properties()));
    public static final RegistryObject<Item> SNOW_BOOTS = HELPER.createItem("snow_boots", () -> new SnowBootsItem(new Item.Properties()));

    // Wooden Buckets //
    public static final RegistryObject<Item> WOODEN_BUCKET = HELPER.createItem("wooden_bucket", () -> new WoodenBucketItem(() -> Fluids.EMPTY, new Item.Properties().durability(24)));
    public static final RegistryObject<Item> WOODEN_WATER_BUCKET = HELPER.createItem("wooden_water_bucket", () -> new WoodenBucketItem(() -> Fluids.WATER, new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final RegistryObject<Item> WOODEN_MILK_BUCKET = HELPER.createItem("wooden_milk_bucket", () -> new WoodenMilkBucketItem(new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final RegistryObject<Item> WOODEN_POWDER_SNOW_BUCKET = HELPER.createItem("wooden_powder_snow_bucket", () -> new WoodenPowderSnowBucketItem(new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));

    // Food //
    public static final RegistryObject<Item> WILD_BERRIES = HELPER.createItem("wild_berries", ItemSubRegistryHelper.areModsLoaded("berry_good") ? () -> new Item(PropertyUtil.food(WindsweptFoods.WILD_BERRIES)) : () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH.get(), PropertyUtil.food(WindsweptFoods.WILD_BERRIES)));
    public static final RegistryObject<Item> WILD_BERRY_PIPS = HELPER.createItem("wild_berry_pips", ItemSubRegistryHelper.areModsLoaded("berry_good") ? () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH.get(), new Item.Properties()) : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WILD_BERRY_JUICE = HELPER.createItem("wild_berry_juice", () -> new DrinkableBottleItem(WindsweptFoods.WILD_BERRY_JUICE));
    public static final RegistryObject<Item> WILD_BERRY_POPSICLE = HELPER.createItem("wild_berry_popsicle", () -> new FoodRemainderItem(() -> Items.STICK, PropertyUtil.food(WindsweptFoods.WILD_BERRY_POPSICLE)));
    public static final RegistryObject<Item> CANDY_CANE = HELPER.createItem("candy_cane", () -> new Item(PropertyUtil.food(WindsweptFoods.CANDY_CANE)));
    public static final RegistryObject<Item> WILD_BERRY_COOKIE = HELPER.createItem("wild_berry_cookie", () -> new Item(PropertyUtil.food(WindsweptFoods.WILD_BERRY_COOKIE)));
    public static final RegistryObject<Item> WILD_BERRY_BOWL = HELPER.createItem("wild_berry_bowl", () -> new BerryBowlItem(WindsweptFoods.BERRY_BOWL));
    public static final RegistryObject<Item> SWEET_BERRY_BOWL = HELPER.createItem("sweet_berry_bowl", () -> new BerryBowlItem(WindsweptFoods.BERRY_BOWL));

    public static final RegistryObject<Item> CHESTNUTS = HELPER.createItem("chestnuts", () -> new Item(PropertyUtil.food(WindsweptFoods.CHESTNUTS)));
    public static final RegistryObject<Item> ROASTED_CHESTNUTS = HELPER.createItem("roasted_chestnuts", () -> new Item(PropertyUtil.food(WindsweptFoods.ROASTED_CHESTNUTS)));
    public static final RegistryObject<Item> CHESTNUT_SOUP = HELPER.createItem("chestnut_soup", () -> new BowlFoodItem(PropertyUtil.food(ItemSubRegistryHelper.areModsLoaded("farmersdelight") ? WindsweptFDCompat.CHESTNUT_SOUP : WindsweptFoods.CHESTNUT_SOUP).craftRemainder(Items.BOWL).stacksTo(1)));
    public static final RegistryObject<Item> CHESTNUT_RISOTTO = HELPER.createItem("chestnut_risotto", () -> new BowlFoodItem((ItemSubRegistryHelper.areModsLoaded("farmersdelight") ? PropertyUtil.food(WindsweptFDCompat.CHESTNUT_RISOTTO) : new Item.Properties()).craftRemainder(Items.BOWL).stacksTo(1)));

    public static final RegistryObject<Item> GINGER_ROOT = HELPER.createItem("ginger_root", () -> new ItemNameBlockItem(WindsweptBlocks.GINGER.get(), PropertyUtil.food(WindsweptFoods.GINGER_ROOT)));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE = HELPER.createItem("gingerbread_cookie", () -> new Item(PropertyUtil.food(WindsweptFoods.GINGERBREAD_COOKIE)));
    public static final RegistryObject<Item> GINGER_SNOW_CONE = HELPER.createItem("ginger_snow_cone", () -> new FoodRemainderItem(() -> WindsweptBlocks.PINECONE.get(), PropertyUtil.food(WindsweptFoods.GINGER_SNOW_CONE)));
    public static final RegistryObject<Item> GINGER_TEA = HELPER.createItem("ginger_tea", () -> new DrinkableBottleItem(WindsweptFoods.GINGER_TEA));

    public static final RegistryObject<Item> PINECONE_JAM_BOTTLE = HELPER.createItem("pinecone_jam_bottle", () -> new DrinkableBottleItem(() -> SoundEvents.HONEY_DRINK, WindsweptFoods.PINECONE_JAM));

    public static final RegistryObject<Item> LAVENDER_SCONES = HELPER.createItem("lavender_scones", () -> new Item(PropertyUtil.food(WindsweptFoods.LAVENDER_SCONES)));
    public static final RegistryObject<Item> LAVENDER_TEA = HELPER.createItem("lavender_tea", () -> new DrinkableBottleItem(WindsweptFoods.LAVENDER_TEA));

    public static final RegistryObject<Item> GOAT = HELPER.createItem("goat", () -> new Item(PropertyUtil.food(WindsweptFoods.GOAT)));
    public static final RegistryObject<Item> COOKED_GOAT = HELPER.createItem("cooked_goat", () -> new Item(PropertyUtil.food(WindsweptFoods.COOKED_GOAT)));
    public static final RegistryObject<Item> GOAT_SHANKS = HELPER.createItem("goat_shanks", () -> new Item(PropertyUtil.food(WindsweptFoods.GOAT_SHANKS)));
    public static final RegistryObject<Item> COOKED_GOAT_SHANKS = HELPER.createItem("cooked_goat_shanks", () -> new Item(PropertyUtil.food(WindsweptFoods.COOKED_GOAT_SHANKS)));
    public static final RegistryObject<Item> GOAT_STEW = HELPER.createItem("goat_stew", () -> new BowlFoodItem(PropertyUtil.food(ItemSubRegistryHelper.areModsLoaded("farmersdelight") ? WindsweptFDCompat.GOAT_STEW : WindsweptFoods.GOAT_STEW).craftRemainder(Items.BOWL).stacksTo(1)));
    public static final RegistryObject<Item> MUTTON_PIE = HELPER.createItem("mutton_pie", () -> new Item(PropertyUtil.food(WindsweptFoods.MUTTON_PIE)));

    public static final RegistryObject<Item> CHRISTMAS_PUDDING_SLICE = HELPER.createItem("christmas_pudding_slice", () -> new Item(new Item.Properties().food(WindsweptFoods.CAKE_SLICE)));

    // Pottery Sherds //
    public static final RegistryObject<Item> HOOT_POTTERY_SHERD = HELPER.createItem("hoot_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLUMAGE_POTTERY_SHERD = HELPER.createItem("plumage_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OFFSHOOT_POTTERY_SHERD = HELPER.createItem("offshoot_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLAKE_POTTERY_SHERD = HELPER.createItem("flake_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DRUPES_POTTERY_SHERD = HELPER.createItem("drupes_pottery_sherd", () -> new Item(new Item.Properties()));

    // Banner Patterns //
    public static final RegistryObject<Item> SNOW_GOLEM_BANNER_PATTERN = HELPER.createItem("snow_golem_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.SNOW_GOLEM, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SNOW_CHARGE_BANNER_PATTERN = HELPER.createItem("snow_charge_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.SNOW_CHARGE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ROSE_FLOWER_BANNER_PATTERN = HELPER.createItem("rose_flower_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.ROSE_FLOWER, new Item.Properties().stacksTo(1)));

    // Music Discs //
    public static final RegistryObject<Item> MUSIC_DISC_RAIN = HELPER.createItem("music_disc_rain", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_RAIN, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 124));
    public static final RegistryObject<Item> MUSIC_DISC_SNOW = HELPER.createItem("music_disc_snow", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_SNOW, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 121));
    public static final RegistryObject<Item> MUSIC_DISC_BUMBLEBEE = HELPER.createItem("music_disc_bumblebee", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_BUMBLEBEE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 55));

    // Spawn Eggs //
    public static final RegistryObject<ForgeSpawnEggItem> CHILLED_SPAWN_EGG = HELPER.createSpawnEggItem("chilled", WindsweptEntityTypes.CHILLED::get, 0x9e9cbe, 0x98654a);
    public static final RegistryObject<ForgeSpawnEggItem> FROSTBITER_SPAWN_EGG = HELPER.createSpawnEggItem("frostbiter", WindsweptEntityTypes.FROSTBITER::get, 0xe2e2e2, 0x8fb5ff);

    // Boats //
    public static final Pair<RegistryObject<Item>, RegistryObject<Item>> HOLLY_BOAT = HELPER.createBoatAndChestBoatItem("holly", WindsweptBlocks.HOLLY_PLANKS);
    public static final RegistryObject<Item> HOLLY_FURNACE_BOAT = HELPER.createItem("holly_furnace_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.HOLLY_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_HOLLY_BOAT = HELPER.createItem("large_holly_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.LARGE_HOLLY_BOAT : () -> new Item(new Item.Properties()));
    public static final Pair<RegistryObject<Item>, RegistryObject<Item>> CHESTNUT_BOAT = HELPER.createBoatAndChestBoatItem("chestnut", WindsweptBlocks.CHESTNUT_PLANKS);
    public static final RegistryObject<Item> CHESTNUT_FURNACE_BOAT = HELPER.createItem("chestnut_furnace_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.CHESTNUT_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_CHESTNUT_BOAT = HELPER.createItem("large_chestnut_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.LARGE_CHESTNUT_BOAT : () -> new Item(new Item.Properties()));
    public static final Pair<RegistryObject<Item>, RegistryObject<Item>> PINE_BOAT = HELPER.createBoatAndChestBoatItem("pine", WindsweptBlocks.PINE_PLANKS);
    public static final RegistryObject<Item> PINE_FURNACE_BOAT = HELPER.createItem("pine_furnace_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.PINE_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_PINE_BOAT = HELPER.createItem("large_pine_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.LARGE_PINE_BOAT : () -> new Item(new Item.Properties()));

}

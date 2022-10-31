package com.rosemods.windswept.core.data.server;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WindsweptRecipeProvider extends RecipeProvider {

	public WindsweptRecipeProvider(GatherDataEvent event) {
		super(event.getGenerator());
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {		
		// wild berry juice
		ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRY_JUICE.get())
			.requires(Items.GLASS_BOTTLE).requires(WindsweptItems.WILD_BERRIES.get(), 3)
			.unlockedBy("has_dune_berries", has(WindsweptItems.WILD_BERRIES.get()))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_juice"));

		// wild berry juice to sugar
		ShapelessRecipeBuilder.shapeless(Items.SUGAR, 3)
			.requires(WindsweptItems.WILD_BERRY_JUICE.get())
			.unlockedBy("has_sugar", has(WindsweptItems.WILD_BERRY_JUICE.get()))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_juice_to_sugar"));
		
		// mutton pie
		ShapelessRecipeBuilder.shapeless(WindsweptItems.MUTTON_PIE.get())
			.requires(Items.COOKED_MUTTON).requires(Items.WHEAT).requires(Items.SUGAR).requires(Items.EGG)
			.unlockedBy("has_cooked_mutton", has(Items.COOKED_MUTTON))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("mutton_pie"));
		
		// goat stew
		ShapelessRecipeBuilder.shapeless(WindsweptItems.GOAT_STEW.get())
			.requires(Items.BOWL).requires(WindsweptItems.COOKED_GOAT.get()).requires(Items.BAKED_POTATO).requires(Items.CARROT).requires(Items.BROWN_MUSHROOM)
			.unlockedBy("has_cooked_goat", has(WindsweptItems.COOKED_GOAT.get()))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("goat_stew"));
		
		// bowl of wild berries
		ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRY_BOWL.get())
			.requires(Items.BOWL).requires(WindsweptItems.WILD_BERRIES.get(), 3)
			.unlockedBy("has_wild_berries", has(WindsweptItems.WILD_BERRIES.get()))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_bowl"));
		
		// bowl of sweet berries
		ShapelessRecipeBuilder.shapeless(WindsweptItems.SWEET_BERRY_BOWL.get())
			.requires(Items.BOWL).requires(Items.SWEET_BERRIES, 3)
			.unlockedBy("has_sweet_berroes", has(Items.SWEET_BERRIES))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("sweet_berry_bowl"));
		
		// bowl of wild berries revert
		ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRIES.get(), 3)
			.requires(WindsweptItems.WILD_BERRY_BOWL.get())
			.unlockedBy("has_wild_berry_bowl", has(WindsweptItems.WILD_BERRY_BOWL.get()))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_bowl_revert"));
		
		// bowl of sweet berries revert
		ShapelessRecipeBuilder.shapeless(Items.SWEET_BERRIES, 3)
			.requires(WindsweptItems.SWEET_BERRY_BOWL.get())
			.unlockedBy("has_wild_berry_bowl", has(WindsweptItems.SWEET_BERRY_BOWL.get()))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("sweet_berry_bowl_revert"));
		
		//cake
	    ShapedRecipeBuilder.shaped(Blocks.CAKE)
	    	.define('A', WindsweptItemTags.MILK).define('B', Items.SUGAR).define('C', Tags.Items.CROPS_WHEAT).define('E', Tags.Items.EGGS)
	    	.pattern("AAA").pattern("BEB").pattern("CCC")
	    	.unlockedBy("has_egg", has(Tags.Items.EGGS))
	    	.save(consumer, Windswept.REGISTRY_HELPER.prefix("cake"));

		
		// roasted chestnuts
		//cooking(WindsweptItems.CHESTNUTS.get(), WindsweptItems.ROASTED_CHESTNUTS.get(), consumer);
				
		// goat
		cooking(WindsweptItems.GOAT.get(), WindsweptItems.COOKED_GOAT.get(), consumer);
				
		// red rose
		flowerToDye(WindsweptBlocks.RED_ROSE, Items.RED_DYE, consumer);
		
		// pink rose
		flowerToDye(WindsweptBlocks.PINK_ROSE, Items.PINK_DYE, consumer);
		
		// blue rose
		flowerToDye(WindsweptBlocks.BLUE_ROSE, Items.BLUE_DYE, consumer);
		
		// white rose
		flowerToDye(WindsweptBlocks.WHITE_ROSE, Items.WHITE_DYE, consumer);
		
		// yellow rose
		flowerToDye(WindsweptBlocks.YELLOW_ROSE, Items.YELLOW_DYE, consumer);
		
		// foxglove
		flowerToDye(WindsweptBlocks.FOXGLOVE, Items.PINK_DYE, consumer);
		
		// bluebell
		flowerToDye(WindsweptBlocks.BLUEBELLS, Items.BLUE_DYE, consumer);
		
		// nighshade
		flowerToDye(WindsweptBlocks.NIGHTSHADE, Items.LIGHT_BLUE_DYE, consumer);
				
		// snow charge banner pattern
		ShapelessRecipeBuilder.shapeless(WindsweptItems.SNOW_CHARGE_BANNER_PATTERN.get())
			.requires(Items.PAPER).requires(BlueprintItemTags.BUCKETS_POWDER_SNOW)
			.unlockedBy("has_powder_snow_bucket", has(BlueprintItemTags.BUCKETS_POWDER_SNOW))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("snow_charge_banner_pattern"));
		
		// snow golem banner pattern
		ShapelessRecipeBuilder.shapeless(WindsweptItems.SNOW_GOLEM_BANNER_PATTERN.get())
			.requires(Items.PAPER).requires(Items.GOLDEN_CARROT)
			.unlockedBy("has_golden_carrot", has(Items.GOLDEN_CARROT))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("snow_golem_banner_pattern"));
		
		// leather from frozen flesh
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(WindsweptItems.FROZEN_FLESH.get()), Items.LEATHER, .35f, 400)
			.unlockedBy("has_frozen_flesh", has(WindsweptItems.FROZEN_FLESH.get()))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("leather_from_frozen_flesh"));
		
		// wooden bucket
		ShapedRecipeBuilder.shaped(WindsweptItems.WOODEN_BUCKET.get())
			.define('#', ItemTags.LOGS)
			.pattern("# #")
			.pattern(" # ")
			.unlockedBy("has_log", has(ItemTags.LOGS))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("wooden_bucket"));
				
		// polished deepslate button
		ShapelessRecipeBuilder.shapeless(WindsweptBlocks.POLISHED_DEEPSLATE_BUTTON.get())
			.requires(Items.POLISHED_DEEPSLATE)
			.unlockedBy("has_polished_deepslate", has(Items.POLISHED_DEEPSLATE))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("polsihed_deepslate_button"));
		
		// polished deepslate pressure plate
		ShapedRecipeBuilder.shaped(WindsweptBlocks.POLISHED_DEEPSLATE_PRESSURE_PLATE.get())
			.define('#', Items.POLISHED_DEEPSLATE)
			.pattern("##")
			.unlockedBy("has_polished_deepslate", has(Items.POLISHED_DEEPSLATE))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("polished_deepslate_pressure_plate"));
		
		// gold door
		ShapedRecipeBuilder.shaped(WindsweptBlocks.GOLDEN_DOOR.get(), 3)
			.define('#', Items.GOLD_INGOT)
			.pattern("##")
			.pattern("##")
			.pattern("##")
			.unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("gold_door"));
		
		// gold trapdoor
		ShapedRecipeBuilder.shaped(WindsweptBlocks.GOLDEN_TRAPDOOR.get())
			.define('#', Items.GOLD_INGOT)
			.pattern("##")
			.pattern("##")
			.unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("gold_trapdoor"));
		
		//ice sheet
		ShapedRecipeBuilder.shaped(WindsweptBlocks.ICE_SHEET.get(), 12)
			.define('#', Items.ICE)
			.pattern("###")
			.pattern("###")
			.unlockedBy("has_ice", has(Items.ICE))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("ice_sheet"));
		
		//snow boots
		ShapedRecipeBuilder.shaped(WindsweptItems.SNOW_BOOTS.get())
			.define('#', Items.IRON_INGOT).define('L', Items.LEATHER)
			.pattern("L L")
			.pattern("L L")
			.pattern("# #")
			.unlockedBy("has_leather", has(Items.LEATHER))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("snow_boots"));
		
		// packed ice bricks set
		brickSet(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_BRICKS, WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS,
				WindsweptBlocks.PACKED_ICE_BRICK_SLAB, WindsweptBlocks.PACKED_ICE_BRICK_STAIRS,
				WindsweptBlocks.PACKED_ICE_BRICK_WALL, consumer);
		
		// blue ice bricks set
		brickSet(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_BRICKS, WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS,
				WindsweptBlocks.BLUE_ICE_BRICK_SLAB, WindsweptBlocks.BLUE_ICE_BRICK_STAIRS,
				WindsweptBlocks.BLUE_ICE_BRICK_WALL, consumer);
		
		// snow bricks set 
		brickSet(Blocks.SNOW_BLOCK, WindsweptBlocks.SNOW_BRICKS, null, WindsweptBlocks.SNOW_BRICK_SLAB,
				WindsweptBlocks.SNOW_BRICK_STAIRS, WindsweptBlocks.SNOW_BRICK_WALL, consumer);
		
		// holly wood set
		woodSet("holly", WindsweptItemTags.HOLLY_LOGS, WindsweptBlocks.HOLLY_PLANKS, WindsweptBlocks.HOLLY_SLAB,
				WindsweptBlocks.HOLLY_STAIRS, WindsweptBlocks.HOLLY_LOG, WindsweptBlocks.HOLLY_WOOD,
				WindsweptBlocks.STRIPPED_HOLLY_LOG, WindsweptBlocks.STRIPPED_HOLLY_WOOD, WindsweptItems.HOLLY_BOAT,
				WindsweptBlocks.HOLLY_BUTTON, WindsweptBlocks.HOLLY_DOOR, WindsweptBlocks.HOLLY_TRAPDOOR,
				WindsweptBlocks.HOLLY_FENCE, WindsweptBlocks.HOLLY_FENCE_GATE, WindsweptBlocks.HOLLY_PRESSURE_PLATE,
				WindsweptBlocks.HOLLY_SIGNS, consumer);
		
		// chestnut wood set
		woodSet("chestnut", WindsweptItemTags.CHESTNUT_LOGS, WindsweptBlocks.CHESTNUT_PLANKS, WindsweptBlocks.CHESTNUT_SLAB,
				WindsweptBlocks.CHESTNUT_STAIRS, WindsweptBlocks.CHESTNUT_LOG, WindsweptBlocks.CHESTNUT_WOOD,
				WindsweptBlocks.STRIPPED_CHESTNUT_LOG, WindsweptBlocks.STRIPPED_CHESTNUT_WOOD, WindsweptItems.CHESTNUT_BOAT,
				WindsweptBlocks.CHESTNUT_BUTTON, WindsweptBlocks.CHESTNUT_DOOR, WindsweptBlocks.CHESTNUT_TRAPDOOR,
				WindsweptBlocks.CHESTNUT_FENCE, WindsweptBlocks.CHESTNUT_FENCE_GATE, WindsweptBlocks.CHESTNUT_PRESSURE_PLATE,
				WindsweptBlocks.CHESTNUT_SIGNS, consumer);
	}
	
	// generates recipes for entire brick sets // minus vertical slabs
	private static void brickSet(ItemLike ingredient, RegistryObject<Block> bricks, @Nullable RegistryObject<Block> chiseled, RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> wall, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(bricks.get(), 4).define('#', ingredient).pattern("##").pattern("##").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, getName(bricks.get()));
		stonecutting(ingredient.asItem(), bricks.get(), 1, consumer);
		stonecutting(bricks.get(), slab.get(), 2, consumer);
		stonecutting(bricks.get(), stairs.get(), 1, consumer);
		stonecutting(bricks.get(), wall.get(), 1, consumer);
		stairs(bricks.get(), stairs.get(), consumer);
		wall(bricks.get(), wall.get(), consumer);
		slab(bricks.get(), slab.get(), consumer);
		
		if (chiseled != null) {
			stonecutting(bricks.get(), chiseled.get(), 1, consumer);
			ShapedRecipeBuilder.shaped(chiseled.get()).define('#', slab.get()).pattern("#").pattern("#").unlockedBy("has_" + getName(bricks.get()), has(bricks.get())).save(consumer, getName(chiseled.get()));
		}

	}
	
	// generates recipes for all items in a wood set // minus compat recipes
	private static void woodSet(String name, TagKey<Item> logs, RegistryObject<Block> planks,
			RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> log,
			RegistryObject<Block> wood, RegistryObject<Block> strippedLog, RegistryObject<Block> strippedWood,
			Pair<RegistryObject<Item>, RegistryObject<Item>> boat, RegistryObject<Block> button, RegistryObject<Block> door,
			RegistryObject<Block> trapdoor, RegistryObject<Block> fence, RegistryObject<Block> fenceGate,
			RegistryObject<Block> pressurePlate,
			Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> signs,
			Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(boat.getSecond().get()).group("chest_boat").requires(Tags.Items.CHESTS_WOODEN).requires(boat.getFirst().get()).unlockedBy("has_" + getName(boat.getFirst().get()), has(boat.getFirst().get())).save(consumer, getRegistryName(boat.getSecond()));
		ShapedRecipeBuilder.shaped(boat.getFirst().get()).group("boat").define('#', planks.get()).pattern("# #").pattern("###").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(boat.getFirst()));
		ShapelessRecipeBuilder.shapeless(button.get()).group("wooden_button").requires(planks.get()).unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(button));
		ShapedRecipeBuilder.shaped(door.get(), 3).group("wooden_door").define('#', planks.get()).pattern("##").pattern("##").pattern("##").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(door));
		ShapedRecipeBuilder.shaped(fence.get(), 3).group("wooden_fence").define('#', planks.get()).define('S', Items.STICK).pattern("#S#").pattern("#S#").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(fence));
		ShapedRecipeBuilder.shaped(fenceGate.get()).group("wooden_fence_gate").define('#', planks.get()).define('S', Items.STICK).pattern("S#S").pattern("S#S").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(fenceGate));
		ShapelessRecipeBuilder.shapeless(planks.get(), 4).group("planks").requires(logs).unlockedBy("has_" + name + "_log", has(logs)).save(consumer, getRegistryName(planks));
		ShapedRecipeBuilder.shaped(pressurePlate.get()).group("wooden_pressure_plate").define('#', planks.get()).pattern("##").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(pressurePlate));
		ShapedRecipeBuilder.shaped(signs.getFirst().get(), 3).group("wooden_sign").define('#', planks.get()).define('S', Items.STICK).pattern("###").pattern("###").pattern(" S ").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(signs.getFirst()));
		ShapedRecipeBuilder.shaped(trapdoor.get(), 2).group("wooden_trapdoor").define('#', planks.get()).pattern("###").pattern("###").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(trapdoor));
		ShapedRecipeBuilder.shaped(wood.get(), 3).group("bark").define('#', log.get()).pattern("##").pattern("##").unlockedBy("has_" + name + "_log", has(log.get())).save(consumer, getRegistryName(wood));
		ShapedRecipeBuilder.shaped(strippedWood.get(), 3).group("bark").define('#', strippedLog.get()).pattern("##").pattern("##").unlockedBy("has_stripped_" + name + "_log", has(strippedLog.get())).save(consumer, getRegistryName(strippedWood));
		slab(planks.get(), slab.get(), "wooden_slab", consumer);
		stairs(planks.get(), stairs.get(), "wooden_stairs", consumer);
	}
		
	private static void flowerToDye(RegistryObject<Block> flower, Item dye, Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(dye)
			.group(getName(dye))
			.requires(flower.get())
			.unlockedBy("has_" + getName(flower.get()), has(flower.get()))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(dye) + "_from_" + getName(flower.get())));
	}
	
	private static void cooking(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
		String path = getName(ingredient);
		String name = getName(result);
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, .35f, 200).unlockedBy("has_" + path, has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(name));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), result, .35f, 600).unlockedBy("has_" + path, has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(name + "_from_campfire_cooking"));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), result, .35f, 100).unlockedBy("has_" + path, has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(name + "_from_smoking"));
	}
	
	private static void stonecutting(ItemLike ingredient, ItemLike result, int amount, Consumer<FinishedRecipe> consumer) {
		String path = getName(ingredient);
		String name = getName(result);
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), result, amount)
			.unlockedBy("has_" + path, has(ingredient))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix(path + "_from_" + name + "_stonecutting"));
	}
	
	private static void stairs(ItemLike ingredient, ItemLike stairs, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(stairs, 4)
			.define('#', ingredient)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.unlockedBy("has_" + getName(ingredient), has(ingredient))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(stairs)));
	}
	
	private static void stairs(ItemLike ingredient, ItemLike stairs, String group, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(stairs, 4)
			.group(group)
			.define('#', ingredient)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.unlockedBy("has_" + getName(ingredient), has(ingredient))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(stairs)));
	}
	
	private static void wall(ItemLike ingredient, ItemLike wall, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(wall, 6)
			.define('#', ingredient)
			.pattern("###")
			.pattern("###")
			.unlockedBy("has_" + getName(ingredient), has(ingredient))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(wall)));
	}
	
	private static void slab(ItemLike ingredient, ItemLike slab, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(slab, 6)
			.define('#', ingredient)
			.pattern("###")
			.unlockedBy("has_" + getName(ingredient), has(ingredient))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(slab)));
	}
	
	private static void slab(ItemLike ingredient, ItemLike slab, String group, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(slab, 6)
			.group(group)
			.define('#', ingredient)
			.pattern("###")
			.unlockedBy("has_" + getName(ingredient), has(ingredient))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(slab)));
	}
	
	private static String getName(ItemLike object) {
		return ForgeRegistries.ITEMS.getKey(object.asItem()).getPath();
	}
	
	private static ResourceLocation getRegistryName(RegistryObject<? extends ItemLike> item) {
		return ForgeRegistries.ITEMS.getKey(item.get().asItem());
	}
	
}


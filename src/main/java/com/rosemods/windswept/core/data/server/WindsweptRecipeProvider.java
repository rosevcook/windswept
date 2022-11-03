package com.rosemods.windswept.core.data.server;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.BlueprintConfig;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.api.conditions.QuarkFlagRecipeCondition;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;

import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
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
		
		// cake
	    ShapedRecipeBuilder.shaped(Blocks.CAKE)
	    	.define('A', WindsweptItemTags.MILK).define('B', Items.SUGAR).define('C', Tags.Items.CROPS_WHEAT).define('E', Tags.Items.EGGS)
	    	.pattern("AAA").pattern("BEB").pattern("CCC")
	    	.unlockedBy("has_egg", has(Tags.Items.EGGS))
	    	.save(consumer, Windswept.REGISTRY_HELPER.prefix("cake"));

		
		// roasted chestnuts
		cooking(WindsweptItems.CHESTNUTS.get(), WindsweptItems.ROASTED_CHESTNUTS.get(), consumer);
				
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
		
		// ice sheet
		ShapedRecipeBuilder.shaped(WindsweptBlocks.ICE_SHEET.get(), 12)
			.define('#', Items.ICE)
			.pattern("###")
			.pattern("###")
			.unlockedBy("has_ice", has(Items.ICE))
			.save(consumer, Windswept.REGISTRY_HELPER.prefix("ice_sheet"));
		
		// snow boots
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
				WindsweptBlocks.PACKED_ICE_BRICK_WALL, WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB, consumer);
		
		// blue ice bricks set
		brickSet(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_BRICKS, WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS,
				WindsweptBlocks.BLUE_ICE_BRICK_SLAB, WindsweptBlocks.BLUE_ICE_BRICK_STAIRS,
				WindsweptBlocks.BLUE_ICE_BRICK_WALL, WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB, consumer);
		
		// snow bricks set 
		brickSet(Blocks.SNOW_BLOCK, WindsweptBlocks.SNOW_BRICKS, null, WindsweptBlocks.SNOW_BRICK_SLAB,
				WindsweptBlocks.SNOW_BRICK_STAIRS, WindsweptBlocks.SNOW_BRICK_WALL, WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB, consumer);
		
		// holly wood set
		woodSet("holly", WindsweptItemTags.HOLLY_LOGS, WindsweptBlocks.HOLLY_PLANKS, WindsweptBlocks.HOLLY_SLAB,
				WindsweptBlocks.HOLLY_STAIRS, WindsweptBlocks.HOLLY_LOG, WindsweptBlocks.HOLLY_WOOD,
				WindsweptBlocks.STRIPPED_HOLLY_LOG, WindsweptBlocks.STRIPPED_HOLLY_WOOD, WindsweptItems.HOLLY_BOAT,
				WindsweptBlocks.HOLLY_BUTTON, WindsweptBlocks.HOLLY_DOOR, WindsweptBlocks.HOLLY_TRAPDOOR,
				WindsweptBlocks.HOLLY_FENCE, WindsweptBlocks.HOLLY_FENCE_GATE, WindsweptBlocks.HOLLY_PRESSURE_PLATE,
				WindsweptBlocks.HOLLY_SIGNS, WindsweptBlocks.HOLLY_VERTICAL_SLAB,
				WindsweptBlocks.HOLLY_POST, WindsweptBlocks.STRIPPED_HOLLY_POST,
				WindsweptBlocks.HOLLY_BOARDS, WindsweptBlocks.HOLLY_BEEHIVE,
				WindsweptBlocks.HOLLY_LADDER, WindsweptBlocks.HOLLY_BOOKSHELF,
				WindsweptBlocks.HOLLY_CHEST, WindsweptBlocks.HOLLY_TRAPPED_CHEST,
				WindsweptItems.LARGE_HOLLY_BOAT, WindsweptItems.HOLLY_FURNACE_BOAT,
				WindsweptBlocks.VERTICAL_HOLLY_PLANKS, consumer);

		// chestnut wood set
		woodSet("chestnut", WindsweptItemTags.CHESTNUT_LOGS, WindsweptBlocks.CHESTNUT_PLANKS, WindsweptBlocks.CHESTNUT_SLAB,
				WindsweptBlocks.CHESTNUT_STAIRS, WindsweptBlocks.CHESTNUT_LOG, WindsweptBlocks.CHESTNUT_WOOD,
				WindsweptBlocks.STRIPPED_CHESTNUT_LOG, WindsweptBlocks.STRIPPED_CHESTNUT_WOOD, WindsweptItems.CHESTNUT_BOAT,
				WindsweptBlocks.CHESTNUT_BUTTON, WindsweptBlocks.CHESTNUT_DOOR, WindsweptBlocks.CHESTNUT_TRAPDOOR,
				WindsweptBlocks.CHESTNUT_FENCE, WindsweptBlocks.CHESTNUT_FENCE_GATE, WindsweptBlocks.CHESTNUT_PRESSURE_PLATE,
				WindsweptBlocks.CHESTNUT_SIGNS, WindsweptBlocks.CHESTNUT_VERTICAL_SLAB,
				WindsweptBlocks.CHESTNUT_POST, WindsweptBlocks.STRIPPED_CHESTNUT_POST,
				WindsweptBlocks.CHESTNUT_BOARDS, WindsweptBlocks.CHESTNUT_BEEHIVE,
				WindsweptBlocks.CHESTNUT_LADDER, WindsweptBlocks.CHESTNUT_BOOKSHELF,
				WindsweptBlocks.CHESTNUT_CHEST, WindsweptBlocks.CHESTNUT_TRAPPED_CHEST,
				WindsweptItems.LARGE_CHESTNUT_BOAT, WindsweptItems.CHESTNUT_FURNACE_BOAT,
				WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS, consumer);

		// holly leaf set
		leafSet(WindsweptBlocks.HOLLY_LOG, WindsweptBlocks.HOLLY_LEAVES, WindsweptBlocks.HOLLY_HEDGE,
				WindsweptBlocks.HOLLY_LEAF_CARPET, WindsweptBlocks.HOLLY_LEAF_PILE, consumer);

		// chestnut leaf set
		leafSet(WindsweptBlocks.CHESTNUT_LOG, WindsweptBlocks.CHESTNUT_LEAVES, WindsweptBlocks.CHESTNUT_HEDGE,
				WindsweptBlocks.CHESTNUT_LEAF_CARPET, WindsweptBlocks.CHESTNUT_LEAF_PILE, consumer);

		// wild berry sack
		conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.WILD_BERRY_SACK.get())
				.define('#', WindsweptItems.WILD_BERRIES.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.unlockedBy("has_berries", has(WindsweptItems.WILD_BERRIES.get())),
				getQuarkCondition("berry_sack"), consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_sack"));

		// wild berry sack revert
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRIES.get(), 9)
				.requires(WindsweptBlocks.WILD_BERRY_SACK.get())
				.unlockedBy("has_wild_berry_sack", has(WindsweptBlocks.WILD_BERRY_SACK.get())),
				getQuarkCondition("berry_sack"), consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_sack_revert"));

		// wild berry pips
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRY_PIPS.get())
				.requires(WindsweptItems.WILD_BERRIES.get())
				.unlockedBy("has_wild_berries", has(WindsweptItems.WILD_BERRIES.get())),
				getModLoaded("berry_good"), consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_pips"));

		// holly berry crate
		conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.HOLLY_BERRY_CRATE.get())
				.define('#', WindsweptItems.HOLLY_BERRIES.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.unlockedBy("has_berries", has(WindsweptItems.HOLLY_BERRIES.get())),
				getQuarkCondition("berry_sack"), consumer, Windswept.REGISTRY_HELPER.prefix("holly_berry_crate"));

		// holly berry crate revert
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.HOLLY_BERRIES.get(), 9)
				.requires(WindsweptBlocks.HOLLY_BERRY_CRATE.get())
				.unlockedBy("has_holly_berry_crate", has(WindsweptBlocks.HOLLY_BERRY_CRATE.get())),
				getQuarkCondition("berry_sack"), consumer, Windswept.REGISTRY_HELPER.prefix("holly_berry_crate_revert"));

		// chestnut crate
		conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.CHESTNUT_CRATE.get())
				.define('#', WindsweptItems.CHESTNUTS.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.unlockedBy("has_chestnuts", has(WindsweptItems.CHESTNUTS.get())),
				getQuarkCondition("apple_crate"), consumer, Windswept.REGISTRY_HELPER.prefix("chestnut_crate"));

		// chestnut crate revert
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.CHESTNUTS.get(), 9)
				.requires(WindsweptBlocks.CHESTNUT_CRATE.get())
				.unlockedBy("has_chestnut_crate", has(WindsweptBlocks.CHESTNUT_CRATE.get())),
				getQuarkCondition("apple_crate"), consumer, Windswept.REGISTRY_HELPER.prefix("chestnut_crate_revert"));

		// roasted chestnut crate
		conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get())
				.define('#', WindsweptItems.ROASTED_CHESTNUTS.get())
				.pattern("###")
				.pattern("###")
				.pattern("###")
				.unlockedBy("has_roasted_chestnuts", has(WindsweptItems.ROASTED_CHESTNUTS.get())),
				getQuarkCondition("apple_crate"), consumer, Windswept.REGISTRY_HELPER.prefix("roasted_chestnut_crate"));

		// roasted chestnut crate revert
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.ROASTED_CHESTNUTS.get(), 9)
				.requires(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get())
				.unlockedBy("has_roasted_chestnut_crate", has(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get())),
				getQuarkCondition("apple_crate"), consumer, Windswept.REGISTRY_HELPER.prefix("roasted_chestnut_crate_revert"));
	}

	// Conditional Recipe Methods //

	private static void conditionalRecipe(RecipeBuilder recipe, ICondition condition, Consumer<FinishedRecipe> consumer, ResourceLocation id) {
		ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	private static QuarkFlagRecipeCondition getQuarkCondition(String flag) {
		return new QuarkFlagRecipeCondition(new ResourceLocation("blueprint", "quark_flag"), flag);
	}

	private static ModLoadedCondition getModLoaded(String modID) {
		return  new ModLoadedCondition(modID);
	}

	private static OrCondition or(ICondition first, ICondition second) {
		return new OrCondition(first, second);
	}

	private static AndCondition and(ICondition first, ICondition second) {
		return new AndCondition(first, second);
	}
	
	// generates recipes for entire brick sets
	private static void brickSet(ItemLike ingredient, RegistryObject<Block> bricks, @Nullable RegistryObject<Block> chiseled, RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> wall, RegistryObject<Block> verticalSlab, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(bricks.get(), 4).define('#', ingredient).pattern("##").pattern("##").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, getName(bricks.get()));
		stonecutting(ingredient.asItem(), bricks.get(), 1, consumer);
		stonecutting(bricks.get(), slab.get(), 2, consumer);
		stonecutting(bricks.get(), stairs.get(), 1, consumer);
		stonecutting(bricks.get(), wall.get(), 1, consumer);
		stairs(bricks.get(), stairs.get(), consumer);
		wall(bricks.get(), wall.get(), consumer);
		slab(bricks.get(), slab.get(), consumer);
		verticalSlab(verticalSlab.get(), slab.get(), consumer);
		conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), verticalSlab.get(), 2).unlockedBy("has_" + getName(ingredient), has(ingredient)), getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(verticalSlab.get()) + "_from_" + getName(ingredient) + "_stonecutting"));
		
		if (chiseled != null) {
			stonecutting(bricks.get(), chiseled.get(), 1, consumer);
			ShapedRecipeBuilder.shaped(chiseled.get()).define('#', slab.get()).pattern("#").pattern("#").unlockedBy("has_" + getName(bricks.get()), has(bricks.get())).save(consumer, getName(chiseled.get()));
		}

	}
	
	// generates recipes for all items in a wood set
	private static void woodSet(String name, TagKey<Item> logs, RegistryObject<Block> planks,
			RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> log,
			RegistryObject<Block> wood, RegistryObject<Block> strippedLog, RegistryObject<Block> strippedWood,
			Pair<RegistryObject<Item>, RegistryObject<Item>> boat, RegistryObject<Block> button, RegistryObject<Block> door,
			RegistryObject<Block> trapdoor, RegistryObject<Block> fence, RegistryObject<Block> fenceGate,
			RegistryObject<Block> pressurePlate,
			Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> signs,
			RegistryObject<Block> verticalSlab, RegistryObject<Block> post, RegistryObject<Block> strippedPost,
			RegistryObject<Block> boards, RegistryObject<Block> beehive, RegistryObject<Block> ladder,
			RegistryObject<Block> bookshelf, RegistryObject<? extends Block> chest, RegistryObject<? extends Block> trappedChest,
			RegistryObject<Item> largeBoat, RegistryObject<Item> furnaceBoat, RegistryObject<Block> verticalPlanks,
			Consumer<FinishedRecipe> consumer) {
		//chest boat
		ShapelessRecipeBuilder.shapeless(boat.getSecond().get())
				.group("chest_boat").
				requires(Tags.Items.CHESTS_WOODEN)
				.requires(boat.getFirst().get())
				.unlockedBy("has_" + getName(boat.getFirst().get()), has(boat.getFirst().get()))
				.save(consumer, getRegistryName(boat.getSecond()));

		//boat
		ShapedRecipeBuilder.shaped(boat.getFirst().get())
				.group("boat").
				define('#', planks.get())
				.pattern("# #")
				.pattern("###")
				.unlockedBy("has_" + name + "_planks", has(planks.get()))
				.save(consumer, getRegistryName(boat.getFirst()));

		//button
		ShapelessRecipeBuilder.shapeless(button.get())
				.group("wooden_button")
				.requires(planks.get())
				.unlockedBy("has_" + name + "_planks", has(planks.get()))
				.save(consumer, getRegistryName(button));

		//door
		ShapedRecipeBuilder.shaped(door.get(), 3)
				.group("wooden_door")
				.define('#', planks.get())
				.pattern("##")
				.pattern("##").
				pattern("##")
				.unlockedBy("has_" + name + "_planks", has(planks.get()))
				.save(consumer, getRegistryName(door));

		//fence
		ShapedRecipeBuilder.shaped(fence.get(), 3)
				.group("wooden_fence")
				.define('#', planks.get())
				.define('S', Items.STICK)
				.pattern("#S#")
				.pattern("#S#")
				.unlockedBy("has_" + name + "_planks", has(planks.get()))
				.save(consumer, getRegistryName(fence));

		//fence gate
		ShapedRecipeBuilder.shaped(fenceGate.get())
				.group("wooden_fence_gate")
				.define('#', planks.get())
				.define('S', Items.STICK)
				.pattern("S#S")
				.pattern("S#S")
				.unlockedBy("has_" + name + "_planks", has(planks.get()))
				.save(consumer, getRegistryName(fenceGate));

		//planks
		ShapelessRecipeBuilder.shapeless(planks.get(), 4)
				.group("planks")
				.requires(logs)
				.unlockedBy("has_" + name + "_log", has(logs))
				.save(consumer, getRegistryName(planks));

		//pressure plate
		ShapedRecipeBuilder.shaped(pressurePlate.get())
				.group("wooden_pressure_plate").
				define('#', planks.get())
				.pattern("##")
				.unlockedBy("has_" + name + "_planks", has(planks.get()))
				.save(consumer, getRegistryName(pressurePlate));

		//sign
		ShapedRecipeBuilder.shaped(signs.getFirst().get(), 3)
				.group("wooden_sign")
				.define('#', planks.get())
				.define('S', Items.STICK)
				.pattern("###")
				.pattern("###")
				.pattern(" S ")
				.unlockedBy("has_" + name + "_planks", has(planks.get()))
				.save(consumer, getRegistryName(signs.getFirst()));

		//trapdoor
		ShapedRecipeBuilder.shaped(trapdoor.get(), 2)
				.group("wooden_trapdoor")
				.define('#', planks.get())
				.pattern("###")
				.pattern("###")
				.unlockedBy("has_" + name + "_planks", has(planks.get()))
				.save(consumer, getRegistryName(trapdoor));

		//wood
		ShapedRecipeBuilder.shaped(wood.get(), 3)
				.group("bark")
				.define('#', log.get())
				.pattern("##")
				.pattern("##")
				.unlockedBy("has_" + name + "_log", has(log.get()))
				.save(consumer, getRegistryName(wood));

		//stripped wood
		ShapedRecipeBuilder.shaped(strippedWood.get(), 3)
				.group("bark")
				.define('#', strippedLog.get())
				.pattern("##")
				.pattern("##")
				.unlockedBy("has_stripped_" + name + "_log", has(strippedLog.get()))
				.save(consumer, getRegistryName(strippedWood));

		//slab
		slab(planks.get(), slab.get(), "wooden_slab", consumer);

		//stairs
		stairs(planks.get(), stairs.get(), "wooden_stairs", consumer);

		//veritcal slab
		verticalSlab(verticalSlab.get(), slab.get(), consumer);

		//post
		post(post.get(), wood.get(), consumer);

		//stripped post
		post(strippedPost.get(), strippedWood.get(), consumer);

		//boards
		conditionalRecipe(ShapedRecipeBuilder.shaped(boards.get(), 3)
				.group("wooden_boards")
				.define('#', planks.get())
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.unlockedBy("has_planks", has(planks.get())),
				getModLoaded("woodworks"), consumer, getRegistryName(boards));

		//beehive
		conditionalRecipe(ShapedRecipeBuilder.shaped(beehive.get())
				.group("wooden_beehive")
				.define('#', planks.get())
				.define('H', Items.HONEYCOMB)
				.pattern("###")
				.pattern("HHH")
				.pattern("###")
				.unlockedBy("has_planks", has(planks.get())),
				getModLoaded("woodworks"), consumer, getRegistryName(beehive));

		//ladder
		conditionalRecipe(ShapedRecipeBuilder.shaped(ladder.get(), 4)
				.group("wooden_ladders")
				.define('#', planks.get())
				.define('S', Items.STICK)
				.pattern("S S")
				.pattern("S#S")
				.pattern("S S")
				.unlockedBy("has_planks", has(planks.get())),
				or(getModLoaded("woodworks"), getQuarkCondition("variant_ladders")), consumer, getRegistryName(ladder));

		//bookshelf
		conditionalRecipe(ShapedRecipeBuilder.shaped(bookshelf.get())
				.group("wooden_bookshelves")
				.define('#', planks.get())
				.define('B', Items.BOOK)
				.pattern("###")
				.pattern("BBB")
				.pattern("###")
				.unlockedBy("has_planks", has(planks.get())),
				or(getModLoaded("woodworks"), getQuarkCondition("variant_bookshelves")), consumer, getRegistryName(bookshelf));

		//chest
		conditionalRecipe(ShapedRecipeBuilder.shaped(chest.get())
				.group("wooden_chests")
				.define('#', planks.get())
				.pattern("###")
				.pattern("# #")
				.pattern("###")
				.unlockedBy("has_planks", has(planks.get())),
				or(getModLoaded("woodworks"), getQuarkCondition("variant_chests")), consumer, getRegistryName(chest));

		//wood chest
		conditionalRecipe(ShapedRecipeBuilder.shaped(chest.get(), 4)
				.group("wooden_chests")
				.define('#', wood.get())
				.pattern("###")
				.pattern("# #")
				.pattern("###")
				.unlockedBy("has_wood", has(wood.get())),
				and(getQuarkCondition("wood_to_chest_recipes"), getQuarkCondition("variant_chests")), consumer, Windswept.REGISTRY_HELPER.prefix(getName(chest.get()) + "_wood"));

		//trapped chest
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(trappedChest.get())
				.requires(chest.get())
				.requires(Items.TRIPWIRE_HOOK)
				.unlockedBy("has_chest", has(chest.get())),
				or(getModLoaded("woodworks"), getQuarkCondition("variant_chests")), consumer, getRegistryName(trappedChest));

		//furnace boat
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(furnaceBoat.get())
				.requires(boat.getFirst().get())
				.requires(Items.FURNACE)
				.unlockedBy("has_boat", has(boat.getFirst().get())),
				getModLoaded("boatload"), consumer, getRegistryName(furnaceBoat));

		//large boat
		conditionalRecipe(ShapedRecipeBuilder.shaped(largeBoat.get())
				.group("wooden_chests")
				.define('#', planks.get())
				.define('B', boat.getFirst().get())
				.pattern("#B#")
				.pattern("###")
				.unlockedBy("has_boat", has(boat.getFirst().get())),
				getModLoaded("boatload"), consumer, getRegistryName(largeBoat));

		//vertical planks
		conditionalRecipe(ShapedRecipeBuilder.shaped(verticalPlanks.get(), 3)
				.define('#', planks.get())
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.unlockedBy("has_planks", has(planks.get())),
				getQuarkCondition("vertical_planks"), consumer, getRegistryName(verticalPlanks));

		//vertical planks revert
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(planks.get())
				.requires(verticalPlanks.get())
				.unlockedBy("has_vertical_planks", has(verticalPlanks.get())),
				getQuarkCondition("vertical_planks"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(verticalPlanks.get()) + "_revert"));
	}

	private static void leafSet(RegistryObject<Block> log, RegistryObject<Block> leaves, RegistryObject<Block> hedge,
			RegistryObject<Block> leafCarpet, RegistryObject<Block> leafPile, Consumer<FinishedRecipe> consumer) {
		//hedge
		conditionalRecipe(ShapedRecipeBuilder.shaped(hedge.get(), 2)
				.define('#', log.get())
				.define('L', leaves.get())
				.pattern("L")
				.pattern("#")
				.unlockedBy("has_leaves", has(leaves.get())),
				getQuarkCondition("hedges"), consumer, getRegistryName(hedge));

		//leaf carpet
		conditionalRecipe(ShapedRecipeBuilder.shaped(leafCarpet.get(), 2)
				.define('#', leaves.get())
				.pattern("##")
				.unlockedBy("has_leaves", has(leaves.get())),
				getQuarkCondition("leaf_carpet"), consumer, getRegistryName(leafCarpet));

		//leaf piles
		conditionalRecipe(ShapelessRecipeBuilder.shapeless(leafPile.get(), 4)
				.requires(leaves.get())
				.unlockedBy("has_leaves", has(leaves.get())),
				getModLoaded("woodworks"), consumer, getRegistryName(leafPile));

		//leaf pile revert
		conditionalRecipe(ShapedRecipeBuilder.shaped(leaves.get())
				.define('#', leafPile.get())
				.pattern("##")
				.pattern("##")
				.unlockedBy("has_leaf_pile", has(leafPile.get())),
				getModLoaded("woodworks"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(leaves.get()) + "_from_leaf_pile"));
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

	private static void verticalSlab(ItemLike verticalSlab, ItemLike slab, Consumer<FinishedRecipe> consumer) {
		conditionalRecipe(ShapedRecipeBuilder.shaped(verticalSlab, 3)
			.define('#', slab)
			.pattern("#").pattern("#").pattern("#")
			.unlockedBy("has_slab", has(slab)),
			getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(verticalSlab)));

		conditionalRecipe(ShapelessRecipeBuilder.shapeless(slab)
			.requires(verticalSlab)
			.unlockedBy("has_vertical_slab", has(verticalSlab)),
			getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(verticalSlab) + "_revert"));
	}

	private static void post(ItemLike post, ItemLike wood, Consumer<FinishedRecipe> consumer) {
		conditionalRecipe(ShapedRecipeBuilder.shaped(post, 8)
			.define('#', wood)
			.pattern("#")
			.pattern("#")
			.pattern("#")
			.unlockedBy("has_wood", has(wood)),
			getQuarkCondition("wooden_posts"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(post)));
	}

	
	private static String getName(ItemLike object) {
		return ForgeRegistries.ITEMS.getKey(object.asItem()).getPath();
	}
	
	private static ResourceLocation getRegistryName(RegistryObject<? extends ItemLike> item) {
		return ForgeRegistries.ITEMS.getKey(item.get().asItem());
	}
	
}


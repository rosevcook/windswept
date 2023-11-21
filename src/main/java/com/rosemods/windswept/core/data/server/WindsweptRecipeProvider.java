package com.rosemods.windswept.core.data.server;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.api.conditions.QuarkFlagRecipeCondition;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.recipes.*;
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
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.*;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class WindsweptRecipeProvider extends RecipeProvider {

    public WindsweptRecipeProvider(GatherDataEvent event) {
        super(event.getGenerator());
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // foods
        ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRY_JUICE.get()).requires(Items.GLASS_BOTTLE).requires(WindsweptItems.WILD_BERRIES.get(), 3).unlockedBy("has_wild_berries", has(WindsweptItems.WILD_BERRIES.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_juice"));
        ShapelessRecipeBuilder.shapeless(Items.SUGAR, 3).requires(WindsweptItems.WILD_BERRY_JUICE.get()).unlockedBy("has_wild_berry_juice", has(WindsweptItems.WILD_BERRY_JUICE.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_juice_to_sugar"));
        ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRY_BOWL.get()).requires(Items.BOWL).requires(WindsweptItems.WILD_BERRIES.get(), 3).unlockedBy("has_wild_berries", has(WindsweptItems.WILD_BERRIES.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_bowl"));
        ShapelessRecipeBuilder.shapeless(WindsweptItems.SWEET_BERRY_BOWL.get()).requires(Items.BOWL).requires(Items.SWEET_BERRIES, 3).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer, Windswept.REGISTRY_HELPER.prefix("sweet_berry_bowl"));
        ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRIES.get(), 3).requires(WindsweptItems.WILD_BERRY_BOWL.get()).unlockedBy("has_wild_berry_bowl", has(WindsweptItems.WILD_BERRY_BOWL.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_bowl_revert"));
        ShapelessRecipeBuilder.shapeless(Items.SWEET_BERRIES, 3).requires(WindsweptItems.SWEET_BERRY_BOWL.get()).unlockedBy("has_sweet_berry_bowl", has(WindsweptItems.SWEET_BERRY_BOWL.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix("sweet_berry_bowl_revert"));
        ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRY_COOKIE.get(), 8).requires(WindsweptItems.WILD_BERRIES.get()).requires(Items.WHEAT, 2).unlockedBy("has_wild_berries", has(WindsweptItems.WILD_BERRIES.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_cookie"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.CHESTNUT_CHICKEN_PLATTER.get()).requires(WindsweptItemTags.COOKED_CHICKEN).requires(WindsweptItems.ROASTED_CHESTNUTS.get(), 2).requires(WindsweptItemTags.COOKED_BACON).requires(Items.BOWL).unlockedBy(getHasName(WindsweptItems.ROASTED_CHESTNUTS.get()), has(WindsweptItems.ROASTED_CHESTNUTS.get())), new ModLoadedCondition("farmersdelight"), consumer, Windswept.REGISTRY_HELPER.prefix("chestnut_chicken_platter"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.CHESTNUT_SOUP.get()).requires(Items.BOWL).requires(WindsweptItems.ROASTED_CHESTNUTS.get(), 2).requires(Items.CARROT).requires(Items.POTATO).unlockedBy("has_roasted_chestnuts", has(WindsweptItems.ROASTED_CHESTNUTS.get())), new NotCondition(new ModLoadedCondition("farmersdelight")), consumer, Windswept.REGISTRY_HELPER.prefix("chestnut_soup"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(Blocks.CAKE).define('A', WindsweptItemTags.MILK).define('B', Items.SUGAR).define('C', Tags.Items.CROPS_WHEAT).define('E', Tags.Items.EGGS).pattern("AAA").pattern("BEB").pattern("CCC").unlockedBy("has_egg", has(Tags.Items.EGGS)), new NotCondition(new ModLoadedCondition("neapolitan")), consumer, Windswept.REGISTRY_HELPER.prefix("cake"));
        cooking(WindsweptItems.CHESTNUTS.get(), WindsweptItems.ROASTED_CHESTNUTS.get(), consumer);
        cooking(WindsweptItems.GOAT.get(), WindsweptItems.COOKED_GOAT.get(), consumer);
        conditionalCooking(WindsweptItems.GOAT_SHANKS.get(), WindsweptItems.COOKED_GOAT_SHANKS.get(), new ModLoadedCondition("farmersdelight"), consumer);
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.GOAT_STEW.get()).requires(Items.BOWL).requires(WindsweptItems.COOKED_GOAT.get()).requires(Items.BAKED_POTATO).requires(Items.CARROT).requires(Items.BROWN_MUSHROOM).unlockedBy("has_cooked_goat", has(WindsweptItems.COOKED_GOAT.get())), new NotCondition(new ModLoadedCondition("farmersdelight")), consumer, Windswept.REGISTRY_HELPER.prefix("goat_stew"));
        ShapelessRecipeBuilder.shapeless(WindsweptItems.MUTTON_PIE.get()).requires(WindsweptItemTags.COOKED_MUTTON).requires(Items.WHEAT).requires(Items.SUGAR).requires(Tags.Items.EGGS).unlockedBy("has_cooked_mutton", has(Items.COOKED_MUTTON)).save(consumer, Windswept.REGISTRY_HELPER.prefix("mutton_pie"));

        // dyes
        flowerToDye(WindsweptBlocks.RED_ROSE, Items.RED_DYE, consumer);
        flowerToDye(WindsweptBlocks.PINK_ROSE, Items.PINK_DYE, consumer);
        flowerToDye(WindsweptBlocks.BLUE_ROSE, Items.BLUE_DYE, consumer);
        flowerToDye(WindsweptBlocks.WHITE_ROSE, Items.WHITE_DYE, consumer);
        flowerToDye(WindsweptBlocks.YELLOW_ROSE, Items.YELLOW_DYE, consumer);
        flowerToDye(WindsweptBlocks.FOXGLOVE, Items.PINK_DYE, consumer);
        flowerToDye(WindsweptBlocks.BLUEBELLS, Items.BLUE_DYE, consumer);
        flowerToDye(WindsweptBlocks.NIGHTSHADE, Items.LIGHT_BLUE_DYE, consumer);
        tallFlowerToDye(WindsweptBlocks.RED_ROSE_BUSH, Items.RED_DYE, consumer);
        tallFlowerToDye(WindsweptBlocks.PINK_ROSE_BUSH, Items.PINK_DYE, consumer);
        tallFlowerToDye(WindsweptBlocks.BLUE_ROSE_BUSH, Items.BLUE_DYE, consumer);
        tallFlowerToDye(WindsweptBlocks.WHITE_ROSE_BUSH, Items.WHITE_DYE, consumer);
        tallFlowerToDye(WindsweptBlocks.YELLOW_ROSE_BUSH, Items.YELLOW_DYE, consumer);
        tallFlowerToDye(WindsweptBlocks.WITHER_ROSE_BUSH, Items.BLACK_DYE, consumer);

        // other items
        ShapelessRecipeBuilder.shapeless(WindsweptItems.SNOW_CHARGE_BANNER_PATTERN.get()).requires(Items.PAPER).requires(BlueprintItemTags.BUCKETS_POWDER_SNOW).unlockedBy("has_powder_snow_bucket", has(BlueprintItemTags.BUCKETS_POWDER_SNOW)).save(consumer, Windswept.REGISTRY_HELPER.prefix("snow_charge_banner_pattern"));
        ShapelessRecipeBuilder.shapeless(WindsweptItems.SNOW_GOLEM_BANNER_PATTERN.get()).requires(Items.PAPER).requires(Items.GOLDEN_CARROT).unlockedBy("has_golden_carrot", has(Items.GOLDEN_CARROT)).save(consumer, Windswept.REGISTRY_HELPER.prefix("snow_golem_banner_pattern"));
        ShapelessRecipeBuilder.shapeless(WindsweptItems.ROSE_FLOWER_BANNER_PATTERN.get()).requires(Items.PAPER).requires(WindsweptItemTags.ROSES).unlockedBy("has_roses", has(WindsweptItemTags.ROSES)).save(consumer, Windswept.REGISTRY_HELPER.prefix("rose_flower_banner_pattern"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(WindsweptItems.FROZEN_FLESH.get()), Items.LEATHER, .35f, 400).unlockedBy("has_frozen_flesh", has(WindsweptItems.FROZEN_FLESH.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix("leather_from_frozen_flesh"));
        ShapedRecipeBuilder.shaped(WindsweptItems.WOODEN_BUCKET.get()).define('#', ItemTags.LOGS).pattern("# #").pattern(" # ").unlockedBy("has_log", has(ItemTags.LOGS)).save(consumer, Windswept.REGISTRY_HELPER.prefix("wooden_bucket"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptItems.SNOW_BOOTS.get()).define('#', Items.IRON_INGOT).define('L', Items.LEATHER).pattern("L L").pattern("L L").pattern("# #").unlockedBy("has_leather", has(Items.LEATHER)), new TagEmptyCondition(WindsweptItemTags.SILVER_INGOT.location()), consumer, Windswept.REGISTRY_HELPER.prefix("snow_boots"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptItems.SNOW_BOOTS.get()).define('#', WindsweptItemTags.SILVER_INGOT).define('L', Items.LEATHER).pattern("L L").pattern("L L").pattern("# #").unlockedBy("has_leather", has(Items.LEATHER)), new NotCondition(new TagEmptyCondition(WindsweptItemTags.SILVER_INGOT.location())), consumer, Windswept.REGISTRY_HELPER.prefix("snow_boots_from_silver"));

        // building blocks
        ShapedRecipeBuilder.shaped(WindsweptBlocks.CUT_ICE.get(), 4).define('#', Items.ICE).pattern("##").pattern("##").unlockedBy("has_ice", has(Items.ICE)).save(consumer, Windswept.REGISTRY_HELPER.prefix("cut_ice"));
        ShapedRecipeBuilder.shaped(WindsweptBlocks.ICE_SHEET.get(), 12).define('#', Items.ICE).pattern("###").pattern("###").unlockedBy("has_ice", has(Items.ICE)).save(consumer, Windswept.REGISTRY_HELPER.prefix("ice_sheet"));
        ShapedRecipeBuilder.shaped(WindsweptBlocks.CUT_ICE_SHEET.get(), 12).define('#', WindsweptBlocks.CUT_ICE.get()).pattern("###").pattern("###").unlockedBy("has_cut_ice", has(WindsweptBlocks.CUT_ICE.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix("cut_ice_sheet"));

        stairs(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_STAIRS.get(), consumer);
        slab(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_SLAB.get(), consumer);
        verticalSlab(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_VERTICAL_SLAB.get(), consumer);
        stonecutting(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_SLAB.get(), 2, consumer);
        stonecutting(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_STAIRS.get(), 1, consumer);
        conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.PACKED_ICE), WindsweptBlocks.PACKED_ICE_VERTICAL_SLAB.get(), 2).unlockedBy("has_packed_ice", has(Blocks.PACKED_ICE)), getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix( "packed_ice_vertical_slab_from_packed_ice_stonecutting"));
        stonecutting(Blocks.PACKED_ICE, WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS.get(), 1, consumer);
        stonecutting(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_BRICK_SLAB.get(), 2, consumer);
        stonecutting(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_BRICK_STAIRS.get(), 1, consumer);
        stonecutting(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_BRICK_WALL.get(), 1, consumer);
        conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.PACKED_ICE), WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB.get(), 2).unlockedBy("has_packed_ice", has(Blocks.PACKED_ICE)), getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix( "packed_ice_brick_vertical_slab_from_packed_ice_stonecutting"));

        stairs(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_STAIRS.get(), consumer);
        slab(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_SLAB.get(), consumer);
        verticalSlab(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_VERTICAL_SLAB.get(), consumer);
        stonecutting(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_SLAB.get(), 2, consumer);
        stonecutting(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_STAIRS.get(), 1, consumer);
        conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.BLUE_ICE), WindsweptBlocks.BLUE_ICE_VERTICAL_SLAB.get(), 2).unlockedBy("has_blue_ice", has(Blocks.BLUE_ICE)), getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix( "blue_ice_vertical_slab_from_blue_ice_stonecutting"));
        stonecutting(Blocks.BLUE_ICE, WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS.get(), 1, consumer);
        stonecutting(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_BRICK_SLAB.get(), 2, consumer);
        stonecutting(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_BRICK_STAIRS.get(), 1, consumer);
        stonecutting(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_BRICK_WALL.get(), 1, consumer);
        conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.BLUE_ICE), WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB.get(), 2).unlockedBy("has_blue_ice", has(Blocks.BLUE_ICE)), getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix( "blue_ice_brick_vertical_slab_from_blue_ice_stonecutting"));
        
        brickSet(Blocks.PACKED_ICE, WindsweptBlocks.PACKED_ICE_BRICKS, WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS, WindsweptBlocks.PACKED_ICE_BRICK_SLAB, WindsweptBlocks.PACKED_ICE_BRICK_STAIRS, WindsweptBlocks.PACKED_ICE_BRICK_WALL, WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB, consumer);
        brickSet(Blocks.BLUE_ICE, WindsweptBlocks.BLUE_ICE_BRICKS, WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS, WindsweptBlocks.BLUE_ICE_BRICK_SLAB, WindsweptBlocks.BLUE_ICE_BRICK_STAIRS, WindsweptBlocks.BLUE_ICE_BRICK_WALL, WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB, consumer);
        brickSet(Blocks.SNOW_BLOCK, WindsweptBlocks.SNOW_BRICKS, null, WindsweptBlocks.SNOW_BRICK_SLAB, WindsweptBlocks.SNOW_BRICK_STAIRS, WindsweptBlocks.SNOW_BRICK_WALL, WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB, consumer);

        // wood sets
        woodSet("holly", WindsweptItemTags.HOLLY_LOGS, WindsweptBlocks.HOLLY_PLANKS, WindsweptBlocks.HOLLY_SLAB, WindsweptBlocks.HOLLY_STAIRS, WindsweptBlocks.HOLLY_LOG, WindsweptBlocks.HOLLY_WOOD, WindsweptBlocks.STRIPPED_HOLLY_LOG, WindsweptBlocks.STRIPPED_HOLLY_WOOD, WindsweptItems.HOLLY_BOAT, WindsweptBlocks.HOLLY_BUTTON, WindsweptBlocks.HOLLY_DOOR, WindsweptBlocks.HOLLY_TRAPDOOR, WindsweptBlocks.HOLLY_FENCE, WindsweptBlocks.HOLLY_FENCE_GATE, WindsweptBlocks.HOLLY_PRESSURE_PLATE, WindsweptBlocks.HOLLY_SIGNS, WindsweptBlocks.HOLLY_VERTICAL_SLAB, WindsweptBlocks.HOLLY_POST, WindsweptBlocks.STRIPPED_HOLLY_POST, WindsweptBlocks.HOLLY_BOARDS, WindsweptBlocks.HOLLY_BEEHIVE, WindsweptBlocks.HOLLY_LADDER, WindsweptBlocks.HOLLY_BOOKSHELF, WindsweptBlocks.HOLLY_CHEST, WindsweptBlocks.HOLLY_TRAPPED_CHEST, WindsweptItems.LARGE_HOLLY_BOAT, WindsweptItems.HOLLY_FURNACE_BOAT, WindsweptBlocks.VERTICAL_HOLLY_PLANKS, WindsweptBlocks.HOLLY_CABINET, consumer);
        woodSet("chestnut", WindsweptItemTags.CHESTNUT_LOGS, WindsweptBlocks.CHESTNUT_PLANKS, WindsweptBlocks.CHESTNUT_SLAB, WindsweptBlocks.CHESTNUT_STAIRS, WindsweptBlocks.CHESTNUT_LOG, WindsweptBlocks.CHESTNUT_WOOD, WindsweptBlocks.STRIPPED_CHESTNUT_LOG, WindsweptBlocks.STRIPPED_CHESTNUT_WOOD, WindsweptItems.CHESTNUT_BOAT, WindsweptBlocks.CHESTNUT_BUTTON, WindsweptBlocks.CHESTNUT_DOOR, WindsweptBlocks.CHESTNUT_TRAPDOOR, WindsweptBlocks.CHESTNUT_FENCE, WindsweptBlocks.CHESTNUT_FENCE_GATE, WindsweptBlocks.CHESTNUT_PRESSURE_PLATE, WindsweptBlocks.CHESTNUT_SIGNS, WindsweptBlocks.CHESTNUT_VERTICAL_SLAB, WindsweptBlocks.CHESTNUT_POST, WindsweptBlocks.STRIPPED_CHESTNUT_POST, WindsweptBlocks.CHESTNUT_BOARDS, WindsweptBlocks.CHESTNUT_BEEHIVE, WindsweptBlocks.CHESTNUT_LADDER, WindsweptBlocks.CHESTNUT_BOOKSHELF, WindsweptBlocks.CHESTNUT_CHEST, WindsweptBlocks.CHESTNUT_TRAPPED_CHEST, WindsweptItems.LARGE_CHESTNUT_BOAT, WindsweptItems.CHESTNUT_FURNACE_BOAT, WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS, WindsweptBlocks.CHESTNUT_CABINET, consumer);
        leafSet(WindsweptItemTags.HOLLY_LOGS, WindsweptBlocks.HOLLY_LEAVES, WindsweptBlocks.HOLLY_HEDGE, WindsweptBlocks.HOLLY_LEAF_CARPET, WindsweptBlocks.HOLLY_LEAF_PILE, consumer);
        leafSet(WindsweptItemTags.CHESTNUT_LOGS, WindsweptBlocks.CHESTNUT_LEAVES, WindsweptBlocks.CHESTNUT_HEDGE, WindsweptBlocks.CHESTNUT_LEAF_CARPET, WindsweptBlocks.CHESTNUT_LEAF_PILE, consumer);

        // compressed blocks
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.WILD_BERRY_BASKET.get()).define('#', WindsweptItems.WILD_BERRIES.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_berries", has(WindsweptItems.WILD_BERRIES.get())), new OrCondition(new ModLoadedCondition("berry_good"), getQuarkCondition("berry_sack")), consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_basket"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRIES.get(), 9).requires(WindsweptBlocks.WILD_BERRY_BASKET.get()).unlockedBy("has_WILD_BERRY_BASKET", has(WindsweptBlocks.WILD_BERRY_BASKET.get())), new OrCondition(new ModLoadedCondition("berry_good"), getQuarkCondition("berry_sack")), consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_basket_revert"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.WILD_BERRY_PIPS.get()).requires(WindsweptItems.WILD_BERRIES.get()).unlockedBy("has_wild_berries", has(WindsweptItems.WILD_BERRIES.get())), new ModLoadedCondition("berry_good"), consumer, Windswept.REGISTRY_HELPER.prefix("wild_berry_pips"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.HOLLY_BERRY_BASKET.get()).define('#', WindsweptItems.HOLLY_BERRIES.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_holly_berries", has(WindsweptItems.HOLLY_BERRIES.get())), new OrCondition(new ModLoadedCondition("berry_good"), getQuarkCondition("berry_sack")), consumer, Windswept.REGISTRY_HELPER.prefix("holly_berry_basket"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.HOLLY_BERRIES.get(), 9).requires(WindsweptBlocks.HOLLY_BERRY_BASKET.get()).unlockedBy("has_holly_berry_basket", has(WindsweptBlocks.HOLLY_BERRY_BASKET.get())), new OrCondition(new ModLoadedCondition("berry_good"), getQuarkCondition("berry_sack")), consumer, Windswept.REGISTRY_HELPER.prefix("holly_berry_basket_revert"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.CHESTNUT_CRATE.get()).define('#', WindsweptItems.CHESTNUTS.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_chestnuts", has(WindsweptItems.CHESTNUTS.get())), getQuarkCondition("apple_crate"), consumer, Windswept.REGISTRY_HELPER.prefix("chestnut_crate"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.CHESTNUTS.get(), 9).requires(WindsweptBlocks.CHESTNUT_CRATE.get()).unlockedBy("has_chestnut_crate", has(WindsweptBlocks.CHESTNUT_CRATE.get())), getQuarkCondition("apple_crate"), consumer, Windswept.REGISTRY_HELPER.prefix("chestnut_crate_revert"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get()).define('#', WindsweptItems.ROASTED_CHESTNUTS.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_roasted_chestnuts", has(WindsweptItems.ROASTED_CHESTNUTS.get())), getQuarkCondition("apple_crate"), consumer, Windswept.REGISTRY_HELPER.prefix("roasted_chestnut_crate"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.ROASTED_CHESTNUTS.get(), 9).requires(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get()).unlockedBy("has_roasted_chestnut_crate", has(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get())), getQuarkCondition("apple_crate"), consumer, Windswept.REGISTRY_HELPER.prefix("roasted_chestnut_crate_revert"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.RED_MUSHROOM_BASKET.get()).define('#', Items.RED_MUSHROOM).pattern("###").pattern("###").pattern("###").unlockedBy("has_red_mushroom", has(Items.RED_MUSHROOM)), new OrCondition(new OrCondition(new ModLoadedCondition("berry_good"), new ModLoadedCondition("farmersdelight")), getQuarkCondition("apple_crate")), consumer, Windswept.REGISTRY_HELPER.prefix("red_mushroom_basket"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(Items.RED_MUSHROOM, 9).requires(WindsweptBlocks.RED_MUSHROOM_BASKET.get()).unlockedBy("has_red_mushroom_basket", has(WindsweptBlocks.RED_MUSHROOM_BASKET.get())), new OrCondition(new OrCondition(new ModLoadedCondition("berry_good"), new ModLoadedCondition("farmersdelight")), getQuarkCondition("apple_crate")), consumer, Windswept.REGISTRY_HELPER.prefix("red_mushroom_basket_revert"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.BROWN_MUSHROOM_BASKET.get()).define('#', Items.BROWN_MUSHROOM).pattern("###").pattern("###").pattern("###").unlockedBy("has_brown_mushroom", has(Items.BROWN_MUSHROOM)), new OrCondition(new OrCondition(new ModLoadedCondition("berry_good"), new ModLoadedCondition("farmersdelight")), getQuarkCondition("apple_crate")), consumer, Windswept.REGISTRY_HELPER.prefix("brown_mushroom_basket"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(Items.BROWN_MUSHROOM, 9).requires(WindsweptBlocks.BROWN_MUSHROOM_BASKET.get()).unlockedBy("has_brown_mushroom_basket", has(WindsweptBlocks.BROWN_MUSHROOM_BASKET.get())), new OrCondition(new OrCondition(new ModLoadedCondition("berry_good"), new ModLoadedCondition("farmersdelight")), getQuarkCondition("apple_crate")), consumer, Windswept.REGISTRY_HELPER.prefix("brown_mushroom_basket_revert"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.FOUL_BERRY_BOWL.get()).requires(Items.BOWL).requires(WindsweptConstants.FOUL_BERRIES.get(), 3).unlockedBy("has_foul_berries", has(WindsweptConstants.FOUL_BERRIES.get())), new ModLoadedCondition("autumnity"), consumer, Windswept.REGISTRY_HELPER.prefix("foul_berry_bowl"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptConstants.FOUL_BERRIES.get(), 3).requires(WindsweptItems.FOUL_BERRY_BOWL.get()).unlockedBy("has_foul_berry_bowl", has(WindsweptItems.FOUL_BERRY_BOWL.get())), new ModLoadedCondition("autumnity"), consumer, Windswept.REGISTRY_HELPER.prefix("foul_berry_bowl_revert"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptBlocks.FROZEN_FLESH_BLOCK.get()).define('#', WindsweptItems.FROZEN_FLESH.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_frozen_flesh", has(WindsweptItems.FROZEN_FLESH.get())), new ModLoadedCondition("caverns_and_chasms"), consumer, Windswept.REGISTRY_HELPER.prefix("frozen_flesh_block"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WindsweptItems.FROZEN_FLESH.get(), 9).requires(WindsweptBlocks.FROZEN_FLESH_BLOCK.get()).unlockedBy("has_brown_mushroom_basket", has(WindsweptBlocks.FROZEN_FLESH_BLOCK.get())), new ModLoadedCondition("caverns_and_chasms"), consumer, Windswept.REGISTRY_HELPER.prefix("frozen_flesh_block_revert"));
    }

    private static void conditionalRecipe(RecipeBuilder recipe, ICondition condition, Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
    }

    private static QuarkFlagRecipeCondition getQuarkCondition(String flag) {
        return new QuarkFlagRecipeCondition(new ResourceLocation("blueprint", "quark_flag"), flag);
    }

    private static void conditionalCooking(ItemLike ingredient, ItemLike result, ICondition condition, Consumer<FinishedRecipe> consumer) {
        String path = getName(ingredient);
        String name = getName(result);

        conditionalRecipe(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, .35f, 200).unlockedBy("has_" + path, has(ingredient)), condition, consumer, Windswept.REGISTRY_HELPER.prefix(name));
        conditionalRecipe(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), result, .35f, 600).unlockedBy("has_" + path, has(ingredient)), condition, consumer, Windswept.REGISTRY_HELPER.prefix(name + "_from_campfire_cooking"));
        conditionalRecipe(SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), result, .35f, 100).unlockedBy("has_" + path, has(ingredient)), condition, consumer, Windswept.REGISTRY_HELPER.prefix(name + "_from_smoking"));
    }

    private static void brickSet(ItemLike ingredient, RegistryObject<Block> block, @Nullable RegistryObject<Block> chiseled, RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> wall, RegistryObject<Block> verticalSlab, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(block.get(), 4).define('#', ingredient).pattern("##").pattern("##").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(block.get())));
        stonecutting(ingredient.asItem(), block.get(), 1, consumer);
        stonecutting(block.get(), slab.get(), 2, consumer);
        stonecutting(block.get(), stairs.get(), 1, consumer);
        stairs(block.get(), stairs.get(), consumer);
        slab(block.get(), slab.get(), consumer);
        verticalSlab(verticalSlab.get(), slab.get(), consumer);
        conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(block.get()), verticalSlab.get(), 2).unlockedBy("has_" + getName(block.get()), has(block.get())), getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(verticalSlab.get()) + "_from_" + getName(block.get()) + "_stonecutting"));
        stonecutting(block.get(), wall.get(), 1, consumer);
        wall(block.get(), wall.get(), consumer);

        if (chiseled != null) {
            stonecutting(block.get(), chiseled.get(), 1, consumer);
            ShapedRecipeBuilder.shaped(chiseled.get()).define('#', slab.get()).pattern("#").pattern("#").unlockedBy("has_" + getName(block.get()), has(block.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(chiseled.get())));
        }

    }

    private static void woodSet(String name, TagKey<Item> logs, RegistryObject<Block> planks, RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> log, RegistryObject<Block> wood, RegistryObject<Block> strippedLog, RegistryObject<Block> strippedWood, Pair<RegistryObject<Item>, RegistryObject<Item>> boat, RegistryObject<Block> button, RegistryObject<Block> door, RegistryObject<Block> trapdoor, RegistryObject<Block> fence, RegistryObject<Block> fenceGate, RegistryObject<Block> pressurePlate, Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> signs, RegistryObject<Block> verticalSlab, RegistryObject<Block> post, RegistryObject<Block> strippedPost, RegistryObject<Block> boards, RegistryObject<Block> beehive, RegistryObject<Block> ladder, RegistryObject<Block> bookshelf, RegistryObject<? extends Block> chest, RegistryObject<? extends Block> trappedChest, RegistryObject<Item> largeBoat, RegistryObject<Item> furnaceBoat, RegistryObject<Block> verticalPlanks, RegistryObject<Block> cabinet, Consumer<FinishedRecipe> consumer) {
        woodenBoat(consumer, boat.getFirst().get(), planks.get());
        ShapelessRecipeBuilder.shapeless(boat.getSecond().get()).group("chest_boat").requires(Tags.Items.CHESTS_WOODEN).requires(boat.getFirst().get()).unlockedBy("has_" + getName(boat.getFirst().get()), has(boat.getFirst().get())).save(consumer, getRegistryName(boat.getSecond()));
        ShapelessRecipeBuilder.shapeless(button.get()).group("wooden_button").requires(planks.get()).unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(button));
        ShapedRecipeBuilder.shaped(door.get(), 3).group("wooden_door").define('#', planks.get()).pattern("##").pattern("##").pattern("##").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(door));
        ShapedRecipeBuilder.shaped(fence.get(), 3).group("wooden_fence").define('#', planks.get()).define('S', Items.STICK).pattern("#S#").pattern("#S#").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(fence));
        ShapedRecipeBuilder.shaped(fenceGate.get()).group("wooden_fence_gate").define('#', planks.get()).define('S', Items.STICK).pattern("S#S").pattern("S#S").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(fenceGate));
        ShapedRecipeBuilder.shaped(pressurePlate.get()).group("wooden_pressure_plate").define('#', planks.get()).pattern("##").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(pressurePlate));
        ShapedRecipeBuilder.shaped(signs.getFirst().get(), 3).group("wooden_sign").define('#', planks.get()).define('S', Items.STICK).pattern("###").pattern("###").pattern(" S ").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(signs.getFirst()));
        ShapedRecipeBuilder.shaped(trapdoor.get(), 2).group("wooden_trapdoor").define('#', planks.get()).pattern("###").pattern("###").unlockedBy("has_" + name + "_planks", has(planks.get())).save(consumer, getRegistryName(trapdoor));
        planksFromLogs(consumer, planks.get(), logs);
        woodFromLogs(consumer, wood.get(), log.get());
        woodFromLogs(consumer, strippedWood.get(), strippedLog.get());
        slab(planks.get(), slab.get(), "wooden_slab", consumer);
        stairs(planks.get(), stairs.get(), "wooden_stairs", consumer);
        verticalSlab(verticalSlab.get(), slab.get(), consumer);
        post(post.get(), wood.get(), consumer);
        post(strippedPost.get(), strippedWood.get(), consumer);
        conditionalRecipe(ShapedRecipeBuilder.shaped(boards.get(), 3).group("wooden_boards").define('#', planks.get()).pattern("#").pattern("#").pattern("#").unlockedBy("has_" + name + "_planks", has(planks.get())), new ModLoadedCondition("woodworks"), consumer, getRegistryName(boards));
        conditionalRecipe(ShapedRecipeBuilder.shaped(beehive.get()).group("wooden_beehive").define('#', planks.get()).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").unlockedBy("has_" + name + "_planks", has(planks.get())), new ModLoadedCondition("woodworks"), consumer, getRegistryName(beehive));
        conditionalRecipe(ShapedRecipeBuilder.shaped(ladder.get(), 4).group("wooden_ladders").define('#', planks.get()).define('S', Items.STICK).pattern("S S").pattern("S#S").pattern("S S").unlockedBy("has_" + name + "_planks", has(planks.get())), new OrCondition(new ModLoadedCondition("woodworks"), getQuarkCondition("variant_ladders")), consumer, getRegistryName(ladder));
        conditionalRecipe(ShapedRecipeBuilder.shaped(bookshelf.get()).group("wooden_bookshelves").define('#', planks.get()).define('B', Items.BOOK).pattern("###").pattern("BBB").pattern("###").unlockedBy("has_" + name + "_planks", has(planks.get())), new OrCondition(new ModLoadedCondition("woodworks"), getQuarkCondition("variant_bookshelves")), consumer, getRegistryName(bookshelf));
        conditionalRecipe(ShapedRecipeBuilder.shaped(chest.get()).group("wooden_chests").define('#', planks.get()).pattern("###").pattern("# #").pattern("###").unlockedBy("has_" + name + "_planks", has(planks.get())), new OrCondition(new ModLoadedCondition("woodworks"), getQuarkCondition("variant_chests")), consumer, getRegistryName(chest));
        conditionalRecipe(ShapedRecipeBuilder.shaped(chest.get(), 4).group("wooden_chests").define('#', logs).pattern("###").pattern("# #").pattern("###").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])), new AndCondition(getQuarkCondition("wood_to_chest_recipes"), getQuarkCondition("variant_chests")), consumer, Windswept.REGISTRY_HELPER.prefix(getName(chest.get()) + "_wood"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(trappedChest.get()).requires(chest.get()).requires(Items.TRIPWIRE_HOOK).unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])), new OrCondition(new ModLoadedCondition("woodworks"), getQuarkCondition("variant_chests")), consumer, getRegistryName(trappedChest));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(furnaceBoat.get()).requires(Items.FURNACE).requires(boat.getFirst().get()).unlockedBy("has_" + name + "_boat", has(boat.getFirst().get())), new ModLoadedCondition("boatload"), consumer, getRegistryName(furnaceBoat));
        conditionalRecipe(ShapedRecipeBuilder.shaped(largeBoat.get()).group("wooden_chests").define('#', planks.get()).define('B', boat.getFirst().get()).pattern("#B#").pattern("###").unlockedBy("has_" + name + "_boat", has(boat.getFirst().get())), new ModLoadedCondition("boatload"), consumer, getRegistryName(largeBoat));
        conditionalRecipe(ShapedRecipeBuilder.shaped(verticalPlanks.get(), 3).define('#', planks.get()).pattern("#").pattern("#").pattern("#").unlockedBy("has_" + name + "_planks", has(planks.get())), getQuarkCondition("vertical_planks"), consumer, getRegistryName(verticalPlanks));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(planks.get()).requires(verticalPlanks.get()).unlockedBy("has_" + name + "_vertical_planks", has(verticalPlanks.get())), getQuarkCondition("vertical_planks"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(verticalPlanks.get()) + "_revert"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(cabinet.get()).define('#', slab.get()).define('T', trapdoor.get()).pattern("###").pattern("T T").pattern("###").unlockedBy("has_" + name + "_trapdoor", has(trapdoor.get())), new ModLoadedCondition("farmersdelight"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(cabinet.get())));
    }

    private static void leafSet(TagKey<Item> log, RegistryObject<Block> leaves, RegistryObject<Block> hedge, RegistryObject<Block> leafCarpet, RegistryObject<Block> leafPile, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(ShapedRecipeBuilder.shaped(hedge.get(), 2).define('#', log).define('L', leaves.get()).pattern("L").pattern("#").unlockedBy("has_leaves", has(leaves.get())), getQuarkCondition("hedges"), consumer, getRegistryName(hedge));
        conditionalRecipe(ShapedRecipeBuilder.shaped(leafCarpet.get(), 3).define('#', leaves.get()).pattern("##").unlockedBy("has_leaves", has(leaves.get())), getQuarkCondition("leaf_carpet"), consumer, getRegistryName(leafCarpet));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(leafPile.get(), 4).requires(leaves.get()).unlockedBy("has_leaves", has(leaves.get())), new ModLoadedCondition("woodworks"), consumer, getRegistryName(leafPile));
        conditionalRecipe(ShapedRecipeBuilder.shaped(leaves.get()).define('#', leafPile.get()).pattern("##").pattern("##").unlockedBy("has_leaf_pile", has(leafPile.get())), new ModLoadedCondition("woodworks"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(leaves.get()) + "_from_leaf_pile"));
    }

    private static void flowerToDye(RegistryObject<Block> flower, Item dye, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(dye).group(getName(dye)).requires(flower.get()).unlockedBy("has_" + getName(flower.get()), has(flower.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(dye) + "_from_" + getName(flower.get())));
    }

    private static void tallFlowerToDye(RegistryObject<Block> flower, Item dye, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(dye, 2).group(getName(dye)).requires(flower.get()).unlockedBy("has_" + getName(flower.get()), has(flower.get())).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(dye) + "_from_" + getName(flower.get())));
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
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), result, amount).unlockedBy("has_" + path, has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(path + "_from_" + name + "_stonecutting"));
    }

    private static void stairs(ItemLike ingredient, ItemLike stairs, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(stairs, 4).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(stairs)));
    }

    private static void stairs(ItemLike ingredient, ItemLike stairs, String group, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(stairs, 4).group(group).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(stairs)));
    }

    private static void wall(ItemLike ingredient, ItemLike wall, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(wall, 6).define('#', ingredient).pattern("###").pattern("###").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(wall)));
    }

    private static void slab(ItemLike ingredient, ItemLike slab, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(slab, 6).define('#', ingredient).pattern("###").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(slab)));
    }

    private static void slab(ItemLike ingredient, ItemLike slab, String group, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(slab, 6).group(group).define('#', ingredient).pattern("###").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, Windswept.REGISTRY_HELPER.prefix(getName(slab)));
    }

    private static void verticalSlab(ItemLike verticalSlab, ItemLike slab, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(ShapedRecipeBuilder.shaped(verticalSlab, 3).define('#', slab).pattern("#").pattern("#").pattern("#").unlockedBy("has_slab", has(slab)), getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(verticalSlab)));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(slab).requires(verticalSlab).unlockedBy("has_vertical_slab", has(verticalSlab)), getQuarkCondition("vertical_slabs"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(verticalSlab) + "_revert"));
    }

    private static void post(ItemLike post, ItemLike wood, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(ShapedRecipeBuilder.shaped(post, 8).define('#', wood).pattern("#").pattern("#").pattern("#").unlockedBy("has_wood", has(wood)), getQuarkCondition("wooden_posts"), consumer, Windswept.REGISTRY_HELPER.prefix(getName(post)));
    }

    private static String getName(ItemLike object) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(object.asItem())).getPath();
    }

    private static ResourceLocation getRegistryName(RegistryObject<? extends ItemLike> item) {
        return ForgeRegistries.ITEMS.getKey(item.get().asItem());
    }

}

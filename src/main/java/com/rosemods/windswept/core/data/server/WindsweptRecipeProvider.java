package com.rosemods.windswept.core.data.server;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
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

import java.util.function.Consumer;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptRecipeProvider extends RecipeProvider {
    public WindsweptRecipeProvider(GatherDataEvent event) {
        super(event.getGenerator());
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // foods
        ShapelessRecipeBuilder.shapeless(WILD_BERRY_JUICE.get()).requires(Items.GLASS_BOTTLE).requires(WILD_BERRIES.get(), 4).unlockedBy(getHasName(WILD_BERRIES.get()), has(WILD_BERRIES.get())).save(consumer, getSaveLocation(WILD_BERRY_JUICE.get()));
        ShapelessRecipeBuilder.shapeless(Items.SUGAR, 1).requires(WILD_BERRY_JUICE.get()).unlockedBy(getHasName(WILD_BERRY_JUICE.get()), has(WILD_BERRY_JUICE.get())).save(consumer, getSaveLocation("wild_berry_juice_to_sugar"));
        ShapelessRecipeBuilder.shapeless(WILD_BERRY_COOKIE.get(), 8).requires(WILD_BERRIES.get()).requires(Items.WHEAT, 2).unlockedBy(getHasName(WILD_BERRIES.get()), has(WILD_BERRIES.get())).save(consumer, getSaveLocation(WILD_BERRY_COOKIE.get()));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(CHESTNUT_SOUP.get()).requires(Items.BOWL).requires(ROASTED_CHESTNUTS.get(), 2).requires(Items.CARROT).requires(Items.POTATO).unlockedBy(getHasName(ROASTED_CHESTNUTS.get()), has(ROASTED_CHESTNUTS.get())), new NotCondition(new ModLoadedCondition("farmersdelight")), consumer, getSaveLocation(CHESTNUT_SOUP.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(Blocks.CAKE).define('A', WindsweptItemTags.MILK).define('B', Items.SUGAR).define('C', Tags.Items.CROPS_WHEAT).define('E', Tags.Items.EGGS).pattern("AAA").pattern("BEB").pattern("CCC").unlockedBy(getHasName(Items.EGG), has(Tags.Items.EGGS)), new NotCondition(new ModLoadedCondition("neapolitan")), consumer, getSaveLocation(Items.CAKE));
        cooking(CHESTNUTS.get(), ROASTED_CHESTNUTS.get(), consumer);
        cooking(GOAT.get(), COOKED_GOAT.get(), consumer);
        conditionalCooking(GOAT_SHANKS.get(), COOKED_GOAT_SHANKS.get(), new ModLoadedCondition("farmersdelight"), consumer);
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(GOAT_STEW.get()).requires(Items.BOWL).requires(COOKED_GOAT.get()).requires(Items.BAKED_POTATO).requires(Items.CARROT).requires(Items.BROWN_MUSHROOM).unlockedBy(getHasName(COOKED_GOAT.get()), has(COOKED_GOAT.get())), new NotCondition(new ModLoadedCondition("farmersdelight")), consumer, getSaveLocation(GOAT_STEW.get()));
        ShapelessRecipeBuilder.shapeless(MUTTON_PIE.get()).requires(WindsweptItemTags.COOKED_MUTTON).requires(Items.WHEAT).requires(Items.SUGAR).requires(Tags.Items.EGGS).unlockedBy(getHasName(Items.COOKED_MUTTON), has(WindsweptItemTags.COOKED_MUTTON)).save(consumer, getSaveLocation(MUTTON_PIE.get()));
        ShapedRecipeBuilder.shaped(WILD_BERRY_POPSICLE.get()).define('#', WILD_BERRIES.get()).define('S', Items.STICK).define('I', ICICLES.get()).pattern(" ##").pattern("I##").pattern("SI ").unlockedBy(getHasName(WILD_BERRIES.get()), has(WILD_BERRIES.get())).save(consumer, getSaveLocation(WILD_BERRY_POPSICLE.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(WindsweptConstants.getItem("farmersdelight", "melon_popsicle")).define('#', Items.MELON_SLICE).define('S', Items.STICK).define('I', ICICLES.get()).pattern(" ##").pattern("I##").pattern("SI ").unlockedBy(getHasName(Items.MELON_SLICE), has(Items.MELON_SLICE)), new ModLoadedCondition("farmersdelight"), consumer, getSaveLocation("melon_popsicle_from_icicles"));
        ShapelessRecipeBuilder.shapeless(CHRISTMAS_PUDDING.get()).requires(HOLLY_BERRIES.get()).requires(GINGER_ROOT.get()).requires(ROASTED_CHESTNUTS.get()).requires(Items.SUGAR).requires(Tags.Items.EGGS).requires(Tags.Items.CROPS_WHEAT).unlockedBy(getHasName(HOLLY_BERRIES.get()), has(HOLLY_BERRIES.get())).save(consumer, getSaveLocation(CHRISTMAS_PUDDING.get()));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(CHRISTMAS_PUDDING.get()).requires(CHRISTMAS_PUDDING_SLICE.get(), 4).unlockedBy(getHasName(CHRISTMAS_PUDDING_SLICE.get()), has(CHRISTMAS_PUDDING_SLICE.get())), new ModLoadedCondition("farmersdelight"), consumer, getSaveLocation("christmas_pudding_from_slices"));
        ShapelessRecipeBuilder.shapeless(GINGERBREAD_COOKIE.get(), 8).requires(GINGER_ROOT.get()).requires(Items.WHEAT, 2).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(consumer, getSaveLocation(GINGERBREAD_COOKIE.get()));
        ShapelessRecipeBuilder.shapeless(GINGER_SNOW_CONE.get()).requires(PINECONE.get()).requires(GINGER_ROOT.get()).requires(ROASTED_CHESTNUTS.get()).requires(Items.SNOWBALL).requires(Items.SUGAR).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(consumer, getSaveLocation(GINGER_SNOW_CONE.get()));
        ShapelessRecipeBuilder.shapeless(CANDY_CANE.get(), 3).requires(WILD_BERRIES.get()).requires(Items.SUGAR, 2).unlockedBy(getHasName(WILD_BERRIES.get()), has(WILD_BERRIES.get())).save(consumer, getSaveLocation(CANDY_CANE.get()));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(GINGER_TEA.get()).requires(Items.GLASS_BOTTLE).requires(GINGER_ROOT.get(), 2).requires(Items.SUGAR).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())), new NotCondition(new ModLoadedCondition("farmersdelight")), consumer, getSaveLocation(GINGER_TEA.get()));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(LAVENDER_TEA.get()).requires(Items.GLASS_BOTTLE).requires(LAVENDER.get(), 4).unlockedBy(getHasName(LAVENDER.get()), has(LAVENDER.get())), new NotCondition(new ModLoadedCondition("farmersdelight")), consumer, getSaveLocation(LAVENDER_TEA.get()));
        ShapelessRecipeBuilder.shapeless(LAVENDER_SCONES.get(), 2).requires(Items.WHEAT).requires(LAVENDER.get()).requires(Items.WHEAT).requires(Items.SUGAR).unlockedBy(getHasName(LAVENDER.get()), has(LAVENDER.get())).save(consumer, getSaveLocation(LAVENDER_SCONES.get()));
        ShapelessRecipeBuilder.shapeless(PINECONE_JAM_BOTTLE.get()).requires(PINECONE.get(), 6).requires(Items.SUGAR, 2).requires(Items.GLASS_BOTTLE).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(consumer, getSaveLocation(PINECONE_JAM_BOTTLE.get()));
        ShapedRecipeBuilder.shaped(PINECONE_JAM_BLOCK.get()).define('#', PINECONE_JAM_BOTTLE.get()).pattern("##").pattern("##").unlockedBy(getHasName(PINECONE_JAM_BOTTLE.get()), has(PINECONE_JAM_BOTTLE.get())).save(consumer, getSaveLocation(PINECONE_JAM_BLOCK.get()));
        ShapelessRecipeBuilder.shapeless(PINECONE_JAM_BOTTLE.get(), 4).requires(PINECONE_JAM_BLOCK.get()).requires(Items.GLASS_BOTTLE, 4).unlockedBy(getHasName(PINECONE_JAM_BLOCK.get()), has(PINECONE_JAM_BLOCK.get())).save(consumer, getSaveLocation("pinecone_jam_bottle_revert"));

        // berry bowls
        ShapelessRecipeBuilder.shapeless(WILD_BERRY_BOWL.get()).requires(Items.BOWL).requires(WILD_BERRIES.get(), 3).unlockedBy(getHasName(WILD_BERRIES.get()), has(WILD_BERRIES.get())).save(consumer, getSaveLocation(WILD_BERRY_BOWL.get()));
        ShapelessRecipeBuilder.shapeless(WILD_BERRIES.get(), 3).requires(WILD_BERRY_BOWL.get()).unlockedBy(getHasName(WILD_BERRY_BOWL.get()), has(WILD_BERRY_BOWL.get())).save(consumer, getSaveLocation("wild_berry_bowl_revert"));
        ShapelessRecipeBuilder.shapeless(SWEET_BERRY_BOWL.get()).requires(Items.BOWL).requires(Items.SWEET_BERRIES, 3).unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES)).save(consumer, getSaveLocation(SWEET_BERRY_BOWL.get()));
        ShapelessRecipeBuilder.shapeless(Items.SWEET_BERRIES, 3).requires(SWEET_BERRY_BOWL.get()).unlockedBy(getHasName(SWEET_BERRY_BOWL.get()), has(SWEET_BERRY_BOWL.get())).save(consumer, getSaveLocation("sweet_berry_bowl_revert"));
        Item foulBerries = WindsweptConstants.getItem("autumnity", "foul_berries");
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(FOUL_BERRY_BOWL.get()).requires(Items.BOWL).requires(foulBerries, 3).unlockedBy("has_foul_berries", has(foulBerries)), new ModLoadedCondition("autumnity"), consumer, getSaveLocation(FOUL_BERRY_BOWL.get()));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(foulBerries, 3).requires(FOUL_BERRY_BOWL.get()).unlockedBy(getHasName(FOUL_BERRY_BOWL.get()), has(FOUL_BERRY_BOWL.get())), new ModLoadedCondition("autumnity"), consumer, getSaveLocation("foul_berry_bowl_revert"));

        // dyes
        flowerToDye(RED_ROSE.get(), Items.RED_DYE, consumer);
        flowerToDye(BLUE_ROSE.get(), Items.BLUE_DYE, consumer);
        flowerToDye(WHITE_ROSE.get(), Items.WHITE_DYE, consumer);
        flowerToDye(YELLOW_ROSE.get(), Items.YELLOW_DYE, consumer);
        flowerToDye(FOXGLOVE.get(), Items.PINK_DYE, consumer);
        flowerToDye(BLUEBELLS.get(), Items.BLUE_DYE, consumer);
        flowerToDye(SNOWDROP.get(), Items.LIGHT_GRAY_DYE, consumer);
        flowerToDye(MOSS_CAMPION.get(), Items.MAGENTA_DYE, consumer);
        flowerToDye(WILD_GINGER.get(), Items.RED_DYE, consumer);
        flowerToDye(NIGHTSHADE.get(), Items.LIGHT_BLUE_DYE, consumer);
        tallFlowerToDye(RED_ROSE_BUSH.get(), Items.RED_DYE, consumer);
        tallFlowerToDye(BLUE_ROSE_BUSH.get(), Items.BLUE_DYE, consumer);
        tallFlowerToDye(WHITE_ROSE_BUSH.get(), Items.WHITE_DYE, consumer);
        tallFlowerToDye(YELLOW_ROSE_BUSH.get(), Items.YELLOW_DYE, consumer);
        tallFlowerToDye(LUPINE.get(), Items.PURPLE_DYE, consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PINECONE.get()), Items.BROWN_DYE, .35f, 200).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(consumer, getSaveLocation("brown_dye_from_pinecone_smelting"));

        // other items
        ShapelessRecipeBuilder.shapeless(SNOW_CHARGE_BANNER_PATTERN.get()).requires(Items.PAPER).requires(BlueprintItemTags.BUCKETS_POWDER_SNOW).unlockedBy(getHasName(Items.POWDER_SNOW_BUCKET), has(BlueprintItemTags.BUCKETS_POWDER_SNOW)).save(consumer, getSaveLocation(SNOW_CHARGE_BANNER_PATTERN.get()));
        ShapelessRecipeBuilder.shapeless(SNOW_GOLEM_BANNER_PATTERN.get()).requires(Items.PAPER).requires(Items.GOLDEN_CARROT).unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT)).save(consumer, getSaveLocation(SNOW_GOLEM_BANNER_PATTERN.get()));
        ShapelessRecipeBuilder.shapeless(ROSE_FLOWER_BANNER_PATTERN.get()).requires(Items.PAPER).requires(WindsweptItemTags.ROSES).unlockedBy("has_roses", has(WindsweptItemTags.ROSES)).save(consumer, getSaveLocation(ROSE_FLOWER_BANNER_PATTERN.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FROZEN_FLESH.get()), Items.LEATHER, .35f, 400).unlockedBy(getHasName(FROZEN_FLESH.get()), has(FROZEN_FLESH.get())).save(consumer, getSaveLocation("leather_from_frozen_flesh"));
        ShapedRecipeBuilder.shaped(WOODEN_BUCKET.get()).define('#', ItemTags.LOGS).pattern("# #").pattern(" # ").unlockedBy("has_log", has(ItemTags.LOGS)).save(consumer, getSaveLocation(WOODEN_BUCKET.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(SNOW_BOOTS.get()).define('#', Items.IRON_INGOT).define('L', Items.LEATHER).pattern("L L").pattern("L L").pattern("# #").unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)), new TagEmptyCondition(WindsweptItemTags.SILVER_INGOT.location()), consumer, getSaveLocation(SNOW_BOOTS.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(SNOW_BOOTS.get()).define('#', WindsweptItemTags.SILVER_INGOT).define('L', Items.LEATHER).pattern("L L").pattern("L L").pattern("# #").unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)), new NotCondition(new TagEmptyCondition(WindsweptItemTags.SILVER_INGOT.location())), consumer, getSaveLocation("snow_boots_from_silver"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(WILD_BERRY_PIPS.get()).requires(WILD_BERRIES.get()).unlockedBy(getHasName(WILD_BERRIES.get()), has(WILD_BERRIES.get())), new ModLoadedCondition("berry_good"), consumer, getSaveLocation(WILD_BERRY_PIPS.get()));
        ShapedRecipeBuilder.shaped(FROST_ARROW.get(), 6).define('#', FROZEN_BRANCH.get()).define('I', ICICLES.get()).define('S', Items.STICK).pattern("#").pattern("S").pattern("I").unlockedBy(getHasName(FROZEN_BRANCH.get()), has(FROZEN_BRANCH.get())).save(consumer, getSaveLocation(FROST_ARROW.get()));
        ShapedRecipeBuilder.shaped(HOLLY_BERRIES_ON_A_STICK.get()).define('#', Items.FISHING_ROD).define('H', HOLLY_BERRIES.get()).pattern("# ").pattern(" H").unlockedBy(getHasName(HOLLY_BERRIES.get()), has(HOLLY_BERRIES.get())).save(consumer, getSaveLocation(HOLLY_BERRIES_ON_A_STICK.get()));
        ShapedRecipeBuilder.shaped(CUPIDS_ARROW.get(), 4).define('#', LAVENDER.get()).define('F', Items.FEATHER).define('S', Items.STICK).pattern("#").pattern("S").pattern("F").unlockedBy(getHasName(LAVENDER.get()), has(LAVENDER.get())).save(consumer, getSaveLocation(CUPIDS_ARROW.get()));
        ShapedRecipeBuilder.shaped(LAVENDER_CROWN.get()).define('#', LAVENDER.get()).define('F', FROZEN_BRANCH.get()).pattern("###").pattern("#F#").unlockedBy(getHasName(LAVENDER.get()), has(LAVENDER.get())).save(consumer, getSaveLocation(LAVENDER_CROWN.get()));
        ShapedRecipeBuilder.shaped(ANTLER_HELMET.get()).define('#', Items.BONE).define('F', FROZEN_BRANCH.get()).pattern("F#F").pattern("# #").unlockedBy(getHasName(FROZEN_BRANCH.get()), has(FROZEN_BRANCH.get())).save(consumer, getSaveLocation(ANTLER_HELMET.get()));
        //ShapedRecipeBuilder.shaped(FEATHER_CLOAK.get()).define('#', Items.FEATHER).define('L', Items.LEATHER).pattern("L L").pattern("#L#").pattern("###").unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER)).save(consumer, getSaveLocation(FEATHER_CLOAK.get()));

        ShapedRecipeBuilder.shaped(HOLLY_WREATH.get()).define('#', HOLLY_BERRIES.get()).define('B', FROZEN_BRANCH.get()).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy(getHasName(HOLLY_BERRIES.get()), has(HOLLY_BERRIES.get())).save(consumer, getSaveLocation(HOLLY_WREATH.get()));
        ShapedRecipeBuilder.shaped(PINECONE_WREATH.get()).define('#', PINECONE.get()).define('B', FROZEN_BRANCH.get()).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(consumer, getSaveLocation(PINECONE_WREATH.get()));
        ShapedRecipeBuilder.shaped(VINE_WREATH.get()).define('#', Items.VINE).define('B', FROZEN_BRANCH.get()).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy(getHasName(Items.VINE), has(Items.VINE)).save(consumer, getSaveLocation(VINE_WREATH.get()));

        // blocks
        ShapedRecipeBuilder.shaped(ICE_SHEET.get(), 12).define('#', Items.ICE).pattern("###").pattern("###").unlockedBy(getHasName(Items.ICE), has(Items.ICE)).save(consumer, getSaveLocation(ICE_SHEET.get()));
        ShapedRecipeBuilder.shaped(FROSTBITER_TROPHY.get()).define('#', FROZEN_BRANCH.get()).define('P', ItemTags.PLANKS).pattern("# #").pattern("#P#").unlockedBy(getHasName(FROZEN_BRANCH.get()), has(FROZEN_BRANCH.get())).save(consumer, getSaveLocation(FROSTBITER_TROPHY.get()));
        ShapedRecipeBuilder.shaped(DRY_MOSS_CARPET.get(), 3).define('#', DRY_MOSS_BLOCK.get()).pattern("##").unlockedBy(getHasName(DRY_MOSS_BLOCK.get()), has(DRY_MOSS_BLOCK.get())).save(consumer, getSaveLocation(DRY_MOSS_CARPET.get()));
        ShapelessRecipeBuilder.shapeless(DRY_MOSSY_COBBLESTONE.get()).requires(Items.COBBLESTONE).requires(DRY_MOSS_BLOCK.get()).unlockedBy(getHasName(DRY_MOSS_BLOCK.get()), has(DRY_MOSS_BLOCK.get())).save(consumer, getSaveLocation("dry_mossy_cobblestone_from_dry_moss"));
        ShapelessRecipeBuilder.shapeless(DRY_MOSSY_STONE_BRICKS.get()).requires(Items.STONE_BRICKS).requires(DRY_MOSS_BLOCK.get()).unlockedBy(getHasName(DRY_MOSS_BLOCK.get()), has(DRY_MOSS_BLOCK.get())).save(consumer, getSaveLocation("dry_mossy_stone_bricks_from_dry_moss"));
        ShapedRecipeBuilder.shaped(ICY_POLISHED_SHALE_BRICKS.get(), 8).define('#', POLISHED_SHALE_BRICKS.get()).define('I', ICICLES.get()).pattern("###").pattern("#I#").pattern("###").unlockedBy(getHasName(POLISHED_SHALE_BRICKS.get()), has(POLISHED_SHALE_BRICKS.get())).save(consumer, getSaveLocation(ICY_POLISHED_SHALE_BRICKS.get()));
        post(WEATHERED_PINE_POST.get(), WEATHERED_PINE_WOOD.get(), consumer);
        woodFromLogs(consumer, WEATHERED_PINE_WOOD.get(), WEATHERED_PINE_LOG.get());
        ShapedRecipeBuilder.shaped(WILL_O_THE_WISP.get()).define('#', CARVED_PINECONE_BLOCK.get()).define('N', NIGHTSHADE.get()).pattern("#").pattern("N").unlockedBy(getHasName(CARVED_PINECONE_BLOCK.get()), has(CARVED_PINECONE_BLOCK.get())).save(consumer, getSaveLocation(WILL_O_THE_WISP.get()));
        ShapelessRecipeBuilder.shapeless(SHALE.get()).requires(Items.COBBLESTONE).requires(Items.BLUE_ICE).unlockedBy(getHasName(SHALE.get()), has(SHALE.get())).save(consumer, getSaveLocation(SHALE.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LUNALITE.get()), SMOOTH_LUNALITE.get(), .1f, 200).unlockedBy(getHasName(LUNALITE.get()), has(LUNALITE.get())).save(consumer, getSaveLocation(SMOOTH_LUNALITE.get()));
        ShapedRecipeBuilder.shaped(GINGERBREAD_BLOCK.get()).define('#', GINGERBREAD_COOKIE.get()).pattern("##").pattern("##").unlockedBy(getHasName(GINGERBREAD_COOKIE.get()), has(GINGERBREAD_COOKIE.get())).save(consumer, getSaveLocation(GINGERBREAD_BLOCK.get()));
        ShapelessRecipeBuilder.shapeless(GLAZED_GINGERBREAD_BLOCK.get()).requires(GINGERBREAD_BLOCK.get()).requires(Items.SUGAR).unlockedBy(getHasName(GINGERBREAD_BLOCK.get()), has(GINGERBREAD_BLOCK.get())).save(consumer, getSaveLocation("glazed_gingerbread_block_from_sugar"));
        ShapelessRecipeBuilder.shapeless(GLAZED_GINGERBREAD_BRICKS.get()).requires(GINGERBREAD_BRICKS.get()).requires(Items.SUGAR).unlockedBy(getHasName(GINGERBREAD_BRICKS.get()), has(GINGERBREAD_BRICKS.get())).save(consumer, getSaveLocation("glazed_gingerbread_bricks_from_sugar"));
        ShapedRecipeBuilder.shaped(LAVENDER_THATCH.get()).define('#', LAVENDER.get()).pattern("##").pattern("##").unlockedBy(getHasName(LAVENDER.get()), has(LAVENDER.get())).save(consumer, getSaveLocation(LAVENDER_THATCH.get()));
        ShapedRecipeBuilder.shaped(LUNALITE.get(), 8).define('#', Items.CALCITE).define('L', Items.LAPIS_LAZULI).pattern("###").pattern("#L#").pattern("###").unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI)).save(consumer, getSaveLocation(LUNALITE.get()));

        ShapedRecipeBuilder.shaped(FEATHER_WING.get(), 2).define('#', PINECONE.get()).define('F', Items.FEATHER).pattern("#FF").unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER)).save(consumer, getSaveLocation(FEATHER_WING.get()));
        ShapedRecipeBuilder.shaped(FEATHER_ORNAMENT.get(), 2).define('#', PINECONE.get()).define('F', Items.FEATHER).pattern("##").pattern("FF").unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER)).save(consumer, getSaveLocation(FEATHER_ORNAMENT.get()));
        ShapedRecipeBuilder.shaped(DREAM_CATCHER.get(), 1).define('#', PINECONE.get()).define('S', Items.STRING).define('F', Items.FEATHER).pattern(" # ").pattern("FSF").pattern(" F ").unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER)).save(consumer, getSaveLocation(DREAM_CATCHER.get()));

        ShapelessRecipeBuilder.shapeless(POLISHED_DEEPSLATE_BUTTON.get()).requires(Items.POLISHED_DEEPSLATE).unlockedBy(getHasName(Items.POLISHED_DEEPSLATE), has(Items.POLISHED_DEEPSLATE)).save(consumer, getSaveLocation(POLISHED_DEEPSLATE_BUTTON.get()));
        ShapedRecipeBuilder.shaped(POLISHED_DEEPSLATE_PRESSURE_PLATE.get()).define('#', Items.POLISHED_DEEPSLATE).pattern("##").unlockedBy(getHasName(Items.POLISHED_DEEPSLATE), has(Items.POLISHED_DEEPSLATE)).save(consumer, getSaveLocation(POLISHED_DEEPSLATE_PRESSURE_PLATE.get()));
        ShapelessRecipeBuilder.shapeless(POLISHED_SHALE_BUTTON.get()).requires(POLISHED_SHALE.get()).unlockedBy(getHasName(POLISHED_SHALE.get()), has(POLISHED_SHALE.get())).save(consumer, getSaveLocation(POLISHED_SHALE_BUTTON.get()));
        ShapedRecipeBuilder.shaped(POLISHED_SHALE_PRESSURE_PLATE.get()).define('#', POLISHED_SHALE.get()).pattern("##").unlockedBy(getHasName(POLISHED_SHALE.get()), has(POLISHED_SHALE.get())).save(consumer, getSaveLocation(POLISHED_SHALE_PRESSURE_PLATE.get()));

        ShapelessRecipeBuilder.shapeless(FAIRY_LIGHT.get()).requires(PINECONE.get()).requires(Items.TORCH).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(consumer, getSaveLocation(FAIRY_LIGHT.get()));
        ShapelessRecipeBuilder.shapeless(SOUL_FAIRY_LIGHT.get()).requires(PINECONE.get()).requires(Items.SOUL_TORCH).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(consumer, getSaveLocation(SOUL_FAIRY_LIGHT.get()));

        ShapedRecipeBuilder.shaped(ICICLE_BLOCK.get(), 2).define('#', ICICLES.get()).pattern("##").pattern("##").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(consumer, getSaveLocation(ICICLE_BLOCK.get()));
        ShapedRecipeBuilder.shaped(CHISELED_ICICLE_BLOCK.get(), 2).define('#', ICICLE_BLOCK.get()).pattern("#").pattern("#").unlockedBy(getHasName(ICICLE_BLOCK.get()), has(ICICLE_BLOCK.get())).save(consumer, getSaveLocation(CHISELED_ICICLE_BLOCK.get()));
        stonecutting(ICICLE_BLOCK.get(), CHISELED_ICICLE_BLOCK.get(), 1, consumer);
        ShapedRecipeBuilder.shaped(ICICLE_DOOR.get(), 3).define('#', ICICLES.get()).pattern("##").pattern("##").pattern("##").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(consumer, getSaveLocation(ICICLE_DOOR.get()));
        ShapedRecipeBuilder.shaped(ICICLE_TRAPDOOR.get(), 2).define('#', ICICLES.get()).pattern("###").pattern("###").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(consumer, getSaveLocation(ICICLE_TRAPDOOR.get()));
        ShapedRecipeBuilder.shaped(ICICLE_BARS.get(), 16).define('#', ICICLE_BLOCK.get()).pattern("###").pattern("###").unlockedBy(getHasName(ICICLE_BLOCK.get()), has(ICICLE_BLOCK.get())).save(consumer, getSaveLocation(ICICLE_BARS.get()));
        stonecutting(ICICLE_BLOCK.get(), ICICLE_BARS.get(), 2, consumer);
        ShapedRecipeBuilder.shaped(ICE_LANTERN.get()).define('#', ICICLES.get()).define('N', NIGHTSHADE.get()).pattern("#").pattern("N").pattern("#").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(consumer, getSaveLocation(ICE_LANTERN.get()));
        ShapedRecipeBuilder.shaped(PINECONE_SHINGLES.get(), 2).define('#', PINECONE.get()).pattern("##").pattern("##").unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(consumer, getSaveLocation(PINECONE_SHINGLES.get()));

        conditionalRecipe(ShapedRecipeBuilder.shaped(DRY_MOSSY_COBBLESTONE_BRICKS.get(), 4).define('#', DRY_MOSSY_COBBLESTONE.get()).pattern("##").pattern("##").unlockedBy(getHasName(DRY_MOSSY_COBBLESTONE.get()), has(DRY_MOSSY_COBBLESTONE.get())), new ModLoadedCondition("caverns_and_chasms"), consumer, getSaveLocation(DRY_MOSSY_COBBLESTONE_BRICKS.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(DRY_MOSSY_COBBLESTONE_TILES.get(), 4).define('#', DRY_MOSSY_COBBLESTONE_BRICKS.get()).pattern("##").pattern("##").unlockedBy(getHasName(DRY_MOSSY_COBBLESTONE_BRICKS.get()), has(DRY_MOSSY_COBBLESTONE_BRICKS.get())), new ModLoadedCondition("caverns_and_chasms"), consumer, getSaveLocation(DRY_MOSSY_COBBLESTONE_TILES.get()));
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICKS.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICK_SLAB.get(), 2, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICK_STAIRS.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICK_WALL.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        verticalSlabStonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICK_VERTICAL_SLAB.get(), new AndCondition(new ModLoadedCondition("caverns_and_chasms"), getQuarkCondition("vertical_slabs")), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILES.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILE_SLAB.get(), 2, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILE_STAIRS.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILE_WALL.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        verticalSlabStonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILE_VERTICAL_SLAB.get(), new AndCondition(new ModLoadedCondition("caverns_and_chasms"), getQuarkCondition("vertical_slabs")), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILES.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILE_SLAB.get(), 2, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILE_STAIRS.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        stonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILE_WALL.get(), 1, new ModLoadedCondition("caverns_and_chasms"), consumer);
        verticalSlabStonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILE_VERTICAL_SLAB.get(), new AndCondition(new ModLoadedCondition("caverns_and_chasms"), getQuarkCondition("vertical_slabs")), consumer);
        
        stairs(Blocks.SNOW_BLOCK, SNOW_STAIRS.get(), consumer);
        slab(Blocks.SNOW_BLOCK, SNOW_SLAB.get(), consumer);
        verticalSlab(SNOW_VERTICAL_SLAB.get(), SNOW_SLAB.get(), consumer);

        stonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICKS.get(), 1, consumer);
        stonecutting(Blocks.PACKED_ICE, CHISELED_PACKED_ICE_BRICKS.get(), 1, consumer);
        stonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICK_SLAB.get(), 2, consumer);
        stonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICK_STAIRS.get(), 1, consumer);
        stonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICK_WALL.get(), 1, consumer);
        verticalSlabStonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICK_VERTICAL_SLAB.get(), consumer);
        stonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICKS.get(), 1, consumer);
        stonecutting(Blocks.BLUE_ICE, CHISELED_BLUE_ICE_BRICKS.get(), 1, consumer);
        stonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICK_SLAB.get(), 2, consumer);
        stonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICK_STAIRS.get(), 1, consumer);
        stonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICK_WALL.get(), 1, consumer);
        verticalSlabStonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICK_VERTICAL_SLAB.get(), consumer);

        stonecutting(SHALE.get(), POLISHED_SHALE_BRICKS.get(), 1, consumer);
        stonecutting(SHALE.get(), CHISELED_POLISHED_SHALE_BRICKS.get(), 1, consumer);
        stonecutting(SHALE.get(), POLISHED_SHALE_BRICK_SLAB.get(), 2, consumer);
        stonecutting(SHALE.get(), POLISHED_SHALE_BRICK_STAIRS.get(), 1, consumer);
        stonecutting(SHALE.get(), POLISHED_SHALE_BRICK_WALL.get(), 1, consumer);
        verticalSlabStonecutting(SHALE.get(), POLISHED_SHALE_BRICK_VERTICAL_SLAB.get(), consumer);
        stonecutting(SHALE.get(), POLISHED_SHALE.get(), 1, consumer);
        stonecutting(SHALE.get(), POLISHED_SHALE_SLAB.get(), 2, consumer);
        stonecutting(SHALE.get(), POLISHED_SHALE_STAIRS.get(), 1, consumer);
        stonecutting(SHALE.get(), POLISHED_SHALE_WALL.get(), 1, consumer);
        verticalSlabStonecutting(SHALE.get(), POLISHED_SHALE_VERTICAL_SLAB.get(), consumer);
        stonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICKS.get(), 1, consumer);
        stonecutting(POLISHED_SHALE.get(), CHISELED_POLISHED_SHALE_BRICKS.get(), 1, consumer);
        stonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICK_SLAB.get(), 2, consumer);
        stonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICK_STAIRS.get(), 1, consumer);
        stonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICK_WALL.get(), 1, consumer);
        verticalSlabStonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICK_VERTICAL_SLAB.get(), consumer);

        stonecutting(LUNALITE.get(), CUT_LUNALITE_BRICKS.get(), 1, consumer);
        stonecutting(LUNALITE.get(), CHISELED_CUT_LUNALITE_BRICKS.get(), 1, consumer);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_BRICK_SLAB.get(), 2, consumer);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_BRICK_STAIRS.get(), 1, consumer);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_BRICK_WALL.get(), 1, consumer);
        verticalSlabStonecutting(LUNALITE.get(), CUT_LUNALITE_BRICK_VERTICAL_SLAB.get(), consumer);
        stonecutting(LUNALITE.get(), CUT_LUNALITE.get(), 1, consumer);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_SLAB.get(), 2, consumer);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_STAIRS.get(), 1, consumer);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_WALL.get(), 1, consumer);
        verticalSlabStonecutting(LUNALITE.get(), CUT_LUNALITE_VERTICAL_SLAB.get(), consumer);
        stonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICKS.get(), 1, consumer);
        stonecutting(CUT_LUNALITE.get(), CHISELED_CUT_LUNALITE_BRICKS.get(), 1, consumer);
        stonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICK_SLAB.get(), 2, consumer);
        stonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICK_STAIRS.get(), 1, consumer);
        stonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICK_WALL.get(), 1, consumer);
        verticalSlabStonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICK_VERTICAL_SLAB.get(), consumer);

        blockset(null, Blocks.PACKED_ICE, null, PACKED_ICE_SLAB.get(), PACKED_ICE_STAIRS.get(), null, PACKED_ICE_VERTICAL_SLAB.get(), true, consumer);
        blockset(null, Blocks.BLUE_ICE, null, BLUE_ICE_SLAB.get(), BLUE_ICE_STAIRS.get(), null, BLUE_ICE_VERTICAL_SLAB.get(), true, consumer);
        blockset(Blocks.PACKED_ICE, PACKED_ICE_BRICKS.get(), CHISELED_PACKED_ICE_BRICKS.get(), PACKED_ICE_BRICK_SLAB.get(), PACKED_ICE_BRICK_STAIRS.get(), PACKED_ICE_BRICK_WALL.get(), PACKED_ICE_BRICK_VERTICAL_SLAB.get(), true, consumer);
        blockset(Blocks.BLUE_ICE, BLUE_ICE_BRICKS.get(), CHISELED_BLUE_ICE_BRICKS.get(), BLUE_ICE_BRICK_SLAB.get(), BLUE_ICE_BRICK_STAIRS.get(), BLUE_ICE_BRICK_WALL.get(), BLUE_ICE_BRICK_VERTICAL_SLAB.get(), true, consumer);
        blockset(Blocks.SNOW_BLOCK, SNOW_BRICKS.get(), null, SNOW_BRICK_SLAB.get(), SNOW_BRICK_STAIRS.get(), SNOW_BRICK_WALL.get(), SNOW_BRICK_VERTICAL_SLAB.get(), true, consumer);
        blockset(null, DRY_MOSSY_COBBLESTONE.get(), null, DRY_MOSSY_COBBLESTONE_SLAB.get(), DRY_MOSSY_COBBLESTONE_STAIRS.get(), DRY_MOSSY_COBBLESTONE_WALL.get(), DRY_MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), true, consumer);
        blockset(null, DRY_MOSSY_STONE_BRICKS.get(), null, DRY_MOSSY_STONE_BRICK_SLAB.get(), DRY_MOSSY_STONE_BRICK_STAIRS.get(), DRY_MOSSY_STONE_BRICK_WALL.get(), DRY_MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), true, consumer);
        blockset(null, SHALE.get(), null, SHALE_SLAB.get(), SHALE_STAIRS.get(), SHALE_WALL.get(), SHALE_VERTICAL_SLAB.get(), true, consumer);
        blockset(SHALE.get(), POLISHED_SHALE.get(), null, POLISHED_SHALE_SLAB.get(), POLISHED_SHALE_STAIRS.get(), POLISHED_SHALE_WALL.get(), POLISHED_SHALE_VERTICAL_SLAB.get(), true, consumer);
        blockset(POLISHED_SHALE.get(), POLISHED_SHALE_BRICKS.get(), CHISELED_POLISHED_SHALE_BRICKS.get(), POLISHED_SHALE_BRICK_SLAB.get(), POLISHED_SHALE_BRICK_STAIRS.get(), POLISHED_SHALE_BRICK_WALL.get(), POLISHED_SHALE_BRICK_VERTICAL_SLAB.get(), true, consumer);
        blockset(null, PINECONE_SHINGLES.get(), null, PINECONE_SHINGLE_SLAB.get(), PINECONE_SHINGLE_STAIRS.get(), PINECONE_SHINGLE_WALL.get(), PINECONE_SHINGLE_VERTICAL_SLAB.get(), false, consumer);
        blockset(null, LUNALITE.get(), null, LUNALITE_SLAB.get(), LUNALITE_STAIRS.get(), LUNALITE_WALL.get(), LUNALITE_VERTICAL_SLAB.get(), true, consumer);
        blockset(LUNALITE.get(), CUT_LUNALITE.get(), null, CUT_LUNALITE_SLAB.get(), CUT_LUNALITE_STAIRS.get(), CUT_LUNALITE_WALL.get(), CUT_LUNALITE_VERTICAL_SLAB.get(), true, consumer);
        blockset(CUT_LUNALITE.get(), CUT_LUNALITE_BRICKS.get(), CHISELED_CUT_LUNALITE_BRICKS.get(), CUT_LUNALITE_BRICK_SLAB.get(), CUT_LUNALITE_BRICK_STAIRS.get(), CUT_LUNALITE_BRICK_WALL.get(), CUT_LUNALITE_BRICK_VERTICAL_SLAB.get(), true, consumer);
        blockset(null, SMOOTH_LUNALITE.get(), null, SMOOTH_LUNALITE_SLAB.get(), SMOOTH_LUNALITE_STAIRS.get(), null, SMOOTH_LUNALITE_VERTICAL_SLAB.get(), true, consumer);
        blockset(GINGERBREAD_BLOCK.get(), GINGERBREAD_BRICKS.get(), null, GINGERBREAD_BRICK_SLAB.get(), GINGERBREAD_BRICK_STAIRS.get(), GINGERBREAD_BRICK_WALL.get(), GINGERBREAD_BRICK_VERTICAL_SLAB.get(), false, consumer);
        blockset(GLAZED_GINGERBREAD_BLOCK.get(), GLAZED_GINGERBREAD_BRICKS.get(), null, GLAZED_GINGERBREAD_BRICK_SLAB.get(), GLAZED_GINGERBREAD_BRICK_STAIRS.get(), GLAZED_GINGERBREAD_BRICK_WALL.get(), GLAZED_GINGERBREAD_BRICK_VERTICAL_SLAB.get(), false, consumer);
        blockset(null, DRY_MOSSY_COBBLESTONE_BRICKS.get(), null, DRY_MOSSY_COBBLESTONE_BRICK_SLAB.get(), DRY_MOSSY_COBBLESTONE_BRICK_STAIRS.get(), DRY_MOSSY_COBBLESTONE_BRICK_WALL.get(), DRY_MOSSY_COBBLESTONE_BRICK_VERTICAL_SLAB.get(), true, consumer);
        blockset(null, DRY_MOSSY_COBBLESTONE_TILES.get(), null, DRY_MOSSY_COBBLESTONE_TILE_SLAB.get(), DRY_MOSSY_COBBLESTONE_TILE_STAIRS.get(), DRY_MOSSY_COBBLESTONE_TILE_WALL.get(), DRY_MOSSY_COBBLESTONE_TILE_VERTICAL_SLAB.get(), true, consumer);
        blockset(null, LAVENDER_THATCH.get(), null, LAVENDER_THATCH_SLAB.get(), LAVENDER_THATCH_STAIRS.get(), null, LAVENDER_THATCH_VERTICAL_SLAB.get(), false, consumer);

        // wood sets
        woodSet(WindsweptItemTags.HOLLY_LOGS, HOLLY_PLANKS.get(), HOLLY_SLAB.get(), HOLLY_STAIRS.get(), HOLLY_LOG.get(), HOLLY_WOOD.get(), STRIPPED_HOLLY_LOG.get(), STRIPPED_HOLLY_WOOD.get(), HOLLY_BOATS.getFirst().get(), HOLLY_BOATS.getSecond().get(), HOLLY_BUTTON.get(), HOLLY_DOOR.get(), HOLLY_TRAPDOOR.get(), HOLLY_FENCE.get(), HOLLY_FENCE_GATE.get(), HOLLY_PRESSURE_PLATE.get(), HOLLY_SIGNS.getFirst().get(), HOLLY_VERTICAL_SLAB.get(), HOLLY_POST.get(), STRIPPED_HOLLY_POST.get(), HOLLY_BOARDS.get(), HOLLY_BEEHIVE.get(), HOLLY_LADDER.get(), HOLLY_BOOKSHELF.get(), HOLLY_CHEST.get(), HOLLY_TRAPPED_CHEST.get(), LARGE_HOLLY_BOAT.get(), HOLLY_FURNACE_BOAT.get(), VERTICAL_HOLLY_PLANKS.get(), HOLLY_CABINET.get(), consumer);
        woodSet(WindsweptItemTags.CHESTNUT_LOGS, CHESTNUT_PLANKS.get(), CHESTNUT_SLAB.get(), CHESTNUT_STAIRS.get(), CHESTNUT_LOG.get(), CHESTNUT_WOOD.get(), STRIPPED_CHESTNUT_LOG.get(), STRIPPED_CHESTNUT_WOOD.get(), CHESTNUT_BOATS.getFirst().get(), CHESTNUT_BOATS.getSecond().get(), CHESTNUT_BUTTON.get(), CHESTNUT_DOOR.get(), CHESTNUT_TRAPDOOR.get(), CHESTNUT_FENCE.get(), CHESTNUT_FENCE_GATE.get(), CHESTNUT_PRESSURE_PLATE.get(), CHESTNUT_SIGNS.getFirst().get(), CHESTNUT_VERTICAL_SLAB.get(), CHESTNUT_POST.get(), STRIPPED_CHESTNUT_POST.get(), CHESTNUT_BOARDS.get(), CHESTNUT_BEEHIVE.get(), CHESTNUT_LADDER.get(), CHESTNUT_BOOKSHELF.get(), CHESTNUT_CHEST.get(), CHESTNUT_TRAPPED_CHEST.get(), LARGE_CHESTNUT_BOAT.get(), CHESTNUT_FURNACE_BOAT.get(), VERTICAL_CHESTNUT_PLANKS.get(), CHESTNUT_CABINET.get(), consumer);
        woodSet(WindsweptItemTags.PINE_LOGS, PINE_PLANKS.get(), PINE_SLAB.get(), PINE_STAIRS.get(), PINE_LOG.get(), PINE_WOOD.get(), STRIPPED_PINE_LOG.get(), STRIPPED_PINE_WOOD.get(), PINE_BOATS.getFirst().get(), PINE_BOATS.getSecond().get(), PINE_BUTTON.get(), PINE_DOOR.get(), PINE_TRAPDOOR.get(), PINE_FENCE.get(), PINE_FENCE_GATE.get(), PINE_PRESSURE_PLATE.get(), PINE_SIGNS.getFirst().get(), PINE_VERTICAL_SLAB.get(), PINE_POST.get(), STRIPPED_PINE_POST.get(), PINE_BOARDS.get(), PINE_BEEHIVE.get(), PINE_LADDER.get(), PINE_BOOKSHELF.get(), PINE_CHEST.get(), PINE_TRAPPED_CHEST.get(), LARGE_PINE_BOAT.get(), PINE_FURNACE_BOAT.get(), VERTICAL_PINE_PLANKS.get(), PINE_CABINET.get(), consumer);
        leafSet(WindsweptItemTags.HOLLY_LOGS, HOLLY_LEAVES.get(), HOLLY_HEDGE.get(), HOLLY_LEAF_CARPET.get(), HOLLY_LEAF_PILE.get(), consumer);
        leafSet(WindsweptItemTags.CHESTNUT_LOGS, CHESTNUT_LEAVES.get(), CHESTNUT_HEDGE.get(), CHESTNUT_LEAF_CARPET.get(), CHESTNUT_LEAF_PILE.get(), consumer);
        leafSet(WindsweptItemTags.PINE_LOGS, PINE_LEAVES.get(), PINE_HEDGE.get(), PINE_LEAF_CARPET.get(), PINE_LEAF_PILE.get(), consumer);

        // compressed blocks
        compressedBlock(WILD_BERRY_BASKET.get(), WILD_BERRIES.get(), new OrCondition(new ModLoadedCondition("berry_good"), getQuarkCondition("berry_sack")), consumer);
        compressedBlock(HOLLY_BERRY_BASKET.get(), HOLLY_BERRIES.get(), new OrCondition(new ModLoadedCondition("berry_good"), getQuarkCondition("berry_sack")), consumer);
        compressedBlock(CHESTNUT_CRATE.get(), CHESTNUTS.get(), getQuarkCondition("apple_crate"), consumer);
        compressedBlock(ROASTED_CHESTNUT_CRATE.get(), ROASTED_CHESTNUTS.get(), getQuarkCondition("apple_crate"), consumer);
        compressedBlock(GINGER_ROOT_CRATE.get(), GINGER_ROOT.get(), new OrCondition(getQuarkCondition("apple_crate"), new ModLoadedCondition("farmersdelight")), consumer);
        compressedBlock(RED_MUSHROOM_BASKET.get(), Items.RED_MUSHROOM, new OrCondition(new ModLoadedCondition("berry_good"), getQuarkCondition("apple_crate")), consumer);
        compressedBlock(BROWN_MUSHROOM_BASKET.get(), Items.BROWN_MUSHROOM, new OrCondition(new ModLoadedCondition("berry_good"), getQuarkCondition("apple_crate")), consumer);
        compressedBlock(FROZEN_FLESH_BLOCK.get(), FROZEN_FLESH.get(), new OrCondition(new ModLoadedCondition("caverns_and_chasms"), getQuarkCondition("apple_crate")), consumer);
        compressedBlock(PINECONE_BLOCK.get(), PINECONE.get(), consumer);
        compressedBlock(GINGERBREAD_COOKIE_BLOCK.get(), GINGERBREAD_COOKIE.get(), consumer);
        compressedBlock(CANDY_CANE_BLOCK.get(), CANDY_CANE.get(), consumer);
        compressedBlock(LAVENDER_BALE.get(), LAVENDER.get(), consumer);
    }

    private static void conditionalRecipe(RecipeBuilder recipe, ICondition condition, Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
    }

    private static void conditionalCooking(ItemLike ingredient, ItemLike result, ICondition condition, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, .35f, 200).unlockedBy(getHasName(ingredient), has(ingredient)), condition, consumer, getSaveLocation(result));
        conditionalRecipe(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), result, .35f, 600).unlockedBy(getHasName(ingredient), has(ingredient)), condition, consumer, getSaveLocation(getName(result) + "_from_campfire_cooking"));
        conditionalRecipe(SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), result, .35f, 100).unlockedBy(getHasName(ingredient), has(ingredient)), condition, consumer, getSaveLocation(getName(result) + "_from_smoking"));
    }

    private static void blockset(ItemLike ingredient, Block block, Block chiseled, Block slab, Block stairs, Block wall, Block verticalSlab, boolean stonecutter, Consumer<FinishedRecipe> consumer) {
        if (ingredient != null)
            ShapedRecipeBuilder.shaped(block, 4).define('#', ingredient).pattern("##").pattern("##").unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(block));

        if (chiseled != null) {
            ShapedRecipeBuilder.shaped(chiseled).define('#', slab).pattern("#").pattern("#").unlockedBy(getHasName(block), has(block)).save(consumer, getSaveLocation(chiseled));

            if (stonecutter)
                stonecutting(block, chiseled, 1, consumer);
        }

        if (slab != null) {
            slab(block, slab, consumer);

            if (stonecutter)
                stonecutting(block, slab, 2, consumer);
        }

        if (stairs != null) {
            stairs(block, stairs, consumer);

            if (stonecutter)
                stonecutting(block, stairs, 1, consumer);
        }

        if (wall != null) {
            wall(block, wall, consumer);

            if (stonecutter)
                stonecutting(block, wall, 1, consumer);
        }

        if (verticalSlab != null) {
            verticalSlab(verticalSlab, slab, consumer);

            if (stonecutter)
                verticalSlabStonecutting(block, verticalSlab, consumer);
        }

    }

    private static void verticalSlabStonecutting(ItemLike block, Block verticalSlab, ICondition condition, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), verticalSlab, 2).unlockedBy(getHasName(block), has(block)), condition, consumer, getSaveLocation(getName(verticalSlab) + "_from_" + getName(block) + "_stonecutting"));
    }

    private static void verticalSlabStonecutting(ItemLike block, Block verticalSlab, Consumer<FinishedRecipe> consumer) {
        verticalSlabStonecutting(block, verticalSlab, getQuarkCondition("vertical_slabs"), consumer);
    }

    private static void compressedBlock(Block block, ItemLike item, ICondition condition, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(ShapedRecipeBuilder.shaped(block).define('#', item).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(item), has(item)), condition, consumer, getSaveLocation(block));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(item, 9).requires(block).unlockedBy(getHasName(block), has(block)), condition, consumer, getSaveLocation(getName(block) + "_revert"));
    }

    private static void compressedBlock(Block block, ItemLike item, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(block).define('#', item).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(item), has(item)).save(consumer, getSaveLocation(block));
        ShapelessRecipeBuilder.shapeless(item, 9).requires(block).unlockedBy(getHasName(block), has(block)).save(consumer, getSaveLocation(getName(block) + "_revert"));
    }

    private static void woodSet(TagKey<Item> logs, Block planks, Block slab, Block stairs, Block log, Block wood, Block strippedLog, Block strippedWood, ItemLike boat, ItemLike chestBoat, Block button, Block door, Block trapdoor, Block fence, Block fenceGate, Block pressurePlate, Block sign, Block verticalSlab, Block post, Block strippedPost, Block boards, Block beehive, Block ladder, Block bookshelf, Block chest, Block trappedChest, Item largeBoat, Item furnaceBoat, Block verticalPlanks, Block cabinet, Consumer<FinishedRecipe> consumer) {
        woodenBoat(consumer, boat, planks);
        ShapelessRecipeBuilder.shapeless(chestBoat).group("chest_boat").requires(Tags.Items.CHESTS_WOODEN).requires(boat).unlockedBy(getHasName(boat), has(boat)).save(consumer, getSaveLocation(chestBoat));
        ShapelessRecipeBuilder.shapeless(button).group("wooden_button").requires(planks).unlockedBy(getHasName(planks), has(planks)).save(consumer, getSaveLocation(button));
        ShapedRecipeBuilder.shaped(door, 3).group("wooden_door").define('#', planks).pattern("##").pattern("##").pattern("##").unlockedBy(getHasName(planks), has(planks)).save(consumer, getSaveLocation(door));
        ShapedRecipeBuilder.shaped(fence, 3).group("wooden_fence").define('#', planks).define('S', Items.STICK).pattern("#S#").pattern("#S#").unlockedBy(getHasName(planks), has(planks)).save(consumer, getSaveLocation(fence));
        ShapedRecipeBuilder.shaped(fenceGate).group("wooden_fence_gate").define('#', planks).define('S', Items.STICK).pattern("S#S").pattern("S#S").unlockedBy(getHasName(planks), has(planks)).save(consumer, getSaveLocation(fenceGate));
        ShapedRecipeBuilder.shaped(pressurePlate).group("wooden_pressure_plate").define('#', planks).pattern("##").unlockedBy(getHasName(planks), has(planks)).save(consumer, getSaveLocation(pressurePlate));
        ShapedRecipeBuilder.shaped(sign, 3).group("wooden_sign").define('#', planks).define('S', Items.STICK).pattern("###").pattern("###").pattern(" S ").unlockedBy(getHasName(planks), has(planks)).save(consumer, getSaveLocation(sign));
        ShapedRecipeBuilder.shaped(trapdoor, 2).group("wooden_trapdoor").define('#', planks).pattern("###").pattern("###").unlockedBy(getHasName(planks), has(planks)).save(consumer, getSaveLocation(trapdoor));
        planksFromLogs(consumer, planks, logs);
        woodFromLogs(consumer, wood, log);
        woodFromLogs(consumer, strippedWood, strippedLog);
        slab(planks, slab, "wooden_slab", consumer);
        stairs(planks, stairs, "wooden_stairs", consumer);
        verticalSlab(verticalSlab, slab, consumer);
        post(post, wood, consumer);
        post(strippedPost, strippedWood, consumer);
        conditionalRecipe(ShapedRecipeBuilder.shaped(boards, 3).group("wooden_boards").define('#', planks).pattern("#").pattern("#").pattern("#").unlockedBy(getHasName(planks), has(planks)), new ModLoadedCondition("woodworks"), consumer, getSaveLocation(boards));
        conditionalRecipe(ShapedRecipeBuilder.shaped(beehive).group("wooden_beehive").define('#', planks).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").unlockedBy(getHasName(planks), has(planks)), new ModLoadedCondition("woodworks"), consumer, getSaveLocation(beehive));
        conditionalRecipe(ShapedRecipeBuilder.shaped(ladder, 4).group("wooden_ladders").define('#', planks).define('S', Items.STICK).pattern("S S").pattern("S#S").pattern("S S").unlockedBy(getHasName(planks), has(planks)), new OrCondition(new ModLoadedCondition("woodworks"), getQuarkCondition("variant_ladders")), consumer, getSaveLocation(ladder));
        conditionalRecipe(ShapedRecipeBuilder.shaped(bookshelf).group("wooden_bookshelves").define('#', planks).define('B', Items.BOOK).pattern("###").pattern("BBB").pattern("###").unlockedBy(getHasName(planks), has(planks)), new OrCondition(new ModLoadedCondition("woodworks"), getQuarkCondition("variant_bookshelves")), consumer, getSaveLocation(bookshelf));
        conditionalRecipe(ShapedRecipeBuilder.shaped(chest).group("wooden_chests").define('#', planks).pattern("###").pattern("# #").pattern("###").unlockedBy(getHasName(planks), has(planks)), new OrCondition(new ModLoadedCondition("woodworks"), getQuarkCondition("variant_chests")), consumer, getSaveLocation(chest));
        conditionalRecipe(ShapedRecipeBuilder.shaped(chest, 4).group("wooden_chests").define('#', logs).pattern("###").pattern("# #").pattern("###").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])), new AndCondition(getQuarkCondition("wood_to_chest_recipes"), getQuarkCondition("variant_chests")), consumer, getSaveLocation(getItemName(chest) + "_wood"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(trappedChest).requires(chest).requires(Items.TRIPWIRE_HOOK).unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])), new OrCondition(new ModLoadedCondition("woodworks"), getQuarkCondition("variant_chests")), consumer, getSaveLocation(trappedChest));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(furnaceBoat).requires(Items.FURNACE).requires(boat).unlockedBy(getHasName(boat), has(boat)), new ModLoadedCondition("boatload"), consumer, getSaveLocation(furnaceBoat));
        conditionalRecipe(ShapedRecipeBuilder.shaped(largeBoat).group("wooden_chests").define('#', planks).define('B', boat).pattern("#B#").pattern("###").unlockedBy(getHasName(boat), has(boat)), new ModLoadedCondition("boatload"), consumer, getSaveLocation(largeBoat));
        conditionalRecipe(ShapedRecipeBuilder.shaped(verticalPlanks, 3).define('#', planks).pattern("#").pattern("#").pattern("#").unlockedBy(getHasName(planks), has(planks)), getQuarkCondition("vertical_planks"), consumer, getSaveLocation(verticalPlanks));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(planks).requires(verticalPlanks).unlockedBy(getHasName(verticalPlanks), has(verticalPlanks)), getQuarkCondition("vertical_planks"), consumer, getSaveLocation(getItemName(verticalPlanks) + "_revert"));
        conditionalRecipe(ShapedRecipeBuilder.shaped(cabinet).define('#', slab).define('T', trapdoor).pattern("###").pattern("T T").pattern("###").unlockedBy(getHasName(trapdoor), has(trapdoor)), new ModLoadedCondition("farmersdelight"), consumer, getSaveLocation(cabinet));
    }

    private static void leafSet(TagKey<Item> log, ItemLike leaves, ItemLike hedge, ItemLike leafCarpet, ItemLike leafPile, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(ShapedRecipeBuilder.shaped(hedge, 2).define('#', log).define('L', leaves).pattern("L").pattern("#").unlockedBy(getHasName(leaves), has(leaves)), getQuarkCondition("hedges"), consumer, getSaveLocation(hedge));
        conditionalRecipe(ShapedRecipeBuilder.shaped(leafCarpet, 3).define('#', leaves).pattern("##").unlockedBy(getHasName(leaves), has(leaves)), getQuarkCondition("leaf_carpet"), consumer, getSaveLocation(leafCarpet));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(leafPile, 4).requires(leaves).unlockedBy(getHasName(leaves), has(leaves)), new ModLoadedCondition("woodworks"), consumer, getSaveLocation(leafPile));
        conditionalRecipe(ShapedRecipeBuilder.shaped(leaves).define('#', leafPile).pattern("##").pattern("##").unlockedBy(getHasName(leafPile), has(leafPile)), new ModLoadedCondition("woodworks"), consumer, getSaveLocation(getItemName(leaves) + "_from_leaf_pile"));
    }

    private static void flowerToDye(Block flower, Item dye, Consumer<FinishedRecipe> consumer) {
        String dyeName = getItemName(dye);
        ShapelessRecipeBuilder.shapeless(dye).group(dyeName).requires(flower).unlockedBy(getHasName(flower), has(flower)).save(consumer, getSaveLocation(dyeName + "_from_" + getItemName(flower)));
    }

    private static void tallFlowerToDye(Block flower, Item dye, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(dye, 2).group(getName(dye)).requires(flower).unlockedBy(getHasName(flower), has(flower)).save(consumer, getSaveLocation(getName(dye) + "_from_" + getName(flower)));
    }

    private static void cooking(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, .35f, 200).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(result));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), result, .35f, 600).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(result) + "_from_campfire_cooking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), result, .35f, 100).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(result) + "_from_smoking"));
    }

    private static void stonecutting(ItemLike ingredient, ItemLike result, int amount, Consumer<FinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), result, amount).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(ingredient) + "_from_" + getName(result) + "_stonecutting"));
    }

    private static void stonecutting(ItemLike ingredient, ItemLike result, int amount, ICondition condition, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), result, amount).unlockedBy(getHasName(ingredient), has(ingredient)), condition, consumer, getSaveLocation(getName(ingredient) + "_from_" + getName(result) + "_stonecutting"));
    }

    private static void stairs(ItemLike ingredient, ItemLike stairs, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(stairs, 4).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###").unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(stairs)));
    }

    private static void stairs(ItemLike ingredient, ItemLike stairs, String group, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(stairs, 4).group(group).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###").unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(stairs)));
    }

    private static void wall(ItemLike ingredient, ItemLike wall, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(wall, 6).define('#', ingredient).pattern("###").pattern("###").unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(wall)));
    }

    private static void slab(ItemLike ingredient, ItemLike slab, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(slab, 6).define('#', ingredient).pattern("###").unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(slab)));
    }

    private static void slab(ItemLike ingredient, ItemLike slab, String group, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(slab, 6).group(group).define('#', ingredient).pattern("###").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(slab)));
    }

    private static void verticalSlab(ItemLike verticalSlab, ItemLike slab, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(ShapedRecipeBuilder.shaped(verticalSlab, 3).define('#', slab).pattern("#").pattern("#").pattern("#").unlockedBy(getHasName(slab), has(slab)), getQuarkCondition("vertical_slabs"), consumer, getSaveLocation(verticalSlab));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(slab).requires(verticalSlab).unlockedBy(getHasName(verticalSlab), has(verticalSlab)), getQuarkCondition("vertical_slabs"), consumer, getSaveLocation(getName(verticalSlab) + "_revert"));
    }

    private static void post(ItemLike post, ItemLike wood, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(ShapedRecipeBuilder.shaped(post, 8).define('#', wood).pattern("#").pattern("#").pattern("#").unlockedBy(getHasName(wood), has(wood)), getQuarkCondition("wooden_posts"), consumer, getSaveLocation(getName(post)));
    }

    // Util //

    private static QuarkFlagRecipeCondition getQuarkCondition(String flag) {
        return new QuarkFlagRecipeCondition(new ResourceLocation("blueprint", "quark_flag"), flag);
    }

    private static String getName(ItemLike object) {
        return ForgeRegistries.ITEMS.getKey(object.asItem()).getPath();
    }

    private static ResourceLocation getSaveLocation(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem());
    }

    private static ResourceLocation getSaveLocation(String name) {
        return Windswept.location(name);
    }

}

package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Predicate;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;
import static net.minecraft.world.item.CreativeModeTabs.*;

public final class WindsweptCreativeTabs {
    public static void setupTabEditors() {
        CreativeModeTabContentsPopulator.mod(Windswept.MOD_ID)
                .tab(BUILDING_BLOCKS)
                .addItemsBefore(Ingredient.of(Blocks.BAMBOO_BLOCK), HOLLY_LOG, HOLLY_WOOD, STRIPPED_HOLLY_LOG, STRIPPED_HOLLY_WOOD, HOLLY_PLANKS)
                .addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), HOLLY_BOARDS)
                .addItemsBefore(Ingredient.of(Blocks.BAMBOO_BLOCK),
                        HOLLY_STAIRS, HOLLY_SLAB, HOLLY_FENCE, HOLLY_FENCE_GATE, HOLLY_DOOR, HOLLY_TRAPDOOR, HOLLY_PRESSURE_PLATE, HOLLY_BUTTON,
                        CHESTNUT_LOG, CHESTNUT_WOOD, STRIPPED_CHESTNUT_LOG, STRIPPED_CHESTNUT_WOOD, CHESTNUT_PLANKS)
                .addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), CHESTNUT_BOARDS)
                .addItemsBefore(Ingredient.of(Blocks.BAMBOO_BLOCK),
                        CHESTNUT_STAIRS, CHESTNUT_SLAB, CHESTNUT_FENCE, CHESTNUT_FENCE_GATE, CHESTNUT_DOOR, CHESTNUT_TRAPDOOR, CHESTNUT_PRESSURE_PLATE, CHESTNUT_BUTTON,
                        PINE_LOG, PINE_WOOD, WEATHERED_PINE_LOG, WEATHERED_PINE_WOOD, STRIPPED_PINE_LOG, STRIPPED_PINE_WOOD, PINE_PLANKS)
                .addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), PINE_BOARDS)
                .addItemsBefore(Ingredient.of(Blocks.BAMBOO_BLOCK),
                        PINE_STAIRS, PINE_SLAB, PINE_FENCE, PINE_FENCE_GATE, PINE_DOOR, PINE_TRAPDOOR, PINE_PRESSURE_PLATE, PINE_BUTTON)
                .addItemsAfter(Ingredient.of(Blocks.MOSSY_COBBLESTONE_WALL), DRY_MOSSY_COBBLESTONE, DRY_MOSSY_COBBLESTONE_STAIRS, DRY_MOSSY_COBBLESTONE_SLAB, DRY_MOSSY_COBBLESTONE_WALL)
                .addItemsAfter(Ingredient.of(Blocks.MOSSY_STONE_BRICK_WALL), DRY_MOSSY_STONE_BRICKS, DRY_MOSSY_STONE_BRICK_STAIRS, DRY_MOSSY_STONE_BRICK_SLAB, DRY_MOSSY_STONE_BRICK_WALL)
                .addItemsAfter(Ingredient.of(Blocks.POLISHED_ANDESITE_SLAB), SHALE, SHALE_STAIRS, SHALE_SLAB, SHALE_WALL, POLISHED_SHALE, POLISHED_SHALE_STAIRS, POLISHED_SHALE_SLAB, POLISHED_SHALE_WALL,
                        POLISHED_SHALE_BRICKS, CHISELED_POLISHED_SHALE_BRICKS, POLISHED_SHALE_BRICK_STAIRS, POLISHED_SHALE_BRICK_SLAB, POLISHED_SHALE_BRICK_WALL)
                .addItemsAfter(Ingredient.of(Blocks.MUD_BRICK_WALL), PINECONE_BLOCK, PINECONE_SHINGLES, PINECONE_SHINGLE_STAIRS, PINECONE_SHINGLE_SLAB,
                        LUNALITE, LUNALITE_STAIRS, LUNALITE_SLAB, LUNALITE_WALL, CUT_LUNALITE, CUT_LUNALITE_STAIRS, CUT_LUNALITE_SLAB, CUT_LUNALITE_WALL,
                        CUT_LUNALITE_BRICKS, CHISELED_CUT_LUNALITE_BRICKS, CUT_LUNALITE_BRICK_STAIRS, CUT_LUNALITE_BRICK_SLAB, CUT_LUNALITE_BRICK_WALL, SMOOTH_LUNALITE, SMOOTH_LUNALITE_STAIRS, SMOOTH_LUNALITE_SLAB)
                .addItemsAfter(Ingredient.of(Blocks.POLISHED_BLACKSTONE_BRICK_WALL), GINGERBREAD_BLOCK, GLAZED_GINGERBREAD_BLOCK, GINGERBREAD_COOKIE_BLOCK, GINGERBREAD_DOOR,
                        GINGERBREAD_TRAPDOOR, GINGERBREAD_BRICKS, GINGERBREAD_BRICK_STAIRS, GINGERBREAD_BRICK_SLAB, GINGERBREAD_BRICK_WALL,
                        GLAZED_GINGERBREAD_BRICKS, GLAZED_GINGERBREAD_BRICK_STAIRS, GLAZED_GINGERBREAD_BRICK_SLAB, GLAZED_GINGERBREAD_BRICK_WALL, CANDY_CANE_BLOCK,
                        ICICLE_BLOCK, CHISELED_ICICLE_BLOCK, ICICLE_DOOR, ICICLE_TRAPDOOR, ICICLE_BARS,
                        () -> Blocks.ICE, ICE_SHEET,
                        () -> Blocks.PACKED_ICE, PACKED_ICE_STAIRS, PACKED_ICE_SLAB, PACKED_ICE_BRICKS, CHISELED_PACKED_ICE_BRICKS, PACKED_ICE_BRICK_STAIRS, PACKED_ICE_BRICK_SLAB, PACKED_ICE_BRICK_WALL,
                        () -> Blocks.BLUE_ICE, BLUE_ICE_STAIRS, PACKED_ICE_SLAB, BLUE_ICE_BRICKS, CHISELED_BLUE_ICE_BRICKS, BLUE_ICE_BRICK_STAIRS, BLUE_ICE_BRICK_SLAB, BLUE_ICE_BRICK_WALL,
                        () -> Blocks.SNOW_BLOCK, SNOW_STAIRS, SNOW_SLAB, SNOW_BRICKS, SNOW_BRICK_STAIRS, SNOW_BRICK_SLAB, SNOW_BRICK_WALL,
                        LAVENDER_THATCH, LAVENDER_THATCH_STAIRS, LAVENDER_THATCH_SLAB)
                .addItemsAfter(modLoaded(WindsweptConstants.MOSSY_COBBLESTONE_BRICK_WALL, "caverns_and_chasms"), DRY_MOSSY_COBBLESTONE_BRICKS, DRY_MOSSY_COBBLESTONE_BRICK_STAIRS, DRY_MOSSY_COBBLESTONE_BRICK_SLAB, DRY_MOSSY_COBBLESTONE_BRICK_WALL)
                .addItemsAfter(modLoaded(WindsweptConstants.MOSSY_COBBLESTONE_TILE_WALL, "caverns_and_chasms"), DRY_MOSSY_COBBLESTONE_TILES, DRY_MOSSY_COBBLESTONE_TILE_STAIRS, DRY_MOSSY_COBBLESTONE_TILE_SLAB, DRY_MOSSY_COBBLESTONE_TILE_WALL)

                .tab(FUNCTIONAL_BLOCKS)
                .addItemsBefore(Ingredient.of(Blocks.BAMBOO_SIGN),
                        HOLLY_SIGNS.getFirst(), HOLLY_HANGING_SIGNS.getFirst(),
                        CHESTNUT_SIGNS.getFirst(), CHESTNUT_HANGING_SIGNS.getFirst(),
                        PINE_SIGNS.getFirst(), PINE_HANGING_SIGNS.getFirst())
                .addItemsBefore(Ingredient.of(Blocks.ANVIL), FAIRY_LIGHT, SOUL_FAIRY_LIGHT)
                .addItemsBefore(modLoaded(Blocks.ANVIL, "caverns_and_chasms"), CUPRIC_FAIRY_LIGHT)
                .addItemsBefore(modLoaded(Blocks.ANVIL, "endergetic"), ENDER_FAIRY_LIGHT)
                .addItemsBefore(Ingredient.of(Blocks.ANVIL), NIGHT_FAIRY_LIGHT, REDSTONE_FAIRY_LIGHT)
                .addItemsBefore(Ingredient.of(Blocks.CHAIN), ICE_LANTERN)
                .addItemsAfter(Ingredient.of(Blocks.CHAIN), ICE_CHAIN)
                .addItemsAfter(Ingredient.of(Blocks.JUKEBOX), CARVED_PINECONE_BLOCK, WILL_O_THE_WISP, ELDER_WING, ELDER_ORNAMENT, DREAM_CATCHER, FROSTBITER_TROPHY)
                .addItemsAfter(modLoaded(Blocks.LADDER/*WindsweptConstants.BAMBOO_LADDER*/, "woodworks"), HOLLY_LADDER, CHESTNUT_LADDER, PINE_LADDER)
                .addItemsAfter(modLoaded(Blocks.BEEHIVE/*WindsweptConstants.BAMBOO_BEEHIVE*/, "woodworks"), HOLLY_BEEHIVE, CHESTNUT_BEEHIVE, PINE_BEEHIVE)
                .addItemsAfter(Ingredient.of(Blocks.BOOKSHELF), HOLLY_BOOKSHELF, /*CHISELED_HOLLY_BOOKSHELF, */CHESTNUT_BOOKSHELF, /*CHISELED_CHESTNUT_BOOKSHELF, */PINE_BOOKSHELF/*, CHISELED_PINE_BOOKSHELF*/)
                .addItemsAfter(modLoaded(Blocks.CHEST/*WindsweptConstants.BAMBOO_CLOSET*/, "woodworks"), HOLLY_CHEST, CHESTNUT_CHEST, PINE_CHEST)
                .addItemsAfter(Ingredient.of(Blocks.SUSPICIOUS_GRAVEL), SUSPICIOUS_SNOW)

                .tab(COLORED_BLOCKS)
                .addItemsAfter(Ingredient.of(Blocks.PINK_CARPET), WHITE_PINECONE_SHINGLES, LIGHT_GRAY_PINECONE_SHINGLES, GRAY_PINECONE_SHINGLES, BLACK_PINECONE_SHINGLES, BROWN_PINECONE_SHINGLES, RED_PINECONE_SHINGLES, ORANGE_PINECONE_SHINGLES, YELLOW_PINECONE_SHINGLES, LIME_PINECONE_SHINGLES, GREEN_PINECONE_SHINGLES, CYAN_PINECONE_SHINGLES, LIGHT_BLUE_PINECONE_SHINGLES, BLUE_PINECONE_SHINGLES, PURPLE_PINECONE_SHINGLES, MAGENTA_PINECONE_SHINGLES, PINK_PINECONE_SHINGLES,
                        WHITE_PINECONE_SHINGLE_STAIRS, LIGHT_GRAY_PINECONE_SHINGLE_STAIRS, GRAY_PINECONE_SHINGLE_STAIRS, BLACK_PINECONE_SHINGLE_STAIRS, BROWN_PINECONE_SHINGLE_STAIRS, RED_PINECONE_SHINGLE_STAIRS, ORANGE_PINECONE_SHINGLE_STAIRS, YELLOW_PINECONE_SHINGLE_STAIRS, LIME_PINECONE_SHINGLE_STAIRS, GREEN_PINECONE_SHINGLE_STAIRS, CYAN_PINECONE_SHINGLE_STAIRS, LIGHT_BLUE_PINECONE_SHINGLE_STAIRS, BLUE_PINECONE_SHINGLE_STAIRS, PURPLE_PINECONE_SHINGLE_STAIRS, MAGENTA_PINECONE_SHINGLE_STAIRS, PINK_PINECONE_SHINGLE_STAIRS,
                        WHITE_PINECONE_SHINGLE_SLAB, LIGHT_GRAY_PINECONE_SHINGLE_SLAB, GRAY_PINECONE_SHINGLE_SLAB, BLACK_PINECONE_SHINGLE_SLAB, BROWN_PINECONE_SHINGLE_SLAB, RED_PINECONE_SHINGLE_SLAB, ORANGE_PINECONE_SHINGLE_SLAB, YELLOW_PINECONE_SHINGLE_SLAB, LIME_PINECONE_SHINGLE_SLAB, GREEN_PINECONE_SHINGLE_SLAB, CYAN_PINECONE_SHINGLE_SLAB, LIGHT_BLUE_PINECONE_SHINGLE_SLAB, BLUE_PINECONE_SHINGLE_SLAB, PURPLE_PINECONE_SHINGLE_SLAB, MAGENTA_PINECONE_SHINGLE_SLAB, PINK_PINECONE_SHINGLE_SLAB)

                .tab(NATURAL_BLOCKS)
                .addItemsAfter(Ingredient.of(Blocks.PODZOL), GELISOL)
                .addItemsAfter(Ingredient.of(Blocks.DIRT_PATH), GELISOL_PATH)
                .addItemsAfter(Ingredient.of(Blocks.ROOTED_DIRT), GINGER_SOIL)
                .addItemsAfter(Ingredient.of(Blocks.MOSS_BLOCK), DRY_MOSS_BLOCK)
                .addItemsAfter(Ingredient.of(Blocks.MOSS_CARPET), DRY_MOSS_CARPET)
                .addItemsAfter(Ingredient.of(Blocks.FERN), GELISOL_GRASS)
                .addItemsBefore(Ingredient.of(Blocks.MUSHROOM_STEM), HOLLY_LOG, CHESTNUT_LOG, PINE_LOG, WEATHERED_PINE_LOG)
                .addItemsBefore(Ingredient.of(Blocks.AZALEA), HOLLY_SAPLING, CHESTNUT_SAPLING, PINE_SAPLING)
                .addItemsBefore(Ingredient.of(Blocks.AZALEA_LEAVES), HOLLY_LEAVES)
                .addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), HOLLY_LEAF_PILE)
                .addItemsBefore(Ingredient.of(Blocks.AZALEA_LEAVES), CHESTNUT_LEAVES)
                .addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), CHESTNUT_LEAF_PILE)
                .addItemsBefore(Ingredient.of(Blocks.AZALEA_LEAVES), PINE_LEAVES)
                .addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), PINE_LEAF_PILE)
                .addItemsAfter(Ingredient.of(Blocks.LILY_OF_THE_VALLEY), FOXGLOVE, MOSS_CAMPION, MIMOSA, SNOWDROP, NIGHTSHADE, BLUEBELLS,
                        BLUE_ROSE, BLUE_ROSE_BUSH, RED_ROSE, RED_ROSE_BUSH, WHITE_ROSE, WHITE_ROSE_BUSH, YELLOW_ROSE, YELLOW_ROSE_BUSH)
                .addItemsBefore(Ingredient.of(Blocks.CRIMSON_ROOTS), MOSSY_SPROUTS, DRY_MOSSY_SPROUTS, SNOWY_SPROUTS, WILD_GINGER)
                .addItemsAfter(Ingredient.of(Blocks.SUNFLOWER), LIONS_TAIL, LUPINE)
                .addItemsAfter(Ingredient.of(Blocks.BAMBOO), PINECONE, PINECONE_BLOCK, ELDER_WING, ELDER_ORNAMENT, DREAM_CATCHER)
                .addItemsAfter(Ingredient.of(Blocks.SUGAR_CANE), LAVENDER, ICICLES)
                .addItemsAfter(Ingredient.of(Blocks.HONEY_BLOCK), PINECONE_JAM_BLOCK)
                .addItemsAfter(Ingredient.of(Items.SWEET_BERRIES), WILD_BERRIES)
                .addItemsAfter(modLoaded(WindsweptConstants.SWEET_BERRY_PIPS, "berry_good"), WILD_BERRY_PIPS)
                .addItemsAfter(Ingredient.of(Blocks.HAY_BLOCK), LAVENDER_BALE, CHESTNUT_CRATE, ROASTED_CHESTNUT_CRATE, GINGER_ROOT_CRATE, HOLLY_BERRY_BASKET, WILD_BERRY_BASKET)
                .addItemsBefore(modLoaded(Blocks.COBWEB, "caverns_and_chasms"), FROZEN_FLESH_BLOCK)
                .addItemsAfter(Ingredient.of(Blocks.FLOWERING_AZALEA), HOLLY_WREATH, VINE_WREATH, PINECONE_WREATH, CHERRY_WREATH)
                .addItemsAfter(Ingredient.of(Blocks.ACACIA_SAPLING), FLOWERING_ACACIA_SAPLING)
                .addItemsAfter(Ingredient.of(Blocks.ACACIA_LEAVES), FLOWERING_ACACIA_LEAVES)
                .addItemsAfter(modLoaded(Blocks.ACACIA_LEAVES, "woodworks"), FLOWERING_ACACIA_LEAF_PILE)
                .addItemsAfter(Ingredient.of(Blocks.PINK_PETALS), YELLOW_PETALS)

                .tab(REDSTONE_BLOCKS)
                .addItemsAfter(Ingredient.of(Blocks.COMPARATOR), REDSTONE_FAIRY_LIGHT)
                .addItemsAfter(Ingredient.of(Blocks.HONEY_BLOCK), PINECONE_JAM_BLOCK)
                .addItemsAfter(Ingredient.of(Blocks.NOTE_BLOCK), CARVED_PINECONE_BLOCK)
                .addItemsAfter(modLoaded(Blocks.TRAPPED_CHEST/*WindsweptConstants.TRAPPED_BAMBOO_CLOSET*/, "woodworks"), TRAPPED_HOLLY_CHEST, TRAPPED_CHESTNUT_CHEST, TRAPPED_PINE_CHEST)

                .tab(COMBAT)
                .addItemsAfter(Ingredient.of(Items.SPECTRAL_ARROW), FROST_ARROW)
                .addItemsAfter(Ingredient.of(Items.TURTLE_HELMET), ANTLER_HELMET, FEATHER_CLOAK, SNOW_BOOTS, LAVENDER_CROWN)

                .tab(INGREDIENTS)
                .addItemsAfter(Ingredient.of(Items.HONEYCOMB), PINECONE, HOLLY_BERRIES)
                .addItemsAfter(Ingredient.of(Items.FEATHER), ELDER_FEATHER)
                .addItemsAfter(Ingredient.of(Items.WHEAT), LAVENDER)
                .addItemsAfter(Ingredient.of(Items.SNOWBALL), ICICLES, FROZEN_BRANCH)
                .addItemsAfter(Ingredient.of(Items.PIGLIN_BANNER_PATTERN), SNOW_GOLEM_BANNER_PATTERN, SNOW_CHARGE_BANNER_PATTERN, ROSE_FLOWER_BANNER_PATTERN)
                .addItemsAfter(Ingredient.of(Items.SNORT_POTTERY_SHERD), HOOT_POTTERY_SHERD, PLUMAGE_POTTERY_SHERD, OFFSHOOT_POTTERY_SHERD, FLAKE_POTTERY_SHERD, DRUPES_POTTERY_SHERD)

                .tab(TOOLS_AND_UTILITIES)
                .addItemsBefore(Ingredient.of(Items.BAMBOO_RAFT), HOLLY_BOAT.getFirst(), HOLLY_BOAT.getSecond())
                .addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), HOLLY_FURNACE_BOAT, LARGE_HOLLY_BOAT)
                .addItemsBefore(Ingredient.of(Items.BAMBOO_RAFT), CHESTNUT_BOAT.getFirst(), CHESTNUT_BOAT.getSecond())
                .addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), CHESTNUT_FURNACE_BOAT, LARGE_CHESTNUT_BOAT)
                .addItemsBefore(Ingredient.of(Items.BAMBOO_RAFT), PINE_BOAT.getFirst(), PINE_BOAT.getSecond())
                .addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), PINE_FURNACE_BOAT, LARGE_PINE_BOAT)
                .addItemsBefore(Ingredient.of(Items.FISHING_ROD), WOODEN_BUCKET, WOODEN_WATER_BUCKET, WOODEN_POWDER_SNOW_BUCKET, WOODEN_MILK_BUCKET)
                .addItemsAfter(Ingredient.of(Items.MUSIC_DISC_OTHERSIDE), MUSIC_DISC_RAIN, MUSIC_DISC_SNOW, MUSIC_DISC_BUMBLEBEE)
                .addItemsAfter(Ingredient.of(Items.CARROT_ON_A_STICK), HOLLY_BERRIES_ON_A_STICK)

                .tab(FOOD_AND_DRINKS)
                .addItemsAfter(Ingredient.of(Items.HONEY_BOTTLE), PINECONE_JAM_BOTTLE, LAVENDER_TEA, GINGER_TEA)
                .addItemsBefore(Ingredient.of(Items.CAKE), CANDY_CANE)
                .addItemsAfter(Ingredient.of(Items.COOKIE), GINGERBREAD_COOKIE)
                .addItemsAfter(Ingredient.of(Items.SWEET_BERRIES), WILD_BERRIES)
                .addItemsAfter(Ingredient.of(Items.GOLDEN_CARROT), CHESTNUTS, ROASTED_CHESTNUTS)
                .addItemsAfter(Ingredient.of(Items.RABBIT_STEW), CHESTNUT_SOUP, GOAT_STEW)
                .addItemsAfter(Ingredient.of(Items.PUMPKIN_PIE), MUTTON_PIE)
                .addItemsAfter(Ingredient.of(Items.CAKE), CHRISTMAS_PUDDING)
                .addItemsAfter(Ingredient.of(Items.POISONOUS_POTATO), GINGER_ROOT, SPICY_SNOW_CONE, SWEET_SNOW_CONE, MINTY_SNOW_CONE)
                .addItemsAfter(Ingredient.of(Items.COOKED_MUTTON), GOAT, COOKED_GOAT)
                .addItemsAfter(Ingredient.of(Items.MILK_BUCKET), WOODEN_MILK_BUCKET)
                .addItemsAfter(Ingredient.of(Items.ROTTEN_FLESH), FROZEN_FLESH)

                .tab(SPAWN_EGGS)
                .addSpawnEggsAlphabetically(CHILLED_SPAWN_EGG, FROSTBITER_SPAWN_EGG);
    }

    private static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
        return stack -> Ingredient.of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
    }

    public static Predicate<ItemStack> modLoaded(ResourceLocation location, String... modids) {
        return modLoaded(ForgeRegistries.ITEMS.getValue(location), modids);
    }

}

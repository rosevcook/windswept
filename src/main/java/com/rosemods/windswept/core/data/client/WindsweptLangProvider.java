package com.rosemods.windswept.core.data.client;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptDamageSources;
import com.rosemods.windswept.core.registry.*;
import com.rosemods.windswept.integration.jei.WindsweptPlugin;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptLangProvider extends LanguageProvider {

    public WindsweptLangProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // Items //

        this.translateItem(HOLLY_BERRIES);
        this.translateItem(HOLLY_BOATS.getFirst());
        this.add(HOLLY_BOATS.getSecond().get(), "Holly Boat with Chest");
        this.translateItem(CHESTNUT_BOATS.getFirst());
        this.add(CHESTNUT_BOATS.getSecond().get(), "Chestnut Boat with Chest");
        this.translateItem(CHESTNUTS);
        this.translateItem(ROASTED_CHESTNUTS);
        this.translateItem(CHESTNUT_SOUP);
        this.translateItem(CHESTNUT_RISOTTO);
        this.translateItem(CHESTNUT_CHICKEN_PLATTER);
        this.translateItem(WOODEN_BUCKET);
        this.translateItem(WOODEN_WATER_BUCKET);
        this.translateItem(WOODEN_MILK_BUCKET);
        this.translateItem(WOODEN_POWDER_SNOW_BUCKET);
        this.translateItem(WILD_BERRIES);
        this.translateItem(WILD_BERRY_COOKIE);
        this.translateItem(WILD_BERRY_JUICE);
        this.translateItem(WILD_BERRY_PIPS);
        this.add(SWEET_BERRY_BOWL.get(), "Bowl of Sweet Berries");
        this.add(WILD_BERRY_BOWL.get(), "Bowl of Wild Berries");
        this.translateItem(MUTTON_PIE);
        this.add(GOAT.get(), "Raw Chevon");
        this.add(COOKED_GOAT.get(), "Cooked Chevon");
        this.translateItem(GOAT_STEW);
        this.add(GOAT_SHANKS.get(), "Raw Chevon Shanks");
        this.add(COOKED_GOAT_SHANKS.get(), "Cooked Chevon Shanks");
        this.add(FOUL_BERRY_BOWL.get(), "Bowl of Foul Berries");
        this.translateItem(FROZEN_FLESH);
        this.translateItem(FROZEN_BRANCH);
        this.translateItem(SNOW_BOOTS);
        this.translateItem(FROST_ARROW);
        this.add(HOLLY_BERRIES_ON_A_STICK.get(), "Holly Berries on a Stick");
        this.translateBannerPattern(SNOW_CHARGE_BANNER_PATTERN, "snow_charge");
        this.translateBannerPattern(SNOW_GOLEM_BANNER_PATTERN, "snow_golem");
        this.translateBannerPattern(ROSE_FLOWER_BANNER_PATTERN, "rose_flower");
        this.translateMusicDisc(MUSIC_DISC_RAIN, "rose - rain");
        this.translateMusicDisc(MUSIC_DISC_SNOW, "rose - snow");
        this.translateMusicDisc(MUSIC_DISC_BUMBLEBEE, "rose - bumblebee");
        this.translateItem(CHILLED_SPAWN_EGG);
        this.translateItem(FROSTBITER_SPAWN_EGG);

        this.add(HOLLY_FURNACE_BOAT.get(), "Holly Boat with Furnace");
        this.translateItem(LARGE_HOLLY_BOAT);

        this.add(CHESTNUT_FURNACE_BOAT.get(), "Chestnut Boat with Furnace");
        this.translateItem(LARGE_CHESTNUT_BOAT);

        // Blocks //

        this.translateBlock(STRIPPED_HOLLY_LOG);
        this.translateBlock(STRIPPED_HOLLY_WOOD);
        this.translateBlock(HOLLY_LOG);
        this.translateBlock(HOLLY_WOOD);
        this.translateBlock(HOLLY_PLANKS);
        this.translateBlock(VERTICAL_HOLLY_PLANKS);
        this.translateBlock(HOLLY_SLAB);
        this.translateBlock(HOLLY_STAIRS);
        this.translateBlock(HOLLY_FENCE);
        this.translateBlock(HOLLY_FENCE_GATE);
        this.translateBlock(HOLLY_PRESSURE_PLATE);
        this.translateBlock(HOLLY_DOOR);
        this.translateBlock(HOLLY_TRAPDOOR);
        this.translateBlock(HOLLY_BUTTON);
        this.translateSign(HOLLY_SIGNS, "holly");
        this.translateBlock(HOLLY_LEAVES);
        this.translateBlock(HOLLY_SAPLING);
        this.translateBlock(POTTED_HOLLY_SAPLING);
        this.translateBlock(HOLLY_BEEHIVE);
        this.add(HOLLY_HEDGE.get(), "Holly Leaf Hedge");
        this.translateBlock(HOLLY_LEAF_CARPET);
        this.add(HOLLY_LEAF_PILE.get(), "Pile of Holly Leaves");
        this.translateBlock(HOLLY_LADDER);
        this.translateBlock(HOLLY_BOOKSHELF);
        this.translateBlock(HOLLY_BOARDS);
        this.translateBlock(HOLLY_CABINET);
        this.translateBlock(HOLLY_VERTICAL_SLAB);
        this.translateBlock(HOLLY_POST);
        this.translateBlock(STRIPPED_HOLLY_POST);
        this.translateBlock(HOLLY_CHEST);
        this.translateBlock(HOLLY_TRAPPED_CHEST);
        this.translateBlock(HOLLY_WREATH);
        this.add(HOLLY_BERRY_BASKET.get(), "Basket of Holly Berries");

        this.translateBlock(STRIPPED_CHESTNUT_LOG);
        this.translateBlock(STRIPPED_CHESTNUT_WOOD);
        this.translateBlock(CHESTNUT_LOG);
        this.translateBlock(CHESTNUT_WOOD);
        this.translateBlock(CHESTNUT_PLANKS);
        this.translateBlock(VERTICAL_CHESTNUT_PLANKS);
        this.translateBlock(CHESTNUT_SLAB);
        this.translateBlock(CHESTNUT_STAIRS);
        this.translateBlock(CHESTNUT_FENCE);
        this.translateBlock(CHESTNUT_FENCE_GATE);
        this.translateBlock(CHESTNUT_PRESSURE_PLATE);
        this.translateBlock(CHESTNUT_DOOR);
        this.translateBlock(CHESTNUT_TRAPDOOR);
        this.translateBlock(CHESTNUT_BUTTON);
        this.translateSign(CHESTNUT_SIGNS, "chestnut");
        this.translateBlock(CHESTNUT_LEAVES);
        this.translateBlock(CHESTNUT_SAPLING);
        this.translateBlock(POTTED_CHESTNUT_SAPLING);
        this.translateBlock(CHESTNUT_BEEHIVE);
        this.add(CHESTNUT_HEDGE.get(), "Chestnut Leaf Hedge");
        this.translateBlock(CHESTNUT_LEAF_CARPET);
        this.add(CHESTNUT_LEAF_PILE.get(), "Pile of Chestnut Leaves");
        this.translateBlock(CHESTNUT_LADDER);
        this.translateBlock(CHESTNUT_BOOKSHELF);
        this.translateBlock(CHESTNUT_BOARDS);
        this.translateBlock(CHESTNUT_CABINET);
        this.translateBlock(CHESTNUT_VERTICAL_SLAB);
        this.translateBlock(CHESTNUT_POST);
        this.translateBlock(STRIPPED_CHESTNUT_POST);
        this.translateBlock(CHESTNUT_CHEST);
        this.translateBlock(CHESTNUT_TRAPPED_CHEST);
        this.add(CHESTNUT_CRATE.get(), "Crate of Chestnuts");
        this.add(ROASTED_CHESTNUT_CRATE.get(), "Crate of Roasted Chestnuts");

        this.translateBlock(SNOWY_SPROUTS);
        this.translateBlock(GELISOL_SPROUTS);
        this.translateBlock(DRY_MOSS_SPROUTS);

        this.add("block.windswept.snow_carpet", "Snow Carpet");
        this.translateBlock(SNOW_STAIRS);
        this.translateBlock(SNOW_SLAB);
        this.translateBlock(SNOW_VERTICAL_SLAB);
        this.translateBlock(SNOW_BRICKS);
        this.translateBlock(SNOW_BRICK_STAIRS);
        this.translateBlock(SNOW_BRICK_SLAB);
        this.translateBlock(SNOW_BRICK_WALL);
        this.translateBlock(SNOW_BRICK_VERTICAL_SLAB);

        this.translateBlock(PACKED_ICE_STAIRS);
        this.translateBlock(PACKED_ICE_SLAB);
        this.translateBlock(PACKED_ICE_VERTICAL_SLAB);
        this.translateBlock(PACKED_ICE_BRICKS);
        this.translateBlock(CHISELED_PACKED_ICE_BRICKS);
        this.translateBlock(PACKED_ICE_BRICK_STAIRS);
        this.translateBlock(PACKED_ICE_BRICK_SLAB);
        this.translateBlock(PACKED_ICE_BRICK_WALL);
        this.translateBlock(PACKED_ICE_BRICK_VERTICAL_SLAB);

        this.translateBlock(BLUE_ICE_STAIRS);
        this.translateBlock(BLUE_ICE_SLAB);
        this.translateBlock(BLUE_ICE_VERTICAL_SLAB);
        this.translateBlock(BLUE_ICE_BRICKS);
        this.translateBlock(CHISELED_BLUE_ICE_BRICKS);
        this.translateBlock(BLUE_ICE_BRICK_STAIRS);
        this.translateBlock(BLUE_ICE_BRICK_SLAB);
        this.translateBlock(BLUE_ICE_BRICK_WALL);
        this.translateBlock(BLUE_ICE_BRICK_VERTICAL_SLAB);

        this.translateBlock(CUT_ICE);
        this.translateBlock(ICE_SHEET);
        this.translateBlock(CUT_ICE_SHEET);

        this.translateBlock(RED_ROSE_BUSH);
        this.translateBlock(PINK_ROSE_BUSH);
        this.translateBlock(BLUE_ROSE_BUSH);
        this.translateBlock(WHITE_ROSE_BUSH);
        this.translateBlock(YELLOW_ROSE_BUSH);
        this.translateBlock(WITHER_ROSE_BUSH);

        this.translateBlock(RED_ROSE);
        this.translateBlock(PINK_ROSE);
        this.translateBlock(BLUE_ROSE);
        this.translateBlock(WHITE_ROSE);
        this.translateBlock(YELLOW_ROSE);
        this.translateBlock(FOXGLOVE);
        this.translateBlock(BLUEBELLS);
        this.translateBlock(SNOWDROP);
        this.translateBlock(MOSS_CAMPION);
        this.translateBlock(NIGHTSHADE);

        this.translateBlock(POTTED_RED_ROSE);
        this.translateBlock(POTTED_PINK_ROSE);
        this.translateBlock(POTTED_BLUE_ROSE);
        this.translateBlock(POTTED_WHITE_ROSE);
        this.translateBlock(POTTED_YELLOW_ROSE);
        this.translateBlock(POTTED_FOXGLOVE);
        this.translateBlock(POTTED_BLUEBELLS);
        this.translateBlock(POTTED_NIGHTSHADE);
        this.translateBlock(POTTED_SNOWDROP);
        this.translateBlock(POTTED_MOSS_CAMPION);
        this.translateBlock(POTTED_SNOWY_SPROUTS);
        this.translateBlock(POTTED_GELISOL_SPROUTS);
        this.translateBlock(POTTED_DRY_MOSS_SPROUTS);

        this.translateBlock(WILD_BERRY_BUSH);
        this.add(WILD_BERRY_BASKET.get(), "Basket of Wild Berries");

        this.translateBlock(ICICLES);
        this.translateBlock(ICICLE_BLOCK);
        this.translateBlock(CHISELED_ICICLE_BLOCK);
        this.translateBlock(ICICLE_DOOR);
        this.translateBlock(ICICLE_TRAPDOOR);
        this.translateBlock(ICE_LANTERN);

        this.translateBlock(DRY_MOSS_CARPET);
        this.translateBlock(DRY_MOSS_BLOCK);
        this.translateBlock(DRY_MOSSY_COBBLESTONE);
        this.translateBlock(DRY_MOSSY_COBBLESTONE_SLAB);
        this.translateBlock(DRY_MOSSY_COBBLESTONE_STAIRS);
        this.translateBlock(DRY_MOSSY_COBBLESTONE_WALL);
        this.translateBlock(DRY_MOSSY_COBBLESTONE_VERTICAL_SLAB);
        this.translateBlock(DRY_MOSSY_STONE_BRICKS);
        this.translateBlock(DRY_MOSSY_STONE_BRICK_SLAB);
        this.translateBlock(DRY_MOSSY_STONE_BRICK_STAIRS);
        this.translateBlock(DRY_MOSSY_STONE_BRICK_WALL);
        this.translateBlock(DRY_MOSSY_STONE_BRICK_VERTICAL_SLAB);

        this.add(RED_MUSHROOM_BASKET.get(), "Basket of Red Mushrooms");
        this.add(BROWN_MUSHROOM_BASKET.get(), "Basket of Brown Mushrooms");
        this.add(GLOW_SHROOM_BASKET.get(), "Basket of Glow Shrooms");

        this.translateBlock(GELISOL);
        this.translateBlock(GELISOL_PATH);
        this.translateBlock(FROSTBITER_TROPHY);
        this.translateBlock(FROZEN_FLESH_BLOCK);

        // Entities //

        this.translateEntity(WindsweptEntityTypes.CHILLED);
        this.translateEntity(WindsweptEntityTypes.FROSTBITER);
        this.translateEntity(WindsweptEntityTypes.FROST_ARROW);

        // Effects //

        this.translateEffect(WindsweptEffects.THORNS, "Causes damage to enemies when they attack you.");

        this.translatePotion(WindsweptEffects.THORNS_POTION, "Prickling");
        this.translatePotion(WindsweptEffects.LONG_THORNS_POTION, "Prickling");
        this.translatePotion(WindsweptEffects.STRONG_THORNS_POTION, "Prickling");

        // Attributes

        this.translateAttribute(WindsweptAttributes.SNOW_SPEED);

        // Biomes //

        this.translateBiome(WindsweptBiomes.CHESTNUT_FOREST);
        this.translateBiome(WindsweptBiomes.SNOWY_CHESTNUT_FOREST);
        this.translateBiome(WindsweptBiomes.TUNDRA);

        // Enchantments //

        this.translateEnchantment(WindsweptEnchantments.SLIPPING_CURSE, "Curse of Slipping", "Causes the wearer to slip on any block as if it was ice whilst damaging the boots.");

        // Damage Sources //

        this.translateDamageSource(WindsweptDamageSources.HOLLY_LEAVES,
                player -> player + " was ripped to death by holly leaves",
                (player, entity) -> player + " was ripped to death by holly leaves whilst trying to escape " + entity);

        this.translateDamageSource(WindsweptDamageSources.HOLLY_SAPLING,
                player -> player + " was ripped to death by holly sapling",
                (player, entity) -> player + " was ripped to death by holly sapling whilst trying to escape " + entity);

        this.translateDamageSource(WindsweptDamageSources.HOLLY_HEDGE,
                player -> player + " was ripped to death by holly leaf hedge",
                (player, entity) -> player + " was ripped to death by holly leaf hedge whilst trying to escape " + entity);

        // Slabfish //

        this.add("entity.environmental.slabfish.type.chestnut", "Chestnut");
        this.add("entity.environmental.slabfish.type.grove", "Grove");

        // JEI Info //

        this.jeiInfo(MUSIC_DISC_RAIN, "Dropped by a Drowned if killed by a Skeleton.");
        this.jeiInfo(MUSIC_DISC_SNOW, "Dropped by a Chilled if killed by a Skeleton.");
        this.jeiInfo(MUSIC_DISC_BUMBLEBEE, "Sometimes dropped when shearing a Beehive for Honeycombs.");

        this.jeiInfo(RED_ROSE_BUSH, "Bonemeal a Red Rose and it will grow into a Red Rose Bush!");
        this.jeiInfo(PINK_ROSE_BUSH, "Bonemeal a Pink Rose and it will grow into a Pink Rose Bush!");
        this.jeiInfo(BLUE_ROSE_BUSH, "Bonemeal a Blue Rose and it will grow into a Blue Rose Bush! This can also be found in Chestnut Forests");
        this.jeiInfo(WHITE_ROSE_BUSH, "Bonemeal a White Rose and it will grow into a White Rose Bush! This can also be found in Snowy Chestnut Forests");
        this.jeiInfo(YELLOW_ROSE_BUSH, "Bonemeal a Yellow Rose and it will grow into a Yellow Rose Bush!");
        this.jeiInfo(WITHER_ROSE_BUSH, "Bonemeal a Wither Rose and it will grow into a Wither Rose Bush!");

        this.jeiInfo(WILD_BERRIES, "Wild Berries can be found growing in snowy biomes but are more commonly found in Groves.");
        this.jeiInfo(NIGHTSHADE, "Nightshades can be found very rarely growing in large patches under trees. They can also be brewing into Night Vision potions.");
        this.jeiInfo(BLUEBELLS, "Bluebells are small flowers that grow in patches in Dark Oak and Birch Forests.");
        this.jeiInfo(FOXGLOVE, "Foxgloves are found in any Taiga biome. Foxes can sometimes be found holding them in their mouths.");
        this.jeiInfo(SNOWY_SPROUTS, "Snowy sprouts are small plants that grow in round patches in snowy areas. They only grow on snow blocks but when collected with shears, can also be placed on grass and dirt.");

        this.jeiInfo(RED_ROSE, "Red Roses can be found in Taiga biomes. They can also be bonemealed into Red Rose Bushes.");
        this.jeiInfo(PINK_ROSE, "Pink Roses can be found in Snowy biomes. They can also be bonemealed into Pink Rose Bushes.");
        this.jeiInfo(BLUE_ROSE, "Blue Roses can be found in Snowy biomes. They can also be bonemealed into Blue Rose Bushes.");
        this.jeiInfo(WHITE_ROSE, "White Roses can be found in Snowy biomes. They can also be bonemealed into White Rose Bushes.");
        this.jeiInfo(YELLOW_ROSE, "Yellow Roses can be found in Taiga biomes. They can also be bonemealed into Yellow Rose Bushes.");

        this.jeiInfo(WOODEN_BUCKET, "Wooden buckets are cheap buckets with a durability of 24. Durability is taken when a fluid exits the bucket.");
        this.jeiInfo(SNOW_BOOTS, "Snow boots allow you to walk faster on any snowy block. They can be dyed any colour you like. They also allow you to walk on Powder Snow.");
        this.jeiInfo(WILD_BERRY_BOWL, "Wild Berry bowls condense 3 Wild Berries into a single item. They are exactly 3 times the saturation and hunger of Wild Berries.");
        this.jeiInfo(SWEET_BERRY_BOWL, "Sweet Berry bowls condense 3 Sweet Berries into a single item. They are exactly 3 times the saturation and hunger of Sweet Berries.");
        this.jeiInfo(FOUL_BERRY_BOWL, "Foul Berry bowls condense 3 Foul Berries into a single item. They are exactly 3 times the saturation and hunger of Foul Berries.");
    }

    private void translateItem(RegistryObject<? extends Item> item) {
        this.add(item.get(), toUpper(ForgeRegistries.ITEMS, item));
    }

    private void translateBlock(RegistryObject<? extends Block> block) {
        this.add(block.get(), toUpper(ForgeRegistries.BLOCKS, block));
    }

    private void translateEntity(RegistryObject<? extends EntityType<?>> entity) {
        this.add(entity.get(), toUpper(ForgeRegistries.ENTITY_TYPES, entity));
    }

    private void translateBiome(BiomeSubRegistryHelper.KeyedBiome biome) {
        this.add("biome." + Windswept.MOD_ID + "." + ForgeRegistries.BIOMES.getKey(biome.get()).getPath(), toUpper(ForgeRegistries.BIOMES, biome.getObject()));
    }

    private void translateEnchantment(RegistryObject<? extends Enchantment> enchantment, String name, String desc) {
        String descId = enchantment.get().getDescriptionId();
        this.add(descId, name);
        this.add(descId + ".desc", desc);
    }

    private void translateMusicDisc(RegistryObject<? extends Item> item, String desc) {
        this.add(item.get(), "Music Disc");
        this.addDescription(item, desc);
    }

    private void translateSign(Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> sign, String name) {
        this.translateBlock(sign.getFirst());
        this.add(sign.getFirst().get().getDescriptionId().replace(name, name + "_wall"), toUpper(ForgeRegistries.BLOCKS, sign.getSecond()));
    }

    private void translateAttribute(RegistryObject<? extends Attribute> attribute) {
        this.add(attribute.get().getDescriptionId(), toUpper(ForgeRegistries.ATTRIBUTES, attribute));
    }

    private void translateEffect(RegistryObject<? extends MobEffect> effect, String desc) {
        this.add(effect.get(), toUpper(ForgeRegistries.MOB_EFFECTS, effect));
        this.add(effect.get().getDescriptionId() + ".description", desc);
    }

    private void translateBannerPattern(RegistryObject<? extends Item> item, String name) {
        String desc = StringUtils.capitaliseAllWords(name.replace('_', ' '));
        this.add(item.get(), "Banner Pattern");
        this.addDescription(item, desc);

        for (DyeColor dye : DyeColor.values())
            this.add("block.minecraft.banner." + Windswept.MOD_ID + "." + name + "." + dye.getName(), StringUtils.capitaliseAllWords(dye.getName().replace('_', ' ')) + " " + desc);
    }

    private void jeiInfo(Supplier<? extends ItemLike> item, String desc) {
        this.add(WindsweptPlugin.getDesc(item), desc);
    }

    private void translateDamageSource(DamageSource source, Function<String, String> death, BiFunction<String, String, String> killed) {
        this.add("death.attack." + source.getMsgId(), death.apply("%1$s"));
        this.add("death.attack." + source.getMsgId() + ".player", killed.apply("%1$s", "%2$s"));
    }

    private void translatePotion(RegistryObject<? extends Potion> potion, String effect) {
        String name = ForgeRegistries.POTIONS.getKey(potion.get()).getPath();

        this.add("item.minecraft.potion.effect." + name, "Potion of " + effect);
        this.add("item.minecraft.splash_potion.effect." + name, "Splash Potion of " + effect);
        this.add("item.minecraft.tipped_arrow.effect." + name, "Arrow of " + effect);
        this.add("item.minecraft.lingering_potion.effect." + name, "Lingering Potion of " + effect);
        this.add("item.caverns_and_chasms.tether_potion.effect." + name, "Tether Potion of " + effect);
    }

    private void addDescription(RegistryObject<? extends Item> item, String desc) {
        this.add(item.get().getDescriptionId() + ".desc", desc);
    }

    private static <T> String toUpper(IForgeRegistry<T> entry, RegistryObject<? extends T> object) {
        return StringUtils.capitaliseAllWords(entry.getKey(object.get()).getPath().replace('_', ' '));
    }

}

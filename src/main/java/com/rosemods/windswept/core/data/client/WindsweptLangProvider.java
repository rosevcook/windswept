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

public class WindsweptLangProvider extends LanguageProvider {

    public WindsweptLangProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // Items //

        this.translateItem(WindsweptItems.HOLLY_BERRIES);

        this.translateItem(WindsweptItems.HOLLY_BOAT.getFirst());
        this.add(WindsweptItems.HOLLY_BOAT.getSecond().get(), "Holly Boat with Chest");
        this.translateItem(WindsweptItems.CHESTNUT_BOAT.getFirst());
        this.add(WindsweptItems.CHESTNUT_BOAT.getSecond().get(), "Chestnut Boat with Chest");

        this.translateItem(WindsweptItems.CHESTNUTS);
        this.translateItem(WindsweptItems.ROASTED_CHESTNUTS);
        this.translateItem(WindsweptItems.CHESTNUT_SOUP);
        this.translateItem(WindsweptItems.CHESTNUT_RISOTTO);
        this.translateItem(WindsweptItems.CHESTNUT_CHICKEN_PLATTER);
        this.translateItem(WindsweptItems.WOODEN_BUCKET);
        this.translateItem(WindsweptItems.WOODEN_WATER_BUCKET);
        this.translateItem(WindsweptItems.WOODEN_MILK_BUCKET);
        this.translateItem(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET);
        this.translateItem(WindsweptItems.WILD_BERRIES);
        this.translateItem(WindsweptItems.WILD_BERRY_COOKIE);
        this.translateItem(WindsweptItems.WILD_BERRY_JUICE);
        this.translateItem(WindsweptItems.WILD_BERRY_PIPS);
        this.add(WindsweptItems.SWEET_BERRY_BOWL.get(), "Bowl of Sweet Berries");
        this.add(WindsweptItems.WILD_BERRY_BOWL.get(), "Bowl of Wild Berries");
        this.translateItem(WindsweptItems.MUTTON_PIE);
        this.add(WindsweptItems.GOAT.get(), "Raw Chevon");
        this.add(WindsweptItems.COOKED_GOAT.get(), "Cooked Chevon");
        this.translateItem(WindsweptItems.GOAT_STEW);
        this.add(WindsweptItems.GOAT_SHANKS.get(), "Raw Chevon Shanks");
        this.add(WindsweptItems.COOKED_GOAT_SHANKS.get(), "Cooked Chevon Shanks");
        this.add(WindsweptItems.FOUL_BERRY_BOWL.get(), "Bowl of Foul Berries");
        this.translateItem(WindsweptItems.FROZEN_FLESH);
        this.translateItem(WindsweptItems.SNOW_BOOTS);
        this.translateBannerPattern(WindsweptItems.SNOW_CHARGE_BANNER_PATTERN, "snow_charge");
        this.translateBannerPattern(WindsweptItems.SNOW_GOLEM_BANNER_PATTERN, "snow_golem");
        this.translateBannerPattern(WindsweptItems.ROSE_FLOWER_BANNER_PATTERN, "rose_flower");
        this.translateMusicDisc(WindsweptItems.MUSIC_DISC_RAIN, "rose - rain");
        this.translateMusicDisc(WindsweptItems.MUSIC_DISC_SNOW, "rose - snow");
        this.translateMusicDisc(WindsweptItems.MUSIC_DISC_BUMBLEBEE, "rose - bumblebee");
        this.translateItem(WindsweptItems.CHILLED_SPAWN_EGG);

        this.add(WindsweptItems.HOLLY_FURNACE_BOAT.get(), "Holly Boat with Furnace");
        this.translateItem(WindsweptItems.LARGE_HOLLY_BOAT);

        this.add(WindsweptItems.CHESTNUT_FURNACE_BOAT.get(), "Chestnut Boat with Furnace");
        this.translateItem(WindsweptItems.LARGE_CHESTNUT_BOAT);

        // Blocks //

        this.translateBlock(WindsweptBlocks.STRIPPED_HOLLY_LOG);
        this.translateBlock(WindsweptBlocks.STRIPPED_HOLLY_WOOD);
        this.translateBlock(WindsweptBlocks.HOLLY_LOG);
        this.translateBlock(WindsweptBlocks.HOLLY_WOOD);
        this.translateBlock(WindsweptBlocks.HOLLY_PLANKS);
        this.translateBlock(WindsweptBlocks.VERTICAL_HOLLY_PLANKS);
        this.translateBlock(WindsweptBlocks.HOLLY_SLAB);
        this.translateBlock(WindsweptBlocks.HOLLY_STAIRS);
        this.translateBlock(WindsweptBlocks.HOLLY_FENCE);
        this.translateBlock(WindsweptBlocks.HOLLY_FENCE_GATE);
        this.translateBlock(WindsweptBlocks.HOLLY_PRESSURE_PLATE);
        this.translateBlock(WindsweptBlocks.HOLLY_DOOR);
        this.translateBlock(WindsweptBlocks.HOLLY_TRAPDOOR);
        this.translateBlock(WindsweptBlocks.HOLLY_BUTTON);
        this.translateSign(WindsweptBlocks.HOLLY_SIGNS, "holly");
        this.translateBlock(WindsweptBlocks.HOLLY_LEAVES);
        this.translateBlock(WindsweptBlocks.HOLLY_SAPLING);
        this.translateBlock(WindsweptBlocks.POTTED_HOLLY_SAPLING);
        this.translateBlock(WindsweptBlocks.HOLLY_BEEHIVE);
        this.add(WindsweptBlocks.HOLLY_HEDGE.get(), "Holly Leaf Hedge");
        this.translateBlock(WindsweptBlocks.HOLLY_LEAF_CARPET);
        this.add(WindsweptBlocks.HOLLY_LEAF_PILE.get(), "Pile of Holly Leaves");
        this.translateBlock(WindsweptBlocks.HOLLY_LADDER);
        this.translateBlock(WindsweptBlocks.HOLLY_BOOKSHELF);
        this.translateBlock(WindsweptBlocks.HOLLY_BOARDS);
        this.translateBlock(WindsweptBlocks.HOLLY_CABINET);
        this.translateBlock(WindsweptBlocks.HOLLY_VERTICAL_SLAB);
        this.translateBlock(WindsweptBlocks.HOLLY_POST);
        this.translateBlock(WindsweptBlocks.STRIPPED_HOLLY_POST);
        this.translateBlock(WindsweptBlocks.HOLLY_CHEST);
        this.translateBlock(WindsweptBlocks.HOLLY_TRAPPED_CHEST);
        this.add(WindsweptBlocks.HOLLY_BERRY_BASKET.get(), "Basket of Holly Berries");

        this.translateBlock(WindsweptBlocks.STRIPPED_CHESTNUT_LOG);
        this.translateBlock(WindsweptBlocks.STRIPPED_CHESTNUT_WOOD);
        this.translateBlock(WindsweptBlocks.CHESTNUT_LOG);
        this.translateBlock(WindsweptBlocks.CHESTNUT_WOOD);
        this.translateBlock(WindsweptBlocks.CHESTNUT_PLANKS);
        this.translateBlock(WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS);
        this.translateBlock(WindsweptBlocks.CHESTNUT_SLAB);
        this.translateBlock(WindsweptBlocks.CHESTNUT_STAIRS);
        this.translateBlock(WindsweptBlocks.CHESTNUT_FENCE);
        this.translateBlock(WindsweptBlocks.CHESTNUT_FENCE_GATE);
        this.translateBlock(WindsweptBlocks.CHESTNUT_PRESSURE_PLATE);
        this.translateBlock(WindsweptBlocks.CHESTNUT_DOOR);
        this.translateBlock(WindsweptBlocks.CHESTNUT_TRAPDOOR);
        this.translateBlock(WindsweptBlocks.CHESTNUT_BUTTON);
        this.translateSign(WindsweptBlocks.CHESTNUT_SIGNS, "chestnut");
        this.translateBlock(WindsweptBlocks.CHESTNUT_LEAVES);
        this.translateBlock(WindsweptBlocks.CHESTNUT_SAPLING);
        this.translateBlock(WindsweptBlocks.POTTED_CHESTNUT_SAPLING);
        this.translateBlock(WindsweptBlocks.CHESTNUT_BEEHIVE);
        this.add(WindsweptBlocks.CHESTNUT_HEDGE.get(), "Chestnut Leaf Hedge");
        this.translateBlock(WindsweptBlocks.CHESTNUT_LEAF_CARPET);
        this.add(WindsweptBlocks.CHESTNUT_LEAF_PILE.get(), "Pile of Chestnut Leaves");
        this.translateBlock(WindsweptBlocks.CHESTNUT_LADDER);
        this.translateBlock(WindsweptBlocks.CHESTNUT_BOOKSHELF);
        this.translateBlock(WindsweptBlocks.CHESTNUT_BOARDS);
        this.translateBlock(WindsweptBlocks.CHESTNUT_CABINET);
        this.translateBlock(WindsweptBlocks.CHESTNUT_VERTICAL_SLAB);
        this.translateBlock(WindsweptBlocks.CHESTNUT_POST);
        this.translateBlock(WindsweptBlocks.STRIPPED_CHESTNUT_POST);
        this.translateBlock(WindsweptBlocks.CHESTNUT_CHEST);
        this.translateBlock(WindsweptBlocks.CHESTNUT_TRAPPED_CHEST);
        this.add(WindsweptBlocks.CHESTNUT_CRATE.get(), "Crate of Chestnuts");
        this.add(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get(), "Crate of Roasted Chestnuts");

        this.translateBlock(WindsweptBlocks.SNOWY_SPROUTS);

        this.add("block.windswept.snow_carpet", "Snow Carpet");
        this.translateBlock(WindsweptBlocks.SNOW_STAIRS);
        this.translateBlock(WindsweptBlocks.SNOW_SLAB);
        this.translateBlock(WindsweptBlocks.SNOW_VERTICAL_SLAB);
        this.translateBlock(WindsweptBlocks.SNOW_BRICKS);
        this.translateBlock(WindsweptBlocks.SNOW_BRICK_STAIRS);
        this.translateBlock(WindsweptBlocks.SNOW_BRICK_SLAB);
        this.translateBlock(WindsweptBlocks.SNOW_BRICK_WALL);
        this.translateBlock(WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB);

        this.translateBlock(WindsweptBlocks.PACKED_ICE_STAIRS);
        this.translateBlock(WindsweptBlocks.PACKED_ICE_SLAB);
        this.translateBlock(WindsweptBlocks.PACKED_ICE_VERTICAL_SLAB);
        this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICKS);
        this.translateBlock(WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS);
        this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICK_STAIRS);
        this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICK_SLAB);
        this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICK_WALL);
        this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB);

        this.translateBlock(WindsweptBlocks.BLUE_ICE_STAIRS);
        this.translateBlock(WindsweptBlocks.BLUE_ICE_SLAB);
        this.translateBlock(WindsweptBlocks.BLUE_ICE_VERTICAL_SLAB);
        this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICKS);
        this.translateBlock(WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS);
        this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICK_STAIRS);
        this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICK_SLAB);
        this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICK_WALL);
        this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB);

        this.translateBlock(WindsweptBlocks.CUT_ICE);
        this.translateBlock(WindsweptBlocks.ICE_SHEET);
        this.translateBlock(WindsweptBlocks.CUT_ICE_SHEET);

        this.translateBlock(WindsweptBlocks.RED_ROSE_BUSH);
        this.translateBlock(WindsweptBlocks.PINK_ROSE_BUSH);
        this.translateBlock(WindsweptBlocks.BLUE_ROSE_BUSH);
        this.translateBlock(WindsweptBlocks.WHITE_ROSE_BUSH);
        this.translateBlock(WindsweptBlocks.YELLOW_ROSE_BUSH);
        this.translateBlock(WindsweptBlocks.WITHER_ROSE_BUSH);

        this.translateBlock(WindsweptBlocks.RED_ROSE);
        this.translateBlock(WindsweptBlocks.PINK_ROSE);
        this.translateBlock(WindsweptBlocks.BLUE_ROSE);
        this.translateBlock(WindsweptBlocks.WHITE_ROSE);
        this.translateBlock(WindsweptBlocks.YELLOW_ROSE);
        this.translateBlock(WindsweptBlocks.FOXGLOVE);
        this.translateBlock(WindsweptBlocks.BLUEBELLS);
        this.translateBlock(WindsweptBlocks.NIGHTSHADE);

        this.translateBlock(WindsweptBlocks.POTTED_RED_ROSE);
        this.translateBlock(WindsweptBlocks.POTTED_PINK_ROSE);
        this.translateBlock(WindsweptBlocks.POTTED_BLUE_ROSE);
        this.translateBlock(WindsweptBlocks.POTTED_WHITE_ROSE);
        this.translateBlock(WindsweptBlocks.POTTED_YELLOW_ROSE);
        this.translateBlock(WindsweptBlocks.POTTED_FOXGLOVE);
        this.translateBlock(WindsweptBlocks.POTTED_BLUEBELLS);
        this.translateBlock(WindsweptBlocks.POTTED_NIGHTSHADE);
        this.translateBlock(WindsweptBlocks.POTTED_SNOWY_SPROUTS);

        this.translateBlock(WindsweptBlocks.WILD_BERRY_BUSH);
        this.add(WindsweptBlocks.WILD_BERRY_BASKET.get(), "Basket of Wild Berries");

        this.add(WindsweptBlocks.RED_MUSHROOM_BASKET.get(), "Basket of Red Mushrooms");
        this.add(WindsweptBlocks.BROWN_MUSHROOM_BASKET.get(), "Basket of Brown Mushrooms");
        this.add(WindsweptBlocks.GLOW_SHROOM_BASKET.get(), "Basket of Glow Shrooms");

        this.translateBlock(WindsweptBlocks.FROZEN_FLESH_BLOCK);

        // Entities //

        this.translateEntity(WindsweptEntities.CHILLED);

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

        this.jeiInfo(WindsweptItems.MUSIC_DISC_RAIN, "Dropped by a Drowned if killed by a Skeleton.");
        this.jeiInfo(WindsweptItems.MUSIC_DISC_SNOW, "Dropped by a Chilled if killed by a Skeleton.");
        this.jeiInfo(WindsweptItems.MUSIC_DISC_BUMBLEBEE, "Sometimes dropped when shearing a Beehive for Honeycombs.");

        this.jeiInfo(WindsweptBlocks.RED_ROSE_BUSH, "Bonemeal a Red Rose and it will grow into a Red Rose Bush!");
        this.jeiInfo(WindsweptBlocks.PINK_ROSE_BUSH, "Bonemeal a Pink Rose and it will grow into a Pink Rose Bush!");
        this.jeiInfo(WindsweptBlocks.BLUE_ROSE_BUSH, "Bonemeal a Blue Rose and it will grow into a Blue Rose Bush! This can also be found in Chestnut Forests");
        this.jeiInfo(WindsweptBlocks.WHITE_ROSE_BUSH, "Bonemeal a White Rose and it will grow into a White Rose Bush! This can also be found in Snowy Chestnut Forests");
        this.jeiInfo(WindsweptBlocks.YELLOW_ROSE_BUSH, "Bonemeal a Yellow Rose and it will grow into a Yellow Rose Bush!");
        this.jeiInfo(WindsweptBlocks.WITHER_ROSE_BUSH, "Bonemeal a Wither Rose and it will grow into a Wither Rose Bush!");

        this.jeiInfo(WindsweptItems.WILD_BERRIES, "Wild Berries can be found growing in snowy biomes but are more commonly found in Groves.");
        this.jeiInfo(WindsweptBlocks.NIGHTSHADE, "Nightshades can be found very rarely growing in large patches under trees. They can also be brewing into Night Vision potions.");
        this.jeiInfo(WindsweptBlocks.BLUEBELLS, "Bluebells are small flowers that grow in patches in Dark Oak and Birch Forests.");
        this.jeiInfo(WindsweptBlocks.FOXGLOVE, "Foxgloves are found in any Taiga biome. Foxes can sometimes be found holding them in their mouths.");
        this.jeiInfo(WindsweptBlocks.SNOWY_SPROUTS, "Snowy sprouts are small plants that grow in round patches in snowy areas. They only grow on snow blocks but when collected with shears, can also be placed on grass and dirt.");

        this.jeiInfo(WindsweptBlocks.RED_ROSE, "Red Roses can be found in Taiga biomes. They can also be bonemealed into Red Rose Bushes.");
        this.jeiInfo(WindsweptBlocks.PINK_ROSE, "Pink Roses can be found in Snowy biomes. They can also be bonemealed into Pink Rose Bushes.");
        this.jeiInfo(WindsweptBlocks.BLUE_ROSE, "Blue Roses can be found in Snowy biomes. They can also be bonemealed into Blue Rose Bushes.");
        this.jeiInfo(WindsweptBlocks.WHITE_ROSE, "White Roses can be found in Snowy biomes. They can also be bonemealed into White Rose Bushes.");
        this.jeiInfo(WindsweptBlocks.YELLOW_ROSE, "Yellow Roses can be found in Taiga biomes. They can also be bonemealed into Yellow Rose Bushes.");

        this.jeiInfo(WindsweptItems.WOODEN_BUCKET, "Wooden buckets are cheap buckets with a durability of 24. Durability is taken when a fluid exits the bucket.");
        this.jeiInfo(WindsweptItems.SNOW_BOOTS, "Snow boots allow you to walk faster on any snowy block. They can be dyed any colour you like. They also allow you to walk on Powder Snow.");
        this.jeiInfo(WindsweptItems.WILD_BERRY_BOWL, "Wild Berry bowls condense 3 Wild Berries into a single item. They are exactly 3 times the saturation and hunger of Wild Berries.");
        this.jeiInfo(WindsweptItems.SWEET_BERRY_BOWL, "Sweet Berry bowls condense 3 Sweet Berries into a single item. They are exactly 3 times the saturation and hunger of Sweet Berries.");
        this.jeiInfo(WindsweptItems.FOUL_BERRY_BOWL, "Foul Berry bowls condense 3 Foul Berries into a single item. They are exactly 3 times the saturation and hunger of Foul Berries.");
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
        this.add("biome." + Windswept.MODID + "." + ForgeRegistries.BIOMES.getKey(biome.get()).getPath(), toUpper(ForgeRegistries.BIOMES, biome.getObject()));
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
            this.add("block.minecraft.banner." + Windswept.MODID + "." + name + "." + dye.getName(), StringUtils.capitaliseAllWords(dye.getName().replace('_', ' ')) + " " + desc);
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

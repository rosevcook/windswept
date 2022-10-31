package com.rosemods.windswept.core.data.client;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.codehaus.plexus.util.StringUtils;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptDamageSources;
import com.rosemods.windswept.core.registry.WindsweptAttributes;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import com.rosemods.windswept.core.registry.WindsweptEnchantments;
import com.rosemods.windswept.core.registry.WindsweptEntities;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.rosemods.windswept.integration.jei.WindsweptPlugin;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;

import net.minecraft.world.damagesource.DamageSource;
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

		//this.translateItem(WindsweptItems.CHESTNUTS);
		//this.translateItem(WindsweptItems.ROASTED_CHESTNUTS);
		this.translateItem(WindsweptItems.WOODEN_BUCKET);
		this.translateItem(WindsweptItems.WOODEN_WATER_BUCKET);
		this.translateItem(WindsweptItems.WOODEN_MILK_BUCKET);
		this.translateItem(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET);
		this.translateItem(WindsweptItems.WILD_BERRIES);
		this.translateItem(WindsweptItems.WILD_BERRY_JUICE);
		this.translateItem(WindsweptItems.WILD_BERRY_PIPS);
		this.add(WindsweptItems.SWEET_BERRY_BOWL.get(), "Bowl of Sweet Berries");
		this.add(WindsweptItems.WILD_BERRY_BOWL.get(), "Bowl of Wild Berries");
		this.translateItem(WindsweptItems.MUTTON_PIE);
		this.add(WindsweptItems.GOAT.get(), "Raw Goat");
		this.translateItem(WindsweptItems.COOKED_GOAT);
		this.translateItem(WindsweptItems.GOAT_STEW);
		this.translateItem(WindsweptItems.FROZEN_FLESH);
		this.translateItem(WindsweptItems.SNOW_BOOTS);
		this.translateBannerPattern(WindsweptItems.SNOW_CHARGE_BANNER_PATTERN, "snow_charge");
		this.translateBannerPattern(WindsweptItems.SNOW_GOLEM_BANNER_PATTERN, "snow_golem");
		this.translateMusicDisc(WindsweptItems.MUSIC_DISC_RAIN, "london - rain");
		this.translateMusicDisc(WindsweptItems.MUSIC_DISC_SNOW, "london - snow");
		this.translateItem(WindsweptItems.CHILLED_SPAWN_EGG);
		
		//this.add(WindsweptItems.HOLLY_FURNACE_BOAT.get(), "Holly Boat with Furnace");
		//this.translateItem(WindsweptItems.LARGE_HOLLY_BOAT);
		
		//this.add(WindsweptItems.CHESTNUT_FURNACE_BOAT.get(), "Chestnut Boat with Furnace");
		//this.translateItem(WindsweptItems.LARGE_CHESTNUT_BOAT);

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
		//this.translateBlock(WindsweptBlocks.HOLLY_BEEHIVE);
		this.add(WindsweptBlocks.HOLLY_HEDGE.get(), "Holly Leaf Hedge");
		this.translateBlock(WindsweptBlocks.HOLLY_LEAF_CARPET);
		//this.translateBlock(WindsweptBlocks.HOLLY_LEAF_PILE);
		this.translateBlock(WindsweptBlocks.HOLLY_LADDER);
		this.translateBlock(WindsweptBlocks.HOLLY_BOOKSHELF);
		this.translateBlock(WindsweptBlocks.HOLLY_VERTICAL_SLAB);
		this.translateBlock(WindsweptBlocks.HOLLY_POST);
		this.translateBlock(WindsweptBlocks.STRIPPED_HOLLY_POST);
		this.translateBlock(WindsweptBlocks.HOLLY_CHESTS.getFirst());
		this.translateBlock(WindsweptBlocks.HOLLY_CHESTS.getSecond());
		this.translateBlock(WindsweptBlocks.HOLLY_BERRY_CRATE);
		
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
		//this.translateBlock(WindsweptBlocks.CHESTNUT_BEEHIVE);
		this.add(WindsweptBlocks.CHESTNUT_HEDGE.get(), "Chestnut Leaf Hedge");
		this.translateBlock(WindsweptBlocks.CHESTNUT_LEAF_CARPET);
		//this.translateBlock(WindsweptBlocks.CHESTNUT_LEAF_PILE);
		this.translateBlock(WindsweptBlocks.CHESTNUT_LADDER);
		this.translateBlock(WindsweptBlocks.CHESTNUT_BOOKSHELF);
		this.translateBlock(WindsweptBlocks.CHESTNUT_VERTICAL_SLAB);
		this.translateBlock(WindsweptBlocks.CHESTNUT_POST);
		this.translateBlock(WindsweptBlocks.STRIPPED_CHESTNUT_POST);
		this.translateBlock(WindsweptBlocks.CHESTNUT_CHESTS.getFirst());
		this.translateBlock(WindsweptBlocks.CHESTNUT_CHESTS.getSecond());
		//this.translateBlock(WindsweptBlocks.CHESTNUT_CRATE);
		//this.translateBlock(WindsweptBlocks.ROASTED_CHESTNUT_CRATE);
		
		this.translateBlock(WindsweptBlocks.SNOWY_SPROUTS);
		
		this.translateBlock(WindsweptBlocks.SNOW_BRICKS);
		this.translateBlock(WindsweptBlocks.SNOW_BRICK_STAIRS);
		this.translateBlock(WindsweptBlocks.SNOW_BRICK_SLAB);
		this.translateBlock(WindsweptBlocks.SNOW_BRICK_WALL);
		this.translateBlock(WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB);
		
		this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICKS);
		this.translateBlock(WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS);
		this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICK_STAIRS);
		this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICK_SLAB);
		this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICK_WALL);
		this.translateBlock(WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB);
		
		this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICKS);
		this.translateBlock(WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS);
		this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICK_STAIRS);
		this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICK_SLAB);
		this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICK_WALL);
		this.translateBlock(WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB);
		
		this.translateBlock(WindsweptBlocks.POLISHED_DEEPSLATE_PRESSURE_PLATE);
		this.translateBlock(WindsweptBlocks.POLISHED_DEEPSLATE_BUTTON);
		
		this.translateBlock(WindsweptBlocks.GOLDEN_DOOR);
		this.translateBlock(WindsweptBlocks.GOLDEN_TRAPDOOR);

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
		
		this.translateBlock(WindsweptBlocks.WILD_BERRY_BUSH);
		this.translateBlock(WindsweptBlocks.WILD_BERRY_BUSH_PIPS);
		this.translateBlock(WindsweptBlocks.WILD_BERRY_SACK);

		this.translateBlock(WindsweptBlocks.ICE_SHEET);

		// Entities //
		
		this.translateEntity(WindsweptEntities.CHILLED);

		// Effects //
				
		this.translatePotion(WindsweptEffects.THORNS_POTION, "Prickling");
		this.translatePotion(WindsweptEffects.LONG_THORNS_POTION, "Prickling");
		this.translatePotion(WindsweptEffects.STRONG_THORNS_POTION, "Prickling");

		// Attributes
		
		this.translateAttribute(WindsweptAttributes.SNOW_SPEED);
		
		// Enchantments //
		
		this.translateEnchantment(WindsweptEnchantments.SLIPPING_CURSE, "Curse of Slipping", "Slipping can only be applied to boots and causes the wearer to slide on every block they stand on while also damaging the boots.");
		
		// Sounds //
		
		// Damage Sources //
		
		this.translateDamageSource(WindsweptDamageSources.HOLLY_LEAVES, 
				player -> player + " was ripped to death by holly leaves", 
				(player, entity) -> player + " was ripped to death by holly leaves whilst trying to escape " + entity
		);
		
		this.translateDamageSource(WindsweptDamageSources.HOLLY_SAPLING, 
				player -> player + " was ripped to death by holly sapling", 
				(player, entity) -> player + " was ripped to death by holly sapling whilst trying to escape " + entity
		);
		
		this.translateDamageSource(WindsweptDamageSources.HOLLY_HEDGE, 
				player -> player + " was ripped to death by holly leaf hedge", 
				(player, entity) -> player + " was ripped to death by holly leaf hedge whilst trying to escape " + entity
		);
		
		// JEI Info
		
		this.jeiInfo(WindsweptItems.MUSIC_DISC_RAIN, "Dropped by a Drowned if killed by a Skeleton.");
		this.jeiInfo(WindsweptItems.MUSIC_DISC_SNOW, "Dropped by a Chilled if killed by a Skeleton.");

		// Tooltips //
		
		this.add(Windswept.MODID + ".tooltip.unobtainable", "Unobtainable");
		this.add(Windswept.MODID + ".tooltip.dyeable", "Dyeable");

	}

	private void translateItem(RegistryObject<? extends Item> item) {
		this.add(item.get(), this.toUpper(ForgeRegistries.ITEMS, item));
	}

	private void translateBlock(RegistryObject<? extends Block> block) {
		this.add(block.get(), this.toUpper(ForgeRegistries.BLOCKS, block));
	}

	private void translateEntity(RegistryObject<? extends EntityType<?>> entity) {
		this.add(entity.get(), this.toUpper(ForgeRegistries.ENTITY_TYPES, entity));
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
		this.add(sign.getFirst().get().getDescriptionId().replace(name, name + "_wall"), this.toUpper(ForgeRegistries.BLOCKS, sign.getSecond()));
	}
	
	private void translateAttribute(RegistryObject<? extends Attribute> attribute) {
		this.add(attribute.get().getDescriptionId(), this.toUpper(ForgeRegistries.ATTRIBUTES, attribute));
	}
	
	private void translateBannerPattern(RegistryObject<? extends Item> item, String name) {
		String desc = StringUtils.capitaliseAllWords(name.replace('_', ' '));
		this.add(item.get(), "Banner Pattern");
		this.addDescription(item, desc);
	
		for (DyeColor dye : DyeColor.values())
			this.add("block.minecraft.banner." + Windswept.MODID + "." + name + "." + dye.getName(), StringUtils.capitaliseAllWords(dye.getName().replace('_', ' ')) + " " + desc);
	}
	
	private void jeiInfo(RegistryObject<? extends ItemLike> item, String desc) {
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
	}
	
	/*
	private void translateSound(RegistryObject<SoundEvent> sound, String subtitle) {
		this.add(WindsweptSoundProvider.subtilte(sound), subtitle);
	}
	*/

	private void addDescription(RegistryObject<? extends Item> item, String desc) {
		this.add(item.get().getDescriptionId() + ".desc", desc);
	}
	
	private <T> String toUpper(IForgeRegistry<T> entry, RegistryObject<? extends T> object) {
		return StringUtils.capitaliseAllWords(entry.getKey(object.get()).getPath().replace('_', ' '));
	}

}

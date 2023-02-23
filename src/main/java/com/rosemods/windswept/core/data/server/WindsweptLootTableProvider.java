package com.rosemods.windswept.core.data.server;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptEntities;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock.VerticalSlabType;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class WindsweptLootTableProvider extends LootTableProvider {
	private final Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet> blockTables = Pair.of(Blocks::new, LootContextParamSets.BLOCK);
	private final Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet> entityTables = Pair.of(Entities::new, LootContextParamSets.ENTITY);
	private final Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet> chestTables = Pair.of(Chests::new, LootContextParamSets.BLOCK);

	public WindsweptLootTableProvider(GatherDataEvent event) {
		super(event.getGenerator());
	}
	
	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
		return ImmutableList.of(this.blockTables, this.entityTables, this.chestTables);
	}
	
	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) { }
	
	private static <T> Iterable<T> getContent(IForgeRegistry<T> entry) {
		return entry.getValues().stream().filter(i -> entry.getKey(i) != null 
				&& Windswept.MODID.equals(entry.getKey(i).getNamespace())).collect(Collectors.toSet());
	}
		
	private static class Blocks extends BlockLoot {
		
		@Override
		protected void addTables() {		
			// holly
			this.dropSelf(WindsweptBlocks.STRIPPED_HOLLY_LOG.get());
			this.dropSelf(WindsweptBlocks.STRIPPED_HOLLY_WOOD.get());
			this.dropSelf(WindsweptBlocks.HOLLY_LOG.get());
			this.dropSelf(WindsweptBlocks.HOLLY_WOOD.get());
			this.dropSelf(WindsweptBlocks.HOLLY_PLANKS.get());
			this.add(WindsweptBlocks.HOLLY_SLAB.get(), Blocks::createSlabItemTable);
			this.dropSelf(WindsweptBlocks.HOLLY_STAIRS.get());
			this.dropSelf(WindsweptBlocks.HOLLY_FENCE.get());
			this.dropSelf(WindsweptBlocks.HOLLY_FENCE_GATE.get());
			this.dropSelf(WindsweptBlocks.HOLLY_PRESSURE_PLATE.get());
			this.add(WindsweptBlocks.HOLLY_DOOR.get(), Blocks::createDoorTable);
			this.dropSelf(WindsweptBlocks.HOLLY_TRAPDOOR.get());
			this.dropSelf(WindsweptBlocks.HOLLY_BUTTON.get());
			this.dropSelf(WindsweptBlocks.HOLLY_SIGNS.getFirst().get());
			this.dropSelf(WindsweptBlocks.HOLLY_SIGNS.getSecond().get());
			this.dropSelf(WindsweptBlocks.HOLLY_SAPLING.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_HOLLY_SAPLING.get());

			this.add(WindsweptBlocks.HOLLY_BEEHIVE.get(), Blocks::createBeeHiveDrop);
			this.dropSelf(WindsweptBlocks.HOLLY_LADDER.get());
			this.dropSelf(WindsweptBlocks.VERTICAL_HOLLY_PLANKS.get());
			this.bookshelf(WindsweptBlocks.HOLLY_BOOKSHELF.get());
			this.dropSelf(WindsweptBlocks.HOLLY_BOARDS.get());
			this.dropSelf(WindsweptBlocks.HOLLY_CABINET.get());
			this.add(WindsweptBlocks.HOLLY_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
			this.dropSelf(WindsweptBlocks.STRIPPED_HOLLY_POST.get());
			this.dropSelf(WindsweptBlocks.HOLLY_POST.get());
			this.dropSelf(WindsweptBlocks.HOLLY_CHEST.get());
			this.dropSelf(WindsweptBlocks.HOLLY_TRAPPED_CHEST.get());

			this.add(WindsweptBlocks.HOLLY_LEAVES.get(), b -> createLeavesDrops(b, WindsweptBlocks.HOLLY_SAPLING
					.get(), .05f, .0625f, .083333336f, .1f).withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1f)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
					.add(applyExplosionCondition(b, LootItem.lootTableItem(WindsweptItems.HOLLY_BERRIES.get()))
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(-6f, 1f)))
							.apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));


			this.dropSelf(WindsweptBlocks.HOLLY_HEDGE.get());
			this.dropSelf(WindsweptBlocks.HOLLY_LEAF_CARPET.get());
			this.leafPile(WindsweptBlocks.HOLLY_LEAF_PILE.get());
			
			this.dropSelf(WindsweptBlocks.HOLLY_BERRY_CRATE.get());
					
			// chestnut
			this.dropSelf(WindsweptBlocks.STRIPPED_CHESTNUT_LOG.get());
			this.dropSelf(WindsweptBlocks.STRIPPED_CHESTNUT_WOOD.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_LOG.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_WOOD.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_PLANKS.get());
			this.add(WindsweptBlocks.CHESTNUT_SLAB.get(), Blocks::createSlabItemTable);
			this.dropSelf(WindsweptBlocks.CHESTNUT_STAIRS.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_FENCE.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_FENCE_GATE.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_PRESSURE_PLATE.get());
			this.add(WindsweptBlocks.CHESTNUT_DOOR.get(), Blocks::createDoorTable);
			this.dropSelf(WindsweptBlocks.CHESTNUT_TRAPDOOR.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_BUTTON.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_SIGNS.getFirst().get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_SIGNS.getSecond().get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_SAPLING.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_CHESTNUT_SAPLING.get());

			this.add(WindsweptBlocks.CHESTNUT_BEEHIVE.get(), Blocks::createBeeHiveDrop);
			this.dropSelf(WindsweptBlocks.CHESTNUT_LADDER.get());
			this.dropSelf(WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS.get());
			this.bookshelf(WindsweptBlocks.CHESTNUT_BOOKSHELF.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_BOARDS.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_CABINET.get());
			this.add(WindsweptBlocks.CHESTNUT_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
			this.dropSelf(WindsweptBlocks.STRIPPED_CHESTNUT_POST.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_POST.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_CHEST.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_TRAPPED_CHEST.get());

			this.add(WindsweptBlocks.CHESTNUT_LEAVES.get(), b -> createLeavesDrops(b, WindsweptBlocks.CHESTNUT_SAPLING
					.get(), .05f, .0625f, .083333336f, .1f).withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1f)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
					.add(applyExplosionCondition(b, LootItem.lootTableItem(WindsweptItems.CHESTNUTS.get()))
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(-4f, 1f)))
							.apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
			
			this.dropSelf(WindsweptBlocks.CHESTNUT_HEDGE.get());
			this.dropSelf(WindsweptBlocks.CHESTNUT_LEAF_CARPET.get());
			this.leafPile(WindsweptBlocks.CHESTNUT_LEAF_PILE.get());

			this.dropSelf(WindsweptBlocks.CHESTNUT_CRATE.get());
			this.dropSelf(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get());

			// snow bricks
			this.dropSelf(WindsweptBlocks.SNOW_BRICKS.get());
			this.dropSelf(WindsweptBlocks.SNOW_BRICK_STAIRS.get());
			this.add(WindsweptBlocks.SNOW_BRICK_SLAB.get(), Blocks::createSlabItemTable);
			this.dropSelf(WindsweptBlocks.SNOW_BRICK_WALL.get());
			this.add(WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
			
			// packed ice bricks
			this.dropSelf(WindsweptBlocks.PACKED_ICE_BRICKS.get());
			this.dropSelf(WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS.get());
			this.dropSelf(WindsweptBlocks.PACKED_ICE_BRICK_STAIRS.get());
			this.add(WindsweptBlocks.PACKED_ICE_BRICK_SLAB.get(), Blocks::createSlabItemTable);
			this.dropSelf(WindsweptBlocks.PACKED_ICE_BRICK_WALL.get());
			this.add(WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
			
			// blue ice bricks
			this.dropSelf(WindsweptBlocks.BLUE_ICE_BRICKS.get());
			this.dropSelf(WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS.get());
			this.dropSelf(WindsweptBlocks.BLUE_ICE_BRICK_STAIRS.get());
			this.add(WindsweptBlocks.BLUE_ICE_BRICK_SLAB.get(), Blocks::createSlabItemTable);
			this.dropSelf(WindsweptBlocks.BLUE_ICE_BRICK_WALL.get());
			this.add(WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
			
			// deepslate
			this.dropSelf(WindsweptBlocks.POLISHED_DEEPSLATE_PRESSURE_PLATE.get());
			this.dropSelf(WindsweptBlocks.POLISHED_DEEPSLATE_BUTTON.get());

			// rose bushes
			this.tallFlower(WindsweptBlocks.RED_ROSE_BUSH.get());
			this.tallFlower(WindsweptBlocks.PINK_ROSE_BUSH.get());
			this.tallFlower(WindsweptBlocks.BLUE_ROSE_BUSH.get());
			this.tallFlower(WindsweptBlocks.WHITE_ROSE_BUSH.get());
			this.tallFlower(WindsweptBlocks.YELLOW_ROSE_BUSH.get());
			this.tallFlower(WindsweptBlocks.WITHER_ROSE_BUSH.get());

			// plants
			this.add(WindsweptBlocks.SNOWY_SPROUTS.get(), Blocks::createShearsOnlyDrop);
			
			this.dropSelf(WindsweptBlocks.RED_ROSE.get());
			this.dropSelf(WindsweptBlocks.PINK_ROSE.get());
			this.dropSelf(WindsweptBlocks.BLUE_ROSE.get());
			this.dropSelf(WindsweptBlocks.WHITE_ROSE.get());
			this.dropSelf(WindsweptBlocks.YELLOW_ROSE.get());
			this.dropSelf(WindsweptBlocks.FOXGLOVE.get());
			this.dropSelf(WindsweptBlocks.BLUEBELLS.get());
			this.dropSelf(WindsweptBlocks.NIGHTSHADE.get());

			// pots
			this.dropPottedContents(WindsweptBlocks.POTTED_RED_ROSE.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_PINK_ROSE.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_BLUE_ROSE.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_WHITE_ROSE.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_YELLOW_ROSE.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_FOXGLOVE.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_BLUEBELLS.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_NIGHTSHADE.get());
			this.dropPottedContents(WindsweptBlocks.POTTED_SNOWY_SPROUTS.get());

			// wild berry
			this.add(WindsweptBlocks.WILD_BERRY_BUSH.get(), b -> applyExplosionDecay(b, LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
									.setProperties(StatePropertiesPredicate.Builder.properties()
											.hasProperty(SweetBerryBushBlock.AGE, 2)))
							.add(LootItem.lootTableItem(WindsweptItems.WILD_BERRIES.get()))
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)))
							.apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
			this.dropSelf(WindsweptBlocks.WILD_BERRY_SACK.get());
			this.dropOther(WindsweptBlocks.WILD_BERRY_BUSH_PIPS.get(), WindsweptItems.WILD_BERRY_PIPS.get());

			// misc
			this.dropSelf(WindsweptBlocks.ICE_SHEET.get());
		}
	
		
		private void bookshelf(Block block) {
			this.add(block, b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantValue.exactly(3f)));
		}

		private void tallFlower(Block block) {
			this.add(block, (b) -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
		}

		private void leafPile(Block block) {
			this.add(block, (b) -> createMultifaceBlockDrops(b, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS))));
		}
		
		@Override
		public Iterable<Block> getKnownBlocks() {
			return getContent(ForgeRegistries.BLOCKS);
		}
		
		private static LootTable.Builder createVerticalSlabItemTable(Block block) {
			return LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
							.add(applyExplosionDecay(block, LootItem.lootTableItem(block)
									.apply(SetItemCountFunction.setCount(ConstantValue.exactly(2f))
											.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
													.setProperties(StatePropertiesPredicate.Builder.properties()
															.hasProperty(VerticalSlabBlock.TYPE, VerticalSlabType.DOUBLE)))))));
		}
		
	}
	
	private static class Entities extends EntityLoot {
		
		@Override
		protected void addTables() {
			this.add(WindsweptEntities.CHILLED.get(),
					LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
							.add(LootItem.lootTableItem(WindsweptItems.FROZEN_FLESH::get)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(-2f, 2f)))
									.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0f, 1f)))))
							.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
									.add(LootItem.lootTableItem(Items.GOLD_INGOT))
									.add(LootItem.lootTableItem(Items.BEETROOT))
									.add(LootItem.lootTableItem(Items.APPLE)
											.apply(SmeltItemFunction.smelted()
													.when(LootItemEntityPropertyCondition.hasProperties(
															LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
									.when(LootItemKilledByPlayerCondition.killedByPlayer())
									.when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(.025f, .01f)))
							.withPool(
									LootPool.lootPool().add(LootItem.lootTableItem(WindsweptItems.MUSIC_DISC_SNOW::get))
											.when(LootItemEntityPropertyCondition.hasProperties(
													LootContext.EntityTarget.KILLER,
													EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));

		}
		
		@Override
		protected Iterable<EntityType<?>> getKnownEntities() {
			return getContent(ForgeRegistries.ENTITY_TYPES);
		}
	}

	private static class Chests extends ChestLoot {

		@Override
		public void accept(BiConsumer<ResourceLocation, Builder> builder) {
			register("grove_weathered_house", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4f, 8f))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(WindsweptBlocks.SNOWY_SPROUTS.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f))))
					.add(LootItem.lootTableItem(WindsweptItems.SNOW_BOOTS.get()).setWeight(1))
					.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1))
					.add(LootItem.lootTableItem(Items.BOOK).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(WindsweptItems.WOODEN_BUCKET.get()).setWeight(1))
					.add(LootItem.lootTableItem(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET.get()).setWeight(1))
					.add(LootItem.lootTableItem(WindsweptItems.WILD_BERRIES.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 3f))))
					.add(LootItem.lootTableItem(Items.SNOW_BLOCK).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(Items.COBWEB).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(WindsweptBlocks.HOLLY_SAPLING.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(WindsweptItems.COOKED_GOAT.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
			), builder);

			register("chestnut_weathered_house", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2f, 5f))
					.add(LootItem.lootTableItem(Items.EMERALD).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(Items.FERN).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f))))
					.add(LootItem.lootTableItem(Items.SWEET_BERRIES).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(WindsweptBlocks.CHESTNUT_LOG.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(Items.COBWEB).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(WindsweptBlocks.CHESTNUT_SAPLING.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(Items.BIRCH_SAPLING).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
					.add(LootItem.lootTableItem(WindsweptItems.WOODEN_BUCKET.get()).setWeight(1))
			), builder);
		}

		private static void register(String name, LootTable.Builder lootTable, BiConsumer<ResourceLocation, Builder> builder) {
			builder.accept(Windswept.REGISTRY_HELPER.prefix("chests/" + name), lootTable);
		}

	}
}


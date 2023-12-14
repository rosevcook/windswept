package com.rosemods.windswept.core.data.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
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
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

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
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }

    private static <T> Iterable<T> getContent(IForgeRegistry<T> entry) {
        return entry.getValues().stream().filter(i -> entry.getKey(i) != null
                && Windswept.MOD_ID.equals(entry.getKey(i).getNamespace())).collect(Collectors.toSet());
    }

    private static class Blocks extends BlockLoot {

        @Override
        protected void addTables() {
            // holly
            this.dropSelf(STRIPPED_HOLLY_LOG.get());
            this.dropSelf(STRIPPED_HOLLY_WOOD.get());
            this.dropSelf(HOLLY_LOG.get());
            this.dropSelf(HOLLY_WOOD.get());
            this.dropSelf(HOLLY_PLANKS.get());
            this.add(HOLLY_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(HOLLY_STAIRS.get());
            this.dropSelf(HOLLY_FENCE.get());
            this.dropSelf(HOLLY_FENCE_GATE.get());
            this.dropSelf(HOLLY_PRESSURE_PLATE.get());
            this.add(HOLLY_DOOR.get(), Blocks::createDoorTable);
            this.dropSelf(HOLLY_TRAPDOOR.get());
            this.dropSelf(HOLLY_BUTTON.get());
            this.dropSelf(HOLLY_SIGNS.getFirst().get());
            this.dropSelf(HOLLY_SIGNS.getSecond().get());
            this.dropSelf(HOLLY_SAPLING.get());
            this.dropPottedContents(POTTED_HOLLY_SAPLING.get());

            this.add(HOLLY_BEEHIVE.get(), Blocks::createBeeHiveDrop);
            this.dropSelf(HOLLY_LADDER.get());
            this.dropSelf(VERTICAL_HOLLY_PLANKS.get());
            this.bookshelf(HOLLY_BOOKSHELF.get());
            this.dropSelf(HOLLY_BOARDS.get());
            this.dropSelf(HOLLY_CABINET.get());
            this.add(HOLLY_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(STRIPPED_HOLLY_POST.get());
            this.dropSelf(HOLLY_POST.get());
            this.dropSelf(HOLLY_CHEST.get());
            this.dropSelf(HOLLY_TRAPPED_CHEST.get());

            this.add(HOLLY_LEAVES.get(), b -> createLeavesDrops(b, HOLLY_SAPLING
                    .get(), .05f, .0625f, .083333336f, .1f).withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                    .add(applyExplosionCondition(b, LootItem.lootTableItem(HOLLY_BERRIES.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(-6f, 1f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));


            this.dropSelf(HOLLY_HEDGE.get());
            this.dropSelf(HOLLY_LEAF_CARPET.get());
            this.leafPile(HOLLY_LEAF_PILE.get());

            this.dropSelf(HOLLY_BERRY_BASKET.get());

            // chestnut
            this.dropSelf(STRIPPED_CHESTNUT_LOG.get());
            this.dropSelf(STRIPPED_CHESTNUT_WOOD.get());
            this.dropSelf(CHESTNUT_LOG.get());
            this.dropSelf(CHESTNUT_WOOD.get());
            this.dropSelf(CHESTNUT_PLANKS.get());
            this.add(CHESTNUT_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(CHESTNUT_STAIRS.get());
            this.dropSelf(CHESTNUT_FENCE.get());
            this.dropSelf(CHESTNUT_FENCE_GATE.get());
            this.dropSelf(CHESTNUT_PRESSURE_PLATE.get());
            this.add(CHESTNUT_DOOR.get(), Blocks::createDoorTable);
            this.dropSelf(CHESTNUT_TRAPDOOR.get());
            this.dropSelf(CHESTNUT_BUTTON.get());
            this.dropSelf(CHESTNUT_SIGNS.getFirst().get());
            this.dropSelf(CHESTNUT_SIGNS.getSecond().get());
            this.dropSelf(CHESTNUT_SAPLING.get());
            this.dropPottedContents(POTTED_CHESTNUT_SAPLING.get());

            this.add(CHESTNUT_BEEHIVE.get(), Blocks::createBeeHiveDrop);
            this.dropSelf(CHESTNUT_LADDER.get());
            this.dropSelf(VERTICAL_CHESTNUT_PLANKS.get());
            this.bookshelf(CHESTNUT_BOOKSHELF.get());
            this.dropSelf(CHESTNUT_BOARDS.get());
            this.dropSelf(CHESTNUT_CABINET.get());
            this.add(CHESTNUT_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(STRIPPED_CHESTNUT_POST.get());
            this.dropSelf(CHESTNUT_POST.get());
            this.dropSelf(CHESTNUT_CHEST.get());
            this.dropSelf(CHESTNUT_TRAPPED_CHEST.get());

            this.add(CHESTNUT_LEAVES.get(), b -> createLeavesDrops(b, CHESTNUT_SAPLING
                    .get(), .05f, .0625f, .083333336f, .1f).withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                    .add(applyExplosionCondition(b, LootItem.lootTableItem(CHESTNUTS.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(-4f, 1f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

            this.dropSelf(CHESTNUT_HEDGE.get());
            this.dropSelf(CHESTNUT_LEAF_CARPET.get());
            this.leafPile(CHESTNUT_LEAF_PILE.get());

            this.dropSelf(CHESTNUT_CRATE.get());
            this.dropSelf(ROASTED_CHESTNUT_CRATE.get());

            //pine
            this.dropSelf(STRIPPED_PINE_LOG.get());
            this.dropSelf(STRIPPED_PINE_WOOD.get());
            this.dropSelf(WEATHERED_PINE_LOG.get());
            this.dropSelf(WEATHERED_PINE_WOOD.get());
            this.dropSelf(PINE_LOG.get());
            this.dropSelf(PINE_WOOD.get());
            this.dropSelf(PINE_PLANKS.get());
            this.add(PINE_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(PINE_STAIRS.get());
            this.dropSelf(PINE_FENCE.get());
            this.dropSelf(PINE_FENCE_GATE.get());
            this.dropSelf(PINE_PRESSURE_PLATE.get());
            this.add(PINE_DOOR.get(), Blocks::createDoorTable);
            this.dropSelf(PINE_TRAPDOOR.get());
            this.dropSelf(PINE_BUTTON.get());
            this.dropSelf(PINE_SIGNS.getFirst().get());
            this.dropSelf(PINE_SIGNS.getSecond().get());
            this.dropSelf(PINE_SAPLING.get());
            this.dropPottedContents(POTTED_PINE_SAPLING.get());

            this.add(PINE_BEEHIVE.get(), Blocks::createBeeHiveDrop);
            this.dropSelf(PINE_LADDER.get());
            this.dropSelf(VERTICAL_PINE_PLANKS.get());
            this.bookshelf(PINE_BOOKSHELF.get());
            this.dropSelf(PINE_BOARDS.get());
            this.dropSelf(PINE_CABINET.get());
            this.add(PINE_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(STRIPPED_PINE_POST.get());
            this.dropSelf(WEATHERED_PINE_POST.get());
            this.dropSelf(PINE_POST.get());
            this.dropSelf(PINE_CHEST.get());
            this.dropSelf(PINE_TRAPPED_CHEST.get());

            this.add(PINE_LEAVES.get(), b -> createLeavesDrops(b, PINE_SAPLING.get(), .05f, .0625f, .083333336f, .1f));
            this.dropSelf(PINE_HEDGE.get());
            this.dropSelf(PINE_LEAF_CARPET.get());
            this.leafPile(PINE_LEAF_PILE.get());

            // pinecone
            this.dropSelf(PINECONE_BLOCK.get());
            this.dropSelf(CARVED_PINECONE_BLOCK.get());
            this.dropSelf(WILL_O_THE_WISP.get());

            this.dropSelf(PINECONE_SHINGLES.get());
            this.dropSelf(PINECONE_SHINGLE_STAIRS.get());
            this.add(PINECONE_SHINGLE_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(PINECONE_SHINGLE_WALL.get());
            this.add(PINECONE_SHINGLE_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);

            // snow blocks
            this.dropSelf(SNOW_STAIRS.get());
            this.add(SNOW_SLAB.get(), Blocks::createSlabItemTable);
            this.add(SNOW_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(SNOW_BRICKS.get());
            this.dropSelf(SNOW_BRICK_STAIRS.get());
            this.add(SNOW_BRICK_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(SNOW_BRICK_WALL.get());
            this.add(SNOW_BRICK_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);

            // packed ice blocks
            this.dropSelf(PACKED_ICE_STAIRS.get());
            this.add(PACKED_ICE_SLAB.get(), Blocks::createSlabItemTable);
            this.add(PACKED_ICE_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(PACKED_ICE_BRICKS.get());
            this.dropSelf(CHISELED_PACKED_ICE_BRICKS.get());
            this.dropSelf(PACKED_ICE_BRICK_STAIRS.get());
            this.add(PACKED_ICE_BRICK_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(PACKED_ICE_BRICK_WALL.get());
            this.add(PACKED_ICE_BRICK_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);

            // blue ice blocks
            this.dropSelf(BLUE_ICE_STAIRS.get());
            this.add(BLUE_ICE_SLAB.get(), Blocks::createSlabItemTable);
            this.add(BLUE_ICE_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(BLUE_ICE_BRICKS.get());
            this.dropSelf(CHISELED_BLUE_ICE_BRICKS.get());
            this.dropSelf(BLUE_ICE_BRICK_STAIRS.get());
            this.add(BLUE_ICE_BRICK_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(BLUE_ICE_BRICK_WALL.get());
            this.add(BLUE_ICE_BRICK_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);

            // shale
            this.dropSelf(SHALE.get());
            this.dropSelf(SHALE_STAIRS.get());
            this.add(SHALE_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(SHALE_WALL.get());
            this.add(SHALE_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(POLISHED_SHALE.get());
            this.dropSelf(POLISHED_SHALE_STAIRS.get());
            this.add(POLISHED_SHALE_SLAB.get(), Blocks::createSlabItemTable);
            this.add(POLISHED_SHALE_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(POLISHED_SHALE_BRICKS.get());
            this.dropSelf(ICY_POLISHED_SHALE_BRICKS.get());
            this.dropSelf(CHISELED_POLISHED_SHALE_BRICKS.get());
            this.dropSelf(POLISHED_SHALE_BRICK_STAIRS.get());
            this.add(POLISHED_SHALE_BRICK_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(POLISHED_SHALE_BRICK_WALL.get());
            this.add(POLISHED_SHALE_BRICK_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(ICY_POLISHED_SHALE_BRICKS.get());

            // decoration
            this.dropSelf(FROSTBITER_TROPHY.get());
            this.add(CHRISTMAS_PUDDING.get(), LootTable.lootTable());
            this.dropSelf(HOLLY_WREATH.get());
            this.dropSelf(ICE_SHEET.get());

            // tall plants
            this.tallFlower(LUPINE.get());
            this.tallFlower(RED_ROSE_BUSH.get());
            this.tallFlower(PINK_ROSE_BUSH.get());
            this.tallFlower(BLUE_ROSE_BUSH.get());
            this.tallFlower(WHITE_ROSE_BUSH.get());
            this.tallFlower(YELLOW_ROSE_BUSH.get());
            this.tallFlower(WITHER_ROSE_BUSH.get());

            // sprouts
            this.add(SNOWY_SPROUTS.get(), Blocks::createShearsOnlyDrop);
            this.add(GELISOL_SPROUTS.get(), Blocks::createShearsOnlyDrop);
            this.add(DRY_MOSS_SPROUTS.get(), Blocks::createShearsOnlyDrop);

            // flowers
            this.dropSelf(RED_ROSE.get());
            this.dropSelf(PINK_ROSE.get());
            this.dropSelf(BLUE_ROSE.get());
            this.dropSelf(WHITE_ROSE.get());
            this.dropSelf(YELLOW_ROSE.get());
            this.dropSelf(FOXGLOVE.get());
            this.dropSelf(BLUEBELLS.get());
            this.dropSelf(SNOWDROP.get());
            this.dropSelf(MOSS_CAMPION.get());
            this.dropSelf(NIGHTSHADE.get());

            // pots
            this.dropPottedContents(POTTED_RED_ROSE.get());
            this.dropPottedContents(POTTED_PINK_ROSE.get());
            this.dropPottedContents(POTTED_BLUE_ROSE.get());
            this.dropPottedContents(POTTED_WHITE_ROSE.get());
            this.dropPottedContents(POTTED_YELLOW_ROSE.get());
            this.dropPottedContents(POTTED_FOXGLOVE.get());
            this.dropPottedContents(POTTED_BLUEBELLS.get());
            this.dropPottedContents(POTTED_SNOWDROP.get());
            this.dropPottedContents(POTTED_MOSS_CAMPION.get());
            this.dropPottedContents(POTTED_NIGHTSHADE.get());
            this.dropPottedContents(POTTED_SNOWY_SPROUTS.get());
            this.dropPottedContents(POTTED_GELISOL_SPROUTS.get());
            this.dropPottedContents(POTTED_DRY_MOSS_SPROUTS.get());

            // wild berry
            this.add(WILD_BERRY_BUSH.get(), b -> applyExplosionDecay(b, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                                    .setProperties(StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(SweetBerryBushBlock.AGE, 2)))
                            .add(LootItem.lootTableItem(WILD_BERRIES.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
            this.dropSelf(WILD_BERRY_BASKET.get());

            // icicle blocks
            this.dropSelf(ICICLES.get());
            this.dropSelf(ICICLE_BLOCK.get());
            this.dropSelf(CHISELED_ICICLE_BLOCK.get());
            this.add(ICICLE_DOOR.get(), Blocks::createDoorTable);
            this.dropSelf(ICICLE_TRAPDOOR.get());
            this.dropSelf(ICICLE_BARS.get());
            this.dropSelf(ICE_LANTERN.get());

            // tundra moss blocks
            this.dropSelf(DRY_MOSS_CARPET.get());
            this.dropSelf(DRY_MOSS_BLOCK.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_STAIRS.get());
            this.add(DRY_MOSSY_COBBLESTONE_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_COBBLESTONE_WALL.get());
            this.add(DRY_MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);
            this.dropSelf(DRY_MOSSY_STONE_BRICKS.get());
            this.dropSelf(DRY_MOSSY_STONE_BRICK_STAIRS.get());
            this.add(DRY_MOSSY_STONE_BRICK_SLAB.get(), Blocks::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_STONE_BRICK_WALL.get());
            this.add(DRY_MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), Blocks::createVerticalSlabItemTable);

            // mushroom crates
            this.dropSelf(RED_MUSHROOM_BASKET.get());
            this.dropSelf(BROWN_MUSHROOM_BASKET.get());
            this.dropSelf(GLOW_SHROOM_BASKET.get());

            // misc
            this.add(GELISOL.get(), b -> createSingleItemTableWithSilkTouch(b, net.minecraft.world.level.block.Blocks.DIRT));
            this.add(GELISOL_PATH.get(), b -> createSingleItemTableWithSilkTouch(b, net.minecraft.world.level.block.Blocks.DIRT));
            this.dropSelf(FROZEN_FLESH_BLOCK.get());
        }


        private void bookshelf(Block block) {
            this.add(block, b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantValue.exactly(3f)));
        }

        private void tallFlower(Block block) {
            this.add(block, b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        }

        private void leafPile(Block block) {
            this.add(block, b -> createMultifaceBlockDrops(b, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS))));
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
            this.add(WindsweptEntityTypes.CHILLED.get(),
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                                    .add(LootItem.lootTableItem(FROZEN_FLESH::get)
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
                                    LootPool.lootPool().add(LootItem.lootTableItem(MUSIC_DISC_SNOW::get))
                                            .when(LootItemEntityPropertyCondition.hasProperties(
                                                    LootContext.EntityTarget.KILLER,
                                                    EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));
            this.add(WindsweptEntityTypes.FROSTBITER.get(), LootTable.lootTable());

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
                    .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(SNOWY_SPROUTS.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f))))
                    .add(LootItem.lootTableItem(SNOW_BOOTS.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20))))
                    .add(LootItem.lootTableItem(Items.BOOK).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(WOODEN_BUCKET.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20))))
                    .add(LootItem.lootTableItem(WILD_BERRIES.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 3f))))
                    .add(LootItem.lootTableItem(Items.SNOW_BLOCK).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(Items.COBWEB).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(HOLLY_SAPLING.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(COOKED_GOAT.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
            ), builder);

            register("chestnut_weathered_house", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2f, 5f))
                    .add(LootItem.lootTableItem(Items.EMERALD).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(Items.FERN).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f))))
                    .add(LootItem.lootTableItem(Items.SWEET_BERRIES).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(CHESTNUT_LOG.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(Items.COBWEB).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(CHESTNUT_SAPLING.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(Items.BIRCH_SAPLING).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(WOODEN_BUCKET.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20))))
            ), builder);
        }

        private static void register(String name, LootTable.Builder lootTable, BiConsumer<ResourceLocation, Builder> builder) {
            builder.accept(Windswept.REGISTRY_HELPER.prefix("chests/" + name), lootTable);
        }

    }
}


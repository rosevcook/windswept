package com.rosemods.windswept.core.data.server;

import com.google.common.collect.ImmutableList;
import com.rosemods.windswept.common.block.GingerCropBlock;
import com.rosemods.windswept.common.block.LavenderBlock;
import com.rosemods.windswept.common.block.PineconeBlock;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;
import static net.minecraft.world.item.Items.*;

public class WindsweptLootTableProvider extends LootTableProvider {

    public WindsweptLootTableProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), BuiltInLootTables.all(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(WindsweptBlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(WindsweptEntityLoot::new, LootContextParamSets.ENTITY),
                new LootTableProvider.SubProviderEntry(WindsweptChestLoot::new, LootContextParamSets.CHEST),
                new LootTableProvider.SubProviderEntry(WindsweptArchaeologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
        ));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }

    private static <T> Stream<T> getContent(IForgeRegistry<T> entry) {
        return entry.getValues().stream().filter(i -> entry.getKey(i) != null
                && Windswept.MOD_ID.equals(entry.getKey(i).getNamespace()));
    }

    private static class WindsweptBlockLoot extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

        protected WindsweptBlockLoot() {
            super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            // holly
            this.dropSelf(STRIPPED_HOLLY_LOG.get());
            this.dropSelf(STRIPPED_HOLLY_WOOD.get());
            this.dropSelf(HOLLY_LOG.get());
            this.dropSelf(HOLLY_WOOD.get());
            this.dropSelf(HOLLY_PLANKS.get());
            this.add(HOLLY_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(HOLLY_STAIRS.get());
            this.dropSelf(HOLLY_FENCE.get());
            this.dropSelf(HOLLY_FENCE_GATE.get());
            this.dropSelf(HOLLY_PRESSURE_PLATE.get());
            this.add(HOLLY_DOOR.get(), this::createDoorTable);
            this.dropSelf(HOLLY_TRAPDOOR.get());
            this.dropSelf(HOLLY_BUTTON.get());
            this.dropSelf(HOLLY_SIGNS.getFirst().get());
            this.dropSelf(HOLLY_HANGING_SIGNS.getFirst().get());
            this.dropSelf(HOLLY_SAPLING.get());
            this.dropPottedContents(POTTED_HOLLY_SAPLING.get());

            this.add(HOLLY_BEEHIVE.get(), WindsweptBlockLoot::createBeeHiveDrop);
            this.dropSelf(HOLLY_LADDER.get());
            this.bookshelf(HOLLY_BOOKSHELF.get());
            this.dropWhenSilkTouch(CHISELED_HOLLY_BOOKSHELF.get());
            this.dropSelf(HOLLY_BOARDS.get());
            this.dropSelf(HOLLY_CHEST.get());
            this.dropSelf(TRAPPED_HOLLY_CHEST.get());

            this.add(HOLLY_LEAVES.get(), b -> createLeavesDrops(b, HOLLY_SAPLING
                    .get(), .05f, .0625f, .083333336f, .1f).withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                    .add(applyExplosionCondition(b, LootItem.lootTableItem(HOLLY_BERRIES.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(-6f, 1f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));


            this.leafPile(HOLLY_LEAF_PILE.get());

            this.dropSelf(HOLLY_BERRY_BASKET.get());

            // chestnut
            this.dropSelf(STRIPPED_CHESTNUT_LOG.get());
            this.dropSelf(STRIPPED_CHESTNUT_WOOD.get());
            this.dropSelf(CHESTNUT_LOG.get());
            this.dropSelf(CHESTNUT_WOOD.get());
            this.dropSelf(CHESTNUT_PLANKS.get());
            this.add(CHESTNUT_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(CHESTNUT_STAIRS.get());
            this.dropSelf(CHESTNUT_FENCE.get());
            this.dropSelf(CHESTNUT_FENCE_GATE.get());
            this.dropSelf(CHESTNUT_PRESSURE_PLATE.get());
            this.add(CHESTNUT_DOOR.get(), this::createDoorTable);
            this.dropSelf(CHESTNUT_TRAPDOOR.get());
            this.dropSelf(CHESTNUT_BUTTON.get());
            this.dropSelf(CHESTNUT_SIGNS.getFirst().get());
            this.dropSelf(CHESTNUT_HANGING_SIGNS.getFirst().get());
            this.dropSelf(CHESTNUT_SAPLING.get());
            this.dropPottedContents(POTTED_CHESTNUT_SAPLING.get());

            this.add(CHESTNUT_BEEHIVE.get(), WindsweptBlockLoot::createBeeHiveDrop);
            this.dropSelf(CHESTNUT_LADDER.get());
            this.bookshelf(CHESTNUT_BOOKSHELF.get());
            this.dropWhenSilkTouch(CHISELED_CHESTNUT_BOOKSHELF.get());
            this.dropSelf(CHESTNUT_BOARDS.get());
            this.dropSelf(CHESTNUT_CHEST.get());
            this.dropSelf(TRAPPED_CHESTNUT_CHEST.get());

            this.add(CHESTNUT_LEAVES.get(), b -> createLeavesDrops(b, CHESTNUT_SAPLING
                    .get(), .05f, .0625f, .083333336f, .1f).withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                    .add(applyExplosionCondition(b, LootItem.lootTableItem(CHESTNUTS.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(-4f, 1f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

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
            this.add(PINE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PINE_STAIRS.get());
            this.dropSelf(PINE_FENCE.get());
            this.dropSelf(PINE_FENCE_GATE.get());
            this.dropSelf(PINE_PRESSURE_PLATE.get());
            this.add(PINE_DOOR.get(), this::createDoorTable);
            this.dropSelf(PINE_TRAPDOOR.get());
            this.dropSelf(PINE_BUTTON.get());
            this.dropSelf(PINE_SIGNS.getFirst().get());
            this.dropSelf(PINE_HANGING_SIGNS.getFirst().get());
            this.dropSelf(PINE_SAPLING.get());
            this.dropPottedContents(POTTED_PINE_SAPLING.get());

            this.add(PINE_BEEHIVE.get(), WindsweptBlockLoot::createBeeHiveDrop);
            this.dropSelf(PINE_LADDER.get());
            this.bookshelf(PINE_BOOKSHELF.get());
            this.dropWhenSilkTouch(CHISELED_PINE_BOOKSHELF.get());
            this.dropSelf(PINE_BOARDS.get());
            this.dropSelf(PINE_CHEST.get());
            this.dropSelf(TRAPPED_PINE_CHEST.get());

            this.add(PINE_LEAVES.get(), b -> createLeavesDrops(b, PINE_SAPLING.get(), .05f, .0625f, .083333336f, .1f));
            this.leafPile(PINE_LEAF_PILE.get());

            // pinecone
            this.add(PINECONE.get(), this::createPineconeTable);
            this.add(FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(SOUL_FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(CUPRIC_FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(ENDER_FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(NIGHT_FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(REDSTONE_FAIRY_LIGHT.get(), this::createPineconeTable);

            this.dropSelf(PINECONE_JAM_BLOCK.get());

            this.dropSelf(PINECONE_BLOCK.get());
            this.dropSelf(CARVED_PINECONE_BLOCK.get());
            this.dropSelf(WILL_O_THE_WISP.get());

            this.dropSelf(ELDER_WING.get());
            this.dropSelf(ELDER_ORNAMENT.get());
            this.add(DREAM_CATCHER.get(), b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));

            // pinecone shingles
            this.dropSelf(PINECONE_SHINGLES.get());
            this.dropSelf(PINECONE_SHINGLE_STAIRS.get());
            this.add(PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(WHITE_PINECONE_SHINGLES.get());
            this.dropSelf(WHITE_PINECONE_SHINGLE_STAIRS.get());
            this.add(WHITE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(LIGHT_GRAY_PINECONE_SHINGLES.get());
            this.dropSelf(LIGHT_GRAY_PINECONE_SHINGLE_STAIRS.get());
            this.add(LIGHT_GRAY_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(GRAY_PINECONE_SHINGLES.get());
            this.dropSelf(GRAY_PINECONE_SHINGLE_STAIRS.get());
            this.add(GRAY_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BLACK_PINECONE_SHINGLES.get());
            this.dropSelf(BLACK_PINECONE_SHINGLE_STAIRS.get());
            this.add(BLACK_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BROWN_PINECONE_SHINGLES.get());
            this.dropSelf(BROWN_PINECONE_SHINGLE_STAIRS.get());
            this.add(BROWN_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(RED_PINECONE_SHINGLES.get());
            this.dropSelf(RED_PINECONE_SHINGLE_STAIRS.get());
            this.add(RED_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(ORANGE_PINECONE_SHINGLES.get());
            this.dropSelf(ORANGE_PINECONE_SHINGLE_STAIRS.get());
            this.add(ORANGE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(YELLOW_PINECONE_SHINGLES.get());
            this.dropSelf(YELLOW_PINECONE_SHINGLE_STAIRS.get());
            this.add(YELLOW_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(LIME_PINECONE_SHINGLES.get());
            this.dropSelf(LIME_PINECONE_SHINGLE_STAIRS.get());
            this.add(LIME_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(GREEN_PINECONE_SHINGLES.get());
            this.dropSelf(GREEN_PINECONE_SHINGLE_STAIRS.get());
            this.add(GREEN_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(CYAN_PINECONE_SHINGLES.get());
            this.dropSelf(CYAN_PINECONE_SHINGLE_STAIRS.get());
            this.add(CYAN_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(LIGHT_BLUE_PINECONE_SHINGLES.get());
            this.dropSelf(LIGHT_BLUE_PINECONE_SHINGLE_STAIRS.get());
            this.add(LIGHT_BLUE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BLUE_PINECONE_SHINGLES.get());
            this.dropSelf(BLUE_PINECONE_SHINGLE_STAIRS.get());
            this.add(BLUE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PURPLE_PINECONE_SHINGLES.get());
            this.dropSelf(PURPLE_PINECONE_SHINGLE_STAIRS.get());
            this.add(PURPLE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(MAGENTA_PINECONE_SHINGLES.get());
            this.dropSelf(MAGENTA_PINECONE_SHINGLE_STAIRS.get());
            this.add(MAGENTA_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PINK_PINECONE_SHINGLES.get());
            this.dropSelf(PINK_PINECONE_SHINGLE_STAIRS.get());
            this.add(PINK_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);

            // snow blocks
            this.dropSelf(SNOW_STAIRS.get());
            this.add(SNOW_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(SNOW_BRICKS.get());
            this.dropSelf(SNOW_BRICK_STAIRS.get());
            this.add(SNOW_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(SNOW_BRICK_WALL.get());

            this.add(SUSPICIOUS_SNOW.get(), noDrop());

            // packed ice blocks
            this.dropSelf(PACKED_ICE_STAIRS.get());
            this.add(PACKED_ICE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PACKED_ICE_BRICKS.get());
            this.dropSelf(CHISELED_PACKED_ICE_BRICKS.get());
            this.dropSelf(PACKED_ICE_BRICK_STAIRS.get());
            this.add(PACKED_ICE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PACKED_ICE_BRICK_WALL.get());

            // blue ice blocks
            this.dropSelf(BLUE_ICE_STAIRS.get());
            this.add(BLUE_ICE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BLUE_ICE_BRICKS.get());
            this.dropSelf(CHISELED_BLUE_ICE_BRICKS.get());
            this.dropSelf(BLUE_ICE_BRICK_STAIRS.get());
            this.add(BLUE_ICE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BLUE_ICE_BRICK_WALL.get());

            // shale
            this.dropSelf(SHALE.get());
            this.dropSelf(SHALE_STAIRS.get());
            this.add(SHALE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(SHALE_WALL.get());
            this.dropSelf(POLISHED_SHALE.get());
            this.dropSelf(POLISHED_SHALE_STAIRS.get());
            this.dropSelf(POLISHED_SHALE_WALL.get());
            this.add(POLISHED_SHALE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(POLISHED_SHALE_BRICKS.get());
            this.dropSelf(ICY_POLISHED_SHALE_BRICKS.get());
            this.dropSelf(CHISELED_POLISHED_SHALE_BRICKS.get());
            this.dropSelf(POLISHED_SHALE_BRICK_STAIRS.get());
            this.add(POLISHED_SHALE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(POLISHED_SHALE_BRICK_WALL.get());

            // candy cane
            this.dropSelf(CANDY_CANE_BLOCK.get());

            // ginger
            this.add(GINGER.get(), applyExplosionDecay(GINGER.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(GINGER_ROOT.get())))
                    .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(GINGER.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 4)))
                            .add(LootItem.lootTableItem(GINGER_ROOT.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, .5714286f, 3))))));
            this.add(GINGER_SOIL.get(), b -> dropTwoOthers(b, Items.DIRT, GINGER_ROOT.get()));
            this.dropSelf(GINGERBREAD_BLOCK.get());
            this.dropSelf(GLAZED_GINGERBREAD_BLOCK.get());
            this.dropSelf(GINGERBREAD_BRICKS.get());
            this.dropSelf(GINGERBREAD_BRICK_STAIRS.get());
            this.add(GINGERBREAD_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(GINGERBREAD_BRICK_WALL.get());
            this.dropSelf(GLAZED_GINGERBREAD_BRICKS.get());
            this.dropSelf(GINGERBREAD_COOKIE_BLOCK.get());
            this.dropSelf(GLAZED_GINGERBREAD_BRICK_STAIRS.get());
            this.add(GLAZED_GINGERBREAD_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(GLAZED_GINGERBREAD_BRICK_WALL.get());
            this.dropSelf(GINGER_ROOT_CRATE.get());
            this.add(GINGERBREAD_DOOR.get(), this::createDoorTable);
            this.dropSelf(GINGERBREAD_TRAPDOOR.get());

            // decoration
            this.dropSelf(FROSTBITER_TROPHY.get());
            /*
            this.add(CHRISTMAS_PUDDING.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(CHRISTMAS_PUDDING_SLICE.get())
                            .apply(ChristmasPuddingBlock.STATE.getPossibleValues(), value -> SetItemCountFunction
                                    .setCount(ConstantValue.exactly(value.getIndex() + 1), false)
                                    .when(stateCond(CHRISTMAS_PUDDING, ChristmasPuddingBlock.STATE, value))))
                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(WindsweptItemTags.KNIVES)))
            ).withPool(LootPool.lootPool().add(LootItem.lootTableItem(HOLLY_BERRIES.get()))));
             */
            this.add(CHRISTMAS_PUDDING.get(), noDrop());

            this.dropSelf(HOLLY_WREATH.get());
            this.dropSelf(PINECONE_WREATH.get());
            this.dropSelf(VINE_WREATH.get());
            this.dropSelf(CHERRY_WREATH.get());
            this.dropSelf(ICE_SHEET.get());

            // tall plants
            this.tallFlower(LUPINE.get());
            this.tallFlower(LIONS_TAIL.get());
            this.tallFlower(RED_ROSE_BUSH.get());
            this.tallFlower(BLUE_ROSE_BUSH.get());
            this.tallFlower(WHITE_ROSE_BUSH.get());
            this.tallFlower(YELLOW_ROSE_BUSH.get());

            // sprouts
            this.add(SNOWY_SPROUTS.get(), WindsweptBlockLoot::createShearsOnlyDrop);
            this.add(GELISOL_GRASS.get(), WindsweptBlockLoot::createShearsOnlyDrop);
            this.add(DRY_MOSSY_SPROUTS.get(), WindsweptBlockLoot::createShearsOnlyDrop);
            this.add(MOSSY_SPROUTS.get(), WindsweptBlockLoot::createShearsOnlyDrop);

            // flowers
            this.dropSelf(RED_ROSE.get());
            this.dropSelf(BLUE_ROSE.get());
            this.dropSelf(WHITE_ROSE.get());
            this.dropSelf(YELLOW_ROSE.get());
            this.dropSelf(FOXGLOVE.get());
            this.dropSelf(BLUEBELLS.get());
            this.dropSelf(SNOWDROP.get());
            this.dropSelf(MOSS_CAMPION.get());
            this.add(WILD_GINGER.get(), this::createWildGingerDrops);
            this.dropSelf(NIGHTSHADE.get());
            this.add(LAVENDER.get(), this::createLavenderTable);
            this.dropSelf(MIMOSA.get());

            this.dropSelf(FLOWERING_ACACIA_SAPLING.get());
            this.dropPottedContents(POTTED_FLOWERING_ACACIA_SAPLING.get());
            this.add(FLOWERING_ACACIA_LEAVES.get(), b -> createLeavesDrops(b, FLOWERING_ACACIA_SAPLING.get(), .05f, .0625f, .083333336f, .1f));
            this.leafPile(FLOWERING_ACACIA_LEAF_PILE.get());
            this.add(YELLOW_PETALS.get(), this::createPetalsTable);

            // pots
            this.dropPottedContents(POTTED_RED_ROSE.get());
            this.dropPottedContents(POTTED_BLUE_ROSE.get());
            this.dropPottedContents(POTTED_WHITE_ROSE.get());
            this.dropPottedContents(POTTED_YELLOW_ROSE.get());
            this.dropPottedContents(POTTED_FOXGLOVE.get());
            this.dropPottedContents(POTTED_BLUEBELLS.get());
            this.dropPottedContents(POTTED_SNOWDROP.get());
            this.dropPottedContents(POTTED_MOSS_CAMPION.get());
            this.dropPottedContents(POTTED_WILD_GINGER.get());
            this.dropPottedContents(POTTED_NIGHTSHADE.get());
            this.dropPottedContents(POTTED_SNOWY_SPROUTS.get());
            this.dropPottedContents(POTTED_GELISOL_GRASS.get());
            this.dropPottedContents(POTTED_DRY_MOSSY_SPROUTS.get());
            this.dropPottedContents(POTTED_MOSSY_SPROUTS.get());
            this.dropPottedContents(POTTED_LAVENDER.get());
            this.dropPottedContents(POTTED_MIMOSA.get());

            // lavender thatch
            this.dropSelf(LAVENDER_BALE.get());
            this.dropSelf(LAVENDER_THATCH.get());
            this.dropSelf(LAVENDER_THATCH_STAIRS.get());
            this.add(LAVENDER_THATCH_SLAB.get(), this::createSlabItemTable);

            // wild berry
            this.add(WILD_BERRY_BUSH.get(), b -> applyExplosionDecay(b, LootTable.lootTable().withPool(LootPool.lootPool()
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
            this.add(ICICLE_DOOR.get(), this::createDoorTable);
            this.dropSelf(ICICLE_TRAPDOOR.get());
            this.dropSelf(ICICLE_BARS.get());
            this.dropSelf(ICE_LANTERN.get());
            this.dropSelf(ICE_CHAIN.get());

            // lunalite
            this.dropSelf(LUNALITE.get());
            this.dropSelf(LUNALITE_STAIRS.get());
            this.add(LUNALITE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(LUNALITE_WALL.get());
            this.dropSelf(CUT_LUNALITE.get());
            this.dropSelf(CUT_LUNALITE_STAIRS.get());
            this.add(CUT_LUNALITE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(CUT_LUNALITE_WALL.get());
            this.dropSelf(CUT_LUNALITE_BRICKS.get());
            this.dropSelf(CHISELED_CUT_LUNALITE_BRICKS.get());
            this.dropSelf(CUT_LUNALITE_BRICK_STAIRS.get());
            this.add(CUT_LUNALITE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(CUT_LUNALITE_BRICK_WALL.get());
            this.dropSelf(SMOOTH_LUNALITE.get());
            this.dropSelf(SMOOTH_LUNALITE_STAIRS.get());
            this.add(SMOOTH_LUNALITE_SLAB.get(), this::createSlabItemTable);

            // dry moss blocks
            this.dropSelf(DRY_MOSS_CARPET.get());
            this.dropSelf(DRY_MOSS_BLOCK.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_STAIRS.get());
            this.add(DRY_MOSSY_COBBLESTONE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_COBBLESTONE_WALL.get());
            this.dropSelf(DRY_MOSSY_STONE_BRICKS.get());
            this.dropSelf(DRY_MOSSY_STONE_BRICK_STAIRS.get());
            this.add(DRY_MOSSY_STONE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_STONE_BRICK_WALL.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_BRICKS.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_BRICK_STAIRS.get());
            this.add(DRY_MOSSY_COBBLESTONE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_COBBLESTONE_BRICK_WALL.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_TILES.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_TILE_STAIRS.get());
            this.add(DRY_MOSSY_COBBLESTONE_TILE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_COBBLESTONE_TILE_WALL.get());

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
            return getContent(ForgeRegistries.BLOCKS).collect(Collectors.toSet());
        }

        protected LootTable.Builder createWildGingerDrops(Block block) {
            return createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(GINGER_ROOT.get())));
        }

        private LootTable.Builder dropTwoOthers(Block block, ItemLike other, ItemLike another) {
            return LootTable.lootTable()
                    .withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).when(HAS_SILK_TOUCH).add(LootItem.lootTableItem(block))))
                    .withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).when(HAS_NO_SILK_TOUCH).add(LootItem.lootTableItem(other))))
                    .withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).when(HAS_NO_SILK_TOUCH).add(LootItem.lootTableItem(another))));
        }

        private LootTable.Builder createPineconeTable(Block block) {
            return LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(applyExplosionDecay(block, LootItem.lootTableItem(block)
                                    .apply(List.of(2, 3, 4), i -> SetItemCountFunction.setCount(ConstantValue.exactly((float) i))
                                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PineconeBlock.AMOUNT, i)))))));
        }

        private LootTable.Builder createPetalsTable(Block block) {
            return LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(applyExplosionDecay(block, LootItem.lootTableItem(block)
                                    .apply(List.of(2, 3, 4), i -> SetItemCountFunction.setCount(ConstantValue.exactly((float) i))
                                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PinkPetalsBlock.AMOUNT, i)))))));
        }

        private LootTable.Builder createLavenderTable(Block block) {
            return LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(this.applyExplosionDecay(block, LootItem.lootTableItem(block)
                                    .apply(List.of(0, 1, 2), i -> SetItemCountFunction.setCount(ConstantValue.exactly(i + 1f))
                                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LavenderBlock.AGE, i)))))));
        }

        private static <V extends Comparable<V>> LootItemCondition.Builder stateCond(RegistryObject<? extends Block> block, Property<V> property, V v) {
            return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, v.toString()));
        }

    }

    private static class WindsweptEntityLoot extends EntityLootSubProvider {

        protected WindsweptEntityLoot() {
            super(FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        public void generate() {
            this.add(WindsweptEntityTypes.CHILLED.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(LootItem.lootTableItem(FROZEN_FLESH.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(-2f, 2f)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0f, 1f)))))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(LootItem.lootTableItem(ICICLES.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(-3f, 1f)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0f, 1f)))))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(LootItem.lootTableItem(GOLD_INGOT))
                            .add(LootItem.lootTableItem(BEETROOT))
                            .add(LootItem.lootTableItem(APPLE)
                                    .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(.025f, .01f)))
                    .withPool(LootPool.lootPool()
                            .add(LootItem.lootTableItem(MUSIC_DISC_SNOW::get))
                            .when(LootItemEntityPropertyCondition.hasProperties(
                                    LootContext.EntityTarget.KILLER,
                                    EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));
            this.add(WindsweptEntityTypes.FROSTBITER.get(), LootTable.lootTable());

        }

        @Override
        public Stream<EntityType<?>> getKnownEntityTypes() {
            return getContent(ForgeRegistries.ENTITY_TYPES);
        }
    }

    private static class WindsweptChestLoot implements LootTableSubProvider {

        @Override
        public void generate(BiConsumer<ResourceLocation, Builder> builder) {
            register("grove_weathered_house", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4f, 8f))
                    .add(LootItem.lootTableItem(GOLD_INGOT).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(SNOWY_SPROUTS.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f))))
                    .add(LootItem.lootTableItem(SNOW_BOOTS.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20))))
                    .add(LootItem.lootTableItem(BOOK).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(WOODEN_BUCKET.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20))))
                    .add(LootItem.lootTableItem(SWEET_SNOW_CONE.get()).setWeight(1))
                    .add(LootItem.lootTableItem(WILD_BERRIES.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 3f))))
                    .add(LootItem.lootTableItem(ICICLES.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(SNOWBALL).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(COBWEB).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(HOLLY_SAPLING.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(COOKED_GOAT.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
            ), builder);

            register("chestnut_weathered_house", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2f, 5f))
                    .add(LootItem.lootTableItem(EMERALD).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(FERN).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f))))
                    .add(LootItem.lootTableItem(SWEET_BERRIES).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(CHESTNUT_LOG.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(COBWEB).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(CHESTNUT_SAPLING.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(BIRCH_SAPLING).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(WOODEN_BUCKET.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20))))
            ), builder);

            register("village/village_frozen_house", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3f, 8f))
                    .add(LootItem.lootTableItem(Items.EMERALD).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(GINGER_ROOT.get()).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(SHALE.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))))
                    .add(LootItem.lootTableItem(HOLLY_LOG.get()).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                    .add(LootItem.lootTableItem(BLUE_ICE_BRICKS.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
                    .add(LootItem.lootTableItem(BEETROOT_SEEDS).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(COAL).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(BLACK_WOOL).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(SNOWBALL).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                    .add(LootItem.lootTableItem(BEETROOT_SOUP))), builder);
        }

        private static void register(String name, LootTable.Builder lootTable, BiConsumer<ResourceLocation, Builder> builder) {
            builder.accept(Windswept.location("chests/" + name), lootTable);
        }

    }

    private static class WindsweptArchaeologyLoot implements LootTableSubProvider {

        @Override
        public void generate(BiConsumer<ResourceLocation, Builder> builder) {
            register("pine_totem", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                    .add(LootItem.lootTableItem(HOOT_POTTERY_SHERD.get()).setWeight(3))
                    .add(LootItem.lootTableItem(PLUMAGE_POTTERY_SHERD.get()).setWeight(4))
                    .add(LootItem.lootTableItem(ELDER_FEATHER.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                    .add(LootItem.lootTableItem(PINECONE.get()).setWeight(1))
                    .add(LootItem.lootTableItem(EMERALD).setWeight(1))
                    .add(LootItem.lootTableItem(STICK).setWeight(1))
            ), builder);

            register("snowy_pine_totem", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                    .add(LootItem.lootTableItem(HOOT_POTTERY_SHERD.get()).setWeight(3))
                    .add(LootItem.lootTableItem(FLAKE_POTTERY_SHERD.get()).setWeight(3))
                    .add(LootItem.lootTableItem(PLUMAGE_POTTERY_SHERD.get()).setWeight(4))
                    .add(LootItem.lootTableItem(ELDER_FEATHER.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                    .add(LootItem.lootTableItem(PINECONE.get()).setWeight(1))
                    .add(LootItem.lootTableItem(FROZEN_FLESH.get()).setWeight(1))
                    .add(LootItem.lootTableItem(EMERALD).setWeight(1))
                    .add(LootItem.lootTableItem(STICK).setWeight(1))
            ), builder);

            register("grove_weathered_house", LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                    .add(LootItem.lootTableItem(FLAKE_POTTERY_SHERD.get()).setWeight(3))
                    .add(LootItem.lootTableItem(OFFSHOOT_POTTERY_SHERD.get()).setWeight(3))
                    .add(LootItem.lootTableItem(DRUPES_POTTERY_SHERD.get()).setWeight(3))
                    .add(LootItem.lootTableItem(ELDER_FEATHER.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                    .add(LootItem.lootTableItem(HOLLY_BERRIES.get()).setWeight(1))
                    .add(LootItem.lootTableItem(FROZEN_FLESH.get()).setWeight(1))
                    .add(LootItem.lootTableItem(EMERALD).setWeight(1))
                    .add(LootItem.lootTableItem(STICK).setWeight(1))
            ), builder);
        }

        private static void register(String name, LootTable.Builder lootTable, BiConsumer<ResourceLocation, Builder> builder) {
            builder.accept(Windswept.location("archaeology/" + name), lootTable);
        }

    }
}


package com.rosemods.windswept.core.data.client;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.wild_berry.WildBerryBushBlock;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock.VerticalSlabType;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.Blueprint;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WindsweptBlockStateProvider extends BlockStateProvider {
	
	public WindsweptBlockStateProvider(GatherDataEvent event) {
		super(event.getGenerator(), Windswept.MODID, event.getExistingFileHelper());
	}

	@Override
	protected void registerStatesAndModels() {
		this.log(WindsweptBlocks.STRIPPED_HOLLY_LOG);
		this.wood(WindsweptBlocks.STRIPPED_HOLLY_WOOD, this.blockTexture(WindsweptBlocks.STRIPPED_HOLLY_LOG.get()));
		this.log(WindsweptBlocks.HOLLY_LOG);
		this.wood(WindsweptBlocks.HOLLY_WOOD, this.blockTexture(WindsweptBlocks.HOLLY_LOG.get()));
		this.cubeAll(WindsweptBlocks.HOLLY_PLANKS);
		this.slab(WindsweptBlocks.HOLLY_SLAB, WindsweptBlocks.HOLLY_PLANKS);
		this.stairs(WindsweptBlocks.HOLLY_STAIRS, WindsweptBlocks.HOLLY_PLANKS);
		this.fence(WindsweptBlocks.HOLLY_FENCE, WindsweptBlocks.HOLLY_PLANKS);
		this.fenceGate(WindsweptBlocks.HOLLY_FENCE_GATE, WindsweptBlocks.HOLLY_PLANKS);
		this.pressurePlate(WindsweptBlocks.HOLLY_PRESSURE_PLATE, WindsweptBlocks.HOLLY_PLANKS);
		this.door(WindsweptBlocks.HOLLY_DOOR);
		this.trapdoor(WindsweptBlocks.HOLLY_TRAPDOOR);
		this.button(WindsweptBlocks.HOLLY_BUTTON, WindsweptBlocks.HOLLY_PLANKS);
		this.signs(WindsweptBlocks.HOLLY_SIGNS, WindsweptBlocks.HOLLY_PLANKS);
		this.leaves(WindsweptBlocks.HOLLY_LEAVES);
		this.pottedPlant(WindsweptBlocks.HOLLY_SAPLING, WindsweptBlocks.POTTED_HOLLY_SAPLING);
		this.beehive(WindsweptBlocks.HOLLY_BEEHIVE);
		this.hedge(WindsweptBlocks.HOLLY_HEDGE, WindsweptBlocks.HOLLY_LEAVES, WindsweptBlocks.HOLLY_LOG);
		this.leafCarpet(WindsweptBlocks.HOLLY_LEAF_CARPET, WindsweptBlocks.HOLLY_LEAVES);
		this.leafPile(WindsweptBlocks.HOLLY_LEAF_PILE, WindsweptBlocks.HOLLY_LEAVES, false);
		this.ladder(WindsweptBlocks.HOLLY_LADDER);
		this.verticalPlanks(WindsweptBlocks.VERTICAL_HOLLY_PLANKS, WindsweptBlocks.HOLLY_PLANKS);
		this.bookshelf(WindsweptBlocks.HOLLY_BOOKSHELF, WindsweptBlocks.HOLLY_PLANKS);
		this.boards(WindsweptBlocks.HOLLY_BOARDS);
		this.cabinet(WindsweptBlocks.HOLLY_CABINET);
		this.verticalSlab(WindsweptBlocks.HOLLY_VERTICAL_SLAB, WindsweptBlocks.HOLLY_PLANKS);
		this.post(WindsweptBlocks.HOLLY_POST, WindsweptBlocks.HOLLY_LOG);
		this.post(WindsweptBlocks.STRIPPED_HOLLY_POST, WindsweptBlocks.STRIPPED_HOLLY_LOG);
		this.chests(WindsweptBlocks.HOLLY_CHEST, WindsweptBlocks.HOLLY_TRAPPED_CHEST, WindsweptBlocks.HOLLY_PLANKS);
		this.cubeBottomTop(WindsweptBlocks.HOLLY_BERRY_CRATE);
		
		this.log(WindsweptBlocks.STRIPPED_CHESTNUT_LOG);
		this.wood(WindsweptBlocks.STRIPPED_CHESTNUT_WOOD, this.blockTexture(WindsweptBlocks.STRIPPED_CHESTNUT_LOG.get()));
		this.log(WindsweptBlocks.CHESTNUT_LOG);
		this.wood(WindsweptBlocks.CHESTNUT_WOOD, this.blockTexture(WindsweptBlocks.CHESTNUT_LOG.get()));
		this.cubeAll(WindsweptBlocks.CHESTNUT_PLANKS);
		this.slab(WindsweptBlocks.CHESTNUT_SLAB, WindsweptBlocks.CHESTNUT_PLANKS);
		this.stairs(WindsweptBlocks.CHESTNUT_STAIRS, WindsweptBlocks.CHESTNUT_PLANKS);
		this.fence(WindsweptBlocks.CHESTNUT_FENCE, WindsweptBlocks.CHESTNUT_PLANKS);
		this.fenceGate(WindsweptBlocks.CHESTNUT_FENCE_GATE, WindsweptBlocks.CHESTNUT_PLANKS);
		this.pressurePlate(WindsweptBlocks.CHESTNUT_PRESSURE_PLATE, WindsweptBlocks.CHESTNUT_PLANKS);
		this.door(WindsweptBlocks.CHESTNUT_DOOR);
		this.trapdoor(WindsweptBlocks.CHESTNUT_TRAPDOOR);
		this.button(WindsweptBlocks.CHESTNUT_BUTTON, WindsweptBlocks.CHESTNUT_PLANKS);
		this.signs(WindsweptBlocks.CHESTNUT_SIGNS, WindsweptBlocks.CHESTNUT_PLANKS);
		this.leaves(WindsweptBlocks.CHESTNUT_LEAVES);
		this.pottedPlant(WindsweptBlocks.CHESTNUT_SAPLING, WindsweptBlocks.POTTED_CHESTNUT_SAPLING);
		this.beehive(WindsweptBlocks.CHESTNUT_BEEHIVE);
		this.hedge(WindsweptBlocks.CHESTNUT_HEDGE, WindsweptBlocks.CHESTNUT_LEAVES, WindsweptBlocks.CHESTNUT_LOG);
		this.leafCarpet(WindsweptBlocks.CHESTNUT_LEAF_CARPET, WindsweptBlocks.CHESTNUT_LEAVES);
		this.leafPile(WindsweptBlocks.CHESTNUT_LEAF_PILE, WindsweptBlocks.CHESTNUT_LEAVES, true);
		this.ladder(WindsweptBlocks.CHESTNUT_LADDER);
		this.verticalPlanks(WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS, WindsweptBlocks.CHESTNUT_PLANKS);
		this.bookshelf(WindsweptBlocks.CHESTNUT_BOOKSHELF, WindsweptBlocks.CHESTNUT_PLANKS);
		this.boards(WindsweptBlocks.CHESTNUT_BOARDS);
		this.cabinet(WindsweptBlocks.CHESTNUT_CABINET);
		this.verticalSlab(WindsweptBlocks.CHESTNUT_VERTICAL_SLAB, WindsweptBlocks.CHESTNUT_PLANKS);
		this.post(WindsweptBlocks.CHESTNUT_POST, WindsweptBlocks.CHESTNUT_LOG);
		this.post(WindsweptBlocks.STRIPPED_CHESTNUT_POST, WindsweptBlocks.STRIPPED_CHESTNUT_LOG);
		this.chests(WindsweptBlocks.CHESTNUT_CHEST, WindsweptBlocks.CHESTNUT_TRAPPED_CHEST, WindsweptBlocks.CHESTNUT_PLANKS);

		this.cubeBottomTop(WindsweptBlocks.CHESTNUT_CRATE);
		this.cubeBottomTop(WindsweptBlocks.ROASTED_CHESTNUT_CRATE);

		this.cubeAll(WindsweptBlocks.SNOW_BRICKS);
		this.stairs(WindsweptBlocks.SNOW_BRICK_STAIRS, WindsweptBlocks.SNOW_BRICKS);
		this.slab(WindsweptBlocks.SNOW_BRICK_SLAB, WindsweptBlocks.SNOW_BRICKS);
		this.wall(WindsweptBlocks.SNOW_BRICK_WALL, WindsweptBlocks.SNOW_BRICKS);
		this.verticalSlab(WindsweptBlocks.SNOW_BRICK_VERTICAL_SLAB, WindsweptBlocks.SNOW_BRICKS);
		
		this.cubeAll(WindsweptBlocks.PACKED_ICE_BRICKS);
		this.cubeAll(WindsweptBlocks.CHISELED_PACKED_ICE_BRICKS);
		this.stairs(WindsweptBlocks.PACKED_ICE_BRICK_STAIRS, WindsweptBlocks.PACKED_ICE_BRICKS);
		this.slab(WindsweptBlocks.PACKED_ICE_BRICK_SLAB, WindsweptBlocks.PACKED_ICE_BRICKS);
		this.wall(WindsweptBlocks.PACKED_ICE_BRICK_WALL, WindsweptBlocks.PACKED_ICE_BRICKS);
		this.verticalSlab(WindsweptBlocks.PACKED_ICE_BRICK_VERTICAL_SLAB, WindsweptBlocks.PACKED_ICE_BRICKS);
		
		this.cubeAll(WindsweptBlocks.BLUE_ICE_BRICKS);
		this.cubeAll(WindsweptBlocks.CHISELED_BLUE_ICE_BRICKS);
		this.stairs(WindsweptBlocks.BLUE_ICE_BRICK_STAIRS, WindsweptBlocks.BLUE_ICE_BRICKS);
		this.slab(WindsweptBlocks.BLUE_ICE_BRICK_SLAB, WindsweptBlocks.BLUE_ICE_BRICKS);
		this.wall(WindsweptBlocks.BLUE_ICE_BRICK_WALL, WindsweptBlocks.BLUE_ICE_BRICKS);
		this.verticalSlab(WindsweptBlocks.BLUE_ICE_BRICK_VERTICAL_SLAB, WindsweptBlocks.BLUE_ICE_BRICKS);

		this.pressurePlate(WindsweptBlocks.POLISHED_DEEPSLATE_PRESSURE_PLATE, () -> Blocks.POLISHED_DEEPSLATE);
		this.button(WindsweptBlocks.POLISHED_DEEPSLATE_BUTTON, () -> Blocks.POLISHED_DEEPSLATE);
		
		this.simpleCross(WindsweptBlocks.SNOWY_SPROUTS);
		this.pot(WindsweptBlocks.POTTED_SNOWY_SPROUTS, this.modLoc("block/potted_snowy_sprouts"));

		this.tallPlant(WindsweptBlocks.RED_ROSE_BUSH);
		this.tallPlant(WindsweptBlocks.PINK_ROSE_BUSH);
		this.tallPlant(WindsweptBlocks.BLUE_ROSE_BUSH);
		this.tallPlant(WindsweptBlocks.WHITE_ROSE_BUSH);
		this.tallPlant(WindsweptBlocks.YELLOW_ROSE_BUSH);
		this.tallPlant(WindsweptBlocks.WITHER_ROSE_BUSH);

		this.pottedPlant(WindsweptBlocks.RED_ROSE, WindsweptBlocks.POTTED_RED_ROSE);
		this.pottedPlant(WindsweptBlocks.PINK_ROSE, WindsweptBlocks.POTTED_PINK_ROSE);
		this.pottedPlant(WindsweptBlocks.BLUE_ROSE, WindsweptBlocks.POTTED_BLUE_ROSE);
		this.pottedPlant(WindsweptBlocks.WHITE_ROSE, WindsweptBlocks.POTTED_WHITE_ROSE);
		this.pottedPlant(WindsweptBlocks.YELLOW_ROSE, WindsweptBlocks.POTTED_YELLOW_ROSE);
		this.pottedPlant(WindsweptBlocks.FOXGLOVE, WindsweptBlocks.POTTED_FOXGLOVE);
		this.pottedPlant(WindsweptBlocks.NIGHTSHADE, WindsweptBlocks.POTTED_NIGHTSHADE);

		this.blubells(WindsweptBlocks.BLUEBELLS, WindsweptBlocks.POTTED_BLUEBELLS);

		this.wildBerryBush(WindsweptBlocks.WILD_BERRY_BUSH);
		this.cubeBottomTop(WindsweptBlocks.WILD_BERRY_SACK);
		this.simpleCross(WindsweptBlocks.WILD_BERRY_BUSH_PIPS);

		this.cubeBottomTop(WindsweptBlocks.RED_MUSHROOM_CRATE);
		this.cubeBottomTop(WindsweptBlocks.BROWN_MUSHROOM_CRATE);
		this.cubeBottomTop(WindsweptBlocks.GLOW_SHROOM_CRATE);

		this.iceSheet(WindsweptBlocks.ICE_SHEET, () -> Blocks.ICE);
	}

	// Blocks //

	private void wildBerryBush(RegistryObject<Block> bush) {
		String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(bush.get())).getPath();
		final Function<Integer, ModelFile> model = i -> this.models().cross(name + "_stage" + i, this.modLoc("block/" + name + "_stage" + i)).renderType("cutout");
		this.getVariantBuilder(bush.get())
			.partialState().with(WildBerryBushBlock.AGE, 0).addModels(new ConfiguredModel(model.apply(0), 0, 0, true))
	     	.partialState().with(WildBerryBushBlock.AGE, 1).addModels(new ConfiguredModel(model.apply(1), 0, 180, true))
	     	.partialState().with(WildBerryBushBlock.AGE, 2).addModels(new ConfiguredModel(model.apply(2), 0, 90, true));
	}

	private void blubells(RegistryObject<Block> bluebells, RegistryObject<Block> pot) {
		this.pot(pot, this.modLoc("block/potted_" + this.getName(bluebells)));
		this.simpleBlock(bluebells.get(), this.models().withExistingParent(this.getName(bluebells), Windswept.REGISTRY_HELPER.prefix("bluebells_template")));
		this.generatedItem(bluebells);
	}

	private void tallPlant(RegistryObject<Block> flower) {
		String name = getName(flower);
		Function<String, ModelFile> model = s -> this.models().cross(name + "_" + s, this.modLoc("block/" + name + "_" + s)).renderType("cutout");

		this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc("block/" + name + "_top"));
		this.getVariantBuilder(flower.get())
				.partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).addModels(new ConfiguredModel(model.apply("top")))
				.partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).addModels(new ConfiguredModel(model.apply("bottom")));
	}
	
	private void pottedPlant(RegistryObject<Block> plant, RegistryObject<Block> pot) {
		this.pot(pot, this.blockTexture(plant.get()));
		this.simpleCross(plant);
		this.generatedItem(plant);
	}

	private void pot(RegistryObject<Block> pot, ResourceLocation texture) {
		ModelFile model = this.models().withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(pot.get())).getPath(), "block/flower_pot_cross").texture("plant", texture).renderType("cutout");
		this.simpleBlock(pot.get(), model);
	}

	private void iceSheet(RegistryObject<Block> block, Supplier<Block> textureBlock) {
		ResourceLocation texture = this.blockTexture(textureBlock.get());

		this.paneBlockWithRenderType((IronBarsBlock) block.get(), texture, texture, "translucent");
		this.itemModels().withExistingParent(this.getName(block), "item/generated").texture("layer0", texture).renderType("translucent");
	}
	
	private void trapdoor(RegistryObject<? extends Block> trapdoor) {		
		this.trapdoorBlockWithRenderType((TrapDoorBlock) trapdoor.get(), this.blockTexture(trapdoor.get()), true, "cutout");
		this.itemModels().withExistingParent(this.getName(trapdoor), this.modLoc("block/" + this.getName(trapdoor) + "_bottom"));
	}
	
	private void door(RegistryObject<? extends Block> door) {
		String name = "block/" + this.getName(door);
		this.doorBlockWithRenderType((DoorBlock) door.get(), this.getName(door).replace("_door", ""), this.modLoc(name + "_bottom"), this.modLoc(name + "_top"), "cutout");
	}
	
	private void button(RegistryObject<? extends Block> button, Supplier<? extends Block> textureBlock) {
		ResourceLocation texture = this.blockTexture(textureBlock.get());
		this.buttonBlock((ButtonBlock) button.get(), texture);
		this.itemModels().buttonInventory(this.getName(button), texture);
	}
	
	private void pressurePlate(RegistryObject<? extends Block> pressurePlate, Supplier<? extends Block> textureBlock) {
		this.pressurePlateBlock((PressurePlateBlock) pressurePlate.get(), this.blockTexture(textureBlock.get()));
		this.itemModel(pressurePlate);
	}
	
	private void ladder(RegistryObject<? extends Block> ladder) {
		ResourceLocation texture = this.blockTexture(ladder.get());

		this.horizontalBlock(ladder.get(), this.models().withExistingParent(this.getName(ladder), "block/ladder").texture("particle", texture).renderType("cutout").texture("texture", texture));
		this.generatedItem(ladder);
	}
	
	private void fence(RegistryObject<? extends Block> fence, Supplier<? extends Block> textureBlock) {
		ResourceLocation texture = this.blockTexture(textureBlock.get());
		this.fenceBlock((FenceBlock) fence.get(), texture);
		this.itemModels().fenceInventory(this.getName(fence), texture);
	}
	
	private void fenceGate(RegistryObject<? extends Block> gate, Supplier<? extends Block> textureBlock) {
		this.fenceGateBlock((FenceGateBlock) gate.get(), this.blockTexture(textureBlock.get()));
		this.itemModel(gate);
	}
	
	private void slab(RegistryObject<? extends Block> slab, Supplier<? extends Block> textureBlock) {
		ResourceLocation texture = this.blockTexture(textureBlock.get());
		this.slabBlock((SlabBlock) slab.get(), texture, texture);
		this.itemModel(slab);
	}
	
	private void stairs(RegistryObject<? extends Block> stairs, Supplier<? extends Block> textureBlock) {
		this.stairsBlock((StairBlock) stairs.get(), this.blockTexture(textureBlock.get()));
		this.itemModel(stairs);
	}
	
	private void wall(RegistryObject<? extends Block> wall, Supplier<? extends Block> textureBlock) {
		ResourceLocation texture = this.blockTexture(textureBlock.get());
		this.wallBlock((WallBlock) wall.get(), texture);
		this.itemModels().wallInventory(this.getName(wall), texture);
	}
	
	private void cubeAll(RegistryObject<? extends Block> block) {
		this.simpleBlock(block.get());
		this.itemModel(block);
	}
	
	private void cubeAll(RegistryObject<? extends Block> block, ResourceLocation texture) {
		this.simpleBlock(block.get(), this.models().cubeAll(this.getName(block), texture));
		this.itemModel(block);
	}
	
	private void leaves(RegistryObject<? extends Block> leaves) {
		this.simpleBlock(leaves.get(), this.models().withExistingParent(this.getName(leaves), "block/leaves").texture("all", this.blockTexture(leaves.get())).renderType("cutout"));
		this.itemModel(leaves);
	}
	
	private void cubeBottomTop(RegistryObject<? extends Block> block) {
		String name = this.getName(block);
		this.simpleBlock(block.get(), this.models().cubeBottomTop(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_bottom"), this.modLoc("block/" + name + "_top")));
		this.itemModel(block);
	}
	
	private void simpleCross(RegistryObject<? extends Block> block) {
		this.simpleBlock(block.get(), this.models().cross(this.getName(block), this.blockTexture(block.get())).renderType("cutout"));
	}
	
	private void signs(Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> signs, Supplier<? extends Block> textureBlock) {
		this.signBlock(signs.getFirst().get(), signs.getSecond().get(), this.blockTexture(textureBlock.get()));
	}

	private void beehive(RegistryObject<? extends Block> beehive) {
		String name = this.getName(beehive);
		ModelFile model = this.models().orientable(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front"), this.modLoc("block/" + name + "_end"));
		ModelFile model_honey = this.models().orientable(name + "_honey", this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front_honey"), this.modLoc("block/" + name + "_end"));

		this.horizontalBlock(beehive.get(), s -> s.getValue(BeehiveBlock.HONEY_LEVEL) == 5 ? model_honey : model);
		this.itemModel(beehive);
	}

	private void cabinet(RegistryObject<? extends Block> cabinet) {
		String name = this.getName(cabinet);
		ModelFile model = this.models().orientable(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front"), this.modLoc("block/" + name + "_end"));
		ModelFile model_open = this.models().orientable(name + "_open", this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front_open"), this.modLoc("block/" + name + "_end"));

		this.horizontalBlock(cabinet.get(), s -> s.getValue(BlockStateProperties.OPEN) ? model_open : model);
		this.itemModel(cabinet);
	}
	
	private void log(RegistryObject<? extends Block> log) {
		this.axisBlock((RotatedPillarBlock)log.get(), this.blockTexture(log.get()), this.modLoc("block/" + this.getName(log) + "_top"));
		this.itemModel(log);
	}

	private void wood(RegistryObject<? extends Block> log, ResourceLocation texture) {
		this.axisBlock((RotatedPillarBlock)log.get(), texture, texture);
		this.itemModel(log);
	}
	
	private void bookshelf(RegistryObject<? extends Block> bookshelf, Supplier<? extends Block> planks) {
		this.simpleBlock(bookshelf.get(), this.models().cubeColumn(this.getName(bookshelf), this.blockTexture(bookshelf.get()), this.blockTexture(planks.get())));
		this.itemModel(bookshelf);
	}
	
	// Blueprint Models //
	
	private void hedge(RegistryObject<? extends Block> hedge, Supplier<? extends Block> leaves, Supplier<? extends Block> log) {
		ModelFile post = this.models().withExistingParent(this.getName(hedge) + "_post", "blueprint:block/hedge_post").texture("leaf", this.blockTexture(leaves.get())).texture("log", this.blockTexture(log.get())).renderType("cutout");
		ModelFile side = this.models().withExistingParent(this.getName(hedge) + "_side", "blueprint:block/hedge_side").texture("leaf", this.blockTexture(leaves.get())).renderType("cutout");
		ModelFile extend = this.models().withExistingParent(this.getName(hedge) + "_extend", "blueprint:block/hedge_extend").texture("leaf", this.blockTexture(leaves.get())).renderType("cutout");

		this.itemModels().withExistingParent(this.getName(hedge), this.modLoc("block/" + this.getName(hedge) + "_post"));
		this.getMultipartBuilder(hedge.get())
			.part().modelFile(post).addModel().condition(BooleanProperty.create("extend"), false).end()
			.part().modelFile(extend).addModel().condition(BooleanProperty.create("extend"), true).end()
			.part().modelFile(side).uvLock(true).addModel().condition(CrossCollisionBlock.NORTH, true).end()
			.part().modelFile(side).uvLock(true).rotationY(90).addModel().condition(CrossCollisionBlock.EAST, true).end()
			.part().modelFile(side).uvLock(true).rotationY(180).addModel().condition(CrossCollisionBlock.SOUTH, true).end()
			.part().modelFile(side).uvLock(true).rotationY(270).addModel().condition(CrossCollisionBlock.WEST, true).end();

	}
	
	private void chests(RegistryObject<BlueprintChestBlock> chest, RegistryObject<BlueprintTrappedChestBlock> trapped, Supplier<? extends Block> textureBlock) {
		ModelFile model =  this.models().getBuilder(this.getName(chest)).texture("particle", this.blockTexture(textureBlock.get()));
		this.simpleBlock(chest.get(), model);
		this.simpleBlock(trapped.get(), model);
		this.itemModels().withExistingParent(this.getName(chest), "blueprint:item/template_chest");
		this.itemModels().withExistingParent(this.getName(trapped), "blueprint:item/template_chest");
	}
	
	private void leafCarpet(RegistryObject<? extends Block> carpet, Supplier<? extends Block> textureBlock) {
		this.simpleBlock(carpet.get(), this.models().withExistingParent(this.getName(carpet), "blueprint:block/leaf_carpet").texture("all", this.blockTexture(textureBlock.get())).renderType("cutout"));
		this.itemModel(carpet);
	}
	
	private void post(RegistryObject<? extends Block> post, Supplier<? extends Block> textureBlock) {
		ModelFile model = this.models().withExistingParent(this.getName(post), "blueprint:block/post").texture("texture", this.blockTexture(textureBlock.get()));
		ModelFile chain = this.models().getExistingFile(Blueprint.REGISTRY_HELPER.prefix("block/chain_small"));
		ModelFile chainTop = this.models().getExistingFile(Blueprint.REGISTRY_HELPER.prefix("block/chain_small_top"));

		this.itemModel(post);
		this.getMultipartBuilder(post.get())
			.part().modelFile(model).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.Y).end()
			.part().modelFile(model).rotationX(90).rotationY(90).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.X).end()
			.part().modelFile(model).rotationX(90).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.Z).end()
			.part().modelFile(chain).addModel().condition(BooleanProperty.create("chain_down"), true).end()
			.part().modelFile(chainTop).addModel().condition(BooleanProperty.create("chain_up"), true).end()
			.part().modelFile(chainTop).rotationX(90).addModel().condition(BooleanProperty.create("chain_north"), true).end()
			.part().modelFile(chain).rotationX(90).addModel().condition(BooleanProperty.create("chain_south"), true).end()
			.part().modelFile(chainTop).rotationX(90).rotationY(90).addModel().condition(BooleanProperty.create("chain_east"), true).end()
			.part().modelFile(chain).rotationX(90).rotationY(90).addModel().condition(BooleanProperty.create("chain_west"), true).end();
	}
	
	private void leafPile(RegistryObject<? extends Block> leafPile, Supplier<? extends Block> textureBlock, boolean tinted) {
		ModelFile model = this.models().withExistingParent(this.getName(leafPile), "blueprint:block/" + (tinted ? "tinted_" : "") + "leaf_pile").texture("all", this.blockTexture(textureBlock.get())).renderType("cutout");

		this.itemModels().withExistingParent(this.getName(leafPile), "item/generated").texture("layer0", this.modLoc("block/" + this.getName(textureBlock)));
		this.getMultipartBuilder(leafPile.get())
			.part().modelFile(model).uvLock(true).rotationX(270).addModel().condition(BlockStateProperties.UP, true).end()
			.part().modelFile(model).uvLock(true).rotationX(90).addModel().condition(BlockStateProperties.DOWN, true).end()
			.part().modelFile(model).addModel().condition(BlockStateProperties.NORTH, true).end()
			.part().modelFile(model).uvLock(true).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, true).end()
			.part().modelFile(model).uvLock(true).rotationY(90).addModel().condition(BlockStateProperties.EAST, true).end()
			.part().modelFile(model).uvLock(true).rotationY(270).addModel().condition(BlockStateProperties.WEST, true).end();
	}
	
	private void verticalSlab(RegistryObject<? extends Block> slab, Supplier<? extends Block> textureBlock) {
		ResourceLocation texture = this.blockTexture(textureBlock.get());
		ModelFile model = this.models().withExistingParent(this.getName(slab), "blueprint:block/vertical_slab").texture("top", texture).texture("bottom", texture).texture("side", texture);
		
		this.itemModel(slab);
        this.getVariantBuilder(slab.get())
        	.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.NORTH).addModels(new ConfiguredModel(model, 0, 0, true))
        	.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.SOUTH).addModels(new ConfiguredModel(model, 0, 180, true))
        	.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.EAST).addModels(new ConfiguredModel(model, 0, 90, true))
        	.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.WEST).addModels(new ConfiguredModel(model, 0, 270, true))
        	.partialState().with(VerticalSlabBlock.TYPE, VerticalSlabType.DOUBLE).addModels(new ConfiguredModel(this.models().getExistingFile(texture)));
	}

	private void verticalPlanks(RegistryObject<? extends Block> planks, Supplier<? extends Block> textureBlock) {
		ResourceLocation texture = this.blockTexture(textureBlock.get());
		ModelFile model = this.models().withExistingParent(this.getName(planks), "blueprint:block/vertical_planks").texture("all", texture);

		this.itemModel(planks);
		this.simpleBlock(planks.get(), model);
	}

	private void boards(RegistryObject<? extends Block> boards) {
		ResourceLocation texture = this.blockTexture(boards.get());
		ModelFile boardsModel = models().getBuilder(this.getName(boards)).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards"))).texture("all", texture);
		ModelFile boardsHorizontalModel = models().getBuilder(this.getName(boards) + "_horizontal").parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards_horizontal"))).texture("all", texture);

		this.itemModel(boards);
		this.getVariantBuilder(boards.get())
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState().modelFile(boardsModel).addModel()
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState().modelFile(boardsHorizontalModel).addModel()
				.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState().modelFile(boardsHorizontalModel).rotationY(270).addModel();
	}

	// Misc //
			
	private void itemModel(RegistryObject<? extends Block> block) {
		this.itemModels().withExistingParent(this.getName(block), this.blockTexture(block.get()));
	}
	
	private void generatedItem(RegistryObject<? extends ItemLike> item) {
		this.itemModels().withExistingParent(this.getName(item), "item/generated").texture("layer0", this.modLoc("block/" + this.getName(item)));
	}

	private String getName(Supplier<? extends ItemLike> object) {
		return ForgeRegistries.ITEMS.getKey(object.get().asItem()).getPath();
	}

}

package com.rosemods.windswept.core.data.client;

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
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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
		this.cubeAll(WindsweptBlocks.STRIPPED_HOLLY_WOOD, this.blockTexture(WindsweptBlocks.STRIPPED_HOLLY_LOG.get()));
		this.log(WindsweptBlocks.HOLLY_LOG);
		this.cubeAll(WindsweptBlocks.HOLLY_WOOD, this.blockTexture(WindsweptBlocks.HOLLY_LOG.get()));
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
		//this.beehive(WindsweptBlocks.HOLLY_BEEHIVE);
		this.hedge(WindsweptBlocks.HOLLY_HEDGE, WindsweptBlocks.HOLLY_LEAVES, WindsweptBlocks.HOLLY_LOG);
		this.leafCarpet(WindsweptBlocks.HOLLY_LEAF_CARPET, WindsweptBlocks.HOLLY_LEAVES);
		//this.leafPile(WindsweptBlocks.HOLLY_LEAF_PILE, WindsweptBlocks.HOLLY_LEAVES);
		this.ladder(WindsweptBlocks.HOLLY_LADDER);
		this.cubeAll(WindsweptBlocks.VERTICAL_HOLLY_PLANKS);
		this.bookshelf(WindsweptBlocks.HOLLY_BOOKSHELF, WindsweptBlocks.HOLLY_PLANKS);
		this.verticalSlab(WindsweptBlocks.HOLLY_VERTICAL_SLAB, WindsweptBlocks.HOLLY_PLANKS);
		this.post(WindsweptBlocks.HOLLY_POST, WindsweptBlocks.HOLLY_LOG);
		this.post(WindsweptBlocks.STRIPPED_HOLLY_POST, WindsweptBlocks.STRIPPED_HOLLY_LOG);
		this.chests(WindsweptBlocks.HOLLY_CHESTS, WindsweptBlocks.HOLLY_PLANKS);
		this.cubeBomttomTop(WindsweptBlocks.HOLLY_BERRY_CRATE);
		
		this.log(WindsweptBlocks.STRIPPED_CHESTNUT_LOG);
		this.cubeAll(WindsweptBlocks.STRIPPED_CHESTNUT_WOOD, this.blockTexture(WindsweptBlocks.STRIPPED_CHESTNUT_LOG.get()));
		this.log(WindsweptBlocks.CHESTNUT_LOG);
		this.cubeAll(WindsweptBlocks.CHESTNUT_WOOD, this.blockTexture(WindsweptBlocks.CHESTNUT_LOG.get()));
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
		//this.beehive(WindsweptBlocks.CHESTNUT_BEEHIVE);
		this.hedge(WindsweptBlocks.CHESTNUT_HEDGE, WindsweptBlocks.CHESTNUT_LEAVES, WindsweptBlocks.CHESTNUT_LOG);
		this.leafCarpet(WindsweptBlocks.CHESTNUT_LEAF_CARPET, WindsweptBlocks.CHESTNUT_LEAVES);
		//this.leafPile(WindsweptBlocks.CHESTNUT_LEAF_PILE, WindsweptBlocks.CHESTNUT_LEAVES);
		this.ladder(WindsweptBlocks.CHESTNUT_LADDER);
		this.cubeAll(WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS);
		this.bookshelf(WindsweptBlocks.CHESTNUT_BOOKSHELF, WindsweptBlocks.CHESTNUT_PLANKS);
		this.verticalSlab(WindsweptBlocks.CHESTNUT_VERTICAL_SLAB, WindsweptBlocks.CHESTNUT_PLANKS);
		this.post(WindsweptBlocks.CHESTNUT_POST, WindsweptBlocks.CHESTNUT_LOG);
		this.post(WindsweptBlocks.STRIPPED_CHESTNUT_POST, WindsweptBlocks.STRIPPED_CHESTNUT_LOG);
		this.chests(WindsweptBlocks.CHESTNUT_CHESTS, WindsweptBlocks.CHESTNUT_PLANKS);

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
		
		this.door(WindsweptBlocks.GOLDEN_DOOR);
		this.trapdoor(WindsweptBlocks.GOLDEN_TRAPDOOR);
		
		this.simpleCross(WindsweptBlocks.SNOWY_SPROUTS);

		this.pottedPlant(WindsweptBlocks.RED_ROSE, WindsweptBlocks.POTTED_RED_ROSE);
		this.pottedPlant(WindsweptBlocks.PINK_ROSE, WindsweptBlocks.POTTED_PINK_ROSE);
		this.pottedPlant(WindsweptBlocks.BLUE_ROSE, WindsweptBlocks.POTTED_BLUE_ROSE);
		this.pottedPlant(WindsweptBlocks.WHITE_ROSE, WindsweptBlocks.POTTED_WHITE_ROSE);
		this.pottedPlant(WindsweptBlocks.YELLOW_ROSE, WindsweptBlocks.POTTED_YELLOW_ROSE);
		this.pottedPlant(WindsweptBlocks.FOXGLOVE, WindsweptBlocks.POTTED_FOXGLOVE);
		this.pottedPlant(WindsweptBlocks.BLUEBELLS, WindsweptBlocks.POTTED_BLUEBELLS, true);
		this.pottedPlant(WindsweptBlocks.NIGHTSHADE, WindsweptBlocks.POTTED_NIGHTSHADE);
		
		this.wildBerryBush(WindsweptBlocks.WILD_BERRY_BUSH);
		this.cubeBomttomTop(WindsweptBlocks.WILD_BERRY_SACK);
		this.simpleCross(WindsweptBlocks.WILD_BERRY_BUSH_PIPS);
		
		this.paneBlock(WindsweptBlocks.ICE_SHEET, () -> Blocks.ICE);
	}

	// Blocks // i should really make some kind of api or something this is tedious
	
	private void wildBerryBush(RegistryObject<Block> bush) {
		String name = ForgeRegistries.BLOCKS.getKey(bush.get()).getPath();
		final Function<Integer, ModelFile> model = i -> this.models().cross(name + "_stage" + i, this.modLoc("block/" + name + "_stage" + i));
		this.getVariantBuilder(bush.get())
			.partialState().with(WildBerryBushBlock.AGE, 0).addModels(new ConfiguredModel(model.apply(0), 0, 0, true))
	     	.partialState().with(WildBerryBushBlock.AGE, 1).addModels(new ConfiguredModel(model.apply(1), 0, 180, true))
	     	.partialState().with(WildBerryBushBlock.AGE, 2).addModels(new ConfiguredModel(model.apply(2), 0, 90, true));
	}
	
	private void pottedPlant(RegistryObject<Block> plant, RegistryObject<Block> pot, boolean pottedTexture) {
		this.simpleCross(plant);
		this.generatedItem(plant);
		this.simpleBlock(pot.get(), this.models().withExistingParent(this.getName(pot), "block/flower_pot_cross")
				.texture("plant", pottedTexture ? this.modLoc("block/potted_" + this.getName(plant)) : this.blockTexture(plant.get())));
	}
	
	private void pottedPlant(RegistryObject<Block> plant, RegistryObject<Block> pot) {
		this.pottedPlant(plant, pot, false);
	}
	
	private void paneBlock(RegistryObject<Block> block, Supplier<Block> textureBlock) {
		ResourceLocation texture = this.blockTexture(textureBlock.get());
		this.paneBlock((IronBarsBlock) block.get(), texture, texture);
		this.itemModels().withExistingParent(this.getName(block), "item/generated").texture("layer0", texture);
	}
	
	private void trapdoor(RegistryObject<? extends Block> trapdoor) {		
		this.trapdoorBlock((TrapDoorBlock) trapdoor.get(), this.blockTexture(trapdoor.get()), true);
		this.itemModels().withExistingParent(this.getName(trapdoor), this.modLoc("block/" + this.getName(trapdoor) + "_bottom"));
	}
	
	private void door(RegistryObject<? extends Block> door) {
		String name = "block/" + this.getName(door);
		this.doorBlock((DoorBlock) door.get(), this.modLoc(name + "_bottom"), this.modLoc(name + "_top"));
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
		this.horizontalBlock(ladder.get(),
			this.models().getBuilder(this.getName(ladder))
				.texture("particle", this.blockTexture(ladder.get()))
				.texture("texture", this.blockTexture(ladder.get()))
				.ao(false)
				.element()
					.from(0f, 0f, 15.2f)
					.to(16f, 16f, 15.2f)
					.shade(false)
					.face(Direction.NORTH)
						.texture("#texture")
						.uvs(0f, 0f, 16f, 16f)
						.end()
					.face(Direction.SOUTH)
						.texture("#texture")
						.uvs(16f, 0f, 0f, 16f)							
						.end()
					.end()
		);
		
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
		this.simpleBlock(leaves.get(), this.models().withExistingParent(this.getName(leaves), "block/leaves").texture("all", this.blockTexture(leaves.get())));
		this.itemModel(leaves);
	}
	
	private void cubeBomttomTop(RegistryObject<? extends Block> block) {
		String name = this.getName(block);
		this.simpleBlock(block.get(), this.models().cubeBottomTop(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_bottom"), this.modLoc("block/" + name + "_top")));
		this.itemModel(block);
	}
	
	private void simpleCross(RegistryObject<? extends Block> block) {
		this.simpleBlock(block.get(), this.models().cross(this.getName(block), this.blockTexture(block.get())));
	}
	
	private void signs(Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> signs, Supplier<? extends Block> textureBlock) {
		this.signBlock((StandingSignBlock) signs.getFirst().get(), (WallSignBlock) signs.getSecond().get(), this.blockTexture(textureBlock.get()));
	}

	@SuppressWarnings("unused")
	private void beehive(RegistryObject<? extends Block> beehive) {
		String name = this.getName(beehive);
		ModelFile model = this.models().orientable(name, this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front"), this.modLoc("block/" + name + "_end"));
		ModelFile model_honey = this.models().orientable(name + "_honey", this.modLoc("block/" + name + "_side"), this.modLoc("block/" + name + "_front_honey"), this.modLoc("block/" + name + "_end"));
		this.horizontalBlock(beehive.get(), s -> s.getValue(BeehiveBlock.HONEY_LEVEL) == 5 ? model_honey : model);
		this.itemModel(beehive);
	}
	
	private void log(RegistryObject<? extends Block> log) {
		this.axisBlock((RotatedPillarBlock)log.get(), this.blockTexture(log.get()), this.modLoc("block/" + this.getName(log) + "_top"));
		this.itemModel(log);
	}
	
	private void bookshelf(RegistryObject<? extends Block> bookshelf, Supplier<? extends Block> planks) {
		this.simpleBlock(bookshelf.get(), this.models().cubeColumn(this.getName(bookshelf), this.blockTexture(bookshelf.get()), this.blockTexture(planks.get())));
		this.itemModel(bookshelf);
	}
	
	// Blueprint Models //
	
	private void hedge(RegistryObject<? extends Block> hedge, Supplier<? extends Block> leaves, Supplier<? extends Block> log) {
		ModelFile post = this.models().withExistingParent(this.getName(hedge) + "_post", "blueprint:block/hedge_post").texture("leaf", this.blockTexture(leaves.get())).texture("log", this.blockTexture(log.get()));
		ModelFile side = this.models().withExistingParent(this.getName(hedge) + "_side", "blueprint:block/hedge_side").texture("leaf", this.blockTexture(leaves.get()));
		ModelFile extend = this.models().withExistingParent(this.getName(hedge) + "_extend", "blueprint:block/hedge_extend").texture("leaf", this.blockTexture(leaves.get()));

		this.itemModels().withExistingParent(this.getName(hedge), this.modLoc("block/" + this.getName(hedge) + "_post"));
		this.getMultipartBuilder(hedge.get())
			.part().modelFile(post).addModel().condition(BooleanProperty.create("extend"), false).end()
			.part().modelFile(extend).addModel().condition(BooleanProperty.create("extend"), true).end()
			.part().modelFile(side).uvLock(true).addModel().condition(CrossCollisionBlock.NORTH, true).end()
			.part().modelFile(side).uvLock(true).rotationY(90).addModel().condition(CrossCollisionBlock.EAST, true).end()
			.part().modelFile(side).uvLock(true).rotationY(180).addModel().condition(CrossCollisionBlock.SOUTH, true).end()
			.part().modelFile(side).uvLock(true).rotationY(270).addModel().condition(CrossCollisionBlock.WEST, true).end();

	}
	
	private void chests(Pair<RegistryObject<BlueprintChestBlock>, RegistryObject<BlueprintTrappedChestBlock>> chests, Supplier<? extends Block> textureBlock) {
		ModelFile model =  this.models().getBuilder(this.getName(chests.getFirst())).texture("particle", this.blockTexture(textureBlock.get()));
		this.simpleBlock(chests.getFirst().get(), model);
		this.simpleBlock(chests.getSecond().get(), model);
		this.itemModels().withExistingParent(this.getName(chests.getFirst()), "blueprint:item/template_chest");
		this.itemModels().withExistingParent(this.getName(chests.getSecond()), "blueprint:item/template_chest");
	}
	
	private void leafCarpet(RegistryObject<? extends Block> carpet, Supplier<? extends Block> textureBlock) {
		this.simpleBlock(carpet.get(), this.models().withExistingParent(this.getName(carpet), "blueprint:block/leaf_carpet").texture("all", this.blockTexture(textureBlock.get())));
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
	
	@SuppressWarnings("unused")
	private void leafPile(RegistryObject<? extends Block> leafPile, Supplier<? extends Block> textureBlock) {
		ModelFile model = this.models().withExistingParent(this.getName(leafPile), "blueprint:block/leaf_pile").texture("all", this.blockTexture(textureBlock.get()));
		this.itemModel(leafPile);
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

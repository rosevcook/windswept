package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.util.DataUtil;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptBlockInfo {
	
	public static void registerCompostables() {
		DataUtil.registerCompostable(WindsweptItems.WILD_BERRIES.get(), .3f);
		DataUtil.registerCompostable(WindsweptItems.WILD_BERRY_PIPS.get(), .3f);
		DataUtil.registerCompostable(WindsweptItems.HOLLY_BERRIES.get(), .3f);

		DataUtil.registerCompostable(WindsweptBlocks.RED_ROSE.get(), .65f);
		DataUtil.registerCompostable(WindsweptBlocks.PINK_ROSE.get(), .65f);
		DataUtil.registerCompostable(WindsweptBlocks.BLUE_ROSE.get(), .65f);
		DataUtil.registerCompostable(WindsweptBlocks.WHITE_ROSE.get(), .65f);
		DataUtil.registerCompostable(WindsweptBlocks.YELLOW_ROSE.get(), .65f);
		DataUtil.registerCompostable(WindsweptBlocks.FOXGLOVE.get(), .65f);
		DataUtil.registerCompostable(WindsweptBlocks.NIGHTSHADE.get(), .65f);
		DataUtil.registerCompostable(WindsweptBlocks.BLUEBELLS.get(), .65f);

		DataUtil.registerCompostable(WindsweptBlocks.HOLLY_LEAVES.get(), .3f);
		DataUtil.registerCompostable(WindsweptBlocks.CHESTNUT_LEAVES.get(), .3f);

		DataUtil.registerCompostable(WindsweptBlocks.HOLLY_SAPLING.get(), .3f);
		DataUtil.registerCompostable(WindsweptBlocks.CHESTNUT_SAPLING.get(), .3f);

		DataUtil.registerCompostable(WindsweptBlocks.HOLLY_LEAF_CARPET.get(), .3f);
		DataUtil.registerCompostable(WindsweptBlocks.CHESTNUT_LEAF_CARPET.get(), .3f);

		DataUtil.registerCompostable(WindsweptBlocks.SNOWY_SPROUTS.get(), .5f);
		
		DataUtil.registerCompostable(WindsweptBlocks.WILD_BERRY_SACK.get(), 1f);

	}
	
	public static void registerFlammables() {
		DataUtil.registerFlammable(WindsweptBlocks.STRIPPED_HOLLY_LOG.get(), 5, 5);
		DataUtil.registerFlammable(WindsweptBlocks.STRIPPED_HOLLY_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_LOG.get(), 5, 5);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.VERTICAL_HOLLY_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_FENCE_GATE.get(), 5, 20);
		
		DataUtil.registerFlammable(WindsweptBlocks.STRIPPED_CHESTNUT_LOG.get(), 5, 5);
		DataUtil.registerFlammable(WindsweptBlocks.STRIPPED_CHESTNUT_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_LOG.get(), 5, 5);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.VERTICAL_CHESTNUT_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_FENCE_GATE.get(), 5, 20);
		
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_BERRY_CRATE.get(), 5, 20);
		//DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_CRATE.get(), 5, 20);
		//DataUtil.registerFlammable(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get(), 5, 20);

		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_LEAVES.get(), 30, 60);

		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_BOOKSHELF.get(), 30, 20);
		//DataUtil.registerFlammable(WindsweptBlocks.HOLLY_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_POST.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.STRIPPED_HOLLY_POST.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_HEDGE.get(), 5, 20);
		
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_BOOKSHELF.get(), 30, 20);
		//DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_POST.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.STRIPPED_CHESTNUT_POST.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_HEDGE.get(), 5, 20);
		
		DataUtil.registerFlammable(WindsweptBlocks.WILD_BERRY_BUSH_PIPS.get(), 60, 100);
		DataUtil.registerFlammable(WindsweptBlocks.WILD_BERRY_BUSH.get(), 60, 100);
		
		DataUtil.registerFlammable(WindsweptBlocks.SNOWY_SPROUTS.get(), 60, 100);

		DataUtil.registerFlammable(WindsweptBlocks.RED_ROSE.get(), 60, 100);
		DataUtil.registerFlammable(WindsweptBlocks.PINK_ROSE.get(), 60, 100);
		DataUtil.registerFlammable(WindsweptBlocks.BLUE_ROSE.get(), 60, 100);
		DataUtil.registerFlammable(WindsweptBlocks.WHITE_ROSE.get(), 60, 100);
		DataUtil.registerFlammable(WindsweptBlocks.YELLOW_ROSE.get(), 60, 100);
		DataUtil.registerFlammable(WindsweptBlocks.FOXGLOVE.get(), 60, 100);
		DataUtil.registerFlammable(WindsweptBlocks.NIGHTSHADE.get(), 60, 100);
		DataUtil.registerFlammable(WindsweptBlocks.BLUEBELLS.get(), 60, 100);

	}
	
	@OnlyIn(Dist.CLIENT)
	public static void setupRenderLayers() {
		setRenderLayer(WindsweptBlocks.WILD_BERRY_BUSH, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.WILD_BERRY_BUSH_PIPS, RenderType.cutout());
		
		setRenderLayer(WindsweptBlocks.RED_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.PINK_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.BLUE_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.WHITE_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.YELLOW_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.FOXGLOVE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.NIGHTSHADE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.BLUEBELLS, RenderType.cutout());
		
		setRenderLayer(WindsweptBlocks.POTTED_RED_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_PINK_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_BLUE_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_WHITE_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_YELLOW_ROSE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_FOXGLOVE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_NIGHTSHADE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_BLUEBELLS, RenderType.cutout());
		
		setRenderLayer(WindsweptBlocks.CHESTNUT_LEAVES, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.CHESTNUT_SAPLING, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_CHESTNUT_SAPLING, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.CHESTNUT_LADDER, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.CHESTNUT_HEDGE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.CHESTNUT_LEAF_CARPET, RenderType.cutout());
		//setRenderLayer(WindsweptBlocks.CHESTNUT_LEAF_PILE, RenderType.cutout());

		setRenderLayer(WindsweptBlocks.HOLLY_LEAVES, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.HOLLY_SAPLING, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.POTTED_HOLLY_SAPLING, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.HOLLY_LADDER, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.HOLLY_HEDGE, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.HOLLY_LEAF_CARPET, RenderType.cutout());
		//setRenderLayer(WindsweptBlocks.HOLLY_LEAF_PILE, RenderType.cutout());

		setRenderLayer(WindsweptBlocks.GOLDEN_DOOR, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.GOLDEN_TRAPDOOR, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.HOLLY_DOOR, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.HOLLY_TRAPDOOR, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.CHESTNUT_DOOR, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.CHESTNUT_TRAPDOOR, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.CHESTNUT_POST, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.STRIPPED_CHESTNUT_POST, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.HOLLY_POST, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.STRIPPED_HOLLY_POST, RenderType.cutout());
		
		setRenderLayer(WindsweptBlocks.SNOWY_SPROUTS, RenderType.cutout());
		setRenderLayer(WindsweptBlocks.ICE_SHEET, RenderType.translucent());
	}
	
	private static void setRenderLayer(RegistryObject<? extends Block> block, RenderType type) {
		ItemBlockRenderTypes.setRenderLayer(block.get(), type::equals);
	}

}

package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.util.DataUtil;

public final class WindsweptBlockInfo {
	
	public static void registerCompostables() {
		DataUtil.registerCompostable(WindsweptItems.WILD_BERRIES.get(), .3f);
		DataUtil.registerCompostable(WindsweptItems.WILD_BERRY_PIPS.get(), .3f);
		DataUtil.registerCompostable(WindsweptItems.HOLLY_BERRIES.get(), .3f);
		DataUtil.registerCompostable(WindsweptItems.CHESTNUTS.get(), .3f);

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
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_BOARDS.get(), 5, 20);

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
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_BOARDS.get(), 5, 20);

		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_BERRY_CRATE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_CRATE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.ROASTED_CHESTNUT_CRATE.get(), 5, 20);

		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_LEAVES.get(), 30, 60);

		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_POST.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.STRIPPED_HOLLY_POST.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.HOLLY_LEAF_PILE.get(), 30, 60);

		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_LEAF_CARPET.get(), 30, 60);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_POST.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.STRIPPED_CHESTNUT_POST.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_HEDGE.get(), 5, 20);
		DataUtil.registerFlammable(WindsweptBlocks.CHESTNUT_LEAF_PILE.get(), 30, 60);

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

}

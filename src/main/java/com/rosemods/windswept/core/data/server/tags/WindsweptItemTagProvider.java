package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptItemTagProvider extends ItemTagsProvider {

    public WindsweptItemTagProvider(GatherDataEvent event, WindsweptBlockTagProvider blockTags) {
        super(event.getGenerator(), blockTags, Windswept.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        //windswept
        this.tag(WindsweptItemTags.ROSES).add(RED_ROSE.get().asItem(), PINK_ROSE.get().asItem(), BLUE_ROSE.get().asItem(), WHITE_ROSE.get().asItem(), YELLOW_ROSE.get().asItem(), Items.WITHER_ROSE, Items.ROSE_BUSH, RED_ROSE_BUSH.get().asItem(), PINK_ROSE_BUSH.get().asItem(), BLUE_ROSE_BUSH.get().asItem(), WHITE_ROSE_BUSH.get().asItem(), YELLOW_ROSE_BUSH.get().asItem(), WITHER_ROSE_BUSH.get().asItem());
        this.copy(WindsweptBlockTags.HOLLY_LOGS, WindsweptItemTags.HOLLY_LOGS);
        this.copy(WindsweptBlockTags.CHESTNUT_LOGS, WindsweptItemTags.CHESTNUT_LOGS);

        //quark
        this.tag(BlueprintItemTags.BOATABLE_CHESTS).add(HOLLY_CHEST.get().asItem(), CHESTNUT_CHEST.get().asItem());
        this.tag(BlueprintItemTags.REVERTABLE_CHESTS).add(HOLLY_CHEST.get().asItem(), CHESTNUT_CHEST.get().asItem());
        this.copy(BlueprintBlockTags.LADDERS, BlueprintItemTags.LADDERS);
        this.copy(BlueprintBlockTags.VERTICAL_SLABS, BlueprintItemTags.VERTICAL_SLABS);
        this.copy(BlueprintBlockTags.WOODEN_VERTICAL_SLABS, BlueprintItemTags.WOODEN_VERTICAL_SLABS);

        //farmers delight
        this.tag(WindsweptItemTags.CABBAGE_ROLL_INGREDIENTS).addTag(WindsweptItemTags.RAW_GOAT);
        this.tag(WindsweptItemTags.WOODEN_CABINETS).add(HOLLY_CABINET.get().asItem(), CHESTNUT_CABINET.get().asItem());

        //forge
        this.tag(WindsweptItemTags.STRIPPED_LOGS).add(STRIPPED_HOLLY_LOG.get().asItem(), STRIPPED_CHESTNUT_LOG.get().asItem());
        this.tag(WindsweptItemTags.STRIPPED_WOOD).add(STRIPPED_HOLLY_WOOD.get().asItem(), STRIPPED_CHESTNUT_WOOD.get().asItem());
        this.tag(BlueprintItemTags.BUCKETS_EMPTY).add(WOODEN_BUCKET.get());
        this.tag(BlueprintItemTags.BUCKETS_MILK).add(WOODEN_MILK_BUCKET.get());
        this.tag(BlueprintItemTags.BUCKETS_POWDER_SNOW).add(WOODEN_POWDER_SNOW_BUCKET.get());
        this.tag(BlueprintItemTags.BUCKETS_WATER).add(WOODEN_WATER_BUCKET.get());
        this.tag(WindsweptItemTags.MILK).addTag(BlueprintItemTags.BUCKETS_MILK);
        this.tag(WindsweptItemTags.WILD_BERRY_SEEDS).add(WILD_BERRY_PIPS.get());
        this.tag(WindsweptItemTags.RAW_GOAT).add(GOAT.get(), GOAT_SHANKS.get());
        this.tag(WindsweptItemTags.COOKED_GOAT).add(COOKED_GOAT.get(), COOKED_GOAT_SHANKS.get());
        this.tag(WindsweptItemTags.BERRIES).add(WILD_BERRIES.get());
        this.tag(WindsweptItemTags.COOKED_MUTTON).add(Items.COOKED_MUTTON);
        this.tag(Tags.Items.BOOKSHELVES).add(HOLLY_BOOKSHELF.get().asItem(), CHESTNUT_BOOKSHELF.get().asItem());
        this.tag(Tags.Items.SEEDS).addTag(WindsweptItemTags.WILD_BERRY_SEEDS);
        this.tag(Tags.Items.ARMORS_BOOTS).add(SNOW_BOOTS.get());
        this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
        this.copy(Tags.Blocks.CHESTS_TRAPPED, Tags.Items.CHESTS_TRAPPED);

        //boatload
        this.tag(BlueprintItemTags.FURNACE_BOATS).add(HOLLY_FURNACE_BOAT.get(), CHESTNUT_FURNACE_BOAT.get());
        this.tag(BlueprintItemTags.LARGE_BOATS).add(LARGE_HOLLY_BOAT.get(), LARGE_CHESTNUT_BOAT.get());

        //vanilla
        this.tag(ItemTags.BOATS).add(HOLLY_BOATS.getFirst().get(), CHESTNUT_BOATS.getFirst().get());
        this.tag(ItemTags.CHEST_BOATS).add(HOLLY_BOATS.getSecond().get(), CHESTNUT_BOATS.getSecond().get());
        this.tag(ItemTags.SIGNS).add(HOLLY_SIGNS.getFirst().get().asItem(), CHESTNUT_SIGNS.getFirst().get().asItem());
        this.tag(ItemTags.MUSIC_DISCS).add(MUSIC_DISC_RAIN.get(), MUSIC_DISC_SNOW.get(), MUSIC_DISC_BUMBLEBEE.get());
        this.copy(WindsweptBlockTags.HOLLY_LEAVES, ItemTags.LEAVES);
        this.copy(WindsweptBlockTags.CHESTNUT_LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
    }

}

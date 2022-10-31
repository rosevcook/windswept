package com.rosemods.windswept.core.data.client;

import java.util.function.Supplier;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WindsweptItemModelProvider extends ItemModelProvider {

	public WindsweptItemModelProvider(GatherDataEvent event) {
		super(event.getGenerator(), Windswept.MODID, event.getExistingFileHelper());
	}

	@Override
	protected void registerModels() {
		this.generated(WindsweptBlocks.SNOWY_SPROUTS);
		this.generated(WindsweptBlocks.HOLLY_SIGNS.getFirst());
		this.generated(WindsweptBlocks.HOLLY_DOOR);
		this.generated(WindsweptBlocks.GOLDEN_DOOR);
		this.generated(WindsweptItems.HOLLY_BOAT.getFirst());
		this.generated(WindsweptItems.HOLLY_BOAT.getSecond());
		this.generated(WindsweptItems.HOLLY_BERRIES);
		this.generated(WindsweptBlocks.CHESTNUT_SIGNS.getFirst());
		this.generated(WindsweptBlocks.CHESTNUT_DOOR);
		this.generated(WindsweptItems.CHESTNUT_BOAT.getFirst());
		this.generated(WindsweptItems.CHESTNUT_BOAT.getSecond());
		this.generated(WindsweptItems.WOODEN_BUCKET);
		this.generated(WindsweptItems.WOODEN_MILK_BUCKET);
		this.generated(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET);
		this.generated(WindsweptItems.WOODEN_WATER_BUCKET);
		this.generated(WindsweptItems.WILD_BERRIES);
		this.generated(WindsweptItems.WILD_BERRY_JUICE);
		this.generated(WindsweptItems.WILD_BERRY_BOWL);
		this.generated(WindsweptItems.SWEET_BERRY_BOWL);
		this.generated(WindsweptItems.MUTTON_PIE);
		this.generated(WindsweptItems.GOAT);
		this.generated(WindsweptItems.COOKED_GOAT);
		this.generated(WindsweptItems.GOAT_STEW);
		this.generated(WindsweptItems.WILD_BERRY_PIPS);
		this.generatedWithOverlay(WindsweptItems.SNOW_BOOTS);
		this.generated(WindsweptItems.FROZEN_FLESH);
		this.generated(WindsweptItems.SNOW_GOLEM_BANNER_PATTERN);
		this.generated(WindsweptItems.SNOW_CHARGE_BANNER_PATTERN);
		this.generated(WindsweptItems.MUSIC_DISC_RAIN);
		this.generated(WindsweptItems.MUSIC_DISC_SNOW);
		this.spawnEgg(WindsweptItems.CHILLED_SPAWN_EGG);
		this.generated(WindsweptItems.HOLLY_FURNACE_BOAT);
		this.generated(WindsweptItems.LARGE_HOLLY_BOAT);
		this.generated(WindsweptItems.CHESTNUT_FURNACE_BOAT);
		this.generated(WindsweptItems.LARGE_CHESTNUT_BOAT);
	}
	
	private void generated(RegistryObject<? extends ItemLike> item) {
		this.withExistingParent(this.getName(item), "item/generated").texture("layer0", this.modLoc("item/" + this.getName(item)));
	}
	
	private void generatedWithOverlay(RegistryObject<? extends ItemLike> item) {
		String name = this.getName(item);
		this.withExistingParent(name, "item/generated").texture("layer0", this.modLoc("item/" + name)).texture("layer1", this.modLoc("item/" + name + "_overlay"));
	}
	
	private void spawnEgg(RegistryObject<? extends Item> egg) {
		this.withExistingParent(this.getName(egg), "item/template_spawn_egg");
	}
	
	private String getName(Supplier<? extends ItemLike> object) {
		return ForgeRegistries.ITEMS.getKey(object.get().asItem()).getPath();
	}
	
}

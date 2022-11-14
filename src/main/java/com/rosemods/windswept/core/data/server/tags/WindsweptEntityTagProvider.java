package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import com.rosemods.windswept.core.registry.WindsweptEntities;

import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptEntityTagProvider extends EntityTypeTagsProvider {
	
	public WindsweptEntityTagProvider(GatherDataEvent event) {
		super(event.getGenerator(), Windswept.MODID, event.getExistingFileHelper());
	}

	@Override
	protected void addTags() {
		this.tag(WindsweptEntityTypeTags.CONVERT_TO_CHILLED).add(EntityType.ZOMBIE, EntityType.HUSK, EntityType.DROWNED);
		this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(WindsweptEntities.CHILLED.get());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(WindsweptEntities.CHILLED.get()).addTag(WindsweptEntityTypeTags.CONVERT_TO_CHILLED);
	}
	
}


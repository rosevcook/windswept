package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintEntityTypeTags;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.rosemods.windswept.core.registry.WindsweptEntityTypes.*;

public class WindsweptEntityTagProvider extends EntityTypeTagsProvider {

    public WindsweptEntityTagProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        this.tag(WindsweptEntityTypeTags.CONVERT_TO_CHILLED).add(EntityType.ZOMBIE, EntityType.HUSK, EntityType.DROWNED);
        this.tag(WindsweptEntityTypeTags.HOLLY_IMMUNE).add(EntityType.BEE, EntityType.GOAT, EntityType.FOX, EntityType.HORSE, FROSTBITER.get()).addOptional(new ResourceLocation("environmental", "reindeer"));

        this.tag(BlueprintEntityTypeTags.MILKABLE).add(FROSTBITER.get());

        this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(CHILLED.get(), FROSTBITER.get(), EntityType.GOAT);
        this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(CHILLED.get(), FROSTBITER.get());
        this.tag(EntityTypeTags.ARROWS).add(FROST_ARROW.get());
    }

}


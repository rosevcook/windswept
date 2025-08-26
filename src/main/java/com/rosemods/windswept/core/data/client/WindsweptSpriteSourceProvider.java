package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.datapack.WindsweptTrimMaterials;
import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.clayworks.core.api.ClayworksTrims;
import net.minecraftforge.common.data.SpriteSourceProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptSpriteSourceProvider extends SpriteSourceProvider {

    public WindsweptSpriteSourceProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getExistingFileHelper(), Windswept.MOD_ID);
    }

    @Override
    protected void addSources() {
        this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS).addSource(BlueprintTrims.materialPatternPermutations(WindsweptTrimMaterials.ICICLES, WindsweptTrimMaterials.PINECONE));
        this.atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(BlueprintTrims.materialPermutationsForItemLayers(WindsweptTrimMaterials.ICICLES, WindsweptTrimMaterials.PINECONE));
        this.atlas(ClayworksTrims.DECORATED_POT_ATLAS).addSource(ClayworksTrims.materialPatternPermutations(WindsweptTrimMaterials.ICICLES, WindsweptTrimMaterials.PINECONE));
    }

}

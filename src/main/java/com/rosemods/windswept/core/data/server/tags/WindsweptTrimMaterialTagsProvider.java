package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.rosemods.windswept.core.registry.datapack.WindsweptTrimMaterials;
import com.teamabnormals.blueprint.core.other.tags.BlueprintTrimMaterialTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptTrimMaterialTagsProvider extends TagsProvider<TrimMaterial> {

    public WindsweptTrimMaterialTagsProvider(GatherDataEvent event, WindsweptDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), Registries.TRIM_MATERIAL, dataPack.getRegistryProvider(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlueprintTrimMaterialTags.GENERATES_OVERRIDES).add(WindsweptTrimMaterials.ICICLES, WindsweptTrimMaterials.PINECONE);
    }

}

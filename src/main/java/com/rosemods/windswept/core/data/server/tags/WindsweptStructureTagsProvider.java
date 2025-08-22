package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.rosemods.windswept.core.registry.datapack.WindsweptStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.tags.StructureTags;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptStructureTagsProvider extends StructureTagsProvider {
    public WindsweptStructureTagsProvider(GatherDataEvent event, WindsweptDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(StructureTags.VILLAGE).add(WindsweptStructures.VILLAGE_FROZEN);
    }

}

package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.rosemods.windswept.core.registry.WindsweptPaintingVariants.*;

public class WindsweptPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

    public WindsweptPaintingVariantTagsProvider(GatherDataEvent event, WindsweptDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(PaintingVariantTags.PLACEABLE).add(CLIFFSIDE.getKey(), TUNDRA.getKey(), DRESS_CODES.getKey());
    }

}

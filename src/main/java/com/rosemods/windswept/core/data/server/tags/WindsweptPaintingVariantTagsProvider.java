package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.rosemods.windswept.core.registry.WindsweptPaintingVariants.*;

public class WindsweptPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

    public WindsweptPaintingVariantTagsProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    public void addTags() {
        this.tag(PaintingVariantTags.PLACEABLE).add(CLIFFSIDE.get(), TUNDRA.get(), DRESS_CODES.get());
    }

}

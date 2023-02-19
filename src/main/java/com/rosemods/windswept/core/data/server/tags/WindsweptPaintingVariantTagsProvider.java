package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptPaintingVariants;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

    public WindsweptPaintingVariantTagsProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MODID, event.getExistingFileHelper());
    }

    @Override
    public void addTags() {
        this.tag(PaintingVariantTags.PLACEABLE).add(WindsweptPaintingVariants.CLIFFSIDE.get(), WindsweptPaintingVariants.BLOOM.get());
    }

}

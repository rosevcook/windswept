package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBannerPatternTags;
import com.rosemods.windswept.core.registry.WindsweptBannerPatterns;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptBannerPatternTagProvider extends BannerPatternTagsProvider {

    public WindsweptBannerPatternTagProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        this.tag(WindsweptBannerPatternTags.SNOW_CHARGE).add(WindsweptBannerPatterns.SNOW_CHARGE.get());
        this.tag(WindsweptBannerPatternTags.SNOW_GOLEM).add(WindsweptBannerPatterns.SNOW_GOLEM.get());
        this.tag(WindsweptBannerPatternTags.ROSE_FLOWER).add(WindsweptBannerPatterns.ROSE_FLOWER.get());
    }

}

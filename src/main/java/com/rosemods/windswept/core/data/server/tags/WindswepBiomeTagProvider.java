package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindswepBiomeTagProvider extends BiomeTagsProvider {

    public WindswepBiomeTagProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        this.tag(WindsweptBiomeTags.HAS_BLUEBELLS).add(Biomes.DARK_FOREST);
        this.tag(WindsweptBiomeTags.HAS_FOXGLOVES).addTag(BiomeTags.IS_TAIGA);
        this.tag(WindsweptBiomeTags.HAS_TAIGA_FLOWERS).addTag(BiomeTags.IS_TAIGA);
        this.tag(WindsweptBiomeTags.HAS_SNOWY_FLOWERS).addTag(Tags.Biomes.IS_SNOWY);
        this.tag(WindsweptBiomeTags.HAS_HOLLY_TREES).add(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA);
        this.tag(WindsweptBiomeTags.HAS_GROVE_HOLLY_TREES).add(Biomes.GROVE);
        this.tag(WindsweptBiomeTags.HAS_SNOWY_SPROUTS).addTag(Tags.Biomes.IS_SNOWY);
        this.tag(WindsweptBiomeTags.HAS_NIGHTSHADES).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_TAIGA).add(Biomes.DARK_FOREST);
        this.tag(WindsweptBiomeTags.HAS_WILD_BERRIES).addTag(Tags.Biomes.IS_SNOWY);
        this.tag(WindsweptBiomeTags.HAS_COMMON_WILD_BERRIES).add(Biomes.GROVE);
        this.tag(WindsweptBiomeTags.HAS_CHESTNUT_TREES).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_TAIGA);

    }
}

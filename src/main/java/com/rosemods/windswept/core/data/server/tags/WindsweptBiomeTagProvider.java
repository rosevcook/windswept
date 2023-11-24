package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.rosemods.windswept.core.registry.WindsweptBiomes.*;

public class WindsweptBiomeTagProvider extends BiomeTagsProvider {

    public WindsweptBiomeTagProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        this.tag(WindsweptBiomeTags.HAS_BLUEBELLS).add(Biomes.DARK_FOREST).add(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.OLD_GROWTH_SPRUCE_TAIGA, CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey()).addOptional(new ResourceLocation("environmental", "pine_barrens"));
        this.tag(WindsweptBiomeTags.HAS_HOLLY_TREES).add(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA).addOptional(new ResourceLocation("environmental", "pine_barrens")).addOptional(new ResourceLocation("atmospheric", "kousa_jungle"));
        this.tag(WindsweptBiomeTags.HAS_NIGHTSHADES).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_TAIGA).add(Biomes.DARK_FOREST);
        this.tag(WindsweptBiomeTags.HAS_WILD_BERRIES).addTag(Tags.Biomes.IS_SNOWY);
        this.tag(WindsweptBiomeTags.HAS_COMMON_WILD_BERRIES).add(Biomes.GROVE);
        this.tag(WindsweptBiomeTags.HAS_RARE_CHESTNUT_TREES).add(Biomes.DARK_FOREST, Biomes.TAIGA, Biomes.FOREST, Biomes.WINDSWEPT_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.SNOWY_TAIGA, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST, Biomes.WINDSWEPT_HILLS).addOptional(new ResourceLocation("environmental", "pine_barrens")).addOptional(new ResourceLocation("environmental", "blossom_woods"));
        this.tag(WindsweptBiomeTags.HAS_RARE_SNOWY_HOLLY_TREES).add(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS);

        this.tag(WindsweptBiomeTags.HAS_GROVE_WEATHERED_HOUSE).add(Biomes.GROVE, Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS);
        this.tag(WindsweptBiomeTags.HAS_CHESTNUT_WEATHERED_HOUSE).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());

        this.tag(WindsweptBiomeTags.IS_CHESTNUT_FOREST).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());

        this.tag(WindsweptBiomeTags.HAS_SPOTTED_ORANGE_MAPLE_TREES).add(CHESTNUT_FOREST.getKey());
        this.tag(WindsweptBiomeTags.HAS_SPOTTED_RED_MAPLE_TREES).add(SNOWY_CHESTNUT_FOREST.getKey());

        this.tag(BiomeTags.IS_OVERWORLD).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(BiomeTags.IS_FOREST).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(BiomeTags.HAS_IGLOO).add(SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(BiomeTags.IS_TAIGA).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(BiomeTags.HAS_MINESHAFT).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(BiomeTags.STRONGHOLD_BIASED_TO).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(BiomeTags.HAS_PILLAGER_OUTPOST).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(Tags.Biomes.IS_SNOWY).add(SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(Tags.Biomes.IS_COLD).add(SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(Tags.Biomes.IS_CONIFEROUS).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(Tags.Biomes.IS_DENSE).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
        this.tag(Tags.Biomes.IS_DENSE_OVERWORLD).add(CHESTNUT_FOREST.getKey(), SNOWY_CHESTNUT_FOREST.getKey());
    }

}

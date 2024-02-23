package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheep.class)
public class SheepMixin {
    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType type, SpawnGroupData spawnGroupData, CompoundTag tag, CallbackInfoReturnable<SpawnGroupData> info) {
        Sheep sheep = (Sheep) (Object) this;
        RandomSource rand = level.getRandom();
        Holder<Biome> biome = level.getBiome(sheep.blockPosition());

        if (biome.is(WindsweptBiomeTags.IS_PINE_BARRENS))
            sheep.setColor(rand.nextInt(4) == 0 ? DyeColor.ORANGE : DyeColor.BROWN);
        else if (biome.is(WindsweptBiomeTags.IS_LAVENDER) && rand.nextInt(4) == 0)
            sheep.setColor(DyeColor.MAGENTA);
        else if (biome.is(WindsweptBiomeTags.IS_CHESTNUT_FOREST) && rand.nextInt(3) == 0)
            sheep.setColor(rand.nextInt(8) == 0 ? DyeColor.BLUE : DyeColor.BROWN);
    }

}

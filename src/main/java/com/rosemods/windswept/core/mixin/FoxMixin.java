package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptBiomes;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Fox.class)
public class FoxMixin {

    @Inject(method = "populateDefaultEquipmentSlots", at = @At(value = "HEAD"))
    private void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty, CallbackInfo info) {
        Fox fox = (Fox) (Object) this;

        if (rand.nextInt(12) == 0)
            fox.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(WindsweptBlocks.FOXGLOVE.get()));
        else if (fox.getFoxType() == Fox.Type.SNOW && rand.nextInt(4) == 0)
            fox.setItemInHand(InteractionHand.MAIN_HAND, WindsweptItems.WILD_BERRIES.get().getDefaultInstance());
    }

    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType type, SpawnGroupData spawnGroupData, CompoundTag tag, CallbackInfoReturnable<SpawnGroupData> info) {
        Fox fox = (Fox) (Object) this;

        if (level.getRandom().nextBoolean() && level.getBiome(fox.blockPosition()).is(WindsweptBiomes.TUNDRA.getKey()))
            fox.setFoxType(Fox.Type.SNOW);
    }

}

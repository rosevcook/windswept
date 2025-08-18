package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.entity.variant.WindsweptGoatVariant;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Goat.class)
public abstract class GoatMixin extends Animal implements VariantHolder<WindsweptGoatVariant> {
    protected GoatMixin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void defineSynchedData(CallbackInfo info) {
        this.entityData.define(WindsweptGoatVariant.DATA_TYPE_ID, WindsweptGoatVariant.WHITE.id());
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveData(CompoundTag tag, CallbackInfo info) {
        tag.putInt("GoatType", this.entityData.get(WindsweptGoatVariant.DATA_TYPE_ID));
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveData(CompoundTag tag, CallbackInfo info) {
        this.entityData.set(WindsweptGoatVariant.DATA_TYPE_ID, tag.getInt("GoatType"));
    }

    @Inject(method = "finalizeSpawn", at = @At("HEAD"))
    private void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData spawnData, CompoundTag tag, CallbackInfoReturnable<SpawnGroupData> info) {
        this.entityData.set(WindsweptGoatVariant.DATA_TYPE_ID, level.getBiome(this.blockPosition()).is(WindsweptBiomeTags.HAS_BROWN_GOAT) ? 1 : 0);
    }

    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/goat/Goat;", at = @At("RETURN"), cancellable = true)
    private void getBreedOffspring(ServerLevel level, AgeableMob partner, CallbackInfoReturnable<Goat> info) {
        Goat baby = info.getReturnValue();
        WindsweptGoatVariant variant = this.getVariant();
        ((VariantHolder<WindsweptGoatVariant>) baby).setVariant(variant.id() == ((VariantHolder<WindsweptGoatVariant>) partner).getVariant().id() ? variant : WindsweptGoatVariant.GRAY);
        info.setReturnValue(baby);
    }

    @Override
    public void setVariant(WindsweptGoatVariant variant) {
        this.entityData.set(WindsweptGoatVariant.DATA_TYPE_ID, variant.id());
    }

    @Override
    public WindsweptGoatVariant getVariant() {
        return WindsweptGoatVariant.byId(this.entityData.get(WindsweptGoatVariant.DATA_TYPE_ID));
    }

}

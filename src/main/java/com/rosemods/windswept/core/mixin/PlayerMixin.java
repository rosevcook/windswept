package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    private PlayerMixin(EntityType<? extends LivingEntity> entity, Level level) {
        super(entity, level);
    }

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void aiStep(CallbackInfo info) {
        if (!this.level.isClientSide && !this.isInPowderSnow && !this.isDeadOrDying() && this.canFreeze() && this.isUnderWater() && WindsweptConfig.COMMON.freezingOceans.get()) {
            Holder<Biome> biome = this.level.getBiome(this.blockPosition());

            if (biome.is(Tags.Biomes.IS_SNOWY) && (biome.is(BiomeTags.IS_OCEAN) || biome.is(BiomeTags.IS_RIVER)))
                this.setTicksFrozen(this.getTicksFrozen() + (this.random.nextBoolean() ? 2 : 3));
        }
    }

}

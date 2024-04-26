package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
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
        if (!this.level.isClientSide && !this.isInPowderSnow && !this.isDeadOrDying() && this.canFreeze() && this.isUnderWater() && WindsweptConfig.COMMON.freezingWater.get() && this.level.getBiome(this.blockPosition()).is(Tags.Biomes.IS_SNOWY) && this.blockPosition().getY() > 0)
            this.setTicksFrozen(this.getTicksFrozen() + (this.random.nextBoolean() ? 2 : 3));
    }

}

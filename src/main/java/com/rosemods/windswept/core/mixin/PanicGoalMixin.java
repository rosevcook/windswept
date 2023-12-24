package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PanicGoal.class)
public class PanicGoalMixin {
    @Shadow
    @Final
    protected PathfinderMob mob;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> info) {
        for (LivingEntity entity : this.mob.level.getEntitiesOfClass(LivingEntity.class, new AABB(this.mob.blockPosition()).inflate(3)))
            if (entity.getItemBySlot(EquipmentSlot.HEAD).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem())) {
                info.setReturnValue(true);
                return;
            }
    }

}

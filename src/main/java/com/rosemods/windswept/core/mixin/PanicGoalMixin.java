package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.entity.animal.Frostbiter;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.level.block.state.BlockState;
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
        AABB radius = new AABB(this.mob.blockPosition()).inflate(2);

        for (LivingEntity entity : this.mob.level.getEntitiesOfClass(LivingEntity.class, radius))
            if (this.mob != entity && (entity.getItemBySlot(EquipmentSlot.HEAD).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem()) || (entity instanceof Frostbiter frostbiter && frostbiter.isVehicle()))) {
                info.setReturnValue(true);
                return;
            }

        for (BlockState state : this.mob.level.getBlockStatesIfLoaded(radius).toList())
            if (state.is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get()) || state.is(WindsweptBlocks.WILL_O_THE_WISP.get())) {
                info.setReturnValue(true);
                return;
            }
    }

}

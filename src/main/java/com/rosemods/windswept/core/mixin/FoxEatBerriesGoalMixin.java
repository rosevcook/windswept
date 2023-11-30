package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.block.WildBerryBushBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Fox.FoxEatBerriesGoal.class)
public abstract class FoxEatBerriesGoalMixin extends MoveToBlockGoal {

    private FoxEatBerriesGoalMixin(PathfinderMob mob, double speedModifier, int searchRange) {
        super(mob, speedModifier, searchRange);
    }

    @Inject(method = "onReachedTarget", at = @At("HEAD"))
    private void onReachedTarget(CallbackInfo ci) {
        BlockState state = this.mob.level.getBlockState(this.blockPos);

        if (ForgeEventFactory.getMobGriefingEvent(this.mob.level, this.mob) && state.is(WindsweptBlocks.WILD_BERRY_BUSH.get())) {
            int amount = 1 + this.mob.level.random.nextInt(2);

            if (this.mob.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                this.mob.setItemSlot(EquipmentSlot.MAINHAND, WindsweptItems.WILD_BERRIES.get().getDefaultInstance());
                amount--;
            }

            if (amount > 0)
                Block.popResource(this.mob.level, this.blockPos, new ItemStack(WindsweptItems.WILD_BERRIES.get(), amount));

            this.mob.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1f, 1f);
            this.mob.level.setBlock(this.blockPos, state.setValue(WildBerryBushBlock.AGE, 1), 2);
        }

    }

    @Inject(at = @At("RETURN"), method = "isValidTarget", cancellable = true)
    private void isValidTarget(LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        BlockState state = level.getBlockState(pos);

        if (state.is(WindsweptBlocks.WILD_BERRY_BUSH.get()) && state.getValue(WildBerryBushBlock.AGE) == 3)
            info.setReturnValue(true);
    }

}

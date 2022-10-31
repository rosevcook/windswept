package com.rosemods.windswept.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(Fox.class)
public abstract class FoxMixin extends Animal {
	
	protected FoxMixin(EntityType<? extends Fox> entity, Level level) {
		super(entity, level);
	}
	
	@Inject(method = "populateDefaultEquipmentSlots", at = @At(value = "HEAD"))
	private void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance diffuculty, CallbackInfo info) {		
		if (this.random.nextInt(12) == 0)
			this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(WindsweptBlocks.FOXGLOVE.get()));
		else if (((Fox) (Object) this).getFoxType() == Fox.Type.SNOW && this.random.nextInt(12) == 0)
			this.setItemInHand(InteractionHand.MAIN_HAND, WindsweptItems.WILD_BERRIES.get().getDefaultInstance());
	}
	
}

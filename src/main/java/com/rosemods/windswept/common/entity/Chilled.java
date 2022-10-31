package com.rosemods.windswept.common.entity;

import com.rosemods.windswept.core.registry.WindsweptEnchantments;
import com.rosemods.windswept.core.registry.WindsweptEntities;
import com.rosemods.windswept.core.registry.WindsweptItems;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Chilled extends Zombie {
	
	public Chilled(Level level) {
		super(WindsweptEntities.CHILLED.get(), level);
	}
	
	public Chilled(EntityType<? extends Zombie> type, Level level) {
		super(type, level);
	}

	@Override
	protected ItemStack getSkull() {
		return ItemStack.EMPTY;
	}
			
	@Override
	protected boolean convertsInWater() {
		return false;
	}
	
	@Override
	public boolean canFreeze() {
		return false;
	}
	
	@Override
	public boolean doHurtTarget(Entity entity) {
		if (super.doHurtTarget(entity) && entity instanceof LivingEntity) {
			((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600), this);
			return true;
		}
		
		return false;
	}
	
	@Override
	protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(rand, difficulty);
		
		if (this.random.nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? .5f : .33f))
			this.setItemSlot(EquipmentSlot.FEET, this.random.nextBoolean() ? Items.LEATHER_BOOTS.getDefaultInstance() : WindsweptItems.SNOW_BOOTS.get().getDefaultInstance());
	}
	
	@Override
	protected void enchantSpawnedArmor(RandomSource rand, float difficulty, EquipmentSlot slot) {
		ItemStack stack = this.getItemBySlot(slot);
		if (!stack.isEmpty() && this.random.nextFloat() < .5f * difficulty) 
	         stack.enchant(WindsweptEnchantments.SLIPPING_CURSE.get(), 0);
		else
			super.enchantSpawnedArmor(rand, difficulty, slot);
	}
			
}

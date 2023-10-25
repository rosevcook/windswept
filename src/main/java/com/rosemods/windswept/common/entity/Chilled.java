package com.rosemods.windswept.common.entity;

import com.rosemods.windswept.core.other.WindsweptConstants;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;

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
        if (super.doHurtTarget(entity) && entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600), this);
            return true;
        }

        return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty) {
        if (ModList.get().isLoaded(WindsweptConstants.CAVERNS_AND_CHASMS))
            cncCompat(rand);

        if (rand.nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? .5f : .33f))
            this.setItemSlot(EquipmentSlot.FEET, rand.nextBoolean() ? Items.LEATHER_BOOTS.getDefaultInstance() : WindsweptItems.SNOW_BOOTS.get().getDefaultInstance());
    }

    public void cncCompat(RandomSource random) {
        if (random.nextFloat() < .075f)
            this.setItemSlot(EquipmentSlot.MAINHAND, randomDurability(random, random.nextBoolean() ? WindsweptConstants.SILVER_SWORD.get() : WindsweptConstants.SILVER_SHOVEL.get()));
        else if (random.nextFloat() < .05f) {
            this.setItemSlot(EquipmentSlot.MAINHAND, randomDurability(random, WindsweptConstants.SILVER_AXE.get()));
            this.setItemSlot(EquipmentSlot.OFFHAND, randomDurability(random, WindsweptConstants.SILVER_AXE.get()));
        }

        if (random.nextFloat() < .05f) this.setItemSlot(EquipmentSlot.HEAD, randomDurability(random, WindsweptConstants.SILVER_HELMET.get()));
        if (random.nextFloat() < .05f) this.setItemSlot(EquipmentSlot.CHEST, randomDurability(random, WindsweptConstants.SILVER_CHESTPLATE.get()));
        if (random.nextFloat() < .05f) this.setItemSlot(EquipmentSlot.LEGS, randomDurability(random, WindsweptConstants.SILVER_LEGGINGS.get()));
        if (random.nextFloat() < .05f) this.setItemSlot(EquipmentSlot.FEET, randomDurability(random, WindsweptConstants.SILVER_BOOTS.get()));
    }

    private static ItemStack randomDurability(RandomSource random, Item item) {
        ItemStack stack = item.getDefaultInstance();
        stack.setDamageValue(random.nextInt(1, stack.getMaxDamage()));

        return stack;
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

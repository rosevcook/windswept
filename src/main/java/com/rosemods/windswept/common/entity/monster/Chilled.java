package com.rosemods.windswept.common.entity.monster;

import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptEnchantments;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
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

    public Chilled(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity) && entity instanceof LivingEntity livingEntity) {
            livingEntity.setTicksFrozen(livingEntity.getTicksFrozen() + 100);
            return true;
        }

        return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty) {
        float chance = this.level.getDifficulty() == Difficulty.HARD ? .5f : .33f;

        if (rand.nextFloat() < chance)
            this.setItemSlot(EquipmentSlot.HEAD, Items.LEATHER_HELMET.getDefaultInstance());

        this.cncCompat(rand);

        if (rand.nextFloat() < .075f) {
            this.setItemSlot(EquipmentSlot.OFFHAND, WindsweptItems.CANDY_CANE.get().getDefaultInstance());
            this.armorDropChances[EquipmentSlot.OFFHAND.getIndex()] = 1f;
        }

        if (rand.nextFloat() < chance) {
            boolean snow = rand.nextBoolean();
            this.setItemSlot(EquipmentSlot.FEET, snow ? WindsweptItems.SNOW_BOOTS.get().getDefaultInstance() : Items.LEATHER_BOOTS.getDefaultInstance());

            if (snow)
                this.armorDropChances[EquipmentSlot.FEET.getIndex()] = .5f;
        }

        if (rand.nextFloat() < .1f && this.level.getBiome(this.blockPosition()).is(WindsweptBiomeTags.IS_PINE_BARRENS)) {
            this.setItemSlot(EquipmentSlot.HEAD, WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem().getDefaultInstance());
            this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = .5f;
        }

    }

    public void cncCompat(RandomSource random) {
        if (ModList.get().isLoaded(WindsweptConstants.CAVERNS_AND_CHASMS)) {
            if (random.nextFloat() < .075f)
                this.setItemSlot(EquipmentSlot.MAINHAND, randomDurability(random, random.nextBoolean() ? WindsweptConstants.getItem("caverns_and_chasms", "silver_sword") : WindsweptConstants.getItem("caverns_and_chasms", "silver_shovel")));
            else if (random.nextFloat() < .05f) {
                Item axe = WindsweptConstants.getItem("caverns_and_chasms", "silver_axe");
                this.setItemSlot(EquipmentSlot.MAINHAND, randomDurability(random, axe));
                this.setItemSlot(EquipmentSlot.OFFHAND, randomDurability(random, axe));
            }

            if (random.nextFloat() < .05f)
                this.setItemSlot(EquipmentSlot.HEAD, randomDurability(random, WindsweptConstants.getItem("caverns_and_chasms", "silver_helmet")));
            if (random.nextFloat() < .05f)
                this.setItemSlot(EquipmentSlot.CHEST, randomDurability(random, WindsweptConstants.getItem("caverns_and_chasms", "silver_chestplate")));
            if (random.nextFloat() < .05f)
                this.setItemSlot(EquipmentSlot.LEGS, randomDurability(random, WindsweptConstants.getItem("caverns_and_chasms", "silver_leggings")));
            if (random.nextFloat() < .05f)
                this.setItemSlot(EquipmentSlot.FEET, randomDurability(random, WindsweptConstants.getItem("caverns_and_chasms", "silver_boots")));
        }
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

    @Override
    protected void doUnderWaterConversion() {
        this.convertToZombieType(EntityType.ZOMBIE);

        if (!this.isSilent())
            this.level.levelEvent(null, 1041, this.blockPosition(), 0);
    }

}

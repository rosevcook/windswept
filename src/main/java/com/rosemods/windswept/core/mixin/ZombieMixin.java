package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.other.tags.WindsweptBiomeTags;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public class ZombieMixin extends Monster {
    private ZombieMixin(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    public void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty, CallbackInfo info) {
        Holder<Biome> biome = this.level.getBiome(this.blockPosition());
        boolean chance = rand.nextFloat() < .1f;
        
        if (chance && biome.is(WindsweptBiomeTags.IS_PINE_BARRENS)) {
            this.setItemSlot(EquipmentSlot.HEAD, WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem().getDefaultInstance());
            this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = .5f;
        } else if (chance && biome.is(WindsweptBiomeTags.IS_LAVENDER)) {
            this.setItemSlot(EquipmentSlot.HEAD, WindsweptItems.LAVENDER_CROWN.get().getDefaultInstance());
            this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = .5f;
        }
    }

}

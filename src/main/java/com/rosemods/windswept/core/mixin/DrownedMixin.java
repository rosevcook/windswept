package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Drowned.class)
public class DrownedMixin {
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    public void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty, CallbackInfo info) {
        if (random.nextFloat() < .05f)
            ((Zombie) (Object) this).setItemSlot(EquipmentSlot.HEAD, WindsweptItems.WOODEN_BUCKET.get().getDefaultInstance());
    }

}

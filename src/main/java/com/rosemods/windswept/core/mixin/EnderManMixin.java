package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.class)
public class EnderManMixin {
    @Inject(method = "isLookingAtMe", at = @At("HEAD"), cancellable = true)
    private void isLookingAtMe(Player player, CallbackInfoReturnable<Boolean> info) {
        if (player.getInventory().armor.get(3).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.get().asItem()))
            info.setReturnValue(true);
    }
}

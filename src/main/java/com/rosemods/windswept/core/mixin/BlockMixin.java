package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.enchantment.curse.SlippingCurseEnchantment;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public abstract class BlockMixin implements IForgeBlock {

    @Override
    public float getFriction(BlockState state, LevelReader level, BlockPos pos, Entity entity) {
        return entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(WindsweptEffects.FROST_RESISTANCE.get()) ? 0f : SlippingCurseEnchantment.getFriction(entity, ((Block) (Object) this).getFriction());
    }

}

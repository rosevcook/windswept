package com.rosemods.windswept.core.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {

	protected MobMixin(EntityType<? extends Mob> type, Level level) {
		super(type, level);
	}
	
	@Override
	public boolean canFreeze() { // they are taking damage in the snow still which is annoying
		return super.canFreeze() || !this.getType().is(WindsweptEntityTypeTags.CONVERT_TO_CHILLED);
	}

}

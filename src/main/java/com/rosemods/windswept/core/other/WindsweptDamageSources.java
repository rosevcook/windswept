package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;

import net.minecraft.world.damagesource.DamageSource;

public final class WindsweptDamageSources {
	public static final DamageSource HOLLY_LEAVES = create("holly_leaves");
	public static final DamageSource HOLLY_SAPLING = create("holly_sapling");
	public static final DamageSource HOLLY_HEDGE = create("holly_hedge");
	
	private static DamageSource create(String name) {
		return new DamageSource(Windswept.MODID + "." + name);
	}

}

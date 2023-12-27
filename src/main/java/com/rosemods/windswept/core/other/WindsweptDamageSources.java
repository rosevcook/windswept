package com.rosemods.windswept.core.other;

import net.minecraft.world.damagesource.DamageSource;

public final class WindsweptDamageSources {
    public static final DamageSource HOLLY_LEAVES = new DamageSource("windswept.holly_leaves");
    public static final DamageSource ICICLE = new DamageSource("windswept.icicle").bypassArmor().setIsFall();
}

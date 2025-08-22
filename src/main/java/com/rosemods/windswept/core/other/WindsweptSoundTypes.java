package com.rosemods.windswept.core.other;

import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

public final class WindsweptSoundTypes {
    public static final ForgeSoundType PINECONE = new ForgeSoundType(1f, 1.33f, () -> SoundEvents.CHERRY_WOOD_BREAK, () -> SoundEvents.CHERRY_WOOD_STEP, () -> SoundEvents.CHERRY_WOOD_PLACE,() ->  SoundEvents.CHERRY_WOOD_HIT, () -> SoundEvents.CHERRY_WOOD_FALL);
    public static final ForgeSoundType SUSPICIOUS_SNOW = new ForgeSoundType(1f, 1f, () -> SoundEvents.SUSPICIOUS_SAND_BREAK, () -> SoundEvents.SNOW_STEP, () -> SoundEvents.SNOW_PLACE,() ->  SoundEvents.SNOW_HIT, () -> SoundEvents.SNOW_FALL);
}

package com.rosemods.windswept.core.other;

import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

public final class WindsweptSoundTypes {
    public static final ForgeSoundType PINECONE = new ForgeSoundType(1f, 1.33f, () -> SoundEvents.WOOD_BREAK, () -> SoundEvents.WOOD_STEP, () -> SoundEvents.WOOD_PLACE,() ->  SoundEvents.WOOD_HIT, () -> SoundEvents.WOOD_FALL);
}

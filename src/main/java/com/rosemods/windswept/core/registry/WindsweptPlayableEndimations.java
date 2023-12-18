package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimationManager;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptPlayableEndimations {
    public static final PlayableEndimation FROSTBITER_SHAKE = register("frostbiter/shake", 25, PlayableEndimation.LoopType.NONE);
    public static final PlayableEndimation FROSTBITER_EAT = register("frostbiter/eat", 40, PlayableEndimation.LoopType.NONE);

    private static PlayableEndimation register(String name, int duration, PlayableEndimation.LoopType loopType) {
        return PlayableEndimationManager.INSTANCE.registerPlayableEndimation(new PlayableEndimation(Windswept.location(name), duration, loopType));
    }

}

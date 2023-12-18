package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.api.BlueprintRabbitTypes;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID)
public class WindsweptRabbitTypes {
    public static final BlueprintRabbitTypes.BlueprintRabbitType CHESTNUT = BlueprintRabbitTypes.register(1227, Windswept.location("chestnut"));
}

package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.client.screen.splash.SplashProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptSplashProvider extends SplashProvider {
    public WindsweptSplashProvider(GatherDataEvent event) {
        super(Windswept.MOD_ID, event.getGenerator().getPackOutput());
    }

    @Override
    protected void registerSplashes() {
        this.add("#WINDSWEEP");
    }

}

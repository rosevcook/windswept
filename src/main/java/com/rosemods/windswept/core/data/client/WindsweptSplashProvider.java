package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.client.screen.splash.SplashProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptSplashProvider extends SplashProvider {
    public WindsweptSplashProvider(GatherDataEvent event) {
        super(Windswept.MOD_ID, event.getGenerator());
    }

    @Override
    protected void registerSplashes() {
        this.add("#WINDSWEEP");
        this.add("new update out in 3-4");
    }

}

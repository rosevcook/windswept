package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptParticleProvider extends ParticleProvider {
    public WindsweptParticleProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID);
    }

    @Override
    protected void addParticles() {
        this.add(WindsweptParticleTypes.CUPIDS_ARROW.get(), "cupids_arrow");
    }

}

package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import net.minecraftforge.data.event.GatherDataEvent;

public class WindsweptParticleProvider extends ParticleProvider {
    public WindsweptParticleProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), Windswept.MOD_ID);
    }

    @Override
    protected void addParticles() {
        this.add(WindsweptParticleTypes.WILL_O_THE_WISP.get(), "will_o_the_wisp_0", "will_o_the_wisp_1", "will_o_the_wisp_2");
        this.add(WindsweptParticleTypes.FROST_LEAF.get(), "frost_leaf_0", "frost_leaf_1", "frost_leaf_2");
        this.add(WindsweptParticleTypes.FEATHER_CLOAK.get(), "feather_cloak_0", "feather_cloak_1", "feather_cloak_2",
                "feather_cloak_3", "feather_cloak_4", "feather_cloak_5", "feather_cloak_6", "feather_cloak_7", "feather_cloak_8", "feather_cloak_9");
    }

}

package com.rosemods.windswept.client.model;

import com.rosemods.windswept.common.entity.Chilled;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;

import java.util.List;

public class ChilledModel extends ZombieModel<Chilled> {
    public ChilledModel(ModelPart root) {
        super(root);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return List.of(this.head, this.hat);
    }

}

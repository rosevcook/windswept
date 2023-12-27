package com.rosemods.windswept.client.model;

import com.google.common.collect.ImmutableList;
import com.rosemods.windswept.common.entity.monster.Chilled;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;

public class ChilledModel extends ZombieModel<Chilled> {
    public ChilledModel(ModelPart root) {
        super(root);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head, this.hat);
    }

}

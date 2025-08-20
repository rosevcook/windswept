package com.rosemods.windswept.common.entity.variant;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.animal.goat.Goat;

import java.util.function.IntFunction;

public enum WindsweptGoatVariant implements StringRepresentable {
    WHITE(0, "white", new ResourceLocation("textures/entity/goat/goat.png")),
    BROWN(1, "brown", Windswept.location("textures/entity/goat/brown.png")),
    GRAY(2, "gray", Windswept.location("textures/entity/goat/gray.png")),
    ALPINE(3, "alpine", Windswept.location("textures/entity/goat/alpine.png"));

    public static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(Goat.class, EntityDataSerializers.INT);
    private static final IntFunction<WindsweptGoatVariant> BY_ID = ByIdMap.sparse(WindsweptGoatVariant::id, values(), WHITE);

    private final int id;
    private final String name;
    private final ResourceLocation texture;

    WindsweptGoatVariant(int id, String name, ResourceLocation texture) {
        this.id = id;
        this.name = name;
        this.texture = texture;
    }

    public int id() {
        return this.id;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public static WindsweptGoatVariant byId(int id) {
        return BY_ID.apply(id);
    }

}
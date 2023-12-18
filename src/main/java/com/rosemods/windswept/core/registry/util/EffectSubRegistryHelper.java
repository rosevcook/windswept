package com.rosemods.windswept.core.registry.util;

import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import com.teamabnormals.blueprint.core.util.registry.ISubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class EffectSubRegistryHelper implements ISubRegistryHelper<MobEffect> {
    protected final RegistryHelper parent;
    protected final DeferredRegister<MobEffect> effectRegister;
    protected final DeferredRegister<Potion> potionRegister;

    public EffectSubRegistryHelper(RegistryHelper parent) {
        this(parent, DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, parent.getModId()), DeferredRegister.create(ForgeRegistries.POTIONS, parent.getModId()));
    }

    public EffectSubRegistryHelper(RegistryHelper parent, DeferredRegister<MobEffect> effectRegister, DeferredRegister<Potion> potionRegister) {
        this.parent = parent;
        this.effectRegister = effectRegister;
        this.potionRegister = potionRegister;
    }

    public <E extends MobEffect> RegistryObject<E> createEffect(String name, Supplier<? extends E> effect) {
        return this.effectRegister.register(name, effect);
    }

    public RegistryObject<MobEffect> createEffect(String name, MobEffectCategory effectType, int liquidColor) {
        return this.effectRegister.register(name, () -> new BlueprintMobEffect(effectType, liquidColor));
    }

    public RegistryObject<Potion> createPotion(String name, Supplier<? extends MobEffect> effect, int duration, int strength) {
        return this.potionRegister.register(name, () -> new Potion(new MobEffectInstance(effect.get(), duration, strength)));
    }

    @Override
    public RegistryHelper getParent() {
        return this.parent;
    }

    @Override
    public DeferredRegister<MobEffect> getDeferredRegister() {
        return this.effectRegister;
    }

    @Override
    public void register(IEventBus eventBus) {
        this.effectRegister.register(eventBus);
        this.potionRegister.register(eventBus);
    }

}

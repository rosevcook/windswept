package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.levelgen.tree.decorator.BranchDecorator;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WindsweptTreeDecorators {
    public static final DeferredRegister<TreeDecoratorType<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Windswept.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<?>> BRANCH_DECORATOR = DECORATORS.register("branch", () -> new TreeDecoratorType<>(BranchDecorator.CODEC));
}

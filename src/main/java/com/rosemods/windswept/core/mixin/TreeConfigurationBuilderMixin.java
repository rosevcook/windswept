package com.rosemods.windswept.core.mixin;

import com.google.common.collect.ImmutableList;
import com.rosemods.windswept.common.levelgen.tree.decorator.BranchDecorator;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;
import java.util.List;

@Mixin(TreeConfigurationBuilder.class)
public class TreeConfigurationBuilderMixin {

    @Shadow
    private List<TreeDecorator> decorators;

    @Inject(method = "decorators", at = @At("HEAD"), cancellable = true)
    private void decorators(List<TreeDecorator> list, CallbackInfoReturnable<TreeConfigurationBuilder> info) {
        if (this.decorators.stream().anyMatch(s -> s instanceof BranchDecorator)) {
            this.decorators = new LinkedList<>(this.decorators);
            this.decorators.addAll(list);
            this.decorators = ImmutableList.copyOf(this.decorators);
            info.setReturnValue((TreeConfigurationBuilder) (Object) this);
        }
    }

}

package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallFlowerBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TallFlowerBlock.class)
public class TallFlowerBlockMixin extends Block {

    protected TallFlowerBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public String getDescriptionId() {
        return this == Blocks.ROSE_BUSH && WindsweptConfig.COMMON.renameRoseBushes.get()
                ? "block.windswept.red_rose_bush" : super.getDescriptionId();
    }

}

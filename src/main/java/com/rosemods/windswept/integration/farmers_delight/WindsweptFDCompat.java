package com.rosemods.windswept.integration.farmers_delight;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public final class WindsweptFDCompat {
    public static final Supplier<Block> CABINET_SUPPLIER = () -> new WindsweptCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BARREL));
}

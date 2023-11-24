package com.rosemods.windswept.core.other;

import com.teamabnormals.blueprint.core.util.PropertyUtil;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public final class WindsweptProperties {

    // Wood Sets //
    public static final PropertyUtil.WoodSetProperties HOLLY = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_PURPLE).build();
    public static final PropertyUtil.WoodSetProperties CHESTNUT = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_BROWN).build();

    // Blocks //

    public static final BlockBehaviour.Properties SNOW_BRICKS = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SNOW).strength(.85f).sound(SoundType.SNOW);
    public static final BlockBehaviour.Properties ICE_BRICKS = BlockBehaviour.Properties.of(Material.ICE_SOLID, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.8f).sound(SoundType.STONE);
    public static final BlockBehaviour.Properties CRATE = BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).sound(SoundType.WOOD);

}

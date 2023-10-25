package com.rosemods.windswept.core.other;

import com.teamabnormals.blueprint.core.util.PropertyUtil;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public final class WindsweptProperties {
    // Wood Sets //
    public static final PropertyUtil.WoodSetProperties HOLLY = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_PURPLE).build();
    public static final PropertyUtil.WoodSetProperties CHESTNUT = PropertyUtil.WoodSetProperties.builder(MaterialColor.COLOR_BROWN).build();

    // Blocks //

    public static final BlockBehaviour.Properties PACKED_SNOW = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SNOW).strength(.85f).sound(SoundType.SNOW);
    public static final BlockBehaviour.Properties ICE_BRICKS = BlockBehaviour.Properties.of(Material.ICE_SOLID, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.8f).sound(SoundType.STONE);

    public static final BlockBehaviour.Properties SNOWY_SPROUTS = BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.XZ);
    public static final BlockBehaviour.Properties NIGHTSHADE = BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(state -> 9);
    public static final BlockBehaviour.Properties NIGHTSHADE_POT = BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion().lightLevel(state -> 9);
    public static final BlockBehaviour.Properties FROZEN_FLESH_BLOCK = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_BLUE).strength(.8f).sound(SoundType.CORAL_BLOCK);

    public static final BlockBehaviour.Properties CRATE = BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties GLOW_SHROOM_CRATE = BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).sound(SoundType.WOOD).lightLevel(s -> 12);

    public static final BlockBehaviour.Properties WILD_BERRY_BUSH = BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).offsetType(BlockBehaviour.OffsetType.XZ);

    public static final BlockBehaviour.Properties POLISHED_DEEPSLATE_PRESSURE_PLATE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().noCollission().strength(.5f).sound(SoundType.POLISHED_DEEPSLATE);
    public static final BlockBehaviour.Properties POLISHED_DEEPSLATE_BUTTON = BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(.5f).sound(SoundType.POLISHED_DEEPSLATE);

    // Items //

    public static final Item.Properties MUSIC_DISC = new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE);
}

package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.block.IWoodenBucketPickupBlock;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LiquidBlock.class)
public class LiquidBlockMixin implements IWoodenBucketPickupBlock {

    @Override
    public boolean canPickupFromWoodenBucket(LevelAccessor level, BlockPos pos, BlockState state) {
        if (ModList.get().isLoaded("create")) {
            ResourceLocation location = ForgeRegistries.BLOCKS.getKey(state.getBlock());

            if (location.equals(WindsweptConstants.HONEY) || location.equals(WindsweptConstants.CHOCOLATE))
                return true;
        }

        return state.is(Blocks.WATER);
    }

    @Override
    public Item getWoodenBucketItem(BlockState state) {
        if (ModList.get().isLoaded("create")) {
            ResourceLocation location = ForgeRegistries.BLOCKS.getKey(state.getBlock());

            if (location.equals(WindsweptConstants.HONEY))
                return WindsweptItems.WOODEN_HONEY_BUCKET.get();
            else if (location.equals(WindsweptConstants.CHOCOLATE))
                return WindsweptItems.WOODEN_CHOCOLATE_BUCKET.get();
        }

        return WindsweptItems.WOODEN_WATER_BUCKET.get();
    }

}

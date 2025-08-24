package com.rosemods.windswept.common.capability.wrappers;

import com.rosemods.windswept.common.item.WoodenBucketItem;
import com.rosemods.windswept.common.item.WoodenMilkBucketItem;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenBucketWrapper implements IFluidHandlerItem, ICapabilityProvider {
    private final LazyOptional<IFluidHandlerItem> holder = LazyOptional.of(() -> this);
    private ItemStack container;

    public WoodenBucketWrapper(ItemStack container) {
        this.container = container;
    }

    @Override
    public ItemStack getContainer() {
        return this.container;
    }

    private FluidStack getFluid() {
        Item item = this.container.getItem();

        if (item instanceof WoodenBucketItem bucket && !bucket.isEmpty())
            return new FluidStack(bucket.getFluid(), FluidType.BUCKET_VOLUME);
        else if (item instanceof WoodenMilkBucketItem && ForgeMod.MILK.isPresent())
            return new FluidStack(ForgeMod.MILK.get(), FluidType.BUCKET_VOLUME);
        else
            return FluidStack.EMPTY;
    }

    private void setFluid(FluidStack stack) {
        if (stack.isEmpty())
            this.container = WoodenBucketItem.getEmpty(this.container, null, null);
        else {
            Item item = getBucketFromFluid(stack.getFluid());

            if (item != null)
                this.container = WoodenBucketItem.getFilled(this.container, item, null);
        }
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        return this.getFluid();
    }

    @Override
    public int getTankCapacity(int tank) {
        return FluidType.BUCKET_VOLUME;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return true;
    }

    @Override
    public int fill(FluidStack stack, FluidAction action) {
        if (!this.getFluid().isEmpty() || stack.getAmount() < FluidType.BUCKET_VOLUME || !canFillFromFluid(stack.getFluid()))
            return 0;
        else if (action.execute())
            this.setFluid(stack);

        return FluidType.BUCKET_VOLUME;
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        if (this.container.getCount() != 1 || maxDrain < FluidType.BUCKET_VOLUME)
            return FluidStack.EMPTY;

        FluidStack fluidStack = this.getFluid();

        if (!fluidStack.isEmpty()) {
            if (action.execute())
                this.setFluid(FluidStack.EMPTY);

            return fluidStack;
        }

        return FluidStack.EMPTY;
    }

    @Override
    public FluidStack drain(FluidStack stack, FluidAction action) {
        if (this.container.getCount() != 1 || stack.getAmount() < FluidType.BUCKET_VOLUME)
            return FluidStack.EMPTY;

        FluidStack fluidStack = this.getFluid();

        if (!fluidStack.isEmpty() && fluidStack.isFluidEqual(stack)) {
            if (action.execute())
                this.setFluid(FluidStack.EMPTY);

            return fluidStack;
        }

        return FluidStack.EMPTY;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
        return ForgeCapabilities.FLUID_HANDLER_ITEM.orEmpty(capability, this.holder);
    }

    private static Item getBucketFromFluid(Fluid fluid) {
        if (fluid == Fluids.WATER)
            return WindsweptItems.WOODEN_WATER_BUCKET.get();
        else if (ForgeMod.MILK.isPresent() && fluid == ForgeMod.MILK.get())
            return WindsweptItems.WOODEN_MILK_BUCKET.get();
        else if (ModList.get().isLoaded("create")) {
            ResourceLocation location = ForgeRegistries.FLUIDS.getKey(fluid);

            if (location.equals(WindsweptConstants.HONEY))
                return WindsweptItems.WOODEN_HONEY_BUCKET.get();
            else if (location.equals(WindsweptConstants.CHOCOLATE))
                return WindsweptItems.WOODEN_CHOCOLATE_BUCKET.get();
        }

        return null;
    }

    private static boolean canFillFromFluid(Fluid fluid) {
        if (ModList.get().isLoaded("create")) {
            ResourceLocation location = ForgeRegistries.FLUIDS.getKey(fluid);

            if (location.equals(WindsweptConstants.HONEY) || location.equals(WindsweptConstants.CHOCOLATE))
                return true;
        }

        return fluid == Fluids.WATER || (ForgeMod.MILK.isPresent() && fluid == ForgeMod.MILK.get());
    }

}

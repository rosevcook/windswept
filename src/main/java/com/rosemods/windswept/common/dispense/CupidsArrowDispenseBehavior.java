package com.rosemods.windswept.common.dispense;

import com.rosemods.windswept.common.entity.projectile.CupidsArrow;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CupidsArrowDispenseBehavior extends AbstractProjectileDispenseBehavior {
    @Override
    protected Projectile getProjectile(Level level, Position pos, ItemStack stack) {
        CupidsArrow entity = new CupidsArrow(level, pos.x(), pos.y(), pos.z());
        entity.pickup = AbstractArrow.Pickup.ALLOWED;

        return entity;
    }

}


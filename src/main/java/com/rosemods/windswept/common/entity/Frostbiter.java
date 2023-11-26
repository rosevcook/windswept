package com.rosemods.windswept.common.entity;

import com.rosemods.windswept.core.registry.WindsweptEntities;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.endimator.Endimatable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Frostbiter extends TamableAnimal implements Endimatable {

    public Frostbiter(EntityType<? extends Frostbiter> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25d, Ingredient.of(WindsweptItems.HOLLY_BERRIES.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1d));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, .7f));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new BreedGoal(this, 1.0D));

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        //this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
       // this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.COW_STEP, .15f, 1f);
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return this.isTame();
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return WindsweptEntities.FROSTBITER.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(WindsweptItems.HOLLY_BERRIES.get());
    }

    @Override
    protected float getStandingEyeHeight(Pose pos, EntityDimensions dimensions) {
        return this.isBaby() ? dimensions.height * .95f : 1.3f;
    }

    public static AttributeSupplier.Builder createFrostbiterAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ARMOR, 4f)
                .add(Attributes.ATTACK_DAMAGE, 5f)
                .add(Attributes.MAX_HEALTH, 45f)
                .add(Attributes.MOVEMENT_SPEED, .22f);
    }

}

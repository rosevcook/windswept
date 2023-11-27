package com.rosemods.windswept.common.entity;

import com.rosemods.windswept.core.registry.WindsweptEntities;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.rosemods.windswept.core.registry.WindsweptPlayableEndimations;
import com.teamabnormals.blueprint.core.endimator.Endimatable;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Frostbiter extends TamableAnimal implements Endimatable, NeutralMob {
    private static final EntityDataAccessor<Boolean> LEFT_ANTLER = SynchedEntityData.defineId(Frostbiter.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RIGHT_ANTLER = SynchedEntityData.defineId(Frostbiter.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(Frostbiter.class, EntityDataSerializers.INT);
    private static final UniformInt ANGER_RANGE = TimeUtil.rangeOfSeconds(20, 39);

    private int dropDelay;
    private int blinkDelay;
    private boolean isBlinking;
    private UUID lastHurtBy;

    public Frostbiter(EntityType<? extends Frostbiter> type, Level level) {
        super(type, level);
        this.setTame(false);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LEFT_ANTLER, true);
        this.entityData.define(RIGHT_ANTLER, true);
        this.entityData.define(ANGER_TIME, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("LeftAntler", this.hasLeftAntler());
        compound.putBoolean("RightAntler", this.hasRightAntler());
        this.addPersistentAngerSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setLeftAntler(compound.getBoolean("LeftAntler"));
        this.setRightAntler(compound.getBoolean("RightAntler"));
        this.readPersistentAngerSaveData(this.level, compound);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FrostbiterPanicGoal());
        this.goalSelector.addGoal(2, new FrostbiterMeleeAttackGoal());
        this.goalSelector.addGoal(2, new BreedGoal(this, 1f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1f, Ingredient.of(WindsweptItems.HOLLY_BERRIES.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1f));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, .7f));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    protected void customServerAiStep() {
        this.updatePersistentAnger((ServerLevel) this.level, true);

        if (this.isAngry())
            this.lastHurtByPlayerTime = this.tickCount;

        super.customServerAiStep();
    }

    public boolean hasEyesClosed() {
        return this.isBlinking || !this.isNoEndimationPlaying();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount % 1000 == 0)
            this.isBlinking = true;

        if (this.isBlinking && this.blinkDelay < 3)
            this.blinkDelay++;
        else {
            this.isBlinking = false;
            this.blinkDelay = 0;
        }

        if (!this.isNoAi()) {
            boolean isShaking = this.isEndimationPlaying(WindsweptPlayableEndimations.FROSTBITER_SHAKE);

            if (isShaking)
                this.dropDelay++;
            else
                this.dropDelay = 0;

            if (this.hasAntlers())
                if (this.dropDelay == 0 && this.random.nextInt(1000) == 0 && this.isNoEndimationPlaying())
                    NetworkUtil.setPlayingAnimation(this, WindsweptPlayableEndimations.FROSTBITER_SHAKE);
                else if (this.dropDelay == 8 && isShaking)
                    this.dropRandomAntler();
        }

    }

    public void setLeftAntler(boolean has) {
        this.entityData.set(LEFT_ANTLER, has);
    }

    public void setRightAntler(boolean has) {
        this.entityData.set(RIGHT_ANTLER, has);
    }

    public boolean hasLeftAntler() {
        return this.entityData.get(LEFT_ANTLER) && !this.isBaby();
    }

    public boolean hasRightAntler() {
        return this.entityData.get(RIGHT_ANTLER) && !this.isBaby();
    }

    public boolean hasAntlers() {
        return(this.entityData.get(LEFT_ANTLER) || this.entityData.get(RIGHT_ANTLER)) && !this.isBaby();
    }

    private void dropRandomAntler() {
        if (this.hasAntlers()) {
            if (this.random.nextBoolean())
                this.setLeftAntler(false);
            else
                this.setRightAntler(false);
        } else if (this.hasRightAntler())
            this.setRightAntler(false);
        else if (this.hasLeftAntler())
            this.setLeftAntler(false);

        Block.popResourceFromFace(this.level, this.blockPosition(), this.getDirection(), WindsweptItems.FROZEN_BRANCH.get().getDefaultInstance());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.is(Items.BUCKET) && !this.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1f, 1f);
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, Items.MILK_BUCKET.getDefaultInstance()));

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (!this.isTame() && stack.is(WindsweptItems.HOLLY_BERRIES.get())) {
            if (this.random.nextInt(10) == 0) {
                this.setTame(true);
                this.setOwnerUUID(player.getUUID());
                this.usePlayerItem(player, hand, stack);
                this.navigation.stop();
                this.level.broadcastEntityEvent(this, (byte) 7);

                //this.setOrderedToSit(true);
            } else {
                this.usePlayerItem(player, hand, stack);
                this.level.broadcastEntityEvent(this, (byte) 6);
            }

            return InteractionResult.SUCCESS;
        }


        return super.mobInteract(player, hand);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isBaby() ? SoundEvents.POLAR_BEAR_AMBIENT_BABY : SoundEvents.POLAR_BEAR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.POLAR_BEAR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.POLAR_BEAR_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.POLAR_BEAR_STEP, .15f, 1f);
    }

    @Override
    public float getVoicePitch() {
        return this.isBaby() ? 1.4f : .4f;
    }

    @Override
    protected float getSoundVolume() {
        return this.isBaby() ? .8f : 1f;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return this.isTame();
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        Frostbiter biter = WindsweptEntities.FROSTBITER.get().create(level);

        UUID uuid = this.getOwnerUUID();
        if (uuid != null) {
            biter.setOwnerUUID(uuid);
            biter.setTame(true);
        }

        return biter;
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
                .add(Attributes.MOVEMENT_SPEED, .22f)
                .add(Attributes.ATTACK_KNOCKBACK, 1.2f);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int time) {
        this.entityData.set(ANGER_TIME, time);
    }

    @Override
    public UUID getPersistentAngerTarget() {
        return this.lastHurtBy;
    }

    @Override
    public void setPersistentAngerTarget(UUID target) {
        this.lastHurtBy = target;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(ANGER_RANGE.sample(this.random));
    }

    public class FrostbiterPanicGoal extends PanicGoal {
        public FrostbiterPanicGoal() {
            super(Frostbiter.this, 2.2d);
        }

        @Override
        protected boolean shouldPanic() {
            return this.mob.getLastHurtByMob() != null && this.mob.isBaby() || this.mob.isOnFire();
        }

    }

    public class FrostbiterMeleeAttackGoal extends MeleeAttackGoal {

        public FrostbiterMeleeAttackGoal() {
            super(Frostbiter.this, 1.2f, false);
        }

        @Override
        public boolean canUse() {
            return !this.mob.isBaby() && super.canUse();
        }

    }

}

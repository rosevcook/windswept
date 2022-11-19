package com.rosemods.windswept.common.block.stinging_nettles;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;

import java.util.function.Supplier;

public class StingingNettleBlock extends BushBlock implements IForgeShearable, BonemealableBlock {
    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(Items.DEAD_BUSH::asItem);
    private static final VoxelShape AABB = Block.box(2f, 0f, 2f, 14f, 14f, 14f);

    private final Supplier<Block> tall;

    public StingingNettleBlock(Supplier<Block> tall, Properties properties) {
        super(properties);
        this.tall = tall;
    }

    public static void entityInside(BlockState state, Level level, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            if (entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE)
                entity.makeStuckInBlock(state, new Vec3(.5f, .5f, .5f));

            if (!level.isClientSide && level.getDifficulty() != Difficulty.PEACEFUL)
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 40));
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entityInside(state, level, entity);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        FILLER.fillItem(this.asItem(), group, items);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextInt(2) == 0;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        DoublePlantBlock.placeAt(level, this.tall.get().defaultBlockState(), pos, 2);
    }
}

package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.tag.ModTags;

public class ChristmasPuddingBlock extends Block {
    public static final EnumProperty<PuddingStates> STATE = EnumProperty.create("state", PuddingStates.class);
    private static final VoxelShape[] SHAPE_BY_BITES = {
            Block.box(2f, 0f, 2f, 8f, 8f, 8f),
            Block.box(2f, 0f, 2f, 8f, 8f, 14f),
            Shapes.join(
                    Block.box(2f, 0f, 2f, 8f, 8f, 14f),
                    Block.box(8f, 0f, 8f, 14f, 8f, 14f),
                    BooleanOp.OR),
            Block.box(2f, 0f, 2f, 14f, 8f, 14f)
    };

    public ChristmasPuddingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STATE, PuddingStates.FOUR));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_BITES[state.getValue(STATE).getIndex()];
    }

    private void takeOneSlice(BlockState state, Player player, Level level, BlockPos pos) {
        switch (state.getValue(STATE)) {
            case FOUR -> level.setBlock(pos, state.setValue(STATE, PuddingStates.THREE), 3);
            case THREE -> level.setBlock(pos, state.setValue(STATE, PuddingStates.TWO), 3);
            case TWO -> level.setBlock(pos, state.setValue(STATE, PuddingStates.ONE), 3);
            case ONE -> {
                level.removeBlock(pos, false);
                level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
                popResourceFromFace(level, pos.offset(.5f, -.5f, .5f), Direction.UP, WindsweptItems.HOLLY_BERRIES.get().getDefaultInstance());
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack stack = player.getItemInHand(hand);
        PuddingStates puddingState = state.getValue(STATE);

        if (stack.is(Items.FLINT_AND_STEEL) && puddingState == PuddingStates.FOUR) {
            level.setBlock(pos, state.setValue(STATE, PuddingStates.FIRE), 3);
            level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1f, level.getRandom().nextFloat() * .4f + .8f);

            if (!level.isClientSide && !player.getAbilities().instabuild)
                stack.hurt(1, level.getRandom(), (ServerPlayer) player);

            return InteractionResult.SUCCESS;
        } else if (puddingState != PuddingStates.FIRE && stack.is(WindsweptItemTags.KNIVES)) {
            takeOneSlice(state, player, level, pos);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);

            popResourceFromFace(level, pos.offset(.5f, -.5f, .5f), Direction.UP,
                    WindsweptItems.CHRISTMAS_PUDDING_SLICE.get().getDefaultInstance());
            return InteractionResult.SUCCESS;
        } else if (puddingState != PuddingStates.FIRE && player.canEat(false)) {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(2, .1f);
            player.addEffect(new MobEffectInstance(WindsweptEffects.THORNS.get(), 400, 0));
            level.gameEvent(player, GameEvent.EAT, pos);
            takeOneSlice(state, player, level, pos);

            return InteractionResult.SUCCESS;
        } else if (puddingState == PuddingStates.FIRE && stack.isEmpty()) {
            level.setBlock(pos, state.setValue(STATE, PuddingStates.FOUR), 3);
            level.playSound(player, pos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1f, 1f);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).getMaterial().isSolid();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return this.canSurvive(state, level, currentPos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    public enum PuddingStates implements StringRepresentable {
        FIRE("fire"),
        FOUR("4"),
        THREE("3"),
        TWO("2"),
        ONE("1");

        private final String name;

        PuddingStates(String name) {
            this.name = name;
        }

        public int getIndex() {
            return this == PuddingStates.FIRE ? 3 : Integer.parseInt(this.getSerializedName()) - 1;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }

    }

}

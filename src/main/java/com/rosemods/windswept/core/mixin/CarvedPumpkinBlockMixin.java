package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin {
    @Inject(method = "canSpawnGolem", at = @At("RETURN"), cancellable = true)
    private void canSpawnGolem(LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        if (level.getBlockState(pos.below()).is(WindsweptBlocks.PINECONE_BLOCK.get()) && ModList.get().isLoaded("environmental"))
            info.setReturnValue(true);
    }

    @Inject(method = "trySpawnGolem", at = @At("HEAD"), cancellable = true)
    private void trySpawnGolem(Level level, BlockPos pos, CallbackInfo info) {
        BlockPos belowpos = pos.below();
        BlockState belowstate = level.getBlockState(belowpos);

        if (belowstate.is(WindsweptBlocks.PINECONE_BLOCK.get()) && ModList.get().isLoaded("environmental")) {
            BlockState state = level.getBlockState(pos);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            level.setBlock(belowpos, Blocks.AIR.defaultBlockState(), 2);
            level.levelEvent(2001, pos, Block.getId(state));
            level.levelEvent(2001, belowpos, Block.getId(belowstate));

            LivingEntity pineconegolem = (LivingEntity) ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation("environmental", "pinecone_golem")).create(level);

            if (pineconegolem != null) {
                float yRot = state.getValue(CarvedPumpkinBlock.FACING).toYRot();
                pineconegolem.moveTo((double) belowpos.getX() + .5D, (double) belowpos.getY() + .05D, (double) belowpos.getZ() + .5D);
                pineconegolem.yHeadRot = yRot;
                pineconegolem.yBodyRot = yRot;
                level.addFreshEntity(pineconegolem);

                for (ServerPlayer serverplayer : level.getEntitiesOfClass(ServerPlayer.class, pineconegolem.getBoundingBox().inflate(5.0D)))
                    CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, pineconegolem);

                level.blockUpdated(pos, Blocks.AIR);
                level.blockUpdated(belowpos, Blocks.AIR);

                info.cancel();
            }
        }
    }

}

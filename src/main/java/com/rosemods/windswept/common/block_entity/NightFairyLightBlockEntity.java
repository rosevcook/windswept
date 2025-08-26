package com.rosemods.windswept.common.block_entity;

import com.rosemods.windswept.common.block.NightFairyLightBlock;
import com.rosemods.windswept.core.registry.WindsweptBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NightFairyLightBlockEntity extends BlockEntity {
    public NightFairyLightBlockEntity(BlockPos pos, BlockState state) {
        super(WindsweptBlockEntities.NIGHT_FAIRY_LIGHT.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, NightFairyLightBlockEntity entity) {
        if (level.getGameTime() % 5f == level.random.nextInt(4)) {
            boolean lit = !level.isDay() && level.dimensionType().hasSkyLight();

            if (state.getValue(NightFairyLightBlock.LIT) != lit)
                level.setBlock(pos, state.setValue(NightFairyLightBlock.LIT, lit), 3);
        }
    }

}

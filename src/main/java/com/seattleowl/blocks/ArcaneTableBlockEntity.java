package com.seattleowl.blocks;

import com.seattleowl.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ArcaneTableBlockEntity extends BlockEntity {
    public ArcaneTableBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.ARCANE_TABLE_BLOCK_ENTITY.get(), pos, state);
    }


}

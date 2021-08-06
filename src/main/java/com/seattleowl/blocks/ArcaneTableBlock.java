package com.seattleowl.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class ArcaneTableBlock extends Block implements EntityBlock {
    public ArcaneTableBlock() {
        super(Properties.of(Material.STONE)
                .sound(SoundType.DEEPSLATE)
                .strength(2.0f));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ArcaneTableBlockEntity(pos, state);
    }
}
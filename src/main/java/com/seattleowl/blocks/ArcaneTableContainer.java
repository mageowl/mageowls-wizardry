package com.seattleowl.blocks;

import com.seattleowl.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.List;

public class ArcaneTableContainer extends AbstractContainerMenu {
    private BlockEntity blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;

    public ArcaneTableContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registration.ARCANE_TABLE_CONTAINER.get(), windowId);
        blockEntity = world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if (blockEntity != null) {
            ArcaneTableBlockEntity table = (ArcaneTableBlockEntity) blockEntity;
            table.container = this;
            addSlot(new SlotItemHandler(table.wandSlot, 0, 48, 37));
            addSlot(new SlotItemHandler(table.energySlot, 0, 132, 37));
            addSlot(new SlotItemHandler(table.spellSlots, 0, 48, 10));
            addSlot(new SlotItemHandler(table.spellSlots, 1, 75, 28));
            addSlot(new SlotItemHandler(table.spellSlots, 2, 67, 60));
            addSlot(new SlotItemHandler(table.spellSlots, 3, 30, 60));
            addSlot(new SlotItemHandler(table.spellSlots, 1, 21, 28));
        }
        layoutPlayerInventorySlots(8, 91);
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }


    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, Registration.ARCANE_TABLE.get());
    }
}

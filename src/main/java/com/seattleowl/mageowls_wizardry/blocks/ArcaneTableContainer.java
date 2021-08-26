package com.seattleowl.mageowls_wizardry.blocks;

import com.seattleowl.mageowls_wizardry.items.SpellItem;
import com.seattleowl.mageowls_wizardry.items.WandItem;
import com.seattleowl.mageowls_wizardry.MageowlsWizardry;
import com.seattleowl.mageowls_wizardry.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public class ArcaneTableContainer extends AbstractContainerMenu {
    private final Player playerEntity;
    private final IItemHandler playerInventory;
    private final BlockPos blockPos;
    private final Level world;

    private final ItemStackHandler wandSlot = createWandHandler();
    private final ItemStackHandler spellSlots = createSpellsHandler();
    private final ItemStackHandler wandOutputSlot = new ItemStackHandler(1);
    private final ItemStackHandler outputDisplaySlot = createOutputHandler(wandOutputSlot);

    private boolean hasWand = false;
    private int manaConsumption = 0;
    private boolean extractingOutput = false;

    private ItemStackHandler createWandHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                hasWand = getSlot(slot).hasItem();
                updateOutput();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() instanceof WandItem;
            }
        };
    }

    private ItemStackHandler createSpellsHandler() {
        return new ItemStackHandler(5) {
            @Override
            protected void onContentsChanged(int slot) {
                updateOutput();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
              return stack.getItem() instanceof SpellItem;
            }
        };
    }

    private ItemStackHandler createOutputHandler(ItemStackHandler internalSlot) {
        return new ItemStackHandler(1) {
            @Override
            public void setStackInSlot(int slot, ItemStack stack) {
                internalSlot.setStackInSlot(slot, stack);
            }

            @Override
            public int getSlots() {
                return internalSlot.getSlots();
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return internalSlot.getStackInSlot(slot);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                return stack;
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                extractingOutput = true;
                wandSlot.extractItem(0, 1, false);
                extractingOutput = false;
                return internalSlot.extractItem(slot, amount, simulate);
            }
        };
    }

    private void updateOutput() {
        if (extractingOutput) return;

        if (hasWand) {
            wandOutputSlot.insertItem(0, wandSlot.getStackInSlot(0).copy(), false);

            ItemStack wandStack = wandOutputSlot.getStackInSlot(0);
            WandItem wandItem = (WandItem) wandStack.getItem();

            // Apply spells
            // For each spell
            for (int i = 0; i < 5; i++) {
                ItemStack spellStack = spellSlots.getStackInSlot(i);

                // If there is a spell
                if (!spellStack.isEmpty()) {
                    SpellItem spellItem = (SpellItem) spellStack.getItem();

                    // Apply it
                    wandItem.applySpell(wandStack, spellItem.getSpellID(spellStack), i);
                }
            }
        } else {
            wandOutputSlot.extractItem(0, 1, false);
        }
    }

    public ArcaneTableContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registration.ARCANE_TABLE_CONTAINER.get(), windowId);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        blockPos = pos;
        world = level;

        addSlot(new SlotItemHandler(wandSlot, 0, 48, 37));
        addSlot(new SlotItemHandler(spellSlots, 0, 48, 10));
        addSlot(new SlotItemHandler(spellSlots, 1, 75, 28));
        addSlot(new SlotItemHandler(spellSlots, 2, 67, 60));
        addSlot(new SlotItemHandler(spellSlots, 3, 30, 60));
        addSlot(new SlotItemHandler(spellSlots, 4, 21, 28));
        addSlot(new SlotItemHandler(outputDisplaySlot, 0, 138, 37));

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
        return stillValid(ContainerLevelAccess.create(world, blockPos), playerEntity, Registration.ARCANE_TABLE.get());
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        if (!player.isAlive() || player instanceof ServerPlayer && ((ServerPlayer)player).hasDisconnected()) {
            player.drop(wandSlot.getStackInSlot(0), false);
            for (int i = 0; i < 5; i++) {
                player.drop(spellSlots.getStackInSlot(i), false);
            }

        } else {
            if (player instanceof ServerPlayer) {
                Inventory inventory = player.getInventory();
                inventory.placeItemBackInInventory(wandSlot.getStackInSlot(0));
                for (int i = 0; i < 5; i++) {
                    inventory.placeItemBackInInventory(spellSlots.getStackInSlot(i));
                }
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotID) {
        return super.quickMoveStack(player, slotID);
    }
}

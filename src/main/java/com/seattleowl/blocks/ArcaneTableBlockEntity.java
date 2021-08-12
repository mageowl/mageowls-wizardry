package com.seattleowl.blocks;

import com.seattleowl.items.WandItem;
import com.seattleowl.mageowls_wizardry.MageowlsWizardry;
import com.seattleowl.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ArcaneTableBlockEntity extends BlockEntity {

    public final ItemStackHandler wandSlot = createWandHandler();
    public final ItemStackHandler energySlot = createManaFuelHandler();
    public final ItemStackHandler spellSlots = createSpellsHandler();

    private final LazyOptional<IItemHandler> wand = LazyOptional.of(() -> wandSlot);
    private final LazyOptional<IItemHandler> energy = LazyOptional.of(() -> energySlot);
    private final LazyOptional<IItemHandler> spells = LazyOptional.of(() -> spellSlots);

    public ArcaneTableContainer container = null;
    private boolean hasWand = false;

    public ArcaneTableBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.ARCANE_TABLE_BLOCK_ENTITY.get(), pos, state);
    }

    private ItemStackHandler createWandHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                hasWand = !getStackInSlot(slot).isEmpty();
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() instanceof WandItem;
            }
        };
    }

    private ItemStackHandler createManaFuelHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return ItemTags
                        .getAllTags()
                        .getTag(
                                new ResourceLocation(MageowlsWizardry.MODID, "mana_fuel")).contains(stack.getItem()
                        );
            }
        };
    }

    private ItemStackHandler createSpellsHandler() {
        return new ItemStackHandler(5) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
//              return ItemTags
//                        .getAllTags()
//                        .getTag(
//                                new ResourceLocation(MageowlsWizardry.MODID, "mana_fuel")).contains(stack.getItem()
//                        );
                return true;
            }
        };
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("wand", wandSlot.serializeNBT());
        tag.put("energy", energySlot.serializeNBT());
        tag.put("spells", spellSlots.serializeNBT());

        return super.save(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        wandSlot.deserializeNBT(tag.getCompound("wand"));
        energySlot.deserializeNBT(tag.getCompound("energy"));
        spellSlots.deserializeNBT(tag.getCompound("spells"));

        super.load(tag);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        wand.invalidate();
        energy.invalidate();
        spells.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.UP) {
                if (hasWand) return spells.cast();
                else return wand.cast();
            } else if (side == Direction.DOWN) {
                return wand.cast();
            } else {
                return energy.cast();
            }
        }
        return super.getCapability(cap, side);
    }
}

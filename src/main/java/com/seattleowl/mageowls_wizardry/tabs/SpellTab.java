package com.seattleowl.mageowls_wizardry.tabs;

import com.seattleowl.mageowls_wizardry.setup.Registration;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SpellTab extends CreativeModeTab {
    public SpellTab() {
        super("spell_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Registration.SPELL_BOOK::get);
    }

    private ItemStack createSpellBook(String spell) {
        ItemStack stack = new ItemStack(Registration.SPELL_BOOK::get);
        stack.getOrCreateTag().putString("spell", spell);
        return stack;
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        items.add(createSpellBook("magic_missile"));
        items.add(createSpellBook("ignite"));
        super.fillItemList(items);
    }
}

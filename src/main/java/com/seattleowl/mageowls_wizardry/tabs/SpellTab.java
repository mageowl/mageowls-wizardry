package com.seattleowl.mageowls_wizardry.tabs;

import com.seattleowl.mageowls_wizardry.setup.Registration;
import com.seattleowl.mageowls_wizardry.tools.SpellRegister;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.Set;

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
        Set<String> spells = SpellRegister.updateGlobalSpells().keySet();

        for (String spell: spells) {
            items.add(createSpellBook(spell));
        }

        super.fillItemList(items);
    }
}

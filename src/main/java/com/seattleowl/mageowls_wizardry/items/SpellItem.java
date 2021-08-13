package com.seattleowl.mageowls_wizardry.items;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SpellItem extends Item {
    public SpellItem(Properties properties) {
        super(properties.stacksTo(1).tab(CreativeModeTab.TAB_COMBAT));
    }

    public String getSpellID(ItemStack stack) {
        return stack.getOrCreateTag().getString("spell");
    }
}

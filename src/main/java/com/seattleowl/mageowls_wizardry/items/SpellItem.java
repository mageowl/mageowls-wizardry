package com.seattleowl.mageowls_wizardry.items;

import com.seattleowl.mageowls_wizardry.tools.SpellRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class SpellItem extends Item {
    public SpellItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    public String getSpellID(ItemStack stack) {
        return stack.getOrCreateTag().getString("spell");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);

        SpellRegister.RegisteredSpell registeredSpell = SpellRegister.get(getSpellID(stack));
        list.add(new TranslatableComponent("spell." + registeredSpell.modID + "." + registeredSpell.name).withStyle(ChatFormatting.BLUE));
    }
}

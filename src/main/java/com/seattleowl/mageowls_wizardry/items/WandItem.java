package com.seattleowl.mageowls_wizardry.items;

import com.seattleowl.mageowls_wizardry.tools.Spell;
import com.seattleowl.mageowls_wizardry.tools.SpellRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class WandItem extends Item {
	private int maxMana;

	public WandItem(int maxMana, Properties properties) {
		super(properties.stacksTo(1).tab(CreativeModeTab.TAB_COMBAT));
		this.maxMana = maxMana;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
		super.appendHoverText(stack, level, list, flags);

		ListTag spells = stack.getOrCreateTag().getList("spells", Tag.TAG_STRING);
		boolean addSpells = spells.size() == 0;
		for (int i = 0; i < 5; i++) {
			if (addSpells) {
				spells.add(StringTag.valueOf("none"));
			}

			String spellID = spells.getString(i);
			if (!spellID.equals("none")) {

				SpellRegister.RegisteredSpell registeredSpell = SpellRegister.get(spellID);
				Spell spell = registeredSpell.spell;

				list.add(
						new TextComponent("[" + spell.element.icon + "] ").withStyle(spell.element.formatting).append(
								new TranslatableComponent(
										"spell." + registeredSpell.modID + "." + registeredSpell.name
								)
						)
				);
			} else {
				list.add(
					new TextComponent("[  ] Empty Slot").withStyle(ChatFormatting.GRAY)
				);
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		// TODO: Cast spell
		return super.use(level, player, hand);
	}

	public void applySpell(ItemStack stack, String spellID, int slot) {
		ListTag spells = stack.getOrCreateTag().getList("spells", Tag.TAG_STRING);
		StringTag spellIDTag = StringTag.valueOf(spellID);

		if (spells.size() == 0) {
			spells.add(StringTag.valueOf("none"));
			spells.add(StringTag.valueOf("none"));
			spells.add(StringTag.valueOf("none"));
			spells.add(StringTag.valueOf("none"));
			spells.add(StringTag.valueOf("none"));
		}

		spells.set(slot, spellIDTag);
		stack.getTag().put("spells", spells);
	}
}
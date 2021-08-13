package com.seattleowl.mageowls_wizardry.items;

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
		list.add(
			new TranslatableComponent(
				"message.mageowls_wizardry.mana", getMana(stack) + " / " + getMaxMana(stack)
			).withStyle(ChatFormatting.GRAY)
		);

		ListTag spells = stack.getOrCreateTag().getList("spells", Tag.TAG_STRING);
		for (int i = 0; i < 5; i++) {
			String spellID = spells.getString(i);
			list.add(
				new TextComponent(" " + "" + " ").append(new TranslatableComponent("spell." + spellID))
			);
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
//		int spellIndex = stack.getOrCreateTag().getInt("spell");
//		spellIndex = (spellIndex + 1) % 5;
//		stack.getTag().putInt("spell", spellIndex);
		// TODO: Cast spell
		return super.use(level, player, hand);
	}

	public int getMana(ItemStack stack) {
		return Math.min(stack.getOrCreateTag().getInt("mana"), getMaxMana(stack));
	}

	public int getMaxMana(ItemStack stack) {
		return stack.hasTag() && stack.getTag().getInt("max_mana") > 0 ?
				stack.getTag().getInt("max_mana") :
				maxMana;
	}

	public boolean hasMana(ItemStack stack) {
		return getMana(stack) > 0;
	}

	public int addMana(ItemStack stack, int mana) {
		int sum = getMana(stack) + mana;
		stack.getOrCreateTag().putInt("mana", sum);
		return sum - getMana(stack);
	}

	public double getDurabilityForDisplay(ItemStack stack) {
		int mana = getMana(stack);
		int maxMana = getMaxMana(stack);
		double manaPercent = ((double) mana / (double) maxMana);
		return 1 - manaPercent;
	}

	public int getRGBDurabilityForDisplay(ItemStack _stack) {
		return 0x007357a6;
	}

	public boolean showDurabilityBar(ItemStack stack) {
		return getMana(stack) < getMaxMana(stack);
	}

	public void applySpell(ItemStack stack, String spellID, int slot) {
		stack.getOrCreateTag().getList("spells", Tag.TAG_STRING).set(slot, StringTag.valueOf(spellID));
	}
}
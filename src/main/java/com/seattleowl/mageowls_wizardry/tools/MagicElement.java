package com.seattleowl.mageowls_wizardry.tools;

import net.minecraft.ChatFormatting;

public enum MagicElement {
    NONE("\u2B50", ChatFormatting.WHITE),
    FIRE("\uD83D", ChatFormatting.RED),
    SUN("\u2600", ChatFormatting.AQUA),
    WATER("\uDF0A", ChatFormatting.BLUE),
    EARTH("\uD83E", ChatFormatting.GREEN),
    MOON("\uD83C", ChatFormatting.DARK_PURPLE);

    public final String icon;
    public final ChatFormatting formatting;

    MagicElement(String emoji, ChatFormatting color) {
        this.icon = emoji;
        this.formatting = color;
    }
}
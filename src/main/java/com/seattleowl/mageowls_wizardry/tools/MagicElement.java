package com.seattleowl.mageowls_wizardry.tools;

public enum MagicElement {
    NONE("⭐️"),
    STORM("⚡️"),
    WATER("💧"),
    EARTH("🪨"),
    FIRE("🔥"),
    NECRO("💀"),
    UTIL("✏️");

    public final String icon;

    private MagicElement(String emoji) {
        this.icon = emoji;
    }
}


package com.seattleowl.mageowls_wizardry.tools;

import net.minecraft.world.entity.player.Player;

public abstract class Spell {
    public abstract void cast(Player player);

    MagicElement element;
    String displayName;
    int manaCost;

}
package com.seattleowl.mageowls_wizardry.tools;

import net.minecraft.world.entity.player.Player;

public interface Spell {
    void cast(Player player);

    MagicElement element = MagicElement.NONE;
    int manaCost = 0;

}
package com.seattleowl.mageowls_wizardry.spells;

import com.seattleowl.mageowls_wizardry.tools.MagicElement;
import com.seattleowl.mageowls_wizardry.tools.Spell;
import net.minecraft.world.entity.player.Player;

public class MagicMissile implements Spell {
    public MagicElement element = MagicElement.NONE;
    public int manaCost = 10;

    @Override
    public void cast(Player player) {
        System.out.println("magic missile casted!");
    }
}

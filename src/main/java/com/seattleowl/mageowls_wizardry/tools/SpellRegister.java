package com.seattleowl.mageowls_wizardry.tools;

import java.util.HashMap;
import java.util.Map;

public class SpellRegister {
    static private HashMap<String, SpellRegister> spellRegisters = new HashMap();
    static private HashMap<String, RegisteredSpell> globalSpells = new HashMap();
    private HashMap<String, RegisteredSpell> spells = new HashMap();

    static public class RegisteredSpell {
        public Spell spell;
        public String name;
        public String modID;

        private RegisteredSpell(Spell spell, String name, String modID) {
            this.spell = spell;
            this.name = name;
            this.modID = modID;
        }
    }

    private final String modID;

    public SpellRegister(String modId) {
        modID = modId;
        spellRegisters.put(modId, this);
    }

    public RegisteredSpell register(String name, Spell spell) {
        RegisteredSpell registeredSpell = new RegisteredSpell(spell, name, modID);
        spells.put(name, registeredSpell);
        return registeredSpell;
    }

    static public RegisteredSpell get(String name) {
        RegisteredSpell spell = globalSpells.get(name);
        if (spell == null) {
            return updateGlobalSpells().get(name);
        }
        return spell;
    }

    static public HashMap<String, RegisteredSpell> updateGlobalSpells() {
        globalSpells.clear();
        for (Map.Entry<String, SpellRegister> keyValue: spellRegisters.entrySet()) {
            for (Map.Entry<String, RegisteredSpell> spell: keyValue.getValue().spells.entrySet()) {
                globalSpells.put(keyValue.getKey() + ":" + spell.getKey(), spell.getValue());
            }
        }
        return globalSpells;
    }
}

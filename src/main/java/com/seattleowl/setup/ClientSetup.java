package com.seattleowl.setup;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

//    public static final ResourceLocation MANA_PROPERTY = new ResourceLocation(MageowlsWizardry.MODID, "mana");

    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
//            initWandItemOverrides();
        });
    }
//
//    public static void initWandItemOverrides() {
//        WandItem item = Registration.WANDITEM.get();
//        ItemProperties.register(item, MANA_PROPERTY,
//                (stack, level, entity, damage) -> item.getMana(stack));
//    }
}

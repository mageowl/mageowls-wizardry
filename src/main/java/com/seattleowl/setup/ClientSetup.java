package com.seattleowl.setup;

import com.seattleowl.blocks.ArcaneTableScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.ARCANE_TABLE_CONTAINER.get(), ArcaneTableScreen::new);
        });
    }
}

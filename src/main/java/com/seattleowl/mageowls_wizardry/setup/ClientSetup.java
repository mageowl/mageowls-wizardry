package com.seattleowl.mageowls_wizardry.setup;

import com.seattleowl.mageowls_wizardry.blocks.ArcaneTableScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.ARCANE_TABLE_CONTAINER.get(), ArcaneTableScreen::new);
        });
    }
}

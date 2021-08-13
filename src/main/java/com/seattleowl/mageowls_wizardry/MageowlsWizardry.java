package com.seattleowl.mageowls_wizardry;

import com.seattleowl.mageowls_wizardry.setup.ClientSetup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.seattleowl.mageowls_wizardry.setup.Registration;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MageowlsWizardry.MODID)
public class MageowlsWizardry {

	public static final String MODID = "mageowls_wizardry";

	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	public MageowlsWizardry() {;
		Registration.init();

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
		bus.addListener(ClientSetup::setup);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}
}

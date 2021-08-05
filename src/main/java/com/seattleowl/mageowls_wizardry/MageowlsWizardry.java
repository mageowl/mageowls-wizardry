package com.seattleowl.mageowls_wizardry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.seattleowl.setup.Registration;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MageowlsWizardry.MODID)
public class MageowlsWizardry {

	public static final String MODID = "mageowls_wizardry";

	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	public MageowlsWizardry() {;
		Registration.init();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}
}

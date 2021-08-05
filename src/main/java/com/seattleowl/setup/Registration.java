package com.seattleowl.setup;

import com.seattleowl.items.WandItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.seattleowl.mageowls_wizardry.MageowlsWizardry.MODID;

public class Registration {

	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<Item> WANDITEM = ITEMS.register("wand", () -> new WandItem(new Item.Properties()));
}
package com.seattleowl.setup;

import com.seattleowl.blocks.ArcaneTableBlock;
import com.seattleowl.blocks.ArcaneTableBlockEntity;
import com.seattleowl.blocks.ArcaneTableContainer;
import com.seattleowl.items.WandItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.seattleowl.mageowls_wizardry.MageowlsWizardry.MODID;

public class Registration {

	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final  DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
	private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);


	public static void init() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(bus);
		BLOCKS.register(bus);
		BLOCK_ENTITIES.register(bus);
		CONTAINERS.register(bus);
	}

	public static final RegistryObject<WandItem> WAND_ITEM = ITEMS.register("wand", () ->
		new WandItem(100, new Item.Properties())
	);

	public static final RegistryObject<Item> MAGIC_CRYSTAL = ITEMS.register("magic_crystal", () ->
			new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC))
	);

	public static final RegistryObject<ArcaneTableBlock> ARCANE_TABLE =
			BLOCKS.register("arcane_table", ArcaneTableBlock::new);
	public static final RegistryObject<Item> ARCANE_TABLE_ITEM = ITEMS.register("arcane_table",
			() -> new BlockItem(ARCANE_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final  RegistryObject<BlockEntityType<ArcaneTableBlockEntity>> ARCANE_TABLE_BLOCK_ENTITY = BLOCK_ENTITIES.register("arcane_table",
			() -> BlockEntityType.Builder.of(ArcaneTableBlockEntity::new, ARCANE_TABLE.get()).build(null));
	public static final RegistryObject<MenuType<ArcaneTableContainer>> ARCANE_TABLE_CONTAINER = CONTAINERS.register("arcane_table", () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		Level world = inv.player.getCommandSenderWorld();
		return new ArcaneTableContainer(windowId, world, pos, inv, inv.player);
	}));
}
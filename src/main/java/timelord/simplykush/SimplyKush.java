package timelord.simplykush;

import net.minecraft.block.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.*;
import timelord.simplykush.block.ModBlocks;
import timelord.simplykush.item.ModItems;

import java.util.UUID;
import java.util.stream.Collectors;


@Mod(SimplyKush.MODID)
@Mod.EventBusSubscriber(modid = SimplyKush.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SimplyKush {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static final String MODID = "simplykush";
	
	public static final String NAME = "Simply Kush";
	
	public static final ItemGroupSimplyKush TAB = new ItemGroupSimplyKush();
	
	public static final ItemGroupSimplyKush ITEM_GROUP = new ItemGroupSimplyKush();
	
	
	public SimplyKush () {
		//SimplyKushConfig.register(ModLoadingContext.get());
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueue);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::process);
		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::client);
		
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		
		ModBlocks.initialize(modEventBus);
		ModItems.initialize(modEventBus);
		//ModCrafting.Recipes.initialize(modEventBus);
		
		/**
		 ModFluids.initialise(modEventBus);
		 ModBiomes.initialise(modEventBus);
		 ModContainerTypes.initialise(modEventBus);
		 ModEffects.initialise(modEventBus);
		 ModEntities.initialise(modEventBus);
		 ModFeatures.initialise(modEventBus);
		 ModLootModifierSerializers.initialise(modEventBus);
		 ModPlacements.initialise(modEventBus);
		 ModPotions.initialise(modEventBus);
		 ModCrafting.Recipes.initialise(modEventBus);
		 ModSoundEvents.initialise(modEventBus);
		 ModSurfaceBuilders.initialise(modEventBus);
		 ModTileEntities.initialise(modEventBus);
		 ModTestRegistryEntries.initialise(modEventBus);**/
	}
	
	@SubscribeEvent
	public void commonSetup (final FMLCommonSetupEvent event) {
		// some preinit code
		LOGGER.warn("****************************************");
		LOGGER.warn("Random UUID: {}", UUID.randomUUID().toString());
		LOGGER.warn("****************************************");
		
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
		
		event.enqueueWork(() -> {
			//ModCrafting.Ingredients.register();
			/**
			 ModCriterion.register();
			 ModLootTables.registerLootTables();
			 ModLootConditionTypes.register();
			 ModLootFunctionTypes.register();
			 
			 BlockDumper.dump();
			 Tests.runTests();**/
		});
	}
	
	@SubscribeEvent
	public void enqueue (final InterModEnqueueEvent event) {
		InterModComms.sendTo("simplykush", "helloworld", () -> {
			LOGGER.info("Hello world from the MDK");
			return "Hello world";
		});
	}
	
	private void client (final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		LOGGER.info("Got game version {}", event.getMinecraftSupplier().get().getGame().getVersion());
	}
	
	private void process (final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}", event.getIMCStream().
				map(m -> m.getMessageSupplier().get()).
				collect(Collectors.toList()));
	}
	
	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting (FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}
	
	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry (final RegistryEvent.Register<Block> blockRegistryEvent) {
			// register a new block here
			LOGGER.info("HELLO from Register Block");
		}
	}
}
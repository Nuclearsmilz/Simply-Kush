package timelord.simplykush;

import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.*;
import timelord.simplykush.block.ModBlocks;
import timelord.simplykush.item.ModItems;
import timelord.simplykush.recipe.ModCrafting;

import java.util.UUID;


@Mod(SimplyKush.MODID)
@Mod.EventBusSubscriber(modid = SimplyKush.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SimplyKush {
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static final String MODID = "simplykush";
	
	public static final String NAME = "Simply Kush";
	
	public static final ItemGroupSimplyKush ITEM_GROUP = new ItemGroupSimplyKush();
	
	
	public SimplyKush() {
		//SimplyKushConfig.register(ModLoadingContext.get());
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		
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
	public static void commonSetup(final FMLCommonSetupEvent event) {
		LOGGER.warn("****************************************");
		LOGGER.warn("Random UUID: {}", UUID.randomUUID().toString());
		LOGGER.warn("****************************************");
		
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
	public static void enqueue(final InterModEnqueueEvent event) {

	}
}
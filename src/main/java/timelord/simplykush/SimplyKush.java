package timelord.simplykush;

import io.netty.handler.proxy.HttpProxyHandler;
import net.minecraft.block.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.*;
import timelord.simplykush.block.ModBlocks;
import timelord.simplykush.item.ModItems;
import timelord.simplykush.proxy.*;
import timelord.simplykush.simplykush.ClientEvents;

import java.util.UUID;
import java.util.stream.Collectors;


@Mod(SimplyKush.MODID)
@Mod.EventBusSubscriber(modid = SimplyKush.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SimplyKush {
	
	public static ISidedProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static final String MODID = "simplykush";
	
	public static final String NAME = "Simply Kush";
	
	public static final ItemGroupSimplyKush TAB = new ItemGroupSimplyKush();
	
	public static final ItemGroupSimplyKush ITEM_GROUP = new ItemGroupSimplyKush();
	
	
	public SimplyKush () {
		//Config.register(ModLoadingContext.get());
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueue);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::process);
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
		
		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(new Registry());
		Registry.init();
		proxy.init();
		DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
			MinecraftForge.EVENT_BUS.register(new ClientEvents());
			return new Object();
		});
	}
	
	@SubscribeEvent
	public void setup (final FMLCommonSetupEvent event) {
		LOGGER.warn("******************************************");
		LOGGER.warn("Random UUID: {}", UUID.randomUUID().toString());
		LOGGER.warn("******************************************");
		
		LOGGER.info("RIP LB - SIMPLYKUSH.");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
		
		event.enqueueWork(() -> {
			//ModCrafting.Ingredients.register();
		});
	}
	
	@SubscribeEvent
	public void enqueue (final InterModEnqueueEvent event) {
		InterModComms.sendTo("simplykush", "helloworld", () -> {
			LOGGER.info("Hello world from the MDK");
			return "Hello world";
		});
	}
	
	public static void client (final FMLClientSetupEvent event) {
		LOGGER.info("Got game version {}", event.getMinecraftSupplier().get().getGame().getVersion());
	}
	
	private void process (final InterModProcessEvent event) {
		LOGGER.info("Got IMC {}", event.getIMCStream().
				map(m -> m.getMessageSupplier().get()).
				collect(Collectors.toList()));
	}
	
	@SubscribeEvent
	public void onServerStarting (FMLServerStartingEvent event) {
		LOGGER.info("HELLO from server starting");
	}
	
	
}
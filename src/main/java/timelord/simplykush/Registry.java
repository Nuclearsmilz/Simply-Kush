package timelord.simplykush;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraftforge.accesstransformer.generated.AtParser;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;
import timelord.simplykush.block.BlockBase;

import java.util.*;
import java.util.function.Supplier;

public class Registry {
	
	static Map<String, Block> BLOCK_MAP = new HashMap<>();
	static Map<String, Item> ITEM_MAP = new HashMap<>();
	
	static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SimplyKush.MODID);
	static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimplyKush.MODID);
	
	static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, SimplyKush.MODID);
	static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SimplyKush.MODID);
	
	static DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, SimplyKush.MODID);
	static DeferredRegister<Potion> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTION_TYPES, SimplyKush.MODID);
	
	static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SimplyKush.MODID);
	static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SimplyKush.MODID);
	
	
	static Item.Properties itemProperties () {
		return new Item.Properties().tab(SimplyKush.TAB);
	}
	
	static AbstractBlock.Properties blockProperties (Material material, MaterialColor matColor) {
		return AbstractBlock.Properties.of(material, matColor);
	}
	
	/**
	 * Adding item to registry with just a name
	 *
	 * @param name
	 * 		String name
	 *
	 * @return registers item with name
	 */
	static RegistryObject<Item> addItem (String name) {
		Item item = new Item(itemProperties());
		ITEM_MAP.put(name, item);
		return ITEMS.register(name, () -> item);
	}
	
	/**
	 * Adding item to registry with item and custom property other than tab
	 *
	 * @param name
	 * 		String name
	 * @param properties
	 * 		Item.Properties properties
	 *
	 * @return registers item with name and custom properties
	 */
	static RegistryObject<Item> addItem (String name, Item.Properties properties) {
		Item item = new Item(properties);
		ITEM_MAP.put(name, item);
		return ITEMS.register(name, () -> item);
	}
	
	/**
	 * Adding item to registry with name and passing an Item object
	 *
	 * @param name
	 * 		String name
	 * @param item
	 * 		Item item
	 *
	 * @return registers item with name and item object
	 */
	static RegistryObject<Item> addItem (String name, Item item) {
		ITEM_MAP.put(name, item);
		return ITEMS.register(name, () -> item);
	}
	
	/**
	 * Adding block as BlockItem with name and properties
	 *
	 * @param name
	 * 		String name
	 * @param properties
	 * 		Block.Properties properties
	 *
	 * @return registers block as blockitem with name and custom properties
	 */
	static RegistryObject<Block> addBlock (String name, Block.Properties properties) {
		Block block = new Block(properties);
		BLOCK_MAP.put(name, block);
		ITEMS.register(name, () -> new BlockItem(block, itemProperties()));
		return BLOCKS.register(name, () -> block);
	}
	
	/**
	 * Adding block as BlockItem with name and passing a Block object
	 *
	 * @param name
	 * 		String name
	 * @param block
	 * 		Block block
	 *
	 * @return registers block as blockitem with name and block object
	 */
	static RegistryObject<Block> addBlock (String name, Block block) {
		BLOCK_MAP.put(name, block);
		ITEMS.register(name, () -> new BlockItem(block, itemProperties()));
		return BLOCKS.register(name, () -> block);
	}
	
	/**
	 * Registering/Adding an entity
	 *
	 * @param name
	 * 		String name
	 * @param width
	 * 		float width
	 * @param height
	 * 		float height
	 * @param factory
	 * 		EntityType.IFactory<T> factory
	 * @param kind
	 * 		EntityClassification kind
	 * @param <T>
	 * 		EntityType T
	 *
	 * @return register an entity with name, width, height, and specifying the factory and kind of entity
	 */
	static <T extends Entity> RegistryObject<EntityType<T>> addEntity (String name, float width, float height, EntityType.IFactory<T> factory, EntityClassification kind) {
		EntityType<T> type = EntityType.Builder.<T>of(factory, kind)
				.setTrackingRange(64)
				.setUpdateInterval(1)
				.sized(width, height)
				.build(SimplyKush.MODID + ":" + name);
		return ENTITIES.register(name, () -> type);
	}
	
	/**
	 * Registering/Adding an entity WITH a spawn egg attached to entity.
	 *
	 * @param name
	 * 		String name
	 * @param color1
	 * 		int color1
	 * @param color2
	 * 		int color2
	 * @param width
	 * 		float width
	 * @param height
	 * 		float height
	 * @param factory
	 * 		EntityType.IFactory<T> factory
	 * @param kind
	 * 		EntityClassification kind
	 * @param <T>
	 * 		EntityType T
	 *
	 * @return register an entity with name, width, height, and specifying the factory and kind of entity
	 */
	static <T extends Entity> RegistryObject<EntityType<T>> addEntity (String name, int color1, int color2, float width, float height, EntityType.IFactory<T> factory, EntityClassification kind) {
		EntityType<T> type = EntityType.Builder.<T>of(factory, kind)
				.setTrackingRange(64)
				.setUpdateInterval(1)
				.sized(width, height)
				.build(SimplyKush.MODID + ":" + name);
		ITEMS.register("spawn_" + name, () -> new SpawnEggItem(type, color1, color2, itemProperties().tab(ItemGroup.TAB_MISC)));
		return ENTITIES.register(name, () -> type);
	}
	
	static <T extends TileEntity> TileEntityType<T> addTileEntity (IForgeRegistry<TileEntityType<?>> registry, String name, Supplier<T> factory, Block... blocks) {
		TileEntityType<T> type = TileEntityType.Builder.<T>of(factory, blocks).build(null);
		type.setRegistryName(SimplyKush.MODID, name);
		registry.register(type);
		for (Block block : blocks)
			if (block instanceof BlockBase) {
				((BlockBase) block).setTile(type);
			}
		return type;
	}
	
	static RegistryObject<SoundEvent> addSound (String name) {
		SoundEvent soundEvent = new SoundEvent(new ResourceLocation(SimplyKush.MODID, name));
		return SOUND_EVENTS.register(name, () -> soundEvent);
	}
	
	static <T extends Container> RegistryObject<ContainerType<T>> addContainer (String name, ContainerType.IFactory factory) {
		return CONTAINERS.register(name, () -> new ContainerType<T>(factory));
	}
	
	public static RegistryObject<Item>
			SATIVA_KUSH = addItem("sativa"),
			INDICA_KUSH = addItem("indica"),
			BAGGED_KUSH = addItem("bagged_kush"),
			PLASTIC_BAGGY = addItem("plastic_baggy"),
			SCALE = addItem("scale"),
			ROLLING_TRAY = addItem("rolling_tray"),
			SEEDS_INDICA = addItem("seeds_indica"),
			SEEDS_SATIVA = addItem("seeds_sativa"),
			SEEDS_HEMP = addItem("seeds_hemp");
	
	public static RegistryObject<Block>
			PREPARATION_DESK = addBlock("preparation_desk", blockProperties(Material.METAL, MaterialColor.METAL));
	
	public static void init () {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
		POTION_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
		CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
		
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void clientInit () {
	}
}

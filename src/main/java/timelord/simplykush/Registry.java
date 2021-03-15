package timelord.simplykush;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.*;
import net.minecraftforge.registries.*;

import java.util.*;

public class Registry {
	
	static Map<String, Block>  BLOCK_MAP = new HashMap<>();
	static Map<String, Item>   ITEM_MAP = new HashMap<>();
	static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SimplyKush.MODID);
	static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimplyKush.MODID);
	
	static DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, SimplyKush.MODID);
	static DeferredRegister<Potion> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTION_TYPES, SimplyKush.MODID);
	
	static Item.Properties itemProperties(){
		return new Item.Properties().tab(SimplyKush.TAB);
	}
}

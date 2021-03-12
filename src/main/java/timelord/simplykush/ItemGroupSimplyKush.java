package timelord.simplykush;

import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.*;

public class ItemGroupSimplyKush extends ItemGroup {
	
	private final ItemStack sword;
	
	public ItemGroupSimplyKush(){
		super(SimplyKush.MODID);
		sword = new ItemStack(Items.DIAMOND_SWORD);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public ItemStack makeIcon() {
		return sword;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void fillItemList (NonNullList<ItemStack> items) {
		items.add(sword.copy());
		super.fillItemList(items);
	}
}

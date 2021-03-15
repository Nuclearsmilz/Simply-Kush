package timelord.simplykush;

import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.*;

public class ItemGroupSimplyKush extends ItemGroup {
	
	public ItemGroupSimplyKush () {
		super(SimplyKush.MODID);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public ItemStack makeIcon () {
		return new ItemStack(Registry.BAGGED_KUSH.get(), 1);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void fillItemList (NonNullList<ItemStack> items) {
		super.fillItemList(items);
	}
}

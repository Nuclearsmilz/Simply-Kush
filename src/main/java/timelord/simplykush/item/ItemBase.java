package timelord.simplykush.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.*;

import java.util.List;


public class ItemBase extends Item {
	String stonerTips = null;
	
	public ItemBase(Properties properties){
		super(properties);
	}
	
	public ItemBase setStonerTips(String tag){
		this.stonerTips = tag;
		return this;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag){
		if(this.stonerTips != null){
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("" + TextFormatting.DARK_GREEN + TextFormatting.ITALIC + String.format(this.stonerTips, flag)));
		}
	}
}

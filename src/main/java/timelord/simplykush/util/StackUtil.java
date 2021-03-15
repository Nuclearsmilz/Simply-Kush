package timelord.simplykush.util;

import com.sun.corba.se.spi.copyobject.CopyobjectDefaults;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

import java.util.List;
import java.util.stream.Collectors;

public class StackUtil {
	public static Ingredient ingredientFromObject (Object object) {
		if (object instanceof Item) return Ingredient.of((Item) object);
		else if (object instanceof Block) return Ingredient.of(new ItemStack((IItemProvider) object));
			//else if (object instanceof ItemStack) return Ingredient.of(new ItemStack((ItemStack) object));
		else if (object instanceof ITag) return Ingredient.of((ITag) object);
		else return Ingredient.EMPTY;
	}
	
	public static List<Ingredient> ingredientsFromObjects (List<Object> objects) {
		return objects.stream().map(StackUtil::ingredientFromObject).collect(Collectors.toList());
	}
	
	public static ItemStack stackFromObject (Object object) {
		if (object instanceof Item) return new ItemStack((Item) object);
		else if (object instanceof Block) return new ItemStack((IItemProvider) object);
		else if (object instanceof ItemStack) return ((ItemStack) object).copy();
		else if (object instanceof ITag) {
			Object first = ((ITag) object).getValues().stream().findFirst();
			return stackFromObject(first);
		} else return ItemStack.EMPTY;
	}
	
	public static List<ItemStack> stacksFromObjects (List<Object> objects) {
		return objects.stream().map(StackUtil::stackFromObject).collect(Collectors.toList());
	}
}

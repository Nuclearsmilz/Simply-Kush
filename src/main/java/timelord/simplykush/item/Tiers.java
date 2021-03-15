package timelord.simplykush.item;

import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;

public class Tiers {
	public static class KushTier implements IItemTier {
		
		@Override
		public int getUses () {
			return 420;
		}
		
		@Override
		public float getSpeed () {
			return 4.2f;
		}
		
		@Override
		public float getAttackDamageBonus () {
			return 4.2f;
		}
		
		@Override
		public int getLevel () {
			return 4;
		}
		
		@Override
		public int getEnchantmentValue () {
			return 12; // 42 -> flip to 24 / 2 = 12
		}
		
		@Override
		public Ingredient getRepairIngredient () {
			return null;
		}
	}
}

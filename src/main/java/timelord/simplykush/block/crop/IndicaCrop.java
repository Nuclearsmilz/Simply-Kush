package timelord.simplykush.block.crop;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import timelord.simplykush.Registry;

import java.util.Random;

public class IndicaCrop extends CropBase {
	
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
	private static final AxisAlignedBB[] INDICA_AABB = new AxisAlignedBB[]{
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.755D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.76D, 1.0D) };
	
	public IndicaCrop (Properties properties) {
		super(properties);
	}
	
	public AxisAlignedBB getBoundingBox (BlockState state, IBlockReader source, BlockPos pos) {
		return INDICA_AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
	}
	
	
	protected RegistryObject<Item> getSeed () {
		RegistryObject<Item> seed;
		Random random = new Random();
		int x = random.nextInt(10) + 1;
		if (x > 7) {
			seed = Registry.SEEDS_SATIVA;
		} else if (x > 9) {
			seed = Registry.SEEDS_INDICA;
		} else {
			seed = Registry.SEEDS_HEMP;
		}
		return seed;
	}
}

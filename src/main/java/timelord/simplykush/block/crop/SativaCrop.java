package timelord.simplykush.block.crop;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import timelord.simplykush.Registry;

import java.util.Random;

public class SativaCrop extends CropBase {
	
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
	private static final AxisAlignedBB[] SATIVA_AABB = new AxisAlignedBB[]{
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.19D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.38D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.57D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.76D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.95D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.14D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.33D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.52D, 1.0D) };
	
	public SativaCrop (Properties properties) {
		super(properties);
	}
	
	public AxisAlignedBB getBoundingBox (BlockState state, IBlockReader source, BlockPos pos) {
		return SATIVA_AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
	}
	
	protected RegistryObject<Item> getSeed () {
		RegistryObject<Item> seed;
		Random random = new Random();
		int x = random.nextInt(10) + 1;
		if (x > 7) {
			seed = Registry.SEEDS_INDICA;
		} else if (x > 9) {
			seed = Registry.SEEDS_HEMP;
		} else {
			seed = Registry.SEEDS_SATIVA;
		}
		return seed;
	}
}

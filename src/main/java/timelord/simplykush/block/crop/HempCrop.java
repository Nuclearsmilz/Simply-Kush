package timelord.simplykush.block.crop;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import timelord.simplykush.Registry;

import java.util.Random;

public class HempCrop extends CropBase {
	
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
	private static final AxisAlignedBB[] HEMP_AABB = new AxisAlignedBB[]{
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1328125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.265625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3984375D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.53125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6640625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.796875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9296875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0625, 1.0D) };
	
	public HempCrop (Properties properties) {
		super(properties);
	}
	
	public AxisAlignedBB getBoundingBox (BlockState state, IBlockReader source, BlockPos pos) {
		return HEMP_AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
	}
	
	protected RegistryObject<Item> getSeed () {
		RegistryObject<Item> seed;
		Random random = new Random();
		int x = random.nextInt(30) + 1;
		if (x > 27) {
			boolean y = random.nextBoolean();
			seed = y ? Registry.SEEDS_INDICA : Registry.SEEDS_SATIVA;
		} else {
			seed = Registry.SEEDS_HEMP;
		}
		return seed;
	}
	
}

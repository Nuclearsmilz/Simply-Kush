package timelord.simplykush.util;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.server.ServerWorld;

public class ChunkUtil {
	public static void load(World world , ChunkPos pos){
		assert !world.isClientSide;
		((ServerWorld)world).getChunk(pos.x, pos.z, ChunkStatus.FULL, true);
	}
	
	public static void unload(World world , ChunkPos pos){
		assert !world.isClientSide;
		((ServerWorld)world).getChunk(pos.x, pos.z, ChunkStatus.FULL, false);
	}
}

package timelord.simplykush.tileentity;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;

import net.minecraft.world.World;

public class TileEntityBase extends TileEntity {
	public TileEntityBase (TileEntityType<?> typeIn) {
		super(typeIn);
	}
	
	public void onDestroyed(BlockState state, BlockPos pos){
		invalidateCaps();
	}
	
	public ActionResultType onActivated(BlockState state, BlockPos pos, PlayerEntity playerEntity, Hand hand){
		return ActionResultType.PASS;
	}
	
	@Override
	public CompoundNBT getUpdateTag () {
		return this.save(new CompoundNBT());
	}
	
	@Override
	public void onDataPacket (NetworkManager net, SUpdateTileEntityPacket pkt) {
		super.onDataPacket(net, pkt);
	}
	
}

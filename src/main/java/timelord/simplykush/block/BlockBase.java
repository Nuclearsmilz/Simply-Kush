package timelord.simplykush.block;

import net.minecraft.block.*;
import net.minecraft.entity.ai.brain.task.ForgetRaidTask;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.*;
import net.minecraft.world.*;
import timelord.simplykush.tileentity.TileEntityBase;

import javax.annotation.Nullable;
import java.util.Objects;

public class BlockBase extends Block {
	VoxelShape shape = null;
	TileEntityType<? extends TileEntity> tileEntityType = null;
	
	public BlockBase (Properties properties) {
		super(properties);
	}
	
	public BlockBase setShape (VoxelShape shape) {
		this.shape = shape;
		return this;
	}
	
	public BlockBase setTile (TileEntityType<? extends TileEntity> type) {
		this.tileEntityType = type;
		return this;
	}
	
	@Override
	public boolean hasTileEntity (BlockState state) {
		return this.tileEntityType != null;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity (BlockState state, IBlockReader world) {
		return tileEntityType.create();
	}
	
	public VoxelShape getRaytraceShape (BlockState state, IBlockReader world, BlockPos pos) {
		return shape != null ? shape : VoxelShapes.block();
	}
	
	@Override
	public VoxelShape getShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx) {
		return getRaytraceShape(state, world, pos);
	}
	
	@Override
	public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx) {
		return getRaytraceShape(state, world, pos);
	}
	
	public void breakBlock (BlockState state, IBlockReader world, BlockPos pos) {
		if (hasTileEntity(state)) {
			TileEntity te = world.getBlockEntity(pos);
			if (te instanceof TileEntityBase) {
				((TileEntityBase) Objects.requireNonNull(world.getBlockEntity(pos))).onDestroyed(state, pos);
			}
		}
	}
	
	@Override
	public void onBlockExploded (BlockState state, World world, BlockPos pos, Explosion explosion) {
		breakBlock(state, world, pos);
		super.onBlockExploded(state, world, pos, explosion);
	}
	
	public void onBlockHarvested (BlockState state, World world, BlockPos pos, Explosion explosion) {
		breakBlock(state, world, pos);
		onBlockHarvested(state, world, pos, explosion);
	}
	
	public ActionResultType onBlockActivated (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
		if (hasTileEntity(state)) {
			TileEntity te = world.getBlockEntity(pos);
			if (te instanceof TileEntityBase) {
				return ((TileEntityBase) world.getBlockEntity(pos)).onActivated(state, pos, player, hand);
			}
		}
		return onBlockActivated(state, world, pos, player, hand, ray);
	}
}
package com.huto.harmonicresonance.block.vibe.gen;

import java.util.Random;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import com.huto.harmonicresonance.tile.vibration.gen.TileEntityThermalInfluxer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockThermalInfluxer extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	private static final VoxelShape SHAPE_N = Stream
			.of(Block.makeCuboidShape(0, 8, 0, 16, 9, 16), Block.makeCuboidShape(3, 9, 3, 13, 14, 13),
					Block.makeCuboidShape(0, 0, 0.109, 3, 8, 3), Block.makeCuboidShape(0, 9, 0.109, 3, 13, 3),
					Block.makeCuboidShape(13, 0, 0, 16, 8, 3), Block.makeCuboidShape(13, 9, 0, 16, 13, 3),
					Block.makeCuboidShape(13, 0, 13, 16, 8, 16), Block.makeCuboidShape(13, 9, 13, 16, 13, 16),
					Block.makeCuboidShape(0, 0, 13, 3, 8, 16), Block.makeCuboidShape(0, 9, 13, 3, 13, 16),
					Block.makeCuboidShape(13, 9, 3, 15, 13, 13), Block.makeCuboidShape(1, 9, 3, 3, 13, 13),
					Block.makeCuboidShape(3, 9, 13, 13, 13, 15), Block.makeCuboidShape(3, 9, 1, 13, 13, 3))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public BlockThermalInfluxer(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));

	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE_N;
	}

	@Override
	public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TileEntityThermalInfluxer) {
			((TileEntityThermalInfluxer) te).checkStructure();
		}
	}

	@Override
	public void animateTick(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos,
			@Nonnull Random random) {
		TileEntityThermalInfluxer tile = (TileEntityThermalInfluxer) world.getTileEntity(pos);
		if (tile != null && tile instanceof TileEntityThermalInfluxer) {
			int count = (int) (10 * 0.5f);
			if (count > 0) {
				for (int i = 0; i < random.nextInt(count); i++) {
					double randX = pos.getX() - 0.1 + random.nextDouble() * 1.2;
					double randY = pos.getY() - 0.1 + random.nextDouble() * 1.2;
					double randZ = pos.getZ() - 0.1 + random.nextDouble() * 1.2;
					if (tile.canGenerate()) {
						world.addParticle(ParticleTypes.FALLING_LAVA, randX, randY, randZ, 0, 0, 0);
					}
				}
			}
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEntityThermalInfluxer();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		super.onBlockClicked(state, worldIn, pos, player);
	}

}

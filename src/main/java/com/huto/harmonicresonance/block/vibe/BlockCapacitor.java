package com.huto.harmonicresonance.block.vibe;

import java.util.Random;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import com.huto.harmonicresonance.init.ItemInit;
import com.huto.harmonicresonance.item.ItemUpgrade;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityCapacitor;
import com.hutoslib.common.VanillaPacketDispatcher;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockCapacitor extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	private static final VoxelShape SHAPE_N = Stream
			.of(Block.makeCuboidShape(4, 0, 4, 12, 1, 12), Block.makeCuboidShape(4, 12, 4, 12, 13, 12),
					Block.makeCuboidShape(9, 13, 9, 11, 14, 11), Block.makeCuboidShape(11, 1, 4, 12, 12, 5),
					Block.makeCuboidShape(4, 1, 4, 5, 12, 5), Block.makeCuboidShape(11, 1, 11, 12, 12, 12),
					Block.makeCuboidShape(4, 1, 11, 5, 12, 12), Block.makeCuboidShape(5, 13, 5, 7, 14, 7),
					Block.makeCuboidShape(9, 13, 5, 11, 14, 7), Block.makeCuboidShape(5, 13, 9, 7, 14, 11))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();;

	public BlockCapacitor(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));

	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isRemote)
			return ActionResultType.SUCCESS;
		TileEntityCapacitor te = (TileEntityCapacitor) worldIn.getTileEntity(pos);
		ItemStack stack = player.getHeldItem(handIn);
		if (stack.getItem() == ItemInit.upgrade_wrench.get()) {
			ModInventoryVibeHelper.withdrawFromInventory(te, player);
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(te);
			return ActionResultType.SUCCESS;
		}
		// If there is something in your hand add it to the block if its not an //
		if (!stack.isEmpty() && stack.getItem() instanceof ItemUpgrade) {
			te.addItem(player, stack, handIn);
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(te);
			return ActionResultType.SUCCESS;

		}
		// Upgrade clause

		if (stack.getItem() == ItemInit.enhanced_magatama.get() && te.getTankLevel() < 3) {
			te.addTankLevel(1);
			player.getHeldItemMainhand().shrink(1);

		}
		// Says the tank is full
		if (te.getVibeCap().getVibes() >= te.getTankSize()) {
			String message = String.format("Drum is full");
			player.sendStatusMessage(new StringTextComponent(TextFormatting.BLUE + message), false);
		}
		VanillaPacketDispatcher.dispatchTEToNearbyPlayers(te);
		return ActionResultType.FAIL;

	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE_N;
	}

	@Override
	public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
	}

	@Override
	public void animateTick(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos,
			@Nonnull Random random) {
		TileEntityCapacitor tile = (TileEntityCapacitor) world.getTileEntity(pos);
		if (tile != null && tile instanceof TileEntityCapacitor) {
			int count = (int) (10 * 0.5f);
			if (count > 0) {
				for (int i = 0; i < random.nextInt(count); i++) {
					double randX = pos.getX() - 0.1 + random.nextDouble() * 1.2;
					double randY = pos.getY() - 0.1 + random.nextDouble() * 1.2;
					double randZ = pos.getZ() - 0.1 + random.nextDouble() * 1.2;
					world.addParticle(ParticleTypes.REVERSE_PORTAL, randX, randY, randZ, 0, 0, 0);

				}
			}
		}
	}

	@Override
	public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
		super.onPlayerDestroy(worldIn, pos, state);

		// inner ring
		for (int j = 0; j < 10; j++) {

			worldIn.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + .5, pos.getY(), pos.getZ() + .5,
					Math.sin(j) / 9, Math.sin(j) / 3, Math.cos(j) / 9);
			worldIn.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + .5, pos.getY(), pos.getZ() + .5,
					Math.cos(j) / 9, Math.sin(j) / 3, Math.sin(j) / 9);
			worldIn.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + .5, pos.getY(), pos.getZ() + .5,
					Math.sin(-j) / 9, Math.sin(j) / 3, Math.cos(-j) / 9);
			worldIn.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + .5, pos.getY(), pos.getZ() + .5,
					Math.cos(-j) / 9, Math.sin(j) / 3, Math.sin(-j) / 9);

		}
		// outer ring
		for (int i = 0; i < 10; i++) {
			worldIn.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + .5, pos.getY(), pos.getZ() + .5,
					Math.sin(i) / 3, Math.sin(i) / 3, Math.cos(i) / 3);
			worldIn.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + .5, pos.getY(), pos.getZ() + .5,
					Math.cos(i) / 3, Math.sin(i) / 3, Math.sin(i) / 3);
			worldIn.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + .5, pos.getY(), pos.getZ() + .5,
					Math.sin(-i) / 3, Math.sin(i) / 3, Math.cos(-i) / 3);
			worldIn.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + .5, pos.getY(), pos.getZ() + .5,
					Math.cos(-i) / 3, Math.sin(i) / 3, Math.sin(-i) / 3);
		}
		worldIn.playSound(null, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.AMBIENT, 0.1f, 0.3F);

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
		return new TileEntityCapacitor();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		super.onBlockClicked(state, worldIn, pos, player);
	}

}

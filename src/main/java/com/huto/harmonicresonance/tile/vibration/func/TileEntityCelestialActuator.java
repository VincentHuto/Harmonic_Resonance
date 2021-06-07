package com.huto.harmonicresonance.tile.vibration.func;

import com.huto.harmonicresonance.capabilities.vibration.IVibrations;
import com.huto.harmonicresonance.capabilities.vibration.VibrationProvider;
import com.huto.harmonicresonance.init.TileEntityInit;
import com.huto.harmonicresonance.tile.util.IImportableTile;
import com.huto.harmonicresonance.tile.vibration.TileModVibes;
import com.huto.harmonicresonance.tile.vibration.gen.TileEntityAbsorber;
import com.hutoslib.math.Vector3;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.world.server.ServerWorld;

public class TileEntityCelestialActuator extends TileModVibes implements ITickableTileEntity, IImportableTile {
	IVibrations vibes = getCapability(VibrationProvider.VIBE_CAPA).orElseThrow(IllegalStateException::new);
	int cooldown = 0;
	private static final int SET_COOLDOWN_EVENT = 1;
	float maxVibes = 500;
	public float clientVibes = 0.0f;
	public final String TAG_VIBES = "vibes";
	public final String TAG_SIZE = "tankSize";
	public final String TAG_LEVEL = "level";
	public int level = 1;
	public static final String TAG_STATE = "state";
	public boolean state = false;

	public TileEntityCelestialActuator() {
		super(TileEntityInit.celestial_actuator.get());
	}

	public int getCooldown() {
		return cooldown;
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}

	public IVibrations getVibeCap() {
		return vibes;
	}

	public float getMaxVibes() {
		return maxVibes;
	}

	public void setMaxVibes(float maxVibes) {
		this.maxVibes = maxVibes;
	}

	public void addLevel(float valIn) {
		this.level += valIn;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int levelIn) {
		this.level = levelIn;
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (cooldown > 0) {
				cooldown--;
			}
		}
	}

	// Nbt
	@Override
	public void readPacketNBT(CompoundNBT tag) {
		super.readPacketNBT(tag);
		maxVibes = tag.getFloat(TAG_SIZE);
		clientVibes = tag.getFloat(TAG_VIBES);
		level = tag.getInt(TAG_LEVEL);
		state = tag.getBoolean(TAG_STATE);

	}

	@Override
	public void writePacketNBT(CompoundNBT tag) {
		super.writePacketNBT(tag);
		tag.putFloat(TAG_SIZE, maxVibes);
		tag.putFloat(TAG_VIBES, vibes.getVibes());
		tag.putInt(TAG_LEVEL, level);
		tag.putBoolean(TAG_STATE, state);

	}

	public void changeState() {
		state = !state;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		return super.write(compound);
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);

	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		super.getUpdatePacket();
		CompoundNBT nbtTag = new CompoundNBT();
		nbtTag.putFloat(TAG_SIZE, maxVibes);
		nbtTag.putFloat(TAG_VIBES, vibes.getVibes());
		nbtTag.putInt(TAG_LEVEL, level);
		return new SUpdateTileEntityPacket(getPos(), -1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getNbtCompound();
		super.onDataPacket(net, pkt);
		maxVibes = tag.getFloat(TAG_SIZE);
		clientVibes = tag.getFloat(TAG_VIBES);
		level = tag.getInt(TAG_LEVEL);

	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		super.handleUpdateTag(state, tag);
		maxVibes = tag.getFloat(TAG_SIZE);
		clientVibes = tag.getFloat(TAG_VIBES);
		level = tag.getInt(TAG_LEVEL);

	}

	@Override
	public boolean receiveClientEvent(int id, int param) {
		switch (id) {
		case SET_COOLDOWN_EVENT:
			cooldown = param;
			return true;
		default:
			return super.receiveClientEvent(id, param);

		}
	}

	public boolean checkCooldown() {
		return getCooldown() > 0 ? false : true;

	}

	public void onActivated(PlayerEntity player, ItemStack wand) {
		if (world.isRemote) {
			return;
		} else {
			ServerWorld sWorld = (ServerWorld) world;

			if (checkCooldown()) {
				System.out.println("t");

				Vector3 vec = Vector3.fromTileEntityCenter(this).add(0, 0.1, 0);
				@SuppressWarnings("unused")
				Vector3 endVec = vec.add(0, 0.5, 0);
				this.getVibeCap().subtractVibes(10);
				cooldown = 10;
				if (state == false) {
					sWorld.setDayTime(world.getDayTime() + 1000);

				} else {
					sWorld.setDayTime(world.getDayTime() - 1000);

				}
				this.sendUpdates();
			}
		}
	}

	public BlockState getState() {
		return world.getBlockState(pos);
	}

	public void setBlockToUpdate() {
		sendUpdates();
	}

	@Override
	public void importFromAbsorber(TileEntityAbsorber importFrom, float rate) {
		this.vibes.addVibes(rate);
		importFrom.vibes.subtractVibes(rate);
	}

	@Override
	public boolean canImport() {
		if (vibes.getVibes() < maxVibes) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, getState(), getState());
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		markDirty();
	}

	private final double NEEDLE_ACCELERATION = 0.4; // acceleration in units per square second
	private final double NEEDLE_MAX_SPEED = 0.4; // maximum needle movement speed in units per second
	private SmoothNeedleMovement smoothNeedleMovement = new SmoothNeedleMovement(NEEDLE_ACCELERATION, NEEDLE_MAX_SPEED);

	// return the smoothed position of the needle, based on the power level
	public double getSmoothedNeedlePosition() {
		// System.out.println(world.getCelestialAngle(1.0F));

		double targetNeedlePosition = world.getCelestialAngleRadians(1.0F) / 1;
		smoothNeedleMovement.setTargetNeedlePosition(targetNeedlePosition, false);
		return smoothNeedleMovement.getSmoothedNeedlePosition();
	}

}

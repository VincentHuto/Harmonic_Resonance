package com.huto.harmonicresonance.tile.util;

import com.huto.harmonicresonance.tile.vibration.gen.TileEntityAbsorber;

public interface ITank {
	public void importFromAbsorber(TileEntityAbsorber importFrom, float rate);

	public boolean canImport();

	public void exportToAbsorber(TileEntityAbsorber exportTo, float rate);

	public boolean canExport();

	public void sendUpdates();
}

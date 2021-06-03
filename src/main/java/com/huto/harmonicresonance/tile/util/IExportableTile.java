
package com.huto.harmonicresonance.tile.util;

import com.huto.harmonicresonance.tile.vibration.gen.TileEntityAbsorber;

public interface IExportableTile {
	public void exportToAbsorber(TileEntityAbsorber exportTo, float rate);

	public boolean canExport();

	public void sendUpdates();

}

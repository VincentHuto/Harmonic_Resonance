
package com.huto.harmonicresonance.tile.util;

import com.huto.harmonicresonance.tile.vibration.gen.TileEntityAbsorber;

public interface IImportableTile {
	public void importFromAbsorber(TileEntityAbsorber importFrom, float rate);

	public boolean canImport();

	public void sendUpdates();

}

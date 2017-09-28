package uk.co.aperistudios.firma.blocks.tileentity;

import net.minecraft.tileentity.TileEntity;
import uk.co.aperistudios.firma.TimeData;
import uk.co.aperistudios.firma.types.CropType;

public class CropTileEntity extends TileEntity {
	private CropType type;
	private TimeData planted;

	public int getStage(TimeData now) {
		int max = type.getStages();
		int elapsedDays = now.getTotalDays() - planted.getTotalDays();
		if (elapsedDays < 0) {
			return -1;
		}
		return Math.min(max, elapsedDays);
	}

	public CropType getType() {
		return type;
	}

	public TimeData getPlanted() {
		return planted;
	}

	public void setCropType(CropType ct) {
		type = ct;
		this.markDirty();
	}

}

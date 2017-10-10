package uk.co.aperistudios.firma.blocks.tileentity;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import uk.co.aperistudios.firma.TimeData;
import uk.co.aperistudios.firma.blocks.living.CropBlock;
import uk.co.aperistudios.firma.types.CropType;

public class CropTileEntity extends TileEntity {
	private CropType type;
	private TimeData planted;

	public CropTileEntity() {
	}

	public CropTileEntity(IBlockState state) {
		type = (CropType) state.getProperties().get(CropBlock.crop);
		//(CropStage)state.getProperties().get(CropBlock.stage);
	}

	public int getStage(TimeData now) {
		if (planted == null) {
			planted = now;
			return 0;
		}
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

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		type = CropType.values()[compound.getInteger("fcrop")];
		planted = new TimeData("");
		planted.readFromNBT(compound);

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("fcrop", getCropIndex(type));
		planted.writeToNBT(compound);
		return compound;
	}

	private static int getCropIndex(CropType type2) {
		int i = 0;
		for (CropType ct : CropType.values()) {
			if (ct == type2) {
				return i;
			}
			i++;
		}
		return -1;
	}

}

package uk.co.aperistudios.firma.blocks.tileentity;

import javax.annotation.Nullable;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import uk.co.aperistudios.firma.blocks.lessboring.MiniBlock;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class MiniBlockTileEntity extends TileEntity {
	int val;
	SolidMaterialEnum sme;

	public MiniBlockTileEntity() {

	}

	public MiniBlockTileEntity(IBlockState state) {
		if (state == null) {
			return;
		}
		if (state.getProperties() == null) {
			return;
		}
		val = 0;
		if ((boolean) state.getProperties().get(MiniBlock.lne)) {
			setPart(MiniBlock.lne);
		}
		if ((boolean) state.getProperties().get(MiniBlock.lnw)) {
			setPart(MiniBlock.lnw);
		}
		if ((boolean) state.getProperties().get(MiniBlock.lse)) {
			setPart(MiniBlock.lse);
		}
		if ((boolean) state.getProperties().get(MiniBlock.lsw)) {
			setPart(MiniBlock.lsw);
		}
		if ((boolean) state.getProperties().get(MiniBlock.une)) {
			setPart(MiniBlock.une);
		}
		if ((boolean) state.getProperties().get(MiniBlock.unw)) {
			setPart(MiniBlock.unw);
		}
		if ((boolean) state.getProperties().get(MiniBlock.use)) {
			setPart(MiniBlock.use);
		}
		if ((boolean) state.getProperties().get(MiniBlock.usw)) {
			setPart(MiniBlock.usw);
		}
		setMaterial((SolidMaterialEnum) state.getProperties().get(MiniBlock.mat));
		this.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		val = compound.getInteger("fval");
		sme = SolidMaterialEnum.values()[compound.getInteger("fmat")];
		if (val == 0) {
			// UH OH
			throw new RuntimeException("Loading empty MiniBlock");
		}
	}

	public SolidMaterialEnum getMaterial() {
		return sme;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("fval", val);
		compound.setInteger("fmat", getSub(sme));
		return compound;
	}

	public boolean getPart(PropertyBool part) {
		if (part == MiniBlock.lse) {
			return (val & 1) > 0;
		} else if (part == MiniBlock.lne) {
			return (val & 2) > 0;
		} else if (part == MiniBlock.lsw) {
			return (val & 4) > 0;
		} else if (part == MiniBlock.lnw) {
			return (val & 8) > 0;
		} else if (part == MiniBlock.use) {
			return (val & 16) > 0;
		} else if (part == MiniBlock.une) {
			return (val & 32) > 0;
		} else if (part == MiniBlock.usw) {
			return (val & 64) > 0;
		} else if (part == MiniBlock.unw) {
			return (val & 128) > 0;
		}
		throw new RuntimeException("Not a part of the whole");
	}

	private int setPart(PropertyBool part) {
		if (part == MiniBlock.lse) {
			val = val | 1;
		} else if (part == MiniBlock.lne) {
			val = val | 2;
		} else if (part == MiniBlock.lsw) {
			val = val | 4;
		} else if (part == MiniBlock.lnw) {
			val = val | 8;
		} else if (part == MiniBlock.use) {
			val = val | 16;
		} else if (part == MiniBlock.une) {
			val = val | 32;
		} else if (part == MiniBlock.usw) {
			val = val | 64;
		} else if (part == MiniBlock.unw) {
			val = val | 128;
		}
		this.markDirty();
		//world.scheduleBlockUpdate(pos, FirmaMod.miniBlocks, 0, 0);
		return val;
	}

	private void removePart(PropertyBool part) {
		this.markDirty();
		//world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
		if (part == MiniBlock.lse) {
			val = val & (255 ^ 1);
		} else if (part == MiniBlock.lne) {
			val = val & (255 ^ 2);
		} else if (part == MiniBlock.lsw) {
			val = val & (255 ^ 4);
		} else if (part == MiniBlock.lnw) {
			val = val & (255 ^ 8);
		} else if (part == MiniBlock.use) {
			val = val & (255 ^ 16);
		} else if (part == MiniBlock.une) {
			val = val & (255 ^ 32);
		} else if (part == MiniBlock.usw) {
			val = val & (255 ^ 64);
		} else if (part == MiniBlock.unw) {
			val = val & (255 ^ 128);
		}
		throw new RuntimeException("Not a part of the whole");
	}

	public int getCount() {
		int i = 0;
		if (getPart(MiniBlock.lse)) {
			i++;
		}
		if (getPart(MiniBlock.lne)) {
			i++;
		}
		if (getPart(MiniBlock.lsw)) {
			i++;
		}
		if (getPart(MiniBlock.lnw)) {
			i++;
		}
		if (getPart(MiniBlock.use)) {
			i++;
		}
		if (getPart(MiniBlock.une)) {
			i++;
		}
		if (getPart(MiniBlock.usw)) {
			i++;
		}
		if (getPart(MiniBlock.unw)) {
			i++;
		}
		return i;
	}

	public IBlockState apply(PropertyBool b, IBlockState ibs) {
		return ibs.withProperty(b, getPart(b));
	}

	public static int getSub(SolidMaterialEnum sme) {
		int index = 0;
		for (index = 0; index < SolidMaterialEnum.values().length; index++) {
			if (sme == SolidMaterialEnum.values()[index]) {
				return index;
			}
		}
		return -1;
	}

	public void setMaterial(SolidMaterialEnum property) {
		this.sme = property;
		this.markDirty();
		//world.scheduleBlockUpdate(pos, FirmaMod.miniBlocks, 0, 0);

	}

	@Override
	public double getMaxRenderDistanceSquared() {
		return 1024;
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
	public String toString() {
		return sme + " miniblock";
	}

	//@Override
	//public boolean shouldRefresh(World world1, BlockPos pos1, IBlockState oldState, IBlockState newState) {
	//	return oldState.getBlock() != newState.getBlock();
	//}
}
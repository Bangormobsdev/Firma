package uk.co.aperistudios.firma.blocks.tileentity;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import uk.co.aperistudios.firma.blocks.lessboring.FirmaMiniBlock;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class MiniBlockTileEntity extends TileEntity {
	int val;
	SolidMaterialEnum sme;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		val = compound.getInteger("val");
		sme = SolidMaterialEnum.values()[compound.getInteger("mat")];
	}

	public SolidMaterialEnum getMaterial() {
		return sme;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("val", val);
		compound.setInteger("mat", getSub(sme));
		return compound;
	}

	public boolean getPart(PropertyBool part) {
		if (part == FirmaMiniBlock.lse) {
			return (val & 1) > 0;
		} else if (part == FirmaMiniBlock.lne) {
			return (val & 2) > 0;
		} else if (part == FirmaMiniBlock.lsw) {
			return (val & 4) > 0;
		} else if (part == FirmaMiniBlock.lnw) {
			return (val & 8) > 0;
		} else if (part == FirmaMiniBlock.use) {
			return (val & 16) > 0;
		} else if (part == FirmaMiniBlock.une) {
			return (val & 32) > 0;
		} else if (part == FirmaMiniBlock.usw) {
			return (val & 64) > 0;
		} else if (part == FirmaMiniBlock.unw) {
			return (val & 128) > 0;
		}
		throw new RuntimeException("Not a part of the whole");
	}

	public int setPart(PropertyBool part) {
		if (part == FirmaMiniBlock.lse) {
			return val | 1;
		} else if (part == FirmaMiniBlock.lne) {
			return val | 2;
		} else if (part == FirmaMiniBlock.lsw) {
			return val | 4;
		} else if (part == FirmaMiniBlock.lnw) {
			return val | 8;
		} else if (part == FirmaMiniBlock.use) {
			return val | 16;
		} else if (part == FirmaMiniBlock.une) {
			return val | 32;
		} else if (part == FirmaMiniBlock.usw) {
			return val | 64;
		} else if (part == FirmaMiniBlock.unw) {
			return val | 128;
		}
		throw new RuntimeException("Not a part of the whole");
	}

	public void removePart(PropertyBool part) {
		if (part == FirmaMiniBlock.lse) {
			val = val & (255 ^ 1);
		} else if (part == FirmaMiniBlock.lne) {
			val = val & (255 ^ 2);
		} else if (part == FirmaMiniBlock.lsw) {
			val = val & (255 ^ 4);
		} else if (part == FirmaMiniBlock.lnw) {
			val = val & (255 ^ 8);
		} else if (part == FirmaMiniBlock.use) {
			val = val & (255 ^ 16);
		} else if (part == FirmaMiniBlock.une) {
			val = val & (255 ^ 32);
		} else if (part == FirmaMiniBlock.usw) {
			val = val & (255 ^ 64);
		} else if (part == FirmaMiniBlock.unw) {
			val = val & (255 ^ 128);
		}
		throw new RuntimeException("Not a part of the whole");
	}

	public int getCount() {
		int i = 0;
		if (getPart(FirmaMiniBlock.lse)) {
			i++;
		}
		if (getPart(FirmaMiniBlock.lne)) {
			i++;
		}
		if (getPart(FirmaMiniBlock.lsw)) {
			i++;
		}
		if (getPart(FirmaMiniBlock.lnw)) {
			i++;
		}
		if (getPart(FirmaMiniBlock.use)) {
			i++;
		}
		if (getPart(FirmaMiniBlock.une)) {
			i++;
		}
		if (getPart(FirmaMiniBlock.usw)) {
			i++;
		}
		if (getPart(FirmaMiniBlock.unw)) {
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

}
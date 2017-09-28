package uk.co.aperistudios.firma.blocks.tileentity;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import uk.co.aperistudios.firma.blocks.lessboring.MiniBlock;
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

	public int setPart(PropertyBool part) {
		this.markDirty();
		if (part == MiniBlock.lse) {
			return val | 1;
		} else if (part == MiniBlock.lne) {
			return val | 2;
		} else if (part == MiniBlock.lsw) {
			return val | 4;
		} else if (part == MiniBlock.lnw) {
			return val | 8;
		} else if (part == MiniBlock.use) {
			return val | 16;
		} else if (part == MiniBlock.une) {
			return val | 32;
		} else if (part == MiniBlock.usw) {
			return val | 64;
		} else if (part == MiniBlock.unw) {
			return val | 128;
		}
		throw new RuntimeException("Not a part of the whole");
	}

	public void removePart(PropertyBool part) {
		this.markDirty();
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

	}

}
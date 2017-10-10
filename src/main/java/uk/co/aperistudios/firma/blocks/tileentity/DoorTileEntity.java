package uk.co.aperistudios.firma.blocks.tileentity;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.blocks.lessboring.DoorBlock;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class DoorTileEntity extends TileEntity {

	private SolidMaterialEnum sme;

	public DoorTileEntity() {
		sme = SolidMaterialEnum.Acacia;
		markDirty();
	}

	public DoorTileEntity(IBlockState state) {
		sme = (SolidMaterialEnum) state.getProperties().get(DoorBlock.MATERIAL);
		markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		sme = SolidMaterialEnum.values()[compound.getInteger("mat")];
	}

	public SolidMaterialEnum getMaterial() {
		return sme;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("mat", MiniBlockTileEntity.getSub(sme));
		return compound;
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
	}

	@Override
	public boolean shouldRefresh(World world1, BlockPos pos1, IBlockState oldState, IBlockState newState) {
		if (oldState.getBlock() == newState.getBlock()) {
			return false;
		}
		return true;
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	public void setMaterial(SolidMaterialEnum material) {
		this.sme = material;
		this.markDirty();
	}

}

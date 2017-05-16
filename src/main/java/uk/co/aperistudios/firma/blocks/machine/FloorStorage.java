package uk.co.aperistudios.firma.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.blocks.tileentity.FloorStorageTileEntity;

public class FloorStorage extends Block implements ITileEntityProvider {

	public FloorStorage() {
		super(Material.AIR);
		this.setCreativeTab(null); // Not a block to be spawned in
		this.setRegistryName("floorstorage");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new FloorStorageTileEntity();
	}

}

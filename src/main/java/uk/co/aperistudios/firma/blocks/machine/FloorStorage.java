package uk.co.aperistudios.firma.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof FloorStorageTileEntity) {
			// TODO Item storage.
		}

		return true;
	}

}

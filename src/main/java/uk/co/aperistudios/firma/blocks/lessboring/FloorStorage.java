package uk.co.aperistudios.firma.blocks.lessboring;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.FloorStorageTileEntity;

public class FloorStorage extends Block implements ITileEntityProvider {

	public FloorStorage() {
		super(Material.GRASS);
		this.setRegistryName("floorstorage");
		this.setUnlocalizedName("floorstorage");
		this.setCreativeTab(null); // Not a block to be spawned in
		this.setBlockUnbreakable();
		this.lightOpacity = 0;
		GameRegistry.register(this);
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.SOLID;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new FloorStorageTileEntity();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState2, IBlockAccess worldIn, BlockPos pos) {
		return null;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, .1, 1);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof FloorStorageTileEntity) {
			FloorStorageTileEntity fste = (FloorStorageTileEntity) worldIn.getTileEntity(pos);
			if (player.isSneaking()) {
				System.out.println(facing + " " + hitX + " " + hitY + " " + hitZ);

				int index = -1;
				if (hitX < .5 && hitZ < .5) {
					index = 0;
				} else if (hitX < .5 && hitZ > .5) {
					index = 1;
				} else if (hitX > .5 && hitZ < .5) {
					index = 2;
				} else if (hitX > .5 && hitZ > .5) {
					index = 3;
				}
				if (index == -1) {
					return false;
				}
				ItemStack removeItem = fste.removeStackFromSlot(index);
				if (!removeItem.isEmpty()) {
					if (!player.inventory.addItemStackToInventory(removeItem)) {
						EntityItem ei = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, removeItem);
						worldIn.spawnEntity(ei);
					}
					if (fste.isEmpty()) {
						worldIn.setBlockToAir(pos);
					}
				}
				return false;
			}

		}

		return false;
	}

	@Override
	public boolean canSpawnInBlock() {
		return true;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		checkFloor(worldIn, pos);
	}

	private static void checkFloor(World worldIn, BlockPos pos) {
		IBlockState bs = worldIn.getBlockState(pos.down());
		if (bs.getBlock() instanceof BaseBlock) {
			BaseBlock fb = (BaseBlock) bs.getBlock();
			if (fb.canSupportVessels(bs)) {
				return;
			}
		}
		worldIn.setBlockToAir(pos);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		FloorStorageTileEntity fste = (FloorStorageTileEntity) worldIn.getTileEntity(pos);
		fste.destroy(worldIn, pos, state);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		if (willHarvest) {
			return true;
		}
		return super.removedByPlayer(state, world, pos, player, willHarvest);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack tool) {
		super.harvestBlock(world, player, pos, state, te, tool);
		world.setBlockToAir(pos);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<ItemStack>();
	}

	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}
}

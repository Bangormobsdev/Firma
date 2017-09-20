package uk.co.aperistudios.firma.blocks.lessboring;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.SoFTileEntity;

public class ShitOnFloor extends Block implements ITileEntityProvider {
	private AxisAlignedBB shitCollision = new AxisAlignedBB(0, 0, 0, 1f, 0.1f, 1f);

	public ShitOnFloor() {
		super(Material.LEAVES);
		this.setRegistryName("shitonfloor");
		this.setUnlocalizedName("shitonfloor");
		this.setCreativeTab(null);
		this.blockHardness = 0;
		this.lightOpacity = 0;
		GameRegistry.register(this);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return shitCollision;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState2, IBlockAccess worldIn, BlockPos pos) {
		return shitCollision;
	}

	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return shitCollision;
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new SoFTileEntity();
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
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		SoFTileEntity te = (SoFTileEntity) world.getTileEntity(pos);
		list.add(te.getItem());
		return list;
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

}

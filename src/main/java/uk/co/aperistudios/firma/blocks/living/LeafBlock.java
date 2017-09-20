package uk.co.aperistudios.firma.blocks.living;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import scala.util.Random;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.types.WoodEnum;

public class LeafBlock extends BaseBlock {
	public static final IProperty<WoodEnum> properties = PropertyEnum.create("variants", WoodEnum.class);

	public LeafBlock(Material materialIn) {
		super(materialIn, "leaf");
		this.setHardness(10);
		this.setResistance(10);
		this.setCreativeTab(FirmaMod.blockTab);
		this.setDefaultState(this.getStateFromMeta(0));
		this.needsRandomTick = true;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, properties);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (final WoodEnum enumType : WoodEnum.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		WoodEnum type = state.getValue(properties);

		return type.getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(properties, WoodEnum.get(meta));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if (stack == null) {
			throw new NullPointerException();
		}
		return WoodEnum.getName(stack.getMetadata());
	}

	@Override
	public ArrayList<String> getVariantNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (WoodEnum tr : WoodEnum.values()) {
			names.add(tr.getName());
		}
		return names;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState bState, IBlockAccess worldIn, BlockPos pos) {
		return null;
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return BlockRenderLayer.CUTOUT == layer;
	}

	@Override
	public boolean isFoliage(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public String getMetaName(int meta) {
		return WoodEnum.getName(meta);
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		//checkForTree(worldIn, pos, state);
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, java.util.Random random) {
		if (worldIn.isRemote) {
			return;
		}
		//super.randomTick(worldIn, pos, state, random);
		checkForTree(worldIn, pos, state);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		Random r = new Random();
		if (r.nextFloat() > 0.8) {
			list.add(Util.getSapling(state));
		}
		if (r.nextFloat() > 0.3) {
			list.add(new ItemStack(Items.STICK));
		}
		return list;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isRemote) {
			return;
		}
		for (ItemStack is : getDrops(worldIn, pos, state, 0)) {
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, is));
		}
		worldIn.setBlockToAir(pos);
	}

	private void checkForTree(World worldIn, BlockPos pos, IBlockState state) {
		WoodEnum woodType = Util.getWoodEnum(state);
		ArrayList<BlockPos> leafLocations = new ArrayList<BlockPos>();
		ArrayList<BlockPos> checkedLocations = new ArrayList<BlockPos>();
		int checkCount = 0;
		leafLocations.add(pos);
		while (leafLocations.size() > 0 && checkCount < 100) {
			if (checkNeighbours(worldIn, leafLocations.remove(0), woodType, checkedLocations, leafLocations)) {
				return;
			}
		}
		System.out.println(leafLocations.size() + " " + checkCount);
		this.breakBlock(worldIn, pos, state);
		worldIn.setBlockToAir(pos);
	}

	public boolean checkNeighbours(World worldIn, BlockPos pos, WoodEnum type, ArrayList<BlockPos> checkedLocations, ArrayList<BlockPos> leafLocations) {
		checkedLocations.add(pos);
		for (EnumFacing facing : EnumFacing.VALUES) {
			BlockPos pos2 = pos.offset(facing);
			IBlockState bs = worldIn.getBlockState(pos2);
			if (contains(checkedLocations, pos2)) {
				continue;
			}
			if (Util.getWoodEnum(bs) == type) {

				if (bs.getBlock() == FirmaMod.log) {
					return true;
				} else if (bs.getBlock() == FirmaMod.leaf) {
					if (!contains(leafLocations, pos2)) {
						leafLocations.add(pos2);
					}
				}
			}
		}
		return false;
	}

	public boolean contains(ArrayList<BlockPos> list, BlockPos item) {
		for (BlockPos pos : list) {
			if (pos.getX() == item.getX() && pos.getY() == item.getY() && pos.getZ() == item.getZ()) {
				return true;
			}
		}
		return false;
	}
}

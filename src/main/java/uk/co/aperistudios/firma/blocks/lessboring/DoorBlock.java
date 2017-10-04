package uk.co.aperistudios.firma.blocks.lessboring;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.BlockDoor.EnumHingePosition;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.blocks.BlockState;
import uk.co.aperistudios.firma.blocks.tileentity.DoorTileEntity;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class DoorBlock extends Block implements BlockState {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool OPEN = PropertyBool.create("open");
	public static final PropertyEnum<EnumHingePosition> HINGE = PropertyEnum.<EnumHingePosition>create("hinge", EnumHingePosition.class);
	public static final PropertyEnum<EnumDoorHalf> HALF = PropertyEnum.<EnumDoorHalf>create("half", EnumDoorHalf.class);
	public static final PropertyEnum<SolidMaterialEnum> MATERIAL = PropertyEnum.<SolidMaterialEnum>create("material", SolidMaterialEnum.class);
	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625);
	protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.9375, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.9375, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625, 1.0D, 1.0D);

	public DoorBlock() {
		super(Material.ROCK);
		this.setRegistryName("firmadoor");
		this.setUnlocalizedName("firmadoor");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false))
				.withProperty(HINGE, EnumHingePosition.LEFT).withProperty(HALF, EnumDoorHalf.LOWER).withProperty(MATERIAL, SolidMaterialEnum.Silver));
		this.setCreativeTab(FirmaMod.deviceTab);
		this.isBlockContainer = true;

		GameRegistry.register(this);
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.SOLID && state.getValue(HALF) == EnumDoorHalf.LOWER;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing enumfacing = state.getValue(FACING);

		/*
		 * 		boolean flag = !state.getValue(OPEN).booleanValue();
		boolean flag1 = state.getValue(HINGE) == EnumHingePosition.RIGHT;
		 * switch (enumfacing) {
		case EAST:
		default:
			return flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
		case SOUTH:
			return flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
		case WEST:
			return flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
		case NORTH:
			return flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
		}*/
		switch (enumfacing) {
		case EAST:
		default:
			return EAST_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		case NORTH:
			return NORTH_AABB;
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return isOpen(combineMetadata(worldIn, pos));
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	private static int getCloseSound(World worldIn, BlockPos pos) {
		DoorTileEntity te = (DoorTileEntity) worldIn.getTileEntity(pos);
		return te.getMaterial().getMaterial() == Material.IRON ? 1011 : 1012;
	}

	private static int getOpenSound(World worldIn, BlockPos pos) {
		DoorTileEntity te = (DoorTileEntity) worldIn.getTileEntity(pos);
		return te.getMaterial().getMaterial() == Material.IRON ? 1005 : 1006;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		BlockPos blockpos = state.getValue(HALF) == EnumDoorHalf.LOWER ? pos : pos.down();
		IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

		if (iblockstate.getBlock().equals(FirmaMod.door)) {
			state = iblockstate.cycleProperty(OPEN);
			worldIn.setBlockState(blockpos, state, 10);
			worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
			worldIn.playEvent(playerIn, state.getValue(OPEN).booleanValue() ? getOpenSound(worldIn, pos) : getCloseSound(worldIn, pos), pos, 0);
			return true;
		}
		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (state.getValue(HALF) == EnumDoorHalf.UPPER) {
			BlockPos blockpos = pos.down();
			IBlockState iblockstate = worldIn.getBlockState(blockpos);

			if (iblockstate.getBlock() != this) {
				worldIn.setBlockToAir(pos);
			} else if (blockIn != this) {
				iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
			}
		} else {
			boolean shouldBreak = false;
			BlockPos blockpos1 = pos.up();
			IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

			if (iblockstate1.getBlock() != this) { // Upper block is missing. Break this one
				worldIn.setBlockToAir(pos);
				shouldBreak = true;
			}

			if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) {
				// Block below no longer supports door
				worldIn.setBlockToAir(pos);
				shouldBreak = true;

				if (iblockstate1.getBlock() == this) {
					// And remove upper half
					worldIn.setBlockToAir(blockpos1);
				}
			}

			if (shouldBreak) {
				if (!worldIn.isRemote) {
					this.dropBlockAsItem(worldIn, pos, state, 0);
				}
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return pos.getY() >= worldIn.getHeight() - 1 ? false
				: worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP) && super.canPlaceBlockAt(worldIn, pos)
						&& super.canPlaceBlockAt(worldIn, pos.up());
	}

	@Override
	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.DESTROY;
	}

	public static int combineMetadata(IBlockAccess worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		int i = iblockstate.getBlock().getMetaFromState(iblockstate);
		boolean flag = isTop(i);
		IBlockState iblockstate1 = worldIn.getBlockState(pos.down());
		int j = iblockstate1.getBlock().getMetaFromState(iblockstate1);
		int k = flag ? j : i;
		IBlockState iblockstate2 = worldIn.getBlockState(pos.up());
		int l = iblockstate2.getBlock().getMetaFromState(iblockstate2);
		int i1 = flag ? i : l;
		boolean flag1 = (i1 & 1) != 0;
		boolean flag2 = (i1 & 2) != 0;
		return removeHalfBit(k) | (flag ? 8 : 0) | (flag1 ? 16 : 0) | (flag2 ? 32 : 0);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		DoorTileEntity te = (DoorTileEntity) worldIn.getTileEntity(pos);
		return new ItemStack(FirmaMod.doorItem, 1, FirmaMod.doorItem.getSubMeta(te.getMaterial().getName()));
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		BlockPos blockpos = pos.down();
		BlockPos blockpos1 = pos.up();

		if (player.capabilities.isCreativeMode && state.getValue(HALF) == EnumDoorHalf.UPPER && worldIn.getBlockState(blockpos).getBlock() == this) {
			worldIn.setBlockToAir(blockpos);
		}

		if (state.getValue(HALF) == EnumDoorHalf.LOWER && worldIn.getBlockState(blockpos1).getBlock() == this) {
			if (player.capabilities.isCreativeMode) {
				worldIn.setBlockToAir(pos);
			}

			worldIn.setBlockToAir(blockpos1);
		}
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te == null) {
			return state;
		}
		state = state.withProperty(MATERIAL, ((DoorTileEntity) te).getMaterial());
		if (state.getValue(HALF) == EnumDoorHalf.LOWER) {
			EnumHingePosition hinge = state.getValue(HINGE);
			Boolean open = state.getValue(OPEN);
			if (open) {
				if (hinge == EnumHingePosition.RIGHT) {
					// Clockwise
					state = state.withRotation(Rotation.CLOCKWISE_90);
				} else {
					state = state.withRotation(Rotation.COUNTERCLOCKWISE_90);
				}
			}

		} else {
			IBlockState downState = worldIn.getBlockState(pos.down());
			if (downState.getBlock() == this) {
				return getActualState(downState, worldIn, pos.down()).withProperty(HALF, EnumDoorHalf.UPPER);
			}
		}
		return state;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.getValue(HALF) != EnumDoorHalf.LOWER ? state : state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return mirrorIn == Mirror.NONE ? state : state.withRotation(mirrorIn.toRotation(state.getValue(FACING))).cycleProperty(HINGE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return (meta & 8) > 0
				? this.getDefaultState().withProperty(HALF, EnumDoorHalf.UPPER)
						.withProperty(HINGE, (meta & 1) > 0 ? EnumHingePosition.RIGHT : EnumHingePosition.LEFT)

				: this.getDefaultState().withProperty(HALF, EnumDoorHalf.LOWER).withProperty(FACING, EnumFacing.getHorizontal(meta & 3).rotateYCCW())
						.withProperty(OPEN, Boolean.valueOf((meta & 4) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if (state.getValue(HALF) == EnumDoorHalf.UPPER) {
			i = i | 8;

			if (state.getValue(HINGE) == EnumHingePosition.RIGHT) {
				i |= 1;
			}
		} else {
			i = i | state.getValue(FACING).rotateY().getHorizontalIndex();

			if (state.getValue(OPEN).booleanValue()) {
				i |= 4;
			}
		}

		return i;
	}

	protected static int removeHalfBit(int meta) {
		return meta & 7;
	}

	public static boolean isOpen(IBlockAccess worldIn, BlockPos pos) {
		return isOpen(combineMetadata(worldIn, pos));
	}

	public static EnumFacing getFacing(IBlockAccess worldIn, BlockPos pos) {
		return getFacing(combineMetadata(worldIn, pos));
	}

	public static EnumFacing getFacing(int combinedMeta) {
		return EnumFacing.getHorizontal(combinedMeta & 3).rotateYCCW();
	}

	protected static boolean isOpen(int combinedMeta) {
		return (combinedMeta & 4) != 0;
	}

	protected static boolean isTop(int meta) {
		return (meta & 8) != 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { HALF, FACING, OPEN, HINGE, MATERIAL });
	}

	@Override
	public ResourceLocation getModelPath() {
		return new ResourceLocation(FirmaMod.MODID + ":firmadoor");
	}

	@Override
	public String getModelSub() {
		return "";
	}

	@Override
	public Block getBlock() {
		return this;
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new DoorTileEntity();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
		super.eventReceived(state, worldIn, pos, id, param);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
	}

	@Override
	public IStateMapper getStateMapper() {
		return new StateMapperBase() {

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(getModelPath(), Util.makeStateString(state, DoorBlock.FACING, DoorBlock.MATERIAL));
			}

		};
	}
}

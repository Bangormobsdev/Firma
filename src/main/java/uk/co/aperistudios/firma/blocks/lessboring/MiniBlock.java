package uk.co.aperistudios.firma.blocks.lessboring;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.BlockState;
import uk.co.aperistudios.firma.blocks.tileentity.MiniBlockTileEntity;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class MiniBlock extends Block implements BlockState, ITileEntityProvider {
	public static final PropertyBool lse = PropertyBool.create("lse"), lne = PropertyBool.create("lne"), lsw = PropertyBool.create("lsw"),
			lnw = PropertyBool.create("lnw");
	public static final PropertyBool use = PropertyBool.create("use"), une = PropertyBool.create("une"), usw = PropertyBool.create("usw"),
			unw = PropertyBool.create("unw");
	public static final IProperty<SolidMaterialEnum> mat = PropertyEnum.create("material", SolidMaterialEnum.class);

	protected static final AxisAlignedBB AABB_TOP_NW = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 0.5D);
	protected static final AxisAlignedBB AABB_TOP_NE = new AxisAlignedBB(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);
	protected static final AxisAlignedBB AABB_TOP_SW = new AxisAlignedBB(0.0D, 0.5D, 0.5D, 0.5D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_TOP_SE = new AxisAlignedBB(0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_BOT_NW = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 0.5D);
	protected static final AxisAlignedBB AABB_BOT_NE = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
	protected static final AxisAlignedBB AABB_BOT_SW = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 0.5D, 0.5D, 1.0D);
	protected static final AxisAlignedBB AABB_BOT_SE = new AxisAlignedBB(0.5D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);

	public MiniBlock() {
		super(Material.LEAVES);
		setRegistryName("miniblock");
		this.isBlockContainer = true;
		this.useNeighborBrightness = true;
		this.translucent = true;
		GameRegistry.register(this);
		setDefaultState(this.blockState.getBaseState().withProperty(lne, false).withProperty(lse, false).withProperty(lnw, false).withProperty(lsw, false)
				.withProperty(une, false).withProperty(use, false).withProperty(unw, false).withProperty(usw, false));
		this.setCreativeTab(FirmaMod.blockTab);
	}

	public IBlockState getHalfBlock(SolidMaterialEnum sme) {
		return this.blockState.getBaseState().withProperty(lse, true).withProperty(lne, true).withProperty(lsw, true).withProperty(lnw, true)
				.withProperty(use, false).withProperty(usw, false).withProperty(une, false).withProperty(unw, false).withProperty(mat, sme);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		MiniBlockTileEntity mbte = (MiniBlockTileEntity) worldIn.getTileEntity(pos);
		state = mbte.apply(lse, state);
		state = mbte.apply(lne, state);
		state = mbte.apply(lsw, state);
		state = mbte.apply(lnw, state);
		state = mbte.apply(unw, state);
		state = mbte.apply(use, state);
		state = mbte.apply(une, state);
		state = mbte.apply(usw, state);
		return state.withProperty(mat, mbte.getMaterial());
	}

	public IBlockState getSteps(SolidMaterialEnum sme) {
		return this.blockState.getBaseState().withProperty(lse, true).withProperty(lne, true).withProperty(lsw, true).withProperty(lnw, true)
				.withProperty(une, true).withProperty(unw, true).withProperty(mat, sme);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, lse, lne, lsw, lnw, use, une, usw, unw, mat);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		MiniBlockTileEntity mbte = (MiniBlockTileEntity) world.getTileEntity(pos);
		int a = mbte.getCount();
		drops.add(new ItemStack(FirmaMod.miniBlocks, a, MiniBlockTileEntity.getSub(mbte.getMaterial())));
		return drops;
	}

	@Override
	public ResourceLocation getModelPath() {
		return new ResourceLocation("miniblock");
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
	public IStateMapper getStateMapper() {
		return null;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new MiniBlockTileEntity(state);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		throw new RuntimeException("Nope");
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false; // TODO Poll tile entity for 4 parts that make up the side requested
	}

	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
		super.eventReceived(state, worldIn, pos, id, param);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
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
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	private static ArrayList<AxisAlignedBB> getCollisionBoxList(World worldIn, BlockPos pos) {
		MiniBlockTileEntity mbte = (MiniBlockTileEntity) worldIn.getTileEntity(pos);
		ArrayList<AxisAlignedBB> list = new ArrayList<AxisAlignedBB>();
		if (mbte.getPart(une)) {
			list.add(AABB_TOP_NE);
		}
		if (mbte.getPart(unw)) {
			list.add(AABB_TOP_NW);
		}
		if (mbte.getPart(use)) {
			list.add(AABB_TOP_SE);
		}
		if (mbte.getPart(usw)) {
			list.add(AABB_TOP_SW);
		}
		if (mbte.getPart(lse)) {
			list.add(AABB_BOT_SE);
		}
		if (mbte.getPart(lsw)) {
			list.add(AABB_BOT_SW);
		}
		if (mbte.getPart(lne)) {
			list.add(AABB_BOT_NE);
		}
		if (mbte.getPart(lnw)) {
			list.add(AABB_BOT_NW);
		}
		return list;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes,
			Entity entityIn, boolean p_185477_7_) {
		for (AxisAlignedBB aabb : getCollisionBoxList(worldIn, pos)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, aabb);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState1, IBlockAccess worldIn, BlockPos pos) {
		return Block.NULL_AABB;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return super.getSelectedBoundingBox(state, worldIn, pos);
	}

	@Override
	@Nullable
	public RayTraceResult collisionRayTrace(IBlockState blockState1, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
		List<RayTraceResult> list = Lists.<RayTraceResult>newArrayList();

		for (AxisAlignedBB axisalignedbb : getCollisionBoxList(worldIn, pos)) {
			list.add(this.rayTrace(pos, start, end, axisalignedbb));
		}

		RayTraceResult raytraceresult1 = null;
		double d1 = 0.0D;

		for (RayTraceResult raytraceresult : list) {
			if (raytraceresult != null) {
				double d0 = raytraceresult.hitVec.squareDistanceTo(end);

				if (d0 > d1) {
					raytraceresult1 = raytraceresult;
					d1 = d0;
				}
			}
		}
		return raytraceresult1;
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		int index = 0;
		for (SolidMaterialEnum sme : SolidMaterialEnum.values()) {
			list.add(new ItemStack(itemIn, 1, index));
			index++;
		}
	}

	public SolidMaterialEnum getSolidMaterial(World world, BlockPos pos) {
		MiniBlockTileEntity mbte = (MiniBlockTileEntity) world.getTileEntity(pos);
		return mbte.getMaterial();
	}
}

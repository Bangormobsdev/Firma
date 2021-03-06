package uk.co.aperistudios.firma.blocks.lessboring;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.blocks.BlockState;
import uk.co.aperistudios.firma.blocks.tileentity.OreTileEntity;
import uk.co.aperistudios.firma.types.OresEnum;
import uk.co.aperistudios.firma.types.RocksEnum;

public class OreBlock extends Block implements ITileEntityProvider, BlockState {
	public static final IProperty<OresEnum> oreLayer = PropertyEnum.create("ore", OresEnum.class);
	public static final IProperty<RocksEnum> rockLayer = PropertyEnum.create("rock", RocksEnum.class);

	public OreBlock() {
		super(Material.ROCK);
		this.setRegistryName("ore");
		this.setUnlocalizedName("ore");
		this.isBlockContainer = true;
		GameRegistry.register(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new OreTileEntity();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
		super.eventReceived(state, worldIn, pos, id, param);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		// Mouse.setGrabbed(false);
		OreTileEntity te = (OreTileEntity) worldIn.getTileEntity(pos);
		if (te != null && te.ore != null && te.rock != null) {
			return super.getActualState(state, worldIn, pos).withProperty(oreLayer, te.ore).withProperty(rockLayer, te.rock);
		}
		return super.getActualState(state, worldIn, pos);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, oreLayer, rockLayer);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		OreTileEntity te = (OreTileEntity) worldIn.getTileEntity(pos);
		if (te != null && te.ore != null && te.rock != null) { // TODO Prospectors pick
			playerIn.sendMessage(new TextComponentString(te.ore + " " + te.rock));
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		// return super.getDrops(world, pos, state, fortune);
		OreTileEntity fote = (OreTileEntity) world.getTileEntity(pos);
		List<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(Util.getOreItemStack(fote.ore, fote.grade, 1));
		return ret;
	}

	@Override
	public ResourceLocation getModelPath() {
		return new ResourceLocation(FirmaMod.MODID + ":ore");
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
		return new StateMapperBase() {

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(getModelPath(), Util.makeStateString(state, oreLayer, rockLayer));
			}
		};
	}
}

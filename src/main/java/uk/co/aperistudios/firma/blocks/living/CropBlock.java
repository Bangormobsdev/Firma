package uk.co.aperistudios.firma.blocks.living;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.BlockState;
import uk.co.aperistudios.firma.blocks.tileentity.CropTileEntity;

public class CropBlock extends Block implements BlockState {

	public CropBlock() {
		super(Material.PLANTS);
		this.setRegistryName("crop");
		this.setUnlocalizedName("crop");
		GameRegistry.register(this);
	}

	@Override
	public ResourceLocation getModelPath() {
		return new ResourceLocation(FirmaMod.MODID + ":crop");
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
				return new ModelResourceLocation(getModelPath(), getModelSub());
			}
		};

	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

		return getDefaultState();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new CropTileEntity();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}

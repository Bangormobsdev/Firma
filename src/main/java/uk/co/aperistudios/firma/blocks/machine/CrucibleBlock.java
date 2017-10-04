package uk.co.aperistudios.firma.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.BlockState;
import uk.co.aperistudios.firma.blocks.tileentity.CrucibleTileEntity;

public class CrucibleBlock extends Block implements BlockState, ITileEntityProvider {

	public CrucibleBlock() {
		super(Material.ROCK);

		this.setRegistryName("crucible");
		this.setUnlocalizedName("crucible");
		Item item = new ItemBlock(this);
		item.setRegistryName("crucible");
		item.setUnlocalizedName("crucible");
		item.setCreativeTab(FirmaMod.deviceTab);
		this.setCreativeTab(FirmaMod.deviceTab);
		GameRegistry.register(this);
		GameRegistry.register(item);
	}

	@Override
	public ResourceLocation getModelPath() {
		return new ResourceLocation(FirmaMod.MODID + ":crucible");
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
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new CrucibleTileEntity();
	}
}

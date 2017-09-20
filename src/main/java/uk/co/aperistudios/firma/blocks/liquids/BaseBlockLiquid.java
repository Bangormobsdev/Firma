package uk.co.aperistudios.firma.blocks.liquids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.blocks.BlockState;

public class BaseBlockLiquid extends BlockFluidClassic implements BlockState {

	private boolean fire;
	private String subName;

	public BaseBlockLiquid(Fluid fluid, Material material, String subName) {
		super(fluid, material);
		this.subName = subName;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	public void setsOnFire(boolean y) {
		this.fire = y;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (fire) {
			entityIn.setFire(5);
		}
		super.onEntityWalk(worldIn, pos, entityIn);
	}

	@Override
	public ResourceLocation getModelPath() {
		return new ResourceLocation(FirmaMod.MODID + ":fluid");
	}

	@Override
	public String getModelSub() {
		return subName;
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
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos neighbourPos) {
		if (Util.allowPhysics) {
			super.neighborChanged(state, world, pos, neighborBlock, neighbourPos);
		}
	}
}

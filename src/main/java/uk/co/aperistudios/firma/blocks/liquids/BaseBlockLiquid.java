package uk.co.aperistudios.firma.blocks.liquids;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BaseBlockLiquid extends BlockFluidClassic {

	private boolean fire;

	public BaseBlockLiquid(Fluid fluid, Material material) {
		super(fluid, material);
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
}

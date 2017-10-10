package uk.co.aperistudios.firma.generation.structures;

import java.util.HashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class PlanDud extends Plan {

	public PlanDud(int cX, int cZ) {
		this.startx = cX * 16 + 8;
		this.startz = cZ * 16 + 8;
	}

	@Override
	public int getWidthX() {
		return 16;
	}

	@Override
	public int getWidthZ() {
		return 16;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public void build(World world, int x, int y, int z, IBlockState rock, IBlockState wood) {
		throw new RuntimeException("Attempting to build a dud plan");
	}

	@Override
	public PlanShape getShape() {
		return null;
	}

	@Override
	public HashMap<String, IBlockState> getBlocks(IBlockState rock, IBlockState wood) {
		return null;
	}

}

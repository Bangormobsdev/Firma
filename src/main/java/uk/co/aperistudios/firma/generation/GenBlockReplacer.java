package uk.co.aperistudios.firma.generation;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface GenBlockReplacer {
	public void replaceBlock(World world, BlockPos pos);

}

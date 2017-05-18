package uk.co.aperistudios.firma.generation;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.Util;

public class FirmaDebugOres implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

		for (int y = 0; y < world.getHeight(); y++) {
			for (int x = 1; x <= 16; x++) {
				for (int z = 1; z <= 16; z++) {
					BlockPos pos = new BlockPos(x + (chunkX * 16), y, z + (chunkZ * 16));
					IBlockState bs = world.getBlockState(pos);
					if (Util.isRawStone(bs.getBlock())) {
						// world.setBlockToAir(pos);
						world.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);
					} else if (Util.isLiquid(bs.getBlock())) {
						world.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);
					}
				}
			}
		}

	}

}
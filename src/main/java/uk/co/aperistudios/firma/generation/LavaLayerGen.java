package uk.co.aperistudios.firma.generation;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;

public class LavaLayerGen implements IWorldGenerator {
	HashMap<String, NoiseGeneratorSimplex> heights = new HashMap<String, NoiseGeneratorSimplex>();
	private int seedOffset;
	IBlockState lava;

	public LavaLayerGen(int seedOffset) {
		// The seed of the prophet shall sit the throne
		this.seedOffset = seedOffset;
		lava = FirmaMod.lava.getBlock().getDefaultState();
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		Random r2 = new Random(seedOffset + world.getSeed());
		int xOff = r2.nextInt(100);
		int zOff = r2.nextInt(100);
		String worldName = world.getWorldInfo().getWorldName();
		if (!heights.containsKey(worldName)) {
			heights.put(worldName, new NoiseGeneratorSimplex(new Random(world.getSeed() + seedOffset)));
		}
		NoiseGeneratorSimplex height = heights.get(worldName);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				double h = (1.0 + (height.getValue((x + xOff + (chunkX * 16)) / 1024.0, (z + zOff + (chunkZ * 16)) / 1024.0)) * 1.80) * .5;
				h = h * 34;
				h += 10;
				for (int y = 1; y < h; y++) {
					BlockPos pos = new BlockPos(x, y, z);
					if (!Util.isOre(world.getBlockState(pos))) {
						world.setBlockState(pos, lava);
					}
				}
			}
		}
	}
}

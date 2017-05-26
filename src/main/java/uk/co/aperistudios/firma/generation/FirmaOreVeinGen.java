package uk.co.aperistudios.firma.generation;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.generation.layers.FirmaGenLayer;
import uk.co.aperistudios.firma.generation.layers.FirmaGenLayerRiver;
import uk.co.aperistudios.firma.generation.layers.FirmaGenLayerZoom;

public class FirmaOreVeinGen implements IWorldGenerator {
	public static int count = 0;
	HashMap<String, FirmaGenLayer> layers = new HashMap<String, FirmaGenLayer>();
	HashMap<String, NoiseGeneratorSimplex> heights = new HashMap<String, NoiseGeneratorSimplex>();
	private int heightVar, minH;
	private GenBlockReplacer replacer;
	private long seedOffset;
	private boolean top = false;

	public FirmaOreVeinGen(int minH, int maxH, long seedOffset, GenBlockReplacer replacer) {
		this.seedOffset = seedOffset;
		this.minH = minH;
		this.heightVar = maxH - minH;
		this.replacer = replacer;
		if (maxH >= 255) {
			top = true;
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		Random r2 = new Random(seedOffset + world.getSeed());
		int xOff = r2.nextInt(100);
		int zOff = r2.nextInt(100);
		String worldName = world.getWorldInfo().getWorldName();
		if (!layers.containsKey(worldName) || !heights.containsKey(worldName)) {
			FirmaGenLayerRiver l = new FirmaGenLayerRiver(1L, FirmaGenLayerZoom.magnify(5L + seedOffset, FirmaGenLayer.genContinent(seedOffset, false), 8));
			layers.put(worldName, l);
			l.initWorldGenSeed(world.getSeed());
			heights.put(worldName, new NoiseGeneratorSimplex(new Random(world.getSeed() + seedOffset)));
		}
		FirmaGenLayer layer = layers.get(worldName);
		NoiseGeneratorSimplex height = heights.get(worldName);
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {

				int[] a = layer.getInts(x + (chunkX * 16) + xOff, z + (chunkZ * 16) + zOff, 1, 1);
				if (a[0] != 0) {
					// Place ore!
					if (top) {
						int topRock = getMaxY(world, x + (chunkX * 16), z + (chunkZ * 16));
						if (topRock <= minH) {
							continue;
						}
						int heightVar2 = topRock - minH;
						double h = (1.0 + (height.getValue((x + (chunkX * 16)) / 1024.0, (z + (chunkZ * 16)) / 1024.0)) * 1.80) * .5;
						// Forced to cap to max/min more as it's a surface ore
						if (h < 0.0) {
							h = 0.0;
						}
						if (h > 1.0) {
							h = 1.0;
						}
						int y = (int) (h * heightVar2) + minH;
						replacer.replaceBlock(world, new BlockPos(x + (chunkX * 16), y, z + (chunkZ * 16)));
					} else {
						double h = (1.0 + (height.getValue((x + (chunkX * 16)) / 1024.0, (z + (chunkZ * 16)) / 1024.0)) * 1.40) * .5;
						if (h < 0.0) {
							h = 0.0;
						}
						if (h > 1.0) {
							h = 1.0;
						}
						int y = (int) (h * heightVar) + minH;
						replacer.replaceBlock(world, new BlockPos(x + (chunkX * 16), y, z + (chunkZ * 16)));
					}
				}
			}
		}

	}

	private static int getMaxY(World world, int x, int z) {
		for (int y = world.getActualHeight(); y > 0; y--) {
			if (Util.isRawStone(world.getBlockState(new BlockPos(x, y, z)).getBlock())) {
				return y;
			}
		}
		return 0;
	}
}

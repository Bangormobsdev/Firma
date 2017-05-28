package uk.co.aperistudios.firma.generation;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.generation.layers.FirmaGenLayer;
import uk.co.aperistudios.firma.generation.layers.FirmaGenLayerRiver;
import uk.co.aperistudios.firma.generation.layers.FirmaGenLayerZoom;

public class FirmaPathGen implements IWorldGenerator {
	HashMap<String, FirmaGenLayer> layers = new HashMap<String, FirmaGenLayer>();
	private int seedOffset;

	public FirmaPathGen(int seedOffset) {
		this.seedOffset = seedOffset;
	}

	public FirmaGenLayer getLayer(World w) {
		String s = w.getWorldInfo().getWorldName();
		if (!layers.containsKey(s)) {
			FirmaGenLayer l = FirmaGenLayerZoom.magnify(22 + seedOffset,
					new FirmaGenLayerRiver(1L, FirmaGenLayerZoom.magnify(5L + seedOffset, FirmaGenLayer.genContinent(seedOffset, false), 5)), 1);
			layers.put(s, l);
			l.initWorldGenSeed(w.getSeed());
		}
		return layers.get(s);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		Random r2 = new Random(seedOffset + world.getSeed());
		int xOff = r2.nextInt(100);
		int zOff = r2.nextInt(100);
		FirmaGenLayer layer = getLayer(world);
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {

				int[] a = layer.getInts(x + (chunkX * 16) + xOff, z + (chunkZ * 16) + zOff, 1, 1);
				if (a[0] != 0) {
					int y = getMaxY(world, x + (chunkX * 16) + 8, z + (chunkZ * 16) + 8);
					if (y > 1) {
						BlockPos pos = new BlockPos(x + (chunkX * 16) + 8, y, z + (chunkZ * 16) + 8);
						world.setBlockState(pos, Util.getGravel(world.getBlockState(pos)));
					}
				}
			}
		}

	}

	private static int getMaxY(World world, int x, int z) {
		for (int y = world.getActualHeight(); y > 0; y--) {
			IBlockState bs = world.getBlockState(new BlockPos(x, y, z));
			if (Util.isRockEnum1(bs)) {
				return y;
			} else if (Util.isRockEnum2(bs)) {
				return y;
			} else if (Util.isWater(bs)) {
				return 0;
			}
		}
		return 0;
	}

}

package uk.co.aperistudios.firma.generation;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.generation.VoronoiNoise.DistanceType;
import uk.co.aperistudios.firma.generation.tree.FirmaTree;

public class FirmaTreeGen implements IWorldGenerator {
	HashMap<String, VoronoiNoise> voro = new HashMap<String, VoronoiNoise>();

	private VoronoiNoise getNoise(World world) {
		if (!voro.containsKey(world.getWorldInfo().getWorldName())) {
			voro.put(world.getWorldInfo().getWorldName(), new VoronoiNoise(world.getSeed() + 3215, DistanceType.MANHATTAN));
		}
		return voro.get(world.getWorldInfo().getWorldName());
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		FirmaTree ft = getTree(world, chunkX, chunkZ);
		if (ft == null) {
			return;
		}
		byte b = world.getChunkFromChunkCoords(chunkX, chunkZ).getBiomeArray()[0];
		FirmaBiome bi = (FirmaBiome) Biome.getBiome(b);
		for (int i = 0; i < bi.treeCount; i++) {
			ft.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}

	public FirmaTree getTree(World world, int chunkX, int chunkZ) {
		VoronoiNoise vRandom = getNoise(world);
		byte b = world.getChunkFromChunkCoords(chunkX, chunkZ).getBiomeArray()[0];
		FirmaTree[] tl = FirmaBiome.getTreeForBiome(b, chunkZ * 16);
		if (tl.length == 0) {
			return null;
		}
		double d = vRandom.noise(chunkX, chunkZ, 0.02);
		d = Math.abs(d) % 1.0;
		return tl[(int) (d * tl.length)];

	}

}

package uk.co.aperistudios.firma.generation;

import java.util.HashMap;
import net.minecraft.block.state.IBlockState;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.generation.VoronoiNoise.DistanceType;

public class FirmaRockStrata {
	static HashMap<Long, VoronoiNoise> noiseCollection = new HashMap<Long, VoronoiNoise>();
	static double freq = 0.02;

	public static IBlockState getTop(long seed, int x, int z) {
		return Util.getRockStrata(getNoise(seed).noise(x / 16., z / 16., freq), 0);
	}

	public static IBlockState getMid(long seed, int x, int z) {
		return Util.getRockStrata(getNoise(seed + 102).noise(x / 16., z / 16., freq), 1);
	}

	public static IBlockState getBot(long seed, int x, int z) {
		return Util.getRockStrata(getNoise(seed + 302).noise(x / 16., z / 16., freq), 2);
	}

	public static VoronoiNoise getNoise(long seed) {
		if (!noiseCollection.containsKey(seed)) {
			noiseCollection.put(seed, new VoronoiNoise(seed, DistanceType.MANHATTAN));
		}
		return noiseCollection.get(seed);
	}

}
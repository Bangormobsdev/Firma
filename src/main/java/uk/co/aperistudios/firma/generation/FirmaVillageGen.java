package uk.co.aperistudios.firma.generation;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.CommonProxy;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.generation.VoronoiNoise.DistanceType;
import uk.co.aperistudios.firma.generation.structures.Village;
import uk.co.aperistudios.firma.generation.tree.FirmaTree;

public class FirmaVillageGen implements IWorldGenerator {

	HashMap<String, VoronoiNoise> voro = new HashMap<String, VoronoiNoise>();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!voro.containsKey(world.getWorldInfo().getWorldName())) {
			voro.put(world.getWorldInfo().getWorldName(), new VoronoiNoise(world.getSeed() + 5116, DistanceType.MANHATTAN));
		}
		VoronoiNoise vRandom = voro.get(world.getWorldInfo().getWorldName());
		double[] center = vRandom.getCoord(chunkX, chunkZ, 0.05);

		int villageChunkX = (int) Math.floor(center[0]);
		int villageChunkZ = (int) Math.floor(center[1]);

		FirmaTree t = CommonProxy.treeGen.getTree(world, villageChunkX, villageChunkZ);

		// While I like this idea, the spawn chances are made too low
		//if (!CommonProxy.pathGen.isProximity(world, center[0], center[1])) { // Village center must be near path
		//	return;
		//}

		double val = vRandom.noise(chunkX, chunkZ, 0.05);

		Village v = new Village(world, (int) (center[0] * 16), (int) (center[1] * 16), new Random(Double.doubleToLongBits(val * center[0] * center[1])));

		IBlockState wood = null;
		if (t == null) {
			wood = FirmaMod.log.getDefaultState();
		} else {
			wood = t.getWood();
		}
		IBlockState rock = FirmaRockStrata.getTop(world.getSeed(), villageChunkX * 16, villageChunkZ * 16);
		v.buildChunk(world, chunkX, chunkZ, rock, wood);
	}
}
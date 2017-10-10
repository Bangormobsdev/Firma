package uk.co.aperistudios.firma.generation.structures;

import java.util.HashMap;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkGenerator;
import uk.co.aperistudios.firma.generation.FirmaChunkGen;

public abstract class Plan {
	boolean set = false;
	int startx, starty, startz;

	public static void init() {
		PlanWell.init();
		PlanHouse.init();
		PlanGarden.init();
	}

	public int getWidthX() {
		return getShape().getWidthX();
	}

	public int getWidthZ() {
		return getShape().getWidthZ();
	}

	public int getHeight() {
		return getShape().getHeight();
	}

	public abstract PlanShape getShape();

	public abstract HashMap<String, IBlockState> getBlocks(IBlockState rock, IBlockState wood);

	public void setPos(int x, int y, int z) {
		this.startx = x;
		this.startz = z;
		this.starty = y;
		this.set = true;
	}

	public void buildArray(List<IBlockState> blocks, int x, int y, int z) {

	}

	public void buildChunk(World world, int chunkX, int chunkZ, IBlockState rock, IBlockState wood) {
		for (int x = 8; x < 24; x++) {
			for (int z = 8; z < 24; z++) {
				BlockPos bp = new BlockPos(chunkX * 16 + x, 1, chunkZ * 16 + z);
				//bp = world.getTopSolidOrLiquidBlock(bp);

				build(world, bp.getX(), bp.getY(), bp.getZ(), rock, wood);
			}
		}
	}

	public void build(World world, int x, int y, int z, IBlockState rock, IBlockState wood) {
		if (x < getX() || x >= getX2()) {
			return;
		}
		if (z < getZ() || z >= getZ2()) {
			return;
		}
		System.out.println("building " + this.toString() + " " + x + "," + y + "," + z);
		HashMap<String, IBlockState> blocks = getBlocks(rock, wood);
		if (blocks != null) {
			getShape().build(blocks, world, x, starty, z, x - startx, z - startz, rock);
		}
	}

	public int getX() {
		return startx;
	}

	public int getZ() {
		return startz;
	}

	public int getX2() {
		return startx + getWidthX();
	}

	public int getZ2() {
		return startz + getWidthZ();
	}

	public static BlockPos getTopBlock(World w, BlockPos pos) {
		WorldServer ws = (WorldServer) w;
		IChunkGenerator cg = ws.getChunkProvider().chunkGenerator;
		FirmaChunkGen fcg = (FirmaChunkGen) cg;
		return fcg.getTopBlock(pos);
	}
}

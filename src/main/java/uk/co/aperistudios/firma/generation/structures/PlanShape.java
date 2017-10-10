package uk.co.aperistudios.firma.generation.structures;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlanShape {
	private int sizex, sizey, sizez, offsetY;
	private ArrayList<String> map = new ArrayList<String>();

	public static PlanShape makePlan(int x, int y, int z, String... strings) {
		PlanShape r = new PlanShape();
		r.sizex = x;
		r.sizey = y;
		r.sizez = z;
		for (int i = 0; i < strings.length; i++) {
			String line = strings[i];
			int strlen = line.length();
			if (r.sizex != strlen) {
				throw new RuntimeException("Plan is not cuboid");
			}
			for (int c = 0; c < line.length(); c++) {
				r.map.add(line.substring(c, c + 1));
			}

		}
		if (r.map.size() != x * y * z) {
			throw new RuntimeException("Not enough data to match dimensions");
		}
		return r;
	}

	public void setBase(int y) {
		offsetY = y;
	}

	public String getMapAt(int posx, int posy, int posz) {
		return map.get((sizex * posz) + (sizex * sizez * posy) + posx);
	}

	public void build(HashMap<String, IBlockState> blocks, World world, int worldx, int worldy, int worldz, int planx, int planz, IBlockState rock) {
		if (planx < 0 || planz < 0 || planx > getWidthX() || planz > getWidthZ()) {
			throw new RuntimeException("Using plan out of bounds x:" + planx + " z:" + planz);
		}
		for (int incy = 0; incy < sizey; incy++) {
			IBlockState b = blocks.get(getMapAt(planx, incy, planz));
			if (b != null) {
				BlockPos pos = new BlockPos(worldx, worldy + incy + offsetY, worldz);
				world.setBlockState(pos, b);
			}
		}

	}

	public int getWidthX() {
		return sizex;
	}

	public int getWidthZ() {
		return sizez;
	}

	public int getHeight() {
		return sizey;
	}
}
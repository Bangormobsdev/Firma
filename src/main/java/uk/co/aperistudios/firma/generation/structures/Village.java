package uk.co.aperistudios.firma.generation.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.Util;

public class Village extends StructureCollectionBuilder {
	ArrayList<Plan> allPlans;

	public Village(World world, int x, int z, Random r) {
		// Start with a middle point.
		spacing = 2;
		BlockPos top = Plan.getTopBlock(world, new BlockPos(x, 1, z));
		allPlans = new ArrayList<Plan>();
		allPlans.add(new PlanWell());
		for (int i = 0; i < r.nextInt(10); i++) {
			allPlans.add(new PlanHouse());
		}
		for (int i = 0; i < r.nextInt(2); i++) {
			allPlans.add(new PlanBlackSmith());
		}
		for (int i = 0; i < r.nextInt(6); i++) {
			allPlans.add(new PlanGarden());
		}
		Collections.shuffle(allPlans, r);
		for (Plan plan : allPlans) {
			if (plan.getShape() == null) {
				continue;
			}
			float incx = r.nextFloat() * 2f - 1f;
			float incz = r.nextFloat() * 2f - 1f;
			float mag = Math.abs(incx) + Math.abs(incz);
			if (mag == 0f) { // Avoid Divide by Zero
				incx = 1f;
				mag = 1f;
			}
			incx = incx / mag;
			incz = incz / mag;
			double px = x, pz = z;
			int ix = (int) Math.floor(px), iz = (int) Math.floor(pz);
			while (!check(plan, ix, iz)) {
				px = px + incx;
				pz = pz + incz;
				ix = (int) Math.floor(px);
				iz = (int) Math.floor(pz);
			}
			plan.setPos(ix, top.getY(), iz);
		}
	}

	@Override
	public ArrayList<Plan> getPlans() {
		return allPlans;
	}

	@Override
	public void spacePlan(World w, int chunkX, int chunkZ, IBlockState rock, IBlockState wood, Plan plan) {
		for (int x = 8; x < 24; x++) {
			for (int z = 8; z < 24; z++) {
				int wx = x + chunkX * 16;
				int wz = z + chunkZ * 16;
				if (wx < plan.getX() - spacing || wx >= plan.getX2() + spacing) {
					continue;
				}
				if (wz < plan.getZ() - spacing || wz >= plan.getZ2() + spacing) {
					continue;
				}
				BlockPos pos = new BlockPos(wx, plan.starty - 1, wz);
				if (Util.isRockEnum1(w.getBlockState(pos)) || Util.isRockEnum2(w.getBlockState(pos))) {
					w.setBlockState(pos, Util.getGravel(w.getBlockState(pos)));
				} else {
					// Legs for rivertowns / overhangs
					if (wx == plan.getX() - spacing && wz == plan.getZ() - spacing) {
						makeLeg(w, wx, plan.starty - 1, wz, Util.getPlank(wood));
					}
					if (wx == plan.getX() - spacing && wz == plan.getZ2() + spacing - 1) {
						makeLeg(w, wx, plan.starty - 1, wz, Util.getPlank(wood));
					}
					if (wx == plan.getX2() + spacing - 1 && wz == plan.getZ() - spacing) {
						makeLeg(w, wx, plan.starty - 1, wz, Util.getPlank(wood));
					}
					if (wx == plan.getX2() + spacing - 1 && wz == plan.getZ2() + spacing - 1) {
						makeLeg(w, wx, plan.starty - 1, wz, Util.getPlank(wood));
					}
					w.setBlockState(pos, Util.getPlank(wood));
				}
				// Destroy terrain upwards to look like space was intentionally leveled
				for (int y = plan.starty; y < plan.starty + plan.getHeight(); y++) {
					w.setBlockToAir(new BlockPos(wx, y, wz));
				}
			}
		}
	}

	private static void makeLeg(World w, int wx, int wy, int wz, IBlockState plank) {
		for (int y = wy - 1; y > 1; y--) {
			BlockPos bp = new BlockPos(wx, y, wz);
			IBlockState bs = w.getBlockState(bp);
			if (bs.getBlock() == Blocks.AIR || Util.isLiquid(bs.getBlock())) {
				w.setBlockState(bp, plank);
			} else {
				return;
			}
		}
	}

}

package uk.co.aperistudios.firma.generation.structures;

import java.util.ArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public abstract class StructureCollectionBuilder {

	int spacing = 0;

	public boolean check(Plan plan, int px, int pz) {
		for (Plan otherPlan : getPlans()) {
			if (plan.equals(otherPlan)) {
				continue;
			}
			if (!otherPlan.set) {
				continue;
			}
			plan.startx = px;
			plan.startz = pz;
			if (rectOverlap(otherPlan, plan)) {
				return false;
			}
		}
		return true;
	}

	boolean rectOverlap(Plan A, Plan B) {
		return !(A.getX2() < B.getX() - spacing || A.getX() >= B.getX2() + spacing || A.getZ2() < B.getZ() - spacing || A.getZ() >= B.getZ2() + spacing);
	}

	public void buildChunk(World world, int chunkX, int chunkZ, IBlockState rock, IBlockState wood) {
		for (Plan plan : getPlans()) {
			if (plan.getShape() == null) {
				continue;
			}
			spacePlan(world, chunkX, chunkZ, rock, wood, plan);
			plan.buildChunk(world, chunkX, chunkZ, rock, wood);
		}
	}

	public abstract ArrayList<Plan> getPlans();

	public abstract void spacePlan(World w, int chunkX, int chunkZ, IBlockState rock, IBlockState wood, Plan plan);
}

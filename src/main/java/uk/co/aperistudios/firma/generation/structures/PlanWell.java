package uk.co.aperistudios.firma.generation.structures;

import java.util.HashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;

public class PlanWell extends Plan {

	static PlanShape wellShape;
	static int wx = 3, height = 7, wz = 3;

	public static void init() {
		wellShape = PlanShape
				.makePlan(wx, height, wz, "SSS", "SWS", "SSS", "SSS", "SWS", "SSS", "SSS", "S S", "SSS", "F F", "   ", "F F", "F F", "   ", "F F", "SSS", "SSS", "SSS", "   ", " S ", "   ");
		wellShape.setBase(-2); // Well below ground level. Pun intended
		wellShape.setFlattenDirt(true);
	}

	@Override
	public PlanShape getShape() {
		return wellShape;
	}

	@Override
	public HashMap<String, IBlockState> getBlocks(IBlockState rock, IBlockState wood) {
		HashMap<String, IBlockState> blocks = new HashMap<String, IBlockState>();
		blocks.put(" ", Blocks.AIR.getDefaultState());
		blocks.put("S", Util.getBricks(rock));
		blocks.put("F", Util.getFence(wood));
		blocks.put("W", FirmaMod.freshwater.getBlock().getDefaultState());
		return blocks;
	}
}

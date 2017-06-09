package uk.co.aperistudios.firma.generation.structures;

import java.util.HashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import uk.co.aperistudios.firma.Util;

public class PlanGarden extends Plan {
	static PlanShape garden;

	public static void init() {
		garden = PlanShape
				.makePlan(13, 3, 7, "PPPPPPPPPPPPP", "PPPPPPPPPPPPP", "PPPPPPPPPPPPP", "PPPPPPPPPPPPP", "PPPPPPPPPPPPP", "PPPPPPPPPPPPP", "PPPPPPPPPPPPP", "PPPPPPPPPPPPP", "PDDDDDDDDDDDP", "PDDDDDDDDDDDP", "PDDDDDDDDDDDP", "PDDDDDDDDDDDP", "PDDDDDDDDDDDP", "PPPPPPPPPPPPP", "             ", " WWWWWWWWWWW ", " WWWWWWWWWWW ", " WWWWWWWWWWW ", " WWWWWWWWWWW ", " WWWWWWWWWWW ", "             ");
		garden.setBase(-1);
	}

	@Override
	public PlanShape getShape() {
		return garden;
	}

	@Override
	public HashMap<String, IBlockState> getBlocks(IBlockState rock, IBlockState wood) {
		HashMap<String, IBlockState> blocks = new HashMap<String, IBlockState>();
		blocks.put(" ", Blocks.AIR.getDefaultState());
		blocks.put("P", Util.getPlank(wood));
		blocks.put("D", Util.getDirt(rock));
		blocks.put("W", Util.getRandomPlant());
		return blocks;
	}

}

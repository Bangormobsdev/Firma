package uk.co.aperistudios.firma.generation.structures;

import java.util.HashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import uk.co.aperistudios.firma.Util;

public class PlanHouse extends Plan {
	static PlanShape houseShape;

	public static void init() {
		houseShape = PlanShape
				.makePlan(10, 6, 6, "SSSSSSSSSS", "SPPPPPPPPS", "SPPPPPPPPS", "SPPPPPPPPS", "SPPPPPPPPS", "SSSSSSSSSS", "SSSDSSSSSS", "S        S", "S        S", "S        S", "S        S", "SSSSSSSSSS", "SSSESSSSSS", "S        S", "S        S", "S        S", "S        S", "SSSSSSSSSS", "SSSSSSSSSS", "S        S", "S        S", "S        S", "S        S", "SSSSSSSSSS", "HHHHHHHHHH", "HPPPPPPPPH", "HPPPPPPPPH", "HPPPPPPPPH", "HPPPPPPPPH", "HHHHHHHHHH", "          ", "          ", "  HHHHHH  ", "  HHHHHH  ", "          ", "          ");
		houseShape.setBase(-1); // Ground is included
	}

	@Override
	public PlanShape getShape() {
		return houseShape;
	}

	@Override
	public HashMap<String, IBlockState> getBlocks(IBlockState rock, IBlockState wood) {
		HashMap<String, IBlockState> blocks = new HashMap<String, IBlockState>();
		blocks.put(" ", Blocks.AIR.getDefaultState());
		blocks.put("S", Util.getBricks(rock));
		blocks.put("P", Util.getPlank(wood));
		blocks.put("H", Util.getHalfPlank(wood));
		blocks.put("D", Util.getDoor(wood, false));
		blocks.put("E", Util.getDoor(wood, true));
		return blocks;
	}
}

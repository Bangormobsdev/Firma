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
	public HashMap<String, BlockStateWithProperties> getBlocks(IBlockState rock, IBlockState wood) {
		HashMap<String, BlockStateWithProperties> blocks = new HashMap<String, BlockStateWithProperties>();
		blocks.put(" ", new BlockStateWithProperties(Blocks.AIR.getDefaultState()));
		blocks.put("S", new BlockStateWithProperties(Util.getBricks(rock)));
		blocks.put("P", new BlockStateWithProperties(Util.getPlank(wood)));
		blocks.put("H", Util.getHalfPlank(wood));
		blocks.put("D", Util.getDoor(wood, false));
		blocks.put("E", Util.getDoor(wood, true));
		return blocks;
	}
}

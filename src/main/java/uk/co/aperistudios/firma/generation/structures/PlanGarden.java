package uk.co.aperistudios.firma.generation.structures;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;

public class PlanGarden extends Plan {
	static PlanShape garden;
	private BlockStateWithProperties plant;

	public PlanGarden(Random r) {
		this.plant = new BlockStateWithProperties(FirmaMod.crops.getDefaultState(), Util.getRandomPlant(r));
	}

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
	public HashMap<String, BlockStateWithProperties> getBlocks(IBlockState rock, IBlockState wood) {
		HashMap<String, BlockStateWithProperties> blocks = new HashMap<String, BlockStateWithProperties>();
		blocks.put(" ", new BlockStateWithProperties(Blocks.AIR.getDefaultState()));
		blocks.put("P", new BlockStateWithProperties(Util.getPlank(wood)));
		blocks.put("D", new BlockStateWithProperties(Util.getFarm(rock)));
		blocks.put("W", this.plant);
		return blocks;
	}

}

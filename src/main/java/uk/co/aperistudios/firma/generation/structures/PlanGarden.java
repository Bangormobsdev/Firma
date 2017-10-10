package uk.co.aperistudios.firma.generation.structures;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.blocks.living.CropBlock;

public class PlanGarden extends Plan {
	static PlanShape garden;
	private IBlockState plant;

	public PlanGarden(Random r) {
		this.plant = FirmaMod.crops.getDefaultState().withProperty(CropBlock.crop, Util.getRandomPlant(r));
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
	public HashMap<String, IBlockState> getBlocks(IBlockState rock, IBlockState wood) {
		HashMap<String, IBlockState> blocks = new HashMap<String, IBlockState>();
		blocks.put(" ", Blocks.AIR.getDefaultState());
		blocks.put("P", Util.getPlank(wood));
		blocks.put("D", Util.getFarm(rock));
		blocks.put("W", this.plant);
		return blocks;
	}

}

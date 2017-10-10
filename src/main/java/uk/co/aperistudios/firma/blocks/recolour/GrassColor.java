package uk.co.aperistudios.firma.blocks.recolour;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import uk.co.aperistudios.firma.ClientProxy;
import uk.co.aperistudios.firma.TimeData;
import uk.co.aperistudios.firma.generation.FirmaBiome;

public class GrassColor implements IBlockColor {
	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {

		Biome b = worldIn.getBiome(pos);
		if (b instanceof FirmaBiome) {

			FirmaBiome fb = (FirmaBiome) b;
			TimeData td = ClientProxy.staticDate;
			return fb.getGrassColour(worldIn, pos, tintIndex, td);
		}
		return b.getGrassColorAtPos(pos);
	}
}

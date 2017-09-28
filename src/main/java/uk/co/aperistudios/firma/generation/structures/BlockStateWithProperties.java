package uk.co.aperistudios.firma.generation.structures;

import net.minecraft.block.state.IBlockState;

public class BlockStateWithProperties {
	IBlockState state;
	Object[] properties;

	public BlockStateWithProperties(IBlockState bs, Object... objects) {
		state = bs;
		properties = objects;
	}
}

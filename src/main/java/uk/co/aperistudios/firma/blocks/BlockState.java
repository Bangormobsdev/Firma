package uk.co.aperistudios.firma.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockState {
	public ResourceLocation getModelPath();

	public String getModelSub();

	public Block getBlock();

	public IStateMapper getStateMapper();

	public void setState(World worldIn, BlockPos pos, Object property);
}
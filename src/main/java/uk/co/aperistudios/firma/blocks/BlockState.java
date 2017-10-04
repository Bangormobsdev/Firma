package uk.co.aperistudios.firma.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.util.ResourceLocation;

public interface BlockState {
	public ResourceLocation getModelPath();

	public String getModelSub();

	public Block getBlock();

	public IStateMapper getStateMapper();
}
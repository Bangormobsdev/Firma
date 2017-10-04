package uk.co.aperistudios.firma.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public interface ItemState {
	public ResourceLocation getModelPath();

	public Item getItem();

	public String getModelSub(int metadata);

	public int getModelCount();
}

package uk.co.aperistudios.firma.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public interface ItemState {
	public ResourceLocation getModelPath();

	public String getModelSub();

	public Item getItem();
}

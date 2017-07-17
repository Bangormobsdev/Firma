package uk.co.aperistudios.firma.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ItemSize;

public abstract class FirmaItem extends Item {
	protected String name;
	protected ItemSize iSize;

	public FirmaItem(String name, ItemSize iSize) {
		this.name = name;
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		FirmaMod.allItems.add(this);
		this.iSize = iSize;
	}

	public String getName() {
		return name;
	}

	public String getBlockStateName() {
		return name;
	}

	public String getVariant() {
		return "inventory";
	}

	public ItemSize getSize(ItemStack is) {
		return iSize;
	}
}

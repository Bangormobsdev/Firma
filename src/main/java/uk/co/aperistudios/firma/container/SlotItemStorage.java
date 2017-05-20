package uk.co.aperistudios.firma.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.items.FirmaItem;
import uk.co.aperistudios.firma.types.ItemSize;

public class SlotItemStorage extends Slot {

	ItemSize maxSize;

	public SlotItemStorage(IInventory inventoryIn, int index, int xPosition, int yPosition, ItemSize maxSize) {
		super(inventoryIn, index, xPosition, yPosition);
		this.maxSize = maxSize;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		Item i = stack.getItem();
		if (i instanceof FirmaItem) {
			FirmaItem fi = (FirmaItem) i;
			return canFit(fi.getSize(stack));
		}
		return super.isItemValid(stack);
	}

	public boolean canFit(ItemSize size) {
		if (size == maxSize) {
			return true;
		}
		if (maxSize == ItemSize.SMALL) {
			return size == ItemSize.TINY;
		} else if (maxSize == ItemSize.MEDIUM) {
			return size == ItemSize.SMALL || size == ItemSize.TINY;
		} else if (maxSize == ItemSize.LARGE) {
			return size == ItemSize.MEDIUM || size == ItemSize.SMALL || size == ItemSize.TINY;
		} else if (maxSize == ItemSize.PLANET) {
			return size == ItemSize.LARGE || size == ItemSize.MEDIUM || size == ItemSize.SMALL || size == ItemSize.TINY;
		}
		return false;
	}

}

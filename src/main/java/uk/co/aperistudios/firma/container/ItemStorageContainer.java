package uk.co.aperistudios.firma.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import uk.co.aperistudios.firma.gui.PlayerInv;
import uk.co.aperistudios.firma.inventory.ItemInventory;
import uk.co.aperistudios.firma.types.ItemSize;

public class ItemStorageContainer extends Container {
	// TODO Make look not shit
	public IInventory inv;

	public ItemStorageContainer(InventoryPlayer invPlayer, int items, ItemSize iSize) {
		inv = new ItemInventory(invPlayer.getCurrentItem());
		if (items == 4) {
			addSlotToContainer(new ItemStorageSlot(inv, 0, 30, 30, iSize));
			addSlotToContainer(new ItemStorageSlot(inv, 1, 48, 30, iSize));
			addSlotToContainer(new ItemStorageSlot(inv, 2, 30, 48, iSize));
			addSlotToContainer(new ItemStorageSlot(inv, 3, 48, 48, iSize));
		}

		PlayerInv.buildInventoryLayout(this, invPlayer, 8, 108, true, true);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}

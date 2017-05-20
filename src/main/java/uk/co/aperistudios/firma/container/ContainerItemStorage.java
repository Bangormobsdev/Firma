package uk.co.aperistudios.firma.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import uk.co.aperistudios.firma.inventory.ItemInventory;
import uk.co.aperistudios.firma.types.ItemSize;

public class ContainerItemStorage extends Container {
	// TODO Make look not shit
	public IInventory inv;

	public ContainerItemStorage(InventoryPlayer invPlayer, int items, ItemSize iSize) {
		inv = new ItemInventory(invPlayer.getCurrentItem());
		if (items == 4) {
			addSlotToContainer(new SlotItemStorage(inv, 0, 8, 7, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 1, 26, 7, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 2, 8, 25, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 3, 26, 25, iSize));
			PlayerInv.buildInventoryLayout(this, invPlayer, GuiItemStorage.width4, GuiItemStorage.height4, true, true);
		} else if (items == 12) {
			addSlotToContainer(new SlotItemStorage(inv, 0, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 1, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 2, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 3, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 4, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 5, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 6, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 7, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 8, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 9, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 10, 30, 30, iSize));
			addSlotToContainer(new SlotItemStorage(inv, 11, 30, 30, iSize));
			PlayerInv.buildInventoryLayout(this, invPlayer, GuiItemStorage.width12, GuiItemStorage.height12, true, true);
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	protected void retrySlotClick(int slotId, int clickedButton, boolean mode, EntityPlayer playerIn) {
	}
}

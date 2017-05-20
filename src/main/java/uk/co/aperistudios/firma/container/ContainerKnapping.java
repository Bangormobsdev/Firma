package uk.co.aperistudios.firma.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;

public class ContainerKnapping extends Container {
	private SlotSpecialCraftingOutput outputSlot;
	public IInventory craftResult = new InventoryCraftResult();

	public ContainerKnapping(InventoryPlayer inventoryplayer, int x, int y, int z) {
		outputSlot = new SlotSpecialCraftingOutput(this, inventoryplayer.player, craftResult, 0, 128, 44);
		addSlotToContainer(outputSlot);

		PlayerInv.buildInventoryLayout(this, inventoryplayer, GuiKnapping.guiwidth, GuiKnapping.guiheight, true, true);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		if (!player.world.isRemote) {
			// DO NOT DROP THE ITEM FOR FREE
			// ItemStack is = this.craftResult.getStackInSlot(0);
			// if (is != null)
			// player.entityDropItem(is, 0);
		}
	}

	@Override
	protected void retrySlotClick(int slotId, int clickedButton, boolean mode, EntityPlayer playerIn) {
	}

}

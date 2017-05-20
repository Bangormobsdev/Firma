package uk.co.aperistudios.firma.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.crafting.CraftingManager;

public class SlotAnvilInput extends Slot {

	public SlotAnvilInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if (CraftingManager.isCraftingInput(stack, CraftMat.ANVIL)) {
			return true;
		}
		return false;
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

}

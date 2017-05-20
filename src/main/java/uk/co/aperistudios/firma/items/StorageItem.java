package uk.co.aperistudios.firma.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.container.HandlerGui;
import uk.co.aperistudios.firma.types.ItemSize;

public class StorageItem extends FirmaItem {
	int items;

	public StorageItem(String name, ItemSize iSize, int items) {
		super(name, iSize);
		this.setMaxDamage(0);
		this.setMaxStackSize(0);
		this.setHasSubtypes(false);
		this.setCreativeTab(FirmaMod.itemTab);
		this.items = items;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack is = player.getHeldItem(handIn);
		if (items == 4) {
			player.openGui(FirmaMod.instance, HandlerGui.GUI_ITEM_4, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
		} else if (items == 12) {
			player.openGui(FirmaMod.instance, HandlerGui.GUI_ITEM_12, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, is);
	}

	public int getSizeInventory() {
		return items;
	}
}

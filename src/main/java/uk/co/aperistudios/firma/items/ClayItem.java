package uk.co.aperistudios.firma.items;

import org.lwjgl.input.Mouse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.container.HandlerGui;
import uk.co.aperistudios.firma.container.GuiKnapping;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.player.PlayerData;
import uk.co.aperistudios.firma.types.ItemSize;

public class ClayItem extends MetaItem {

	public ClayItem(String name) {
		super(name, ItemSize.SMALL);
		setSubs(new String[] { "clay", "fireclay" });
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		Mouse.setGrabbed(false); // TODO Remove debug
		ItemStack is = player.getHeldItem(handIn);
		if (player.getHeldItemMainhand().getCount() > 4) {
			if (worldIn.isRemote) {
				GuiKnapping.staticMaterial = CraftMat.CLAY;
				GuiKnapping.staticMaterialSub = this.getSubName(is.getItemDamage());

				player.openGui(FirmaMod.instance, HandlerGui.GUI_KNAPPING, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
			} else {
				PlayerData pd = PlayerData.getPlayerData(player.getUniqueID());
				pd.resetKnapCraft();
				pd.setItemStack(player.getHeldItemMainhand());
				pd.setCraftingMaterial(CraftMat.CLAY);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, is);
	}

}

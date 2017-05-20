package uk.co.aperistudios.firma.items;

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

public class PebbleItem extends MetaItem {

	public PebbleItem(String name) {
		super(name, ItemSize.TINY);
		setSubs(new String[] { "andesite", "basalt", "chalk", "chert", "claystone", "conglomerate", "dacite", "diorite", "dolomite", "gabbro", "gneiss",
				"granite", "limestone", "marble", "phyllite", "quartzite", "rhyolite", "rocksalt", "schist", "shale", "slate" });
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack is = player.getHeldItem(handIn);
		if (player.getHeldItemMainhand().getCount() > 1) {
			if (worldIn.isRemote) {
				GuiKnapping.staticMaterial = CraftMat.STONE;
				GuiKnapping.staticMaterialSub = this.getSubName(is.getItemDamage());

			} else {
				PlayerData pd = PlayerData.getPlayerData(player.getUniqueID());
				pd.resetKnapCraft();
				pd.setItemStack(player.getHeldItemMainhand());
				pd.setCraftingMaterial(CraftMat.STONE);
			}
		}
		player.openGui(FirmaMod.instance, HandlerGui.GUI_KNAPPING, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, is);
	}
}

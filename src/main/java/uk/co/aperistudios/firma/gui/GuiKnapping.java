package uk.co.aperistudios.firma.gui;

import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.container.ContainerSpecialCrafting;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.crafting.CraftingManager;
import uk.co.aperistudios.firma.crafting.Recipe;
import uk.co.aperistudios.firma.packet.KnapToServer;
import uk.co.aperistudios.firma.player.PlayerData;

public class GuiKnapping extends FirmaGuiContainer {
	public GuiKnapping(EntityPlayer player, int x, int y, int z) {
		super(new ContainerSpecialCrafting(player.inventory, x, y, z), 176, 103);
		// inventorySlots = ;
		tex = new ResourceLocation(FirmaMod.MODID + ":textures/gui/guiknapping.png");
	}

	public static CraftMat staticMaterial;
	public static String staticMaterialSub;

	@Override
	public void initGui() {
		super.initGui();
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				buttonList.add(new GuiKnappingButton(x + (y * 5), guiLeft + (x * 16) + 10, guiTop + (y * 16) + 12, 16, 16, this, x, y));
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof GuiKnappingButton) {
			GuiKnappingButton gkb = (GuiKnappingButton) button;
			PlayerData pd = PlayerData.getPlayerClient();
			int x = gkb.getKnapX();
			int y = gkb.getKnapY();
			gkb.set(!gkb.get());
			KnapToServer nts = new KnapToServer(x, y, gkb.get());
			pd.setKnapCraft(x, y, gkb.get());
			Recipe r = CraftingManager.getMatchingRecipe(pd.getCraftingMaterial(), pd.getItemStack(), pd.getKnapLayout());
			if (r != null) {
				if (Minecraft.getMinecraft().player.openContainer instanceof ContainerSpecialCrafting) {
					((ContainerSpecialCrafting) Minecraft.getMinecraft().player.openContainer).craftResult.setInventorySlotContents(0, r.getOutput());
				}
			} else {
				if (Minecraft.getMinecraft().player.openContainer instanceof ContainerSpecialCrafting) {
					Block air = Block.REGISTRY.getObjectById(0);
					((ContainerSpecialCrafting) Minecraft.getMinecraft().player.openContainer).craftResult.setInventorySlotContents(0, new ItemStack(air));
				}
			}
			FirmaMod.dispatcher.sendToServer(nts);
		}

	}

	public String getTexture() {
		if (staticMaterial == CraftMat.ANVIL) {
			if (staticMaterialSub != null) {
				return "firma:textures/blocks/metal/" + staticMaterialSub + ".png";
			}
		} else if (staticMaterial == CraftMat.CLAY) {
			if (staticMaterialSub != null) {
				return "firma:textures/blocks/" + staticMaterialSub + ".png";
			}
		} else if (staticMaterial == CraftMat.STONE) {
			if (staticMaterialSub != null) {
				return "firma:textures/blocks/rock/" + staticMaterialSub + ".png";
			}
		} else if (staticMaterial == CraftMat.LEATHER) {

		}
		return "firma:textures/blocks/rock/andesite.png";
	}

	public String getRemovedTexture() {
		if (staticMaterial == CraftMat.LEATHER) {
			if (staticMaterialSub != null) {
				return "firma:textures/items/leather" + staticMaterialSub + ".png";
			}
			return "firma:textures/blocks/flatleather.png";
		}
		return null;
	}

	public void resetButtons(boolean b) {
		for (int i = 0; i < 25; i++) {
			((GuiKnappingButton) buttonList.get(i)).show = b;
		}

	}

	@Override
	public void drawForeground(int let, int top) {
	}

}

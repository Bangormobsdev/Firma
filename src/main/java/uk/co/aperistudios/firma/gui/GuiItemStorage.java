package uk.co.aperistudios.firma.gui;

import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ItemSize;

public class GuiItemStorage extends FirmaGuiContainer {
	// TODO make look not shit
	public GuiItemStorage(Container cont, int items, ItemSize size) {
		super(cont, 50, 50); // TODO Width, height
		tex = new ResourceLocation(FirmaMod.MODID + ":textures/gui/guistorage" + size + ".png");
	}

	@Override
	public void drawForeground(int let, int top) {
	}

}

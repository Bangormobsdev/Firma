package uk.co.aperistudios.firma.container;

import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ItemSize;

public class GuiItemStorage extends GuiFirmaBase {
	public static final int width4 = 50, height4 = 50;
	public static final int width12 = 50, height12 = 50;

	// TODO make look not shit
	public GuiItemStorage(Container cont, int items, ItemSize size) {
		super(cont, items == 4 ? width4 : items == 12 ? width12 : 0, items == 4 ? width4 : items == 12 ? width12 : 0);
		tex = new ResourceLocation(FirmaMod.MODID + ":textures/gui/guistorage" + items + ".png");
	}

	@Override
	public void drawForeground(int let, int top) {
	}

}

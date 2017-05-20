package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;

public class DoubleIngotItem extends MetaItem {

	public DoubleIngotItem(String name) {
		super(name, ItemSize.SMALL);
		setSubs(new String[] { "copper", "tin", "bismuth", "bronze", "bismuthbronze", "blackbronze", "brass", "silver", "gold", "rosegold", "copper", "lead",
				"nickel", "platinum", "wroughtiron", "pigiron", "steel", "sterlingsilver", "zinc", "blacksteel", "redsteel", "bluesteel" });
	}

}

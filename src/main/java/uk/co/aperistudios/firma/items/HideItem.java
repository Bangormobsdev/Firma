package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;

public class HideItem extends MetaItem {

	public HideItem(String name) {
		super(name, ItemSize.MEDIUM);
		setSubs(new String[] { "raw", "soaked", "scraped", "prep", "sheep" });
	}
}

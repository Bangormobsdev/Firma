package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.CropType;
import uk.co.aperistudios.firma.types.ItemSize;

public class SeedItem extends MetaItem {

	public SeedItem() {
		super("seed", ItemSize.TINY);
		this.setSubs(CropType.strings());
	}

}

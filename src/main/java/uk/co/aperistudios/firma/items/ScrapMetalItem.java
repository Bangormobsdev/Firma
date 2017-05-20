package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;
import uk.co.aperistudios.firma.types.MetalEnum;

public class ScrapMetalItem extends MetaItem {

	public ScrapMetalItem(String name) {
		super(name, ItemSize.TINY);
		setSubs(MetalEnum.getList());
	}

}

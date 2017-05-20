package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;
import uk.co.aperistudios.firma.types.MetalEnum;

public class IngotItem extends MetaItem {

	public IngotItem(String name) {
		super(name, ItemSize.SMALL);
		setSubs(MetalEnum.getList());
	}

}

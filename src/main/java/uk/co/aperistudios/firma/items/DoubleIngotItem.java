package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;
import uk.co.aperistudios.firma.types.MetalEnum;

public class DoubleIngotItem extends MetaItem {

	public DoubleIngotItem(String name) {
		super(name, ItemSize.SMALL);
		setSubs(MetalEnum.getList());
	}

}

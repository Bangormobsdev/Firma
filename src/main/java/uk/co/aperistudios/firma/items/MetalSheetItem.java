package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;
import uk.co.aperistudios.firma.types.MetalEnum;

public class MetalSheetItem extends MetaItem {

	public MetalSheetItem(String name) {
		super(name, ItemSize.LARGE);
		setSubs(MetalEnum.getList());
	}

}

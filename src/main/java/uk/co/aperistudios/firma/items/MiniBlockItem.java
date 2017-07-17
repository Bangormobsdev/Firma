package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class MiniBlockItem extends MetaItem {

	public MiniBlockItem() {
		super("miniblockitem", ItemSize.TINY);
		this.setSubs(SolidMaterialEnum.names());
	}
}

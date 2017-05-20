package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;

public class BrickItem extends MetaItem {

	public BrickItem(String name) {
		super(name, ItemSize.SMALL);
		setSubs(new String[] { "andesite", "basalt", "chalk", "chert", "claystone", "conglomerate", "dacite", "diorite", "dolomite", "gabbro", "gneiss",
				"granite", "limestone", "marble", "phyllite", "quartzite", "rhyolite", "rocksalt", "schist", "shale", "slate", "clayfirebrick", "firebrick" });
	}

}

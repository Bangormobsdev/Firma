package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.ItemSize;

public class UnfiredClay extends MetaItem {

	public UnfiredClay(String name) {
		super(name, ItemSize.MEDIUM);
		setSubs(new String[] { "jug", "mold", "moldaxe", "moldchisel", "moldhammer", "moldhoe", "moldjavelin", "moldknife", "moldmace", "moldpick",
				"moldpropick", "moldsaw", "moldscythe", "moldshovel", "moldsword", "pot", "vessel", "bowl", "firebrick" });
	}
}

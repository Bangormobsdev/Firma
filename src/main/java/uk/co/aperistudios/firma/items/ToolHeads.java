package uk.co.aperistudios.firma.items;

import java.util.ArrayList;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ItemSize;
import uk.co.aperistudios.firma.types.ToolMaterials;
import uk.co.aperistudios.firma.types.ToolType;

public class ToolHeads extends MetaItem {

	public ToolHeads(String name) {
		super(name, ItemSize.MEDIUM);
		ArrayList<String> list = new ArrayList<String>();
		for (ToolType type : ToolType.values()) {
			for (ToolMaterials metals : ToolMaterials.values()) {
				list.add(metals.getName() + type.getName());
			}
		}
		setSubs(list);
		this.setCreativeTab(FirmaMod.headTab);
	}

}

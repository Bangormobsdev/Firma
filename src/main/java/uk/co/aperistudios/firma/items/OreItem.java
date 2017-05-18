package uk.co.aperistudios.firma.items;

import java.util.ArrayList;
import uk.co.aperistudios.firma.types.OresEnum;

public class OreItem extends MetaItem {
	public static String[] QUALITY = new String[] { "poor", "small", "med", "rich" };

	public OreItem(String name) {
		super(name);
		ArrayList<String> subs = new ArrayList<String>();
		for (OresEnum ore : OresEnum.values()) {
			for (String s : QUALITY) {
				subs.add(ore.getName() + s);
			}
		}
		setSubs(subs);
	}
}

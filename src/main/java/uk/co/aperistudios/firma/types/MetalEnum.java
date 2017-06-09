package uk.co.aperistudios.firma.types;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.IStringSerializable;

public enum MetalEnum implements IStringSerializable {
	Copper("copper", 1),
	Tin("tin", 2),
	Bismuth("bismuth", 3),
	Bronze("bronze", 4),
	BismuthBronze("bismuthbronze", 5),
	BlackBronze("blackbronze", 6),
	Brass("brass", 7),
	Silver("silver", 8),
	Gold("gold", 9),
	RoseGold("rosegold", 10),
	Lead("lead", 11),
	Nickel("nickel", 12),
	Platinum("platinum", 13),
	WroughtIron("wroughtiron", 14),
	PigIron("pigiron", 15),
	Steel("steel", 16),
	SterlingSilver("sterlingsilver", 17),
	Zinc("zinc", 18),
	BlackSteel("blacksteel", 19),
	RedSteel("redsteel", 20),
	BlueSteel("bluesteel", 21);

	private int meta;
	private String name;

	MetalEnum(String name, int meta) {
		this.name = name;
		this.meta = meta;
	}

	@Override
	public String getName() {
		return name;
	}

	public int getMeta() {
		return meta;
	}

	public static String getName(int itemDamage) {
		MetalEnum a = get(itemDamage);
		if (a == null) {
			return null;
		}
		return a.getName();
	}

	public static MetalEnum get(int itemDamage) {
		for (MetalEnum e : MetalEnum.values()) {
			if (e.meta == itemDamage) {
				return e;
			}
		}
		return null;
	}

	public static List<String> getList() {
		ArrayList<String> list = new ArrayList<String>();
		for (MetalEnum e : MetalEnum.values()) {
			list.add(e.name);
		}
		return list;
	}
}

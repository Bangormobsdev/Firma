package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum ToolMaterials implements IStringSerializable {
	Stone("stone", 3f, 0), Copper("copper", 4f, 1), Bronze("bronze", 13f, 2), BismuthBronze("bismuthbronze", 13f, 3), BlackBronze("blackbronze", 13f,
			4), WroughtIron("wroughtiron", 16f,
					5), Steel("steel", 20f, 6), BlackSteel("blacksteel", 30f, 7), BlueSteel("bluesteel", 50f, 8), RedSteel("redsteel", 50f, 9);

	String name;
	float harvestLevel;
	private int meta;

	ToolMaterials(String name, float harvestLevel, int meta) {
		this.name = name;
		this.harvestLevel = harvestLevel;
		this.meta = meta;
	}

	@Override
	public String getName() {
		return name;
	}

	public float getHarvestLevel() {
		return harvestLevel;
	}

	public int getMeta() {
		return meta;
	}

	public static String getName(int itemDamage) {
		ToolMaterials a = get(itemDamage);
		if (a == null) {
			return null;
		}
		return a.getName();
	}

	public static ToolMaterials get(int itemDamage) {
		for (ToolMaterials e : ToolMaterials.values()) {
			if (e.meta == itemDamage) {
				return e;
			}
		}
		return null;
	}
}

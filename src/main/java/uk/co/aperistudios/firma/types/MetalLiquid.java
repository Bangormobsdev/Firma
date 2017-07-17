package uk.co.aperistudios.firma.types;

public enum MetalLiquid {
	BISMUTHBRONZE(0xff4a8054, "BismuthBronze"),
	BISMUTH(0xff628892, "Bismuth"),
	BLACKBRONZE(0xff754764, "BlackBronze"),
	BLACKSTEEL(0xff424242, "BlackSteel"),
	BLUESTEEL(0xff4874af, "BlueSteel"),
	BRASS(0xff7d753a, "Brass"),
	BRONZE(0xff7d5e37, "Bronze"),
	COPPER(0xffb95133, "Copper"),
	GOLD(0xffd9d900, "Gold"),
	LEAD(0xff5e6467, "Lead"),
	NICKEL(0xff757562, "Nickel"),
	PIGIRON(0xff544c4e, "PigIron"),
	PLATINUM(0xffacb4bd, "Platinum"),
	REDSTEEL(0xffbb3434, "RedSteel"),
	ROSEGOLD(0xffda945d, "RoseGold"),
	SILVER(0xffa6a6a6, "Silver"),
	STEEL(0xff555861, "Steel"),
	STERLINGSILVER(0xffb0a196, "SterlingSilver"),
	TIN(0xffc7d1dd, "Tin"),
	UNKNOWN(0xff39322b, "Unknown"),
	WROUGHTIRON(0xff777777, "WroughtIron"),
	ZINC(0xffbfc0c3, "Zinc");

	private int col;
	private String name;

	MetalLiquid(int col, String name) {
		this.col = col;
		this.name = name;
	}

	MetalLiquid(String name) {
		this(0xffd29062, name);
	}

	public int getCol() {
		return col;
	}

	public String getName() {
		return name;
	}
}

package uk.co.aperistudios.firma.types;

import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public enum SolidMaterialEnum implements IStringSerializable {
	Acacia("acacia", ToolType.Axe, Material.WOOD),
	Ash("ash", ToolType.Axe, Material.WOOD),
	Aspen("aspen", ToolType.Axe, Material.WOOD),
	Birch("birch", ToolType.Axe, Material.WOOD),
	Chestnut("chestnut", ToolType.Axe, Material.WOOD),
	Douglasfir("douglasfir", ToolType.Axe, Material.WOOD),
	Hickory("hickory", ToolType.Axe, Material.WOOD),
	Kapok("kapok", ToolType.Axe, Material.WOOD),
	Maple("maple", ToolType.Axe, Material.WOOD),
	Oak("oak", ToolType.Axe, Material.WOOD),
	Pine("pine", ToolType.Axe, Material.WOOD),
	Sequoia("sequoia", ToolType.Axe, Material.WOOD),
	Spruce("spruce", ToolType.Axe, Material.WOOD),
	Sycamore("sycamore", ToolType.Axe, Material.WOOD),
	Whitecedar("whitecedar", ToolType.Axe, Material.WOOD),
	Whiteelm("whiteelm", ToolType.Axe, Material.WOOD),
	Willow("willow", ToolType.Axe, Material.WOOD),
	Andesite("andesite", ToolType.Pick, Material.ROCK),
	Basalt("basalt", ToolType.Pick, Material.ROCK),
	Chalk("chalk", ToolType.Pick, Material.ROCK),
	Chert("chert", ToolType.Pick, Material.ROCK),
	Claystone("claystone", ToolType.Pick, Material.ROCK),
	Conglomerate("conglomerate", ToolType.Pick, Material.ROCK),
	Dacite("dacite", ToolType.Pick, Material.ROCK),
	Diorite("diorite", ToolType.Pick, Material.ROCK),
	Dolomite("dolomite", ToolType.Pick, Material.ROCK),
	Gabbro("gabbro", ToolType.Pick, Material.ROCK),
	Gneiss("gneiss", ToolType.Pick, Material.ROCK),
	Granite("granite", ToolType.Pick, Material.ROCK),
	Limestone("limestone", ToolType.Pick, Material.ROCK),
	Marble("marble", ToolType.Pick, Material.ROCK),
	Phyllite("phyllite", ToolType.Pick, Material.ROCK),
	Quartzite("quartzite", ToolType.Pick, Material.ROCK),
	Rhyolite("rhyolite", ToolType.Pick, Material.ROCK),
	RockSalt("rocksalt", ToolType.Pick, Material.ROCK),
	Schist("schist", ToolType.Pick, Material.ROCK),
	Shale("shale", ToolType.Pick, Material.ROCK),
	Slate("slate", ToolType.Pick, Material.ROCK),
	Copper("copper", ToolType.Pick, Material.IRON),
	Tin("tin", ToolType.Pick, Material.IRON),
	Bismuth("bismuth", ToolType.Pick, Material.IRON),
	Bronze("bronze", ToolType.Pick, Material.IRON),
	BismuthBronze("bismuthbronze", ToolType.Pick, Material.IRON),
	BlackBronze("blackbronze", ToolType.Pick, Material.IRON),
	Brass("brass", ToolType.Pick, Material.IRON),
	Silver("silver", ToolType.Pick, Material.IRON),
	Gold("gold", ToolType.Pick, Material.IRON),
	RoseGold("rosegold", ToolType.Pick, Material.IRON),
	Lead("lead", ToolType.Pick, Material.IRON),
	Nickel("nickel", ToolType.Pick, Material.IRON),
	Platinum("platinum", ToolType.Pick, Material.IRON),
	WroughtIron("wroughtiron", ToolType.Pick, Material.IRON),
	PigIron("pigiron", ToolType.Pick, Material.IRON),
	Steel("steel", ToolType.Pick, Material.IRON),
	SterlingSilver("sterlingsilver", ToolType.Pick, Material.IRON),
	Zinc("zinc", ToolType.Pick, Material.IRON),
	BlackSteel("blacksteel", ToolType.Pick, Material.IRON),
	RedSteel("redsteel", ToolType.Pick, Material.IRON),
	BlueSteel("bluesteel", ToolType.Pick, Material.IRON);

	private String name;
	private ToolType tool;
	private Material material;

	SolidMaterialEnum(String name, ToolType breaker, Material m) {
		this.name = name;
		this.tool = breaker;
		this.material = m;
	}

	@Override
	public String getName() {
		return name;
	}

	public ToolType getTool() {
		return tool;
	}

	public Material getMaterial() {
		return material;
	}

	public static String[] names() {
		String[] names = new String[SolidMaterialEnum.values().length];
		for (int i = 0; i < names.length; i++) {
			names[i] = SolidMaterialEnum.values()[i].getName();
		}
		return names;
	}

	public static SolidMaterialEnum get(String name) {
		for (SolidMaterialEnum sme : values()) {
			if (sme.name.equals(name)) {
				return sme;
			}
		}
		return null;
	}

	public static SolidMaterialEnum get(WoodEnum we) {
		switch (we) {
		case Acacia:
			return Acacia;
		case Ash:
			return Ash;
		case Aspen:
			return Aspen;
		case Birch:
			return Birch;
		case Chestnut:
			return Chestnut;
		case Douglasfir:
			return Douglasfir;
		case Hickory:
			return Hickory;
		case Kapok:
			return Kapok;
		case Maple:
			return Maple;
		case Oak:
			return Oak;
		case Pine:
			return Pine;
		case Sequoia:
			return Sequoia;
		case Spruce:
			return Spruce;
		case Sycamore:
			return Sycamore;
		case Whitecedar:
			return Whitecedar;
		case Whiteelm:
			return Whiteelm;
		}
		return null;
	}

	public static SolidMaterialEnum get(WoodEnum2 we2) {
		return Willow;
	}

	public static SolidMaterialEnum get(RockEnum re) {
		switch (re) {
		case Andesite:
			return Andesite;
		case Basalt:
			return Basalt;
		case Chalk:
			return Chalk;
		case Chert:
			return Chert;
		case Claystone:
			return Claystone;
		case Conglomerate:
			return Conglomerate;
		case Dacite:
			return Dacite;
		case Diorite:
			return Diorite;
		case Dolomite:
			return Dolomite;
		case Gabbro:
			return Gabbro;
		case Gneiss:
			return Gneiss;
		case Granite:
			return Granite;
		case Limestone:
			return Limestone;
		case Marble:
			return Marble;
		case Phyllite:
			return Phyllite;
		case Quartzite:
			return Quartzite;
		}
		return null;
	}

	public static SolidMaterialEnum get(RockEnum2 re2) {
		switch (re2) {
		case Rhyolite:
			return Rhyolite;
		case RockSalt:
			return RockSalt;
		case Schist:
			return Schist;
		case Shale:
			return Shale;
		case Slate:
			return Slate;
		}
		return null;
	}

}

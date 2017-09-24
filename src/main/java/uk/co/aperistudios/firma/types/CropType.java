package uk.co.aperistudios.firma.types;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.IStringSerializable;

public enum CropType implements IStringSerializable {
	BARLEY("barley", 8, 4, 50),
	CABBAGE("cabbage", 6, 3, 40),
	CARROT("carrot", 5, -5, 40),
	CORN("corn", 6, 10, 60),
	GARLIC("garlic", 5, -5, 40),
	GREENBEAN("greenbean", 7, 8, 50),
	JUTE("jute", 6, 13, 80),
	OAT("oat", 8, 5, 50),
	ONION("onion", 7, 3, 40),
	REDBELLPEPPER("redbellpepper", 7, 3, 40),
	YELLOWBELLPEPPER("yellowbellpepper", 7, 3, 40),
	POTATO("potato", 7, -5, 40),
	RICE("rice", 8, 5, 50),
	RYE("rye", 8, 5, 50),
	SOYBEAN("soybean", 7, 7, 50),
	SQUASH("squash", 7, 0, 40),
	SUGARCANE("sugarcane", 8, 10, 60),
	TOMATO("tomato", 8, 6, 40),
	WHEAT("wheat", 8, 10, 60);

	private String name;
	private int stages;
	private float lowTemp;
	private float highTemp;

	CropType(String name, int stages, float lowTemp, float highTemp) {
		this.name = name;
		this.stages = stages;
		this.lowTemp = lowTemp;
		this.highTemp = highTemp;
	}

	@Override
	public String getName() {
		return name;
	}

	public int getStages() {
		return stages;
	}

	public float getLowTemp() {
		return lowTemp;
	}

	public float getHighTemp() {
		return highTemp;
	}

	public static List<String> strings() {
		ArrayList<String> s = new ArrayList<String>();
		for (CropType ct : values()) {
			s.add(ct.getName());
		}
		return s;
	}

}

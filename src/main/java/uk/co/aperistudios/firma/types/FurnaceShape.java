package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum FurnaceShape implements IStringSerializable {
	SMALL("small"), MEDIUM("medium"), LARGE("large");

	private String size;

	FurnaceShape(String size) {
		this.size = size;
	}

	@Override
	public String getName() {
		return size;
	}

	public static int get(FurnaceShape shape) {
		int i = -1;
		int c = 0;
		for(FurnaceShape s : FurnaceShape.values()) {
			if(s == shape) {
				i = c;
				break;
			}
			c++;
		}
		return i;
	}

}

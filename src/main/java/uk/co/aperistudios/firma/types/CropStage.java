package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum CropStage implements IStringSerializable {
	SEED("seed"), STAGE1("stage1"), STAGE2("stage2"), STAGE3("stage3"), STAGE4("stage4"), STAGE5("stage5"), STAGE6("stage6"), STAGE7("stage7");

	private String name;

	CropStage(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}

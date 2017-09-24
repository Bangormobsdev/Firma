package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

/**
 * A list of INGREDIENT foods.
 * 
 * @author triggerhapp
 *
 */

public enum FoodEnum implements IStringSerializable {
	// Raw Animal
	RAWPORK("rawpork", true, NutritionEnum.PROTEIN, NutritionEnum.HAZARD),
	RAWCHICKEN("rawchicken", true, NutritionEnum.PROTEIN, NutritionEnum.HAZARD),
	RAWBEEF("rawbeef", true, NutritionEnum.PROTEIN, NutritionEnum.HAZARD),
	RAWCALAMARI("rawcalamari", true, NutritionEnum.PROTEIN, NutritionEnum.HAZARD),
	RAWFISH("rawfish", true, NutritionEnum.PROTEIN, NutritionEnum.HAZARD),
	RAWHORSE("rawhorse", true, NutritionEnum.PROTEIN, NutritionEnum.HAZARD),
	RAWMUTTON("rawmutton", true, NutritionEnum.PROTEIN, NutritionEnum.HAZARD),
	// Cooked Animal
	PORK("pork", true, NutritionEnum.PROTEIN),
	CHICKEN("chicken", true, NutritionEnum.PROTEIN),
	BEEF("beef", true, NutritionEnum.PROTEIN),
	CALAMARI("calamari", true, NutritionEnum.PROTEIN),
	FISH("fish", true, NutritionEnum.PROTEIN),
	HORSE("horse", true, NutritionEnum.PROTEIN),
	MUTTON("mutton", true, NutritionEnum.PROTEIN),
	// Cultivated foods
	BARLEY("barley", false, NutritionEnum.GRAIN),
	CABBAGE("cabbage", true, NutritionEnum.VEGETABLE),
	CARROT("carrot", true, NutritionEnum.VEGETABLE),
	GARLIC("garlic", true, NutritionEnum.VEGETABLE),
	GREENBEAN("greenbean", true, NutritionEnum.VEGETABLE),
	CORN("corn", true, NutritionEnum.GRAIN),
	OAT("oat", false, NutritionEnum.GRAIN),
	ONION("onion", true, NutritionEnum.VEGETABLE),
	POTATO("potato", true, NutritionEnum.VEGETABLE),
	REDBELLPEPPER("redbellpepper", true, NutritionEnum.VEGETABLE),
	GREENBELLPEPPER("greenbellpepper", true, NutritionEnum.VEGETABLE),
	RICE("rice", false, NutritionEnum.GRAIN),
	RYE("rye", false, NutritionEnum.GRAIN),
	SOYBEAN("soybean", true, NutritionEnum.PROTEIN),
	SQUASH("squash", true, NutritionEnum.VEGETABLE),
	SUGARCANE("sugarcane", false, NutritionEnum.CONFECTION),
	TOMATO("tomato", true, NutritionEnum.VEGETABLE),
	WHEAT("wheat", false, NutritionEnum.GRAIN),
	YELLOWBELLPEPPER("yellowbellpepper", true, NutritionEnum.VEGETABLE),
	SEAWEED("seaweed", true, NutritionEnum.VEGETABLE),
	// Processed Cultivated foods
	SUGAR("sugar", true, NutritionEnum.CONFECTION, NutritionEnum.HAZARD),
	GROUNDBARLEY("groundbarley", false, NutritionEnum.GRAIN),
	GROUNDMAIZE("groundmaize", false, NutritionEnum.GRAIN),
	GROUNDOAT("groundoat", false, NutritionEnum.GRAIN),
	GROUNDRICE("groundrice", false, NutritionEnum.GRAIN),
	GROUNDRYE("groundrye", false, NutritionEnum.GRAIN),
	GROUNDWHEAT("groundwheat", false, NutritionEnum.GRAIN),
	DOUGHBARLEY("doughbarley", true, NutritionEnum.GRAIN),
	DOUGHMAIZE("doughmaize", true, NutritionEnum.GRAIN),
	DOUGHOAT("doughoat", true, NutritionEnum.GRAIN),
	DOUGHRICE("doughrice", true, NutritionEnum.GRAIN),
	DOUGHRYE("doughrye", true, NutritionEnum.GRAIN),
	DOUGHWHEAT("doughwheat", true, NutritionEnum.GRAIN),
	BREADBARLEY("breadbarley", true, NutritionEnum.GRAIN),
	BREADMAIZE("breadmaize", true, NutritionEnum.GRAIN),
	BREADOAT("breadoat", true, NutritionEnum.GRAIN),
	BREADRICE("breadrice", true, NutritionEnum.GRAIN),
	BREADRYE("breadrye", true, NutritionEnum.GRAIN),
	BREADWHEAT("breadwheat", true, NutritionEnum.GRAIN),
	// Dairy farming
	EGG("egg", false, NutritionEnum.PROTEIN),
	COOKEDEGG("cookedegg", true, NutritionEnum.PROTEIN),
	CHEESE("cheese", true, NutritionEnum.DAIRY),
	MILK("milk", true, NutritionEnum.DAIRY, NutritionEnum.LIQUID),
	// Wild crops
	BANANA("banana", true, NutritionEnum.FRUIT),
	BLACKBERRY("blackberry", true, NutritionEnum.FRUIT),
	BLUEBERRY("blueberry", true, NutritionEnum.FRUIT),
	BUNCHBERRY("bunchberry", true, NutritionEnum.FRUIT),
	CHERRY("cherry", true, NutritionEnum.FRUIT),
	CLOUDBERRY("cloudberry", true, NutritionEnum.FRUIT),
	CRANBERRY("cranberry", true, NutritionEnum.FRUIT),
	ELDERBERRY("elderberry", true, NutritionEnum.FRUIT),
	GOOSEBERRY("gooseberry", true, NutritionEnum.FRUIT),
	GREENAPPLE("greenapple", true, NutritionEnum.FRUIT),
	OLIVE("olive", false, NutritionEnum.FRUIT),
	ORANGE("orange", true, NutritionEnum.FRUIT),
	PEACH("peach", true, NutritionEnum.FRUIT),
	PLUM("plum", true, NutritionEnum.FRUIT),
	RASPBERRY("raspberry", true, NutritionEnum.FRUIT),
	REDAPPLE("redapple", true, NutritionEnum.FRUIT),
	STRAWBERRY("strawberry", true, NutritionEnum.FRUIT),
	WINTERGREENBERRY("wintergreenberry", true, NutritionEnum.FRUIT);

	private String name;
	private boolean edible;
	private NutritionEnum[] types;

	private FoodEnum(String name, boolean edibleForm, NutritionEnum type) {
		this.name = name;
		this.edible = edibleForm;
		this.types = new NutritionEnum[] { type };
	}

	private FoodEnum(String name, boolean edibleForm, NutritionEnum... types) {
		this.name = name;
		this.edible = edibleForm;
		this.types = types;
	}

	@Override
	public String getName() {
		return name;
	}

	public boolean isEdible() {
		return edible;
	}

}

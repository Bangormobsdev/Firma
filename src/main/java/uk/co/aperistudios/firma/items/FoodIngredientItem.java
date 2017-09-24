package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.types.FoodEnum;
import uk.co.aperistudios.firma.types.ItemSize;

public class FoodIngredientItem extends MetaItem {

	public FoodIngredientItem() {
		super("food", ItemSize.SMALL);
		this.setSubs(FoodEnum.strings());
	}

}

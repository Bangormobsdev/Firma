package uk.co.aperistudios.firma.blocks;

import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;

public class CrucibleBlock extends BaseBlock {

	public CrucibleBlock() {
		super(Material.ROCK, "crucible");
	}

	@Override
	public ArrayList<String> getVariantNames() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("normal");
		return list;
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return "";
	}

	@Override
	public String getMetaName(int meta) {
		return "normal";
	}

}

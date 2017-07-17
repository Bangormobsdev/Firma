package uk.co.aperistudios.firma.items;

import java.util.Arrays;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ItemSize;

public abstract class MetaItem extends FirmaItem {
	private List<String> subs;

	public MetaItem(String name, ItemSize iSize) {
		super(name, iSize);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(FirmaMod.itemTab);
	}

	public void setSubs(List<String> list) {
		this.subs = list;
	}

	public void setSubs(String[] list) {
		setSubs(Arrays.asList(list));
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	public int getSubMeta(String s) {
		for (int i = 0; i < this.subs.size(); i++) {
			if (this.subs.get(i).equalsIgnoreCase(s)) {
				return i;
			}
		}
		return -1;
	}

	public int getSubCount() {
		return subs.size();
	}

	public String getSubName(int i) {
		return subs.get(i);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return FirmaMod.MODID + ":" + name + "." + subs.get(stack.getItemDamage());
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for (int c = 0; c < subs.size(); c++) {
			ItemStack is = new ItemStack(this, 1, c);
			subItems.add(is);
		}
	}

	public String getMainVariant() {
		return "variants";
	}

	public boolean skipRegister() {
		return false;
	}
}

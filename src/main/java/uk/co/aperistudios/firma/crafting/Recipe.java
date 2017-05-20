package uk.co.aperistudios.firma.crafting;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;

public class Recipe {
	CraftMat material;
	String itemName;
	String metaSub;
	String itemName2;
	String metaSub2;
	RecipeShape rs;
	ItemStack is;

	public static Recipe makeRecipeTwoInput(ItemStack output, CraftMat mat, ItemStack is, ItemStack is2, RecipeShape rs) {
		Recipe r = new Recipe();
		r.is = output.copy();
		r.material = mat;
		r.rs = rs;

		if (is != null) {
			if (r.material == CraftMat.ANVIL) {
				if (is.getItem() == FirmaMod.ingot) {
					r.itemName = "ingot";
					r.metaSub = FirmaMod.ingot.getSubName(is.getItemDamage());
				} else if (is.getItem() == FirmaMod.doubleingot) {
					r.itemName = "doubleingot";
					r.metaSub = FirmaMod.doubleingot.getSubName(is.getItemDamage());
				} else if (is.getItem() == FirmaMod.metalsheet) {
					r.itemName = "metalsheet";
					r.metaSub = FirmaMod.metalsheet.getSubName(is.getItemDamage());
				}
				if (is2.getItem() == FirmaMod.ingot) {
					r.itemName2 = "ingot";
					r.metaSub2 = FirmaMod.ingot.getSubName(is2.getItemDamage());
				} else if (is2.getItem() == FirmaMod.doubleingot) {
					r.itemName2 = "doubleingot";
					r.metaSub2 = FirmaMod.doubleingot.getSubName(is2.getItemDamage());
				} else if (is2.getItem() == FirmaMod.metalsheet) {
					r.itemName2 = "metalsheet";
					r.metaSub2 = FirmaMod.metalsheet.getSubName(is2.getItemDamage());
				}

			} else if (r.material == CraftMat.STONE && is.getItem() == FirmaMod.pebble) {
				throw new RuntimeException("Cannot have a two-input stone knap");
			} else if (r.material == CraftMat.CLAY && is.getItem() == FirmaMod.clay) {
				throw new RuntimeException("Cannot have a two-input clay shape");
			} else if (r.material == CraftMat.LEATHER) { // Takes no subtypes
				throw new RuntimeException("Cannot have a dual input leather shape");
			} else {
				throw new RuntimeException("Itemstack " + is + " does not match Craft Mat " + mat);
			}
		}
		CraftingManager.addRecipe(r);
		return r;
	}

	public static Recipe makeRecipeOneInput(ItemStack output, CraftMat mat, ItemStack is, RecipeShape rs) {
		Recipe r = new Recipe();
		r.is = output.copy();
		r.material = mat;
		r.rs = rs;

		if (is != null) {
			if (r.material == CraftMat.STONE && is.getItem() == FirmaMod.pebble) {
				r.itemName = "pebble";
				r.metaSub = FirmaMod.pebble.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.CLAY && is.getItem() == FirmaMod.clay) {
				r.itemName = "clay";
				r.metaSub = FirmaMod.clay.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.ingot) {
				r.itemName = "ingot";
				r.metaSub = FirmaMod.ingot.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.doubleingot) {
				r.itemName = "doubleingot";
				r.metaSub = FirmaMod.doubleingot.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.metalsheet) {
				r.itemName = "metalsheet";
				r.metaSub = FirmaMod.metalsheet.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.LEATHER) { // Takes no subtypes
															// yet
				System.err.println("Leather cannot have a specified ItemStack");
				assert r == null;
			} else {
				// Specifying an itemstack with a craftmat that does not match.
				// So specifying leather crafting with metal or stonecrafting
				// with a sandwich
				System.err.println("Itemstack " + is + " does not match Craft Mat " + mat);
				assert r == null;
			}
		}
		CraftingManager.addRecipe(r);
		return r;
	}

	public CraftMat getCraftMat() {
		return material;
	}

	public String getItemName() {
		return itemName;
	}

	public String getSubItemName() {
		return metaSub;
	}

	public int getWidth() {
		return rs.width;
	}

	public int getHeight() {
		return rs.height;
	}

	public RecipeShape getShape() {
		return rs;
	}

	public ItemStack getOutput() {
		return is.copy();
	}

	public boolean payPrice(EntityPlayerMP p) {
		ItemStack i = p.getHeldItemMainhand();
		if (material == CraftMat.STONE) {
			if (i.getItem() == FirmaMod.pebble) {
				if (i.getCount() > 1) {
					i.setCount(i.getCount() - 1);
					return true;
				}
			}
			return false;
		} else if (material == CraftMat.CLAY) {
			if (i.getItem() == FirmaMod.clay) {
				if (i.getCount() > 5) {
					i.setCount(i.getCount() - 5);
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public boolean payPrice(TileEntity tileEntity) {
		if (material == CraftMat.ANVIL) {
			AnvilTileEntity ate = (AnvilTileEntity) tileEntity;
			ItemStack is1 = ate.getStackInSlot(0);
			if (is1.getItem() == FirmaMod.ingot && FirmaMod.ingot.getSubName(is1.getMetadata()).equals(metaSub)) { // Ingot
																													// of
																													// the
																													// right
																													// sub
				ate.setInventorySlotContents(0, ItemStack.EMPTY);
				return true;
			}
		}
		return false;
	}

}

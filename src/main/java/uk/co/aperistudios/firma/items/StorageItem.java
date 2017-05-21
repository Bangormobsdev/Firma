package uk.co.aperistudios.firma.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.FloorStorageTileEntity;
import uk.co.aperistudios.firma.container.HandlerGui;
import uk.co.aperistudios.firma.types.ItemSize;

public class StorageItem extends FirmaItem {
	int items;

	public StorageItem(String name, ItemSize iSize, int items) {
		super(name, iSize);
		this.setMaxDamage(0);
		this.setMaxStackSize(0);
		this.setHasSubtypes(false);
		this.setCreativeTab(FirmaMod.itemTab);
		this.items = items;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.isSneaking() && items == 4) { // Only vessels use floor
			IBlockState bs = worldIn.getBlockState(pos);

			if (bs.getBlock() != FirmaMod.floorStorage) {
				// We're attempting to start new storage
				if (facing != EnumFacing.UP || !(bs.getBlock() instanceof BaseBlock) || !((BaseBlock) bs.getBlock()).canSupportVessels(bs)) {
					return EnumActionResult.PASS;
				}
				BlockPos bp = pos.add(0, 1, 0);
				IBlockState bs2 = worldIn.getBlockState(bp);
				if (bs2.getBlock() != Blocks.AIR) {
					return EnumActionResult.PASS;
				}
				worldIn.setBlockState(bp, FirmaMod.floorStorage.getDefaultState());
				pos = bp; // Set the selected block as the new storage and
							// continue on
				bs = bs2;
			}
			FloorStorageTileEntity fste = (FloorStorageTileEntity) worldIn.getTileEntity(pos);
			int index = -1;
			if (hitX < .5 && hitZ < .5) {
				index = 0;
			} else if (hitX < .5 && hitZ > .5) {
				index = 1;
			} else if (hitX > .5 && hitZ < .5) {
				index = 2;
			} else if (hitX > .5 && hitZ > .5) {
				index = 3;
			}
			if (index == -1) {
				return EnumActionResult.PASS;
			}
			ItemStack removeItem = fste.removeStackFromSlot(index);
			if (!removeItem.isEmpty()) {
				if (!player.inventory.addItemStackToInventory(removeItem)) {
					EntityItem ei = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, removeItem);
					worldIn.spawnEntity(ei);
				}
			}
			fste.setInventorySlotContents(index, player.getHeldItem(hand));
			player.setHeldItem(hand, ItemStack.EMPTY);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack is = player.getHeldItem(handIn);
		if (player.isSneaking()) { // Attempt to place the item.

		} else {
			if (items == 4) {
				player.openGui(FirmaMod.instance, HandlerGui.GUI_ITEM_4, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
			} else if (items == 12) {
				player.openGui(FirmaMod.instance, HandlerGui.GUI_ITEM_12, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, is);
	}

	public int getSizeInventory() {
		return items;
	}
}

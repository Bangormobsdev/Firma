package uk.co.aperistudios.firma.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;
import uk.co.aperistudios.firma.types.ItemSize;

public class HandlerGui implements IGuiHandler {
	public static final int GUI_KNAPPING = 0;
	public static final int GUI_SMITHING = 1;
	public static final int GUI_ITEM_4 = 2;
	public static final int GUI_ITEM_12 = 3;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_KNAPPING) {
			return new ContainerKnapping(player.inventory, x, y, z);
		} else if (ID == GUI_SMITHING) {
			return new ContainerAnvil(player.inventory, world, new BlockPos(x, y, z), (AnvilTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == GUI_ITEM_4) {
			return new ContainerItemStorage(player.inventory, 4, ItemSize.TINY);
		} else if (ID == GUI_ITEM_12) {
			return new ContainerItemStorage(player.inventory, 12, ItemSize.MEDIUM);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if (ID == GUI_KNAPPING) {
			return new GuiKnapping(player, x, y, z);
		} else if (ID == GUI_SMITHING) {
			return new GuiAnvil(player, player.world, (AnvilTileEntity) world.getTileEntity(pos));
		} else if (ID == GUI_ITEM_4) {
			return new GuiItemStorage(new ContainerItemStorage(player.inventory, 4, ItemSize.TINY), 4, ItemSize.TINY);
		} else if (ID == GUI_ITEM_12) {
			return new GuiItemStorage(new ContainerItemStorage(player.inventory, 12, ItemSize.MEDIUM), 12, ItemSize.MEDIUM);
		}
		return null;
	}
}
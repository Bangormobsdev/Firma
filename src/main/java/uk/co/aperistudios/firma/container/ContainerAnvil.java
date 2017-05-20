package uk.co.aperistudios.firma.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;

public class ContainerAnvil extends Container {

	private AnvilTileEntity te;

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}

	@SideOnly(Side.CLIENT)
	public ContainerAnvil(InventoryPlayer playerInv, World worldIn, AnvilTileEntity ate) {
		this(playerInv, worldIn, BlockPos.ORIGIN, ate);

	}

	public ContainerAnvil(InventoryPlayer playerInv, World worldIn, BlockPos pos, AnvilTileEntity ate) {
		this.te = ate;
		this.addSlotToContainer(new SlotAnvilInput(ate, 0, 19, 22));
		this.addSlotToContainer(new SlotAnvilInput(ate, 1, 19, 41));
		this.addSlotToContainer(new SlotOutput(ate, 2, 140, 31));
		PlayerInv.buildInventoryLayout(this, playerInv, GuiAnvil.guiwidth, GuiAnvil.guiheight, true, true);

	}

	@Override
	protected void retrySlotClick(int slotId, int clickedButton, boolean mode, EntityPlayer playerIn) {
	}
}

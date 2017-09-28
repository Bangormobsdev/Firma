package uk.co.aperistudios.firma.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.types.CropType;
import uk.co.aperistudios.firma.types.ItemSize;

public class SeedItem extends MetaItem {

	public SeedItem() {
		super("seed", ItemSize.TINY);
		this.setSubs(CropType.strings());
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing != EnumFacing.UP) {
			return EnumActionResult.FAIL;
		}
		BlockPos cropAt = pos.up();
		IBlockState bsUp = worldIn.getBlockState(cropAt);
		IBlockState bsDown = worldIn.getBlockState(pos);

		if (bsUp.getBlock() == Blocks.AIR) {
			//if(bsDown.getBlock() == FirmaMod.)
		}
		return EnumActionResult.PASS;
	}

}

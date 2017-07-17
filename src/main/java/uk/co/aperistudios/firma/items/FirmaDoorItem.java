package uk.co.aperistudios.firma.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.BlockDoor.EnumHingePosition;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.lessboring.FirmaDoor;
import uk.co.aperistudios.firma.blocks.tileentity.FirmaDoorTileEntity;
import uk.co.aperistudios.firma.types.ItemSize;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class FirmaDoorItem extends MetaItem implements ItemState {

	public FirmaDoorItem() {
		super("firmadooritem", ItemSize.LARGE);
		this.setSubs(SolidMaterialEnum.names());
		this.setCreativeTab(FirmaMod.deviceTab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (side != EnumFacing.UP) {
			return EnumActionResult.FAIL;
		}
		IBlockState iblockstate = world.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (!block.isReplaceable(world, pos)) {
			pos = pos.offset(side);
		}

		ItemStack itemstack = player.getHeldItem(hand);

		if (player.canPlayerEdit(pos, side, itemstack) && FirmaMod.door.canPlaceBlockAt(world, pos)) {
			EnumFacing enumfacing = EnumFacing.fromAngle(player.rotationYaw);
			int i = enumfacing.getFrontOffsetX();
			int j = enumfacing.getFrontOffsetZ();
			boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
			placeDoor(world, pos, enumfacing, flag, SolidMaterialEnum.get(this.getSubName(itemstack.getItemDamage())));
			SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
			world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.FAIL;
	}

	public static void placeDoor(World worldIn, BlockPos pos, EnumFacing facing, boolean isRightHinge, SolidMaterialEnum material) {
		BlockPos blockpos = pos.offset(facing.rotateY());
		BlockPos blockpos1 = pos.offset(facing.rotateYCCW());
		int i = (worldIn.getBlockState(blockpos1).isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos1.up()).isNormalCube() ? 1 : 0);
		int j = (worldIn.getBlockState(blockpos).isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos.up()).isNormalCube() ? 1 : 0);
		boolean flag = worldIn.getBlockState(blockpos1).getBlock() == FirmaMod.door || worldIn.getBlockState(blockpos1.up()).getBlock() == FirmaMod.door;
		boolean flag1 = worldIn.getBlockState(blockpos).getBlock() == FirmaMod.door || worldIn.getBlockState(blockpos.up()).getBlock() == FirmaMod.door;

		if ((!flag || flag1) && j <= i) {
			if (flag1 && !flag || j < i) {
				isRightHinge = false;
			}
		} else {
			isRightHinge = true;
		}

		BlockPos blockpos2 = pos.up();
		IBlockState iblockstate = FirmaMod.door.getDefaultState().withProperty(FirmaDoor.FACING, facing)
				.withProperty(FirmaDoor.HINGE, isRightHinge ? EnumHingePosition.RIGHT : EnumHingePosition.LEFT).withProperty(FirmaDoor.OPEN, false);
		worldIn.setBlockState(pos, iblockstate.withProperty(FirmaDoor.HALF, EnumDoorHalf.LOWER), 2);
		worldIn.setBlockState(blockpos2, iblockstate.withProperty(FirmaDoor.HALF, EnumDoorHalf.UPPER), 2);
		((FirmaDoorTileEntity) worldIn.getTileEntity(pos)).setMaterial(material);
		((FirmaDoorTileEntity) worldIn.getTileEntity(blockpos2)).setMaterial(material);
		worldIn.notifyNeighborsOfStateChange(pos, FirmaMod.door, false);
		worldIn.notifyNeighborsOfStateChange(blockpos2, FirmaMod.door, false);
	}

	@Override
	public boolean skipRegister() {
		return true;
	}

	@Override
	public ResourceLocation getModelPath() {
		return new ResourceLocation(FirmaMod.MODID + ":firmadoor");
	}

	@Override
	public String getModelSub() {
		return "";
	}

	@Override
	public Item getItem() {
		return this;
	}

	@Override
	public String getMainVariant() {
		return "material";
	}
}

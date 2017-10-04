package uk.co.aperistudios.firma.items;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.lessboring.MiniBlock;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class MiniBlockItem extends ItemBlock implements ItemState {

	public MiniBlockItem(Block block) {
		super(block);
		setRegistryName("miniblock");
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("miniblock");
		GameRegistry.register(this);
	}

	@Override
	public ResourceLocation getModelPath() {
		return new ResourceLocation("firma:miniblock");
	}

	@Override
	public Item getItem() {
		return this;
	}

	@Override
	public String getModelSub(int metadata) {
		return "lne=true,lnw=false,lse=false,lsw=false,material=" + SolidMaterialEnum.values()[metadata].getName() + ",une=false,unw=false,use=false,usw=false";
		// TODO Once again #inventory doesn't want to be put anywhere in here?
	}

	@Override
	public int getModelCount() {
		return SolidMaterialEnum.values().length;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		RayTraceResult rtr = this.rayTrace(worldIn, playerIn, true);
		if (rtr.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		}
		return new ActionResult<ItemStack>(onItemUse(playerIn, worldIn, rtr
				.getBlockPos(), handIn, rtr.sideHit, (float) (rtr.hitVec.xCoord - rtr.getBlockPos().getX()), (float) (rtr.hitVec.yCoord
						- rtr.getBlockPos().getY()), (float) (rtr.hitVec.zCoord - rtr.getBlockPos().getZ())), playerIn.getHeldItem(handIn));
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		ItemStack is = player.getHeldItem(hand);
		BlockPosHolder holder = new BlockPosHolder();
		PropertyBool part = getPartAndBlock(world, pos, side, hitX, hitY, hitZ, holder);
		IBlockState bs = world.getBlockState(holder.pos);
		if (bs.getBlock() == FirmaMod.miniBlocks) {
			// Ok. Are we even the same material?
			SolidMaterialEnum smeBlock = FirmaMod.miniBlocks.getSolidMaterial(world, holder.pos);
			SolidMaterialEnum smeHeld = getSolidMaterial(is);
			if (smeBlock != smeHeld) {
				return EnumActionResult.PASS;
			}
		} else if (bs.getBlock() != Blocks.AIR) {
			return EnumActionResult.PASS;
		} else {
			// So Air needs to be not-air
			IBlockState newbs = FirmaMod.miniBlocks.getDefaultState().withProperty(MiniBlock.mat, this.getSolidMaterial(is)).withProperty(part, true);
			world.setBlockState(holder.pos, newbs, 3); // Unintended pun was unintended
			return EnumActionResult.SUCCESS;
		}
		bs = world.getBlockState(holder.pos);
		world.setBlockState(holder.pos, bs.withProperty(part, true), 3);
		return EnumActionResult.SUCCESS;
	}

	/***
	 * Returns the part of the block the player is placing against.
	 * 
	 * @param worldIn
	 * @param posIn
	 * @param hitX
	 * @param hitY
	 * @param hitZ
	 * @param holder
	 * @return
	 */

	public PropertyBool getPartAndBlock(World worldIn, BlockPos posIn, EnumFacing side, float hitX, float hitY, float hitZ, BlockPosHolder holder) {
		holder.pos = posIn.toImmutable();
		return getPartAndBlockWithDirection(hitX, hitY, hitZ, side, holder);
	}

	public PropertyBool getPartAndBlockWithDirection(float partX, float partY, float partZ, EnumFacing side, BlockPosHolder holder) {
		partX = partX + (side == EnumFacing.EAST ? +0.25f : side == EnumFacing.WEST ? -0.25f : 0f);
		partY = partY + (side == EnumFacing.UP ? +0.25f : side == EnumFacing.DOWN ? -0.25f : 0f);
		partZ = partZ + (side == EnumFacing.SOUTH ? +0.25f : side == EnumFacing.NORTH ? -0.25f : 0f);
		System.out.println(partX + " " + partY + " " + partZ);
		if (partX > 1f) {
			partX -= 1f;
			holder.pos = holder.pos.add(1, 0, 0);
		}
		if (partX < 0f) {
			partX += 1f;
			holder.pos = holder.pos.add(-1, 0, 0);
		}
		if (partY > 1f) {
			partY -= 1f;
			holder.pos = holder.pos.add(0, 1, 0);
		}
		if (partY < 0f) {
			partY += 1f;
			holder.pos = holder.pos.add(0, -1, 0);
		}
		if (partZ > 1f) {
			partZ -= 1f;
			holder.pos = holder.pos.add(0, 0, 1);
		}
		if (partZ < 0f) {
			partZ += 1f;
			holder.pos = holder.pos.add(0, 0, -1);
		}
		return getPart(partX, partY, partZ);

	}

	/*
	 * NORTH IS -Z
	 * SOUTH IS +Z
	 * EAST IS +X
	 * WEST IS -X
	 * UP IS +Y
	 * DOWN IS -Y
	 */
	public PropertyBool getPart(float partX, float partY, float partZ) {
		if (partX < .5f && partY < .5f && partZ < .5f) {
			return MiniBlock.lnw;
		}
		if (partX > .5f && partY < .5f && partZ < .5f) {
			return MiniBlock.lne;
		}
		if (partX < .5f && partY < .5f && partZ > .5f) {
			return MiniBlock.lsw;
		}
		if (partX > .5f && partY < .5f && partZ > .5f) {
			return MiniBlock.lse;
		}
		if (partX < .5f && partY > .5f && partZ < .5f) {
			return MiniBlock.unw;
		}
		if (partX > .5f && partY > .5f && partZ < .5f) {
			return MiniBlock.une;
		}
		if (partX < .5f && partY > .5f && partZ > .5f) {
			return MiniBlock.usw;
		}
		if (partX > .5f && partY > .5f && partZ > .5f) {
			return MiniBlock.use;
		}
		return null;
	}

	public SolidMaterialEnum getSolidMaterial(ItemStack is) {
		return SolidMaterialEnum.values()[is.getItemDamage()];
	}

	public static class BlockPosHolder {
		public BlockPos pos = null;

		public BlockPosHolder() {

		}
	}
}

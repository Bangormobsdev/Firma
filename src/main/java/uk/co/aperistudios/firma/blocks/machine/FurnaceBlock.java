package uk.co.aperistudios.firma.blocks.machine;

import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.FurnaceTileEntity;
import uk.co.aperistudios.firma.types.FurnaceShape;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class FurnaceBlock extends BaseBlock {
	public static final PropertyEnum<SolidMaterialEnum> MATERIAL = PropertyEnum.<SolidMaterialEnum>create("material", SolidMaterialEnum.class);
	public static final PropertyEnum<FurnaceShape> SHAPE = PropertyEnum.<FurnaceShape>create("shape", FurnaceShape.class);

	public FurnaceBlock() {
		super(Material.ROCK, "firmafurnace");
	}

	@Override
	public ArrayList<String> getVariantNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (SolidMaterialEnum sme : SolidMaterialEnum.values()) {
			for (FurnaceShape fs : FurnaceShape.values()) {
				names.add(sme.getName() + "" + fs.getName());
			}
		}
		return names;
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return getMetaName(stack.getMetadata());
	}

	@Override
	public String getMetaName(int meta) {
		return getVariantNames().get(meta);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		FurnaceTileEntity te = (FurnaceTileEntity) worldIn.getTileEntity(pos);
		return state.withProperty(MATERIAL, te.getSME()).withProperty(SHAPE, te.getShape());
	}

}

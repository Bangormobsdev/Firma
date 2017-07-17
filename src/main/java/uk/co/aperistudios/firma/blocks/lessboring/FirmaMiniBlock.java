package uk.co.aperistudios.firma.blocks.lessboring;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.tileentity.MiniBlockTileEntity;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;

public class FirmaMiniBlock extends Block {
	public static final PropertyBool lse = PropertyBool.create("lse"), lne = PropertyBool.create("lne"), lsw = PropertyBool.create("lsw"),
			lnw = PropertyBool.create("lnw");
	public static final PropertyBool use = PropertyBool.create("use"), une = PropertyBool.create("une"), usw = PropertyBool.create("usw"),
			unw = PropertyBool.create("unw");
	public static final IProperty<SolidMaterialEnum> mat = PropertyEnum.create("material", SolidMaterialEnum.class);

	public FirmaMiniBlock() {
		super(Material.LEAVES);
		setRegistryName("mini" + mat.getName());
		GameRegistry.register(this);
		setDefaultState(this.blockState.getBaseState());
	}

	public IBlockState getHalfBlock(SolidMaterialEnum sme) {
		return this.blockState.getBaseState().withProperty(lse, true).withProperty(lne, true).withProperty(lsw, true).withProperty(lnw, true)
				.withProperty(mat, SolidMaterialEnum.Andesite);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		MiniBlockTileEntity mbte = (MiniBlockTileEntity) worldIn.getTileEntity(pos);
		state = mbte.apply(lse, state);
		state = mbte.apply(lne, state);
		state = mbte.apply(lsw, state);
		state = mbte.apply(lnw, state);
		state = mbte.apply(unw, state);
		state = mbte.apply(use, state);
		state = mbte.apply(une, state);
		state = mbte.apply(usw, state);
		return state.withProperty(mat, mbte.getMaterial());
	}

	public IBlockState getSteps(SolidMaterialEnum sme) {
		return this.blockState.getBaseState().withProperty(lse, true).withProperty(lne, true).withProperty(lsw, true).withProperty(lnw, true)
				.withProperty(une, true).withProperty(unw, true).withProperty(mat, SolidMaterialEnum.Andesite);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, lse, lne, lsw, lnw, use, une, usw, unw, mat);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		MiniBlockTileEntity mbte = (MiniBlockTileEntity) world.getTileEntity(pos);
		int a = mbte.getCount();
		drops.add(new ItemStack(FirmaMod.miniItems, a, MiniBlockTileEntity.getSub(mbte.getMaterial())));
		return drops;
	}
}

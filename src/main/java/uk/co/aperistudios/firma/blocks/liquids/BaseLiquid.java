package uk.co.aperistudios.firma.blocks.liquids;

import java.util.function.Consumer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;

public class BaseLiquid extends Fluid {
	public Item i;
	private int col;

	public BaseLiquid(String fluidName) {
		super(fluidName, new ResourceLocation(FirmaMod.MODID + ":blocks/water_still"), new ResourceLocation(FirmaMod.MODID + ":blocks/water_flow"));
	}

	public Block getFluidBlock() {
		return block;
	}

	public ResourceLocation getModelPath() {
		return new ResourceLocation(FirmaMod.MODID + ":fluid");
	}

	public Item getFluidItem() {
		return i;
	}

	@Override
	public int getColor() {
		return col;
	}

	@Override
	public int getColor(FluidStack stack) {
		return col;
	}

	@Override
	public int getColor(World world, BlockPos pos) {
		return col;
	}

	public static BaseLiquid create(String fluidName, Consumer<Fluid> f, int col, boolean isLava) {
		BaseLiquid nL = new BaseLiquid(fluidName);
		nL.setUnlocalizedName(FirmaMod.MODID + ":fluid." + fluidName);
		nL.col = col;
		f.accept(nL);
		FluidRegistry.registerFluid(nL);
		BaseBlockLiquid newblock;
		if (isLava) {
			newblock = new BaseBlockLiquid(nL, Material.LAVA, fluidName);
		} else {
			newblock = new BaseBlockLiquid(nL, Material.WATER, fluidName);
		}
		nL.block = newblock;
		nL.block.setRegistryName(FirmaMod.MODID + ":fluid." + fluidName);
		nL.block.setUnlocalizedName(FirmaMod.MODID + ":fluid." + fluidName);
		nL.block.setCreativeTab(FirmaMod.liquidTab);
		nL.block.setLightOpacity(3);
		nL.block.setLightLevel(0);

		GameRegistry.register(nL.block);
		nL.i = new ItemBlock(nL.block);
		nL.i.setRegistryName(FirmaMod.MODID + ":fluid." + fluidName);
		nL.i.setUnlocalizedName(FirmaMod.MODID + ":fluid." + fluidName);
		GameRegistry.register(nL.i);
		FirmaMod.allFluids.add(nL);
		return nL;
	}
}

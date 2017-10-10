package uk.co.aperistudios.firma;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import uk.co.aperistudios.firma.blocks.lessboring.DoorBlock;
import uk.co.aperistudios.firma.blocks.lessboring.MiniBlock;
import uk.co.aperistudios.firma.items.OreItem;
import uk.co.aperistudios.firma.types.CropType;
import uk.co.aperistudios.firma.types.OresEnum;
import uk.co.aperistudios.firma.types.RockEnum;
import uk.co.aperistudios.firma.types.RockEnum2;
import uk.co.aperistudios.firma.types.SolidMaterialEnum;
import uk.co.aperistudios.firma.types.WoodEnum;
import uk.co.aperistudios.firma.types.WoodEnum2;

public class Util {
	public static int daysInMonth = 28, monthsInYear = 12, daysInYear = daysInMonth * monthsInYear;
	public static int ticksInDay = 24000;
	public static boolean allowPhysics = true;// Set to false when world genning to stop excess checking/item spawning etcetc
	public static boolean allowDrops = true;// Set to false when world genning to stop items spawning while genning world

	public static boolean isGrass(Block b) {
		return b == FirmaMod.grass || b == FirmaMod.grass2 || b == FirmaMod.grasss || b == FirmaMod.grasss2;
	}

	public static boolean isDirt(Block b) {
		return b == FirmaMod.dirt || b == FirmaMod.dirt2;
	}

	public static boolean isRawStone(Block b) {
		return b == FirmaMod.rock || b == FirmaMod.rock2;
	}

	public static boolean isSoilOrGravel(Block b) {
		return isSoil(b) || isGravel(b);
	}

	public static boolean isGravel(Block b) {
		return b == FirmaMod.gravel || b == FirmaMod.gravel2;
	}

	public static boolean isSoil(Block b) {
		return isDirt(b) || isClay(b) || isGrass(b);
	}

	public static boolean isClay(Block b) {
		return b == FirmaMod.clayBlock || b == FirmaMod.clayBlock2;
	}

	public static boolean isSand(Block b) {
		return b == FirmaMod.sand || b == FirmaMod.sand2;
	}

	public static IBlockState getRockStrata(double noise, int layer) {
		noise = Math.abs(noise) % 1.0;
		if (layer == 0) {
			// Top Layer Rocks
			int count = CommonProxy.rockLayerTop.length;
			int opt = (int) (noise * count);
			return CommonProxy.rockLayerTop[opt];
		} else if (layer == 1) {
			// Mid Layer Rocks
			int count = CommonProxy.rockLayerMid.length;
			int opt = (int) (noise * count);
			return CommonProxy.rockLayerMid[opt];
		} else if (layer == 2) {
			// Bottom Layer Rocks
			int count = CommonProxy.rockLayerBot.length;
			int opt = (int) (noise * count);
			return CommonProxy.rockLayerBot[opt];
		}
		return null;
	}

	public static IBlockState getDirt(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.dirt.getStateFromMeta(meta) : FirmaMod.dirt2.getStateFromMeta(meta);
	}

	public static IBlockState getFarm(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.farm.getStateFromMeta(meta) : FirmaMod.farm2.getStateFromMeta(meta);
	}

	public static boolean isRockEnum1(Block b) {
		return b == FirmaMod.rock || b == FirmaMod.dirt || b == FirmaMod.grass || b == FirmaMod.gravel || b == FirmaMod.sand || b == FirmaMod.rockb
				|| b == FirmaMod.rockc || b == FirmaMod.rocks || b == FirmaMod.grasss || b == FirmaMod.clayBlock || b == FirmaMod.farm;
	}

	public static boolean isRockEnum2(Block b) {
		return b == FirmaMod.rock2 || b == FirmaMod.dirt2 || b == FirmaMod.grass2 || b == FirmaMod.gravel2 || b == FirmaMod.sand2 || b == FirmaMod.rockb2
				|| b == FirmaMod.rockc2 || b == FirmaMod.rocks2 || b == FirmaMod.grasss2 || b == FirmaMod.clayBlock2 || b == FirmaMod.farm2;
	}

	public static RockEnum getRockEnum(IBlockState b) {
		if (isRockEnum1(b.getBlock())) {
			return RockEnum.get(b.getBlock().getMetaFromState(b));
		}
		return null;
	}

	public static RockEnum2 getRockEnum2(IBlockState b) {
		if (isRockEnum2(b.getBlock())) {
			return RockEnum2.get(b.getBlock().getMetaFromState(b));
		}
		return null;
	}

	public static WoodEnum getWoodEnum(IBlockState b) {
		if (isWoodEnum1(b.getBlock())) {
			return WoodEnum.get(b.getBlock().getMetaFromState(b));
		}
		return null;
	}

	public static WoodEnum2 getWoodEnum2(IBlockState b) {
		if (isWoodEnum2(b.getBlock())) {
			return WoodEnum2.get(b.getBlock().getMetaFromState(b));
		}
		return null;
	}

	public static ItemStack getSapling(IBlockState s) {
		WoodEnum we = getWoodEnum(s);
		WoodEnum2 we2 = getWoodEnum2(s);
		if (we != null) {
			return new ItemStack(FirmaMod.sapling, 1, we.getMeta());
		}
		if (we2 != null) {
			return new ItemStack(FirmaMod.sapling, 1, we2.getMeta() + 16);
		}
		return new ItemStack(FirmaMod.sapling);
	}

	public static IBlockState getSparseGrass(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.grasss.getStateFromMeta(meta) : FirmaMod.grasss2.getStateFromMeta(meta);
	}

	public static IBlockState getGrass(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.grass.getStateFromMeta(meta) : FirmaMod.grass2.getStateFromMeta(meta);
	}

	public static boolean isWater(Block b) {
		return b == FirmaMod.freshwater.getBlock() || b == FirmaMod.saltwater.getBlock();
	}

	public static IBlockState getSand(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.sand.getStateFromMeta(meta) : FirmaMod.sand2.getStateFromMeta(meta);
	}

	public static IBlockState getRock(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.rock.getStateFromMeta(meta) : isRockEnum2(b) ? FirmaMod.rock2.getStateFromMeta(meta) : null;
	}

	public static IBlockState getCobble(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.rockc.getStateFromMeta(meta) : FirmaMod.rockc2.getStateFromMeta(meta);
	}

	public static IBlockState getBricks(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.rockb.getStateFromMeta(meta) : FirmaMod.rockb2.getStateFromMeta(meta);
	}

	public static IBlockState getGravel(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.gravel.getStateFromMeta(meta) : FirmaMod.gravel2.getStateFromMeta(meta);
	}

	public static IBlockState getClay(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.clayBlock.getStateFromMeta(meta) : FirmaMod.clayBlock2.getStateFromMeta(meta);
	}

	public static boolean isRockEnum1(IBlockState in) {
		return isRockEnum1(in.getBlock());
	}

	/**
	 * Get Temperature ignoring given season and any heat producing blocks. Mostly
	 * used for world gen.
	 * 
	 * @param pos
	 * @return
	 */
	public static double getEquatorialHeat(BlockPos pos) {
		return getEquatorialHeat(pos.getZ());
	}

	/**
	 * Get Temperature ignoring given season and any heat producing blocks. Mostly
	 * used for world gen.
	 * 
	 * @param z
	 * @return
	 */
	public static double getEquatorialHeat(int z) {
		z = Math.abs(z);
		int temp = 25 - (z / 250);
		if (temp < -20) {
			temp = -20;
		}
		return temp;
	}

	public static boolean isWoodEnum1(IBlockState state) {
		return isWoodEnum1(state.getBlock());
	}

	public static boolean isWoodEnum2(IBlockState state) {
		return isWoodEnum2(state.getBlock());
	}

	public static boolean isWoodEnum1(Block b) {
		return b == FirmaMod.log || b == FirmaMod.leaf || b == FirmaMod.plank || b == FirmaMod.sapling;
	}

	public static boolean isWoodEnum2(Block b) {
		return b == FirmaMod.log2 || b == FirmaMod.leaf2 || b == FirmaMod.plank2 || b == FirmaMod.sapling2;
	}

	public static long getYear(long time) {
		return time / daysInYear;
	}

	public static long getTotalDays(long time) {
		return time / ticksInDay;
	}

	public static long getTotalMonths(long time) {
		return time / daysInMonth;
	}

	public static int getMonthOfYear(long time) {
		return (int) (getTotalMonths(time) % monthsInYear);
	}

	public static int getDayOfYear(long time) {
		return (int) (getTotalDays(time) % daysInYear);
	}

	public static int getDayOfMonth(long time) {
		return getDayOfYear(time) - getMonthOfYear(time) * daysInMonth;
	}

	public static int getTimeOfDay(long time) {
		return (int) (time - getTotalDays(time) * ticksInDay);
	}

	public static OresEnum getOreForRock(Random r, String meta) {
		OresEnum ret = null;
		int iter = 0;
		while (true) {
			if (iter > 100) {
				break;
			}
			ret = OresEnum.values()[r.nextInt(OresEnum.values().length)];
			for (RockEnum re : RockEnum.values()) {
				if (re.getName().equals(meta)) {
					return ret;
				}
			}
			for (RockEnum2 re : RockEnum2.values()) {
				if (re.getName().equals(meta)) {
					return ret;
				}
			}
			ret = null;
			iter++;
		}
		throw new RuntimeException("No ores can spawn in " + meta);
	}

	public static ItemStack getOreItemStack(OresEnum ore, int grade, int quant) {
		return new ItemStack(FirmaMod.oreItem, quant, FirmaMod.oreItem.getSubMeta(ore + OreItem.QUALITY[grade]));
	}

	public static boolean isLiquid(Block block) {
		return block == FirmaMod.freshwater.getBlock() || block == FirmaMod.saltwater.getBlock() || block == FirmaMod.lava.getBlock();
	}

	public static boolean isRawStone(IBlockState blockState) {
		return isRawStone(blockState.getBlock());
	}

	private static boolean isOre(Block block) {
		return block == FirmaMod.ore;
	}

	public static boolean isOre(IBlockState blockState) {
		return isOre(blockState.getBlock());
	}

	public static boolean isRockEnum2(IBlockState bs) {
		return isRockEnum2(bs.getBlock());
	}

	public static boolean isWater(IBlockState bs) {
		return isWater(bs.getBlock());
	}

	public static IBlockState getFence(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in); // TODO FENCE
		Block b = in.getBlock();
		return isWoodEnum1(b) ? FirmaMod.plank.getStateFromMeta(meta) : isWoodEnum2(b) ? FirmaMod.plank2.getStateFromMeta(meta) : null;
	}

	public static IBlockState getPlank(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isWoodEnum1(b) ? FirmaMod.plank.getStateFromMeta(meta) : isWoodEnum2(b) ? FirmaMod.plank2.getStateFromMeta(meta) : null;
	}

	public static IBlockState getHalfPlank(IBlockState in) {
		SolidMaterialEnum sme = getSolidMaterial(in);
		return FirmaMod.miniBlocks.getHalfBlock(sme).withProperty(MiniBlock.lne, true).withProperty(MiniBlock.lnw, true).withProperty(MiniBlock.lsw, true)
				.withProperty(MiniBlock.lse, true);
	}

	private static SolidMaterialEnum getSolidMaterial(IBlockState in) {
		Block b = in.getBlock();
		if (isWoodEnum1(b)) {
			WoodEnum we = getWoodEnum(in);
			return SolidMaterialEnum.get(we);
		} else if (isWoodEnum2(b)) {
			WoodEnum2 we2 = getWoodEnum2(in);
			return SolidMaterialEnum.get(we2);
		} else if (isRockEnum1(b)) {
			RockEnum re = getRockEnum(in);
			return SolidMaterialEnum.get(re);
		} else if (isRockEnum2(b)) {
			RockEnum2 re2 = getRockEnum2(in);
			return SolidMaterialEnum.get(re2);
		}

		return null;
	}

	public static IBlockState getDoor(IBlockState in, boolean topHalf) {
		SolidMaterialEnum sme = getSolidMaterial(in);
		EnumDoorHalf edh = topHalf ? EnumDoorHalf.UPPER : EnumDoorHalf.LOWER;
		return FirmaMod.door.getDefaultState().withProperty(DoorBlock.HALF, edh).withProperty(DoorBlock.MATERIAL, sme);
	}

	public static CropType getRandomPlant(Random r) {
		float f = r.nextFloat();
		int index = (int) (CropType.values().length * f);
		return CropType.values()[index];
	}

	public static String makeStateString(IBlockState state, IProperty<?>... props) {
		StringBuilder sb = new StringBuilder();
		boolean comma = false;
		for (IProperty<?> prop : props) {
			if (comma) {
				sb.append(",");
			}
			comma = true;
			sb.append(prop.getName());
			sb.append("=");
			sb.append(state.getValue(prop));
		}
		return sb.toString();
	}

	/**
	 * Return how much to change an areas heat by based on day of year and how
	 * hot/cold a year is
	 * 
	 * @param worldIn
	 * 
	 * @return
	 */
	public static double getHeatForDate(IBlockAccess worldIn, TimeData td) {
		// Each year has a different overall heat
		// TODO Get World seed to add to year to make each worlds temperature procedural?
		if (td == null) {
			return 0;
		}
		Random rand = new Random(td.getYear());
		double yearHeat = rand.nextDouble() * 2.0 + 1.0;// Between -1 and 1 non-inclusive
		double dayOfYear = ((Util.daysInMonth * td.getMonth() + td.getDay()) * 1.0) / (Util.daysInYear * 1.0); // 0 = first day of year 0.9999 = last day of year
		double sineTemperature = Math.sin(dayOfYear * Math.PI * 2) * 0.25;
		return yearHeat + sineTemperature;

	}

	public static double getHeat(IBlockAccess worldIn, BlockPos pos, TimeData td) {
		return getEquatorialHeat(pos) + (getHeatForDate(worldIn, td) * 20.0);
	}
}

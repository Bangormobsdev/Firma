package uk.co.aperistudios.firma.generation;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.FirmaOreTileEntity;
import uk.co.aperistudios.firma.types.OresEnum;
import uk.co.aperistudios.firma.types.RockEnum;
import uk.co.aperistudios.firma.types.RockEnum2;

public class OreGenReplacer implements GenBlockReplacer {

	private OresEnum ore;
	private int grade;

	public OreGenReplacer(OresEnum ore, int grade) {
		this.ore = ore;
		this.grade = grade;
	}

	@Override
	public void replaceBlock(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock().isReplaceableOreGen(state, world, pos, new OreGenReplacer.StonePredicate())) {
			RockEnum r = null;
			RockEnum2 r2 = null;
			if (state.getBlock() instanceof BaseBlock) {
				r = Util.getRockEnum(state);
				r2 = Util.getRockEnum2(state);
			}
			boolean isAllowed = false;
			if (r == null && r2 == null) {
				isAllowed = true;
				// Non-Firma-Rock that says it
				// allows replacing? Must be some
				// other mod! Let them have it
			} else if (r != null) {
				for (int v = 0; v < ore.getRock1().length; v++) {
					if (ore.getRock1()[v] == r) {
						isAllowed = true;
					}
				}
			} else if (r2 != null) {
				for (int v = 0; v < ore.getRock2().length; v++) {
					if (ore.getRock2()[v] == r2) {
						isAllowed = true;
					}
				}
			}
			if (isAllowed) {
				setToOre(world, pos, state, ore, grade);
			} else {
			}
		}
	}

	public void setToOre(World world, BlockPos pos, IBlockState currentBlockState, OresEnum ore, int grade) {
		world.setBlockState(pos, FirmaMod.ore.getDefaultState(), 2);
		FirmaOreTileEntity te = (FirmaOreTileEntity) world.getTileEntity(pos);
		if (te != null) {
			te.setState(currentBlockState);
			te.grade = grade;
			te.ore = ore;
			te.markDirty();
			FirmaOreVeinGen.count++;
		}
	}

	static class StonePredicate implements Predicate<IBlockState> {
		private StonePredicate() {
		}

		@Override
		public boolean apply(IBlockState p_apply_1_) {
			return p_apply_1_ != null && Util.isRawStone(p_apply_1_.getBlock());
		}
	}

}

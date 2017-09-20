package uk.co.aperistudios.firma.blocks.tileentity;

import java.util.HashMap;
import net.minecraft.tileentity.TileEntity;
import uk.co.aperistudios.firma.types.MetalLiquid;

public class CrucibleTileEntity extends TileEntity {
	boolean fireClay;
	HashMap<MetalLiquid, Integer> liquids = new HashMap<MetalLiquid, Integer>();
}

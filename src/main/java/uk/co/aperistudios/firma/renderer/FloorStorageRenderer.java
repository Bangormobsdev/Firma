package uk.co.aperistudios.firma.renderer;

import java.util.List;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.FastTESR;
import uk.co.aperistudios.firma.blocks.tileentity.FloorStorageTileEntity;

public class FloorStorageRenderer extends FastTESR<FloorStorageTileEntity> implements ITileEntityProvider {

	@Override
	public void renderTileEntityFast(FloorStorageTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, VertexBuffer vertexBuffer) {

		ItemStack is = te.getStackInSlot(0);
		if (!is.isEmpty()) {
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(is, Minecraft.getMinecraft().world, null);
			List<BakedQuad> quads = model.getQuads(null, null, 0L);
			for (BakedQuad quad : quads) {
				int[] quadinfo = quad.getVertexData().clone();
				for (int i = 0; i < 4; i++) {
					quadinfo[i * 7] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7]) / 2f);
					quadinfo[i * 7 + 1] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7 + 1]) / 2f);
					quadinfo[i * 7 + 2] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7 + 2]) / 2f);
				}
				vertexBuffer.addVertexData(quadinfo);
				vertexBuffer.putPosition(x, y, z);
			}
		}
		is = te.getStackInSlot(1);
		if (!is.isEmpty()) {
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(is, Minecraft.getMinecraft().world, null);
			List<BakedQuad> quads = model.getQuads(null, null, 0L);
			for (BakedQuad quad : quads) {
				int[] quadinfo = quad.getVertexData().clone();
				for (int i = 0; i < 4; i++) {
					quadinfo[i * 7] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7]) / 2f);
					quadinfo[i * 7 + 1] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7 + 1]) / 2f);
					quadinfo[i * 7 + 2] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7 + 2]) / 2f);
				}
				vertexBuffer.addVertexData(quadinfo);
				vertexBuffer.putPosition(x, y, z + .5);
			}
		}
		is = te.getStackInSlot(2);
		if (!is.isEmpty()) {
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(is, Minecraft.getMinecraft().world, null);
			List<BakedQuad> quads = model.getQuads(null, null, 0L);
			for (BakedQuad quad : quads) {
				int[] quadinfo = quad.getVertexData().clone();
				for (int i = 0; i < 4; i++) {
					quadinfo[i * 7] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7]) / 2f);
					quadinfo[i * 7 + 1] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7 + 1]) / 2f);
					quadinfo[i * 7 + 2] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7 + 2]) / 2f);
				}
				vertexBuffer.addVertexData(quadinfo);
				vertexBuffer.putPosition(x + .5, y, z);
			}
		}
		is = te.getStackInSlot(3);
		if (!is.isEmpty()) {
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(is, Minecraft.getMinecraft().world, null);
			List<BakedQuad> quads = model.getQuads(null, null, 0L);
			for (BakedQuad quad : quads) {
				int[] quadinfo = quad.getVertexData().clone();
				for (int i = 0; i < 4; i++) {
					quadinfo[i * 7] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7]) / 2f);
					quadinfo[i * 7 + 1] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7 + 1]) / 2f);
					quadinfo[i * 7 + 2] = Float.floatToIntBits(Float.intBitsToFloat(quadinfo[i * 7 + 2]) / 2f);
				}
				vertexBuffer.addVertexData(quadinfo);
				vertexBuffer.putPosition(x + .5, y, z + .5);
			}
		}

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new FloorStorageTileEntity();
	}

}

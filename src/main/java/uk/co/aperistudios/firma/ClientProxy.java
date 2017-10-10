package uk.co.aperistudios.firma;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import uk.co.aperistudios.firma.blocks.BlockState;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.liquids.BaseLiquid;
import uk.co.aperistudios.firma.blocks.recolour.GrassColor;
import uk.co.aperistudios.firma.blocks.recolour.LeafColor;
import uk.co.aperistudios.firma.blocks.tileentity.FloorStorageTileEntity;
import uk.co.aperistudios.firma.blocks.tileentity.SoFTileEntity;
import uk.co.aperistudios.firma.items.FirmaItem;
import uk.co.aperistudios.firma.items.ItemState;
import uk.co.aperistudios.firma.items.MetaItem;
import uk.co.aperistudios.firma.renderer.FloorStorageRenderer;
import uk.co.aperistudios.firma.renderer.ShitOnFloorRenderer;

public class ClientProxy extends CommonProxy {

	public static TimeData staticDate;
	public static boolean isRaining;
	public static boolean isSnowing;
	public static boolean isSandstorm;

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		FirmaMod.blockTab = new CreativeTabs("FirmaBlocks") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(Item.getItemFromBlock(FirmaMod.rock));
			}
		};
		FirmaMod.itemTab = new CreativeTabs("FirmaItems") {

			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.brick);
			}
		};
		FirmaMod.gemTab = new CreativeTabs("FirmaGems") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.gem, 1, 40);
			}
		};
		FirmaMod.headTab = new CreativeTabs("FirmaHead") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.toolHeads, 1, 14);
			}
		};
		FirmaMod.toolTab = new CreativeTabs("FirmaTools") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.bunchOfTools.get(4));
			}
		};
		FirmaMod.liquidTab = new CreativeTabs("FirmaLiquid") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.freshwater.getBlock());
			}
		};
		FirmaMod.deviceTab = new CreativeTabs("FirmaDevices") {

			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.crucible.getBlock());
			}

		};
		super.preInit(e);

		for (FirmaItem i : FirmaMod.allItems) {
			if (i instanceof MetaItem) {
				MetaItem mi = (MetaItem) i;
				if (mi.skipRegister()) {
					continue;
				}
				ResourceLocation[] list = new ResourceLocation[mi.getSubCount()];
				for (int f = 0; f < mi.getSubCount(); f++) {
					String loc = mi.getRegistryName().toString();
					ResourceLocation res = new ResourceLocation(loc);
					ModelResourceLocation mrl = new ModelResourceLocation(loc, "variants=" + mi.getSubName(f));
					ModelLoader.setCustomModelResourceLocation(i, f, mrl);
					list[f] = res;
				}
				//ModelBakery.registerItemVariants(mi, list);
			} else {
				FirmaItem fi = i;
				String loc = FirmaMod.MODID + ":" + fi.getBlockStateName();
				ModelResourceLocation mrl = new ModelResourceLocation(loc, "variants=" + fi.getVariant());
				ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
			}
		}

		for (BaseBlock b : FirmaMod.allBlocks) {
			b.registerRender();
		}

		noModelRegister(FirmaMod.floorStorage);
		noModelRegister(FirmaMod.shitOnFloor);
		register(FirmaMod.door);
		register(FirmaMod.doorItem);
		register(FirmaMod.ore);
		register(FirmaMod.crucible);
		register(FirmaMod.miniBlockItem);

		for (BaseLiquid f : FirmaMod.allFluids) {
			register((BlockState) f.getBlock());
		}

		// Special Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(SoFTileEntity.class, new ShitOnFloorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(FloorStorageTileEntity.class, new FloorStorageRenderer());
		LeafColor.init();
	}

	private static void register(ItemState is) {
		for (int f = 0; f < is.getModelCount(); f++) {
			ModelResourceLocation mrl = new ModelResourceLocation(is.getModelPath(), is.getModelSub(f));
			ModelLoader.setCustomModelResourceLocation(is.getItem(), f, mrl);
		}
	}

	private static void register(BlockState block) {
		final Item item = Item.getItemFromBlock(block.getBlock());
		if (!(item instanceof ItemAir)) {
			ModelBakery.registerItemVariants(item);

			ItemMeshDefinition imd = new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return new ModelResourceLocation(block.getModelPath(), block.getModelSub());
				}
			};
			ModelLoader.setCustomMeshDefinition(item, imd);
		}

		ModelLoader.setCustomStateMapper(block.getBlock(), block.getStateMapper());

	}

	private static void noModelRegister(Block block) {
		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(FirmaMod.MODID + ":nomodel", "");
			}
		});

	}

	@Override
	public void init(FMLInitializationEvent e) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new LeafColor(), FirmaMod.leaf);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new LeafColor(), FirmaMod.leaf2);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new GrassColor(), FirmaMod.grass);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new GrassColor(), FirmaMod.grass2);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new GrassColor(), FirmaMod.grasss);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new GrassColor(), FirmaMod.grasss2);
		super.init(e);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

	@Override
	public void setDate(TimeData data) {
		staticDate = data;
	}

	public void setupGui() {
		GuiIngameForge.renderHealth = false;
		GuiIngameForge.renderArmor = false;
		GuiIngameForge.renderExperiance = false;
	}

}

package uk.co.aperistudios.firma.handler;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import uk.co.aperistudios.firma.CommonProxy;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.SpawnTeleport;
import uk.co.aperistudios.firma.TimeData;
import uk.co.aperistudios.firma.generation.FirmaWorld;
import uk.co.aperistudios.firma.packet.SetDayPacket;

public class FirmaHandler {

	@SubscribeEvent
	public void teleport(PlayerChangedDimensionEvent event) {
		if (event.player.world.isRemote) {
			return;
		} // Server side only

		if (CommonProxy.d != 0 && event.toDim == 0) {
			new SpawnTeleport(DimensionManager.getWorld(CommonProxy.d)).teleport(event.player, DimensionManager.getWorld(CommonProxy.d));
			event.setCanceled(true);
			return;
		}
		WorldServer worldTo = event.player.getServer().worldServerForDimension(event.toDim);
		if (worldTo.getWorldType() instanceof FirmaWorld) {
			// One of us
			TimeData td = FirmaMod.proxy.getTimeData(worldTo);
			SetDayPacket setDayPacket = new SetDayPacket(td);
			FirmaMod.dispatcher.sendTo(setDayPacket, (EntityPlayerMP) event.player);

		}
	}

	@SubscribeEvent
	public void join(EntityJoinWorldEvent event) {
		if (event.getWorld().isRemote) {
			return;
		}
		Entity e = event.getEntity();
		if (e instanceof EntityPlayerMP) {
			World worldTo = event.getWorld();
			if (worldTo.getWorldType() instanceof FirmaWorld) {
				// One of us
				TimeData td = FirmaMod.proxy.getTimeData(worldTo);
				SetDayPacket setDayPacket = new SetDayPacket(td);
				FirmaMod.dispatcher.sendTo(setDayPacket, (EntityPlayerMP) e);

			}
		}
	}

	@SubscribeEvent
	public void move(TickEvent.PlayerTickEvent event) {
		if (event.player.world.isRemote) {
			return;
		}
		if (CommonProxy.d == 0) {
			return;
		}
		World w = event.player.world;
		if (w != null && w.getWorldType() == WorldType.DEFAULT) {
			new SpawnTeleport(DimensionManager.getWorld(CommonProxy.d)).teleport(event.player, DimensionManager.getWorld(CommonProxy.d));
		}
	}

	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		EntityLivingBase e = event.getEntityLiving();
		if (e instanceof EntitySheep) {
			removeDrops(event.getDrops());
			ItemStack i = new ItemStack(FirmaMod.hide, 1, FirmaMod.hide.getSubMeta("sheep"));
			event.getDrops().add(new EntityItem(e.world, e.posX, e.posY, e.posZ, i));
		} else if (e instanceof EntityHorse) {
			removeDrops(event.getDrops());
			ItemStack i = new ItemStack(FirmaMod.hide, 1, FirmaMod.hide.getSubMeta("raw"));
			event.getDrops().add(new EntityItem(e.world, e.posX, e.posY, e.posZ, i));
		} else if (e instanceof EntityCow) {
			removeDrops(event.getDrops());
			ItemStack i = new ItemStack(FirmaMod.hide, 1, FirmaMod.hide.getSubMeta("raw"));
			event.getDrops().add(new EntityItem(e.world, e.posX, e.posY, e.posZ, i));
		} else if (e instanceof EntityPig) {
			removeDrops(event.getDrops());
			ItemStack i = new ItemStack(FirmaMod.hide, 1, FirmaMod.hide.getSubMeta("raw"));
			event.getDrops().add(new EntityItem(e.world, e.posX, e.posY, e.posZ, i));
		}
	}

	public void removeDrops(List<EntityItem> drops) {
		int i = 0;
		while (i < drops.size()) {
			if (removeDrop(drops.get(i).getEntityItem().getItem())) {
				drops.remove(i);
				continue;
			}
			i++;
		}
	}

	public boolean removeDrop(Item i) {
		Block b = Block.getBlockFromItem(i);
		return i == Items.LEATHER || b == Blocks.WOOL;
	}
}

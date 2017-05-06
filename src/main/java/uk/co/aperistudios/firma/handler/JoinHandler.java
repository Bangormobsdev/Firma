package uk.co.aperistudios.firma.handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import uk.co.aperistudios.firma.CommonProxy;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.SpawnTeleport;
import uk.co.aperistudios.firma.TimeData;
import uk.co.aperistudios.firma.generation.FirmaWorld;
import uk.co.aperistudios.firma.packet.SetDayPacket;

public class JoinHandler {

	@SubscribeEvent
	public void teleport(PlayerChangedDimensionEvent event) {
		if (event.player.world.isRemote) { return; } // Server side only
		
		if (CommonProxy.d!=0 && event.toDim == 0) {
			new SpawnTeleport(DimensionManager.getWorld(CommonProxy.d)).teleport(event.player, DimensionManager.getWorld(CommonProxy.d));
			event.setCanceled(true);
			return;
		}
		WorldServer worldTo = event.player.getServer().worldServerForDimension(event.toDim);
		if(worldTo.getWorldType() instanceof FirmaWorld){
			// One of us
			TimeData td = (TimeData) worldTo.getPerWorldStorage().getOrLoadData(TimeData.class, "firmatime");
			SetDayPacket setDayPacket = new SetDayPacket(td);
			FirmaMod.dispatcher.sendTo(setDayPacket, (EntityPlayerMP) event.player);
			
		}
	}

	@SubscribeEvent
	public void move(TickEvent.PlayerTickEvent event) {
		

		if (event.player.world.isRemote) {
			//System.out.println("CLIENT "+event.player.world.getBiome(event.player.getPosition()).getBiomeName());

			return;
		}
		//System.out.println(event.player.world.getBiome(event.player.getPosition()).getBiomeName());
		if (CommonProxy.d == 0) {
			return;
		}
		World w = event.player.world;
		if (w != null && w.getWorldType() == WorldType.DEFAULT) {
			new SpawnTeleport(DimensionManager.getWorld(CommonProxy.d)).teleport(event.player, DimensionManager.getWorld(CommonProxy.d));
		}
	}
}

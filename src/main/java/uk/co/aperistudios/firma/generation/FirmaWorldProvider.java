package uk.co.aperistudios.firma.generation;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import uk.co.aperistudios.firma.ClientProxy;
import uk.co.aperistudios.firma.CommonProxy;

import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.TimeData;
import uk.co.aperistudios.firma.Util;

public class FirmaWorldProvider extends WorldProvider {
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public DimensionType getDimensionType() {
		return CommonProxy.firmaDimension;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public int getDimension() {
		return CommonProxy.d;
	}
	
	@Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        BlockPos blockpos = new BlockPos(x, 0, z);
        Block a = this.world.getGroundAboveSeaLevel(blockpos).getBlock();
        
        return this.world.getBiome(blockpos).ignorePlayerSpawnSuitability() ? true :Util.isSoil(a) || Util.isGravel(a) || Util.isSand(a);
    }
	
	@Override
	public int getRespawnDimension(EntityPlayerMP player) {
		return 2;
	}
	
	@Override
	public Biome getBiomeForCoords(BlockPos pos)
    {
		return getBiomeProvider().getBiome(pos, FirmaBiome.OCEAN);
    }
	
	@Override
	public ICapabilityProvider initCapabilities() {
		return super.initCapabilities();
	}
	
	@Override
    public boolean canDropChunk(int x, int z)
    {
        return !this.world.isSpawnChunk(x, z) || !this.world.provider.getDimensionType().shouldLoadSpawn();
    }
	
	@Override
    protected void init()
    {
        this.hasSkyLight = true;
        this.biomeProvider = world.getWorldInfo().getTerrainType().getBiomeProvider(world);
        world.getWorldInfo().getGeneratorOptions();
    }
	
	@Override
	public long getSeed() {
		return world.getWorldInfo().getSeed();
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		worldTime = worldTime % Util.ticksInDay;
		worldTime = (long) (((worldTime*1f) / (Util.ticksInDay*1f)) * 24000f); //Break day length into 0f-1f and then * by old day length for scale 
		return super.calculateCelestialAngle(worldTime, 0f);
	}
	
	@Override
	public int getMoonPhase(long worldTime) {
		TimeData td = ClientProxy.staticDate;
		//MapStorage st = this.world.getPerWorldStorage();
		//TimeData td = (TimeData) st.getOrLoadData(TimeData.class, "firmatime");
		if(td==null){ return 0; }
		return td.getMoonPhase();
	}
}

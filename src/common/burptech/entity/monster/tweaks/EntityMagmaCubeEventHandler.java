package burptech.entity.monster.tweaks;

import burptech.BurpTechCore;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

public class EntityMagmaCubeEventHandler 
{
	public EntityMagmaCubeEventHandler()
	{
		BurpTechCore.Log.info("Enabling Magma Cube Spawning Restrictions");
	}
	
	@ForgeSubscribe
	public void entitySpawn(CheckSpawn event)
	{
		if (event.entity instanceof EntityMagmaCube && !canSpawnHere((EntityMagmaCube)event.entity))
		{
			event.setResult(Result.DENY);
			return;
		}
	}
	
	private boolean canSpawnHere(EntityMagmaCube entity)
	{
    	int x = MathHelper.floor_double(entity.posX);
    	int y = MathHelper.floor_double(entity.boundingBox.minY);
    	int z = MathHelper.floor_double(entity.posZ);
    	
    	int spawnBlock = entity.worldObj.getBlockId(x, y - 1, z);
    	
    	if (spawnBlock != Block.netherrack.blockID && spawnBlock != Block.netherBrick.blockID)
    		return false;
    	
		return true;
	}
}

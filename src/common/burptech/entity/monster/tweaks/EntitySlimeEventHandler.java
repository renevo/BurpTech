package burptech.entity.monster.tweaks;

import burptech.BurpTechCore;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

/*
 * Slime Tweaks
 */
public class EntitySlimeEventHandler 
{	
	public EntitySlimeEventHandler()
	{
		BurpTechCore.log.info("Enabling Slime Spawning Restrictions");
	}
	
	@ForgeSubscribe
	public void entitySpawn(CheckSpawn event)
	{
		if (event.entity instanceof EntitySlime && !canSpawnHere((EntitySlime)event.entity))
		{
			event.setResult(Result.DENY);
			return;
		}
	}
	
	private boolean canSpawnHere(EntitySlime entity)
	{
    	int x = MathHelper.floor_double(entity.posX);
    	int y = MathHelper.floor_double(entity.boundingBox.minY);
    	int z = MathHelper.floor_double(entity.posZ);
    	
    	int spawnBlock = entity.worldObj.getBlockId(x, y - 1, z);
    	
    	if (spawnBlock != Block.stone.blockID && spawnBlock != Block.dirt.blockID && spawnBlock != Block.grass.blockID)
    		return false;
    	
		return true;
	}
}

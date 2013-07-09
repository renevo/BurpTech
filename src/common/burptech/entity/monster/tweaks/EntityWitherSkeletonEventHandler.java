package burptech.entity.monster.tweaks;

import burptech.BurpTechCore;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

public class EntityWitherSkeletonEventHandler 
{
	public EntityWitherSkeletonEventHandler()
	{
		BurpTechCore.Log.info("Enabling Wither Skeleton Spawning Restrictions");
	}
	
	@ForgeSubscribe
	public void entitySpawn(CheckSpawn event)
	{
		if (event.entity instanceof EntitySkeleton && !canSpawnHere((EntitySkeleton)event.entity))
		{
			event.setResult(Result.DENY);
			return;
		}
	}
	
	private boolean canSpawnHere(EntitySkeleton entity)
	{
		if (entity.getSkeletonType() != 1)
			return true;

    	int x = MathHelper.floor_double(entity.posX);
    	int y = MathHelper.floor_double(entity.boundingBox.minY);
    	int z = MathHelper.floor_double(entity.posZ);
    	
    	int spawnBlock = entity.worldObj.getBlockId(x, y - 1, z);
    	
    	if (spawnBlock != Block.netherrack.blockID && spawnBlock != Block.netherBrick.blockID)
    		return false;
    	
    	return true;
	}
}

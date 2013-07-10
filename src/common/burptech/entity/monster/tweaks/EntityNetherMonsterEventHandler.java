package burptech.entity.monster.tweaks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import burptech.BurpTechCore;

public class EntityNetherMonsterEventHandler 
{
	public EntityNetherMonsterEventHandler()
	{
		BurpTechCore.Log.info("Enabling Nether Monster Spawning Restrictions");
	}
	
	@ForgeSubscribe
	public void entitySpawn(CheckSpawn event)
	{
		if (event.entity.dimension == -1 && event.entity instanceof IMob && !canSpawnHere(event.entity))
		{
			event.setResult(Result.DENY);
			return;
		}
	}
	
	private boolean canSpawnHere(Entity entity)
	{
    	int x = MathHelper.floor_double(entity.posX);
    	int y = MathHelper.floor_double(entity.boundingBox.minY);
    	int z = MathHelper.floor_double(entity.posZ);
    	
    	int spawnBlock = entity.worldObj.getBlockId(x, y - 1, z);
    	
    	if (spawnBlock != Block.netherrack.blockID && spawnBlock != Block.netherBrick.blockID && spawnBlock != Block.slowSand.blockID)
    		return false;
    	
		return true;
	}
}

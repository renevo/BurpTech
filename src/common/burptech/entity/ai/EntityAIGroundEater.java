package burptech.entity.ai;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;

/*
 * Enables mobs to eat breeding food from the ground
 */
public class EntityAIGroundEater extends EntityAIBase
{
	private EntityAnimal entity;
	
	public EntityAIGroundEater(EntityAnimal entity)
	{
		this.entity = entity;
	}
	
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
    	return entity.getHealth() > 0 && entity.inLove == 0 && entity.getGrowingAge() == 0;
    }
    
    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
    	if (entity.getHealth() > 0 && entity.inLove == 0 && entity.getGrowingAge() == 0)
    	{
	    	List nearbyItems = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(1.0D, 0.0D, 1.0D));
	    	
	    	if (nearbyItems != null && nearbyItems.size() > 0)
	    	{
	    		for (int itemId = 0; itemId < nearbyItems.size(); itemId++)
	    		{
	    			Entity e = (Entity)nearbyItems.get(itemId);
	    			if ((e instanceof EntityItem) && entity.isBreedingItem(((EntityItem)e).getEntityItem()))
	    			{
	    				return true;
	    			}
	    		}
	    	}
    	}
    	
    	return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
    	if (entity.getHealth() > 0 && entity.inLove == 0 && entity.getGrowingAge() == 0)
        {
        	List nearbyItems = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(1.0D, 0.0D, 1.0D));
        	if (nearbyItems != null && nearbyItems.size() > 0)
        	{
        		boolean ateFood = false;
        		for (int itemId = 0; itemId < nearbyItems.size(); itemId++)
        		{
        			Entity e = (Entity)nearbyItems.get(itemId);
        			if ((e instanceof EntityItem) && entity.isBreedingItem(((EntityItem)e).getEntityItem()))
        			{
        				if (((EntityItem)e).getEntityItem().stackSize >= 1)
        				{
        					ateFood = true;
        					((EntityItem)e).getEntityItem().stackSize--;
        				}
        				
        				if (((EntityItem)e).getEntityItem().stackSize <= 0)
        				{
        					e.setDead();
        				}
        			}
        			
        			if (ateFood)
        				break;
        		}
        		
        		if (ateFood)
        		{
        			// sound/particle not working all the time for some reason, needs research....
        			Random rand = entity.getRNG();
        			entity.worldObj.playSoundAtEntity(entity, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        			
                    for (int var3 = 0; var3 < 7; ++var3)
                    {
                        double var4 = rand.nextGaussian() * 0.02D;
                        double var6 = rand.nextGaussian() * 0.02D;
                        double var8 = rand.nextGaussian() * 0.02D;
                        entity.worldObj.spawnParticle("heart", entity.posX + (double)(rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, entity.posY + 0.5D + (double)(rand.nextFloat() * entity.height), entity.posZ + (double)(rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, var4, var6, var8);
                    }
        	        
        	        entity.inLove = 600;
        	        
        	        return;
        		}
        	
        	}
        
        }
    }
}

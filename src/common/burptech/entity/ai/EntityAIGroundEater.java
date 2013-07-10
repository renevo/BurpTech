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
    	return entity.getHealth() > 0 && !entity.isInLove() && entity.getGrowingAge() == 0;
    }
    
    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
    	if (entity.getHealth() > 0 && !entity.isInLove() && entity.getGrowingAge() == 0)
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
    	if (entity.getHealth() > 0 && !entity.isInLove() && entity.getGrowingAge() == 0)
        {
        	List nearbyItems = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(2.0D, 0.0D, 2.0D));
        	if (nearbyItems != null && nearbyItems.size() > 0)
        	{
        		for (int itemId = 0; itemId < nearbyItems.size(); itemId++)
        		{
        			Entity e = (Entity)nearbyItems.get(itemId);
        			if ((e instanceof EntityItem) && entity.isBreedingItem(((EntityItem)e).getEntityItem()))
        			{
	    				burptech.BurpTechCore.log.info("FOOD ME!!!");
	    				
        				if (((EntityItem)e).getEntityItem().stackSize >= 1)
        				{
        					// making the love time higher, due to the fact that other AI might need to catch up
        					entity.inLove = 2400; 
                	        

        					((EntityItem)e).getEntityItem().stackSize--;
        					
            				if (((EntityItem)e).getEntityItem().stackSize <= 0)
            				{
            					e.setDead();
            				}
            				
                			// sound/particle not working all the time for some reason, needs research....
                			entity.worldObj.playSoundAtEntity(entity, "random.pop", 0.2F, ((entity.getRNG().nextFloat() - entity.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                	        
                            double d0 = entity.getRNG().nextGaussian() * 0.02D;
                            double d1 = entity.getRNG().nextGaussian() * 0.02D;
                            double d2 = entity.getRNG().nextGaussian() * 0.02D;
                            
                            entity.worldObj.spawnParticle("heart", entity.posX + (double)(entity.getRNG().nextFloat() * entity.width * 2.0F) - (double)entity.width, entity.posY + 0.5D + (double)(entity.getRNG().nextFloat() * entity.height), entity.posZ + (double)(entity.getRNG().nextFloat() * entity.width * 2.0F) - (double)entity.width, d0, d1, d2);
                            
            				break;
        				}
    				}
        		}
        	}
        
        }
    }
}

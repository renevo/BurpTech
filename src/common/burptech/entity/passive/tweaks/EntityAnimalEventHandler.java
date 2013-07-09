package burptech.entity.passive.tweaks;

import java.util.List;
import java.util.Random;

import burptech.BurpTechCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EntityAnimalEventHandler 
{
	public EntityAnimalEventHandler()
	{
		BurpTechCore.Log.info("Enabling Mobs Eating Dropped Items");
	}
	
	@ForgeSubscribe
	public void entityUpdating(LivingUpdateEvent event)
	{
		if (event.entity instanceof EntityAnimal && !(event.entity instanceof EntityTameable) && !(event.entity instanceof EntityChicken))
		{
			tryEatWheat((EntityAnimal)event.entity);
			return;
		}
		
		if (event.entity instanceof EntityChicken)
		{
			tryEatSeeds((EntityAnimal)event.entity);
			return;
		}
	}
	
	private boolean tryEatWheat(EntityAnimal entity)
	{
		if (entity.getHealth() > 0 && entity.inLove == 0 && entity.getGrowingAge() == 0)
        {
        	List nearbyItems = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(1.0D, 0.0D, 1.0D));
        	if (nearbyItems != null && nearbyItems.size() > 0)
        	{
        		boolean ateWheat = false;
        		for (int itemId = 0; itemId < nearbyItems.size(); itemId++)
        		{
        			Entity e = (Entity)nearbyItems.get(itemId);
        			if ((e instanceof EntityItem) && ((EntityItem)e).getEntityItem().getItem() == Item.wheat)
        			{
        				if (((EntityItem)e).getEntityItem().stackSize >= 1)
        				{
        					ateWheat = true;
        					((EntityItem)e).getEntityItem().stackSize--;
        				}
        				
        				if (((EntityItem)e).getEntityItem().stackSize <= 0)
        				{
        					e.setDead();
        				}
        			}
        			
        			if (ateWheat)
        				break;
        		}
        		
        		if (ateWheat)
        		{
        			// sound/particle not working for some reason, needs research....
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
        	        
        	        return true;
        		}
        	
        	}
        
        }
		
		return false;
	}
	
	private boolean tryEatSeeds(EntityAnimal entity)
	{
		if (entity.getHealth() > 0 && entity.inLove == 0 && entity.getGrowingAge() == 0)
        {
        	List nearbyItems = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(1.0D, 0.0D, 1.0D));
        	if (nearbyItems != null && nearbyItems.size() > 0)
        	{
        		boolean ateSeeds = false;
        		for (int itemId = 0; itemId < nearbyItems.size(); itemId++)
        		{
        			Entity e = (Entity)nearbyItems.get(itemId);
        			if ((e instanceof EntityItem) && ((EntityItem)e).getEntityItem().getItem() instanceof ItemSeeds)
        			{
        				if (((EntityItem)e).getEntityItem().stackSize >= 1)
        				{
        					ateSeeds = true;
        					((EntityItem)e).getEntityItem().stackSize--;
        				}
        				
        				if (((EntityItem)e).getEntityItem().stackSize <= 0)
        				{
        					e.setDead();
        				}
        			}
        			
        			if (ateSeeds)
        				break;
        		}
        		
        		if (ateSeeds)
        		{
        			// sound/particle not working for some reason, needs research....
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
        	        
        	        return true;
        		}
        	
        	}
        
        }
		
		return false;
	}
}

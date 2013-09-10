package burptech.entity.living.tweaks;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EntityLivingEventHandler 
{
	@ForgeSubscribe
	public void entitySpawning(EntityJoinWorldEvent event)
	{
		if (!(event.entity instanceof EntityLiving)) 
			return;

		updateAI((EntityLiving)event.entity);
	}
  
	private void updateAI(EntityLiving entity)
	{
		EntityAITaskEntry wanderTask = null;
		
		for (Object task: entity.tasks.taskEntries)
		{
			EntityAITaskEntry taskEntry = (EntityAITaskEntry)task;
			
			if (taskEntry.action instanceof EntityAIWander)
				wanderTask = taskEntry;
		}
		
		if (wanderTask != null)
		{
			entity.tasks.taskEntries.remove(wanderTask);
			float moveSpeed = 1.0F;
			if (entity instanceof EntityChicken)
				moveSpeed = 1.0F;
			else if	(entity instanceof EntityCow)
				moveSpeed = 1.0F;
			else if (entity instanceof EntityCreeper)
				moveSpeed = 0.8F;
			else if (entity instanceof EntityHorse)
				moveSpeed = 0.7F;
			else if (entity instanceof EntityIronGolem)
				moveSpeed = 0.6F;
			else if (entity instanceof EntityOcelot)
				moveSpeed = 0.8F;
			else if (entity instanceof EntityPig)
				moveSpeed = 1.0F;
			else if (entity instanceof EntitySheep)
				moveSpeed = 1.0F;
			else if (entity instanceof EntitySkeleton)
				moveSpeed = 1.0F;
			else if (entity instanceof EntitySnowman)
				moveSpeed = 1.0F;
			else if (entity instanceof EntityVillager)
				moveSpeed = 0.6F;
			else if (entity instanceof EntityWitch)
				moveSpeed = 1.0F;
			else if (entity instanceof EntityWither)
				moveSpeed = 1.0F;
			else if (entity instanceof EntityWolf)
				moveSpeed = 1.0F;
			else if (entity instanceof EntityZombie)
				moveSpeed = 1.0F;
			else
				moveSpeed = 0.85F;
			
			entity.tasks.addTask(((EntityAITaskEntry)wanderTask).priority, new burptech.entity.ai.EntityAITweakedWandering((EntityCreature)entity, moveSpeed));
		}
	}
}

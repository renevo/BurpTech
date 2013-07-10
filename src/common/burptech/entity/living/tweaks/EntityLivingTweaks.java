package burptech.entity.living.tweaks;

import java.lang.reflect.Field;

import scala.Console;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.transformers.ForgeAccessTransformer;

public class EntityLivingTweaks 
{
	//TODO Add an entity type - moveSpeed table to reduce reflection usage
	
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
			float moveSpeed = 2.0F;
			moveSpeed = burptech.lib.ReflectionHelper.getFloatFieldFromObject(wanderTask.action, "speed");
			
			entity.tasks.addTask(((EntityAITaskEntry)wanderTask).priority, new burptech.entity.ai.EntityAITweakedWandering((EntityCreature)entity, moveSpeed));
		}
	}
}

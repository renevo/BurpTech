package burptech.entity.living.tweaks;

import java.lang.reflect.Field;

import cpw.mods.fml.relauncher.ReflectionHelper;
import scala.Console;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.transformers.ForgeAccessTransformer;

public class EntityLivingEventHandler 
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
			float moveSpeed = 0.2F;
			
			/* TODO: Fix this later, it doesn't compile (with mcp, it does with eclipse)
			try
			{
				// The second param has to be updated for each build of MC from fields.csv in MCP -> pro tip, start with first param, then go a,b,c,d,e, speed is the 5th param, which is e
				moveSpeed = (float)ReflectionHelper.getPrivateValue(EntityAIWander.class, (EntityAIWander)(wanderTask.action), "speed", "e");
			} catch (Exception ex)
			{
				ex.printStackTrace();
				moveSpeed = 0.2F;
			}
			*/
			
			entity.tasks.addTask(((EntityAITaskEntry)wanderTask).priority, new burptech.entity.ai.EntityAITweakedWandering((EntityCreature)entity, moveSpeed));
		}
	}
}

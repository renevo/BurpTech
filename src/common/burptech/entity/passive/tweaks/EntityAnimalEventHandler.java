package burptech.entity.passive.tweaks;

import burptech.entity.ai.EntityAIGroundEater;
import net.minecraft.entity.passive.*;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

// TODO: Add this as an AI, instead of an event handler
public class EntityAnimalEventHandler 
{
	
	@ForgeSubscribe
	public void entitySpawning(EntityJoinWorldEvent event)
	{
		if (!(event.entity instanceof EntityAnimal)) 
			return;

		EntityAnimal animal = (EntityAnimal)event.entity;
		
		// add
		animal.tasks.addTask(0, new EntityAIGroundEater(animal));
	}
}

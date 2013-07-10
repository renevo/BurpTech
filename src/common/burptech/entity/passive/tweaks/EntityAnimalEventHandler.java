package burptech.entity.passive.tweaks;

import java.util.List;
import java.util.Random;

import burptech.BurpTechCore;
import burptech.entity.ai.EntityAIGroundEater;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

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

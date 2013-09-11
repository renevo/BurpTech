package burptech.entity.living.tweaks;

import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EntityVillagerEventHandler 
{
	@ForgeSubscribe
	public void entitySpawning(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityVillager)
			updateVillagerAI((EntityVillager)event.entity);
	}
	
	private void updateVillagerAI(EntityVillager entity)
	{
		entity.tasks.addTask(1, new EntityAITempt(entity, 1.0D, Item.diamond.itemID, false));
		entity.tasks.addTask(1, new EntityAITempt(entity, 1.0D, Item.emerald.itemID, false));
		
	}
}

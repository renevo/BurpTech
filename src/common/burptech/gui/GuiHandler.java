package burptech.gui;

import burptech.BurpTechCore;
import burptech.client.gui.GuiPortableWorkbech;
import burptech.client.gui.GuiRucksack;
import burptech.item.ItemRucksack;
import burptech.item.RucksackInventory;
import burptech.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch (ID)
		{
			case Constants.GUI_PORTABLE_WORKBECH_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == BurpTechCore.configuration.items.portableWorkbench.itemID)
					return new ContainerPortableWorkbench(player.inventory, world);
				
			case Constants.GUI_RUCKSACK_ID:	
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new ContainerRucksack(player.inventory, (RucksackInventory) ((ItemRucksack) player.getCurrentEquippedItem().getItem()).getInventory(player, player.getCurrentEquippedItem()));
			
			case Constants.GUI_ENDER_RUCKSACK_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new ContainerRucksack(player.inventory, player.getInventoryEnderChest());
		}
		
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) 
	{
		switch (ID)
		{
			case Constants.GUI_PORTABLE_WORKBECH_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == BurpTechCore.configuration.items.portableWorkbench.itemID)
					return new GuiPortableWorkbech(player.inventory, world);
				
			case Constants.GUI_RUCKSACK_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new GuiRucksack(player.inventory, (RucksackInventory) ((ItemRucksack)player.getCurrentEquippedItem().getItem()).getInventory(player,  player.getCurrentEquippedItem()));
			
			case Constants.GUI_ENDER_RUCKSACK_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new GuiRucksack(player.inventory, player.getInventoryEnderChest());
		}
		return null;
	}

}

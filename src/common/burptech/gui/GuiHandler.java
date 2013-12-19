package burptech.gui;

import burptech.*;
import burptech.client.gui.*;
import burptech.item.*;
import burptech.lib.Constants;
import burptech.tileentity.TileEntityAdvancedWorkbench;
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

            case Constants.GUI_ADVANCED_WORKBENCH_ID:
                return new ContainerAdvancedWorkbench(player.inventory, (TileEntityAdvancedWorkbench)world.getBlockTileEntity(x,y,z));
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
					return new GuiPortableWorkbench(player.inventory, world);
				
			case Constants.GUI_RUCKSACK_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new GuiRucksack(player.inventory, (RucksackInventory) ((ItemRucksack)player.getCurrentEquippedItem().getItem()).getInventory(player,  player.getCurrentEquippedItem()));
			
			case Constants.GUI_ENDER_RUCKSACK_ID:
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack)
					return new GuiRucksack(player.inventory, player.getInventoryEnderChest());

            case Constants.GUI_ADVANCED_WORKBENCH_ID:
                return new GuiAdvancedWorkbench(player.inventory, (TileEntityAdvancedWorkbench)world.getBlockTileEntity(x, y, z));

		}
		return null;
	}

}

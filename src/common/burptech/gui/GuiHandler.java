package burptech.gui;

import burptech.BurpTechCore;
import burptech.client.gui.GuiPortableWorkbech;
import burptech.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID == Constants.GUI_PORTABLE_WORKBECH_ID)
		{
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == BurpTechCore.configuration.items.portableWorkbench.itemID)
				return new ContainerPortableWorkbench(player.inventory, world);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) 
	{
		if (ID == Constants.GUI_PORTABLE_WORKBECH_ID)
		{
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == BurpTechCore.configuration.items.portableWorkbench.itemID)
				return new GuiPortableWorkbech(player.inventory, world);
		}
		return null;
	}

}

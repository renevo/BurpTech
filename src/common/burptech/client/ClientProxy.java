package burptech.client;

import burptech.BurpTechCore;
import burptech.CommonProxy;
import burptech.client.gui.GuiPortableWorkbech;
import burptech.integration.NeiIntegration;

public class ClientProxy extends CommonProxy
{
	public void addNeiSupport() 
	{
		if (BurpTechCore.configuration.recipePortableWorkbench.getBoolean(true))
    	{
    		NeiIntegration.registerCraftingContainers(GuiPortableWorkbech.class);
    	}

    }
}

package burptech.integration;

import java.lang.reflect.Method;
import java.net.InterfaceAddress;
import java.util.Collection;
import java.util.logging.Level;

import burptech.BurpTechCore;
import net.minecraft.client.gui.inventory.GuiContainer;

public class NeiIntegration 
{
	public static void registerCraftingContainers(Class<? extends GuiContainer> craftingContainer)
	{
		try
        {
            Class API = Class.forName("codechicken.nei.api.API");
            Class IOverlayHandler = Class.forName("codechicken.nei.api.IOverlayHandler");
            Class defaultOverlayHandler = Class.forName("codechicken.nei.recipe.DefaultOverlayHandler");
            
            Method registerGuiOverlayMethod = API.getMethod("registerGuiOverlay", new Class[] { Class.class, String.class });
            Method registerGuiOverlayHandlerMethod = API.getMethod("registerGuiOverlayHandler", new Class[] { Class.class, IOverlayHandler, String.class });
            
            registerGuiOverlayMethod.invoke((Object)null, new Object[]{ craftingContainer, "crafting" });
            registerGuiOverlayHandlerMethod.invoke((Object)null, new Object[]{ craftingContainer, defaultOverlayHandler.newInstance(), "crafting" });
            BurpTechCore.log.log(Level.INFO, "NEI integration of " + craftingContainer.getName() + " was successful");
        }
        catch (Throwable ex)
        {
        	BurpTechCore.log.log(Level.INFO, "NEI integration of " + craftingContainer.getName() + " failed:" + ex.getMessage());
        }
		
	}
}

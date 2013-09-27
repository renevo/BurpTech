package burptech.integration;

import burptech.BurpTechCore;
import cpw.mods.fml.common.Loader;

import java.lang.reflect.Method;
import java.util.logging.Level;

public class BasicComponentsIntegration
{
    /**
     * This mod is a bit finicky, and doesn't look well managed (most ore dict is wrong), might remove all of this at some point
     *
     */
    public static void registerBronzeAge()
    {
        if (!Loader.isModLoaded("BasicComponents"))
            return;

        BurpTechCore.log.info("Registering Bronze Age with Basic Components");

        register("itemPlateIron");
        register("itemPlateGold");

        register("itemIngotCopper");
        register("blockOreCopper");
        register("itemPlateCopper");

        register("itemIngotTin");
        register("blockOreTin");
        register("itemPlateTin");

        register("itemIngotBronze");
        register("itemDustBronze");
        register("itemPlateBronze");
    }

    public static void register(String request)
    {
       if (!Loader.isModLoaded("BasicComponents"))
           return;

       try {
           Class<?> basicRegistry = Class.forName("basiccomponents.api.BasicRegistry");
           Method m = basicRegistry.getMethod("register", String.class);
           m.invoke(null, request);

       } catch(Exception ex) {
           BurpTechCore.log.log(Level.FINEST, "Basic Components Request for " + request + " failed:" + ex.getMessage());
       }
    }
}

package burptech.integration;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.ItemStack;

public class BuildcraftIntegration
{
    public static boolean addFacade(int blockId, int metaData)
    {
        if (Loader.isModLoaded("BuildCraft|Silicon"))
        {
            // this is a fire and forget, need to verify that it actually works somehow
            return FMLInterModComms.sendMessage("BuildCraft|Silicon", "add-facade", blockId + "@" + metaData);
        }

        return false;
    }
}

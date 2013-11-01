package burptech.integration;

import buildcraft.api.fuels.IronEngineFuel;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

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

    public static boolean addEngineFuel(Fluid fluid, float powerPerCycle, int totalBurningTime)
    {
        if (!Loader.isModLoaded("BuildCraft|Silicon"))
            return false;

        IronEngineFuel.addFuel(fluid, powerPerCycle, totalBurningTime);

        return false;
    }
}

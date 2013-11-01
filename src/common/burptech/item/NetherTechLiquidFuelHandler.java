package burptech.item;

import burptech.BurpTechCore;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

/**
 * Liquid Fuel NetherTech Fuel Handler
 *
 */
public class NetherTechLiquidFuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if (fuel == null)
            return 0;

        if (fuel.itemID == BurpTechCore.configuration.items.bucketNetherFluid.itemID)
            return 30000; // lava bucket * 1.5

        return 0;
    }
}

package burptech.item;

import burptech.BurpTechCore;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

/**
 * Solid Fuel NetherTech Fuel Handler
 *
 */
public class NetherTechSolidFuelHandler implements IFuelHandler
{
    private int baseBurnValue = 1600;

    public NetherTechSolidFuelHandler()
    {
        baseBurnValue = net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime(new ItemStack(Item.coal, 1, 0));
    }

	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		if (fuel == null)
			return 0;

        int multiplier = 2;


        if (Loader.isModLoaded("Railcraft"))
            multiplier = 6;

		if (fuel.itemID == BurpTechCore.configuration.items.netherCoal.itemID)
			return baseBurnValue * multiplier;
		
		if (fuel.itemID == BurpTechCore.configuration.blocks.blockNetherCoal.blockID)
			return (baseBurnValue * multiplier) * 9;

		return 0;
	}
}

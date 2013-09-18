package burptech.item;

import burptech.BurpTechCore;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

/**
 * Solid Fuel NetherTech Fuel Handler
 *
 */
public class NetherTechSolidFuelHandler implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		if (fuel == null)
			return 0;
		
		if (fuel.itemID == BurpTechCore.configuration.items.netherCoal.itemID)
			return 1600 * 2; // coal * 2
		
		if (fuel.itemID == BurpTechCore.configuration.blocks.blockNetherCoal.blockID)
			return (1600 * 2) * 9; // (coal * 2) * 9
		
		return 0;
	}
}

package burptech.integration;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.*;

public class AppliedEnergisticsIntegration 
{
	public static boolean addGrinderRecipe(ItemStack input, ItemStack output)
	{
		if (Loader.isModLoaded("AppliedEnergistics"))
		{
			// this is a fire and forget, need to verify that it actually works somehow
			return FMLInterModComms.sendMessage("AppliedEnergistics", "add-grindable", input.itemID + "," + input.getItemDamage() + "," + output.itemID + "," + output.getItemDamage() + ",8");
		}
		
		return false;
	}
}

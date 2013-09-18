package burptech.integration;

import net.minecraft.item.ItemStack;

public class Integration 
{
	/*
	 * Adds a basic crushable item to mods that we integrate with. Returns true if any of them worked
	 */
	public static boolean addCrushableItem(ItemStack input, ItemStack output)
	{
		boolean isAddedToMod = false;
		
		isAddedToMod = isAddedToMod | AppliedEnergisticsIntegration.addGrinderRecipe(input, output);
		isAddedToMod = isAddedToMod | GregTechIntegration.addGrinderRecipe(input, output, null, null, null);
		isAddedToMod = isAddedToMod | IndustrialcraftIntegration.addMaceratorRecipe(input, output);
		isAddedToMod = isAddedToMod | MekanismIntegration.addCrusherRecipe(input, output);
		isAddedToMod = isAddedToMod | RailcraftIntegration.addRockCrusherRecipe(input, output, null, 0);
		isAddedToMod = isAddedToMod | ThermalExpansionIntegration.addPulverizerRecipe(input, output, null, 0);
		
		return isAddedToMod;
	}
}

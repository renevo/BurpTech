package burptech.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

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

    public static boolean addCompressedItem(ItemStack input, ItemStack output)
    {
        boolean isAddedToMod = false;

        isAddedToMod = isAddedToMod | IndustrialcraftIntegration.addCompressorRecipe(input, output);

        return isAddedToMod;
    }

    public static boolean addFluidEnrichment(ItemStack itemInput, FluidStack fluidInput, FluidStack output)
    {
        boolean isAddedToMod = false;

        isAddedToMod = isAddedToMod | IndustrialcraftIntegration.addEnrichmentRecipe(itemInput, fluidInput, output);

        return isAddedToMod;
    }

    public static boolean addFuel(String fluidName, int amount, double euPower, int heat, int mjPower, int burnDuration)
    {
        boolean isAddedToMod = false;

        isAddedToMod = isAddedToMod | IndustrialcraftIntegration.addSemiFlueGeneratorFuel(fluidName, amount, euPower);
        isAddedToMod = isAddedToMod | RailcraftIntegration.addBoilerFuel(FluidRegistry.getFluid(fluidName), heat);
        isAddedToMod = isAddedToMod | BuildcraftIntegration.addEngineFuel(FluidRegistry.getFluid(fluidName), mjPower, burnDuration);

        return isAddedToMod;
    }
}

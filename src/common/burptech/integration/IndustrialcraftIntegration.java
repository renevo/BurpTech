package burptech.integration;

import cpw.mods.fml.common.Loader;
import ic2.api.recipe.*;
import net.minecraft.item.*;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class IndustrialcraftIntegration 
{
    // TODO: Support ore dictionary
	public static boolean addMaceratorRecipe(ItemStack input, ItemStack output)
	{
        if (!Loader.isModLoaded("IC2"))
		    return false;

        Recipes.macerator.addRecipe(new RecipeInputItemStack(input), null, output);

        return true;
	}

    // TODO: Support ore dictionary
    public static boolean addCompressorRecipe(ItemStack input, ItemStack output)
    {
        if (!Loader.isModLoaded("IC2"))
            return false;

        Recipes.compressor.addRecipe(new RecipeInputItemStack(input), null, output);

        return true;
    }

    // TODO: Support ore dictionary
    public static boolean addEnrichmentRecipe(ItemStack itemInput, FluidStack fluidInput, FluidStack fluidOutput)
    {
        if (!Loader.isModLoaded("IC2"))
            return false;

        Recipes.cannerEnrich.addRecipe(fluidInput, new RecipeInputItemStack(itemInput), fluidOutput);

        return true;
    }

    public static boolean addSemiFlueGeneratorFuel(String fluidName, int amount, double power)
    {
        if (!Loader.isModLoaded("IC2"))
            return false;

        Recipes.semiFluidGenerator.addFluid(fluidName, amount, power);

        return true;
    }

    public static ItemStack getItem(String itemName)
    {
        if (!Loader.isModLoaded("IC2"))
        {
            return null;
        }

        return ic2.api.item.Items.getItem(itemName).copy();
    }
}

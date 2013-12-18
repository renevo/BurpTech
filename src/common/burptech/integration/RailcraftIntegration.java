package burptech.integration;

import burptech.BurpTechCore;
import cpw.mods.fml.common.Loader;
import mods.railcraft.api.crafting.*;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class RailcraftIntegration 
{
	public static boolean addRockCrusherRecipe(ItemStack input, ItemStack output, ItemStack bonus, float bonusChance)
	{
        if (!Loader.isModLoaded("Railcraft"))
            return false;

        BurpTechCore.log.info("Adding Railcraft Rockcrusher Recipe for " + input.getDisplayName());

        IRockCrusherRecipe recipe = RailcraftCraftingManager.rockCrusher.createNewRecipe(input, true, true);

        recipe.addOutput(output, 1F);

        if (bonus != null)
            recipe.addOutput(bonus, bonusChance);

		return true;
	}

    public static boolean addBoilerFuel(Fluid fluid, int heatValuePerBucket)
    {
        if (!Loader.isModLoaded("Railcraft"))
            return false;

        FuelManager.addBoilerFuel(fluid, heatValuePerBucket);

        return true;
    }

    public static boolean addCokeOvenRecipe(ItemStack input, boolean matchDamage, boolean matchNBT, ItemStack output, FluidStack liquidOutput, int cookTime)
    {
        if (!Loader.isModLoaded("Railcraft"))
            return false;

        RailcraftCraftingManager.cokeOven.addRecipe(input, matchDamage, matchNBT, output, liquidOutput, cookTime);

        return true;
    }

    public static boolean addBlastFurnaceRecipe(ItemStack input, boolean matchDamage, boolean matchNBT, int cookTime, ItemStack output)
    {
        if (!Loader.isModLoaded("Railcraft"))
            return false;

        RailcraftCraftingManager.blastFurnace.addRecipe(input, matchDamage, matchNBT, cookTime, output);

        return true;
    }
}

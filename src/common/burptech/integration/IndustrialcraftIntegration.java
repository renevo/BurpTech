package burptech.integration;

import cpw.mods.fml.common.Loader;
import ic2.api.recipe.*;
import net.minecraft.item.*;

public class IndustrialcraftIntegration 
{
	public static boolean addMaceratorRecipe(ItemStack input, ItemStack output)
	{
        if (!Loader.isModLoaded("IC2"))
		    return false;

        Recipes.macerator.addRecipe(new RecipeInputItemStack(input), null, output);

        return true;
	}
}

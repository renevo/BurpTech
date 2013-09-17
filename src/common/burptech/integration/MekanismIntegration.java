package burptech.integration;

import java.lang.reflect.Method;

import burptech.BurpTechCore;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;

public class MekanismIntegration 
{
	public static boolean addCrusherRecipe(ItemStack input, ItemStack output)
	{
		if (!Loader.isModLoaded("Mekanism"))
			return false;
		
		try {
			Class<?> recipeClass = Class.forName("mekanism.common.RecipeHandler");
			Method m = recipeClass.getMethod("addCrusherRecipe", ItemStack.class, ItemStack.class);
			m.invoke(null, input, output);
			
			return true;
		} catch(Exception e) {
			BurpTechCore.log.info("Error while adding Crusher Recipe to Mekanism: " + e.getMessage());
		}
		
		return false;
	}
}

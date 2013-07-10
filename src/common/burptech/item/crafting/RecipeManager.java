package burptech.item.crafting;

import net.minecraft.item.crafting.CraftingManager;

public class RecipeManager 
{
    public void addRecipes()
    {
    	(new RecipesVanilla()).addRecipes();
    	(new RecipesBurpTech()).addRecipes();
    	(new RecipesNetherTech()).addRecipes();
    }
}

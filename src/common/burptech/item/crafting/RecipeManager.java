package burptech.item.crafting;

public class RecipeManager 
{
    public void addRecipes()
    {
    	(new RecipesVanilla()).addRecipes();
    	(new RecipesBurpTech()).addRecipes();
    	(new RecipesNetherTech()).addRecipes();
    }
}

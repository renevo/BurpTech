package burptech.item.crafting;

public class RecipeManager 
{
    public void addRecipes()
    {
    	(new RecipesVanilla()).addRecipes();
    	(new RecipesBurpTech()).addRecipes();
    	(new RecipesNetherTech()).addRecipes();
    }

    public void postInitialization()
    {
        (new RecipesNetherTech()).postInitialization();
        (new RecipesIntegration()).postInitialization();
    }
}

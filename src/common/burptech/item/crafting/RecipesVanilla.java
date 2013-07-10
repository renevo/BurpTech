package burptech.item.crafting;

import burptech.BurpTechCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class RecipesVanilla 
{
    /**
     * Adds the crafting recipes to the CraftingManager.
     */
    public void addRecipes()
    {
    	if (BurpTechCore.configuration.recipeCobwebs.getBoolean(true))
    		GameRegistry.addRecipe(new ItemStack(Block.web),new Object[] {"# #"," # ","# #",'#',Item.silk});
    }
}

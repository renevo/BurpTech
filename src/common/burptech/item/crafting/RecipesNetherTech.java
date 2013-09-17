package burptech.item.crafting;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.*;
import burptech.*;
import burptech.item.*;
import burptech.integration.*;

public class RecipesNetherTech 
{
    /**
     * Adds the crafting recipes to the CraftingManager.
     */
    public void addRecipes()
    {
		addSolidFuels();
    }
    
    private void addSolidFuels()
    {
    	if (!BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true))
    		return;
    	
		if (!Integration.addCrushableItem(new ItemStack(Block.netherrack), new ItemStack(BurpTechCore.configuration.items.netherDust)))
		{
			// add vanilla recipe for nether coal if no mods are present to support it
			GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal), 
					new Object[] { "###", "#C#", "###", '#', new ItemStack(Block.netherrack), 'C', new ItemStack(Item.coal, 1, 1) });
		} else {
			// find out if we have charcoal dust, if so, use it for our recipe
			ArrayList<ItemStack> charcoals = OreDictionary.getOres("dustCharcoal");

			// add modded recipe. might switch this to a compression later with an intermediate item?
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal),
					new Object[] {"###", "#C#", "###", '#', "dustNetherrack", 'C', charcoals.size() == 0 ? new ItemStack(Item.coal, 1, 1) : "dustCharcoal" }));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal),
					new Object[] {"###", "#C#", "###", '#', "itemDustNetherrack", 'C', charcoals.size() == 0 ? new ItemStack(Item.coal, 1, 1) : "dustCharcoal" }));
		}
		
		// nether coal block
		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.blocks.blockNetherCoal),
				new Object[] { "###", "###", "###", '#', BurpTechCore.configuration.items.netherCoal });
		
		GameRegistry.registerFuelHandler(new NetherTechSolidFuelHandler());
    }

}

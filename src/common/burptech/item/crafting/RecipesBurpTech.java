package burptech.item.crafting;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import burptech.BurpTechCore;
import burptech.block.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public class RecipesBurpTech 
{
    /**
     * Adds the crafting recipes to the CraftingManager.
     */
    public void addRecipes()
    {
    	if (BurpTechCore.configuration.recipeRucksack.getBoolean(true))
    	{
    		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.rucksack),
    						new Object[] { "#s#", "scs", "#s#", '#', Item.leather, 's', Item.silk, 'c', Block.chest });
    		
    		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.rucksack),
					new Object[] { "s#s", "#c#", "s#s", '#', Item.leather, 's', Item.silk, 'c', Block.chest });
    		
    		GameRegistry.addRecipe(new RecipesRucksackDyes());
    	}
    	
    	if (BurpTechCore.configuration.recipeEnderRucksack.getBoolean(true))
    	{
    		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.enderRucksack),
    				new Object[] { "#s#", "scs", "#s#", '#', Item.leather, 's', Item.silk, 'c', Block.enderChest });
    		
    		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.enderRucksack),
    				new Object[] { "s#s", "#c#", "s#s", '#', Item.leather, 's', Item.silk, 'c', Block.enderChest });
    	}
    	
    	if (BurpTechCore.configuration.recipePortableWorkbench.getBoolean(true))
    	{
    		GameRegistry.addShapelessRecipe(new ItemStack(BurpTechCore.configuration.items.portableWorkbench),
    				new Object[] { Block.workbench, Item.silk });
    	}
    	
    	if (BurpTechCore.configuration.recipeCookedEgg.getBoolean(true))
    	{
    		GameRegistry.addSmelting(Item.egg.itemID, new ItemStack(BurpTechCore.configuration.items.cookedEgg), 0.35F); // xp matches standard food cooking xp
    	}

        if (BurpTechCore.configuration.recipeCobbleGenerator.getBoolean(true))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.blocks.blockCobbleGenerator,
                    "CCC", "CHC", "CPC", 'C', "cobblestone", 'H', Block.hopperBlock, 'P', Block.pistonBase));
        }

        if (BurpTechCore.configuration.recipeAdvancedWorkbench.getBoolean(true))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.blocks.blockAdvancedWorkbench,
                    "RcR", "PCP", "RWR", 'c', new ItemStack(Block.carpet, 1, 11), 'C', Block.chest, 'W', Block.workbench, 'R', "dyeRed", 'P', "plankWood"));
        }

        if (BurpTechCore.configuration.recipeSickle.getBoolean(false) && !Loader.isModLoaded("ProjRed|Exploration"))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.woodSickle, " m ", "  m", "sm ",  's', Item.stick, 'm', "plankWood"));
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.stoneSickle, " m ", "  m", "sm ",  's', Item.stick, 'm', "cobblestone"));
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.ironSickle, " m ", "  m", "sm ",  's', Item.stick, 'm', new ItemStack(Item.ingotIron)));
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.goldSickle, " m ", "  m", "sm ",  's', Item.stick, 'm', new ItemStack(Item.ingotGold)));
            GameRegistry.addRecipe(new ShapedOreRecipe(BurpTechCore.configuration.items.diamondSickle, " m ", "  m", "sm ",  's', Item.stick, 'm', new ItemStack(Item.diamond)));
        }
    }
}

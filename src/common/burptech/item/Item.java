package burptech.item;

import net.minecraftforge.common.*;


/*
 * Item definitions for BurpTech 
 */
public class Item 
{
	/*
	 * Default constructor
	 */
	public Item(Configuration configuration)
	{
		// load the config
		
		portableCraftingBenchId = configuration.getItem("PortableCraftingBench", 18258); // currently what it is in burpcraft v4
	}
	
	/*
	 * Portable Crafting Bench
	 */
	public Property portableCraftingBenchId;
	
}

package burptech.item;

import net.minecraft.item.Item;
import net.minecraftforge.common.*;


/*
 * Item definitions for BurpTech 
 */
public class Items 
{
	Configuration configuration;
	
	public Item portableCraftingBench;
	
	/*
	 * Default constructor
	 */
	public Items(Configuration configuration)
	{
		this.configuration = configuration;
	}
		
	/*
	 * Creates all of the item instances
	 */
	public void create()
	{
		portableCraftingBench = new Item(configuration.getItem("PortableCraftingBench", 18258).getInt());
	}
	
}

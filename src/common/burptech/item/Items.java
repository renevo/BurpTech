package burptech.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.*;


/*
 * Item definitions for BurpTech 
 */
public class Items 
{
	Configuration configuration;
	
	public Item portableWorkbench;
	
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
		portableWorkbench = new ItemPortableWorkbench(configuration.getItem("PortableWorkbench", 18002).getInt()).setUnlocalizedName("portableWorkbench").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);;
	}
	
}

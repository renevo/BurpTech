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
	public Item rucksack;
	public Item enderRucksack;
	
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
		enderRucksack = new ItemRucksack(configuration.getItem("EnderRucksack", 18000).getInt(), true).setUnlocalizedName("enderRucksack").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		rucksack = new ItemRucksack(configuration.getItem("Rucksack", 18001).getInt(), false).setUnlocalizedName("rucksack").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		portableWorkbench = new ItemPortableWorkbench(configuration.getItem("PortableWorkbench", 18002).getInt()).setUnlocalizedName("portableWorkbench").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);;
    }
	
}

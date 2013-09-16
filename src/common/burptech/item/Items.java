package burptech.item;

import burptech.lib.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
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

	public Item cookedEgg;
	
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
		enderRucksack = new ItemRucksack(configuration.getItem("EnderRucksack", burptech.lib.Constants.ITEM_START + 0).getInt(), true).setUnlocalizedName("enderRucksack").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		rucksack = new ItemRucksack(configuration.getItem("Rucksack", burptech.lib.Constants.ITEM_START + 1).getInt(), false).setUnlocalizedName("rucksack").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		portableWorkbench = new ItemPortableWorkbench(configuration.getItem("PortableWorkbench", burptech.lib.Constants.ITEM_START + 2).getInt()).setUnlocalizedName("portableWorkbench").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);;
		
		cookedEgg = new ItemFood(configuration.getItem("CookedEgg", burptech.lib.Constants.ITEM_START + 3).getInt(), 2, .1F, false).setUnlocalizedName("cookedEgg").setTextureName(Constants.MOD_ID + ":" + "egg_cooked").setMaxStackSize(16);
    }
	
}

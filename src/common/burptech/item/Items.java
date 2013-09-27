package burptech.item;

import burptech.*;
import burptech.lib.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraftforge.oredict.OreDictionary;

/*
 * Item definitions for BurpTech 
 */
public class Items 
{
	public Item portableWorkbench;
	public Item rucksack;
	public Item enderRucksack;

	public Item cookedEgg;
	
	public Item netherDust;
	public Item netherCoal;
	
	/*
	 * Creates all of the item instances
	 */
	public void create(BurpTechConfig configuration)
	{
        // create items
		enderRucksack = new ItemRucksack(configuration.itemEnderRucksack.getInt(), true).setUnlocalizedName("enderRucksack").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		rucksack = new ItemRucksack(configuration.itemRucksack.getInt(), false).setUnlocalizedName("rucksack").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		portableWorkbench = new ItemPortableWorkbench(configuration.itemPortableWorkbench.getInt()).setUnlocalizedName("portableWorkbench").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);;
		cookedEgg = new ItemFood(configuration.itemCookedEgg.getInt(), 2, .1F, false).setUnlocalizedName("cookedEgg").setTextureName(Constants.MOD_ID + ":" + "egg_cooked").setMaxStackSize(16);
		netherDust = new Item(configuration.itemNetherDust.getInt()).setUnlocalizedName("netherDust").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Constants.MOD_ID + ":" + "nether_dust");
		netherCoal = new Item(configuration.itemNetherCoal.getInt()).setUnlocalizedName("netherCoal").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Constants.MOD_ID + ":" + "nether_coal");

        // item registry
        GameRegistry.registerItem(enderRucksack, "enderRucksack");
        GameRegistry.registerItem(rucksack, "rucksack");
        GameRegistry.registerItem(portableWorkbench, "portableWorkbench");
        GameRegistry.registerItem(cookedEgg, "cookedEgg");
        GameRegistry.registerItem(netherDust, "netherDust");
        GameRegistry.registerItem(netherCoal, "netherCoal");

        // ore dictionary (pulled from: http://minecraftmodcustomstuff.wikia.com/wiki/Ore_Dictionary - more here: http://www.minecraftforge.net/wiki/Common_Oredict_names)
        OreDictionary.registerOre("dustNetherrack", netherDust);
        OreDictionary.registerOre("itemDustNetherrack", netherDust);
    }
	
}

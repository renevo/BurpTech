package burptech.item;

import burptech.*;
import burptech.lib.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/*
 * Item definitions for BurpTech 
 */
public class Items 
{
	public Item portableWorkbench;
	public Item rucksack;
	public Item enderRucksack;

	public Item cookedEgg;
	

	public Item netherCoal;
    public Item genericDust;

    public ItemStack goldDust;
    public ItemStack ironDust;
    public ItemStack netherDust;
    public ItemStack infusedNetherDust;

    public Item bucketNetherFluid;
    public Item cellNetherFluid;

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
		netherCoal = new Item(configuration.itemNetherCoal.getInt()).setUnlocalizedName("netherCoal").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Constants.MOD_ID + ":" + "nether_coal");
        bucketNetherFluid = new ItemBucket(configuration.itemBucketNetherFluid.getInt(), configuration.blockNetherFluid.getInt()).setUnlocalizedName("bucketNetherFluid").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Constants.MOD_ID + ":" + "nether_fluid_bucket").setContainerItem(Item.bucketEmpty);
        cellNetherFluid = new Item(configuration.itemCellNetherFluid.getInt()).setUnlocalizedName("cellNetherFluid").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Constants.MOD_ID + ":" + "nether_fluid_cell");
        genericDust = new ItemDust(configuration.itemDust.getInt());

        // item registry
        GameRegistry.registerItem(enderRucksack, "enderRucksack");
        GameRegistry.registerItem(rucksack, "rucksack");
        GameRegistry.registerItem(portableWorkbench, "portableWorkbench");
        GameRegistry.registerItem(cookedEgg, "cookedEgg");
        GameRegistry.registerItem(netherCoal, "netherCoal");
        GameRegistry.registerItem(bucketNetherFluid, "bucketNetherFluid");
        GameRegistry.registerItem(cellNetherFluid, "cellNetherFluid");
        GameRegistry.registerItem(genericDust, "genericDust");

        // ore dictionary (pulled from: http://minecraftmodcustomstuff.wikia.com/wiki/Ore_Dictionary - more here: http://www.minecraftforge.net/wiki/Common_Oredict_names)

        // Ore Dusts
        ArrayList<ItemStack> goldDusts = OreDictionary.getOres("dustGold");
        ArrayList<ItemStack> ironDusts = OreDictionary.getOres("dustIron");
        ArrayList<ItemStack> netherDusts = OreDictionary.getOres("dustNetherrack");
        ArrayList<ItemStack> infusedNetherDusts = OreDictionary.getOres("dustInfusedNetherrack");

        if (netherDusts.isEmpty())
        {
            netherDust = new ItemStack(genericDust, 1, 0);
            OreDictionary.registerOre("dustNetherrack", netherDust);
        }
        else
        {
            netherDust = netherDusts.get(0);
        }

        if (ironDusts.isEmpty())
        {
            ironDust = new ItemStack(genericDust, 1, 1);
            OreDictionary.registerOre("dustIron", ironDust);
        }
        else
        {
            ironDust = ironDusts.get(0);
        }

        if (goldDusts.isEmpty())
        {
            goldDust = new ItemStack(genericDust, 1, 2);
            OreDictionary.registerOre("dustGold", goldDust);
        }
        else
        {
            goldDust = goldDusts.get(0);
        }

        if (infusedNetherDusts.isEmpty())
        {
            infusedNetherDust = new ItemStack(genericDust, 1, 3);
            OreDictionary.registerOre("dustInfusedNetherrack", infusedNetherDust);
        }
        else
        {
            infusedNetherDust = netherDusts.get(0);
        }

    }
	
}

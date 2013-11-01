package burptech;

import java.io.File;

import burptech.block.*;
import burptech.item.*;
import burptech.lib.*;
import net.minecraftforge.common.*;

/*
 * All config for BurpTech here
 */
public class BurpTechConfig 
{
	public Property disableEndermanGriefing;
	public Property enableSlimeSpawningRestrictions;
	public Property enableNetherSpawningRestrictions;
	public Property enableMobsEatingOffOfGround;
	public Property enableMobsWandering;
	public Property enableGreedyVillagers;
	public Property enableIlluminatedCocoa;
	
	public Property recipeCobwebs;
	public Property recipePortableWorkbench;
	public Property recipeRucksack;
	public Property recipeEnderRucksack;
	
	public Property recipeCookedEgg;
	
	public Property enableNetherTechSolidFuels;
    public Property enableNetherTechLiquidFuels;

	public Property blockIlluminatedCocoa;
	public Property blockNetherCoal;
    public Property blockOres;
    public Property blockNetherFluid;
	
	public Property itemEnderRucksack;
	public Property itemRucksack;
	public Property itemPortableWorkbench;
	public Property itemCookedEgg;
	public Property itemNetherDust;
	public Property itemNetherCoal;
    public Property itemInfusedNetherDust;
    public Property itemBucketNetherFluid;
    public Property itemCellNetherFluid;

	/*
	 * BurpTech Items
	 */
	public Items items;
	
	/*
	 * BurpTech Blocks
	 */
	public Blocks blocks;
	
	/*
	 * Loads the burptech configuration file
	 */
	public static BurpTechConfig load(File configFolder)
	{
		BurpTechConfig result = new BurpTechConfig();
		
		File configurationFile = new File(configFolder.getAbsolutePath() + "/" + Constants.MOD_NAME + ".cfg");
		Configuration configuration = new Configuration(configurationFile);
		configuration.load();
		
		// Tweaks
		result.disableEndermanGriefing = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "DisableEndermanGriefing", true);
		result.disableEndermanGriefing.comment = "Disables Enderman from picking up any blocks other than vanilla flowers";
		
		result.enableSlimeSpawningRestrictions = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS,  "EnableSlimeSpawnRestrictions", true);
		result.enableSlimeSpawningRestrictions.comment = "Restricts Slimes (Green) to only spawn on Stone, Dirt, and Grass";
		
		result.enableNetherSpawningRestrictions = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS,  "EnableNetherSpawnRestrictions", true);
		result.enableNetherSpawningRestrictions.comment = "Restricts Nether Mobs to only spawn on netherrack, nether brick, and soul sand";

		result.enableMobsEatingOffOfGround = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "EnableMobsEatingDroppedFood", true);
		result.enableMobsEatingOffOfGround.comment = "Adds a new AI for mobs eating breeding food from the ground near them";
		
		result.enableMobsWandering = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "EnableMobsWandering", true);
		result.enableMobsWandering.comment = "When enabled, mobs will keep wandering past the 32 block vanilla limit";
		
		result.enableGreedyVillagers = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "EnableGreedyVillagers", true);
		result.enableGreedyVillagers.comment = "When enabled, villagers will follow players with diamonds and emeralds in there hands";
		
		result.enableIlluminatedCocoa = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "IlluminatedCocoaPlants", true);
		result.enableIlluminatedCocoa.comment = "When enabled, allows you to right click a grown cocoa plant with glowstone to turn it into a lamp";
				
		// Recipes
		result.recipeCobwebs = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "Cobwebs", true);
		result.recipeCobwebs.comment = "Enables crafting of cobwebs from string";
		
		result.recipePortableWorkbench = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "PortableWorkbench", true);
		result.recipePortableWorkbench.comment = "Enables crafting of Portable Workbench";
		
		result.recipeRucksack = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "Rucksack", true);
		result.recipeRucksack.comment = "Enables crafting of Rucksacks";
		
		result.recipeEnderRucksack = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "EnderRucksack", true);
		result.recipeEnderRucksack.comment = "Enables crafting of Ender Rucksacks";
		
		result.recipeCookedEgg = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "CookedEggs", true);
		result.recipeCookedEgg.comment = "Enables cooked eggs for food";
		
		// Nether Tech
		result.enableNetherTechSolidFuels = configuration.get(Constants.CONFIG_CATEGORY_NETHERTECH, "SolidFuels", true);
		result.enableNetherTechSolidFuels.comment = "Enables Nether Tech Solid Fuels";

        result.enableNetherTechLiquidFuels = configuration.get(Constants.CONFIG_CATEGORY_NETHERTECH, "LiquidFuels", true);
        result.enableNetherTechLiquidFuels.comment = "Enables Nether Tech Liquid Fuels";

        // Items
        result.itemEnderRucksack = configuration.getItem("EnderRucksack", burptech.lib.Constants.ITEM_START + 0);
        result.itemRucksack = configuration.getItem("Rucksack", burptech.lib.Constants.ITEM_START + 1);
        result.itemPortableWorkbench = configuration.getItem("PortableWorkbench", burptech.lib.Constants.ITEM_START + 2);
        result.itemCookedEgg = configuration.getItem("CookedEgg", burptech.lib.Constants.ITEM_START + 3);
        result.itemNetherDust = configuration.getItem("NetherDust", burptech.lib.Constants.ITEM_START + 4);
        result.itemNetherCoal = configuration.getItem("NetherCoal", burptech.lib.Constants.ITEM_START + 5);
        result.itemInfusedNetherDust = configuration.getItem("InfusedNetherDust", burptech.lib.Constants.ITEM_START + 6);
        result.itemBucketNetherFluid = configuration.getItem("BucketNetherFluid", burptech.lib.Constants.ITEM_START + 7);
        result.itemCellNetherFluid = configuration.getItem("CellNetherFluid", burptech.lib.Constants.ITEM_START + 8);

        // Blocks
		result.blockIlluminatedCocoa = configuration.getBlock("IlluminatedCocoaPlant", Constants.BLOCK_START + 0);
		result.blockNetherCoal = configuration.getBlock("BlockNetherCoal", Constants.BLOCK_START + 1);
        result.blockNetherFluid = configuration.getBlock("BlockNetherFluid", Constants.BLOCK_START + 2);

        result.items = new Items();
        result.blocks = new Blocks();

        result.items.create(result);
		result.blocks.create(result);
		
		// save only if modified
		if (configuration.hasChanged())
			configuration.save();
		
		// return
		return result;
	}
}

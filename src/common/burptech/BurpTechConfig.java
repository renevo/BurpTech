package burptech;

import java.io.File;

import burptech.block.Blocks;
import burptech.item.Items;
import burptech.lib.*;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

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
	
	public Property recipeCobwebs;
	
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
		
		
		// Recipes
		result.recipeCobwebs = configuration.get(Constants.CONFIG_CATEGORY_RECIPES, "Cobwebs", true);
		result.recipeCobwebs.comment = "Enables crafting of cobwebs from string";
		
		
		// Blocks
		result.blocks = new Blocks(configuration);
		
		// Items
		result.items = new Items(configuration);
		
		// save
		configuration.save();
		
		// return
		return result;
	}
}

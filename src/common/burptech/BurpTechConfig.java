package burptech;

import java.io.File;

import burptech.block.Block;
import burptech.item.Item;
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
	public Property enablePigZombieSpawningRestrictions;
	public Property enableMagmaCubeSpawningRestrictions;
	public Property enableWitherSkeletonSpawningRestrictions;
	
	/*
	 * BurpTech Items
	 */
	public Item Items;
	
	/*
	 * BurpTech Blocks
	 */
	public Block Blocks;
	
	/*
	 * Loads the burptech configuration file
	 */
	public static BurpTechConfig Load(File configFolder)
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
		
		result.enablePigZombieSpawningRestrictions = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS,  "EnablePigZombieSpawnRestrictions", true);
		result.enablePigZombieSpawningRestrictions.comment = "Restricts Zombie Pigmen to only spawn on netherrack and nether brick";
		
		result.enableMagmaCubeSpawningRestrictions = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS,  "EnableMagmaCubeSpawnRestrictions", true);
		result.enableMagmaCubeSpawningRestrictions.comment = "Restricts Magma Cubes to only spawn on netherrack and nether brick";
		
		result.enableWitherSkeletonSpawningRestrictions = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS,  "EnableWitherSkeletonSpawnRestrictions", true);
		result.enableWitherSkeletonSpawningRestrictions.comment = "Restricts Wither Skeletons to only spawn on netherrack and nether brick";
		
		// Blocks
		result.Blocks = new Block(configuration);
		
		// Items
		result.Items = new Item(configuration);
		
		// save
		configuration.save();
		
		// return
		return result;
	}
}

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
	/*
	 * Disables Enderman from picking up any blocks other than flowers
	 */
	public Property disableEndermanGriefing;
	
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

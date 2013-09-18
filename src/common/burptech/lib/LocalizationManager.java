package burptech.lib;

import burptech.BurpTechCore;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationManager 
{
	// TODO: Support built in localization patterns
	
	public static void addLocalization()
	{
		LanguageRegistry.addName(BurpTechCore.configuration.items.portableWorkbench, "Portable Workbench");
		LanguageRegistry.addName(BurpTechCore.configuration.items.rucksack, "Rucksack");
    	LanguageRegistry.addName(BurpTechCore.configuration.items.enderRucksack, "Ender Rucksack");
    	
    	LanguageRegistry.addName(BurpTechCore.configuration.items.cookedEgg, "Cooked Egg");
    	
    	LanguageRegistry.addName(BurpTechCore.configuration.items.netherCoal, "Nether Infused Coal");
    	LanguageRegistry.addName(BurpTechCore.configuration.items.netherDust, "Nether Dust");
    	LanguageRegistry.addName(BurpTechCore.configuration.blocks.blockNetherCoal, "Block of Nether Infused Coal");
	}
}

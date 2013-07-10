package burptech.lib;

import burptech.BurpTechCore;
import burptech.item.Items;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationManager 
{

	public static void addLocalization()
	{
		LanguageRegistry.addName(BurpTechCore.configuration.items.portableWorkbench, "Portable Workbench");
		LanguageRegistry.addName(BurpTechCore.configuration.items.rucksack, "Rucksack");
    	LanguageRegistry.addName(BurpTechCore.configuration.items.enderRucksack, "Ender Rucksack");
	}
}

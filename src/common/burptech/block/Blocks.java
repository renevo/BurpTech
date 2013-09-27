package burptech.block;

import burptech.item.ItemBlockOres;
import cpw.mods.fml.common.registry.GameRegistry;
import burptech.BurpTechConfig;
import burptech.BurpTechCore;
import burptech.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

/*
 * Block definitions for BurpTech
 */
public class Blocks 
{
	public Block blockIlluminatedCocoaOn;
	public Block blockIlluminatedCocoaOff;
	public Block blockNetherCoal;
	public Block blockOres;

	public void create(BurpTechConfig configuration)
	{
        // create blocks
		addIlluminatedCocoa(configuration);
		
		blockNetherCoal = (new Block(configuration.blockNetherCoal.getInt(), Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blockNetherCoal").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Constants.MOD_ID + ":" + "nether_coal_block");
        blockOres = (new BlockOres(configuration.blockOres.getInt())).setUnlocalizedName("blockOres");

        // block registry
		GameRegistry.registerBlock(blockNetherCoal, "blockNetherCoal");
        GameRegistry.registerBlock(blockOres, ItemBlockOres.class, "blockOres");

        // ore dictionary (pulled from: http://minecraftmodcustomstuff.wikia.com/wiki/Ore_Dictionary - more here: http://www.minecraftforge.net/wiki/Common_Oredict_names)
        for (int i = 0; i < BlockOres.ORES.length; i++)
        {
            OreDictionary.registerOre(BlockOres.ORES[i], new ItemStack(blockOres, 1, i));
        }

        // harvesting

        MinecraftForge.setBlockHarvestLevel(blockOres, 0, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(blockOres, 1, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(blockOres, 2, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(blockOres, 3, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(blockOres, 4, "pickaxe", 2);

        MinecraftForge.setBlockHarvestLevel(blockNetherCoal, 0, "pickaxe", 1);
	}
	
	private void addIlluminatedCocoa(BurpTechConfig configuration)
	{
		if (!configuration.enableIlluminatedCocoa.getBoolean(true))
			return;
		
		int illuminatedCocoaPlantID = configuration.blockIlluminatedCocoa.getInt();
		
		BurpTechCore.log.info("Enabling Illuminated Cocoa Blocks");
		blockIlluminatedCocoaOn = new BlockIlluminatedCocoa(illuminatedCocoaPlantID, true).setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cocoa").setTextureName("cocoa");
		int cocoaPlantID = Block.cocoaPlant.blockID;
		Block.blocksList[cocoaPlantID] = null;
		blockIlluminatedCocoaOff = new BlockIlluminatedCocoa(cocoaPlantID, false).setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cocoa").setTextureName("cocoa");
	}
}

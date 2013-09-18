package burptech.block;

import cpw.mods.fml.common.registry.GameRegistry;
import burptech.BurpTechConfig;
import burptech.BurpTechCore;
import burptech.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/*
 * Block definitions for BurpTech
 */
public class Blocks 
{
	public Block blockIlluminatedCocoaOn;
	public Block blockIlluminatedCocoaOff;
	public Block blockNetherCoal;
	
	public void create(BurpTechConfig configuration)
	{
		addIlluminatedCocoa(configuration);
		
		blockNetherCoal = (new Block(configuration.blockNetherCoal.getInt(), Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blockNetherCoal").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Constants.MOD_ID + ":" + "nether_coal_block");
		GameRegistry.registerBlock(blockNetherCoal, "blockNetherCoal");
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

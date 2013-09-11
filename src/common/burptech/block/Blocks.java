package burptech.block;

import burptech.BurpTechCore;
import burptech.lib.Constants;
import net.minecraft.block.Block;
import net.minecraftforge.common.*;

/*
 * Block definitions for BurpTech
 */
public class Blocks 
{
	Configuration configuration;
	
	public Block illuminatedCocoaOn;
	public Block illuminatedCocoaOff;
	
	public Property enableIlluminatedCocoa;
	
	/*
	 * Default constructor
	 */
	public Blocks(Configuration configuration)
	{
		this.configuration = configuration;
	}
	
	public void create()
	{
		enableIlluminatedCocoa = configuration.get(Constants.CONFIG_CATEGORY_TWEAKS, "IlluminatedCocoaPlants", true);
		enableIlluminatedCocoa.comment = "When enabled, allows you to right click a grown cocoa plant with glowstone to turn it into a lamp";
		
		if (enableIlluminatedCocoa.getBoolean(true))
		{
			BurpTechCore.log.info("Adding Illuminated Cocoa Plants");
			AddIlluminatedCocoa(Constants.BLOCK_START + 0);
		}
	}
	
	private void AddIlluminatedCocoa(int defaultBlockID)
	{
		Property illuminatedCocoaOnBlockId = configuration.getBlock("IlluminatedCocoa",	defaultBlockID, "The ID for the Illuminated Cocoa Plants. (Replaces default BlockCocoa)");
		
		int illuminatedCocoaPlantID = illuminatedCocoaOnBlockId.getInt();
		if (illuminatedCocoaPlantID != 0)
		{
			illuminatedCocoaOn = new BlockIlluminatedCocoa(illuminatedCocoaPlantID, true).setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cocoa").setTextureName("cocoa");
			int cocoaPlantID = Block.cocoaPlant.blockID;
			Block.blocksList[cocoaPlantID] = null;
			illuminatedCocoaOff = new BlockIlluminatedCocoa(cocoaPlantID, false).setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cocoa").setTextureName("cocoa");
		}
	}
}

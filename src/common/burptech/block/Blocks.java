package burptech.block;

import burptech.integration.BuildcraftIntegration;
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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
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
    public Block blockNetherFluid;

    public Fluid fluidNetherFluid;

	public void create(BurpTechConfig configuration)
	{
        // create blocks
		addIlluminatedCocoa(configuration);
		
		blockNetherCoal = (new Block(configuration.blockNetherCoal.getInt(), Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blockNetherCoal").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Constants.MOD_ID + ":" + "nether_coal_block");

        fluidNetherFluid = new Fluid("nether").setDensity(800).setViscosity(1500);
        FluidRegistry.registerFluid(fluidNetherFluid);

        blockNetherFluid = new BlockBurpTechFluid(configuration.blockNetherFluid.getInt(),fluidNetherFluid, Material.lava).setParticleColor(131,24,24).setUnlocalizedName("blockNetherFluid");

        // block registry
		GameRegistry.registerBlock(blockNetherCoal, "blockNetherCoal");
        GameRegistry.registerBlock(blockNetherFluid, "blockNetherFluid");

        // harvesting
        MinecraftForge.setBlockHarvestLevel(blockNetherCoal, 0, "pickaxe", 1);

        // facades
        BuildcraftIntegration.addFacade(blockNetherCoal.blockID, 0);

        // multi part??? not sure how to do this tbh or if you even need to with solid blocks.
	}

    private void addOres(BurpTechConfig configuration)
    {
        blockOres = (new BlockOres(configuration.blockOres.getInt())).setUnlocalizedName("blockOres");

        GameRegistry.registerBlock(blockOres, ItemBlockOres.class, "blockOres");

        // ore dictionary (pulled from: http://minecraftmodcustomstuff.wikia.com/wiki/Ore_Dictionary - more here: http://www.minecraftforge.net/wiki/Common_Oredict_names)
        for (int i = 0; i < BlockOres.ORES.length; i++)
        {
            OreDictionary.registerOre(BlockOres.ORES[i], new ItemStack(blockOres, 1, i));
        }

        MinecraftForge.setBlockHarvestLevel(blockOres, 0, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(blockOres, 1, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(blockOres, 2, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(blockOres, 3, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(blockOres, 4, "pickaxe", 2);
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

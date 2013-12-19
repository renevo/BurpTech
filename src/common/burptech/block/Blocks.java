package burptech.block;

import burptech.tileentity.*;
import cpw.mods.fml.common.registry.GameRegistry;
import burptech.BurpTechConfig;
import burptech.BurpTechCore;
import burptech.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/*
 * Block definitions for BurpTech
 */
public class Blocks 
{
	public Block blockIlluminatedCocoaOn;
	public Block blockIlluminatedCocoaOff;

    public Block blockNetherCoal;
    public Block blockNetherFluid;
    public Fluid fluidNetherFluid;


    public Block blockAdvancedWorkbench;
    public Block blockCobbleGenerator;

	public void create(BurpTechConfig configuration)
	{
        // create blocks
		addIlluminatedCocoa(configuration);
        addNetherCoalBlocks(configuration);
        addNetherFuelBlocks(configuration);

        if (configuration.recipeAdvancedWorkbench.getBoolean(false))
        {
            blockAdvancedWorkbench = new BlockAdvancedWorkbench(configuration.blockAdvancedWorkbench.getInt()).setHardness(5.0f).setResistance(10.0f).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("blockAdvancedWorkbench");
            GameRegistry.registerBlock(blockAdvancedWorkbench, "blockAdvancedWorkbench");
            GameRegistry.registerTileEntity(TileEntityAdvancedWorkbench.class, "AdvancedWorkbench");
            MinecraftForge.setBlockHarvestLevel(blockAdvancedWorkbench, 0, "axe", 1);
        }

        if (configuration.recipeCobbleGenerator.getBoolean(true))
        {
            blockCobbleGenerator = new BlockCobbleGenerator(configuration.blockCobbleGenerator.getInt()).setHardness(5.0f).setResistance(10.0f).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blockCobbleGenerator");
            GameRegistry.registerBlock(blockCobbleGenerator, "blockCobbleGenerator");
            GameRegistry.registerTileEntity(TileEntityCobbleGenerator.class, "CobblestoneGenerator");
            MinecraftForge.setBlockHarvestLevel(blockCobbleGenerator, 0, "pickaxe", 1);
        }
	}

    private void addNetherCoalBlocks(BurpTechConfig configuration)
    {
        if (!configuration.enableNetherTechSolidFuels.getBoolean(true))
            return;

        blockNetherCoal = (new Block(configuration.blockNetherCoal.getInt(), Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blockNetherCoal").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Constants.MOD_ID + ":" + "nether_coal_block");
        fluidNetherFluid = new Fluid("nether").setDensity(800).setViscosity(1500);
        GameRegistry.registerBlock(blockNetherCoal, "blockNetherCoal");
        MinecraftForge.setBlockHarvestLevel(blockNetherCoal, 0, "pickaxe", 1);
    }

    private void addNetherFuelBlocks(BurpTechConfig configuration)
    {
        if (!configuration.enableNetherTechLiquidFuels.getBoolean(true))
            return;

        FluidRegistry.registerFluid(fluidNetherFluid);
        blockNetherFluid = new BlockBurpTechFluid(configuration.blockNetherFluid.getInt(),fluidNetherFluid, Material.lava).setBurning(true).setParticleColor(131,24,24).setUnlocalizedName("blockNetherFluid");
        GameRegistry.registerBlock(blockNetherFluid, "blockNetherFluid");
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

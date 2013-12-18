package burptech.item.crafting;

import java.util.ArrayList;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLStateEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.*;
import burptech.*;
import burptech.item.*;
import burptech.integration.*;

public class RecipesNetherTech 
{
    /**
     * Adds the crafting recipes to the CraftingManager.
     */
    public void addRecipes()
    {
		addSolidFuels();
        addLiquidFuels();
    }

    private void addLiquidFuels()
    {
        if (!BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean(true))
            return;

        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(BurpTechCore.configuration.items.bucketNetherFluid), new ItemStack(Item.bucketEmpty));
        BucketHandler.INSTANCE.buckets.put(BurpTechCore.configuration.blocks.blockNetherFluid, BurpTechCore.configuration.items.bucketNetherFluid);
    }

    private void addSolidFuels()
    {
    	if (!BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true))
    		return;

		// nether coal block
		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.blocks.blockNetherCoal),
				new Object[] { "###", "###", "###", '#', BurpTechCore.configuration.items.netherCoal });

        // reverse nether coal block recipe
		GameRegistry.addShapelessRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal, 9), BurpTechCore.configuration.blocks.blockNetherCoal);

        // torches
        GameRegistry.addRecipe(new ItemStack(Block.torchWood, 8),
                new Object[]{"#", "S", '#', BurpTechCore.configuration.items.netherCoal, 'S', Item.stick});

    }

    public void postInitialization()
    {
        // add fuel handler
        if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true))
            GameRegistry.registerFuelHandler(new NetherTechSolidFuelHandler());

        if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean(true))
            GameRegistry.registerFuelHandler(new NetherTechLiquidFuelHandler());

        // vanilla recipes for nether coal
        if (BurpTechCore.configuration.enableNetherTechVanillaRecipes.getBoolean(true))
        {
            // add vanilla recipe for nether coal if no mods are present to support it
            GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal),
                    new Object[] { "###", "#C#", "###", '#', new ItemStack(Block.netherrack), 'C', new ItemStack(Item.coal, 1, 1) });
        }

        // ic2 recipes for nether dust
        if (Loader.isModLoaded("IC2") && BurpTechCore.configuration.enableNetherTechIndustrialcraftRecipes.getBoolean(true))
        {
            if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean(true))
            {
                IndustrialcraftIntegration.addSemiFlueGeneratorFuel("nether", 10, 32);
                IndustrialcraftIntegration.addMaceratorRecipe(new ItemStack(Block.netherrack), BurpTechCore.configuration.items.netherDust.copy());
                IndustrialcraftIntegration.addEnrichmentRecipe(BurpTechCore.configuration.items.netherDust.copy(), FluidRegistry.getFluidStack("lava", FluidContainerRegistry.BUCKET_VOLUME), FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME));

                Item ic2Cell = GameRegistry.findItem("IC2", "itemCellEmpty");
                if (ic2Cell != null)
                {
                    FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(BurpTechCore.configuration.items.cellNetherFluid), new ItemStack(ic2Cell));
                }
            }

            if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true))
            {
                IndustrialcraftIntegration.addMaceratorRecipe(new ItemStack(Block.netherrack), BurpTechCore.configuration.items.netherDust.copy());
                ItemStack netherDust = BurpTechCore.configuration.items.netherDust.copy();
                netherDust.stackSize = 8;
                IndustrialcraftIntegration.addCompressorRecipe(netherDust, new ItemStack(BurpTechCore.configuration.items.netherCoal));
            }
        }

        if (Loader.isModLoaded("Railcraft") && BurpTechCore.configuration.enableNetherTechRailcraftRecipes.getBoolean(true))
        {
            if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true) && BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean(true))
            {
                RailcraftIntegration.addBoilerFuel(FluidRegistry.getFluid("nether"), 32000);
                RailcraftIntegration.addRockCrusherRecipe(new ItemStack(Block.netherrack), BurpTechCore.configuration.items.netherDust.copy(), null, 0);
                RailcraftIntegration.addBlastFurnaceRecipe(BurpTechCore.configuration.items.netherDust.copy(), true, false, 600, BurpTechCore.configuration.items.infusedNetherDust.copy());
                RailcraftIntegration.addCokeOvenRecipe(BurpTechCore.configuration.items.infusedNetherDust.copy(), true, false, new ItemStack(BurpTechCore.configuration.items.netherCoal), FluidRegistry.getFluidStack("nether", 250), 600);
            }

            if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true) && !BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean(true))
            {
                RailcraftIntegration.addRockCrusherRecipe(new ItemStack(Block.netherrack), BurpTechCore.configuration.items.netherDust.copy(), null, 0);
                RailcraftIntegration.addBlastFurnaceRecipe(BurpTechCore.configuration.items.netherDust.copy(), true, false, 600, new ItemStack(BurpTechCore.configuration.items.netherCoal));
            }

            if (!BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true) && BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean(true))
            {
                RailcraftIntegration.addBoilerFuel(FluidRegistry.getFluid("nether"), 32000);
                RailcraftIntegration.addRockCrusherRecipe(new ItemStack(Block.netherrack), BurpTechCore.configuration.items.netherDust.copy(), null, 0);
                RailcraftIntegration.addCokeOvenRecipe(BurpTechCore.configuration.items.netherDust.copy(), true, false, BurpTechCore.configuration.items.tinyCharcoalDust.copy(), FluidRegistry.getFluidStack("nether", 250), 600);
            }
        }

        if (Loader.isModLoaded("BuildCraft|Silicon") && BurpTechCore.configuration.enableNetherTechBuildcraftRecipes.getBoolean(true))
        {
            if (BurpTechCore.configuration.enableNetherTechLiquidFuels.getBoolean(true))
                BuildcraftIntegration.addEngineFuel(FluidRegistry.getFluid("nether"), 5, 20000);

            if (BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true))
                BuildcraftIntegration.addFacade(BurpTechCore.configuration.blocks.blockNetherCoal.blockID, 0);
        }
    }
}

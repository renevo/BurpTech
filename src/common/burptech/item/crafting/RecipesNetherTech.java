package burptech.item.crafting;

import java.util.ArrayList;

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

        Integration.addFluidEnrichment(new ItemStack(BurpTechCore.configuration.items.netherDust), FluidRegistry.getFluidStack("lava", FluidContainerRegistry.BUCKET_VOLUME), FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME));

        Integration.addFuel("nether", 10, 32, 32000, 5, 20000);

        GameRegistry.registerFuelHandler(new NetherTechLiquidFuelHandler());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void addSolidFuels()
    {
    	if (!BurpTechCore.configuration.enableNetherTechSolidFuels.getBoolean(true))
    		return;
    	
		if (!Integration.addCrushableItem(new ItemStack(Block.netherrack), new ItemStack(BurpTechCore.configuration.items.netherDust)))
		{
			// add vanilla recipe for nether coal if no mods are present to support it
			GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal), 
					new Object[] { "###", "#C#", "###", '#', new ItemStack(Block.netherrack), 'C', new ItemStack(Item.coal, 1, 1) });
		} else {
			// find out if we have charcoal dust, if so, use it for our recipe
			ArrayList<ItemStack> charcoals = OreDictionary.getOres("dustCharcoal");

            if (!Integration.addCompressedItem(new ItemStack(BurpTechCore.configuration.items.infusedNetherDust), new ItemStack(BurpTechCore.configuration.items.netherCoal)))
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal),
                        new Object[] {"###", "#C#", "###", '#', "dustNetherrack", 'C', charcoals.size() == 0 ? new ItemStack(Item.coal, 1, 1) : "dustCharcoal" }));

                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal),
                        new Object[] {"###", "#C#", "###", '#', "itemDustNetherrack", 'C', charcoals.size() == 0 ? new ItemStack(Item.coal, 1, 1) : "dustCharcoal" }));
            } else
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BurpTechCore.configuration.items.infusedNetherDust),
                        new Object[] {"###", "#C#", "###", '#', "dustNetherrack", 'C', charcoals.size() == 0 ? new ItemStack(Item.coal, 1, 1) : "dustCharcoal" }));

                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BurpTechCore.configuration.items.infusedNetherDust),
                        new Object[] {"###", "#C#", "###", '#', "itemDustNetherrack", 'C', charcoals.size() == 0 ? new ItemStack(Item.coal, 1, 1) : "dustCharcoal" }));
            }
		}
		
		// nether coal block
		GameRegistry.addRecipe(new ItemStack(BurpTechCore.configuration.blocks.blockNetherCoal),
				new Object[] { "###", "###", "###", '#', BurpTechCore.configuration.items.netherCoal });

        // reverse nether coal block recipe
		GameRegistry.addShapelessRecipe(new ItemStack(BurpTechCore.configuration.items.netherCoal, 9), BurpTechCore.configuration.blocks.blockNetherCoal);

        // torches
        GameRegistry.addRecipe(new ItemStack(Block.torchWood, 8),
                new Object[]{"#", "S", '#', BurpTechCore.configuration.items.netherCoal, 'S', Item.stick});

        GameRegistry.registerFuelHandler(new NetherTechSolidFuelHandler());
    }

    public void postInitialization()
    {
        Item ic2Cell = GameRegistry.findItem("IC2", "itemCellEmpty");
        if (ic2Cell != null)
        {
            FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(BurpTechCore.configuration.items.cellNetherFluid), new ItemStack(ic2Cell));
        }

        Item forestryCell = GameRegistry.findItem("Forestry", "item.canEmpty");
        if (forestryCell != null)
        {
            // TODO: After refactoring the cell item to be a damage based multi-item, add support for this one
            // FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("nether", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(BurpTechCore.configuration.items.canNetherFluid), new ItemStack(forestryCell));
        }
    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)  // TODO: Move this, this is so that the fluids render properly in tanks and things
    public void textureHook(TextureStitchEvent.Post event) {
        if (event.map.textureType == 0) {
            BurpTechCore.configuration.blocks.fluidNetherFluid.setIcons(BurpTechCore.configuration.blocks.blockNetherFluid.getBlockTextureFromSide(1), BurpTechCore.configuration.blocks.blockNetherFluid.getBlockTextureFromSide(2));
        }
    }


}

package burptech.item.crafting;

import burptech.BurpTechCore;
import burptech.integration.IndustrialcraftIntegration;
import burptech.integration.Integration;
import burptech.integration.RailcraftIntegration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.*;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.Iterator;

public class RecipesIntegration {

    public void postInitialization()
    {
        if (BurpTechCore.configuration.enableCreosoteToIndustrialcraftEnergy.getBoolean(true))
        {
            if (Loader.isModLoaded("Railcraft") && Loader.isModLoaded("IC2"))
            {
                Fluid creosote = FluidRegistry.getFluid("creosote");
                if (creosote != null)
                {
                    IndustrialcraftIntegration.addSemiFlueGeneratorFuel("creosote", 66, 10);
                    BurpTechCore.log.info("Added Railcraft Creosote to Industrialcraft Semi-Fluid Generator");
                }
            }
        }

        if (BurpTechCore.configuration.enableSaplingCokeOvenProcessing.getBoolean(true))
        {
            if (Loader.isModLoaded("Railcraft"))
            {
                Fluid creosote = FluidRegistry.getFluid("creosote");
                if (creosote != null)
                {
                    // get all saplings from ore dictionary
                    ArrayList<ItemStack> saplings = OreDictionary.getOres("treeSapling");
                    saplings.addAll(OreDictionary.getOres("saplingTree"));

                    for (Iterator<ItemStack> i = saplings.iterator(); i.hasNext();)
                    {
                        ItemStack item = i.next().copy();

                        // 16x saplings = 1 charcoal and a bit of creosote
                        RailcraftIntegration.addCokeOvenRecipe(item, true, true, BurpTechCore.configuration.items.tinyCharcoalDust.copy(), FluidRegistry.getFluidStack("creosote", 30), 200);
                    }
                }
            }
        }
        if (BurpTechCore.configuration.enableStoneDustCompression.getBoolean(true))
        {
            if (Loader.isModLoaded("IC2"))
            {
                ItemStack stoneDust = IndustrialcraftIntegration.getItem("stoneDust");
                if (stoneDust != null)
                {
                    stoneDust.stackSize = 8;
                    IndustrialcraftIntegration.addCompressorRecipe(stoneDust, new ItemStack(net.minecraft.block.Block.stone));
                    BurpTechCore.log.info("Added Compressor Recipe for IC2 Stone Dust to Stone");
                }
            }
        }

        if (BurpTechCore.configuration.enableCompressedPlantBallEnrichment.getBoolean(true))
        {
            if (Loader.isModLoaded("IC2") && FluidRegistry.isFluidRegistered("biomass"))
            {
                ItemStack compressedPlantBall = IndustrialcraftIntegration.getItem("compressedPlantBall");
                if (compressedPlantBall != null)
                {
                    // water- produces 1.0
                    IndustrialcraftIntegration.addEnrichmentRecipe(compressedPlantBall, FluidRegistry.getFluidStack(FluidRegistry.WATER.getName(), 250), FluidRegistry.getFluidStack("biomass", 250));
                    BurpTechCore.log.info("Added Enrichment Recipe for Water + Compressed Plant Ball = Biomass");

                    // juice - produces 1.5
                    if (FluidRegistry.isFluidRegistered("juice"))
                    {
                        IndustrialcraftIntegration.addEnrichmentRecipe(compressedPlantBall, FluidRegistry.getFluidStack("juice", 250), FluidRegistry.getFluidStack("biomass", 375));
                        BurpTechCore.log.info("Added Enrichment Recipe for Juice + Compressed Plant Ball = Biomass");
                    }

                    // honey - produces 1.5
                    if (FluidRegistry.isFluidRegistered("honey"))
                    {
                        IndustrialcraftIntegration.addEnrichmentRecipe(compressedPlantBall, FluidRegistry.getFluidStack("honey", 250), FluidRegistry.getFluidStack("biomass", 375));
                        BurpTechCore.log.info("Added Enrichment Recipe for Honey + Compressed Plant Ball = Biomass");
                    }
                }
            }
        }

        if (BurpTechCore.configuration.enableVanillaOreDoubling.getBoolean(false))
        {
            // iron
            ItemStack ironDust = BurpTechCore.configuration.items.ironDust.copy();
            ironDust.stackSize = 2;

            if (Integration.addCrushableItem(new ItemStack(Block.oreIron), ironDust))
                FurnaceRecipes.smelting().addSmelting(ironDust.itemID, ironDust.getItemDamage(), new ItemStack(Item.ingotIron), 0.7F);

            // gold
            ItemStack goldDust = BurpTechCore.configuration.items.goldDust.copy();
            goldDust.stackSize = 2;

            if (Integration.addCrushableItem(new ItemStack(Block.oreGold), goldDust))
                FurnaceRecipes.smelting().addSmelting(goldDust.itemID, goldDust.getItemDamage(), new ItemStack(Item.ingotGold), 1F);
        }

        if (Loader.isModLoaded("Railcraft"))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("dustCharcoal").get(0),
                    new Object[] {"###", "###", "###", '#', BurpTechCore.configuration.items.tinyCharcoalDust.copy() }));
        }
        else
        {
            GameRegistry.addRecipe(new ItemStack(Item.coal, 1, 1),
                    new Object[] {"###", "###", "###", '#', BurpTechCore.configuration.items.tinyCharcoalDust.copy() });
        }
    }
}

package burptech.item.crafting;

import burptech.BurpTechCore;
import burptech.integration.IndustrialcraftIntegration;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.*;

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
    }
}

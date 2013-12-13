package burptech.item.crafting;

import burptech.BurpTechCore;
import burptech.integration.IndustrialcraftIntegration;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

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
    }
}

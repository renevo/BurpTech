package burptech.item.crafting;

import burptech.BurpTechCore;
import burptech.integration.IndustrialcraftIntegration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class RecipesIntegration {

    public void postInitialization()
    {
        if (BurpTechCore.configuration.enableCreosoteToIndustrialcraftEnergy.getBoolean(true))
        {
            Fluid creosote = FluidRegistry.getFluid("creosote");
            if (creosote != null)
            {
                IndustrialcraftIntegration.addSemiFlueGeneratorFuel("creosote", 66, 10);
            }
        }
    }
}

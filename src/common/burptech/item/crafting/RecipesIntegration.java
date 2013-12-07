package burptech.item.crafting;

import burptech.BurpTechCore;
import burptech.integration.IndustrialcraftIntegration;
import burptech.integration.Integration;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
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

        if (BurpTechCore.configuration.enableStoneDustToStoneSmelting.getBoolean(true))
        {
            ItemStack stoneDust = IndustrialcraftIntegration.getItem("stoneDust");
            if (stoneDust != null)
            {
                FurnaceRecipes.smelting().addSmelting(stoneDust.itemID, stoneDust.getItemDamage(), new ItemStack(net.minecraft.block.Block.stone), 0);
                BurpTechCore.log.info("Added Smelting Recipe for IC2 Stone Dust to Stone");

            }
        }
    }
}

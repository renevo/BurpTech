package burptech.client;

import burptech.BurpTechCore;
import burptech.CommonProxy;
import burptech.client.gui.GuiPortableWorkbench;
import burptech.integration.NeiIntegration;
import burptech.lib.VersionChecker;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

public class ClientProxy extends CommonProxy
{
    @Override
    public void postInitialization()
    {
         addNeiSupport();

        MinecraftForge.EVENT_BUS.register(this);

        if (BurpTechCore.configuration.enableCheckForUpdates.getBoolean(true))
            TickRegistry.registerTickHandler(new VersionChecker(), Side.CLIENT);
    }

	private void addNeiSupport()
	{
		if (BurpTechCore.configuration.recipePortableWorkbench.getBoolean(true))
    	{
    		NeiIntegration.registerCraftingContainers(GuiPortableWorkbench.class);
    	}

    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Post event)
    {
        if (event.map.textureType != 0)
            return;

        if (BurpTechCore.configuration.blocks.fluidNetherFluid != null && BurpTechCore.configuration.blocks.blockNetherFluid != null)
        {
            BurpTechCore.configuration.blocks.fluidNetherFluid.setIcons(BurpTechCore.configuration.blocks.blockNetherFluid.getBlockTextureFromSide(1), BurpTechCore.configuration.blocks.blockNetherFluid.getBlockTextureFromSide(2));
        }
    }
}

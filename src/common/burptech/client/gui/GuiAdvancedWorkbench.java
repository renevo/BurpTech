package burptech.client.gui;

import burptech.BurpTechCore;
import burptech.block.Blocks;
import burptech.gui.ContainerAdvancedWorkbench;
import burptech.lib.Constants;
import burptech.tileentity.TileEntityAdvancedWorkbench;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiAdvancedWorkbench extends GuiContainer
{
    private static final ResourceLocation resourceLocation = new ResourceLocation(Constants.MOD_ID + ":textures/gui/advancedworkbench.png");
    private TileEntityAdvancedWorkbench tileEntityAdvancedWorkbench;

    public GuiAdvancedWorkbench(TileEntityAdvancedWorkbench tileEntity)
    {
        super(new ContainerAdvancedWorkbench(tileEntity));
        this.tileEntityAdvancedWorkbench = tileEntity;

        ySize = 232;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        if (!tileEntityAdvancedWorkbench.isInvNameLocalized())
            this.fontRenderer.drawString(StatCollector.translateToLocal(BurpTechCore.configuration.blocks.blockAdvancedWorkbench.getUnlocalizedName() + ".name"), 8, 6, 4210752);
        else
            this.fontRenderer.drawString(tileEntityAdvancedWorkbench.getInvName(), 8, 6, 4210752);

        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, 140, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(resourceLocation);

        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
    }
}

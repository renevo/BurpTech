package burptech.client.gui;

import burptech.BurpTechCore;
import burptech.gui.ContainerAdvancedWorkbench;
import burptech.lib.Constants;
import burptech.tileentity.TileEntityAdvancedWorkbench;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

// TODO: Currently the recipe still needs to be manually put in, need to figure out NEI support proper for this one
public class GuiAdvancedWorkbench extends GuiContainer
{
    private static final ResourceLocation resourceLocation = new ResourceLocation(Constants.MOD_ID + ":textures/gui/advancedworkbench.png");
    private TileEntityAdvancedWorkbench workbench;
    private ContainerAdvancedWorkbench container;

    public GuiAdvancedWorkbench(InventoryPlayer inventoryPlayer, TileEntityAdvancedWorkbench tileEntity)
    {
        super(new ContainerAdvancedWorkbench(inventoryPlayer, tileEntity));
        this.container = (ContainerAdvancedWorkbench)inventorySlots;
        this.workbench = tileEntity;

        ySize = 232;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        if (!workbench.isInvNameLocalized())
            this.fontRenderer.drawString(StatCollector.translateToLocal(BurpTechCore.configuration.blocks.blockAdvancedWorkbench.getUnlocalizedName() + ".name"), 8, 6, 4210752);
        else
            this.fontRenderer.drawString(workbench.getInvName(), 8, 6, 4210752);

        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, 140, 4210752);
        drawBackgroundOnMissingItems(0, 0, false);
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

    private void drawBackgroundOnMissingItems(int xOffset, int yOffset, boolean overItem)
    {
        //draw the missing items
        boolean[] itemMissing = new boolean[9];
        ItemStack[] recipe = new ItemStack[9];

        for(int y = 27; y < 36; ++y)
        {
            ItemStack recipeItem = workbench.getStackInSlot(y);

            if (recipeItem != null)
            {
                for (int x = 0; x < 9; ++x)
                {
                    if (recipe[x] != null && recipe[x].isItemEqual(recipeItem))
                    {
                        recipe[x].stackSize++;
                        break;
                    }

                    if (recipe[x] == null)
                    {
                        recipe[x] = recipeItem.copy();
                        recipe[x].stackSize = 1;
                        break;
                    }
                }
            }
        }

        for (int y = 0; y < 9; ++y)
            for (int x = 0; x < 9; x++)
            {
                ItemStack slotItem = workbench.getStackInSlot(y + 27);
                if (recipe[x] != null && slotItem != null && recipe[x].isItemEqual(slotItem))
                    itemMissing[y] = (!workbench.hasItemInWorkbench(recipe[x]));
            }

        int a = 64;
        int r = 255;
        int g = 0;
        int b = 0;

        int color = a << 24;
        color += r << 16;
        color += g << 8;
        color += b;

        for (int y = 0; y < 3; ++y)
        {
            for (int x = 0; x < 3; ++x)
            {
                if (itemMissing[y * 3 + x])
                {
                    if (overItem)
                    {
                        GL11.glDisable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_DEPTH_TEST);
                    }
                    drawGradientRect(xOffset + 30 + x * 18, yOffset + 17 + y * 18, xOffset + 46 + x * 18, yOffset + 33 + y * 18, color, color);
                    if (overItem)
                    {
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glEnable(GL11.GL_DEPTH_TEST);
                    }
                }
            }
        }
    }
}

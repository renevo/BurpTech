package burptech.gui;


import burptech.tileentity.TileEntityAdvancedWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;

public class ContainerAdvancedWorkbench extends Container
{
    private TileEntityAdvancedWorkbench tileEntityAdvancedWorkbench;

    public ContainerAdvancedWorkbench(TileEntityAdvancedWorkbench tileEntity)
    {
        tileEntityAdvancedWorkbench = tileEntity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        if (tileEntityAdvancedWorkbench == null)
            return false;

        return tileEntityAdvancedWorkbench.isUseableByPlayer(player);
    }
}

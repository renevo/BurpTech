package burptech.gui;

import cpw.mods.fml.relauncher.*;
import invtweaks.api.container.*;
import burptech.item.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

import java.util.*;

@ChestContainer(rowSize=9, isLargeChest=false) /** inventory tweaks support **/
public class ContainerRucksack extends Container
{
	private int numRows;
    private IInventory container;
    @SideOnly(Side.CLIENT)
    private java.util.Map<ContainerSection, List<Slot>> slotMap;

    public ContainerRucksack(IInventory playerIInventory, IInventory container)
    {
    	this.container = container;
        this.numRows = container.getSizeInventory() / 9;
        int i;
        int j;

        for (i = 0; i < this.numRows; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(container, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerIInventory, j + i * 9 + 9, 8 + j * 18, 49 + i * 18 + (numRows - 1) * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerIInventory, i, 8 + i * 18, 107 + (numRows - 1) * 18));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
    	return player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRucksack ? true : false;
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int par1)
    {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack())
        {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (container instanceof RucksackInventory & var2.getItem() instanceof ItemRucksack)
            	return null;
            
            if (par1 < this.numRows * 9)
            {
                if (!this.mergeItemStack(var4, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var4, 0, this.numRows * 9, false))
            {
                return null;
            }

            if (var4.stackSize == 0)
            {
                var3.putStack((ItemStack)null);
            }
            else
            {
                var3.onSlotChanged();
            }
        }

        return var2;
    }

    @Override
    public ItemStack slotClick(int var1, int var2, int var3, EntityPlayer player)
    {
    	ItemStack stack = player.inventory.getItemStack();

    	if (stack != null && stack.getItem() instanceof ItemRucksack && var1 < container.getSizeInventory() && container instanceof RucksackInventory)
    	{
    		return stack;
    	}

        return super.slotClick(var1, var2, var3, player);
    }
    
    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
    	if (!(container instanceof InventoryEnderChest))
    		((RucksackInventory) container).onGuiClose(par1EntityPlayer);
        super.onContainerClosed(par1EntityPlayer);
    }

    /*
     * Provided for advanced inventory tweaks support
     */
    @SideOnly(Side.CLIENT)
    @ContainerSectionCallback
    public java.util.Map<ContainerSection, List<Slot>> getSlotMap()
    {
        if (slotMap != null)
            return slotMap;

        slotMap = new EnumMap<ContainerSection, List<Slot>>(ContainerSection.class);

        // internal storage
        List<Slot> internalStorageSlots = new ArrayList<Slot>();
        for (int i = 0; i < 27; i++)
        {
            internalStorageSlots.add(getSlot(i));
        }
        slotMap.put(ContainerSection.CHEST, internalStorageSlots);

        // player inventory
        List<Slot> playerInventory = new ArrayList<Slot>();
        for (int i = 27; i < 55; i++)
        {
            playerInventory.add(getSlot(i));
        }
        slotMap.put(ContainerSection.INVENTORY_NOT_HOTBAR, playerInventory);

        // player hot bar
        List<Slot> playerHotBar = new ArrayList<Slot>();
        for (int i = 55; i < 63; i++)
        {
            playerHotBar.add(getSlot(i));
        }
        slotMap.put(ContainerSection.INVENTORY_HOTBAR, playerHotBar);

        return slotMap;
    }
}

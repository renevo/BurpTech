package burptech.gui;

import burptech.BurpTechCore;
import cpw.mods.fml.relauncher.*;
import invtweaks.api.container.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

import java.util.*;

@ChestContainer(rowSize=9, isLargeChest=false) /** inventory tweaks support **/
public class ContainerPortableWorkbench extends Container 
{

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    private World worldObj;

    @SideOnly(Side.CLIENT)
    private java.util.Map<ContainerSection, List<Slot>> slotMap;

    public ContainerPortableWorkbench(InventoryPlayer par1InventoryPlayer, World world)
    {
        this.worldObj = world;
        this.addSlotToContainer(new SlotCrafting(par1InventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 124, 35));
        int var6;
        int var7;

        for (var6 = 0; var6 < 3; ++var6)
        {
            for (var7 = 0; var7 < 3; ++var7)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, var7 + var6 * 3, 30 + var7 * 18, 17 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 3; ++var6)
        {
            for (var7 = 0; var7 < 9; ++var7)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 9; ++var6)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 142));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void onCraftMatrixChanged(IInventory par1IInventory)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);

        if (!this.worldObj.isRemote)
        {
            for (int var2 = 0; var2 < 9; ++var2)
            {
                ItemStack var3 = this.craftMatrix.getStackInSlotOnClosing(var2);

                if (var3 != null)
                {
                    par1EntityPlayer.dropPlayerItem(var3);
                }
            }
        }
    }
    
    public String getInvName()
    {
        return "container.crafting";
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
    	return player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == BurpTechCore.configuration.items.portableWorkbench.itemID ? true : false;
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.inventorySlots.get(slotIndex);

        if (var3 != null && var3.getHasStack())
        {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (slotIndex == 0)
            {
                if (!this.mergeItemStack(var4, 10, 46, true))
                {
                    return null;
                }

                var3.onSlotChange(var4, var2);
            }
            else if (slotIndex >= 10 && slotIndex < 37)
            {
                if (!this.mergeItemStack(var4, 1, 10, false))
                {
                    return null;
                }
            }
            else if (slotIndex >= 37 && slotIndex < 46)
            {
                if (!this.mergeItemStack(var4, 1, 10, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var4, 10, 46, false))
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

            if (var4.stackSize == var2.stackSize)
            {
                return null;
            }

            var3.onPickupFromSlot(player, var4);
        }

        return var2;
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

        // craft result 0
        // IGNORE

        // craft matrix 1-9
        // IGNORE

        // player inventory
        List<Slot> playerInventory = new ArrayList<Slot>();
        for (int i = 10; i < 37; i++)
        {
            playerInventory.add(getSlot(i));
        }
        slotMap.put(ContainerSection.INVENTORY_NOT_HOTBAR, playerInventory);

        // player hot bar
        List<Slot> playerHotBar = new ArrayList<Slot>();
        for (int i = 37; i < 46; i++)
        {
            playerHotBar.add(getSlot(i));
        }
        slotMap.put(ContainerSection.INVENTORY_HOTBAR, playerHotBar);

        return slotMap;
    }
}

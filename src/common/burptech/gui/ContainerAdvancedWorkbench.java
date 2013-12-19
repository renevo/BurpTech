package burptech.gui;

import burptech.tileentity.TileEntityAdvancedWorkbench;
import cpw.mods.fml.relauncher.*;
import invtweaks.api.container.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

import java.util.*;

@ChestContainer(rowSize=9, isLargeChest=false) /** inventory tweaks support **/
public class ContainerAdvancedWorkbench extends Container
{
    public TileEntityAdvancedWorkbench workbench;
    public IInventory inventory;

    @SideOnly(Side.CLIENT)
    private java.util.Map<ContainerSection, List<Slot>> slotMap;

    public ContainerAdvancedWorkbench(InventoryPlayer playerInventory, TileEntityAdvancedWorkbench tile)
    {
        workbench = tile;
        inventory = playerInventory;

        int i;
        int j;

        // internal storage (workbench 0-26 slots)
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(workbench, j + i * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // craft grid (workbench 27-35 slots)
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(tile, (j + i * 3) + 27, 30 + j * 18, 17 + i * 18));
            }
        }

        // craft result (slot 36)
        this.addSlotToContainer(new SlotAdvancedCrafting(playerInventory.player, tile, tile, 36, 124, 35));

        // player inventory (slot 37 - 64)
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 151 + i * 18));
            }
        }

        // player hot bar (slot 65 - 72)
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 209));
        }
    }

    @Override
    public ItemStack slotClick(int slotIndex, int button, int modifier, EntityPlayer player)
    {
        // need to do this both client/server side for prediction to work properly
        if (workbench.isCraftingGrid(slotIndex))
        {
            Slot craftMatrixSlot = this.getSlot(slotIndex);
            if (craftMatrixSlot != null)
            {
                ItemStack clickedItem = player.inventory.getItemStack();

                if (clickedItem != null)
                {
                    if (craftMatrixSlot.getStack() != null && craftMatrixSlot.getStack().isItemEqual(clickedItem))
                        craftMatrixSlot.putStack(null);
                    else
                    {
                        ItemStack copiedItem = clickedItem.copy();
                        copiedItem.setItemDamage(clickedItem.getItemDamage());
                        copiedItem.setTagCompound(clickedItem.getTagCompound());

                        craftMatrixSlot.putStack(copiedItem);
                    }
                }
                else
                    craftMatrixSlot.putStack(null);

                return null;
            }
        }

        // need to do this both client/server side for prediction to work properly
        if (workbench.isCraftingResult(slotIndex))
        {
            Slot craftOutputSlot = this.getSlot(slotIndex);
            if (craftOutputSlot != null)
            {
                ItemStack craftedItem = workbench.craft(false);

                if (craftedItem != null)
                {
                    // check for full equipped item
                    ItemStack currentlyEquipped = player.inventory.getItemStack();

                    if (currentlyEquipped != null && !currentlyEquipped.isItemEqual(craftedItem))
                        return null;

                    if (currentlyEquipped != null && currentlyEquipped.isItemEqual(craftedItem) && (currentlyEquipped.stackSize + craftedItem.stackSize) > currentlyEquipped.getMaxStackSize())
                        return null;

                    return super.slotClick(slotIndex, button, modifier, player);
                }
            }

            return null;
        }

        return super.slotClick(slotIndex, button, modifier, player);
    }

    @Override
    protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer)
    {
        this.slotClick(par1, par2, 1, par4EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack returnStack = null;
        Slot sourceSlot = (Slot)this.inventorySlots.get(slotIndex);

        if (sourceSlot != null && sourceSlot.getHasStack())
        {
            ItemStack sourceStack = sourceSlot.getStack();
            returnStack = sourceStack.copy();

            if (slotIndex == 36)
            {
                if (!this.mergeItemStack(sourceStack, 37, 73, true))
                {
                    return null;
                }

                // consume the items
                workbench.craft(true);

                sourceSlot.onSlotChange(sourceStack, returnStack);
            }
            else if (slotIndex < 36 && slotIndex > 26)
            {
                return null;
            }

            else if (slotIndex >= 0 && slotIndex < 27)
            {
                if (!this.mergeItemStack(sourceStack, 37, 73, false))
                {
                    return null;
                }
            }
            else if (slotIndex >= 37 && slotIndex < 73)
            {
                if (!this.mergeItemStack(sourceStack, 0, 27, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(sourceStack, 37, 73, false))
            {
                return null;
            }

            if (sourceStack.stackSize == 0)
            {
                sourceSlot.putStack(null);
            }
            else
            {
                sourceSlot.onSlotChanged();
            }

            if (sourceStack.stackSize == returnStack.stackSize)
            {
                return null;
            }

            sourceSlot.onPickupFromSlot(player, sourceStack);
        }

        return returnStack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        if (workbench == null)
            return false;

        return workbench.isUseableByPlayer(player);
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

        // internal storage (workbench 0-26 slots)
        List<Slot> internalStorageSlots = new ArrayList<Slot>();
        for (int i = 0; i < 27; i++)
        {
            internalStorageSlots.add(getSlot(i));
        }
        slotMap.put(ContainerSection.CHEST, internalStorageSlots);

        // craft grid (workbench 27-35 slots)
        // IGNORE

        // craft result (slot 36)
        // IGNORE

        // player inventory (slot 37 - 64)
        List<Slot> playerInventory = new ArrayList<Slot>();
        for (int i = 37; i < 65; i++)
        {
            playerInventory.add(getSlot(i));
        }
        slotMap.put(ContainerSection.INVENTORY_NOT_HOTBAR, playerInventory);

        // player hot bar (slot 65 - 72)
        List<Slot> playerHotBar = new ArrayList<Slot>();
        for (int i = 65; i < 73; i++)
        {
            playerHotBar.add(getSlot(i));
        }
        slotMap.put(ContainerSection.INVENTORY_HOTBAR, playerHotBar);

        return slotMap;
    }
}

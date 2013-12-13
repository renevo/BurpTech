package burptech.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityAdvancedWorkbench extends TileEntity implements IInventory
{
    private Random random = new Random();
    public ItemStack[] inventoryContents = new ItemStack[37];
    public String inventoryName;

    public static boolean isCraftingGrid(int slotIndex)
    {
        return slotIndex >= 27 && slotIndex < 36;
    }

    public static boolean isCraftingResult(int slotIndex)
    {
        return slotIndex == 36;
    }

    /**
     * Consumes the items for the current recipe
     */
    private void consumeCurrentRecipeItems()
    {
        if (inventoryContents[36] == null)
            return;

        for (int i = 27; i < 36; ++i)
        {
            consumeWorkbenchItem(inventoryContents[i]);
        }
    }

    /**
     * Consumes an item from the workbench
     */
    private void consumeWorkbenchItem(ItemStack item)
    {
        if (item == null)
            return;

        for (int i = 0; i < 27; ++i)
        {
            ItemStack inventoryItem = this.getStackInSlot(i);

            if (inventoryItem != null && inventoryItem.isItemEqual(item))
            {
                inventoryItem.stackSize--;

                if (inventoryItem.stackSize == 0)
                    setInventorySlotContents(i, null);
                else
                    setInventorySlotContents(i, inventoryItem);

                if (inventoryItem.getItem().hasContainerItem())
                {
                    ItemStack containerItem = inventoryItem.getItem().getContainerItemStack(inventoryItem);
                    if (containerItem.isItemStackDamageable() && containerItem.getItemDamage() > containerItem.getMaxDamage())
                        containerItem = null;


                    if (containerItem != null)
                    {
                        containerItem = mergeItemStack(containerItem, 0, 27);

                        // can ONLY be done server side
                        if (containerItem != null && !worldObj.isRemote)
                        {
                            float xRandom = this.random.nextFloat() * 0.8F + 0.1F;
                            float yRandom = this.random.nextFloat() * 0.8F + 0.1F;
                            float zRandom = this.random.nextFloat() * 0.8F + 0.1F;

                            EntityItem spawnedEntity = new EntityItem(worldObj, (double)((float)this.xCoord + xRandom), (double)((float)this.yCoord + yRandom), (double)((float)this.zCoord + zRandom), new ItemStack(containerItem.itemID, containerItem.stackSize, containerItem.getItemDamage()));

                            if (containerItem.hasTagCompound())
                            {
                                spawnedEntity.getEntityItem().setTagCompound((NBTTagCompound)containerItem.getTagCompound().copy());
                            }

                            worldObj.spawnEntityInWorld(spawnedEntity);
                        }
                    }
                }

                return;
            }
        }
    }

    /**
     * Used to detect if the workbench has enough of the specified item in its inventory
     */
    public boolean hasItemInWorkbench(ItemStack item)
    {
        if (item == null)
            return true;

        int craftableQuantity = 0;

        // count how many they have in the storage
        for (int i = 0; i < 27; ++i)
        {
            ItemStack inventoryItem = this.getStackInSlot(i);

            if (inventoryItem != null && inventoryItem.isItemEqual(item))
                craftableQuantity += inventoryItem.stackSize;
        }

        return craftableQuantity >= item.stackSize;
    }

    /**
     * Used to find out if we can actually craft an item or not
     */
    public boolean canCraftItem()
    {
        if (this.getStackInSlot(36) == null)
            return false;

        ItemStack[] recipe = new ItemStack[9];

        for(int i = 27; i < 36; ++i)
        {
            ItemStack recipeItem = this.getStackInSlot(i);

            if (recipeItem != null)
            {
                for (int j = 0; j < 9; ++j)
                {
                    if (recipe[j] != null && recipe[j].isItemEqual(recipeItem))
                    {
                        recipe[j].stackSize++;
                        break;
                    }

                    if (recipe[j] == null)
                    {
                        recipe[j] = recipeItem.copy();
                        recipe[j].stackSize = 1;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 9; ++i)
            if (!hasItemInWorkbench(recipe[i]))
                return false;

        return true;
    }

    /**
     * Use this to set the display name of the inventory, used for when the item is renamed in an anvil
     */
    public void setInventoryName(String inventoryName)
    {
        this.inventoryName = inventoryName;
    }

    /**
     * Used to update the crafting result
     */
    private void onInventoryChanged(int slotIndex)
    {
        if (isCraftingGrid(slotIndex) || isCraftingResult(slotIndex))
        {
            inventoryContents[36] = getCraftingResult();
        }

        // this.onInventoryChanged(); // not sure if we need to call this or not?
    }

    public ItemStack craft(boolean consumeItems)
    {
        if (canCraftItem())
        {
            if (consumeItems)
                consumeCurrentRecipeItems();

            onInventoryChanged(36);
            return this.getStackInSlot(36).copy();
        }

        return null;
    }

    /**
     * Gets a crafting result for the inventory
     */
    private ItemStack getCraftingResult()
    {
        InventoryCrafting craftMatrix = new LocalInventoryCrafting();
        for (int i = 27; i < 36; ++i)
        {
            ItemStack sourceStack = this.getStackInSlot(i);
            ItemStack outputStack = null;
            if (sourceStack != null)
            {
                outputStack = sourceStack.copy();
                outputStack.stackSize = 1;
            }

            craftMatrix.setInventorySlotContents(i - 27, outputStack);
        }

        return CraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj);
    }

    /**
     * Need to find out if there is some sort of helper method out there for this or not...
     */
    private ItemStack mergeItemStack(ItemStack itemStack, int startIndex, int endIndex)
    {
        for(int i = startIndex; i < endIndex; ++i)
        {
            ItemStack itemInSlot = getStackInSlot(i);
            if (itemInSlot == null)
            {
                setInventorySlotContents(i, itemStack);
                return null;
            }

            if (itemInSlot.isItemEqual(itemStack))
            {
                while (itemInSlot.stackSize <= itemInSlot.getMaxStackSize() && itemStack.stackSize > 0)
                {
                    itemInSlot.stackSize++;
                    itemStack.stackSize--;
                }

                if (itemStack.stackSize == 0)
                    return null;
            }
        }

        return itemStack;
    }

    @Override
    public int getSizeInventory()
    {
        return this.inventoryContents.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        if (slotIndex >= 37 || slotIndex < 0)
            return null;

        return inventoryContents[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        if (isCraftingResult(slotIndex))
        {
            return this.craft(true);
        }

        if (this.inventoryContents[slotIndex] != null)
        {
            ItemStack returnStack;

            if (this.inventoryContents[slotIndex].stackSize <= amount)
            {
                returnStack = this.inventoryContents[slotIndex];
                this.inventoryContents[slotIndex] = null;
                this.onInventoryChanged(slotIndex);
                return returnStack;
            }
            else
            {
                returnStack = this.inventoryContents[slotIndex].splitStack(amount);

                if (this.inventoryContents[slotIndex].stackSize == 0)
                {
                    this.inventoryContents[slotIndex] = null;
                }

                this.onInventoryChanged(slotIndex);
                return returnStack;
            }
        }

        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        if (isCraftingGrid(slotIndex))
        {
            return null;
        }

        if (this.inventoryContents[slotIndex] != null)
        {
            ItemStack itemstack = this.inventoryContents[slotIndex];
            this.inventoryContents[slotIndex] = null;
            return itemstack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        if (isCraftingGrid(slotIndex))
        {
            if (itemStack != null)
            {
                this.inventoryContents[slotIndex] = itemStack.copy();
                this.inventoryContents[slotIndex].stackSize = 0;
            }
            else
            {
                this.inventoryContents[slotIndex] = null;
            }

            this.onInventoryChanged(slotIndex);
            return;
        }

        if (isCraftingResult(slotIndex) && !worldObj.isRemote)
        {
            this.inventoryContents[36] = getCraftingResult(); // not sure why we are doing this like this, need to troubleshoot
            this.onInventoryChanged(slotIndex);
            return;
        }

        this.inventoryContents[slotIndex] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged(slotIndex);
    }

    @Override
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.inventoryName :  "container.advancedworkbench";
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return this.inventoryName != null && this.inventoryName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openChest()
    {
        // NOOP
    }

    @Override
    public void closeChest()
    {
        // NOOP
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return !isCraftingGrid(slotIndex) && !isCraftingResult(slotIndex);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("Items");
        this.inventoryContents = new ItemStack[this.getSizeInventory()];

        if (tagCompound.hasKey("CustomName"))
        {
            this.inventoryName = tagCompound.getString("CustomName");
        }

        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound slotTagCompound = (NBTTagCompound)tagList.tagAt(i);
            byte b0 = slotTagCompound.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventoryContents.length)
            {
                this.inventoryContents[b0] = ItemStack.loadItemStackFromNBT(slotTagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < this.inventoryContents.length; ++i)
        {
            if (this.inventoryContents[i] != null)
            {
                NBTTagCompound slotTagCompound = new NBTTagCompound();
                slotTagCompound.setByte("Slot", (byte) i);
                this.inventoryContents[i].writeToNBT(slotTagCompound);
                tagList.appendTag(slotTagCompound);
            }
        }

        tagCompound.setTag("Items", tagList);

        if (this.isInvNameLocalized())
        {
            tagCompound.setString("CustomName", this.inventoryName);
        }
    }

    /**
     * Internal local crafting grid helper
     */
    private class LocalInventoryCrafting extends InventoryCrafting
    {
        public LocalInventoryCrafting()
        {
            super(new Container() {
                @SuppressWarnings("all")
                public boolean isUsableByPlayer(EntityPlayer player)
                {
                    return false;
                }
                public boolean canInteractWith(EntityPlayer player)
                {
                    return false;
                }
            }, 3, 3);
        }
    }
}

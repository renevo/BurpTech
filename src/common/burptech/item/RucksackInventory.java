package burptech.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class RucksackInventory implements IInventory 
{
	private ItemStack[] inventoryContents = null;
	private int inventorySize = 0;
	private String inventoryName;
	public ItemStack stack;
	public boolean isLinkedWithEnderChest;

	public RucksackInventory(EntityPlayer player, ItemStack stack, int size, boolean isLinkedWithEnderChest)
	{
		inventoryContents = new ItemStack[size];
		inventorySize = size;
		this.stack = stack;
		this.isLinkedWithEnderChest = isLinkedWithEnderChest;

		if (!player.worldObj.isRemote)
		{
			if (stack.stackTagCompound == null)
			{
				stack.setTagCompound(new NBTTagCompound());
			}

			if (!stack.stackTagCompound.hasKey("items"))
			{
				stack.stackTagCompound.setTag("items", new NBTTagList("items"));
			}
			stack.stackTagCompound.setInteger("cid", (new Random()).nextInt());
			NBTTagList tagList = stack.stackTagCompound.getTagList("items");

			for (int i = 0; i < tagList.tagCount(); i++)
			{
				NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
				byte slotIndex = tagCompound.getByte("slot");

				if (slotIndex >= 0 && slotIndex < inventorySize)
				{
					inventoryContents[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
				}
			}
		}		
	}

	public void onGuiClose(EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
			if (stack.stackTagCompound == null)
			{
				stack.setTagCompound(new NBTTagCompound());
			}

			if (!stack.stackTagCompound.hasKey("items"))
			{
				stack.stackTagCompound.setTag("items", new NBTTagList("items"));
			}

			NBTTagList tagList = new NBTTagList();

			for (int i = 0; i < this.inventoryContents.length; ++i)
			{
				if (this.inventoryContents[i] != null)
				{
					NBTTagCompound tagCompound = new NBTTagCompound();
					tagCompound.setByte("slot", (byte)i);
					this.inventoryContents[i].writeToNBT(tagCompound);
					tagList.appendTag(tagCompound);
				}
			}
			stack.stackTagCompound.setTag("items", tagList);

			for (int i = -1; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack tempStack;

                if (i == -1)
                {
                    tempStack = player.inventory.getItemStack();
                }
                else
                {
                    tempStack = player.inventory.getStackInSlot(i);
                }

                if (tempStack != null)
                {
                    NBTTagCompound nbt = tempStack.getTagCompound();

                    if (nbt != null && stack.stackTagCompound.getInteger("cid") == nbt.getInteger("cid"))
                    {
                        this.stack.stackSize = 1;

                        if (i == -1)
                        {
                            player.inventory.setItemStack(this.stack);
                        }
                        else
                        {
                            player.inventory.setInventorySlotContents(i, this.stack);
                        }

                        break;
                    }
                }
            }
        }
	}

	public RucksackInventory setInventoryName(String name)
	{
		this.inventoryName = name;
		return this;
	}

	@Override
	public int getSizeInventory() 
	{
		return inventorySize;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) 
	{
		if (slotIndex > getSizeInventory() - 1) 
    	{
    		return null;
    	}
        return this.inventoryContents[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) 
	{
		if (this.inventoryContents[par1] != null)
        {
            ItemStack var3;

            if (this.inventoryContents[par1].stackSize <= par2)
            {
                var3 = this.inventoryContents[par1];
                this.inventoryContents[par1] = null;
                this.onInventoryChanged();
                return var3;
            }
            else
            {
                var3 = this.inventoryContents[par1].splitStack(par2);

                if (this.inventoryContents[par1].stackSize == 0)
                {
                    this.inventoryContents[par1] = null;
                }

                this.onInventoryChanged();
                return var3;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) 
	{
		if (this.inventoryContents[par1] != null)
        {
            ItemStack var2 = this.inventoryContents[par1];
            this.inventoryContents[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack stack) 
	{
		this.inventoryContents[par1] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
        	stack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
	}

	@Override
	public String getInvName() 
	{
		return inventoryName;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public void onInventoryChanged() 
	{

	}

	public boolean getShareTag()
    {
        return false;
    }

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return true;
	}

	@Override
	public void openChest() 
	{
	}

	@Override
	public void closeChest() 
	{
	}

	@Override
	public boolean isInvNameLocalized() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		// TODO Auto-generated method stub
		return false;
	}
}

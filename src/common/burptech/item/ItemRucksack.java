package burptech.item;

import burptech.BurpTechCore;
import burptech.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemRucksack extends Item
{
	private ItemStack[] inventoryContents;
	private int inventorySize;
	public boolean isLinkedWithEnderChest;
	private Icon overlay;

	protected ItemRucksack(int itemID, boolean isLinkedWithEnderChest) 
	{
		super(itemID);
		setMaxStackSize(1);
		this.inventorySize = 27;
		this.isLinkedWithEnderChest = isLinkedWithEnderChest;
		inventoryContents = new ItemStack[inventorySize];
	}

    @Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (world.isRemote)
		{
			return player.getCurrentEquippedItem();
		}
		else
		{
			player.openGui(BurpTechCore.instance, !isLinkedWithEnderChest ? Constants.GUI_RUCKSACK_ID : Constants.GUI_ENDER_RUCKSACK_ID, world, 0, 0, 0);
			return player.getCurrentEquippedItem();
		}
	}

    public boolean hasColor(ItemStack par1ItemStack)
    {
        return isLinkedWithEnderChest ? false : (!par1ItemStack.hasTagCompound() ? false : (!par1ItemStack.getTagCompound().hasKey("display") ? false : par1ItemStack.getTagCompound().getCompoundTag("display").hasKey("color")));
    }

    /**
     * Remove the color from the specified armor ItemStack.
     */
    public void removeColor(ItemStack par1ItemStack)
    {
        if (!isLinkedWithEnderChest)
        {
            NBTTagCompound var2 = par1ItemStack.getTagCompound();

            if (var2 != null)
            {
                NBTTagCompound var3 = var2.getCompoundTag("display");

                if (var3.hasKey("color"))
                {
                    var3.removeTag("color");
                }
            }
        }
    }
    
    /**
     * Return the color for the specified armor ItemStack.
     */
    public int getColor(ItemStack par1ItemStack)
    {
        if (isLinkedWithEnderChest)
        {
            return -1;
        }
        else
        {
            NBTTagCompound var2 = par1ItemStack.getTagCompound();

            if (var2 == null)
            {
                return 10511680;
            }
            else
            {
                NBTTagCompound var3 = var2.getCompoundTag("display");
                return var3 == null ? 10511680 : (var3.hasKey("color") ? var3.getInteger("color") : 10511680);
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        if (par2 > 0)
        {
            return 16777215;
        }
        else
        {
            int var3 = this.getColor(par1ItemStack);

            if (var3 < 0)
            {
                var3 = 16777215;
            }

            return var3;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses()
    {
        return !isLinkedWithEnderChest;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon(Constants.MOD_ID + ":" + this.getUnlocalizedName().replace("item.", ""));
    	if (!isLinkedWithEnderChest)
    	{
    		this.overlay = par1IconRegister.registerIcon(Constants.MOD_ID + ":" + this.getUnlocalizedName().replace("item.", "") + "_overlay");
    	}
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamageForRenderPass(int par1, int par2)
    {
        return par2 == 1 ? overlay : itemIcon;
    }
    
    public void setColor(ItemStack par1ItemStack, int par2)
    {
    	if (isLinkedWithEnderChest)
        {
            return;
        }
    	else
    	{
            NBTTagCompound var3 = par1ItemStack.getTagCompound();

            if (var3 == null)
            {
                var3 = new NBTTagCompound();
                par1ItemStack.setTagCompound(var3);
            }

            NBTTagCompound var4 = var3.getCompoundTag("display");

            if (!var3.hasKey("display"))
            {
                var3.setCompoundTag("display", var4);
            }

            var4.setInteger("color", par2);
    	}
    }
    
	public IInventory getInventory(EntityPlayer player, ItemStack stack)
	{
		return new RucksackInventory(player, stack, inventorySize, isLinkedWithEnderChest).setInventoryName(this.getUnlocalizedName());
	}
}

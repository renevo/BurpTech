package burptech.gui;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAdvancedCrafting extends Slot
{
    public IInventory craftMatrix;
    private EntityPlayer thePlayer;
    private int amountCrafted;

    public SlotAdvancedCrafting(EntityPlayer player, IInventory craftMatrix, IInventory inventory, int slotIndex, int slotX, int slotY)
    {
        super(inventory, slotIndex, slotX, slotY);

        this.thePlayer = player;
        this.craftMatrix = craftMatrix;
    }

    @Override
    public boolean isItemValid(ItemStack inputItemStack)
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int stackSize)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(stackSize, this.getStack().stackSize);
        }

        return super.decrStackSize(stackSize);
    }

    @Override
    public void putStack(ItemStack par1ItemStack)
    {
        // NO-OP, we don't want this to happen
    }

    @Override
    protected void onCrafting(ItemStack outputStack, int outputAmount)
    {
        if (outputStack == null)
            return;

        this.amountCrafted += outputAmount;
        this.onCrafting(outputStack);
    }

    @Override
    protected void onCrafting(ItemStack outputStack)
    {
        if (outputStack == null)
            return;

        outputStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack outputStack)
    {
        if (outputStack == null)
            return;

        GameRegistry.onItemCrafted(player, outputStack, craftMatrix);
        this.onCrafting(outputStack);
    }
}

package burptech.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.Facing;

public class TileEntityCobbleGenerator extends TileEntity
{
    private int tickCount = 0;

    @Override
    public void updateEntity()
    {
        if (this.worldObj == null || this.worldObj.isRemote)
            return;

        ++tickCount;

        if (tickCount % 20 != 0)
            return;

        tickCount = 0;

        boolean hasWater = hasMaterialAsNeighbor(Material.water);
        boolean hasLava = hasMaterialAsNeighbor(Material.lava);
        boolean hasRedstone = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);

        if (hasWater && hasLava && !hasRedstone)
        {
            ItemStack stack = new ItemStack(Block.cobblestone);

            IInventory inventory = getOutputInventory();

            if (inventory == null)
                return;

            TileEntityHopper.insertStack(inventory, stack, Facing.oppositeSide[getBlockMetadata()]);

            if (stack == null || stack.stackSize == 0)
                playSoundFX();
        }
    }

    protected void playSoundFX()
    {
        worldObj.playSoundEffect((double) ((float) xCoord + 0.5F), (double) ((float) yCoord + 0.5F), (double) ((float) zCoord + 0.5F), "random.fizz", 0.25F, 2.6F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.8F);
    }

    private boolean hasMaterialAsNeighbor(Material material)
    {
        Material locatedMaterial;

        locatedMaterial = worldObj.getBlockMaterial(xCoord - 1, yCoord, zCoord);
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlockMaterial(xCoord + 1, yCoord, zCoord);
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlockMaterial(xCoord, yCoord - 1, zCoord);
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlockMaterial(xCoord, yCoord + 1, zCoord);
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlockMaterial(xCoord, yCoord, zCoord - 1);
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlockMaterial(xCoord, yCoord, zCoord + 1);
        if (locatedMaterial == material)
            return true;


        return false;
    }

    private IInventory getOutputInventory()
    {
        int facing = getBlockMetadata();
        return TileEntityHopper.getInventoryAtLocation(this.getWorldObj(), (double) (this.xCoord + Facing.offsetsXForSide[facing]), (double) (this.yCoord + Facing.offsetsYForSide[facing]), (double) (this.zCoord + Facing.offsetsZForSide[facing]));
    }
}

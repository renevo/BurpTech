package burptech.block;

import burptech.BurpTechCore;
import burptech.lib.Constants;
import burptech.tileentity.TileEntityAdvancedWorkbench;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockAdvancedWorkbench extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon iconTop;
    @SideOnly(Side.CLIENT)
    private Icon iconBottom;
    @SideOnly(Side.CLIENT)
    private Icon iconFront;

    public BlockAdvancedWorkbench(int id)
    {
        super(id, Material.wood);
        setLightOpacity(0);
        setCreativeTab(CreativeTabs.tabRedstone);
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return side == 1 ? this.iconTop : (side == 0 ? this.iconBottom : (side != metadata ? this.blockIcon : this.iconFront));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(Constants.MOD_ID + ":advanced_workbench_side");
        this.iconFront = par1IconRegister.registerIcon(Constants.MOD_ID + ":advanced_workbench_front");
        this.iconTop = par1IconRegister.registerIcon(Constants.MOD_ID + ":advanced_workbench_top");
        this.iconBottom = par1IconRegister.registerIcon(Constants.MOD_ID + ":advanced_workbench_bottom");
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityAdvancedWorkbench();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
            return false;

        if (world.isRemote)
            return true;

        player.openGui(BurpTechCore.instance, Constants.GUI_ADVANCED_WORKBENCH_ID, world, x, y, z);

        return true;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        int facing = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (facing == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (facing == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (facing == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (facing == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemStack.hasDisplayName())
        {
            TileEntityAdvancedWorkbench tileEntity = (TileEntityAdvancedWorkbench)world.getBlockTileEntity(x, y, z);
            tileEntity.setInventoryName(itemStack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int blockId, int metadata)
    {
        TileEntityAdvancedWorkbench tileEntity = (TileEntityAdvancedWorkbench)world.getBlockTileEntity(x, y, z);

        if (tileEntity != null)
        {
            for (int i = 0; i < tileEntity.getSizeInventory(); ++i)
            {
                if (TileEntityAdvancedWorkbench.isCraftingGrid(i) || TileEntityAdvancedWorkbench.isCraftingResult(i))
                    continue;

                tileEntity.dropInWorld(world, tileEntity.getStackInSlot(i), x, y, z);
            }

            tileEntity.dropInWorld(world, tileEntity.itemOverflow, x, y, z);

            world.func_96440_m(x, y, z, blockId);
        }

        super.breakBlock(world, x, y, z, blockId, metadata);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            int l = world.getBlockId(x, y, z - 1);
            int i1 = world.getBlockId(x, y, z + 1);
            int j1 = world.getBlockId(x - 1, y, z);
            int k1 = world.getBlockId(x + 1, y, z);
            byte front = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                front = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                front = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                front = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                front = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, front, 2);
        }
    }
}

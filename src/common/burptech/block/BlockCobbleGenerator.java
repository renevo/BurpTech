package burptech.block;

import burptech.tileentity.TileEntityCobbleGenerator;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockCobbleGenerator extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon iconFront;

    public BlockCobbleGenerator(int blockId)
    {
        super(blockId, Material.rock);
        setLightOpacity(0);
        setCreativeTab(CreativeTabs.tabRedstone);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityCobbleGenerator();
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return side != (metadata & 7) ? this.blockIcon : this.iconFront;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("piston_bottom");
        this.iconFront = par1IconRegister.registerIcon("piston_inner");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        int facing = determineOrientation(x, y, z, entityLivingBase);
        world.setBlockMetadataWithNotify(x, y, z, facing, 2);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        //this.setDefaultDirection(world, x, y, z);
    }

    public static ForgeDirection getOrientation(int metadata)
    {
        return ForgeDirection.getOrientation(metadata & 7);
    }

    public static int determineOrientation(int x, int y, int z, EntityLivingBase entity)
    {
        if (MathHelper.abs((float)entity.posX - (float)x) < 2.0F && MathHelper.abs((float)entity.posZ - (float)z) < 2.0F)
        {
            double d0 = entity.posY + 1.82D - (double)entity.yOffset;

            if (d0 - (double)y > 2.0D)
            {
                return 1;
            }

            if ((double)y - d0 > 0.0D)
            {
                return 0;
            }
        }

        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
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

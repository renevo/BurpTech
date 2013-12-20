package burptech.item;

import burptech.lib.Constants;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

/**
 * Pulled from ProjectRed - https://github.com/MrTJP/ProjectRed/blob/master/common/mrtjp/projectred/exploration/ItemGemSickle.java
 */
public class ItemSickle extends ItemTool
{
    private int radiusCrops = 2;
    private int radiusLeaves = 1;
    private static int baseDamage = 3;

    public ItemSickle(int itemId, EnumToolMaterial material)
    {
        super(itemId, baseDamage, material, new Block[0]);
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(Constants.MOD_ID + ":" + this.getUnlocalizedName().replace("item.", ""));
    }

    @Override
    public float getStrVsBlock(ItemStack tool, Block block)
    {
        if (block instanceof BlockLeaves)
            return this.efficiencyOnProperMaterial;

        return super.getStrVsBlock(tool, block);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, int blockID, int x, int y, int z, EntityLivingBase entity)
    {
        EntityPlayer player;

        if (entity instanceof EntityPlayer)
            player = (EntityPlayer) entity;
        else
            return false;

        Block b = Block.blocksList[blockID];
        if (b != null)
        {
            if (b.isLeaves(world, x, y, z))
                return recurseLeaves(stack, world, x, y, z, player);

            if (b instanceof BlockFlower)
                return recurseCrops(stack, world, x, y, z, player);
        }
        return super.onBlockDestroyed(stack, world, blockID, x, y, z, entity);
    }

    public boolean recurseLeaves(ItemStack stack, World w, int x, int y, int z, EntityPlayer player)
    {
        boolean used = false;
        for (int i = -radiusLeaves; i <= radiusLeaves; i++)
            for (int j = -radiusLeaves; j <= radiusLeaves; j++)
                for (int k = -radiusLeaves; k <= radiusLeaves; k++)
                {
                    int localX = x + i;
                    int localY = y + j;
                    int localZ = z + k;
                    int id = w.getBlockId(localX, localY, localZ);
                    int meta = w.getBlockMetadata(localX, localY, localZ);
                    Block localBlock = Block.blocksList[id];
                    if (localBlock != null && (localBlock.isLeaves(w, localX, localY, localZ) || localBlock instanceof BlockLeaves))
                    {
                        if (localBlock.canHarvestBlock(player, meta))
                            localBlock.harvestBlock(w, player, localX, localY, localZ, meta);
                        w.setBlock(localX, localY, localZ, 0, 0, 3);
                        used = true;
                    }
                }
        if (used)
            stack.damageItem(1, player);

        return used;
    }

    public boolean recurseCrops(ItemStack stack, World w, int x, int y, int z, EntityPlayer player)
    {
        boolean used = false;
        for (int i = -radiusCrops; i <= radiusCrops; i++)
            for (int j = -radiusCrops; j <= radiusCrops; j++)
            {
                int localX = x + i;
                int localY = y;
                int localZ = z + j;
                int id = w.getBlockId(localX, localY, localZ);
                int meta = w.getBlockMetadata(localX, localY, localZ);
                Block localBlock = Block.blocksList[id];
                if (localBlock != null && (localBlock instanceof BlockFlower || localBlock instanceof IPlantable))
                {
                    if (localBlock.canHarvestBlock(player, meta))
                        localBlock.harvestBlock(w, player, localX, localY, localZ, meta);
                    w.setBlock(localX, localY, localZ, 0, 0, 3);
                    used = true;
                }
            }
        if (used)
            stack.damageItem(1, player);

        return used;
    }
}

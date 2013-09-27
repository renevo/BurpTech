package burptech.block;

import burptech.lib.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

public class BlockOres extends Block
{
    public static final String[] ORES = {"oreCopper", "oreTin", "oreSilver", "oreLead", "oreNickel"};
    public static final String[] INGOTS = {"ingotCopper", "ingotTin", "ingotSilver", "ingotLead", "ingotNickel"};

    private Icon[] icons = new Icon[5];

    public BlockOres(int id)
    {
        super(id, Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        for (int i = 0; i < ORES.length; i++)
        {
            icons[i] = iconRegister.registerIcon(Constants.MOD_ID + ":" + ORES[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int metadata)
    {
        return icons[metadata];
    }

    @Override
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(int itemId, CreativeTabs tab, List list)
    {
        for (int i = 0; i < ORES.length; i++)
        {
            list.add(new ItemStack(itemId, 1, i));
        }
    }
}

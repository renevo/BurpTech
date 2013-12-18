package burptech.item;

import burptech.lib.Constants;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.util.Icon;

import java.util.List;


public class ItemDust extends Item
{
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public static final String[] dusts = {"dustNetherrack", "dustIron", "dustGold", "dustInfusedNetherrack", "dustTinyCharcoal"};

    public ItemDust(int itemId)
    {
        super(itemId);
        setHasSubtypes(true);
        setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setTextureName(Constants.MOD_ID + ":genericDust");
        this.setUnlocalizedName("genericDust");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int damage)
    {
        if (damage >= 0 && damage < dusts.length && icons != null)
            return icons[damage];

        return super.getIconFromDamage(damage);
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int damage = par1ItemStack.getItemDamage();
        if (damage >= 0 && damage < dusts.length)
            return "item." + dusts[damage];

        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int itemId, CreativeTabs creativeTab, List itemList)
    {
        for (int i = 0; i < dusts.length; i++)
        {
            itemList.add(new ItemStack(itemId, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);

        icons = new Icon[dusts.length];

        for (int i = 0; i < dusts.length; i++)
        {
            icons[i] = par1IconRegister.registerIcon(Constants.MOD_ID + ":" + dusts[i]);
        }
    }
}

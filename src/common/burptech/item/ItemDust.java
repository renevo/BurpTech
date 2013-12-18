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
    private Icon[] icons = new Icon[4];

    public static final String[] dusts = {"dustNetherrack", "dustIron", "dustGold", "dustInfusedNetherrack"};

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
        if (damage >= 0 && damage < dusts.length)
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
        itemList.add(new ItemStack(itemId, 1, 0));
        itemList.add(new ItemStack(itemId, 1, 1));
        itemList.add(new ItemStack(itemId, 1, 2));
        itemList.add(new ItemStack(itemId, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);

        icons[0] = par1IconRegister.registerIcon(Constants.MOD_ID + ":" + dusts[0]);
        icons[1] = par1IconRegister.registerIcon(Constants.MOD_ID + ":" + dusts[1]);
        icons[2] = par1IconRegister.registerIcon(Constants.MOD_ID + ":" + dusts[2]);
        icons[3] = par1IconRegister.registerIcon(Constants.MOD_ID + ":" + dusts[3]);
    }
}

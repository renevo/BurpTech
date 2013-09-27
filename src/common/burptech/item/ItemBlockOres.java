package burptech.item;

import burptech.block.BlockOres;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockOres extends ItemBlock
{
    public ItemBlockOres(int id)
    {
        super(id);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return getUnlocalizedName() + "." + BlockOres.ORES[itemStack.getItemDamage()];
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }
}

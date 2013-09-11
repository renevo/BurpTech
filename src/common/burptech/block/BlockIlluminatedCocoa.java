package burptech.block;

import burptech.BurpTechCore;
import net.minecraft.block.BlockCocoa;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockIlluminatedCocoa extends BlockCocoa
{
	public BlockIlluminatedCocoa(int blockId, boolean illuminated)
	{
		super(blockId);
		
		if (illuminated)
		{
			this.setLightValue(.9375F);
		} else 
		{
			this.setTickRandomly(true);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (blockID == BurpTechCore.configuration.blocks.illuminatedCocoaOn.blockID)
			return true;
		
		int meta = world.getBlockMetadata(x, y, z);
		
		ItemStack currentItem = player.getCurrentEquippedItem();
		
		if (currentItem == null)
			return true;
		
		if (currentItem.itemID == Item.glowstone.itemID && ((meta & 12) >> 2) == 2)
		{
			world.setBlock(x, y, z, BurpTechCore.configuration.blocks.illuminatedCocoaOn.blockID, meta, 1 | 2 | 4);
			if (!player.capabilities.isCreativeMode)
			{
				currentItem.stackSize--;
			}
		}
		
		return true;
	}
}

package burptech.entity.monster.tweaks;

import burptech.BurpTechCore;
import net.minecraft.block.Block;

/**
 * Enderman tweaks 
 */
public final class EntityEnderman 
{
	/**
	 * Will make Enderman only pickup and carry red and yellow flowers (anti-griefing)
	 */
	public static void EnableAntiGriefing()
	{
		BurpTechCore.Log.info("Enabling Enderman Anti Griefing");
		
		// TODO: Probably change this to a for each, instead of hard coding the ids, then set the ones we want, will be easier to maintain
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.grass.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.dirt.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.sand.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.gravel.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.plantYellow.blockID] = true;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.plantRed.blockID] = true;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.mushroomBrown.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.mushroomRed.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.tnt.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.cactus.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.blockClay.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.pumpkin.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.melon.blockID] = false;
        net.minecraft.entity.monster.EntityEnderman.carriableBlocks[Block.mycelium.blockID] = false;
	}
}

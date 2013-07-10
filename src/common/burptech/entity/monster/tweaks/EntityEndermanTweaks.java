package burptech.entity.monster.tweaks;

import burptech.BurpTechCore;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;

/**
 * Enderman tweaks 
 */
public final class EntityEndermanTweaks 
{
	/**
	 * Will make Enderman only pickup and carry red and yellow flowers (anti-griefing)
	 */
	public static void enableAntiGriefing()
	{
		BurpTechCore.log.info("Enabling Enderman Anti Griefing");
		
		// TODO: Probably change this to a for each, instead of hard coding the ids, then set the ones we want, will be easier to maintain
        EntityEnderman.carriableBlocks[Block.grass.blockID] = false;
        EntityEnderman.carriableBlocks[Block.dirt.blockID] = false;
        EntityEnderman.carriableBlocks[Block.sand.blockID] = false;
        EntityEnderman.carriableBlocks[Block.gravel.blockID] = false;
        EntityEnderman.carriableBlocks[Block.plantYellow.blockID] = true;
        EntityEnderman.carriableBlocks[Block.plantRed.blockID] = true;
        EntityEnderman.carriableBlocks[Block.mushroomBrown.blockID] = false;
        EntityEnderman.carriableBlocks[Block.mushroomRed.blockID] = false;
        EntityEnderman.carriableBlocks[Block.tnt.blockID] = false;
        EntityEnderman.carriableBlocks[Block.cactus.blockID] = false;
        EntityEnderman.carriableBlocks[Block.blockClay.blockID] = false;
        EntityEnderman.carriableBlocks[Block.pumpkin.blockID] = false;
        EntityEnderman.carriableBlocks[Block.melon.blockID] = false;
        EntityEnderman.carriableBlocks[Block.mycelium.blockID] = false;
	}
}

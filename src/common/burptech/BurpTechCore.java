package burptech;

import java.util.logging.Logger;

import burptech.lib.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import net.minecraftforge.common.*;

/**
 * BurpTech core mod but not... a core mod !( . Y . )!
 *
 */
@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION)
public class BurpTechCore
{
    /**
     * Default logger instance
     */
    public static Logger Log = null;
    
    /**
     * Instance of the mod
     */
    @Instance(Constants.MOD_ID)
    public static BurpTechCore Instance;
    
    /**
     * Instance of the configuration
     */
    public static BurpTechConfig Configuration;
    
    @PreInit
    public void PreInitialization(FMLPreInitializationEvent e)
    {
        // setup logger
        Log = Logger.getLogger(Constants.MOD_ID);
        Configuration = BurpTechConfig.Load(e.getModConfigurationDirectory());
        
        // load up language translations
        
        // initialize blocks/items
                
        // register keyboard bindings
    }
    
    @Init
    public void Initialization(FMLInitializationEvent e)
    {
        // gui handlers
    	
        // event handlers
    	if (Configuration.enableSlimeSpawningRestrictions.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntitySlimeEventHandler());
    	
    	if (Configuration.enablePigZombieSpawningRestrictions.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntityPigZombieEventHandler());
    	
    	if (Configuration.enableMagmaCubeSpawningRestrictions.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntityMagmaCubeEventHandler());
    	
    	if (Configuration.enableWitherSkeletonSpawningRestrictions.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.monster.tweaks.EntityWitherSkeletonEventHandler());
    	
    	if (Configuration.enableMobsEatingOffOfGround.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new burptech.entity.passive.tweaks.EntityAnimalEventHandler());

    	// tile entity registrations
    	
        // recipes
    	
    }
    
    @PostInit
    public void PostInitialization(FMLPostInitializationEvent e)
    {
    	// tweaks
    	if (Configuration.disableEndermanGriefing.getBoolean(true))
    		burptech.entity.monster.tweaks.EntityEndermanTweaks.EnableAntiGriefing();

    	
        // mod integrations
    	
    }
}
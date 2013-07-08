package burptech;

import java.util.logging.Logger;

import burptech.lib.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;

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
     * Instance of this class
     */
    @Instance(Constants.MOD_ID)
    public static BurpTechCore Instance;
    
    /**
     * Instance of the configuration
     */
    public static BurpTechConfig Configuration;
    
    /**
     * Preinitalization stuff here
     * @param e
     */
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
    
    /**
     * Initialization stuff here
     * @param e
     */
    @Init
    public void Initialization(FMLInitializationEvent e)
    {
        // gui handlers
    	
        // event handlers
    	
        // tile entity registrations
    	
        // recipes
    	
    }
    
    /**
     * Post initialization stuff here
     * @param e
     */
    @PostInit
    public void PostInitialization(FMLPostInitializationEvent e)
    {
    	// tweaks
    	if (Configuration.disableEndermanGriefing.getBoolean(true))
    		burptech.entity.monster.tweaks.EntityEnderman.EnableAntiGriefing();

    	
        // mod integrations
    	
    }
}
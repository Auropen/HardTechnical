package techplex;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import core.TechPlexCore;

@Mod(modid = TechPlex.MODID, name = TechPlex.NAME, version = TechPlex.VERSION)
public class TechPlex {
    public static final String MODID = "techplex";
    public static final String NAME = "TechPlex";
    public static final String VERSION = "0.1.0-alpha";
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    	TechPlexCore.GetInstance().preinit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	TechPlexCore.GetInstance().init();
    }
}

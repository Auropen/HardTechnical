package techplex;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import techplex.core.TechPlexCore;

@Mod(modid = TechPlex.MODID, name = TechPlex.NAME, version = TechPlex.VERSION, acceptedMinecraftVersions="[1.8]")
public class TechPlex {
    public static final String MODID = "techplex";
    public static final String NAME = "TechPlex";
    public static final String VERSION = "0.0.0-alpha";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	TechPlexCore.getInstance().preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	TechPlexCore.getInstance().init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	TechPlexCore.getInstance().postInit();
    }
}

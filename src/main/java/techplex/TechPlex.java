package techplex;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import techplex.core.TechPlexCore;
import techplex.core.proxy.CommonProxy;
import techplex.core.handlers.GUIHandler;

@Mod(modid = TechPlex.MODID, name = TechPlex.NAME, version = TechPlex.VERSION, acceptedMinecraftVersions="[1.8]")
public class TechPlex {
    public static final String MODID = "techplex";
    public static final String NAME = "TechPlex";
    public static final String VERSION = "0.0.0-alpha";
    
    @Instance(MODID)
	public static TechPlex instance;
    
    @SidedProxy(clientSide="techplex.core.proxy.ClientProxy", serverSide="techplex.core.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    public static SimpleNetworkWrapper network;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	TechPlexCore.getInstance().preInit();
    	proxy.preInit(event);
    	network = NetworkRegistry.INSTANCE.newSimpleChannel("TechPlex");
    	

		if (TechPlex.proxy != null) {
			NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
		}
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	TechPlexCore.getInstance().init();
	    proxy.init(event);
		proxy.registerRenders();
		proxy.registerProxies();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	TechPlexCore.getInstance().postInit();
    }
}

package techplex.core.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import techplex.core.blocks.nature.TPBlockLeaves;
import techplex.core.blocks.nature.TPBlockLog;
import techplex.core.blocks.nature.TPBlockPlanks;
import techplex.core.blocks.nature.TPBlockSapling;
import techplex.core.pipes.TileEntityPipeRender;
import techplex.core.pipes.energy.TileEntityCopperCable;
import techplex.core.registry.TPBlocks;
import techplex.core.registry.TPItems;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}	
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCopperCable.class, new TileEntityPipeRender());
	}
	
	public void registerRenders() {
		TPBlocks.registerRenders();
		TPBlockLog.inventoryRender();
		TPBlockLeaves.inventoryRender();
		TPBlockPlanks.inventoryRender();
		TPBlockSapling.inventoryRender();
		
		TPItems.registerRenders();
	}
}

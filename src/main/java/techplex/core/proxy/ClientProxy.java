package techplex.core.proxy;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import techplex.TechPlex;
import techplex.core.blocks.nature.TPBlockLeaves;
import techplex.core.blocks.nature.TPBlockLog;
import techplex.core.pipes.TileEntityPipe;
import techplex.core.pipes.TileEntityPipeRender;
import techplex.core.registry.TPBlocks;
import techplex.core.registry.TPItems;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
		super.preInit(event);
		ModelBakery.addVariantName(Item.getItemFromBlock(TPBlocks.techplex_log), new String[]{TechPlex.MODID + ":sharinga_log"});
		ModelBakery.addVariantName(Item.getItemFromBlock(TPBlocks.techplex_leaves), new String[]{TechPlex.MODID + ":sharinga_leaves"});
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		((TPBlockLog) TPBlocks.techplex_log).registerRenderer("sharinga_log");
		((TPBlockLeaves) TPBlocks.techplex_leaves).registerRenderer("sharinga_leaves");
		super.init(event);
		
		/*Item log = GameRegistry.findItem(TechPlex.MODID, "sharinga_log");
		registerItem(log, 0, TechPlex.MODID + ":sharinga_leaves");*/
	}	
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		super.postInit(event);
	}
	
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new TileEntityPipeRender());
	}
	
	public void registerRenders() {
		TPBlocks.registerRenders();
		//TPBlockLog.inventoryRender();
		//TPBlockLeaves.inventoryRender();
		
		TPItems.registerRenders();
	}
}

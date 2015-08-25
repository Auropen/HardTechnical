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
import techplex.core.blocks.nature.TPBlockPlanks;
import techplex.core.blocks.nature.TPBlockSapling;
import techplex.core.pipes.TileEntityPipe;
import techplex.core.pipes.TileEntityPipeRender;
import techplex.core.registry.TPBlocks;
import techplex.core.registry.TPItems;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ModelBakery.addVariantName(Item.getItemFromBlock(TPBlocks.techplex_log), new String[]{TechPlex.MODID + ":sharinga_log"});
		ModelBakery.addVariantName(Item.getItemFromBlock(TPBlocks.techplex_leaves), new String[]{TechPlex.MODID + ":sharinga_leaves"});
		ModelBakery.addVariantName(Item.getItemFromBlock(TPBlocks.techplex_planks), new String[]{TechPlex.MODID + ":sharinga_planks"});
		ModelBakery.addVariantName(Item.getItemFromBlock(TPBlocks.techplex_sapling), new String[]{TechPlex.MODID + ":sharinga_sapling"});
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new TileEntityPipeRender());
	}
	
	public void registerRenders() {
		TPBlocks.registerRenders();
		((TPBlockLog) TPBlocks.techplex_log).registerRenderer("sharinga_log");
		((TPBlockLeaves) TPBlocks.techplex_leaves).registerRenderer("sharinga_leaves");
		((TPBlockPlanks) TPBlocks.techplex_planks).registerRenderer("sharinga_planks");
		((TPBlockSapling) TPBlocks.techplex_sapling).RegisterRenderer("sharinga_sapling");
		
		TPItems.registerRenders();
	}
}

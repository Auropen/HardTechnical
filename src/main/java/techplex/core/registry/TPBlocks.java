package techplex.core.registry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;
import techplex.core.blocks.*;
import techplex.core.blocks.nature.TPBlockLeaves;
import techplex.core.blocks.nature.TPBlockLog;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.pipes.BlockPipe;

public class TPBlocks {
    public final static Block copperOre = new CopperOre();
    public final static Block tinOre = new TinOre();
    public final static Block leadOre = new LeadOre();
    public final static Block titaniumOre = new TitaniumOre();
    
    //public final static Block sharingaLog = new Sharinga_Log();
    //public final static Block sharingaLeaves = new Sharinga_Leaves();
    public final static Block techplex_log = new TPBlockLog("techplex_log");
    public final static Block techplex_leaves = new TPBlockLeaves("techplex_leaves");
    
    public final static Block blockPipe = new BlockPipe();
    public final static Block tinCable = new TinCable();
    public final static Block copperCable = new CopperCable();
    
    public void register() {
    	GameRegistry.registerBlock(copperOre, CopperOre.BLOCKID);
    	GameRegistry.registerBlock(tinOre, TinOre.BLOCKID);
    	//GameRegistry.registerBlock(leadOre, LeadOre.BLOCKID);
    	//GameRegistry.registerBlock(bauxiteOre, BauxiteOre.BLOCKID);
    	//GameRegistry.registerBlock(titaniumOre, TitaniumOre.BLOCKID);
    	//GameRegistry.registerBlock(techplex_log, TPBlockLog.BLOCKID);
    	//GameRegistry.registerBlock(techplex_leaves, TPBlockLeaves.BLOCKID);
    	
    	GameRegistry.registerBlock(blockPipe, BlockPipe.BLOCKID);
    	GameRegistry.registerBlock(tinCable, TinCable.BLOCKID);
    	GameRegistry.registerBlock(copperCable, CopperCable.BLOCKID);
    }

	public static void registerRenders() {
		registerRender(copperOre, CopperOre.BLOCKID);
		registerRender(tinOre, TinOre.BLOCKID);
    	//registerRender(leadOre, LeadOre.BLOCKID);
    	//registerRender(bauxiteOre, BauxiteOre.BLOCKID);
    	//registerRender(titaniumOre, TitaniumOre.BLOCKID);
		registerRender(blockPipe, BlockPipe.BLOCKID);
		registerRender(tinCable, TinCable.BLOCKID);
		registerRender(copperCable, CopperCable.BLOCKID);
	}
	
	private static void registerRender(Block b, String blockID) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(b), 0, new ModelResourceLocation(TechPlex.MODID+":"+blockID, "inventory"));
	}
}

package techplex.core.registry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;
import techplex.core.blocks.*;
import techplex.core.blocks.nature.ItemBlockLeaves;
import techplex.core.blocks.nature.ItemBlockPlanks;
import techplex.core.blocks.nature.ItemBlockSapling;
import techplex.core.blocks.nature.TPBlockLeaves;
import techplex.core.blocks.nature.TPBlockLog;
import techplex.core.blocks.nature.TPBlockPlanks;
import techplex.core.blocks.nature.TPBlockSapling;
import techplex.core.pipes.energy.CopperCable;
import techplex.core.pipes.energy.TinCable;

public class TPBlocks {
    public final static Block copperOre = new CopperOre();
    public final static Block tinOre = new TinOre();
    public final static Block leadOre = new LeadOre();
    public final static Block titaniumOre = new TitaniumOre();
    public final static Block techplex_log = new TPBlockLog().setUnlocalizedName("techplex_log");
    public final static Block techplex_leaves = new TPBlockLeaves().setUnlocalizedName("techplex_leaves");;
    public final static Block techplex_planks = new TPBlockPlanks().setUnlocalizedName("techplex_planks");;
    public final static Block techplex_sapling = new TPBlockSapling().setUnlocalizedName("techplex_sapling");;
    
    public final static Block tinCable = new TinCable();
    public final static Block copperCable = new CopperCable();
    
    public void register() {
    	GameRegistry.registerBlock(copperOre, CopperOre.BLOCKID);
    	GameRegistry.registerBlock(tinOre, TinOre.BLOCKID);
    	//GameRegistry.registerBlock(leadOre, LeadOre.BLOCKID);
    	//GameRegistry.registerBlock(bauxiteOre, BauxiteOre.BLOCKID);
    	GameRegistry.registerBlock(titaniumOre, TitaniumOre.BLOCKID);
    	//GameRegistry.registerBlock(techplex_log, ItemBlockLog.class, TPBlockLog.BLOCKID);
    	GameRegistry.registerBlock(techplex_leaves, ItemBlockLeaves.class, TPBlockLeaves.BLOCKID);
    	GameRegistry.registerBlock(techplex_planks, ItemBlockPlanks.class, TPBlockPlanks.BLOCKID);
    	GameRegistry.registerBlock(techplex_sapling, ItemBlockSapling.class, TPBlockSapling.BLOCKID);
    	GameRegistry.registerBlock(tinCable, TinCable.BLOCKID);
    	GameRegistry.registerBlock(copperCable, CopperCable.BLOCKID);
    }

	public static void registerRenders() {
		registerRender(copperOre, CopperOre.BLOCKID);
		registerRender(tinOre, TinOre.BLOCKID);
    	//registerRender(leadOre, LeadOre.BLOCKID);
    	//registerRender(bauxiteOre, BauxiteOre.BLOCKID);
    	registerRender(titaniumOre, TitaniumOre.BLOCKID);
		registerRender(tinCable, TinCable.BLOCKID);
		registerRender(copperCable, CopperCable.BLOCKID);
	}
	
	private static void registerRender(Block b, String blockID) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(b), 0, new ModelResourceLocation(TechPlex.MODID+":"+blockID, "inventory"));
	}
}

package techplex.core;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.core.pipes.energy.TileEntityCopperCable;
import techplex.core.registry.TPBlocks;
import techplex.core.registry.TPItems;
import techplex.core.worldGen.WorldGeneration;

public class TechPlexCore {
	private static TechPlexCore instance;
	private TPBlocks blocks = new TPBlocks();
	private TPItems items = new TPItems();
	
	/**
	 * Prevents initialising this class, making it a singleton
	 */
	private TechPlexCore() {}
	
	/**
	 * If nonexistent, this method creates a new instance of TechPlexCore
	 * else returns the existent one
	 * @return the instance of TechPlexCore
	 */
	public static TechPlexCore getInstance() {
		if (instance == null)
			instance = new TechPlexCore();
		return instance;
	}
	
	public void preInit() {
		blocks.register();
		items.register();
		//FMLCommonHandler.instance().bus().register(new TileEntityCable());
	}		

	
	public void init() {
		/*
		//Items registering
		ItemRegistry.registerItem(TPItems.copperIngot, CopperIngot.ITEMID);
		ItemRegistry.registerItem(TPItems.tinIngot, TinIngot.ITEMID);
		ItemRegistry.registerItem(TPItems.bronzeIngot, BronzeIngot.ITEMID);
		ItemRegistry.registerItem(TPItems.ironDust, IronDust.ITEMID);
		ItemRegistry.registerItem(TPItems.goldDust, GoldDust.ITEMID);
		ItemRegistry.registerItem(TPItems.copperDust, CopperDust.ITEMID);
		ItemRegistry.registerItem(TPItems.tinDust, TinDust.ITEMID);
		ItemRegistry.registerItem(TPItems.bronzeDust, BronzeDust.ITEMID);
		
		//Blocks registering
		BlockRegistry.registerBlock(TPBlocks.copperOre, CopperOre.BLOCKID);
		BlockRegistry.registerBlock(TPBlocks.tinOre, TinOre.BLOCKID);
		BlockRegistry.registerBlock(TPBlocks.sharingaLog, Sharinga_Log.BLOCKID);
		BlockRegistry.registerBlock(TPBlocks.sharingaLeaves, Sharinga_Leaves.BLOCKID);
		BlockRegistry.registerBlock(TPBlocks.tinCable, TinCable.BLOCKID);
		BlockRegistry.registerBlock(TPBlocks.copperCable, CopperCable.BLOCKID);
		BlockRegistry.registerBlock(TPBlocks.blockPipe, BlockPipe.BLOCKID);
		
		*/
		//TiteEntity
		GameRegistry.registerTileEntity(TileEntityCopperCable.class, "tileEntityPipe");
		
		//Recipe registering
		GameRegistry.addShapelessRecipe(new ItemStack(TPItems.bronzeDust, 4), new Object[] {TPItems.tinDust, TPItems.copperDust, TPItems.copperDust, TPItems.copperDust});
		GameRegistry.addRecipe(new ItemStack(Items.baked_potato, 1), " i ", "iii", " i ", 'i', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(TPBlocks.copperCable, 1), "ccc", 'c', TPItems.copperIngot);
		GameRegistry.addRecipe(new ItemStack(TPBlocks.tinCable, 1), "ttt", 't', TPItems.tinIngot);
		//GameRegistry.addShapelessRecipe(new ItemStack(TPBlocks.techplex_log, 1, 0), new Object[] {TPBlocks.techplex_log});
		
		//Recipe smelting
		GameRegistry.addSmelting(TPBlocks.copperOre, 
				new ItemStack(TPItems.copperIngot,1), 0.7f);
		GameRegistry.addSmelting(TPBlocks.tinOre, 
				new ItemStack(TPItems.tinIngot,1), 0.7f);
		GameRegistry.addSmelting(TPItems.ironDust, 
				new ItemStack(Items.iron_ingot,1), 0.35f);
		GameRegistry.addSmelting(TPItems.goldDust, 
				new ItemStack(Items.gold_ingot,1), 0.35f);
		GameRegistry.addSmelting(TPItems.copperDust, 
				new ItemStack(TPItems.copperIngot,1), 0.35f);
		GameRegistry.addSmelting(TPItems.tinDust, 
				new ItemStack(TPItems.tinIngot,1), 0.35f);
		GameRegistry.addSmelting(TPItems.bronzeDust, 
				new ItemStack(TPItems.bronzeIngot,1), 0.35f);
	}
	
	public void postInit() {
		GameRegistry.registerWorldGenerator(new WorldGeneration(), 10);
	}
}

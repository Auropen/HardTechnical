package techplex.core;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.core.blocks.*;
import techplex.core.items.*;
import techplex.core.pipes.BlockPipe;
import techplex.core.pipes.TileEntityPipe;
import techplex.core.registry.*;

public class TechPlexCore {
	private static TechPlexCore instance;
	
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
	}
	
	public void init() {
		//Items registering
		ItemRegistry.registerItem(new CopperIngot(), CopperIngot.ITEMID);
		ItemRegistry.registerItem(new TinIngot(), TinIngot.ITEMID);
		ItemRegistry.registerItem(new BronzeIngot(), BronzeIngot.ITEMID);
		ItemRegistry.registerItem(new IronDust(), IronDust.ITEMID);
		ItemRegistry.registerItem(new GoldDust(), GoldDust.ITEMID);
		ItemRegistry.registerItem(new CopperDust(), CopperDust.ITEMID);
		ItemRegistry.registerItem(new TinDust(), TinDust.ITEMID);
		ItemRegistry.registerItem(new BronzeDust(), BronzeDust.ITEMID);
		
		//Blocks registering
		BlockRegistry.registerBlock(new CopperOre(), CopperOre.BLOCKID);
		BlockRegistry.registerBlock(new TinOre(), TinOre.BLOCKID);
		BlockRegistry.registerBlock(new SharingaLog(), SharingaLog.BLOCKID);
		BlockRegistry.registerBlock(new TinCable(), TinCable.BLOCKID);
		BlockRegistry.registerBlock(new CopperCable(), CopperCable.BLOCKID);
		BlockRegistry.registerBlock(new BlockPipe(), "blockPipe");
		
		//TiteEntity
		GameRegistry.registerTileEntity(TileEntityPipe.class, "tileEntityPipe");
		
		//Recipe registering
		RecipeRegistry.registerShapelessRecipe(BronzeDust.ITEMID, CopperDust.ITEMID, CopperDust.ITEMID, CopperDust.ITEMID, TinDust.ITEMID);
		GameRegistry.addRecipe(new ItemStack(Items.baked_potato, 1), " i ", "iii", " i ", 'i', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(BlockRegistry.REGBLOCKS.get(CopperCable.BLOCKID), 1), "ccc", 'c', ItemRegistry.REGITEMS.get(CopperIngot.ITEMID));
		GameRegistry.addRecipe(new ItemStack(BlockRegistry.REGBLOCKS.get(TinCable.BLOCKID), 1), "ttt", 't', ItemRegistry.REGITEMS.get(TinIngot.ITEMID));
		
		//Recipe smelting
		GameRegistry.addSmelting(BlockRegistry.REGBLOCKS.get(CopperOre.BLOCKID), 
				new ItemStack(ItemRegistry.REGITEMS.get(CopperIngot.ITEMID),1), 0.7f);
		GameRegistry.addSmelting(BlockRegistry.REGBLOCKS.get(TinOre.BLOCKID), 
				new ItemStack(ItemRegistry.REGITEMS.get(TinIngot.ITEMID),1), 0.7f);
		GameRegistry.addSmelting(ItemRegistry.REGITEMS.get(IronDust.ITEMID), 
				new ItemStack(Items.iron_ingot,1), 0.35f);
		GameRegistry.addSmelting(ItemRegistry.REGITEMS.get(GoldDust.ITEMID), 
				new ItemStack(Items.gold_ingot,1), 0.35f);
		GameRegistry.addSmelting(ItemRegistry.REGITEMS.get(CopperDust.ITEMID), 
				new ItemStack(ItemRegistry.REGITEMS.get(CopperIngot.ITEMID),1), 0.35f);
		GameRegistry.addSmelting(ItemRegistry.REGITEMS.get(TinDust.ITEMID), 
				new ItemStack(ItemRegistry.REGITEMS.get(TinIngot.ITEMID),1), 0.35f);
		GameRegistry.addSmelting(ItemRegistry.REGITEMS.get(BronzeDust.ITEMID), 
				new ItemStack(ItemRegistry.REGITEMS.get(BronzeIngot.ITEMID),1), 0.35f);
	}
	
	public void postInit() {
		GameRegistry.registerWorldGenerator(new WorldGeneration(), 0);
	}
}

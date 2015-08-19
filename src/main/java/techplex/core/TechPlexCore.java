package techplex.core;

import techplex.core.blocks.*;
import techplex.core.items.*;
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
	public static TechPlexCore GetInstance() {
		if (instance == null)
			instance = new TechPlexCore();
		return instance;
	}
	
	public void preinit() {
	}
	
	public void init() {
		//Items registering
		ItemRegistry.registerItem(new CopperIngot(), CopperIngot.ITEMID);
		ItemRegistry.registerItem(new TinIngot(), TinIngot.ITEMID);
		ItemRegistry.registerItem(new CopperDust(), CopperDust.ITEMID);
		ItemRegistry.registerItem(new TinDust(), TinDust.ITEMID);
		
		//Blocks registering
		BlockRegistry.registerBlock(new CopperOre(), CopperOre.BLOCKID);
		BlockRegistry.registerBlock(new TinOre(), TinOre.BLOCKID);
	}
}

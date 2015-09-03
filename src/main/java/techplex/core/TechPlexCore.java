package techplex.core;

import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.core.pipes.energy.TileEntityCopperCable;
import techplex.core.registry.RecipeRegistry;
import techplex.core.registry.TPBlocks;
import techplex.core.registry.TPDictionary;
import techplex.core.registry.TPItems;
import techplex.core.tileentity.TileEntityAlloyFurnace;
import techplex.core.worldGen.WorldGeneration;

public final class TechPlexCore {
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
		
		RecipeRegistry.register();
		
		TPDictionary.load();
	}

	
	public void init() {
		//TiteEntity
		GameRegistry.registerTileEntity(TileEntityCopperCable.class, "tileEntityPipe");
		GameRegistry.registerTileEntity(TileEntityAlloyFurnace.class, "tileEntityAlloyFurnace");
		
		
	}
	
	public void postInit() {
		GameRegistry.registerWorldGenerator(new WorldGeneration(), 10);
	}
}

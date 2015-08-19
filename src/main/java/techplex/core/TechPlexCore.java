package techplex.core;

import net.minecraft.block.Block;
//import core.items.UninsulatedCable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;
import techplex.core.block.CopperOre;
import techplex.core.block.TinOre;

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
		Block copper = new CopperOre();
		Block tin = new TinOre();
		//GameRegistry.registerItem(new UninsulatedCable(), "tp.uninsulatedCable");
		GameRegistry.registerBlock(copper, CopperOre.BLOCKID);
		GameRegistry.registerBlock(tin, TinOre.BLOCKID);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(copper), 0, new ModelResourceLocation(TechPlex.MODID+":"+CopperOre.BLOCKID, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(tin), 0, new ModelResourceLocation(TechPlex.MODID+":"+TinOre.BLOCKID, "inventory"));
	}
}

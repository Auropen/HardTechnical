package techplex.core;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;
import techplex.core.block.CopperOre;
import techplex.core.block.TinOre;
import techplex.core.items.CopperDust;
import techplex.core.items.CopperIngot;
import techplex.core.items.TinDust;
import techplex.core.items.TinIngot;

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
		Item copperI = new CopperIngot();
		Item tinI = new TinIngot();
		Item copperD = new CopperDust();
		Item tinD = new TinDust();
		
		GameRegistry.registerItem(copperI, CopperIngot.ITEMID);
		GameRegistry.registerItem(tinI, TinIngot.ITEMID);
		GameRegistry.registerItem(copperD, CopperDust.ITEMID);
		GameRegistry.registerItem(tinD, TinDust.ITEMID);

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(copperI, 0, new ModelResourceLocation(TechPlex.MODID+":"+CopperIngot.ITEMID, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tinI, 0, new ModelResourceLocation(TechPlex.MODID+":"+TinIngot.ITEMID, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(copperD, 0, new ModelResourceLocation(TechPlex.MODID+":"+CopperDust.ITEMID, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tinD, 0, new ModelResourceLocation(TechPlex.MODID+":"+TinDust.ITEMID, "inventory"));
		
		//Blocks registering
		Block copperO = new CopperOre();
		Block tinO = new TinOre();
		
		GameRegistry.registerBlock(copperO, CopperOre.BLOCKID);
		GameRegistry.registerBlock(tinO, TinOre.BLOCKID);
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(copperO), 0, new ModelResourceLocation(TechPlex.MODID+":"+CopperOre.BLOCKID, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(tinO), 0, new ModelResourceLocation(TechPlex.MODID+":"+TinOre.BLOCKID, "inventory"));
	}
}

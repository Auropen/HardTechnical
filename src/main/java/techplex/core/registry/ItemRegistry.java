package techplex.core.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;

public final class ItemRegistry {
	public final static List<Item> REGITEMS = new ArrayList<Item>();
	
	private ItemRegistry() {}
	
	public static void registerItem(Item i, String itemID) {
		GameRegistry.registerItem(i, itemID);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(TechPlex.MODID+":"+itemID, "inventory"));
		REGITEMS.add(i);
	}
}

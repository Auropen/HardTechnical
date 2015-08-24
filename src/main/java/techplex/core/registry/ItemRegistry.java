package techplex.core.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;

public final class ItemRegistry {
	private ItemRegistry() {}
	
	public static void registerItem(Item i, String itemID) {
		GameRegistry.registerItem(i, itemID);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(TechPlex.MODID+":"+itemID, "inventory"));
	}
}

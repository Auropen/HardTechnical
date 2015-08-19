package techplex.core.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;

public final class BlockRegistry {
public final static List<Block> REGBLOCKS = new ArrayList<Block>();
	
	private BlockRegistry() {}
	
	public static void registerBlock(Block b, String blockID) {
		GameRegistry.registerBlock(b, blockID);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(b), 0, new ModelResourceLocation(TechPlex.MODID+":"+blockID, "inventory"));
		REGBLOCKS.add(b);
	}
}

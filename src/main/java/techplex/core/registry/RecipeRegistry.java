package techplex.core.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry {
	private RecipeRegistry() {}
	
	public static void registerShapelessRecipe(String outputID, String... inputsID) {
		if (BlockRegistry.REGBLOCKS.containsKey(outputID)) {
			ItemStack output = new ItemStack(BlockRegistry.REGBLOCKS.get(outputID), 1);
			Object[] inputs = new Object[inputsID.length];
			
			for (int i = 0; i < inputsID.length; i++)
				inputs[i] = (BlockRegistry.REGBLOCKS.containsKey(inputsID[i]) 
								? BlockRegistry.REGBLOCKS.get(inputsID[i]) 
								: ItemRegistry.REGITEMS.get(inputsID[i]));
			
			GameRegistry.addShapelessRecipe(output, inputs);
		}
	}
}

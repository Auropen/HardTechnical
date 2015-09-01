package techplex.core.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry {
	public static void register() {
		GameRegistry.addShapelessRecipe(new ItemStack(TPItems.bronzeDust, 4), TPItems.tinDust, TPItems.copperDust, TPItems.copperDust, TPItems.copperDust);
		GameRegistry.addRecipe(new ItemStack(TPItems.ironCogwheel, 1), " i ", "iii", " i ", 'i', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(TPItems.bronzeCogwheel, 1), " b ", "bbb", " b ", 'b', TPItems.bronzeIngot);
		GameRegistry.addRecipe(new ItemStack(TPItems.diamondCogwheel, 1), " d ", "ddd", " d ", 'd', Items.diamond);
		GameRegistry.addRecipe(new ItemStack(TPItems.rubber, 1), "rr", "rr", 'r', TPItems.rubberScrap);
		GameRegistry.addRecipe(new ItemStack(TPItems.plasticBoard, 1), "ppp", "pgp", "ppp", 'p', TPItems.plastic, 'g', new ItemStack(Items.dye, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(TPItems.circuitPaste, 1), TPItems.resin, Items.redstone, Items.water_bucket );
		GameRegistry.addShapelessRecipe(new ItemStack(TPItems.circuitGlue, 1), Items.redstone, Items.slime_ball );
		GameRegistry.addRecipe(new ItemStack(TPItems.circuitBoard, 1), " p ", " c ", "ggg", 'p', TPItems.plasticBoard, 'c', TPItems.circuitPaste, 'g', TPItems.goldDust);
		GameRegistry.addRecipe(new ItemStack(TPBlocks.copperCable, 6), "rrr", "ccc", "rrr", 'c', TPItems.copperIngot, 'r', TPItems.rubber);
		GameRegistry.addRecipe(new ItemStack(TPBlocks.tinCable, 6), "rrr", "ttt", "rrr", 't', TPItems.tinIngot, 'r', TPItems.rubber);
		//GameRegistry.addShapelessRecipe(new ItemStack(TPBlocks.techplex_log, 1, 0), new Object[] {TPBlocks.techplex_log});

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
		GameRegistry.addSmelting(TPItems.resin, 
				new ItemStack(TPItems.rubberScrap,1), 0.1f);
		GameRegistry.addSmelting(TPItems.rubber, 
				new ItemStack(TPItems.plastic,1), 0.1f);
	}
}

package techplex.core.registry;

import net.minecraftforge.oredict.OreDictionary;

public class TPDictionary {
	public static final int	WILDCARD_VALUE	= Short.MAX_VALUE;
	
	public static void load() {
		//Ingots
		OreDictionary.registerOre("ingotCopper", TPItems.copperIngot);
		OreDictionary.registerOre("ingotTin", TPItems.tinIngot);
		OreDictionary.registerOre("ingotBronze", TPItems.bronzeIngot);
		
		//Dusts
		
		//Gears
		
		//Tree
		OreDictionary.registerOre("rubberScrap", TPItems.rubberScrap);
		OreDictionary.registerOre("rubber", TPItems.rubber);
		
		//Misc
	}
}

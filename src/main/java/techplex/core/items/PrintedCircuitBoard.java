package techplex.core.items;

import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class PrintedCircuitBoard extends Item {
	public final static String ITEMID = "circuitBoard_printed";
	
	public PrintedCircuitBoard() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlexMain);
		setUnlocalizedName(TechPlex.MODID+"_"+ITEMID);
	}
}

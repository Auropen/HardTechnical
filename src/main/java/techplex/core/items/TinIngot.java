package techplex.core.items;

import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class TinIngot extends Item {
	public final static String ITEMID = "tinIngot";
	
	public TinIngot() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlexMain);
		setUnlocalizedName(TechPlex.MODID+"_"+ITEMID);
	}
}

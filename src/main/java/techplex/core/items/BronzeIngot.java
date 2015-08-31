package techplex.core.items;

import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class BronzeIngot extends Item {
	public final static String ITEMID = "bronzeIngot";
	
	public BronzeIngot() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlexMain);
		setUnlocalizedName(TechPlex.MODID+"_"+ITEMID);
	}
}

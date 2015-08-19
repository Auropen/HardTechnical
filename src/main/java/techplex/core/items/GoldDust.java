package techplex.core.items;

import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class GoldDust extends Item {
public final static String ITEMID = "goldDust";
	
	public GoldDust() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		setUnlocalizedName(TechPlex.MODID+"_"+ITEMID);
	}
}

package techplex.core.items;

import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class CopperDust extends Item {
public final static String ITEMID = "copperDust";
	
	public CopperDust() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		setUnlocalizedName(TechPlex.MODID+"_"+ITEMID);
	}
}

package techplex.core.items;

import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class IronDust extends Item {
	public final static String ITEMID = "ironDust";
	
	public IronDust() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		setUnlocalizedName(TechPlex.MODID+"_"+ITEMID);
	}
}

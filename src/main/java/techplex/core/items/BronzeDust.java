package techplex.core.items;

import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class BronzeDust extends Item {
public final static String ITEMID = "bronzeDust";
	
	public BronzeDust() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		setUnlocalizedName(TechPlex.MODID+"_"+ITEMID);
	}
}

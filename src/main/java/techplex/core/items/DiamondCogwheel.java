package techplex.core.items;

import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class DiamondCogwheel extends Item {
public final static String ITEMID = "diamondCogwheel";
	
	public DiamondCogwheel() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		setUnlocalizedName(TechPlex.MODID+"_"+ITEMID);
	}
}

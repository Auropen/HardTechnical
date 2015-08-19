package techplex.core.items;

import net.minecraft.item.Item;
import techplex.core.CreativeTabsTechPlex;

public class UninsulatedCable extends Item {
	
	public UninsulatedCable() {
		maxStackSize = 64;
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		setUnlocalizedName("uninsulatedCable");
	}
}

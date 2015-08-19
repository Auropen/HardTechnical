package techplex.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import techplex.core.items.UninsulatedCable;

public class CreativeTabsTechPlex extends CreativeTabs{
	public static final CreativeTabs tabTechPlex = new CreativeTabsTechPlex("techplex");
	
	public CreativeTabsTechPlex(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return new UninsulatedCable();
	}
}

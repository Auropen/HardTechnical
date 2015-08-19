package techplex.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import techplex.TechPlex;

public class CreativeTabsTechPlex extends CreativeTabs{
	public static final String MAINTABID = "techplex";
	public static final String MAINTABNAME = "TechPlex";
	
	public static final CreativeTabs tabTechPlex = new CreativeTabsTechPlex(TechPlex.MODID + "." + MAINTABID);
	
	public CreativeTabsTechPlex(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return Items.fire_charge;
	}
	
	@Override
	public String getTranslatedTabLabel() {
		return MAINTABNAME;
	}
}

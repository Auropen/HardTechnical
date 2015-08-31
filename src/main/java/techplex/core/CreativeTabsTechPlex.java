package techplex.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import techplex.TechPlex;
import techplex.core.registry.TPBlocks;
import techplex.core.registry.TPItems;

public class CreativeTabsTechPlex extends CreativeTabs{
	public static final String MAINTABID = "techplex main";
	public static final String MACHINETABID = "techplex machines";
	public static final String MAINTABNAME = "TechPlex Main";
	public static final String MACHINETABNAME = "Techplex Machines";
	
	public static final CreativeTabs tabTechPlexMain = new CreativeTabsTechPlex(TechPlex.MODID + "." + MAINTABID, 0);
	public static final CreativeTabs tabTechPlexMachines = new CreativeTabsTechPlex(TechPlex.MODID + "." + MACHINETABID, 1);
	private int tabID;
	
	public CreativeTabsTechPlex(String label, int id) {
		super(label);
		this.tabID = id;
	}

	@Override
	public Item getTabIconItem() {
		return (tabID == 0) ? TPItems.rubber : Item.getItemFromBlock(TPBlocks.alloyFurnace);
	}
	
	@Override
	public String getTranslatedTabLabel() {
		return (tabID == 0) ? MAINTABNAME : MACHINETABNAME;
	}
}

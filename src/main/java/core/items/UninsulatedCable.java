package core.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class UninsulatedCable extends Item {
	
	public UninsulatedCable() {
		maxStackSize = 64;
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("uninsulatedCable");
	}
}

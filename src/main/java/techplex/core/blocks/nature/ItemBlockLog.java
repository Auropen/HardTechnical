package techplex.core.blocks.nature;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import techplex.core.enumtypes.TPWoodType;

public class ItemBlockLog extends ItemBlock {
	public ItemBlockLog(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxStackSize(0);
	}
	
	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}
	
	public String getUnlocalizedName(ItemStack item)
	{
		return TPWoodType.byMetadata(item.getMetadata()) + "_log";
	}
}

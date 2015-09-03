package techplex.core.util;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TPOreUtil {
	public static final int WILDCARD_VALUE	= OreDictionary.WILDCARD_VALUE;

	public static boolean matchesOreDict(ItemStack input, ItemStack... stacks)
	{
		if (input == null) return false;

		final int oreID = OreDictionary.getOreID(input.getDisplayName());

		if (oreID == -1) return false;

		final List<ItemStack> ores = OreDictionary.getOres(OreDictionary.getOreName(oreID));

		for (final ItemStack ore : ores)
			for (final ItemStack stack : stacks)
				if (stack != null && OreDictionary.itemMatches(stack, ore, true)) 
					return true;

		return false;
	}
}

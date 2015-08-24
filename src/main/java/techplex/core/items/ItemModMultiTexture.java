package techplex.core.items;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
/**
 * Similar to vanilla's ItemMultiTexture, but doesn't have the unused theBlock field and doesn't set nameFunction from the constructor.
 * <p/>
 * The latter is so that GameRegistry can correctly instantiate this class via reflection. Class#getConstructor doesn't recognise derived types,
 * so GameRegistry will try (and fail) to get the constructor with the actual implementation classes instead of the base Block/Function types.
 */
public class ItemModMultiTexture extends ItemBlock {
	protected Function<ItemStack, String> nameFunction;

	public ItemModMultiTexture(Block block) {
		super(block);
	}

	public Function<ItemStack, String> getNameFunction() {
		return nameFunction;
	}

	public void setNameFunction(Function<ItemStack, String> nameFunction) {
		this.nameFunction = nameFunction;
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + this.nameFunction.apply(stack);
	}
}

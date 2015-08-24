package techplex.core.blocks.nature;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techplex.core.CreativeTabsTechPlex;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.items.ItemModMultiTexture;

public class TPBlockPlank extends Block{

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", TPWoodType.class, new Predicate() {
		public boolean apply(TPWoodType type) {
			return type.getMetadata() < 4;
		}

		public boolean apply(Object p_apply_1_) {
			return this.apply((TPWoodType) p_apply_1_);
		}
	});

	public TPBlockPlank(String modelName) {
		super(Material.wood);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, TPWoodType.SHARINGA));
		this.setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		
		System.out.println("INITIALIZING BLOCK: " + modelName);
		GameRegistry.registerBlock(this, ItemModMultiTexture.class, modelName); // Register the Block using ItemModMultiTexture as the ItemBlock class
		((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() { // Set the Item's name function
			@Nullable
			public String apply(ItemStack input) {
				return TPWoodType.byMetadata(input.getItemDamage()).getName();
			}
		});
	}

	/**
	 * Get the damage value that this Block should drop
	 */
	public int damageDropped(IBlockState state) {
		return ((TPWoodType)state.getValue(VARIANT)).getMetadata();
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		TPWoodType[] aenumtype = TPWoodType.values();
		int i = aenumtype.length;

		for (int j = 0; j < i; ++j)
		{
			TPWoodType enumtype = aenumtype[j];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, TPWoodType.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return ((TPWoodType)state.getValue(VARIANT)).getMetadata();
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {VARIANT});
	}
}
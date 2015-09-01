package techplex.core.blocks.nature;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techplex.core.CreativeTabsTechPlex;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.items.ItemModMultiTexture;
import techplex.core.registry.TPItems;

public class TPBlockLog extends BlockLog {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", TPWoodType.class, new Predicate() {
		public boolean apply(TPWoodType type) {
			return type.getMetadata() < 4;
		}

		public boolean apply(Object p_apply_1_) {
			return apply((TPWoodType)p_apply_1_);
		}
	});
	public static final String BLOCKID = "techplex_log";

	public TPBlockLog() {
		setDefaultState((this.blockState.getBaseState().withProperty(VARIANT, TPWoodType.SHARINGA).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y)));
		setCreativeTab(CreativeTabsTechPlex.tabTechPlexMain);

		GameRegistry.registerBlock(this, ItemModMultiTexture.class, BLOCKID); // Register the Block using ItemModMultiTexture as the ItemBlock class
		((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() { // Set the Item's name function
			@Nullable
			public String apply(ItemStack input) {
				return TPWoodType.byMetadata(input.getItemDamage()).getName();
			}
		});
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		TPWoodType[] enumtype = TPWoodType.values();
		for (int i = 0; i < enumtype.length; i++) {
			list.add(new ItemStack(itemIn, 1, enumtype[i].getMetadata()));
		}
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 150;
	}
	
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, TPWoodType.byMetadata((meta & 3) % 4));
		switch (meta & 12)
		{
		case 0: 
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
			break;
		case 4: 
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
			break;
		case 8: 
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
			break;
		default: 
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}
		return iblockstate;
	}

	public int getMetaFromState(IBlockState state)
	{
		byte b0 = 0;
		int i = b0 | ((TPWoodType)state.getValue(VARIANT)).getMetadata();
		//switch (((BlockLog)state.getValue(LOG_AXIS)).ordinal())
		switch (TPBlockLog.SwitchEnumAxis.AXIS_LOOKUP[((BlockLog.EnumAxis)state.getValue(LOG_AXIS)).ordinal()]) {
		case 1: 
			i |= 4;
			break;
		case 2: 
			i |= 8;
			break;
		case 3: 
			i |= 12;
		}
		return i;
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		if ((TPWoodType)state.getValue(VARIANT) == TPWoodType.SHARINGA && Math.random() < 0.75)
			new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ()).dropItem(TPItems.resin, 1);
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	}
	
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {VARIANT,LOG_AXIS});
	}

	protected ItemStack createStackedBlock(IBlockState state) {

		return new ItemStack(Item.getItemFromBlock(this), 1, ((TPWoodType)state.getValue(VARIANT)).getMetadata());
	}

	public int damageDropped(IBlockState state) {

		return ((TPWoodType)state.getValue(VARIANT)).getMetadata();
	}

	public static void inventoryRender() {
		Item itemBlockBrickVariants = GameRegistry.findItem("techplex", "techplex_log");

		ModelBakery.addVariantName(itemBlockBrickVariants, new String[] { "techplex:sharinga_log" });

		Item itemBlockVariants = GameRegistry.findItem("techplex", "techplex_log");
		TPWoodType[] enumtype = TPWoodType.values();
		for (int i = 0; i < enumtype.length; i++) {
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("techplex:" + enumtype[i] + "_log", "inventory");
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockVariants, enumtype[i].getMetadata(), itemModelResourceLocation);
		}
	}

	static final class SwitchEnumAxis {

		static final int[] AXIS_LOOKUP = new int[BlockLog.EnumAxis.values().length];
		static {

			try {
				AXIS_LOOKUP[BlockLog.EnumAxis.X.ordinal()] = 1;
			}
			catch (NoSuchFieldError var3) 
			{ ; }

			try {
				AXIS_LOOKUP[BlockLog.EnumAxis.Z.ordinal()] = 2;
			}
			catch (NoSuchFieldError var2) 
			{ ; }

			try {
				AXIS_LOOKUP[BlockLog.EnumAxis.NONE.ordinal()] = 3;
			}
			catch (NoSuchFieldError var1)
			{ ; }
		}
	}
}

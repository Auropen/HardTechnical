package techplex.core.blocks.nature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;
import techplex.core.CreativeTabsTechPlex;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.registry.TPBlocks;

public class TPBlockLeaves extends BlockLeaves {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", TPWoodType.class, new Predicate() {
		public boolean apply(TPWoodType type)
		  {
		    return type.getMetadata() < 4;
		  }
		  
		  public boolean apply(Object p_apply_1_)
		  {
		    return apply((TPWoodType)p_apply_1_);
		  }
	});
	
	public static final String BLOCKID = "techplex_leaves";

	public TPBlockLeaves() {
		setDefaultState((this.blockState.getBaseState().withProperty(VARIANT, TPWoodType.SHARINGA).withProperty(DECAYABLE, true).withProperty(CHECK_DECAY, true)));
		setCreativeTab(CreativeTabsTechPlex.tabTechPlexMain);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		TPWoodType[] aenumtype = TPWoodType.values();
		int i = aenumtype.length;
		for (int j = 0; j < i; j++) {
			TPWoodType enumtype = aenumtype[j];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
		}
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state) {
		if (state.getBlock() != this) {
			return super.getRenderColor(state);
		}else {
			TPWoodType enumtype = (TPWoodType)state.getValue(VARIANT);
			return enumtype == TPWoodType.SHARINGA ? LeafColors.sharinga() : super.getRenderColor(state);
		}
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
		IBlockState iblockstate = worldIn.getBlockState(pos);

		if (iblockstate.getBlock() == this) {
			TPWoodType enumtype = (TPWoodType)iblockstate.getValue(VARIANT);

			if (enumtype == TPWoodType.SHARINGA)
				return LeafColors.sharinga();
		}
		return super.colorMultiplier(worldIn, pos, renderPass);
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, TPWoodType.byMetadata((meta & 3) % 4)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));

	}

	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | ((TPWoodType)state.getValue(VARIANT)).getMetadata();
		if (!((Boolean)state.getValue(DECAYABLE)).booleanValue()) {
			i |= 4;
		}
		if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) {
			i |= 8;
		}
		return i;
	}

	/**
	 * Get the damage value that this Block should drop
	 */
	public int damageDropped(IBlockState state) {
		return ((TPWoodType)state.getValue(VARIANT)).getMetadata();
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return Blocks.leaves.getBlockLayer();
	}

	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
		super.harvestBlock(worldIn, player, pos, state, te);
	}

	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { CHECK_DECAY, DECAYABLE, VARIANT });
	}

	protected int getSaplingDropChance(IBlockState state) {
		return state.getValue(VARIANT) == TPWoodType.SHARINGA ? 20 : super.getSaplingDropChance(state);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (state.getValue(VARIANT) == TPWoodType.SHARINGA) ? Item.getItemFromBlock(TPBlocks.techplex_sapling) : super.getItemDropped(state, rand, fortune);
	}

	protected ItemStack createStackedBlock(IBlockState state) { 	
		return new ItemStack(Item.getItemFromBlock(this), 1, ((TPWoodType)state.getValue(VARIANT)).getMetadata());
	}

	public static void inventoryRender() {
		Item itemBlockBrickVariants = GameRegistry.findItem("techplex", "techplex_leaves");
	    
	    ModelBakery.addVariantName(itemBlockBrickVariants, new String[] { "techplex:sharinga_leaves" });
	    
	    Item itemBlockVariants = GameRegistry.findItem("techplex", "techplex_leaves");
		TPWoodType[] enumtype = TPWoodType.values();
		for (int i = 0; i < enumtype.length; i++) {
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("techplex:" + enumtype[i] + "_leaves", "inventory");
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockVariants, enumtype[i].getMetadata(), itemModelResourceLocation);
		}
	}

	public TPWoodType getCustomWoodType(int meta) {
		return TPWoodType.byMetadata((meta & 0x3) % 4);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		IBlockState state = world.getBlockState(pos);
		return new ArrayList<ItemStack>(Arrays.asList(new ItemStack[] { new ItemStack(this, 0, ((TPWoodType)state.getValue(VARIANT)).getMetadata()) }));
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}
}

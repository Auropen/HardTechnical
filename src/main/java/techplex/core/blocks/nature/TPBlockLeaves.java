package techplex.core.blocks.nature;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;
import techplex.core.enumtypes.TPWoodType;

public class TPBlockLeaves extends BlockLeaves {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", TPWoodType.class, new Predicate() {
		public boolean apply(TPWoodType type) {
			return type.getMetadata() < 4;
		}

		public boolean apply(Object p_apply_1_) {
			return this.apply((TPWoodType) p_apply_1_);
		}
	});

	public static final String BLOCKID = "techplex_leaves";

	public TPBlockLeaves(String modelName) {
		setDefaultState((this.blockState.getBaseState().withProperty(VARIANT, TPWoodType.SHARINGA).withProperty(DECAYABLE, Boolean.valueOf(true)).withProperty(CHECK_DECAY, Boolean.valueOf(true))));
		setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
		setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		System.out.println("INITIALIZING BLOCK: " + modelName);    
		GameRegistry.registerBlock(this, ItemBlockLeaves.class, modelName); // Register the Block using ItemModMultiTexture as the ItemBlock class
	}

	@SideOnly(Side.CLIENT)
	public void registerRenderer(String modelName) {
		System.out.println("INITIALIZING BLOCK: " + modelName);

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(TechPlex.MODID+":" + modelName, "inventory"));
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
				return ColorizerFoliage.getFoliageColor(0, 0);
			/*if (enumtype == ???)
			 * 	return ColorizerFoliage.getFoliageColorBirch();
			 */
		}
		return super.colorMultiplier(worldIn, pos, renderPass);
	}

	public IBlockState getStateFromMeta(int meta)
	{
		//return this.getDefaultState().withProperty(VARIANT, getCustomWoodType(meta)).withProperty(DECAYABLE, Boolean.valueOf((meta & 0x4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 0x8) > 0));
		return this.getDefaultState().withProperty(VARIANT, TPWoodType.byMetadata((meta & 3) % 4)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));

	}

	public int getMetaFromState(IBlockState state)
	{
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
		return new BlockState(this, new IProperty[] { VARIANT, CHECK_DECAY, DECAYABLE });
	}

	protected int getSaplingDropChance(IBlockState state) {
		return state.getValue(VARIANT) == TPWoodType.SHARINGA ? 20 : super.getSaplingDropChance(state);
	}

	protected ItemStack createStackedBlock(IBlockState state) { 	
		return new ItemStack(Item.getItemFromBlock(this), 1, ((TPWoodType)state.getValue(VARIANT)).getMetadata());
	}

	/*public static void inventoryRender() {
		Item itemBlockBrickVariants = GameRegistry.findItem(TechPlex.MODID, "techplex_leaves");

		ModelBakery.addVariantName(itemBlockBrickVariants, new String[] { TechPlex.MODID + ":sharinga_leaves" });
		TPWoodType[] enumtype = TPWoodType.values();
		for (int i = 0; i < enumtype.length; i++) {
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(TechPlex.MODID + ":" + enumtype[i] + "_leaves", "inventory");
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockBrickVariants, enumtype[i].getMetadata(), itemModelResourceLocation);
		}
	}*/

	public TPWoodType getCustomWoodType(int meta)
	{
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

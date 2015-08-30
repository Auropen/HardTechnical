package techplex.core.blocks.nature;

import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techplex.core.CreativeTabsTechPlex;
import techplex.core.enumtypes.TPWoodType;

public class TPBlockPlanks extends Block {
	public static final PropertyEnum TYPE = PropertyEnum.create("type", TPWoodType.class, new Predicate() {
		public boolean apply(TPWoodType type)
		  {
		    return type.getMetadata() < 4;
		  }
		  
		  public boolean apply(Object p_apply_1_)
		  {
		    return apply((TPWoodType)p_apply_1_);
		  }
	});
	public static final String BLOCKID = "techplex_planks";

	public TPBlockPlanks() {
		super(Material.wood);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, TPWoodType.SHARINGA));
		this.setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		this.setStepSound(soundTypeWood);
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 300;
	}
	
	/**
	 * Get the damage value that this Block should drop
	 */
	public int damageDropped(IBlockState state) {
		return ((TPWoodType)state.getValue(TYPE)).getMetadata();
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
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
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, TPWoodType.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return ((TPWoodType)state.getValue(TYPE)).getMetadata();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {TYPE});
	}

	public static void inventoryRender() {
		Item itemBlockBrickVariants = GameRegistry.findItem("techplex", "techplex_planks");
	    
	    ModelBakery.addVariantName(itemBlockBrickVariants, new String[] { "techplex:sharinga_planks" });
	    
	    Item itemBlockVariants = GameRegistry.findItem("techplex", "techplex_planks");
		TPWoodType[] enumtype = TPWoodType.values();
		for (int i = 0; i < enumtype.length; i++) {
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("techplex:" + enumtype[i] + "_planks", "inventory");
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockVariants, enumtype[i].getMetadata(), itemModelResourceLocation);
		}
	}
}
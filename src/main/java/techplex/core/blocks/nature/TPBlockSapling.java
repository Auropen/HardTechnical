package techplex.core.blocks.nature;

import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techplex.core.CreativeTabsTechPlex;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.registry.TPBlocks;
import techplex.core.worldGen.WorldGenSharingaTree;

public class TPBlockSapling extends BlockBush implements IGrowable {
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
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	public static final String BLOCKID = "techplex_sapling";

	public TPBlockSapling() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, TPWoodType.SHARINGA).withProperty(STAGE, 0));
		this.setCreativeTab(CreativeTabsTechPlex.tabTechPlexMain);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setStepSound(soundTypeGrass);
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		TPWoodType[] aenumtype = TPWoodType.values();
		int i = aenumtype.length;

		for (int j = 0; j < i; ++j) {
			TPWoodType enumtype = aenumtype[j];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
		}
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, pos, state, rand);
			}
		}
	}

	//markOrGrowMarked
	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (((Integer)state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		}else {
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!TerrainGen.saplingGrowTree(world, rand, pos)) return;
		
		switch (TPBlockSapling.SwitchEnumType.WOOD_TYPE_LOOKUP[((TPWoodType)state.getValue(TYPE)).ordinal()]) {
		case 1:
			WorldGenSharingaTree treeGen = new WorldGenSharingaTree(TPBlocks.techplex_log, TPBlocks.techplex_leaves, TPWoodType.SHARINGA.getMetadata(), TPWoodType.SHARINGA.getMetadata(), false, 4);
			treeGen.generate(world, rand, pos);
			break;
		case 2:
			//object = new WorldGenAnotherTree();
			break;
		}
	}

	/**
	 * Check whether the given BlockPos has a Sapling of the given type
	 */
	public boolean isTypeAt(World worldIn, BlockPos pos, TPWoodType type) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		return iblockstate.getBlock() == this && iblockstate.getValue(TYPE) == type;
	}

	/**
	 * Get the damage value that this Block should drop
	 */
	public int damageDropped(IBlockState state) {
		return ((TPWoodType)state.getValue(TYPE)).getMetadata();
	}

	/**
	 * Whether this IGrowable can grow
	 */
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return (double)worldIn.rand.nextFloat() < 0.45D;
	}

	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		this.grow(worldIn, pos, state, rand);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, TPWoodType.byMetadata(meta & 7)).withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}

	public static void inventoryRender() {
		Item itemBlockBrickVariants = GameRegistry.findItem("techplex", "techplex_sapling");
	    
	    ModelBakery.addVariantName(itemBlockBrickVariants, new String[] { "techplex:sharinga_sapling" });
	    
	    Item itemBlockVariants = GameRegistry.findItem("techplex", "techplex_sapling");
		TPWoodType[] enumtype = TPWoodType.values();
		for (int i = 0; i < enumtype.length; i++) {
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("techplex:" + enumtype[i] + "_sapling", "inventory");
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockVariants, enumtype[i].getMetadata(), itemModelResourceLocation);
		}
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | ((TPWoodType)state.getValue(TYPE)).getMetadata();
		i |= ((Integer)state.getValue(STAGE)).intValue() << 3;
		return i;
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {TYPE, STAGE});
	}

	static final class SwitchEnumType {
		static final int[] WOOD_TYPE_LOOKUP = new int[TPWoodType.values().length];

		static {
			try {
				WOOD_TYPE_LOOKUP[TPWoodType.SHARINGA.ordinal()] = 1;
			}
			catch (NoSuchFieldError var3) {
				;
			}
		}
	}
}
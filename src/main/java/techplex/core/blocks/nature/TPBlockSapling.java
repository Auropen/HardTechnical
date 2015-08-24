package techplex.core.blocks.nature;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techplex.TechPlex;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.items.ItemModMultiTexture;
import techplex.core.registry.TPBlocks;

public class TPBlockSapling extends BlockBush implements IGrowable {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", TPWoodType.class);
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	public TPBlockSapling(String modelName) {
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, TPWoodType.SHARINGA).withProperty(STAGE, Integer.valueOf(0)));
		this.setUnlocalizedName(modelName);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);

		System.out.println("INITIALIZING BLOCK: " +modelName);    
		GameRegistry.registerBlock(this, ItemModMultiTexture.class, modelName); // Register the Block using ItemModMultiTexture as the ItemBlock class

		((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() { // Set the Item's name function
			@Nullable
			public String apply(ItemStack input) {
				return TPWoodType.byMetadata(input.getItemDamage()).getName();
			}
		});
	}

	@SideOnly(Side.CLIENT)
	public void RegisterRenderer(String modelName) {

		System.out.println("REGISTERING BLOCK RENDERER: " +modelName);  
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(TechPlex.MODID+":" + modelName, "inventory"));
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

	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
		Object object = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
		int i = 0;
		int j = 0;
		boolean flag = false;

		switch (TPBlockSapling.SwitchEnumType.WOOD_TYPE_LOOKUP[((TPWoodType)state.getValue(TYPE)).ordinal()]) {
		case 1:
			object = new WorldGenSharingaTree(TPBlocks.techplex_log, TPBlocks.techplex_leaves, TPWoodType.SHARINGA.getMetadata(), TPWoodType.SHARINGA.getMetadata(), false, 5, 3, false);
			break;
		case 2:
			//object = new WorldGenPineTree(CustomPlanks.EnumType.PINE.getMetadata());
			break;
		}

		IBlockState iblockstate1 = Blocks.air.getDefaultState();

		if (flag) {
			worldIn.setBlockState(pos.add(i, 0, j), iblockstate1, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate1, 4);
			worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate1, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate1, 4);
		}else {
			worldIn.setBlockState(pos, iblockstate1, 4);
		}

		if (!((WorldGenerator)object).generate(worldIn, rand, pos.add(i, 0, j))) {
			if (flag) {
				worldIn.setBlockState(pos.add(i, 0, j), state, 4);
				worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
				worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
				worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
			}else {
				worldIn.setBlockState(pos, state, 4);
			}
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
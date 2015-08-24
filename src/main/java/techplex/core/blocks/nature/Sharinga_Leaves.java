package techplex.core.blocks.nature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class Sharinga_Leaves extends BlockLeaves {
	public static final String BLOCKID = "sharinga_leaves";
	
	public Sharinga_Leaves() {
        setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(this, 1, 0)));
	}

	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
	}

	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0;

		if (!((Boolean) state.getValue(DECAYABLE)).booleanValue()) {
			i |= 4;
		}

		if (((Boolean) state.getValue(CHECK_DECAY)).booleanValue()) {
			i |= 8;
		}

		return i;
	}
	
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { CHECK_DECAY, DECAYABLE });
	}
	
	@Override
	public EnumType getWoodType(int meta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return Blocks.leaves.getBlockLayer();
	}
}

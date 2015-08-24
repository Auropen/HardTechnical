package techplex.core.blocks.nature;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class Sharinga_Log extends BlockLog {
	public static final String BLOCKID = "sharinga_log";
	
	public Sharinga_Log() {
        setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
	}
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LOG_AXIS, 
				(meta & 4) == 0 ? EnumAxis.X : 
					(meta & 8) == 0 ? EnumAxis.Y : 
						(meta & 16) == 0 ? EnumAxis.Z : 
							EnumAxis.NONE);
	}

	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if (state.getValue(LOG_AXIS) == EnumAxis.X)
			i |= 4;
		
		if (state.getValue(LOG_AXIS) == EnumAxis.Y)
			i |= 8;
		
		if (state.getValue(LOG_AXIS) == EnumAxis.Z)
			i |= 16;
		
		if (state.getValue(LOG_AXIS) == EnumAxis.NONE)
			i |= 32;

		return i;
	}
	
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {LOG_AXIS});
	}
}

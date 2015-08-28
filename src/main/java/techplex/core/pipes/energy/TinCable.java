package techplex.core.pipes.energy;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techplex.TechPlex;
import techplex.core.pipes.BlockPipe;

public class TinCable extends BlockPipe {
public final static String BLOCKID = "tinCable";
	
	public TinCable() {
		super();
        setStepSound(Block.soundTypeCloth);
        setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTinCable();
	}
}

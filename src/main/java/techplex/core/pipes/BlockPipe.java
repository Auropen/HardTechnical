package techplex.core.pipes;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import techplex.core.CreativeTabsTechPlex;

public class BlockPipe extends BlockContainer{
	public final static String BLOCKID = "blockPipe";
	public final static float p = 1f/16f;
	
	public BlockPipe() {
		super(Material.ground);
		setUnlocalizedName("BlockPipe");
		setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
		setBlockBounds(p*4, p*4, p*4, 1-p*4, 1-p*4, 1-p*4);
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCable();
	}
}

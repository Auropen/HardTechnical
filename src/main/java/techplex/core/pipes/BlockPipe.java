package techplex.core.pipes;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import techplex.core.CreativeTabsTechPlex;

public class BlockPipe extends BlockContainer{
	public final static String BLOCKID = "blockPipe";
	public final static float p = 1f/16f;
	
	public BlockPipe() {
		super(Material.ground);
		setUnlocalizedName("BlockPipe");
		setCreativeTab(CreativeTabsTechPlex.tabTechPlexMachines);
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
	
	public boolean isSolidFullCube() {
		return false;
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPipe();
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		TileEntityPipe.updatePipes(world, neighbor);
		super.onNeighborChange(world, pos, neighbor);
	}
}

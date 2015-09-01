package techplex.core.blocks.machine;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techplex.TechPlex;
import techplex.core.handlers.GUIHandler;
import techplex.core.registry.TPBlocks;
import techplex.core.tileentity.TileEntityAlloy;

public class AlloyFurnace extends BlockMachine {
	public final static String BLOCKID = "alloyFurnace";
	public static boolean keepInventory = false;
	private boolean isActive = false;
	
	public AlloyFurnace(boolean active) {
		super(Material.iron);
		setUnlocalizedName(BLOCKID);
		setStepSound(soundTypeMetal);
		setHardness(4F);
		isActive = active;
	}
	
	@Override
	public int getRenderType() {
		return 3;
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return 80;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored() {
		return 0;
	}

	@Override
	public int getMaxEnergyStored() {
		return 20000;
	}
	
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityAlloy();
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!keepInventory) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if ((tileentity instanceof TileEntityAlloy)) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityAlloy)tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepInventory = true;
		if (active) {
			worldIn.setBlockState(pos, TPBlocks.alloyFurnace_active.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, TPBlocks.alloyFurnace_active.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		}
		else {
			worldIn.setBlockState(pos, TPBlocks.alloyFurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, TPBlocks.alloyFurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		}
		keepInventory = false;
		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (this.isActive) {
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
			double d0 = pos.getX() + 0.5D;
			double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = pos.getZ() + 0.5D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;
			switch (enumfacing) {
			case WEST: 
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case EAST: 
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case NORTH: 
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case SOUTH: 
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
			default:
				break;
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
			return true;
		
		playerIn.openGui(TechPlex.instance, GUIHandler.GUIID_ALLOYFURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}

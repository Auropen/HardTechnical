package techplex.core.pipes.energy;

import cofh.api.energy.IEnergyProvider;
import net.minecraft.util.EnumFacing;
import techplex.core.pipes.BlockPipe;

public abstract class EnergyPipe extends BlockPipe implements IEnergyProvider {
	@Override
	public boolean canConnectEnergy(EnumFacing facing) {
		return false;
	}
	
	@Override
	public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(EnumFacing facing) {
		return 0;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing facing) {
		return 0;
	}
}

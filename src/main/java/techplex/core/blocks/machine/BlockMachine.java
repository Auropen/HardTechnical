package techplex.core.blocks.machine;

import cofh.api.energy.IEnergyStorage;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;

public class BlockMachine extends BlockDirectional implements IEnergyStorage {
	public BlockMachine() {
		super(Material.iron);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergyStored() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyStored() {
		// TODO Auto-generated method stub
		return 0;
	}
}

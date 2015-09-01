package techplex.core.blocks.machine;

import cofh.api.energy.IEnergyStorage;
import net.minecraft.block.material.Material;
import techplex.core.blocks.TPBlockDirectional;

public abstract class BlockMachine extends TPBlockDirectional implements IEnergyStorage {
	protected BlockMachine(Material material) {
		super(material);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return 0;
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
		return 0;
	}
}

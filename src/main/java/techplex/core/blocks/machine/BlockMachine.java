package techplex.core.blocks.machine;

import cofh.api.energy.IEnergyStorage;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.util.EnumFacing;
import techplex.core.enumtypes.TPWoodType;

public abstract class BlockMachine extends BlockDirectional implements IEnergyStorage {
	public static final PropertyEnum VARIANT = PropertyEnum.create("facing", EnumFaceDirection);
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

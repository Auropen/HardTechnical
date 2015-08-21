package techplex.core.energy;

public interface IEnergyStorage {
	public int getEnergyAmount();
	public int getEnergyCapacity();
	public int getTransferRate();
	public int getTransferCriticalRate();
	public void setEnergyAmount(int amount);
	public void setEnergyCapacity(int capacity);
	public void setTransferRate(int rate);
	public void setTransferCriticalRate(int criticalRate);
}

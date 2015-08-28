package techplex.core.pipes;

import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class TileEntityCable extends TileEntity {
	//Pre-defined enumfacedirections, to use in a for loop
	private final static EnumFaceDirection[] DIRECTIONS = new EnumFaceDirection[] 
			{
					EnumFaceDirection.UP, EnumFaceDirection.DOWN, 
					EnumFaceDirection.NORTH, EnumFaceDirection.EAST,
					EnumFaceDirection.SOUTH, EnumFaceDirection.WEST
			};
	
	//Up, Down, North, East, South, West
	private EnumFaceDirection[] connections = new EnumFaceDirection[6];
	
	//Pre-defined relative block-positions, to match the enumfacedirections array
	private final static BlockPos[] POSITIONS = new BlockPos[] 
			{
					BlockPos.ORIGIN.up(), BlockPos.ORIGIN.down(), 	//0,1,0 & 0,-1,0
					BlockPos.ORIGIN.north(), BlockPos.ORIGIN.east(),//0,0,-1 & 1,0,0
					BlockPos.ORIGIN.south(), BlockPos.ORIGIN.west()	//0,0,1 & -1,0,0
			};

	public void updatePipes(boolean updateOnce) {
		for (int i = 0; i < DIRECTIONS.length; i++) {
			Object o = worldObj.getTileEntity(getPos().add(POSITIONS[i]));
			TileEntityCable tep = (o instanceof TileEntityCable) 
					? (TileEntityCable) o : null;
			if (tep != null) {
				getConnections()[i] = DIRECTIONS[i];
				if (!updateOnce) tep.updatePipes(true);
			}
		}
	}

	public EnumFaceDirection[] getConnections() {
		return connections;
	}
}

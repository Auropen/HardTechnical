package techplex.core.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import techplex.core.blocks.nature.TPBlockLeaves;
import techplex.core.blocks.nature.TPBlockLog;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.registry.TPBlocks;

public class WorldGenSharingaTree extends WorldGenAbstractTree {
	private final int minTreeHeight;
    private final Block wood;
    private final Block leaves;
    private final int metaWood;
    private final int metaLeaves;
	
	public WorldGenSharingaTree(Block wood, Block leaves, int metaWood, int metaLeaves, boolean doBlockNotify, int minTreeHeight) {
		super(doBlockNotify);
		this.minTreeHeight = minTreeHeight;
		this.wood = wood;
		this.leaves = leaves;
		this.metaWood = metaWood;
		this.metaLeaves = metaLeaves;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos treePos) {
		//Checks if can grow
		if (!canGrow(world, treePos))
			return false;
		
		//Generate Logs
		int treeHeight = rand.nextInt(3) + minTreeHeight;
		for (int y = 0; y < treeHeight; y++)
			world.setBlockState(treePos.up(y), wood.getDefaultState().withProperty(TPBlockLog.VARIANT, TPWoodType.byMetadata(metaWood)));
		
		//Generate Leaves
		float leavesRadius = 3f; //Radius size (max 5x5) from bottom-most layer of leaves
		float leavesRadiusDecrementer = -0.2f; //Decrements leaves radius size from bottom to up
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 5; x++) {
				for (int z = 0; z < 5; z++) {
					BlockPos leafPos = treePos.add(x-2, treeHeight-3 + y, z-2);
					if (blockInPosition(world, leafPos, Blocks.air)) {
						float distLeafToLog = (float) Math.hypot(treePos.getX() - leafPos.getX(), treePos.getZ() - leafPos.getZ());
						if (Math.ceil(distLeafToLog) < leavesRadius) {
							if (distLeafToLog > 1.1)
								world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.CHECK_DECAY, false).withProperty(TPBlockLeaves.VARIANT, TPWoodType.byMetadata(metaLeaves)));
							else
								world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.VARIANT, TPWoodType.byMetadata(metaLeaves)));
						}
						else {
							if (rand.nextDouble() < leavesRadius - distLeafToLog) { //Creates a bit distortion on the edge of the leaves
								if (distLeafToLog > 1.1)
									world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.CHECK_DECAY, false).withProperty(TPBlockLeaves.VARIANT, TPWoodType.byMetadata(metaLeaves)));
								else
									world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.VARIANT, TPWoodType.byMetadata(metaLeaves)));
							}
						}
					}
				}
			}
			leavesRadius -= leavesRadiusDecrementer;
			leavesRadiusDecrementer += 0.66f;
		}
		return true;
	}
	
	public boolean canGrow(World world, BlockPos treePos) {
		if ((world.getBlockState(treePos).getBlock() == Blocks.air &&
				!world.getBlockState(treePos.down()).getBlock().canSustainPlant(world, treePos, EnumFacing.UP, (IPlantable) Blocks.sapling)) ||
				!(world.getBlockState(treePos.down()).getBlock() == Blocks.dirt) ||
				!(world.getBlockState(treePos.down()).getBlock() == Blocks.grass) ||
				treePos.getY() > 256 - minTreeHeight + 4)
			return false;
		for (int y = 0; y < minTreeHeight + 3; y++) {
			for (int x = 0; x < 3; x++) {
				for (int z = 0; z < 3; z++) {
				if (!checkClearance(world, treePos.add(x - 1, y, z - 1)))
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkClearance(World world, BlockPos pos) {
		if (blockInPosition(world, pos, Blocks.air) ||
			blockInPosition(world, pos, Blocks.leaves) ||
			blockInPosition(world, pos, Blocks.leaves2) ||
			blockInPosition(world, pos, Blocks.tallgrass) ||
			blockInPosition(world, pos, Blocks.double_plant) ||
			blockInPosition(world, pos, Blocks.red_flower) ||
			blockInPosition(world, pos, Blocks.yellow_flower) ||
			blockInPosition(world, pos, TPBlocks.techplex_sapling))
			return true;
		return false;
	}
	

	public boolean blockInPosition(World world, BlockPos pos, Block b) {
		if (world.getBlockState(pos).getBlock().getClass() == b.getDefaultState().getBlock().getClass())
			return true;
		return false;
	}
}

package techplex.core.blocks.nature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.registry.TPBlocks;

public class WorldGenSharingaTree extends WorldGenAbstractTree {
	public WorldGenSharingaTree(Block techplexLog, Block techplexLeaves, int metadata, int metadata2, boolean b, int i, int j, boolean c) {
		super(b);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos treePos) {
		// TODO Auto-generated method stub
		int treeHeight = rand.nextInt(3) + 4;
		Block leaves = TPBlocks.techplex_leaves;
		Block log = TPBlocks.techplex_log;
		for (int y = 0; y < treeHeight; y++)
			world.setBlockState(treePos.up(y), log.getDefaultState().withProperty(TPBlockLog.VARIANT, TPWoodType.SHARINGA));
		
		//Generate Leaves
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 5; x++) {
				for (int z = 0; z < 5; z++) {
					BlockPos leafPos = treePos.add(x-2, treeHeight-3 + y, z-2);
					if (blockInPosition(world, leafPos, Blocks.air))
						if (Math.abs(x) > 1 || Math.abs(z) > 1)
							world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.CHECK_DECAY, false).withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
						else
							world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
				}
			}
		}
		for (int x = 0; x < 5; x++) {
			for (int z = 0; z < 5; z++) {
				BlockPos leafPos = treePos.add(x - 2, treeHeight-1, z - 2);
				if (blockInPosition(world, leafPos, Blocks.air))
					if (treePos.up(treeHeight-1).distanceSq(leafPos) < 3)
						if (Math.abs(x) > 1 || Math.abs(z) > 1)
							world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.CHECK_DECAY, false).withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
						else
							world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
			}
		}
		for (int x = 0; x < 3; x++) {
			for (int z = 0; z < 3; z++) {
				BlockPos leafPos = treePos.add(x - 1, treeHeight, z - 1);
				if (blockInPosition(world, leafPos, Blocks.air))
					if (treePos.up(treeHeight).distanceSq(leafPos) < 2)
						if (Math.abs(x) > 1 || Math.abs(z) > 1)
							world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.CHECK_DECAY, false).withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
						else
							world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
			}
		}
		return false;
	}
	
	public boolean checkClearance(World world, BlockPos pos) {
		if (blockInPosition(world, pos, Blocks.air) ||
			blockInPosition(world, pos, Blocks.leaves) ||
			blockInPosition(world, pos, Blocks.leaves2) ||
			blockInPosition(world, pos, Blocks.tallgrass) ||
			blockInPosition(world, pos, Blocks.double_plant) ||
			blockInPosition(world, pos, Blocks.red_flower) ||
			blockInPosition(world, pos, Blocks.yellow_flower))
			return true;
		return false;
	}
	

	public boolean blockInPosition(World world, BlockPos pos, Block b) {
		if (world.getBlockState(pos).getBlock().getClass() == b.getDefaultState().getBlock().getClass())
			return true;
		return false;
	}
}

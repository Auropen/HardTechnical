package techplex.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;
import techplex.core.blocks.nature.Sharinga_Leaves;
import techplex.core.blocks.nature.TPBlockLeaves;
import techplex.core.blocks.nature.TPBlockLog;
import techplex.core.enumtypes.TPWoodType;
import techplex.core.registry.TPBlocks;

public class WorldGeneration implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		generateOre(TPBlocks.copperOre, world, random, chunkX, chunkZ, 1, 8, 20, 32, 72, true);
		generateOre(TPBlocks.tinOre, world, random, chunkX, chunkZ, 1, 8, 18, 16, 56, true);
		//RubberTree (Sharinga)
		generateTree(TPBlocks.techplex_log, TPBlocks.techplex_leaves, world, random, chunkX, chunkZ, 3, 7, 0.05f, 4, 6, true);
	}
	
	private void generateOre(Block block, World world, Random rand, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, boolean generate) {
        if (!generate) return;
        
        int heightRange = maxY - minY;
        WorldGenMinable gen = new WorldGenMinable( block.getDefaultState(), minVienSize + rand.nextInt(maxVienSize - minVienSize));
        for (int i = 0; i < chance; i++) {
        	BlockPos bp = new BlockPos((chunkX * 16) + rand.nextInt(16), rand.nextInt(heightRange) + minY, (chunkZ * 16) + rand.nextInt(16));
            gen.generate(world, rand, bp);
        }
    }
	
	private void generateTree(Block log, Block leaves, World world, Random rand, int chunkX, int chunkZ, int minTreeAmount, int maxTreeAmount, float chance, int minHeight, int maxHeight, boolean generate) {
		//Should there be generated tree?
		if (!generate) 
			return;
					
		if (rand.nextFloat() > chance) 
			return;
		
		//Is the biome habitable for trees
		BlockPos centerChunk = new BlockPos(chunkX*16+8, 64, chunkZ*16+8);
		if (!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.birchForest) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.birchForestHills) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.forest) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.forestHills) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.coldTaiga) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.coldTaigaHills) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.jungle) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.jungleHills) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.swampland) &&
			!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.mushroomIsland))
			return;
		
		//To check distance between tree's
		//List<BlockPos> treePositions = new ArrayList<BlockPos>();
		
		int treeAmount = minTreeAmount + rand.nextInt(maxTreeAmount-minTreeAmount); 
		skipTree:
		for (int i = 0; i < treeAmount; i++) {
            //Check if tree can be placed
			BlockPos treePos = new BlockPos((chunkX * 16) + rand.nextInt(16), 0, (chunkZ * 16) + rand.nextInt(16));
			treePos = new BlockPos(treePos.getX(), world.getTopSolidOrLiquidBlock(treePos).getY(), treePos.getZ());
			
			if (treePos.getY() >= 64 && treePos.getY() <= 240) {
				if (world.getBlockState(treePos.down()).getBlock().canSustainPlant(world, treePos, net.minecraft.util.EnumFacing.UP, (IPlantable) Blocks.sapling)) {
					int treeHeight = minHeight + rand.nextInt(maxHeight-minHeight);
					
					for (int y = 0; y <= treeHeight; y++) {
						if (!checkClearance(world, treePos.add(1, y, 0)) ||
							!checkClearance(world, treePos.add(0, y, 1)) ||
							!checkClearance(world, treePos.add(-1, y, 0)) ||
							!checkClearance(world, treePos.add(0, y, -1)))
							continue skipTree;
					}
					
					//Generate Tree
					for (int y = 0; y < treeHeight; y++)
						world.setBlockState(treePos.up(y), log.getDefaultState().withProperty(TPBlockLog.VARIANT, TPWoodType.SHARINGA));
					
					//Generate Leaves
					for (int y = 0; y < 2; y++) {
						for (int x = 0; x < 5; x++) {
							for (int z = 0; z < 5; z++) {
								BlockPos leafPos = treePos.add(x-2, treeHeight-3 + y, z-2);
								if (blockInPosition(world, leafPos, Blocks.air))
									if (Math.abs(x) > 1 || Math.abs(z) > 1)
										world.setBlockState(leafPos, leaves.getDefaultState().withProperty(Sharinga_Leaves.CHECK_DECAY, false).withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
									else
										world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
							}
						}
					}
					for (int x = 0; x < 5; x++) {
						for (int z = 0; z < 5; z++) {
							BlockPos leafPos = treePos.add(x - 2, treeHeight-1, z - 2);
							if (blockInPosition(world, leafPos, Blocks.air))
								if (treePos.distanceSq(leafPos) < 2.5)
									if (Math.abs(x) > 1 || Math.abs(z) > 1)
										world.setBlockState(leafPos, leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
									else
										world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
						}
					}
					for (int x = 0; x < 3; x++) {
						for (int z = 0; z < 3; z++) {
							BlockPos leafPos = treePos.add(x - 1, treeHeight, z - 1);
							if (blockInPosition(world, leafPos, Blocks.air))
								if (treePos.distanceSq(leafPos) < 1)
									if (Math.abs(x) > 1 || Math.abs(z) > 1)
										world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.CHECK_DECAY, false).withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
									else
										world.setBlockState(leafPos, leaves.getDefaultState().withProperty(TPBlockLeaves.VARIANT, TPWoodType.SHARINGA));
						}
					}
				}
			}
        }
	}
	
	private boolean checkClearance(World world, BlockPos pos) {
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
	

	private boolean blockInPosition(World world, BlockPos pos, Block b) {
		if (world.getBlockState(pos).getBlock().getClass() == b.getDefaultState().getBlock().getClass())
			return true;
		return false;
	}
}

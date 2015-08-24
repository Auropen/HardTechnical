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
import techplex.core.blocks.CopperOre;
import techplex.core.blocks.TinOre;
import techplex.core.blocks.nature.Sharinga_Leaves;
import techplex.core.blocks.nature.Sharinga_Log;
import techplex.core.registry.BlockRegistry;

public class WorldGeneration implements IWorldGenerator {
	
	private final int[][][] shapeCircle = {{{0, 0, 1, 0, 0},{0, 1, 1, 1, 0},{1, 1, 1, 1, 1},{0, 1, 1, 1, 0},{0, 0, 1, 0, 0}},
										  {{0, 1, 0},{1, 1, 1},{0, 1, 0}}};
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		generateOre(BlockRegistry.REGBLOCKS.get(CopperOre.BLOCKID), world, random, chunkX, chunkZ, 1, 8, 20, 32, 72, true);
		generateOre(BlockRegistry.REGBLOCKS.get(TinOre.BLOCKID), world, random, chunkX, chunkZ, 1, 8, 18, 16, 56, true);
		//RubberTree (Sharinga)
		generateTree(BlockRegistry.REGBLOCKS.get(Sharinga_Log.BLOCKID), world, random, chunkX, chunkZ, 3, 7, 0.05f, 4, 6, true);
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
	
	private void generateTree(Block block, World world, Random rand, int chunkX, int chunkZ, int minTreeAmount, int maxTreeAmount, float chance, int minHeight, int maxHeight, boolean generate) {
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
						world.setBlockState(treePos.up(y), BlockRegistry.REGBLOCKS.get(Sharinga_Log.BLOCKID).getDefaultState());
					
					//Generate Leaves
					for (int y = 0; y < 2; y++) {
						for (int x = 0; x < 5; x++) {
							for (int z = 0; z < 5; z++) {
								if (blockInPosition(world, treePos.add(x-2, treeHeight-3 + y, z-2), Blocks.air))
									if (Math.abs(x) == 2 || Math.abs(z) == 2)
										world.setBlockState(treePos.add(x-2, treeHeight-3 + y, z-2), BlockRegistry.REGBLOCKS.get(Sharinga_Leaves.BLOCKID).getDefaultState().withProperty(Sharinga_Leaves.CHECK_DECAY, false));
									else
										world.setBlockState(treePos.add(x-2, treeHeight-3 + y, z-2), BlockRegistry.REGBLOCKS.get(Sharinga_Leaves.BLOCKID).getDefaultState());
							}
						}
					}
					for (int type = 0; type < shapeCircle.length; type++) {
						for (int x = 0; x < shapeCircle[type].length; x++) {
							for (int z = 0; z < shapeCircle[type].length; z++) {
								if (blockInPosition(world, treePos.add(x - (type + 1), treeHeight-1 + type, z - (type + 1)), Blocks.air))
									if (shapeCircle[type][x][z] == 1)
										if (Math.abs(x) == 2 || Math.abs(z) == 2)
											world.setBlockState(treePos.add(x - (2 - type), treeHeight-1 + type, z - (2 - type)), BlockRegistry.REGBLOCKS.get(Sharinga_Leaves.BLOCKID).getDefaultState().withProperty(Sharinga_Leaves.CHECK_DECAY, false));
										else
											world.setBlockState(treePos.add(x - (2 - type), treeHeight-1 + type, z - (2 - type)), BlockRegistry.REGBLOCKS.get(Sharinga_Leaves.BLOCKID).getDefaultState());
							}
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

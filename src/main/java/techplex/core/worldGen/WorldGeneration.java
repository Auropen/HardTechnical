package techplex.core.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import techplex.core.registry.TPBlocks;

public class WorldGeneration implements IWorldGenerator {
	WorldGenAbstractTree sharingaTreeGen;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (chunkGenerator instanceof ChunkProviderGenerate) {
			generateOre(TPBlocks.copperOre, world, random, chunkX, chunkZ, 1, 8, 20, 32, 72, true);
			generateOre(TPBlocks.tinOre, world, random, chunkX, chunkZ, 1, 8, 18, 16, 56, true);
			generateOre(TPBlocks.titaniumOre, world, random, chunkX, chunkZ, 1, 2, 10, 1, 36, true);
			//RubberTree (Sharinga)
			sharingaTreeGen = new WorldGenSharingaTree(TPBlocks.techplex_log, TPBlocks.techplex_leaves, chunkX, chunkZ, false, 4);
			generateTree(random, chunkX, chunkZ, world, sharingaTreeGen, 0.05f, 3, 7, true);
		}
		else if (chunkGenerator instanceof ChunkProviderHell) {
			
		}
		else if (chunkGenerator instanceof ChunkProviderEnd) {
			
		}
	}

	private void generateOre(Block block, World world, Random rand, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, boolean generate) {
		if (!generate) return;

		int heightRange = maxY - minY;
		WorldGenMinable gen = new WorldGenMinable( block.getDefaultState(), minVienSize + rand.nextInt(maxVienSize - minVienSize));
		for (int i = 0; i < chance; i++) {
			BlockPos bp = new BlockPos(chunkX*16+rand.nextInt(16), rand.nextInt(heightRange) + minY, chunkZ*16+rand.nextInt(16));
			gen.generate(world, rand, bp);
		}
	}

	private void generateTree(Random rand, int chunkX, int chunkZ, World world, WorldGenAbstractTree treeGen, float chance, int minTreeAmount, int maxTreeAmount, boolean generate) {
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
				!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.taiga) &&
				!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.coldTaiga) &&
				!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.coldTaigaHills) &&
				!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.jungle) &&
				!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.jungleHills) &&
				!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.swampland) &&
				!world.getBiomeGenForCoords(centerChunk).isEqualTo(BiomeGenBase.mushroomIsland))
			return;

		int treeAmount = minTreeAmount + rand.nextInt(maxTreeAmount-minTreeAmount); 
		for (int i = 0; i < treeAmount; i++) {
			//Check if tree can be placed
			BlockPos treePos = new BlockPos((chunkX * 16) + rand.nextInt(16), 0, (chunkZ * 16) + rand.nextInt(16));
			treePos = new BlockPos(treePos.getX(), world.getTopSolidOrLiquidBlock(treePos).getY(), treePos.getZ());
			if (treeGen instanceof WorldGenSharingaTree) {
				WorldGenSharingaTree currentGen = (WorldGenSharingaTree) treeGen;
				currentGen.generate(world, rand, treePos);
			}
		}
	}
}

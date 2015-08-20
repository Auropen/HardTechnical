package techplex.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import techplex.core.blocks.CopperOre;
import techplex.core.blocks.TinOre;
import techplex.core.registry.BlockRegistry;

public class WorldGeneration implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		generateOre(BlockRegistry.REGBLOCKS.get(CopperOre.BLOCKID), world, random, chunkX, chunkZ, 1, 8, 20, 32, 72, true);
		generateOre(BlockRegistry.REGBLOCKS.get(TinOre.BLOCKID), world, random, chunkX, chunkZ, 1, 8, 18, 16, 56, true);
	}
	
	private void generateOre(Block block, World world, Random rand, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, boolean generate) {
        if (generate) {
            int heightRange = maxY - minY;
            WorldGenMinable gen = new WorldGenMinable( block.getDefaultState(), minVienSize + rand.nextInt(maxVienSize - minVienSize));
            for (int i = 0; i < chance; i++) {
            	BlockPos bp = new BlockPos((chunkX * 16) + rand.nextInt(16), rand.nextInt(heightRange) + minY, (chunkZ * 16) + rand.nextInt(16));
                gen.generate(world, rand, bp);
            }
        }
    }
}

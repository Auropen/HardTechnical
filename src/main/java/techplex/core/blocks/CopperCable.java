package techplex.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class CopperCable extends Block {
public final static String BLOCKID = "copperCable";
	
	public CopperCable() {
		super(Material.leaves);
		setHardness(1.0F);
		setResistance(2.5f);
        setStepSound(Block.soundTypeCloth);
        setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
        setLightOpacity(0);
	}
	
	public void extendUp() {
		setBlockBounds(
				(float) getBlockBoundsMinX(), 
				(float) getBlockBoundsMinY(), 
				(float) getBlockBoundsMinZ(), 
				(float) getBlockBoundsMaxX(), 
				(float) getBlockBoundsMaxY() * 1.5f, 
				(float) getBlockBoundsMaxZ());
	}
	
	public void extendDown() {
		setBlockBounds(
				(float) getBlockBoundsMinX(), 
				(float) getBlockBoundsMinY(), 
				(float) getBlockBoundsMinZ(), 
				(float) getBlockBoundsMaxX(), 
				(float) getBlockBoundsMaxY(), 
				(float) getBlockBoundsMaxZ());
	}
}

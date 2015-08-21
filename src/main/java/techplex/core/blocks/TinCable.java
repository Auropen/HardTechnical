package techplex.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class TinCable extends Block {
public final static String BLOCKID = "tinCable";
	
	public TinCable() {
		super(Material.leaves);
		setHardness(1.0F);
		setResistance(2.5f);
        setStepSound(Block.soundTypeCloth);
        setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
        setLightOpacity(0);
	}
}

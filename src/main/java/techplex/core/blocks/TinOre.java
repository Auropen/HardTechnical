package techplex.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class TinOre extends Block{
	public static final String BLOCKID = "tin_ore";
	
	public TinOre() {
		super(Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
        setStepSound(Block.soundTypeStone);
        setHarvestLevel("pickaxe", 1);
        setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
	}
}

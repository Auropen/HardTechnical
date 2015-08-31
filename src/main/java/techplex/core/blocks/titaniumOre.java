package techplex.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class TitaniumOre extends Block {
	public static final String BLOCKID = "titanium_ore";
	
	public TitaniumOre() {
		super(Material.rock);
		setHardness(30.0F);
		setResistance(20.0f);
        setStepSound(Block.soundTypeStone);
        setHarvestLevel("pickaxe", 3);
        setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
	}
}

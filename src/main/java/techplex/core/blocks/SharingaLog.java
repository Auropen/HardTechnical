package techplex.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import techplex.TechPlex;
import techplex.core.CreativeTabsTechPlex;

public class SharingaLog extends Block {
	public static final String BLOCKID = "sharingaLog";
	
	public SharingaLog() {
		super(Material.wood);
		setHardness(2.0F);
		setResistance(3.5f);
        setStepSound(Block.soundTypeWood);
        setUnlocalizedName(TechPlex.MODID + "_" + BLOCKID);
        setCreativeTab(CreativeTabsTechPlex.tabTechPlex);
	}
}

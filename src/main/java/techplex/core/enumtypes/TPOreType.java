package techplex.core.enumtypes;

import net.minecraft.util.IStringSerializable;

public enum TPOreType implements IStringSerializable {
	COPPER(0, "copperOre"), TIN(1, "tinOre"), LEAD(2, "leadOre"), BAUXITE(3, "BauxiteOre"), TITANIUM(4, "TitaniumOre");

	private static final TPOreType[] META_LOOKUP;
	private final int meta;
	private final String name;
	private final String unlocalizedName;

	private TPOreType(int meta, String name) {
		this(meta, name, name);
	}

	private TPOreType(int meta, String name, String unlocalizedName) {
		this.meta = meta;
		this.name = name;
		this.unlocalizedName = unlocalizedName;
	}

	public int getMetadata() {
		return this.meta;
	}

	public String toString() {
		return this.name;
	}

	public static TPOreType byMetadata(int meta) {
		if ((meta < 0) || (meta >= META_LOOKUP.length))
			meta = 0;
		return META_LOOKUP[meta];
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUnlocalizedName() {
		return this.unlocalizedName;
	}

	static {
		META_LOOKUP = new TPOreType[values().length];

		TPOreType[] types = values();
		int numOfTypes = types.length;
		for (int i = 0; i < numOfTypes; i++)
		{
			TPOreType type = types[i];
			META_LOOKUP[type.getMetadata()] = type;
		}
	}
}

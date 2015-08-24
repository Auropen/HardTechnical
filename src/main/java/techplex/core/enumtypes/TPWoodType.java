package techplex.core.enumtypes;

import net.minecraft.util.IStringSerializable;

public enum TPWoodType implements IStringSerializable {
	SHARINGA(0, "sharinga");

	private static final TPWoodType[] META_LOOKUP;
	private final int meta;
	private final String name;
	private final String unlocalizedName;

	private TPWoodType(int meta, String name) {
		this(meta, name, name);
	}

	private TPWoodType(int meta, String name, String unlocalizedName) {
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

	public static TPWoodType byMetadata(int meta) {
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
		META_LOOKUP = new TPWoodType[values().length];
		TPWoodType[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; ++var2) {
			TPWoodType var3 = var0[var2];
			META_LOOKUP[var3.getMetadata()] = var3;
		}
	}
}

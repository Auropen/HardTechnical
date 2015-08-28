package techplex.core.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;
import techplex.core.items.BronzeDust;
import techplex.core.items.BronzeIngot;
import techplex.core.items.CopperDust;
import techplex.core.items.CopperIngot;
import techplex.core.items.GoldDust;
import techplex.core.items.IronDust;
import techplex.core.items.Resin;
import techplex.core.items.TinDust;
import techplex.core.items.TinIngot;

public class TPItems {
	public final static Item tinDust = new TinDust();
	public final static Item copperDust = new CopperDust();
	public final static Item bronzeDust = new BronzeDust();
	public final static Item ironDust = new IronDust();
	public final static Item goldDust = new GoldDust();
	
	public final static Item tinIngot = new TinIngot();	
	public final static Item copperIngot = new CopperIngot();	
	public final static Item bronzeIngot = new BronzeIngot();
	public static final Item resin = new Resin();
	
	public void register() {
		GameRegistry.registerItem(tinDust, TinDust.ITEMID);
		GameRegistry.registerItem(copperDust, CopperDust.ITEMID);
		GameRegistry.registerItem(bronzeDust, BronzeDust.ITEMID);
		GameRegistry.registerItem(ironDust, IronDust.ITEMID);
		GameRegistry.registerItem(goldDust, GoldDust.ITEMID);
		GameRegistry.registerItem(tinIngot, TinIngot.ITEMID);
		GameRegistry.registerItem(copperIngot, CopperIngot.ITEMID);
		GameRegistry.registerItem(bronzeIngot, BronzeIngot.ITEMID);
		GameRegistry.registerItem(resin, Resin.ITEMID);
	}

	public static void registerRenders() {
		registerRender(tinDust, TinDust.ITEMID);
		registerRender(copperDust, CopperDust.ITEMID);
		registerRender(bronzeDust, BronzeDust.ITEMID);
		registerRender(ironDust, IronDust.ITEMID);
		registerRender(goldDust, GoldDust.ITEMID);
		registerRender(tinIngot, TinIngot.ITEMID);
		registerRender(copperIngot, CopperIngot.ITEMID);
		registerRender(bronzeIngot, BronzeIngot.ITEMID);
		registerRender(resin, Resin.ITEMID);
	}
	
	private static void registerRender(Item i, String itemID) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(TechPlex.MODID+":"+itemID, "inventory"));
	}
}

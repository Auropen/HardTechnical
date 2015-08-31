package techplex.core.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import techplex.TechPlex;
import techplex.core.items.*;

public class TPItems {
	public final static Item tinDust = new TinDust();
	public final static Item copperDust = new CopperDust();
	public final static Item bronzeDust = new BronzeDust();
	public final static Item ironDust = new IronDust();
	public final static Item goldDust = new GoldDust();
	public final static Item tinIngot = new TinIngot();	
	public final static Item copperIngot = new CopperIngot();	
	public final static Item bronzeIngot = new BronzeIngot();
	public final static Item ironCogwheel = new IronCogwheel();
	public final static Item bronzeCogwheel = new BronzeCogwheel();
	public final static Item diamondCogwheel = new DiamondCogwheel();
	
	public static final Item resin = new Resin();
	public static final Item rubber = new Rubber();
	public static final Item rubberScrap = new RubberScrap();
	public static final Item plastic = new Plastic();
	public static final Item plasticBoard = new PlasticBoard();
	public static final Item circuitBoard = new CircuitBoard();
	public static final Item printedCircuitBoard = new PrintedCircuitBoard();
	public static final Item circuitPaste = new CircuitPaste();
	public static final Item circuitGlue = new CircuitGlue();
	
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
		GameRegistry.registerItem(rubber, Rubber.ITEMID);
		GameRegistry.registerItem(rubberScrap, RubberScrap.ITEMID);
		GameRegistry.registerItem(plastic, Plastic.ITEMID);
		GameRegistry.registerItem(plasticBoard, PlasticBoard.ITEMID);
		GameRegistry.registerItem(circuitBoard, CircuitBoard.ITEMID);
		GameRegistry.registerItem(printedCircuitBoard, PrintedCircuitBoard.ITEMID);
		GameRegistry.registerItem(circuitPaste, CircuitPaste.ITEMID);
		GameRegistry.registerItem(circuitGlue, CircuitGlue.ITEMID);
		GameRegistry.registerItem(ironCogwheel, IronCogwheel.ITEMID);
		GameRegistry.registerItem(bronzeCogwheel, BronzeCogwheel.ITEMID);
		GameRegistry.registerItem(diamondCogwheel, DiamondCogwheel.ITEMID);
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
		registerRender(rubber, Rubber.ITEMID);
		registerRender(rubberScrap, RubberScrap.ITEMID);
		registerRender(plastic, Plastic.ITEMID);
		registerRender(ironCogwheel, IronCogwheel.ITEMID);
		registerRender(bronzeCogwheel, BronzeCogwheel.ITEMID);
		registerRender(diamondCogwheel, DiamondCogwheel.ITEMID);
		registerRender(plasticBoard, PlasticBoard.ITEMID);
		registerRender(circuitBoard, CircuitBoard.ITEMID);
		registerRender(printedCircuitBoard, PrintedCircuitBoard.ITEMID);
		registerRender(circuitPaste, CircuitPaste.ITEMID);
		registerRender(circuitGlue, CircuitGlue.ITEMID);
	}
	
	private static void registerRender(Item i, String itemID) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(TechPlex.MODID+":"+itemID, "inventory"));
	}
}

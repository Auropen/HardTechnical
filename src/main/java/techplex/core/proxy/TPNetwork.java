package techplex.core.proxy;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class TPNetwork {
	public static void registerPackets(SimpleNetworkWrapper network)
	{
		network.registerMessage(OpenGUIPacket.Handler.class, OpenGUIPacket.class, 0, Side.SERVER);
	}
}

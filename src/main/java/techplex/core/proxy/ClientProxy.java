package techplex.core.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import techplex.core.pipes.TileEntityPipe;
import techplex.core.pipes.TileEntityPipeRender;

public class ClientProxy extends CommonProxy {
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new TileEntityPipeRender());
	}
}

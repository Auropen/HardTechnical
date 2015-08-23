package techplex.core.pipes;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPipeRender extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double translatetionX, double translatetionY, double translatetionZ, float f, int meta) {
		System.out.println("Trying to render pipe");
	}
}

package techplex.core.pipes;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import techplex.TechPlex;
import techplex.core.pipes.energy.TileEntityCopperCable;
import techplex.core.pipes.energy.TileEntityTinCable;

public class TileEntityPipeRender extends TileEntitySpecialRenderer {
	private ResourceLocation textureCopper = new ResourceLocation(TechPlex.MODID + ":textures/blocks/copperCable.png");
	private ResourceLocation textureTin = new ResourceLocation(TechPlex.MODID + ":textures/blocks/tinCable.png");
	private final static float p = 1f / 16f;

	@Override
	public void renderTileEntityAt(TileEntity te, double translationX, double translationY, double translationZ, float f, int i) {
		GL11.glPushMatrix();
		GL11.glTranslated(translationX, translationY, translationZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		if (te instanceof TileEntityTinCable)
			bindTexture(textureTin);
		else if (te instanceof TileEntityCopperCable)
			bindTexture(textureCopper);
		
		drawCore();
		TileEntityPipe pipe = (TileEntityPipe) te;
		pipe.updatePipes(true);
		for (EnumFaceDirection dir : pipe.getConnections())
			if (dir != null)
				drawConnector(dir);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	public void drawCore() {
		Tessellator tes = Tessellator.getInstance();
		WorldRenderer wr = tes.getWorldRenderer();
		wr.startDrawingQuads();
			{
			wr.addVertexWithUV(1-p*4, 	p*4, 	1-p*4, 	1-p*4, 1-p*4);
			wr.addVertexWithUV(1-p*4, 	1-p*4, 	1-p*4, 	1-p*4, p*4);
			wr.addVertexWithUV(p*4, 	1-p*4, 	1-p*4, 	p*4, p*4);
			wr.addVertexWithUV(p*4, 	p*4,	1-p*4,  p*4, 1-p*4);

			wr.addVertexWithUV(1-p*4, 	p*4,	p*4, 	1-p*4, 1-p*4);
			wr.addVertexWithUV(1-p*4, 	1-p*4, 	p*4, 	1-p*4, p*4);
			wr.addVertexWithUV(1-p*4, 	1-p*4, 	1-p*4, 	p*4, p*4);
			wr.addVertexWithUV(1-p*4, 	p*4, 	1-p*4, 	p*4, 1-p*4);

			wr.addVertexWithUV(p*4, 	p*4,	p*4, 	1-p*4, 1-p*4);
			wr.addVertexWithUV(p*4, 	1-p*4, 	p*4, 	1-p*4, p*4);
			wr.addVertexWithUV(1-p*4, 	1-p*4, 	p*4, 	p*4, p*4);
			wr.addVertexWithUV(1-p*4, 	p*4, 	p*4, 	p*4, 1-p*4);

			wr.addVertexWithUV(p*4, 	p*4, 	1-p*4, 	1-p*4, 1-p*4);
			wr.addVertexWithUV(p*4, 	1-p*4, 	1-p*4, 	1-p*4, p*4);
			wr.addVertexWithUV(p*4, 	1-p*4, 	p*4, 	p*4, p*4);
			wr.addVertexWithUV(p*4, 	p*4,	 p*4, 	p*4, 1-p*4);

			wr.addVertexWithUV(1-p*4, 	1-p*4, 	1-p*4, 	1-p*4, 1-p*4);
			wr.addVertexWithUV(1-p*4, 	1-p*4, 	p*4, 	1-p*4, p*4);
			wr.addVertexWithUV(p*4, 	1-p*4, 	p*4, 	p*4, p*4);
			wr.addVertexWithUV(p*4, 	1-p*4,	1-p*4, 	p*4, 1-p*4);

			wr.addVertexWithUV(p*4, 	p*4,	1-p*4, 	1-p*4, 1-p*4);
			wr.addVertexWithUV(p*4, 	p*4, 	p*4, 	1-p*4, p*4);
			wr.addVertexWithUV(1-p*4, 	p*4, 	p*4, 	p*4, p*4);
			wr.addVertexWithUV(1-p*4, 	p*4, 	1-p*4, 	p*4, 1-p*4);
		}
		tes.draw();
	}
	
	public void drawConnector(EnumFaceDirection dir) {
		
		Tessellator tes = Tessellator.getInstance();
		WorldRenderer wr = tes.getWorldRenderer();
		GL11.glPushMatrix();
		wr.startDrawingQuads();
		{
			GL11.glTranslatef(0.5f, 0.5f, 0.5f);
			switch(dir) {
			case UP:
				break;
			case DOWN:
				GL11.glRotatef(180, 1, 0, 0);
				break;
			case SOUTH:
				GL11.glRotatef(90, 1, 0, 0);
				break;
			case NORTH:
				GL11.glRotatef(270, 1, 0, 0);
				break;
			case WEST:
				GL11.glRotatef(90, 0, 0, 1);
				break;
			case EAST:
				GL11.glRotatef(270, 0, 0, 1);
				break;
			}
			GL11.glTranslated(-0.5f, -0.5f, -0.5f);
			
			wr.addVertexWithUV(1-p*4, 	1-p*4, 	1-p*4, 1-p*4, p*4);
			wr.addVertexWithUV(1-p*4, 	1, 		1-p*4, 1-p*4, 0);
			wr.addVertexWithUV(p*4, 	1, 		1-p*4, p*4, 0);
			wr.addVertexWithUV(p*4, 	1-p*4, 	1-p*4, p*4, p*4);

			wr.addVertexWithUV(p*4, 	1-p*4, 	p*4, 1-p*4, p*4);
			wr.addVertexWithUV(p*4, 	1, 		p*4, 1-p*4, 0);
			wr.addVertexWithUV(1-p*4, 	1,		p*4, p*4, 0);
			wr.addVertexWithUV(1-p*4, 	1-p*4, 	p*4, p*4, p*4);

			wr.addVertexWithUV(1-p*4, 	1-p*4, 	p*4, 1-p*4, p*4);
			wr.addVertexWithUV(1-p*4, 	1, 		p*4, 1-p*4, 0);
			wr.addVertexWithUV(1-p*4, 	1,		1-p*4, p*4, 0);
			wr.addVertexWithUV(1-p*4, 	1-p*4, 	1-p*4, p*4, p*4);

			wr.addVertexWithUV(p*4, 	1-p*4, 	1-p*4, 1-p*4, p*4);
			wr.addVertexWithUV(p*4, 	1,		1-p*4, 1-p*4, 0);
			wr.addVertexWithUV(p*4, 	1, 		p*4, p*4, 0);
			wr.addVertexWithUV(p*4, 	1-p*4, 	p*4, p*4, p*4);
		}
		tes.draw();
		GL11.glPopMatrix();
	}
}

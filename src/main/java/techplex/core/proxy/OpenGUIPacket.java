package techplex.core.proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import techplex.TechPlex;

public class OpenGUIPacket implements IMessage
{
	private int id;
	public static OpenGUIPacket instance = null;

	public OpenGUIPacket() { instance = this;}

	public OpenGUIPacket(int id) 
	{
		this.id = id;
		instance = this;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		id = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(id);
	}

	public static class Handler implements IMessageHandler<OpenGUIPacket, IMessage> 
	{
		@Override
		public IMessage onMessage(OpenGUIPacket message, MessageContext ctx) 
		{
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj; // or ctx.getServerHandler() on the server
			final EntityPlayer player = ctx.getServerHandler().playerEntity;
			mainThread.addScheduledTask(new Runnable() 
			{
				@Override
				public void run() 
				{
					player.openGui(TechPlex.instance, OpenGUIPacket.instance.id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
				}
			});
			return null; // no response in this case
		}
	}
}

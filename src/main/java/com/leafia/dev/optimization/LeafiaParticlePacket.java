package com.leafia.dev.optimization;

import com.hbm.main.MainRegistry;
import com.hbm.packet.PacketDispatcher;
import com.hbm.particle.ParticleRBMKMush;
import com.leafia.dev.optimization.bitbyte.LeafiaBuf;
import com.leafia.dev.optimization.diagnosis.RecordablePacket;
import com.llib.exceptions.LeafiaDevFlaw;
import com.llib.group.LeafiaSet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LeafiaParticlePacket extends RecordablePacket {
	/**
	 * The particle's NBT data buffer.
	 * <br>Position and motion information are written automatically and does not need manual setting.
	 */
	NBTTagCompound nbt = new NBTTagCompound();
	LeafiaParticle particle = null;
	static final LeafiaSet<LeafiaParticle> registry = new LeafiaSet<>();
	int supports = registry.size();
	public static class VanillaExt extends LeafiaParticle {
		public int mode;
		public int blockdust_block;
		public byte largeexplode_count;
		public float largeexplode_size;
		protected VanillaExt() {}
		public static VanillaExt Flame() { VanillaExt self = new VanillaExt(); self.mode = 0; return self; }
		public static VanillaExt Smoke() { VanillaExt self = new VanillaExt(); self.mode = 1; return self; }
		public static VanillaExt Volcano() { VanillaExt self = new VanillaExt(); self.mode = 2; return self; }
		public static VanillaExt Cloud() { VanillaExt self = new VanillaExt(); self.mode = 3; return self; }
		public static VanillaExt RedDust() { VanillaExt self = new VanillaExt(); self.mode = 4; return self; }
		public static VanillaExt BlueDust() { VanillaExt self = new VanillaExt(); self.mode = 5; return self; }
		public static VanillaExt GreenDust() { VanillaExt self = new VanillaExt(); self.mode = 6; return self; }
		public static VanillaExt LargeExplode(int count,float size) {
			VanillaExt self = new VanillaExt();
			self.mode = 7;
			self.largeexplode_count = (byte)count;
			self.largeexplode_size = size;
			return self;
		}
		public static VanillaExt TownAura() { VanillaExt self = new VanillaExt(); self.mode = 8; return self; }
		public static VanillaExt BlockDust(int blockId) {
			VanillaExt self = new VanillaExt();
			self.mode = 9;
			self.blockdust_block = blockId;
			return self;
		}
		protected VanillaExt(LeafiaBuf buf,NBTTagCompound nbt) {
			nbt.setString("type","vanillaExt");
			mode = buf.extract(4);
			switch(mode) {
				case 0: nbt.setString("mode","flame"); break;
				case 1: nbt.setString("mode","smoke"); break;
				case 2: nbt.setString("mode","volcano"); break;
				case 3: nbt.setString("mode","cloud"); break;
				case 4: nbt.setString("mode","reddust"); break;
				case 5: nbt.setString("mode","bluedust"); break;
				case 6: nbt.setString("mode","greendust"); break;
				case 7:
					nbt.setString("mode","largeexplode");
					nbt.setByte("count",buf.readByte());
					nbt.setFloat("size",buf.readFloat());
					break;
				case 8: nbt.setString("mode","townaura"); break;
				case 9:
					nbt.setString("mode","blockdust");
					nbt.setInteger("block",buf.readInt());
					break;
			}
		}
		@Override
		protected LeafiaParticle fromBits(LeafiaBuf buf,NBTTagCompound nbt) {
			return new VanillaExt(buf,nbt);
		}
		@Override
		protected void toBits(LeafiaBuf buf) {
			buf.insert(mode,4);
			switch(mode) {
				case 7:
					buf.writeByte(largeexplode_count);
					buf.writeFloat(largeexplode_size);
					break;
				case 9:
					buf.writeInt(blockdust_block);
					break;
			}
		}
	}
	public static class Sweat extends LeafiaParticle {
		int entityId;
		int blockId;
		int meta;
		int count;
		protected Sweat() {}
		public Sweat(Entity entity,IBlockState appearance,int amount) {
			this.blockId = Block.getIdFromBlock(appearance.getBlock());
			this.entityId = entity.getEntityId();
			this.meta = appearance.getBlock().getMetaFromState(appearance);
			this.count = amount;
		}
		@Override
		protected LeafiaParticle fromBits(LeafiaBuf buf,NBTTagCompound nbt) {
			nbt.setString("type","sweat");
			nbt.setInteger("entity",buf.readInt());
			nbt.setInteger("block",buf.readInt());
			nbt.setInteger("meta",buf.readInt());
			nbt.setInteger("count",buf.readInt());
			return new Sweat();
		}
		@Override
		protected void toBits(LeafiaBuf buf) {
			buf.writeInt(entityId);
			buf.writeInt(blockId);
			buf.writeInt(meta);
			buf.writeInt(count);
		}
	}
	public static class PinkRBMK extends LeafiaParticle {
		@Override
		protected LeafiaParticle fromBits(LeafiaBuf buf,NBTTagCompound nbt) { return new PinkRBMK(); }
		@Override
		protected void toBits(LeafiaBuf buf) {}
		@Override
		protected void emit(NBTTagCompound nbt) {
			World world = Minecraft.getMinecraft().world;
			ParticleRBMKMush mush = new ParticleRBMKMush(
					world,
					nbt.getDouble("posX"),nbt.getDouble("posY"),nbt.getDouble("posZ"),
					12
			);
			mush.isPink = true;
			Minecraft.getMinecraft().effectRenderer.addEffect(mush);
		}
	}

	static {
		for (Class<?> cl : LeafiaParticlePacket.class.getClasses()) {
			if (LeafiaParticle.class.isAssignableFrom(cl)) {
				try {
					registry.add(cl.asSubclass(LeafiaParticle.class).newInstance());
				} catch (InstantiationException | IllegalAccessException exception) {
					LeafiaDevFlaw flaw = new LeafiaDevFlaw("Exception during initialization of particles: "+exception.toString());
					flaw.setStackTrace(exception.getStackTrace());
					throw flaw;
				}
			}
		}
	}
	@Override
	protected void fromBits(LeafiaBuf buf) {
		nbt.setDouble("posX",buf.readDouble());
		nbt.setDouble("posY",buf.readDouble());
		nbt.setDouble("posZ",buf.readDouble());
		nbt.setDouble("mX",buf.readFloat());
		nbt.setDouble("mY",buf.readFloat());
		nbt.setDouble("mZ",buf.readFloat());
		supports = buf.readUnsignedByte();
		if (supports != registry.size()) {
			nbt = null;
			return;
		}
		this.particle = registry.get(buf.readUnsignedByte()).fromBits(buf,nbt);
	}
	@Override
	protected void toBits(LeafiaBuf buf) {
		buf.writeDouble(nbt.getDouble("posX"));
		buf.writeDouble(nbt.getDouble("posY"));
		buf.writeDouble(nbt.getDouble("posZ"));
		buf.writeFloat((float)nbt.getDouble("mX"));
		buf.writeFloat((float)nbt.getDouble("mY"));
		buf.writeFloat((float)nbt.getDouble("mZ"));
		buf.writeByte(registry.size());
		for (int i = 0; i < registry.size(); i++) {
			if (registry.get(i).getClass().equals(this.particle.getClass())) {
				buf.writeByte(i);
				this.particle.toBits(buf);
				return;
			}
		}
		throw new LeafiaDevFlaw("Invalid particle "+this.particle.getClass().getName());
	}
	private static abstract class LeafiaParticle {
		/**
		 * Method used for retrieving data from encoded packet
		 * @param buf The buffer to read data from
		 * @param nbt NBT data buffer. By default, this will be used for proxy.effectNT
		 * @return The particle type itself, required for identification
		 * <h3>Should NOT return <tt>this</tt>, because <tt>this</tt> is the one in the registry table
		 */
		protected abstract LeafiaParticle fromBits(LeafiaBuf buf,NBTTagCompound nbt);
		/**
		 * Used to write all customization into packet buffer.
		 * @param buf The buffer to write into
		 */
		protected abstract void toBits(LeafiaBuf buf);
		public final LeafiaParticlePacket packet(Vec3d pos,Vec3d motion) {
			LeafiaParticlePacket packet = new LeafiaParticlePacket();
			packet.nbt.setDouble("posX",pos.x);
			packet.nbt.setDouble("posY",pos.y);
			packet.nbt.setDouble("posZ",pos.z);
			packet.nbt.setDouble("mX",(float)motion.x);
			packet.nbt.setDouble("mY",(float)motion.y);
			packet.nbt.setDouble("mZ",(float)motion.z);
			packet.particle = this;
			return packet;
		}

		/**
		 * @return The maximum distance for the particle to render. Default is 340
		 */
		public double getDefaultRange() { return 340; }
		public final void emit(Vec3d pos,Vec3d motion,int dimension) { emit(pos,motion,dimension,getDefaultRange()); }
		public final void emit(Vec3d pos,Vec3d motion,int dimension,double range) {
			PacketDispatcher.wrapper.sendToAllAround(packet(pos,motion),new TargetPoint(dimension,pos.x,pos.y,pos.z,range));
		}
		/**
		 * Method used for how the particle is spawned.
		 * By default, it just uses proxy.effectNT
		 * @param nbt NBT data buffer
		 */
		protected void emit(NBTTagCompound nbt) {
			MainRegistry.proxy.effectNT(nbt);
		}
	}
	public static class Handler implements IMessageHandler<LeafiaParticlePacket,IMessage> {
		@Override
		public IMessage onMessage(LeafiaParticlePacket message,MessageContext ctx) {
			if (message.nbt == null) {
				ITextComponent reason = new TextComponentString("########").setStyle(new Style().setColor(TextFormatting.GOLD))
						.appendSibling(new TextComponentString(" NTM:LCE FATAL ERROR ").setStyle(new Style().setColor(TextFormatting.WHITE)))
						.appendSibling(new TextComponentString("########\nInvalid protocol on LeafiaParticlePacket").setStyle(new Style().setColor(TextFormatting.GOLD)))
						.appendSibling(
								new TextComponentString("\nThe server supports "+message.supports+" particles. Your client supports "+ registry.size()).setStyle(new Style().setColor(TextFormatting.WHITE))
						)
						.appendSibling(new TextComponentString("\n\nPossible reasons are:").setStyle(new Style().setColor(TextFormatting.AQUA)))
						.appendSibling(
								new TextComponentString("\n- Your client is outdated. Check for any updates on github (I don't add version numbers!)\n- Or the server is outdated. Contact server owner\n- Else perhaps it's some unpredictable fucks going on idfk").setStyle(new Style().setColor(TextFormatting.GRAY))
						);
				Minecraft.getMinecraft().player.connection.getNetworkManager().closeChannel(reason);
			} else
				message.particle.emit(message.nbt);
			return null;
		}
	}
}

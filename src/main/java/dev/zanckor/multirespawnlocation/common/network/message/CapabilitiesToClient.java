package dev.zanckor.multirespawnlocation.common.network.message;

import dev.zanckor.multirespawnlocation.common.network.handler.ClientHandler;
import dev.zanckor.multirespawnlocation.server.capability.PlayerData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CapabilitiesToClient {
    CompoundTag nbt;

    public CapabilitiesToClient(PlayerData playerData) {
        this.nbt = playerData.serializeNBT();
    }

    public void encodeBuffer(FriendlyByteBuf buf) {
        buf.writeNbt(nbt);
    }

    public CapabilitiesToClient(FriendlyByteBuf buf) {
        nbt = buf.readNbt();
    }

    public static void handle(CapabilitiesToClient msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHandler.capabilityToClient(msg.nbt));
        });

        ctx.get().setPacketHandled(true);
    }
}

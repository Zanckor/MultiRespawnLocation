package dev.zanckor.multirespawnlocation.common.network.message;

import dev.zanckor.multirespawnlocation.common.network.handler.ServerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChangeRespawnPoint {
    BlockPos blockPos;

    public ChangeRespawnPoint(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public void encodeBuffer(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
    }

    public ChangeRespawnPoint(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
    }

    public static void handle(ChangeRespawnPoint msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerHandler.changeRespawnPoint(ctx.get().getSender(), msg.blockPos);
        });

        ctx.get().setPacketHandled(true);
    }
}

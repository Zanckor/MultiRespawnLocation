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
    ResourceKey<Level> levelResourceKey;

    public ChangeRespawnPoint(BlockPos blockPos, ResourceKey<Level> levelResourceKey) {
        this.blockPos = blockPos;
        this.levelResourceKey = levelResourceKey;
    }

    public void encodeBuffer(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
        buf.writeResourceKey(levelResourceKey);
    }

    public ChangeRespawnPoint(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
        levelResourceKey = buf.readResourceKey(Registries.DIMENSION);
    }

    public static void handle(ChangeRespawnPoint msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerHandler.changeRespawnPoint(ctx.get().getSender(), msg.blockPos, msg.levelResourceKey);
        });

        ctx.get().setPacketHandled(true);
    }
}

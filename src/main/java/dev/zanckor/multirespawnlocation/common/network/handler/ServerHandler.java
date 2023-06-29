package dev.zanckor.multirespawnlocation.common.network.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class ServerHandler {

    public static void changeRespawnPoint(ServerPlayer player, BlockPos blockPos) {
        player.setRespawnPosition(player.level().dimension(), blockPos, player.getYRot(), true, false);
    }
}

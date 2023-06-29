package dev.zanckor.multirespawnlocation.common.network.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class ServerHandler {

    public static void changeRespawnPoint(ServerPlayer player, BlockPos blockPos, ResourceKey<Level> levelResourceKey) {
        player.setRespawnPosition(levelResourceKey, blockPos, player.getYRot(), true, false);
    }
}

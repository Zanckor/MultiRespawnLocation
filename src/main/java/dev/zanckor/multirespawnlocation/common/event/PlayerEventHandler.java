package dev.zanckor.multirespawnlocation.common.event;

import dev.zanckor.multirespawnlocation.api.respawnpointmanager.RespawnPoint;
import dev.zanckor.multirespawnlocation.api.respawnpointmanager.SerializablePos;
import dev.zanckor.multirespawnlocation.core.config.server.MultiRespawn;
import dev.zanckor.multirespawnlocation.server.player.PlayerData;
import dev.zanckor.multirespawnlocation.server.player.PlayerDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static dev.zanckor.multirespawnlocation.MultiRespawnLocation.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void openRespawnScreen(PlayerSleepInBedEvent e) {
        if (e.getEntity().isShiftKeyDown()) addRespawnPoint(e.getEntity(), e.getPos());
    }

    public static void addRespawnPoint(Player player, BlockPos blockPos) {
        final PlayerData PLAYER_DATA = PlayerDataProvider.getPlayer(player);
        final RespawnPoint RESPAWN_POINT = new RespawnPoint(new SerializablePos(blockPos), "respawn_" + PLAYER_DATA.getRespawnPoint().size(), player.level().dimension());

        if (PLAYER_DATA.getRespawnPoint().size() < MultiRespawn.RESPAWN_LOCATION_LIMIT.get()) {

            //Checks each actual respawnPoint, if player already has this as respawnPoint, do not add it again
            for (RespawnPoint respawnPoint : PLAYER_DATA.getRespawnPoint()) {

                if (respawnPoint.getPosition().toBlockPos().equals(RESPAWN_POINT.getPosition().toBlockPos()) || respawnPoint.getName().equals(RESPAWN_POINT.getName())) {
                    return;
                }
            }

            PLAYER_DATA.addRespawnPoint(RESPAWN_POINT);
        }
    }
}

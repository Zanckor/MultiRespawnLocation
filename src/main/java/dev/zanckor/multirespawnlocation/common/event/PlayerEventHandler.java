package dev.zanckor.multirespawnlocation.common.event;

import dev.zanckor.multirespawnlocation.api.respawnpointmanager.RespawnPoint;
import dev.zanckor.multirespawnlocation.api.respawnpointmanager.SerializablePos;
import dev.zanckor.multirespawnlocation.server.player.PlayerData;
import dev.zanckor.multirespawnlocation.server.player.PlayerDataProvider;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static dev.zanckor.multirespawnlocation.MultiRespawnLocation.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void addRespawnPoint(PlayerSleepInBedEvent e) {
        final PlayerData PLAYER_DATA = PlayerDataProvider.getPlayer(e.getEntity());
        final RespawnPoint RESPAWN_POINT = new RespawnPoint(new SerializablePos(e.getPos()), "respawn_" + PLAYER_DATA.getRespawnPoint().size());


        //Checks each actual respawnPoint, if player already has this as respawnPoint, do not add it again
        for (RespawnPoint respawnPoint : PLAYER_DATA.getRespawnPoint()) {

            if (respawnPoint.getPosition().toBlockPos().equals(RESPAWN_POINT.getPosition().toBlockPos()) ||
                    respawnPoint.getName().equals(RESPAWN_POINT.getName())) {
                return;
            }
        }

        PLAYER_DATA.addRespawnPoint(RESPAWN_POINT);
    }
}

package dev.zanckor.multirespawnlocation.client.screen;

import dev.zanckor.multirespawnlocation.common.network.SendQuestPacket;
import dev.zanckor.multirespawnlocation.common.network.message.ChangeRespawnPoint;
import dev.zanckor.multirespawnlocation.common.util.MCUtilClient;
import dev.zanckor.multirespawnlocation.server.capability.PlayerData;
import dev.zanckor.multirespawnlocation.server.capability.PlayerDataProvider;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;


public class MultiRespawnScreen extends DeathScreen {

    private int xButtonPosition, yButtonPosition;

    public MultiRespawnScreen(@Nullable Component component, boolean b) {
        super(component, b);
    }

    @Override
    protected void init() {
        super.init();
        final Player PLAYER = minecraft.player;
        final PlayerData PLAYER_DATA = PlayerDataProvider.getPlayer(PLAYER);


        if (PLAYER_DATA != null) {
            System.out.println(PLAYER_DATA.getRespawnPoint().size());
            xButtonPosition = width / 2 - ((PLAYER_DATA.getRespawnPoint().size() * 80) / 2);
            yButtonPosition = height / 3;

            //Creates button to respawn for each respawn point
            PLAYER_DATA.getRespawnPoint().forEach(respawnPoint -> {

                addRenderableWidget(MCUtilClient.createButton(xButtonPosition, yButtonPosition, 60, 20,
                        Component.literal(respawnPoint.getName()), button -> {
                            SendQuestPacket.TO_SERVER(new ChangeRespawnPoint(respawnPoint.getPosition().toBlockPos()));
                        }));

                xButtonPosition += 80;
            });
        }
    }
}
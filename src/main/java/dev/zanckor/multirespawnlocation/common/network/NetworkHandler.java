package dev.zanckor.multirespawnlocation.common.network;

import dev.zanckor.multirespawnlocation.common.network.message.CapabilitiesToClient;
import dev.zanckor.multirespawnlocation.common.network.message.ChangeRespawnPoint;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import static dev.zanckor.multirespawnlocation.MultiRespawnLocation.MOD_ID;

public class NetworkHandler {

    private static final String PROTOCOL_VERSION = "1.0";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "network"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int index = 0;

        CHANNEL.messageBuilder(ChangeRespawnPoint.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ChangeRespawnPoint::encodeBuffer).decoder(ChangeRespawnPoint::new)
                .consumerNetworkThread(ChangeRespawnPoint::handle).add();

        CHANNEL.messageBuilder(CapabilitiesToClient.class, index++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(CapabilitiesToClient::encodeBuffer).decoder(CapabilitiesToClient::new)
                .consumerNetworkThread(CapabilitiesToClient::handle).add();
    }
}

package dev.zanckor.multirespawnlocation.common.network.handler;

import dev.zanckor.multirespawnlocation.server.capability.PlayerData;
import dev.zanckor.multirespawnlocation.server.capability.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import static dev.zanckor.multirespawnlocation.MultiRespawnLocation.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class ClientHandler {

    public static void capabilityToClient(CompoundTag nbt) {
        PlayerData playerData = new PlayerData();
        playerData.deserializeNBT(nbt);

        PlayerDataProvider.getPlayer(Minecraft.getInstance().player).copyForRespawn(playerData);
    }
}

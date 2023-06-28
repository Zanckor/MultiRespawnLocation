package dev.zanckor.multirespawnlocation;

import com.mojang.logging.LogUtils;
import dev.zanckor.multirespawnlocation.server.player.PlayerDataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static dev.zanckor.multirespawnlocation.server.player.PlayerDataProvider.PLAYER_DATA_CAPABILITY;

@Mod(MultiRespawnLocation.MOD_ID)
public class MultiRespawnLocation {
    public static final String MOD_ID = "multirespawnlocation";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MultiRespawnLocation() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class EventCapabilityHandler {

        @SubscribeEvent
        public static void addCapabilityPlayer(AttachCapabilitiesEvent<Entity> e) {
            PlayerDataProvider capability = new PlayerDataProvider();
            e.addCapability(new ResourceLocation(MOD_ID + "player_capability"), capability);
        }


        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone e) {
            Player player = e.getEntity();

            if (e.isWasDeath()) {
                e.getOriginal().reviveCaps();
                e.getOriginal().getCapability(PLAYER_DATA_CAPABILITY).ifPresent(oldStore -> player.getCapability(PLAYER_DATA_CAPABILITY).ifPresent(newStore -> newStore.copyForRespawn(oldStore)));

                e.getOriginal().invalidateCaps();
            }
        }
    }
}

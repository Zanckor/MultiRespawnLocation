package dev.zanckor.multirespawnlocation.client.event;

import dev.zanckor.multirespawnlocation.client.screen.MultiRespawnScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static dev.zanckor.multirespawnlocation.MultiRespawnLocation.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void replaceRespawnScreen(ScreenEvent.Opening e) {
        if (e.getScreen() instanceof DeathScreen) {

            e.setNewScreen(new MultiRespawnScreen(Component.literal("multiRespawnScreen.title"),
                    Minecraft.getInstance().level.getLevelData().isHardcore()));
        }
    }
}

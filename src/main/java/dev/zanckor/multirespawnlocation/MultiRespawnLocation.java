package dev.zanckor.multirespawnlocation;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MultiRespawnLocation.MODID)
public class MultiRespawnLocation {

    public static final String MODID = "multirespawnlocation";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MultiRespawnLocation() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

    }
}

package dev.zanckor.multirespawnlocation.core.config.server;

import net.minecraftforge.common.ForgeConfigSpec;

public class MultiRespawn {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.ConfigValue<Integer> RESPAWN_LOCATION_LIMIT;

    static {
        BUILDER.push("MultiRespawn configuration");

        RESPAWN_LOCATION_LIMIT = BUILDER.comment("Limit of respawn locations able to store.")
                .define("Respawn Location Limit", 4);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}

package dev.zanckor.multirespawnlocation.api.respawnpointmanager;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.io.Serializable;

public class RespawnPoint implements Serializable {
    final private static long serialVersionUID = -5424499292399560161L;
    final private SerializablePos RESPAWN_POS;
    final private String RESPAWN_NAME;
    final private ResourceKey<Level> levelResourceKey;

    public RespawnPoint(SerializablePos respawnPosition, String respawnName, ResourceKey<Level> levelResourceKey) {
        this.RESPAWN_POS = respawnPosition;
        RESPAWN_NAME = respawnName;
        this.levelResourceKey = levelResourceKey;
    }

    public SerializablePos getPosition() {
        return RESPAWN_POS;
    }

    public String getName() {
        return RESPAWN_NAME;
    }

    public ResourceKey<Level> getLevelResourceKey() {
        return levelResourceKey;
    }
}

package dev.zanckor.multirespawnlocation.api.respawnpointmanager;

import java.io.Serializable;

public class RespawnPoint implements Serializable {
    final private static long serialVersionUID = -5424499292399560161L;
    final private SerializablePos RESPAWN_POS;
    final private String RESPAWN_NAME;
    public RespawnPoint(SerializablePos respawnPosition, String respawnName) {
        this.RESPAWN_POS = respawnPosition;
        RESPAWN_NAME = respawnName;
    }

    public SerializablePos getPosition() {
        return RESPAWN_POS;
    }
    public String getName(){ return RESPAWN_NAME; }
}

package dev.zanckor.multirespawnlocation.api.respawnpointmanager;

import net.minecraft.core.BlockPos;

import java.io.Serializable;

public class SerializablePos implements Serializable {
    final int x, y, z;

    public SerializablePos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SerializablePos(BlockPos blockPos) {
        this.x = blockPos.getX();
        this.y = blockPos.getY();
        this.z = blockPos.getZ();
    }

    public BlockPos toBlockPos(){
        return new BlockPos(x, y, z);
    }
}
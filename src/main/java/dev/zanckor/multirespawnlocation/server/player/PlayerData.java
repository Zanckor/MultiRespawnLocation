package dev.zanckor.multirespawnlocation.server.player;

import dev.zanckor.multirespawnlocation.api.respawnpointmanager.RespawnPoint;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerData implements INBTSerializable<CompoundTag> {
    private List<RespawnPoint> respawnPoint = new ArrayList<>();

    public List<RespawnPoint> getRespawnPoint() {
        return respawnPoint;
    }

    public void addRespawnPoint(RespawnPoint respawnPoint) {
        this.respawnPoint.add(respawnPoint);
    }

    public void removeRespawnPoint(int positionOnList){
        respawnPoint.remove(positionOnList);
    }
    public void removeRespawnPoint(String respawnName){
        this.respawnPoint.removeIf(respawnPoint -> respawnPoint.getName().equals(respawnName));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putByteArray("respawnPoint", serializeList(respawnPoint));

        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        respawnPoint = deserializeList(nbt.getByteArray("respawnPoint"));
    }

    public void copyForRespawn(PlayerData oldStore) {
        respawnPoint = oldStore.respawnPoint;
    }


    private byte[] serializeList(List<?> list) {
        try {
            ByteArrayOutputStream byteOStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteOStream);

            outputStream.writeObject(list);
            return byteOStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<RespawnPoint> deserializeList(byte[] byteArray) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
            ObjectInputStream is = new ObjectInputStream(in);
            return (List<RespawnPoint>) is.readObject();

        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}

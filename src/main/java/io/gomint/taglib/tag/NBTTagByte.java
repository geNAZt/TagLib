package io.gomint.taglib.tag;

import io.gomint.taglib.NBTReader;
import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTTagByte extends NBTTag {
    @Getter private byte value;

    @Override
    public void read( NBTReader nbtReader ) {
        this.value = nbtReader.readByte();
    }

    @Override
    public void write( ByteBuffer byteBuffer ) {

    }
}

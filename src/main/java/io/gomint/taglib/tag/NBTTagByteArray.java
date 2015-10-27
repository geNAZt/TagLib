package io.gomint.taglib.tag;

import io.gomint.taglib.NBTReader;
import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTTagByteArray extends NBTTag {
    @Getter private byte[] value;

    @Override
    public void read( NBTReader nbtReader ) {
        int amountOfBytes = nbtReader.readInteger();
        this.value = new byte[amountOfBytes];
        for ( int i = 0; i < amountOfBytes; i++ ) {
            this.value[i] = nbtReader.readByte();
        }
    }

    @Override
    public void write( ByteBuffer byteBuffer ) {

    }
}

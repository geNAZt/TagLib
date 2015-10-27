package io.gomint.taglib.tag;

import io.gomint.taglib.NBTReader;
import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTTagLong extends NBTTag {
    @Getter private long value;

    @Override
    public void read( NBTReader nbtReader ) {
        this.value = nbtReader.readLong();
    }

    @Override
    public void write( ByteBuffer byteBuffer ) {

    }
}

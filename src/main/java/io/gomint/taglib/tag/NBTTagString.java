package io.gomint.taglib.tag;

import io.gomint.taglib.NBTReader;
import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTTagString extends NBTTag {
    @Getter private String value;

    @Override
    public void read( NBTReader nbtReader ) {
        this.value = nbtReader.readString();
    }

    @Override
    public void write( ByteBuffer byteBuffer ) {

    }
}

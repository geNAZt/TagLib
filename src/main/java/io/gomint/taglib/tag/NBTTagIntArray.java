package io.gomint.taglib.tag;

import io.gomint.taglib.NBTReader;
import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTTagIntArray extends NBTTag {
    @Getter private int[] value;

    @Override
    public void read( NBTReader nbtReader ) {
        int amountOfElements = nbtReader.readInteger();
        this.value = new int[amountOfElements];
        for ( int i = 0; i < amountOfElements; i++ ) {
            this.value[i] = nbtReader.readInteger();
        }
    }

    @Override
    public void write( ByteBuffer byteBuffer ) {

    }
}

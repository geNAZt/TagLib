package io.gomint.taglib.tag;

import io.gomint.taglib.NBTReader;

import java.nio.ByteBuffer;

/**
 * @author geNAZt
 * @version 1.0
 */
public abstract class NBTTag {
    public abstract void read( NBTReader nbtReader );
    public abstract void write( ByteBuffer byteBuffer );
}

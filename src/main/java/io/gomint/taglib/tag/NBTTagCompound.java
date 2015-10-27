package io.gomint.taglib.tag;

import io.gomint.taglib.NBTReader;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTTagCompound extends NBTTag {
    @Getter private Map<String, NBTTag> value;

    @Override
    public void read( NBTReader nbtReader ) {
        nbtReader.ensureInBound( 4 + 4 + 4 + 4 );   // HashMap construction has 3 ints and a float
        this.value = new HashMap<>();

        NBTTag tag = null;
        byte tagId;

        do {
            tagId = nbtReader.readByte();
            if ( tagId != 0 ) {
                String name = nbtReader.readString();
                tag = nbtReader.parseNBTTag( tagId );
                this.value.put( name, tag );
            }
        } while ( tagId != 0 && tag != null );
    }

    @Override
    public void write( ByteBuffer byteBuffer ) {

    }
}

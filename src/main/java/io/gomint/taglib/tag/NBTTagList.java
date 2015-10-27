package io.gomint.taglib.tag;

import io.gomint.taglib.NBTReader;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTTagList extends NBTTag {
    @Getter private List<NBTTag> value;

    @Override
    public void read( NBTReader nbtReader ) {
        nbtReader.ensureInBound( 4 ); // Special case since the list uses 4 bytes on heap on construction

        this.value = new ArrayList<>();
        byte listType = nbtReader.readByte();
        int amountOfElements = nbtReader.readInteger();

        for ( int i = 0; i < amountOfElements; i++ ) {
            this.value.add( nbtReader.parseNBTTag( listType ) );
        }
    }

    @Override
    public void write( ByteBuffer byteBuffer ) {

    }
}

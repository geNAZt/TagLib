package io.gomint.taglib;

import io.gomint.taglib.tag.*;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTReader {
    public static final int DEFAULT_READ_MAX = 2 << 20;
    private int maxRead;
    private int currentRead = 0;
    private ByteBuffer byteBuffer;
    private Map<String, NBTTag> nbtTags = new HashMap<>();

    public NBTReader( ByteBuffer byteBuffer ) {
        this.maxRead = DEFAULT_READ_MAX;
        this.byteBuffer = byteBuffer;
        this.parse();
    }

    public void ensureInBound( int needed ) {
        if ( currentRead + needed > maxRead ) {
            throw new IllegalStateException( "Want to read " + needed + " bytes. But there are only " + ( maxRead - currentRead ) + " bytes allowed" );
        }

        this.currentRead += needed;
    }

    public NBTTag parseNBTTag( byte tagType ) {
        switch ( tagType ) {
            case 0:
                return null;
            case 1:
                NBTTagByte nbtTagByte = new NBTTagByte();
                nbtTagByte.read( this );
                return nbtTagByte;
            case 2:
                NBTTagShort nbtTagShort = new NBTTagShort();
                nbtTagShort.read( this );
                return nbtTagShort;
            case 3:
                NBTTagInteger nbtTagInteger = new NBTTagInteger();
                nbtTagInteger.read( this );
                return nbtTagInteger;
            case 4:
                NBTTagLong nbtTagLong = new NBTTagLong();
                nbtTagLong.read( this );
                return nbtTagLong;
            case 5:
                NBTTagFloat nbtTagFloat = new NBTTagFloat();
                nbtTagFloat.read( this );
                return nbtTagFloat;
            case 6:
                NBTTagDouble nbtTagDouble = new NBTTagDouble();
                nbtTagDouble.read( this );
                return nbtTagDouble;
            case 7:
                NBTTagByteArray nbtTagByteArray = new NBTTagByteArray();
                nbtTagByteArray.read( this );
                return nbtTagByteArray;
            case 8:
                NBTTagString nbtTagString = new NBTTagString();
                nbtTagString.read( this );
                return nbtTagString;
            case 9:
                NBTTagList nbtTagList = new NBTTagList();
                nbtTagList.read( this );
                return nbtTagList;
            case 10:
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.read( this );
                return nbtTagCompound;
            case 11:
                NBTTagIntArray nbtTagIntArray = new NBTTagIntArray();
                nbtTagIntArray.read( this );
                return nbtTagIntArray;
        }

        return null;
    }

    private void parse() {
        NBTTag tag;
        byte tagId;

        do {
            tagId = readByte();
            if ( tagId == 0 ) {
                continue;
            }

            String name = readString();
            tag = parseNBTTag( tagId );
            nbtTags.put( name, tag );
        } while ( tagId != 0 && byteBuffer.remaining() > 0 );
    }

    public byte readByte() {
        ensureInBound( 1 );
        return byteBuffer.get();
    }

    public short readShort() {
        ensureInBound( 2 );
        return byteBuffer.getShort();
    }

    public String readString() {
        short strLen = readShort();
        ensureInBound( strLen );

        byte[] strBuffer = new byte[strLen];
        byteBuffer.get( strBuffer );
        return new String( strBuffer );
    }

    public int readInteger() {
        ensureInBound( 4 );
        return byteBuffer.getInt();
    }

    public long readLong() {
        ensureInBound( 8 );
        return byteBuffer.getLong();
    }

    public float readFloat() {
        ensureInBound( 4 );
        return byteBuffer.getFloat();
    }

    public double readDouble() {
        ensureInBound( 8 );
        return byteBuffer.getDouble();
    }
}

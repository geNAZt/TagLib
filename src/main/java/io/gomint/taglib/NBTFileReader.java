package io.gomint.taglib;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.GZIPInputStream;

/**
 * @author geNAZt
 * @version 1.0
 */
public class NBTFileReader {
    private NBTReader nbtReader;

    public NBTFileReader( String file, boolean compressed ) {
        try {
            ByteBuffer readBuffer;
            if ( compressed ) {
                InputStream is = new GZIPInputStream( new FileInputStream( new File( file ) ) );
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] data = new byte[16384];

                while ( ( nRead = is.read( data, 0, data.length ) ) != -1 ) {
                    buffer.write( data, 0, nRead );
                }

                buffer.flush();
                readBuffer = ByteBuffer.wrap( buffer.toByteArray() );
            } else {
                RandomAccessFile aFile = new RandomAccessFile( file, "r" );
                FileChannel inChannel = aFile.getChannel();
                readBuffer = inChannel.map( FileChannel.MapMode.READ_ONLY, 0, inChannel.size() );
                ( (MappedByteBuffer) readBuffer ).load();
            }

            // Read / parse the NBT Data
            this.nbtReader = new NBTReader( readBuffer );
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        System.out.println( this.nbtReader );
    }
}

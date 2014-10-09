package lz4;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import net.jpountz.lz4.*;

public class Plugin_main {
    
    LZ4Factory factory = LZ4Factory.fastestJavaInstance();
    
    public byte[] compress_byte(byte[] data) throws UnsupportedEncodingException {   
        LZ4Compressor compressor = factory.fastCompressor();
        //Given the max decompressed length , get maxCompressed Length
        int maxCompressedLength=compressor.maxCompressedLength(data.length);
        
        //int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
        System.out.println(" Max compressed length is: "+ maxCompressedLength);
        
        //Using max compressed length allocate memory to compressed length, and compress it
        byte[] compressed = new byte[maxCompressedLength];
        int compressedLength=compressor.compress(data,0, data.length, compressed, 0,maxCompressedLength);
        
        byte[] finalCompressed=Arrays.copyOf(compressed,compressedLength);
        System.out.println("Final compress Data length is: "+finalCompressed.length);
        return finalCompressed;    
    }
    
    public byte[] compressString(String input) throws UnsupportedEncodingException {   
        int orignalLenth=input.length();
        byte[] data=input.getBytes("UTF-8");
        LZ4Compressor compressor = factory.fastCompressor();
        //Given the max decompressed length , get maxCompressed Length
        int maxCompressedLength=compressor.maxCompressedLength(orignalLenth);
        
        //int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
        System.out.println(" Max compressed length is: "+ maxCompressedLength);
        
        //Using max compressed length allocate memory to compressed length, and compress it
        byte[] compressed = new byte[maxCompressedLength];
        int compressedLength=compressor.compress(data,0, data.length, compressed, 0,maxCompressedLength);
        
        byte[] finalCompressed=Arrays.copyOf(compressed,compressedLength);
        System.out.println("Final compress Data length is: "+finalCompressed.length);
        return finalCompressed;    
    }
    
    
    public byte[] decompress_rbyte(byte[] input,int origLen) {
        
        //Get decompressor
        LZ4FastDecompressor decompressor =factory.fastDecompressor();      
        
        //Restored data length has to be equal to length of original data
        byte[] restored = new byte[origLen];
        
        //returns the number of bytes read to restore the original input      
        int compressedLength2 = decompressor.decompress(input, 0, restored, 0, origLen);
        
        return restored;
    } 
    
    
    public String decompress_rstring(byte[] input,int origLen) {
        
        //Get decompressor
        LZ4FastDecompressor decompressor =factory.fastDecompressor();      
        
        //Restored data length has to be equal to length of original data
        byte[] restored = new byte[origLen];
        
        //returns the number of bytes read to restore the original input      
        int compressedLength2 = decompressor.decompress(input, 0, restored, 0, origLen);

        System.out.println(" Restored data is: "+new String(restored, StandardCharsets.UTF_8)+" and length: "+restored.length);
        return new String(restored, StandardCharsets.UTF_8);        
    } 
    
    /*
    public String function() throws UnsupportedEncodingException {
        byte[] data = "mmmmmm3333333333ssssuuuuumiiiiittt000000111fsdfsfsdsdfsdfs112222222t".getBytes("UTF-8");
        System.out.println(" Original data is: "+data);
        
        //final decompressLength has to be equal to the data
        final int decompressedLength = data.length;
        System.out.println("Final decompresstion Length would be: "+decompressedLength);
        
        // compress data
        LZ4Compressor compressor = factory.fastCompressor();
        
        //Given the max decompressed length , get maxCompressed Length
        int maxCompressedLength=compressor.maxCompressedLength(decompressedLength);
        
        //int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
        System.out.println(" Max compressed length is: "+ maxCompressedLength);
        
        //Using max compressed length allocate memory to compressed length, and compress it
        byte[] compressed = new byte[maxCompressedLength];
        int compressedLength=compressor.compress(data,0, data.length, compressed, 0,maxCompressedLength);
        
        byte[] finalCompressed=Arrays.copyOf(compressed,compressedLength);
        System.out.println("Final compress Data length is: "+finalCompressed.length);
        
        //Get decompressor
        LZ4FastDecompressor decompressor =factory.fastDecompressor();      
        
        //Restored data length has to be equal to length of original data
        byte[] restored = new byte[decompressedLength];
        
        //returns the number of bytes read to restore the original input      
        int compressedLength2 = decompressor.decompress(finalCompressed, 0, restored, 0, decompressedLength);
  
        System.out.println(" Compressed data is: "+new String(finalCompressed, StandardCharsets.UTF_8));
        System.out.println(" Restored data is: "+new String(restored, StandardCharsets.UTF_8)+" and length: "+restored.length);
        System.out.println(" Original data was: "+new String(data, StandardCharsets.UTF_8)+" and length: "+data.length);
        return new String(restored, StandardCharsets.UTF_8);
    }
    
    
    
    public static void main(String[] args) throws UnsupportedEncodingException {            
        Plugin_main m=new Plugin_main();
        //m.function(new byte[500]);     
    } */   
}



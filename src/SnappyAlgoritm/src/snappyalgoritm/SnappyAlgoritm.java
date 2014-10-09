/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snappyalgoritm;

/**
 *
 * @author msumit
 */
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;
import org.xerial.snappy.*;
import org.xerial.snappy.Snappy;
public class SnappyAlgoritm {

    /**
     * @param args the command line arguments
     */
    public byte[] compress(String input) throws IOException
    {
         byte[] compressed = Snappy.compress(input.getBytes("UTF-32"));
         return compressed;
    }
    public String decompress(byte[] input) throws IOException
    {   
        
        byte[] uncompressed = Snappy.uncompress(input);
        
        /*
        byte[] compressed=input.getBytes("UTF-8");
        if(Snappy.isValidCompressedBuffer(compressed)) {
            byte[] uncompressed = Snappy.uncompress(compressed);
            String result = new String(uncompressed, "UTF-8");
            return result;
        }
        */
        return new String(uncompressed,"UTF-8");
        
    }
    public byte[] compress64(String input) throws IOException
    {
        byte[] message = input.getBytes();
        String encoded = DatatypeConverter.printBase64Binary(message);
        return compress(encoded);
        
    }
    
    public String decompressu8(String encoded) throws IOException
    {
        byte[] decoded = encoded.getBytes("UTF-8");
        return decompress(decoded);
    }
    
    public String decompressu32(String encoded) throws IOException
    {
        byte[] decoded = encoded.getBytes("UTF-32");
        return decompress(decoded);
    }
    
    public String decompressb64(String encoded) throws IOException
    {
        byte[] decoded = Base64.decode(encoded);
        return decompress(decoded);
    }
    
    
    
    
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
     + "Snappy, a fast compresser/decompresser.";
        byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
        
        String result1 = new String(compressed, "UTF-8");
        System.out.println(result1);
          System.out.println(result1.length());
          
        byte[] uncompressed = Snappy.uncompress(compressed);

        String result = new String(uncompressed, "UTF-8");
        System.out.println(result);
        System.out.println(result.length());
        
        
        //SnappyAlgoritm s= new SnappyAlgoritm();
        //Base64.encode("Hello".getBytes());
        
        //s.decompress64(new String("Hello",);
        
    }
}

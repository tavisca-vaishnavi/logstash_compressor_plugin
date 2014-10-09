/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gzipplugin;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author msumit
 */
public class GzipPlugin {

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
         String string = "vaaaaaaaaaaaaaiiiiiiiiiiiisssssssss";
        System.out.println("after compress:");
        String compressed = compress(string);
        System.out.println(compressed);
        System.out.println("after decompress:");
        String decomp = decompress(compressed);
        System.out.println(decomp);*/
    //}
        public byte[] compress(String str) throws IOException {
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();           
            return out.toByteArray();
     }
    

    public String decompress(byte[] bytes) throws Exception {
        byte[] arr =new byte[1000];
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis));
        String outStr = "";
        String line;
        while ((line=bf.readLine())!=null) {
          outStr += line;
        }
        return outStr;
     }
    
    public String decompressb64(String input) throws Exception {
        
        byte[] bytes=Base64.decode(input);
        byte[] arr =new byte[1000];
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis));
        String outStr = "";
        String line;
        while ((line=bf.readLine())!=null) {
          outStr += line;
        }
        return outStr;
     }
}

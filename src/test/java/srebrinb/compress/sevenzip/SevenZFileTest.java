/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srebrinb.compress.sevenzip;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sbalabanov
 */
public class SevenZFileTest {
    
    public SevenZFileTest() {
    }

    /**
     * Test of close method, of class SevenZFile.
     */
    @Test
    public void testUnzip() throws Exception {
        System.out.println("Unzip");
      //  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // br.readLine();
        long startTime = System.currentTimeMillis();
        File file = new File("jSevenZip.7z");//new File("src\\test\\resources\\1K_10blocks.7z");
        SevenZFile archive = new SevenZFile(file);
     //   Iterable<SevenZArchiveEntry> entries = sevenZ.getEntries();
        SevenZArchiveEntry entry;
        int i=0;
        while ((entry = archive.getNextEntry()) != null) {
            
            if (!entry.getName().endsWith("pom.xml")){
                continue;
            }
                
           
                
            entry.getWindowsAttributes();
        
                byte[] b;
                b = new byte[(int)entry.getSize()];
                System.out.println("b = " + (int)entry.getSize());
                archive.read(b);
                ByteArrayOutputStream output=new ByteArrayOutputStream();
                IOUtils.write(b, output);                
                System.out.println(entry.getName()+"\t"+output.size()+"\t"+ new String(output.toByteArray()));
                output.close();
        }
        long endTime = System.currentTimeMillis();
        
    }
    @Test
    public void testGetBlocls() throws Exception {
        System.out.println("GetBlocls");
        long startTime = System.currentTimeMillis();
        File fileArh = new File("test_pack_PDF.7z");
        SevenZFile archive7zip = new SevenZFile(fileArh);  
        Archive archive = archive7zip.getArchive();
        System.out.println("archive = " + archive);
        
//        SevenZArchiveEntry[] files = archive.files;
//        for (SevenZArchiveEntry file : files) {
//            System.out.println("file = " + file.getName());
//        }
        
        Folder[] folders = archive.folders;
        for (Folder folder : folders) {
            System.out.println("folder = " + folder);
            int unpackSubStreams=folder.numUnpackSubStreams;
            System.out.println("unpackSubStreams = " + unpackSubStreams);
            
            
            BindPair[] bindPairs = folder.bindPairs;
            for (BindPair bindPair : bindPairs) {
                
                System.out.println("bindPair = " + bindPair);
            }
        }        
        
//        ArrayList<InputStream> fileStreams=archive7zip.getStreamByIndex(0);        
//     
//        System.out.println("fileStreams = " + fileStreams.size());
//        IOUtils.copy(fileStream, new FileOutputStream("src\\test\\resources\\block"+100+".lzma"));
//        fileStream.close();
//        HashMap fileName = archive7zip.getMapFilename();        
//        System.out.println("fileName = " + fileName.get("test_30"));
//        InputStream fileStreamnew=archive7zip.getStreamByName("test_30");
//        IOUtils.copy(fileStreamnew, new FileOutputStream("src\\test\\resources\\test_30"));
        /* for (SevenZArchiveEntry file : files) {
        System.out.println("file = " + file.toString());
        }
         */
    }
    
}

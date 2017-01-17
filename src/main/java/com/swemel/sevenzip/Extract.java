/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swemel.sevenzip;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import srebrinb.compress.sevenzip.SevenZArchiveEntry;
import srebrinb.compress.sevenzip.SevenZFile;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.io.IOUtils;
/**
 *
 * @author sbalabanov
 */
public class Extract {

    public static void main(String[] args) throws IOException {
        String fileName="D:\\incubator\\commpressors\\test_100K_2_b.7z";
    //    SevenZFile sevenZFile = new SevenZFile(new File("D:\\incubator\\commpressors\\test_100K_2_b.7z"));
        byte[] inputData=IOUtils.toByteArray(new FileInputStream( fileName)); // 7z archive contents
        
        SeekableInMemoryByteChannel inMemoryByteChannel = new SeekableInMemoryByteChannel(inputData);
      SevenZFile sevenZFile = new SevenZFile( inMemoryByteChannel);
       
        
      SevenZArchiveEntry entry;
        Iterable<SevenZArchiveEntry> entrys = sevenZFile.getEntries();
        for (SevenZArchiveEntry entry1 : entrys) {
            String name = entry1.getName();
            System.out.println("name = " + name+"\t"+entry1.toString());
        }
         ArrayList<InputStream> strams = sevenZFile.getStrams();
        String str = IOUtils.toString(strams.get(10));
        System.out.println("str = " + str);
        //   entry = sevenZFile.getNextEntry();
        //   sevenZFile.read();  // read current entry's data
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author sbalabanov
 */
public class MyTest {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
br.readLine();
        SevenZFile sevenZFile = new SevenZFile(new File("test_pack_b1000_ok.7z"));
        Iterable<SevenZArchiveEntry> entries = sevenZFile.getEntries();
        int pos=0;
        for (SevenZArchiveEntry entry : entries) {
            System.out.println("entry = " + entry.getName()); 
            pos+=entry.getCompressedSize();
            if (entry.getName().endsWith("BG123526430.123526430_1046887085.pdf")){
               byte[] content = new byte[(int)entry.getSize()];
                sevenZFile.read(content, pos, content.length - 0);
                IOUtils.write(content,new FileOutputStream("BG123526430.123526430_1046887085.pdf"));
            }
        }
        
    }
}

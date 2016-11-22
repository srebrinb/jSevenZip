/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swemel.sevenzip;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sbalabanov
 */
public class SevenZipTest {
    
    public SevenZipTest() {
    }

    /**
     * Test of createArchive method, of class SevenZip.
     */
    @Test
    public void testCreateArchive() throws Exception {
        System.out.println("createArchive");
        File file1=new File("db://blob/test_1");
        File file2=new File("db://blob/test_2");
        File[] files={file1,file2};
        SevenZip sevenZip = new SevenZip("D:\\work\\compress\\SevenZip\\src\\test\\resources\\standard_2_1block.7z", files);
        sevenZip.createArchive();
    }    
}

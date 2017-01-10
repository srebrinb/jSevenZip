/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srebrinb.compress.sevenzip;

import srebrinb.compress.sevenzip.SevenZMethod;
import srebrinb.compress.sevenzip.SevenZArchiveEntry;
import srebrinb.compress.sevenzip.SevenZOutputFile;
import static srebrinb.compress.sevenzip.BaseParams.resPath;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sbalabanov
 */
public class SevenZOutputFileTest {
    String mockXML;
    public SevenZOutputFileTest() throws IOException {
        mockXML=IOUtils.toString(new FileInputStream(resPath+"mock.xml"));
    }

    /**
     * Test of setContentCompression method, of class SevenZOutputFile.
     */
    @Test
    public void testContentCompression() throws IOException {
        System.out.println("ContentCompression");
        File file = new File(resPath+"1K_xml_mock.7z");
        file.delete();
        SevenZOutputFile sevenZOutput = new SevenZOutputFile(file);
        SevenZMethod method=SevenZMethod.LZMA2;
        sevenZOutput.setContentCompression(method);
        int limit=2;
        for (int i = 1; i <= limit; i++) {
            File fileToArchive = new File(resPath+"1");
            SevenZArchiveEntry entry = sevenZOutput.createArchiveEntry(fileToArchive, "test_"+i);
            sevenZOutput.putArchiveEntry(entry);            
            sevenZOutput.write(mockXML.replaceAll("#NUMBER",String.format("%010d", i)) .getBytes());   
            sevenZOutput.closeArchiveEntry();
            
 
        }
        
        sevenZOutput.close();
        
    }
    
}

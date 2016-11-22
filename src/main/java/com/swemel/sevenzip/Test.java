package com.swemel.sevenzip;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: sokolov_a
 * Date: 26.04.2011
 * Time: 17:19:39
 */
class Test {
    public static void main(String[] args) {
        try {
            SevenZip sevenZip = new SevenZip("D:\\work\\compress\\SevenZip\\src\\test\\resources\\standard_2_1block.7z", new File("D:\\work\\compress\\SevenZip\\src\\test\\resources\\1K_xml_mock\\test_1"),new File("D:\\work\\compress\\SevenZip\\src\\test\\resources\\1K_xml_mock\\test_2"));
            sevenZip.createArchive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


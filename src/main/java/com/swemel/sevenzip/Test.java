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
            SevenZip sevenZip = new SevenZip("test_ok.7z", new File("invoices"));
            sevenZip.createArchive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

